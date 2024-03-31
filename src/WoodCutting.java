import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WoodCutting extends JPanel {
    Inventory inventory = Inventory.getInstance();

    private JProgressBar progressBar;
    private JButton autoHuntButton;
    private JButton autoCutButton; // button for automatic woodcutting
    private Timer timer;
    private Image bgImage;
    private JLabel grantedLabel; // Label to display wood granted message
    private boolean auto = false;
    private boolean currentlyCutting = false; // flag to determine if process is cutting. Used to grant correct resource.
    private boolean currentlyHunting = false; // flag to determine if process is hunting. Used to grant correct resource.
    private boolean resetProgress = true;
    private int huntIncrement = 0; // used to determine whether to grant meat or pelt when hunting


    public WoodCutting() { // Accepts an Inventory object
    
        // Load the background image
        try {
            bgImage = ImageIO.read(new File("assets/images/forest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the layout with vertical alignment and padding
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(950, 20, 0, 20)); // Add padding around the panel

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorGreen = new Color(0, 100, 0);
        Color customColorGold = new Color(205, 133, 63);


        autoHuntButton = new JButton("Hunt Wildlife");
        buttons.add(autoHuntButton);
        autoCutButton = new JButton("Cut Tree");
        buttons.add(autoCutButton);
        JButton leave = new JButton("Leave");
        buttons.add(leave);

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            // buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);

            buttons.get(i).setPreferredSize(new Dimension(200, 45));
            buttons.get(i).setMaximumSize(new Dimension(200, 80));
            buttons.get(i).setBackground(customColorGreen);
            buttons.get(i).setForeground(customColorGold);
            buttons.get(i).setFont(new Font("Serif", Font.ITALIC, 26));
            buttons.get(i).setOpaque(true); // Make the background visible
            buttons.get(i).setFocusPainted(false); // Remove focus ring around the button
        }

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

        // Action listener for the 'hunt' button
        autoHuntButton.addActionListener(e -> {
            try{
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyHunting = true;
                currentlyCutting = false;
                autoHunt(); // Start hunting process
                if (auto) {
                    SFX.playSound("assets/SFX/.wav"); // TODO: play hunting sfx
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

        // Action listener for the 'Cut Wood' button
        autoCutButton.addActionListener(e -> {
            try{
                SFX.playSound("assets/SFX/interface1.wav");
                currentlyHunting = false;
                currentlyCutting = true;
                autoCutWood(); // Start/Stop auto woodcutting process
                if (auto) {
                    SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play woodcutting sound effect only when starting woodcutting
                }
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                timer.stop();
                progressBar.setValue(0);
                auto = false; // stop auto mining if left panel
                resetProgress = true;
                currentlyHunting = false; // set hunting flag to default
                currentlyCutting = false; // set cutting flag to default
                // timer.stop(); // stop woodcutting process
                autoCutButton.setText("Cut Tree"); // reset autocutting label
                autoHuntButton.setText("Hunt Wildlife"); // reset autohunting label

                Driver.changePanel("world");
                SFX.stopAllSounds(); 
                MusicPlayer.playMusic("assets/Music/Brilliant1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for woodcutting/hunting process
        // Progress variable increases by 1 every 100 milliseconds. Progress variable needs to equal 100 for progress bar to fill up completely. Takes 10 seconds to fill up.
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
                        SFX.playSound("assets/SFX/.wav"); // TODO: add sfx
    
                        int currentPelt = inventory.getResource("Pelt");
                        // Increment wood resource variable
                        inventory.setResource("Pelt", currentPelt + 1);
                        } else {
                            grantedLabel.setText("Meat granted!"); // Update granted label
                            SFX.playSound("assets/SFX/.wav"); // TODO: add sfx
        
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
                    } else {
                        SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play sfx again
                        // TODO: add appropriate sfx for when hunting
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
            SFX.stopAllSounds();
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
            SFX.stopAllSounds();
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
