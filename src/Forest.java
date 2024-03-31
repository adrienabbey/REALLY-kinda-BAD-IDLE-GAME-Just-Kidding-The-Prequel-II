/*
 * Woodcutting Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
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
 * Implements the "Forest" panel acessed through the World screen. Allows the player to cut wood or hunt animals. Uses a JSlider to track progress and after each a process finishes a grant message will pop update indicting which respective resourve was granted to the player. This item will be added to the player's inventory. 
 * 
 * Current reources that can be gathered at the mineshaft: wood, meat, pelt...
 * 
 * TODO: add gradient color to bar. 
 * Add levels to the forest. Deeper levels can be a way to add more items to the game. Player can unlock deeper levels by increasing their woodcutting/hunting stat. Can use a different bgm and bg image for new levels. Maybe even add secret diaog options when unlocking new levels, such as new dialog messages in the tavern or in the forest itself. 
 */
public class Forest extends JPanel {
    Inventory inventory = Inventory.getInstance(); // initialize the singeton inventory object

    private JProgressBar progressBar; 
    private JButton autoHuntButton; // Button to activate hunting
    private JButton autoCutButton; // Button to activate woodcutting
    private Timer timer;
    private Image bgImage;
    private JLabel grantedLabel; // Label to display wood granted message
    private boolean auto = false;
    private boolean currentlyCutting = false; // flag to determine if process is cutting. Used to grant correct resource.
    private boolean currentlyHunting = false; // flag to determine if process is hunting. Used to grant correct resource.
    private boolean resetProgress = true;
    private int huntIncrement = 0; // used to determine whether to grant meat or pelt when hunting


    public Forest() { // Accepts an Inventory object
    
        // Load the background image
        try {
            bgImage = ImageIO.read(new File("assets/images/forest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the layout with vertical alignment and padding
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(950, 20, 0, 20)); // Add padding around the panel


        // Format the "Hunt Wildlife" button
        autoHuntButton = new JButton("Hunt Wildlife");
        autoHuntButton.setFont(new Font("Serif", Font.ITALIC, 26));
        autoHuntButton.setForeground(new Color(205, 133, 63)); // Light wood color
        autoHuntButton.setBackground(new Color(0,100,0)); // Set the background color to a transparent green
        autoHuntButton.setOpaque(true); // Make the background visible
        autoHuntButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Cut Tree' button
        autoCutButton = new JButton("Cut Tree");
        autoCutButton.setFont(new Font("Serif", Font.ITALIC, 26));
        autoCutButton.setForeground(new Color(205, 133, 63)); // Light wood color
        autoCutButton.setBackground(new Color(0,100,0)); // Set the background color to a transparent green
        autoCutButton.setOpaque(true); // Make the background visible
        autoCutButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Leave' button
        JButton leave = new JButton("Leave");
        leave.setFont(new Font("Serif", Font.ITALIC, 26));
        leave.setForeground(new Color(205, 133, 63)); // Light wood color
        leave.setBackground(new Color(0,100,0)); // Set the background color to a transparent green
        leave.setOpaque(true); // Make the background visible
        leave.setFocusPainted(false); // Remove focus ring around the button

        // Create the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Serif", Font.ITALIC, 21));
        progressBar.setForeground(new Color(205, 133, 63)); // Light wood color
        progressBar.setBackground(new Color(0,100,0)); // Set the background color to a transparent green
        progressBar.setOpaque(true); // Make the background visible
        progressBar.setPreferredSize(new Dimension(10, 20)); // Set the preferred size of the progress bar


        // Create label for wood granted message
        grantedLabel = new JLabel("");
        grantedLabel.setFont(new Font("Serif", Font.BOLD, 21));
        grantedLabel.setForeground(Color.GREEN); // Green color for wood granted message

        // Add components to the panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.add(autoHuntButton);
        buttonPanel.add(autoCutButton);
        buttonPanel.add(leave);
        add(buttonPanel, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.CENTER);
        add(grantedLabel, BorderLayout.NORTH); // Add wood granted label to the panel

        // Action listener for the 'Cut Wood' button
        autoHuntButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyHunting = true;
                currentlyCutting = false;
                autoHunt(); // Start hunting process
                if (auto) {
                    SFX.playSound("assets/SFX/hunting-sfx3.wav"); //play hunting sfx
                }
            }
        });

        // Action listener for the 'Auto Cut' button
        autoCutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyHunting = false;
                currentlyCutting = true;
                autoCutWood(); // Start/Stop auto woodcutting process
                if (auto) {
                    SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play woodcutting sound effect only when starting woodcutting
                }
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                timer.stop();
                progressBar.setValue(0);
                //((Timer)e.getSource()).stop();
                auto = false; // stop auto mining if left panel
                resetProgress = true;
                currentlyHunting = false; // set hunting flag to default
                currentlyCutting = false; // set cutting flag to default
                // timer.stop(); // stop woodcutting process
                autoCutButton.setText("Cut Tree"); // reset autocutting label
                autoHuntButton.setText("Hunt Wildlife"); // reset autohunting label

                Driver.changePanel("world");
                SFX.stopAllSounds(); 
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
                SFX.playSound("assets/SFX/interface1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for woodcutting/hunting process. 
        // Progress variable increases by 1 every 100 milliseconds. Progress variable needs to equal 100 for progress bar to fill up completely. Currently, akes 10 seconds to fill up.
        timer = new Timer(100, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
            if (resetProgress) { 
                progress = 0; // reset progress to zero if left screen prior. 
                resetProgress = false;
            }

            if (progress == 40) {
                grantedLabel.setText(""); // erase grant label
            }
                if (progress >= 100) {  // when progress reaches 100 set the progress bar to 100 and proceed granting resource logic and looping back the timer. 
                    progressBar.setValue(100);

                    // if player was cutting tree grant wood
                    if (currentlyCutting) {
                    grantedLabel.setText("Wood granted!"); // Update wood granted label
                    SFX.playSound("assets/SFX/wood-gathering-sfx.wav");

                    int currentWood = inventory.getResource("Wood");
                    // Increment wood resource variable
                    inventory.setResource("Wood", currentWood + 1);
                    }

                    // if player was hunting animal grant meat or pelt
                    if (currentlyHunting) {
                        if (huntIncrement % 3 == 0) {
                        grantedLabel.setText("Pelt granted!"); // Update granted label
                        SFX.playSound("assets/SFX/.wav"); // TODO: add meat-gathering sfx
    
                        int currentPelt = inventory.getResource("Pelt");
                        // Increment wood resource variable
                        inventory.setResource("Pelt", currentPelt + 1);
                        } else {
                            grantedLabel.setText("Meat granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav"); // TODO: add pelt gathering sfx
        
                            int currentMeat = inventory.getResource("Meat");
                            // Increment wood resource variable
                            inventory.setResource("Meat", currentMeat + 1);
                            }
                        huntIncrement++;
                        }   
                    
                    // Update resource in inventory
                    inventory.updateResourceLabels();

                    progress = 0;
                    if (!auto) {
                        timer.stop();
                        // SFX.stopAllSounds();
                    } else if (currentlyCutting) {
                        SFX.stopAllNonLoopingSounds();
                        SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play sfx again
                        // 
                    } else {
                        SFX.stopAllNonLoopingSounds();
                        SFX.playSound("assets/SFX/hunting-sfx3.wav"); //play hunting sfx
                    }
                } else {
                    progress++;// increment progress by 1
                    progressBar.setValue(progress); // set progress bar to progress value
                }
            }
        });
    }

    // Method to start/stop the automatic woodcutting process
    private void autoHunt() {
        if (!auto) {
            auto = true; // Start auto hunting
            autoHuntButton.setText("Stop Hunting...");
            grantedLabel.setText(""); 
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto hunting
            timer.stop(); // stop timer
            SFX.stopAllNonLoopingSounds();
            autoHuntButton.setText("Hunt Wildlife");
        }
    }

    // Method to start/stop the automatic woodcutting process
    private void autoCutWood() {
        if (!auto) {
            auto = true; // Start auto woodcutting
            autoCutButton.setText("Stop Cutting Tree...");
            grantedLabel.setText(""); 
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto woodcutting when method is called again
            timer.stop(); // stop timer
            SFX.stopAllNonLoopingSounds();
            autoCutButton.setText("Cut Tree");
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
