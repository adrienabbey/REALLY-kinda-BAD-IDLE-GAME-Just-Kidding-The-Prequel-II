/*
 * Forest Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements the "Forest" panel acessed through the World screen. Allows the player to cut wood or hunt animals. Uses a JSlider to track progress and after each a process finishes a grant message will pop update indicting which respective resourve was granted to the player. This item will be added to the player's inventory. 
 * 
 * Current reources that can be gathered at the mineshaft: wood, meat, pelt...
 * 
 * TODO: add gradient color to bar. 
 * Add levels to the forest. Deeper levels can be a way to add more items to the game. Player can unlock deeper levels by increasing their woodcutting/hunting stat. Can use a different bgm and bg image for new levels. Maybe even add secret diaog options when unlocking new levels, such as new dialog messages in the tavern or in the forest itself. 
 */
public class Forest extends JPanel {

    //========================================================
    // Fields
    //========================================================
    // These are used for formating the gui elements
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 82;

    // Components and variables used in forest screen
    private JProgressBar progressBar;
    private JButton autoHuntButton; // Button to activate hunting
    private JButton autoCutButton; // Button to activate woodcutting
    private JButton statusButton;
    private Timer timer; // timer for gathering processes
    private Image bgImage;
    private JLabel harvestedLabel; // Label to display wood harvested message
    private boolean auto = false;
    private boolean currentlyCutting = false; // flag to determine if process is cutting. Used to grant correct resource.
    private boolean currentlyHunting = false; // flag to determine if process is hunting. Used to grant correct resource.
    private boolean resetProgress = true;
    private int huntIncrement = 0; // used to determine whether to grant meat or pelt when hunting
    private String downArrow = "\u25BC"; // Unicode for Down-Pointing Triangle
    private String upArrow = "\u25B2"; // Unicode for Up-Pointing Triangle
    // https://www.vertex42.com/ExcelTips/unicode-symbols.html
    // https://www.vertex42.com/ExcelTips/unicode-symbols.html
    private boolean statusBarOpen = false; // flag used to determine when the status bar is open
    private JPanel statusBar;
    private JProgressBar health;
    private JProgressBar magic;
    private JButton gold;
    
    final private Color customColorBeige = new Color(253, 236, 166);
    private Border forestBorder = BorderFactory.createLineBorder(Color.white, 1);



    //========================================================
    // Constructor
    //========================================================
    public Forest() {
    
        // Load the background image
        try {
            bgImage = ImageIO.read(new File("assets/images/forest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(null); //Set layout of screen to null
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorGreen = new Color(0, 100, 0);
        autoHuntButton = new JButton("🏹 Hunt Wildlife");
        buttons.add(autoHuntButton);
        autoCutButton = new JButton("⁋ Cut Tree");
        buttons.add(autoCutButton);
        statusButton = new JButton(downArrow);
        buttons.add(statusButton);
        JButton leave = new JButton("Leave");
        buttons.add(leave);

        // Formatting all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            // buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);

            buttons.get(i).setPreferredSize(new Dimension(200, 45));
            buttons.get(i).setMaximumSize(new Dimension(100, 80));
            buttons.get(i).setBackground(customColorGreen);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setBorder(forestBorder);
            buttons.get(i).setFont(new Font("Serif", Font.ITALIC, buttonFont));
            buttons.get(i).setOpaque(true); // Make the background visible
            buttons.get(i).setFocusPainted(false); // Remove focus ring around the button
        }
        statusButton.setFont(new Font("Times New Roman", Font.BOLD, buttonFont));

        // Create the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Serif", Font.ITALIC, buttonFont));
        progressBar.setForeground(new Color(205, 133, 63)); // Light wood color
        progressBar.setBackground(new Color(0, 100, 0)); // Set the background color to a transparent green
        progressBar.setBorder(forestBorder);
        progressBar.setOpaque(true); // Make the background visible
        progressBar.setPreferredSize(new Dimension(10, buttonFont)); // Set the preferred size of the progress bar

        // Create label for wood harvested message
        harvestedLabel = new JLabel("");
        harvestedLabel.setFont(new Font("Serif", Font.BOLD, buttonFont));
        harvestedLabel.setForeground(Color.GREEN); // Green color for wood harvested message


        //===============================================================================
        //
        // Relatively scaling and sizing components
        //
        //
        //
        JPanel buttonPanel = new JPanel(new GridLayout()); // Initialize button panel to house action buttons

        // Set position and size of panel, label, and progress bar using relative scaling
        buttonPanel.setBounds((int) (width * 0.0134), (int) (height * 0.9500), (int) (width * 0.9713), (int) (height * 0.0483)); 
        harvestedLabel.setBounds((int) (width * 0.0134), (int) (height * 0.8499), (int) (width * 0.156), (int) (height * 0.0545));
        progressBar.setBounds((int) (width * 0.0134), (int) (height * 0.8962), (int) (width * 0.9713), (int) (height * 0.0573));
        statusButton.setBounds((width - (int) (width * 0.03125)) / 2, 0,  (int) (width * 0.03125), (int) (height * 0.04166));// Set the position and size of the button
  
        //================================================================================

        // add buttons to button panel
        buttonPanel.add(autoHuntButton);
        buttonPanel.add(autoCutButton);
        buttonPanel.add(leave);
        add(buttonPanel); // add panel that contains hunt wildlife, cut wood, and leave buttons to layout
        add(progressBar); // add progress bar to layout
        add(harvestedLabel); // Add harvested label to panel
        add(statusButton); // Add the button to the panel


        //---
        //
        // Methods for actions listeners
        //
        //
        // Action listener for the 'status' button
        autoCutButton.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyHunting = false;
                currentlyCutting = true;
                autoCutWood(); // Start/Stop auto woodcutting process
                if (auto) {
                    SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play woodcutting sound effect only when starting woodcutting
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Action listener for the 'hunt' button
        autoHuntButton.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyHunting = true;
                currentlyCutting = false;
                autoHunt(); // Start hunting process
                if (auto) {
                    SFX.playSound("assets/SFX/hunting-sfx5.wav"); 
                    // play hunting sfx
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Action listener for the Status button
        statusButton.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            if (!statusBarOpen) {
                statusButton.setBounds((width - (int) (width * 0.03125)) / 2, (int) (height * 0.0453),  (int) (width * 0.03125), (int) (height * 0.04166));// Set the position and size of the button
                statusButton.setText(upArrow);
                statusBar = new JPanel(new GridLayout()); // assign statusbar

                // assign buttons to shown character statuses
                health = new JProgressBar(0, Driver.getPlayer().getMaxHealth());
                health.setStringPainted(true); // Enable string painting
                health.setValue((int) Driver.getPlayer().getHealth());
                health.setString("Health: " + health.getValue());
                magic = new JProgressBar(0, Driver.getPlayer().getMaxMagic());
                magic.setStringPainted(true); // Enable string painting
                magic.setValue((int) Driver.getPlayer().getMagic());
                magic.setString("Magic: " + magic.getValue());
                gold = new JButton("Gold: " + Driver.player.getGold());

                // format buttons
                health.setForeground(Color.red);
                health.setBackground(new Color(255, 153, 153)); // light-red
                magic.setForeground(Color.blue);
                magic.setBackground(new Color(153, 204, 255)); // light-blue
                gold.setBackground(Color.yellow);
        
                health.setFont(new Font("Times New Roman", Font.PLAIN, buttonFont));
                magic.setFont(new Font("Times New Roman", Font.PLAIN, buttonFont));
                gold.setFont(new Font("Times New Roman", Font.PLAIN, buttonFont));
                
                // add components to status bar
                gold.setBackground(Color.yellow);
                statusBar.add(health);
                statusBar.add(magic);
                statusBar.add(gold);
                statusBar.setBounds((int) (width * 0.0104), (int) (height * 0.0), (int) (width * 0.9714),
                        (int) (height * 0.04629)); // set location and size of status bar
                add(statusBar);
                revalidate();
                repaint();
                statusBarOpen = true; // set statusBarOpen to true
            } else {
                remove(statusBar); // remove the status bar from the screen
                statusButton.setBounds((width - (int) (width * 0.03125)) / 2, 0,  (int) (width * 0.03125), (int) (height * 0.04166));// Set the position and size of the button
                statusButton.setText(downArrow);
                revalidate();
                repaint();
                statusBarOpen = false;
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                if (statusBarOpen) {
                    remove(statusBar);
                    statusButton.setText(downArrow);
                    statusBarOpen = false;
                statusButton.setBounds((width - (int) (width * 0.03125)) / 2, 0,  (int) (width * 0.03125), (int) (height * 0.04166));// Reset status button position
                }
                timer.stop();
                progressBar.setValue(0);
                auto = false; // stop auto mining if left panel
                resetProgress = true;
                currentlyHunting = false; // set hunting flag to default
                currentlyCutting = false; // set cutting flag to default
                // timer.stop(); // stop woodcutting process
                autoCutButton.setText("⁋ Cut Tree"); // reset autocutting label
                autoHuntButton.setText("🏹 Hunt Wildlife"); // reset autohunting label

                Driver.changePanel("world");
                SFX.stopAllSounds(); 
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
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
                    harvestedLabel.setText(""); // erase grant label
                }
                if (progress >= 100) { // when progress reaches 100 set the progress bar to 100 and proceed granting resource logic and looping back the timer.
                    progressBar.setValue(100);

                    // regenerate magic by 10% when player harvests a resource
                    if (Driver.getPlayer().getMagic() < Driver.getPlayer().getMaxMagic()) { // if current magic is less than maximum magic
                        double magicRegenRate = Driver.getPlayer().getMaxMagic() * 0.1;
                        Driver.getPlayer().setMagic(Driver.getPlayer().getMagic() + magicRegenRate); // regenerate magic by 10% of max mana
                        magic.setValue((int) Driver.getPlayer().getMagic());
                        magic.setString("Magic: " + magic.getValue());
                    }

                    // regenerate health by 10% when player harvests a resource
                    if (Driver.getPlayer().getHealth() < Driver.getPlayer().getMaxHealth()) { // if current Health is less than maximum Health
                        double HealthRegenRate = Driver.getPlayer().getMaxHealth() * 0.1;
                        Driver.getPlayer().setHealth(Driver.getPlayer().getHealth() + HealthRegenRate); // regenerate Health by 10% of max mana
                        health.setValue((int) Driver.getPlayer().getHealth());
                        health.setString("Health: " + health.getValue());
                    }

                    // if player was cutting tree grant wood
                    if (currentlyCutting) {
                        harvestedLabel.setText("Wood harvested!"); // Update wood harvested label
                        SFX.playSound("assets/SFX/wood-gathering-sfx.wav");

                        int currentWood = Driver.player.inventory.getResource("Wood");
                        // Increment wood resource variable
                        Driver.player.inventory.setResource("Wood", currentWood + 1);
                    }

                    // if player was hunting animal grant meat or pelt
                    if (currentlyHunting) {
                        if (huntIncrement % 3 == 0) {
                        harvestedLabel.setText("Pelt harvested!"); // Update harvested label
                        SFX.playSound("assets/SFX/cloth-heavy.wav"); // pelt gathering sfx
    
                        int currentPelt = Driver.player.inventory.getResource("Pelt");
                        // Increment wood resource variable
                        Driver.player.inventory.setResource("Pelt", currentPelt + 1);
                        } else {
                            harvestedLabel.setText("Meat harvested!"); // Update harvested label
                            SFX.playSound("assets/SFX/meat-gathering-sfx.wav"); // meat gathering sfx
        
                            int currentMeat = Driver.player.inventory.getResource("Meat");
                            // Increment wood resource variable
                            Driver.player.inventory.setResource("Meat", currentMeat + 1);
                        }
                        huntIncrement++;
                    }

                    // Update resource in inventory
                    Driver.inventoryUI.updateResourceLabels();

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
                        SFX.playSound("assets/SFX/hunting-sfx5.wav"); //play hunting sfx
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
            harvestedLabel.setText("");
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto hunting
            timer.stop(); // stop timer
            SFX.stopAllNonLoopingSounds();
            autoHuntButton.setText("🏹 Hunt Wildlife");
        }
    }

    // Method to start/stop the automatic woodcutting process
    private void autoCutWood() {
        if (!auto) {
            auto = true; // Start auto woodcutting
            autoCutButton.setText("Stop Cutting Tree...");
            harvestedLabel.setText("");
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto woodcutting when method is called again
            timer.stop(); // stop timer
            SFX.stopAllNonLoopingSounds();
            autoCutButton.setText("⁋ Cut Tree");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (bgImage != null) {
            // scale it to the panel size
            Image scaledImage = bgImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, this);
        }
    }
}
