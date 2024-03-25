import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Mining extends JPanel {

    private Inventory inventory; // Reference to the Inventory object

    private JProgressBar progressBar;
    private JButton autoScavengeButton;
    private JButton autoMineButton; // Button for automatic woodcutting
    private Timer timer;
    private Image bgImage;
    private JLabel grantedLabel; // Label to display wood granted message
    private boolean auto; // flag that turns auto processes on or off
    private boolean currentlyScavenge = false; // flag to determine if process is scavengeing. Used to grant correct resource.
    private boolean currentlyMining = false; // flag to determine if process is mining. Used to grant correct resource.

    public Mining(Inventory inventory) { // Accepts an Inventory object
        this.inventory = inventory; // Assign the Inventory object to the local variable

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
                SFX.playSound("assets/SFX/.wav"); // TODO: play scavenging sound 
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
                SFX.playSound("assets/SFX/pickaxe-sfx.wav"); 
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                auto = false; // stop auto ptocess when leaving panel
                currentlyScavenge = false;
                currentlyMining = false;
                timer.stop(); // stop mining process
                autoMineButton.setText("Mine Ore"); // reset mining label
                autoScavengeButton.setText("Scavenge Area"); // reset scavenge label
                
                Driver.changePanel("world");
                SFX.stopSound();
                MusicPlayer.playMusic("assets/Music/Brilliant1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for automatic process
        // Progress bar takes 11 seconds to fill up
        timer = new Timer(1100, new ActionListener() {
            int progress = 0;
            int ore = 1;
            int scavenge = 1; 

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 10) {
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
                            SFX.playSound("assets/SFX/stone-Scavengeing-sfx.wav"); 

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
                            grantedLabel.setText("Spleenwort granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav"); // TODO: play correct sfx 

                            int currentSpleenwort = inventory.getResource("Spleenwort");
                            // Increment Spleenwort resource variable
                            inventory.setResource("Spleenwort", currentSpleenwort + 1);

                        } else {
                            grantedLabel.setText("Tongue Fern granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav");  // TODO: add magical essence sfx

                            int currentTongueFern = inventory.getResource("Tongue Fern");
                            // Increment TongueFern resource variable
                            inventory.setResource("Tongue Fern", currentTongueFern + 1);
                        }
                        }
                    //update resource in inventory
                    inventory.updateResourceLabels();
                    progress = 0;
                    ore++;
                    scavenge++;
                    if (!auto) {
                        timer.stop();  
                    } else {
                        SFX.playSound("assets/SFX/pickaxe-sfx.wav");
                        // TODO: ADD appropriate sfx sound scavenging or mining 
                    }
                } else {
                    progress++;
                    progressBar.setValue(progress * 10);
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
            autoScavengeButton.setText("Scavenge Area");
        }
    }

    // Method to start/stop the automatic woodcutting process
    private void autoMineOre() {
        if (!auto) {
            auto = true; // Start auto mining
            autoMineButton.setText("Stop Mining Ore...");
            grantedLabel.setText(""); 
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto woodcutting
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
