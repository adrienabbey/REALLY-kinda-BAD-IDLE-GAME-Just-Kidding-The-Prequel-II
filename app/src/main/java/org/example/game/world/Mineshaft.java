/*
 * Mineshaft Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
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
 * Implements the "Mineshaft" panel accessed through the World screen. Allows the player to mine ore or gather resources. Uses a JProgressBar to track progress. After each a process finishes a grant message will pop up to indictie which respective resource was granted to the player. This item will be added to the player's inventory.
 * 
 * Current reources that can be gathered at the mineshaft: Stone, Metal, Spleenwort, Tongue Fern, Magical Essence...
 * 
 * TODO: add gradient color to bar. 
 * Add levels to the mineshaft. Deeper levels can be a way to add more items to the game. Player can unlock deeper levels by increasing their mining/scavenging stat. Can use a different bgm and bg image for new levels. Maybe even add secret diaog options when unlocking new levels, such as new dialog messages in the tavern or in the mineshaft itself. 
 */
public class Mineshaft extends JPanel {

    //========================================================
    // Fields
    //========================================================
    // These are used for formating the gui elements
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 82;

    // Components and variables used in mineshaft screen
    private JProgressBar progressBar;
    private JButton autoScavengeButton; // button to activate scavenging
    private JButton autoMineButton; // Button to activate mining
    private Timer timer;
    private Image bgImage;
    private JLabel harvestLabel; // Label to display harvest message
    private boolean auto; // flag that turns auto processes on or off
    private boolean currentlyScavenge = false; // flag to determine if process is scavengeing. Used to grant appropriate resource.
    private boolean currentlyMining = false; // flag to determine if process is mining. Used to grant appropriate resource.
    private boolean resetProgress = true; // flag used to determine if player had left the mineshaft panel. If true reset the progress value to 0.

    private String downArrow = "\u25BC"; // Down-Pointing Triangle
    private String upArrow = "\u25B2"; // Up-Pointing Triangle
    private boolean statusBarOpen = false; // flag used to determine when the status bar is open
    final private Color customColorGold = new Color(253, 236, 166);
    final private Color customColorBlack = new Color(0,0,0);
    private Border mineBorder = BorderFactory.createLineBorder(Color.white, 1);

    // Declare stateBar panel and comeponents of the status bar
    private JPanel statusBar;
    private JButton statusButton;
    private JProgressBar health;
    private JProgressBar magic;
    private JButton gold;
    

    //========================================================
    // Constructor
    //========================================================
    public Mineshaft() { 
        // Load the background image
        try {
            bgImage = ImageIO.read(new File("assets/images/mineshaft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the layout with vertical alignment and padding
        setLayout(null);

        ArrayList<JButton> buttons = new ArrayList<JButton>();        

        //initializes components for action and status panels. 
        autoScavengeButton = new JButton("🔍 Scavenge Area");
        buttons.add(autoScavengeButton);
        autoMineButton = new JButton("⛏️ Mine Ore");
        buttons.add(autoMineButton);
        statusButton = new JButton(downArrow);
        buttons.add(statusButton);
        JButton leave = new JButton("Leave");
        buttons.add(leave);

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            // buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);

            buttons.get(i).setPreferredSize(new Dimension(200, 45));
            buttons.get(i).setMaximumSize(new Dimension(200, 80));
            buttons.get(i).setBackground(customColorBlack);
            buttons.get(i).setForeground(customColorGold);
            buttons.get(i).setBorder(mineBorder);
            buttons.get(i).setFont(new Font("Serif", Font.ITALIC, buttonFont));
            buttons.get(i).setOpaque(true); // Make the background visible
            buttons.get(i).setFocusPainted(false); // Remove focus ring around the button
        }

        // Create the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Serif", Font.ITALIC, buttonFont));
        progressBar.setForeground(new Color(205, 133, 63)); // Light wood color
        progressBar.setBackground(new Color(0, 0, 0)); // Set the background color to black
        progressBar.setBorder(mineBorder);
        progressBar.setOpaque(true); // Make the background visible
        progressBar.setPreferredSize(new Dimension(10, 20)); // Set the preferred size of the progress bar

        // Create label for ore harvest message
        harvestLabel = new JLabel("");
        harvestLabel.setFont(new Font("Serif", Font.BOLD, buttonFont));
        harvestLabel.setForeground(Color.GREEN); // Green color for ore harvest message


        //============================================================
        //
        // Relative scaling components
        //
        //

        // Initialize button panel to house action buttons
        JPanel buttonPanel = new JPanel(new GridLayout());

        // Set position and size of panel, label, and progress bar using relative scaling
        buttonPanel.setBounds((int) (width * 0.0134), (int) (height * 0.9500), (int) (width * 0.9713), (int) (height * 0.0483)); 

        harvestLabel.setBounds((int) (width * 0.0134), (int) (height * 0.8499), (int) (width * 0.176), (int) (height * 0.0545));

        progressBar.setBounds((int) (width * 0.0134), (int) (height * 0.8962), (int) (width * 0.9713), (int) (height * 0.0573));

        // Create the Status button
        statusButton.setBounds((width - (int) (width * 0.03125)) / 2, 0,  (int) (width * 0.03125), (int) (height * 0.04166));// Set the position and size of the button
        statusButton.setFont(new Font("Times New Roman", Font.BOLD, buttonFont));
        //===========================================================
        // adding buttons to panel
        buttonPanel.add(autoScavengeButton);
        buttonPanel.add(autoMineButton);
        buttonPanel.add(leave);
        add(buttonPanel); // add panel that contains mine, scavenege and leave buttons
        add(progressBar); // add progress bar to layout
        add(harvestLabel); // Add harvested label to panel
        add(statusButton); // Add the button to the panel


//======================================================
// methods for action listeners
//========================================================

        // Action listener for the 'Scavenge Area' button
        autoScavengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyScavenge = true;
                currentlyMining = false;
                autoScavenge();
                if (auto) {
                    SFX.playSound("assets/SFX/scavenging-sfx.wav"); // play scavenge sfx 
                }
            }
        });

        // Action listener for the 'Mine Ore' button
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

                // format buttonet
                health.setForeground(Color.red);
                health.setBackground(new Color(255, 153, 153)); // light-red
                magic.setForeground(Color.blue);
                magic.setBackground(new Color(153, 204, 255)); // light-blue
                gold.setBackground(Color.yellow);

                health.setFont(new Font("Times New Roman", Font.PLAIN, buttonFont));
                magic.setFont(new Font("Times New Roman", Font.PLAIN, buttonFont));
                gold.setFont(new Font("Times New Roman", Font.PLAIN, buttonFont));

                // add bttons to status bar
                statusBar.add(health);
                statusBar.add(magic);
                statusBar.add(gold);
                statusBar.setBounds((int) (width * 0.0104), (int) (height * 0.0), (int) (width * 0.9714), (int) (height * 0.04629)); // set location and size of status bar
                add(statusBar);
                revalidate();
                repaint();
                statusBarOpen = true; // set statusBarOpen to true
            } else {
                remove(statusBar); // remove the status bar from the screen
                statusButton.setBounds((width - (int) (width * 0.03125)) / 2, 0,  (int) (width * 0.03125), (int) (height * 0.04166));// Reset status button position
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
                currentlyMining = false; // set mining flag to default
                currentlyScavenge = false; // set scavengingflag to default
                // timer.stop(); // stop woodcutting process
                autoMineButton.setText("⛏️ Mine Ore"); // reset autocutting label
                autoScavengeButton.setText("🔍 Scavenge Area"); // reset autohunting label

                Driver.changePanel("world");
                SFX.stopAllSounds(); 
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for automatic process
        // Progress variable increases by 1 every 100 milliseconds. Progress variable needs to equal 100 for progress bar to fill up completely. Takes 10 seconds to fill up.
        timer = new Timer(100, new ActionListener() {
            int progress = 0;
            int ore = 1; // used to track which resource to grant
            int scavenge = 0; // used to track which resource to grant

            @Override
            public void actionPerformed(ActionEvent e) {
                if (resetProgress) {
                    progress = 0; // reset progress to zero if left screen prior.
                    resetProgress = false;
                }

                if (progress == 40) {
                    harvestLabel.setText(""); // erase grant label when progress reaches 40
                }

                if (progress >= 100) {
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
                        int HealthRegenRate = Driver.getPlayer().getMaxHealth() * 1 / 10;
                        Driver.getPlayer().setHealth(Driver.getPlayer().getHealth() + HealthRegenRate); // regenerate Health by 10% of max mana
                        health.setValue((int) Driver.getPlayer().getHealth());
                        health.setString("Health: " + health.getValue());
                    }

                    if (currentlyMining) {
                        if (ore % 3 == 0) {
                            harvestLabel.setText("Metal harvested!"); // Update metal harvest label
                            SFX.playSound("assets/SFX/metal-ringing1.wav");

                            int currentMetal = Driver.player.inventory.getResource("Metal");
                            // Increment metal resource variable
                            Driver.player.inventory.setResource("Metal", currentMetal + 1);

                        } else {
                            harvestLabel.setText("Stone harvested!"); // Update stone harvest label
                            SFX.playSound("assets/SFX/stone-gathering-sfx.wav");

                            int currentStone = Driver.player.inventory.getResource("Stone");
                            // Increment stone resource variable
                            Driver.player.inventory.setResource("Stone", currentStone + 1);
                            // update resource in inventory
                        }
                    }

                    if (currentlyScavenge) {
                        if (scavenge ==  0 || (scavenge % 3 == 0)) {
                            harvestLabel.setText("Magical Essence harvested!"); // Update harvest label
                            SFX.playSound("assets/SFX/magical-essence-sfx.wav");  // magical essence sfx

                            int currentMagicalEssence = Driver.player.inventory.getResource("Magical Essence");
                            // Increment MagicalEssence resource variable
                            Driver.player.inventory.setResource("Magical Essence", currentMagicalEssence + 1);

                        } else if (scavenge % 2 == 0) {
                            harvestLabel.setText("Tongue Fern harvested!"); // Update harvest label
                            SFX.playSound("assets/SFX/RPG Sound Pack/interface/interface3.wav");  // play tongue fern sfx

                            int currentTongueFern = Driver.player.inventory.getResource("Tongue Fern");
                            // Increment TongueFern resource variable
                            Driver.player.inventory.setResource("Tongue Fern", currentTongueFern + 1);

                        } else {
                            harvestLabel.setText("Spleenwort harvested!"); // Update harvest label
                            SFX.playSound("assets/SFX/RPG Sound Pack/interface/interface3.wav"); // [lay spleenwort sfx

                            int currentSpleenwort = Driver.player.inventory.getResource("Spleenwort");
                            // Increment Spleenwort resource variable
                            Driver.player.inventory.setResource("Spleenwort", currentSpleenwort + 1);
                        }
                    }
                    // update resource in inventory
                    Driver.inventoryUI.updateResourceLabels();
                    progress = 0;
                    ore++;
                    scavenge++;
                    if (!auto) {
                        timer.stop();  
                    } else if (currentlyMining) {
                        SFX.stopAllNonLoopingSounds();
                        SFX.playSound("assets/SFX/pickaxe-sfx.wav"); // loop pickaxe-sfx
                    } else {
                        SFX.stopAllNonLoopingSounds();
                        SFX.playSound("assets/SFX/scavenging-sfx.wav"); // loop scavenging sfx
                    }
                } else {
                    progress++;
                    ;// increment progress by 1
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
            harvestLabel.setText("");
            timer.start(); // Start the timer for auto scavenging
        } else {
            auto = false; // Stop auto scavenging
            timer.stop(); //stop timer
            SFX.stopAllNonLoopingSounds();
            autoScavengeButton.setText("🔍 Scavenge Area");
        }
    }

    // Method to start/stop the automatic mining process
    private void autoMineOre() {
        if (!auto) {
            auto = true; // Start auto mining
            autoMineButton.setText("Stop Mining Ore...");
            harvestLabel.setText("");
            timer.start(); // Start the timer for auto mining
        } else {
            auto = false; // Stop auto mining
            timer.stop(); //stop timer
            SFX.stopAllNonLoopingSounds();
            autoMineButton.setText("⛏️ Mine Ore");
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
