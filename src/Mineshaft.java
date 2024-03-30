/*
 * Mineshaft Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Implements the "Mineshaft" panel accessed through the World screen. Allows the player to mine ore or gather resources. Uses a JProgressBar to track progress. After each a process finishes a grant message will pop up to indictie which respective resource was granted to the player. This item will be added to the player's inventory.
 * 
 * Current reources that can be gathered at the mineshaft: Stone, Metal, Spleenwort, Tongue Fern, Magical Essence...
 * 
 * TODO: add gradient color to bar. 
 * Add levels to the mineshaft. Deeper levels can be a way to add more items to the game. Player can unlock deeper levels by increasing their mining/scavenging stat. Can use a different bgm and bg image for new levels. Maybe even add secret diaog options when unlocking new levels, such as new dialog messages in the tavern or in the mineshaft itself. 
 */
public class Mineshaft extends JPanel {

    Inventory inventory = Inventory.getInstance(); //initialize singleton inventory object
    private JProgressBar progressBar;
    private JButton autoScavengeButton; // button to activate scavenging
    private JButton autoMineButton; // Button to activate mining
    private Timer timer;
    private Image bgImage;
    private JLabel grantedLabel; // Label to display wood granted message
    private boolean auto; // flag that turns auto processes on or off
    private boolean currentlyScavenge = false; // flag to determine if process is scavengeing. Used to grant appropriate resource.
    private boolean currentlyMining = false; // flag to determine if process is mining. Used to grant appropriate resource.
    private boolean resetProgress = true; // flag used to determine if player had left the mineshaft panel. If true reset the progress value to 0.

    public Mineshaft() { // Accepts an Inventory object
        // Load the background image
        try {
            bgImage = ImageIO.read(new File("assets/images/mineshaft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the layout with vertical alignment and padding
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(950, 20, 0, 20)); // Add padding around the panel

        // Create the 'Scaveneg Area' button
        autoScavengeButton = new JButton("Scavenge Area");
        autoScavengeButton.setFont(new Font("Serif", Font.ITALIC, 28));
        autoScavengeButton.setForeground(new Color(205, 133, 63)); // Light wood color
        autoScavengeButton.setBackground(new Color(0, 0, 0)); // Set the background color to black
        autoScavengeButton.setOpaque(true); // Make the background visible
        autoScavengeButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Mine Ore' button
        autoMineButton = new JButton("Mine Ore");
        autoMineButton.setFont(new Font("Serif", Font.ITALIC, 26));
        autoMineButton.setForeground(new Color(205, 133, 63)); // Light wood color
        autoMineButton.setBackground(new Color(0, 0, 0)); // Set the background color to black
        autoMineButton.setOpaque(true); // Make the background visible
        autoMineButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Leave' button
        JButton leave = new JButton("Leave");
        leave.setFont(new Font("Serif", Font.ITALIC, 26));
        leave.setForeground(new Color(205, 133, 63)); // Light wood color
        leave.setBackground(new Color(0, 0, 0)); // Set the background color to black
        leave.setOpaque(true); // Make the background visible
        leave.setFocusPainted(false); // Remove focus ring around the button

        // Create the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Serif", Font.ITALIC, 24));
        progressBar.setForeground(new Color(205, 133, 63)); // Light wood color
        progressBar.setBackground(new Color(0, 0, 0)); // Set the background color to black
        progressBar.setOpaque(true); // Make the background visible
        progressBar.setPreferredSize(new Dimension(10, 20)); // Set the preferred size of the progress bar

        // Create label for ore granted message
        grantedLabel = new JLabel("");
        grantedLabel.setFont(new Font("Serif", Font.BOLD, 24));
        grantedLabel.setForeground(Color.GREEN); // Green color for ore granted message

        // Add components to the panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(autoScavengeButton);
        buttonPanel.add(autoMineButton);
        buttonPanel.add(leave);
        add(buttonPanel, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.CENTER);
        add(grantedLabel, BorderLayout.NORTH); // Add ore granted label to the panel

        // Action listener for the 'Scavenge Area' button
        autoScavengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyScavenge = true;
                currentlyMining = false;
                autoScavenge();
                if (auto) {
                    SFX.playSound("assets/SFX/.wav"); // TODO: play scavenge sfx only when starting scavenging. 
                }
            }
        });

        // Action listener for the 'Auto Mine' button
        autoMineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyScavenge = false;
                currentlyMining = true;
                autoMineOre();
                if (auto) {
                    SFX.playSound("assets/SFX/pickaxe-sfx.wav"); // play sound effect only when starting, not stopping, mining
                }
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                timer.stop(); // stop auto process when leaving
                progressBar.setValue(0); // reset progress bar
                auto = false; // stop auto ptocess when leaving panel
                resetProgress = true; // will reset progress to 0 when reentering mineshaft
                currentlyScavenge = false; 
                currentlyMining = false;
                autoMineButton.setText("Mine Ore"); // reset mining label
                autoScavengeButton.setText("Scavenge Area"); // reset scavenge label
                
                Driver.changePanel("world");
                SFX.stopAllSounds();
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
                SFX.playSound("assets/SFX/interface1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for automatic process
        // Progress variable increases by 1 every 100 milliseconds. Progress variable needs to equal 100 for progress bar to fill up completely. Takes 10 seconds to fill up.
        timer = new Timer(120, new ActionListener() {
            int progress = 0;
            int ore = 1; // used to track which resource to grant
            int scavenge = 1; // used to track which resource to grant

            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetProgress) { 
                    progress = 0; // reset progress to zero if left screen prior. 
                    resetProgress = false;
                }

                if (progress == 40) {
                    grantedLabel.setText(""); // erase grant label when progress reaches 40
                }
                
                if (progress >= 100) {
                    progressBar.setValue(100);

                    if (currentlyMining) {
                        if (ore % 5 == 0) {
                            grantedLabel.setText("Metal granted!"); // Update metal granted label
                            SFX.playSound("assets/SFX/metal-ringing1.wav"); 

                            int currentMetal = inventory.getResource("Metal");
                            // Increment metal resource variable
                            inventory.setResource("Metal", currentMetal + 1);

                        } else {
                            grantedLabel.setText("Stone granted!"); // Update stone granted label
                            SFX.playSound("assets/SFX/stone-gathering-sfx.wav"); 

                            int currentStone = inventory.getResource("Stone");
                            // Increment stone resource variable
                            inventory.setResource("Stone", currentStone + 1);
                            //update resource in inventory
                            }
                        }
                    
                    if (currentlyScavenge) {
                        if (scavenge % 5 == 0) {
                            grantedLabel.setText("Magical Essence granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav");  // TODO: add magical essence sfx

                            int currentMagicalEssence = inventory.getResource("Magical Essence");
                            // Increment MagicalEssence resource variable
                            inventory.setResource("Magical Essence", currentMagicalEssence + 1);

                        } else if (scavenge % 3 == 0) {
                            grantedLabel.setText("Tongue Fern granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav");  // TODO: add magical essence sfx

                            int currentTongueFern = inventory.getResource("Tongue Fern");
                            // Increment TongueFern resource variable
                            inventory.setResource("Tongue Fern", currentTongueFern + 1);
                    
                        } else {
                            grantedLabel.setText("Spleenwort granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav"); // TODO: play correct sfx 

                            int currentSpleenwort = inventory.getResource("Spleenwort");
                            // Increment Spleenwort resource variable
                            inventory.setResource("Spleenwort", currentSpleenwort + 1);
                            }
                        }
                    //update resource in inventory
                    inventory.updateResourceLabels();
                    progress = 0;
                    ore++;
                    scavenge++;
                    if (!auto) {
                        timer.stop();  
                    } else if (currentlyMining) {
                        SFX.stopAllSounds();
                        SFX.playSound("assets/SFX/pickaxe-sfx.wav");
                    } else {
                        SFX.stopAllSounds();
                        // TODO: ADD scavenging sfx 
                    }
                } else {
                    progress++; ;// increment progress by 1
                    progressBar.setValue(progress); // set progress bar to progress value
                }
            }
        });
    }

    // Method to start/stop the automatic scavenging process
    private void autoScavenge() {
        if (!auto) {
            auto = true; // Start auto scavenging
            autoScavengeButton.setText("Stop Scavenging...");
            grantedLabel.setText(""); 
            timer.start(); // Start the timer for auto scavenging
        } else {
            auto = false; // Stop auto scavenging
            timer.stop(); //stop timer
            SFX.stopAllSounds();
            autoScavengeButton.setText("Scavenge Area");
        }
    }

    // Method to start/stop the automatic mining process
    private void autoMineOre() {
        if (!auto) {
            auto = true; // Start auto mining
            autoMineButton.setText("Stop Mining Ore...");
            grantedLabel.setText(""); 
            timer.start(); // Start the timer for auto mining
        } else {
            auto = false; // Stop auto mining
            timer.stop(); //stop timer
            SFX.stopAllSounds();
            autoMineButton.setText("Mine Ore");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (bgImage != null) {
            //scale it to the panel size
            Image scaledImage = bgImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);
        }
    }
}
