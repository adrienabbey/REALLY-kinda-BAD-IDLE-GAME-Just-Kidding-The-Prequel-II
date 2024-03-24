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
    private JButton cutButton;
    private JButton autoCutButton; // New button for automatic woodcutting
    private Timer timer;
    private Image bgImage;
    private JLabel oreGrantedLabel; // Label to display wood granted message
    private boolean auto;

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

        // Create the 'Cut Wood' button
        cutButton = new JButton("Mine Ore");
        cutButton.setFont(new Font("Serif", Font.ITALIC, 28));
        cutButton.setForeground(new Color(205, 133, 63)); // Light wood color
        cutButton.setBackground(new Color(0, 0, 0)); // Set the background color to black
        cutButton.setOpaque(true); // Make the background visible
        cutButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Auto Cut' button
        autoCutButton = new JButton("Auto Mine");
        autoCutButton.setFont(new Font("Serif", Font.ITALIC, 26));
        autoCutButton.setForeground(new Color(205, 133, 63)); // Light wood color
        autoCutButton.setBackground(new Color(0, 0, 0)); // Set the background color to black
        autoCutButton.setOpaque(true); // Make the background visible
        autoCutButton.setFocusPainted(false); // Remove focus ring around the button

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
        oreGrantedLabel = new JLabel("");
        oreGrantedLabel.setFont(new Font("Serif", Font.BOLD, 24));
        oreGrantedLabel.setForeground(Color.GREEN); // Green color for ore granted message

        // Add components to the panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(cutButton);
        buttonPanel.add(autoCutButton);
        buttonPanel.add(leave);
        add(buttonPanel, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.CENTER);
        add(oreGrantedLabel, BorderLayout.NORTH); // Add ore granted label to the panel

        // Action listener for the 'Cut Wood' button
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                mineOre();
                SFX.playSound("assets/SFX/pickaxe-sfx.wav"); 
            }
        });

        // Action listener for the 'Auto Cut' button
        autoCutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SFX.playSound("assets/SFX/interface1.wav");
                autoMineOre();
                SFX.playSound("assets/SFX/pickaxe-sfx.wav"); 
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                auto = false; // stop auto mining if left panel
                timer.stop(); // stop mining process
                autoCutButton.setText("Auto Cut"); // reset automining label
                
                Driver.changePanel("world");
                SFX.stopSound();
                MusicPlayer.playMusic("assets/Music/Brilliant1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for automatic woodcutting process
        timer = new Timer(1100, new ActionListener() {
            int progress = 0;
            int ore = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 10) {
                    progressBar.setValue(100);

                    if (ore % 5 == 0) {
                        oreGrantedLabel.setText("Metal granted!"); // Update metal granted label
                        SFX.playSound("assets/SFX/metal-ringing1.wav"); 

                        int currentMetal = inventory.getResource("Metal");
                        // Increment metal resource variable
                        inventory.setResource("Metal", currentMetal + 1);
                        //update resource in inventory
                        inventory.updateResourceLabels();

                    } else {
                        oreGrantedLabel.setText("Stone granted!"); // Update stone granted label
                        SFX.playSound("assets/SFX/stone-gathering-sfx.wav"); 

                        int currentStone = inventory.getResource("Stone");
                        // Increment stone resource variable
                        inventory.setResource("Stone", currentStone + 1);
                        //update resource in inventory
                        inventory.updateResourceLabels();
                        }

                    progress = 0;
                    ore++;
                    if (!auto) {
                        timer.stop();  
                    } else {
                        SFX.playSound("assets/SFX/pickaxe-sfx.wav"); 
                    }
                } else {
                    progress++;
                    progressBar.setValue(progress * 10);
                }
            }
        });
    }

    // Method to start the woodcutting process
    private void mineOre() {
        progressBar.setValue(0); // Reset progress bar
        oreGrantedLabel.setText(""); // Clear wood granted label
        timer.start(); // Start the timer for woodcutting
    }

    // Method to start/stop the automatic woodcutting process
    private void autoMineOre() {
        if (!auto) {
            auto = true; // Start auto woodcutting
            autoCutButton.setText("Stop Auto Mining...");
            oreGrantedLabel.setText(""); 
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto woodcutting
            autoCutButton.setText("Auto Mine");
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
