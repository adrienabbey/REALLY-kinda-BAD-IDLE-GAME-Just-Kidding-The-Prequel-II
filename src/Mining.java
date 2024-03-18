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
        cutButton.setFont(new Font("Serif", Font.BOLD, 24));
        cutButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Auto Cut' button
        autoCutButton = new JButton("Auto Mine");
        autoCutButton.setFont(new Font("Serif", Font.BOLD, 24));
        autoCutButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Leave' button
        JButton leave = new JButton("Leave");
        leave.setFont(new Font("Serif", Font.BOLD, 24));
        leave.setFocusPainted(false); // Remove focus ring around the button

        // Create the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        // Add components to the panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(cutButton);
        buttonPanel.add(autoCutButton);
        buttonPanel.add(leave);
        add(buttonPanel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);

        // Action listener for the 'Cut Wood' button
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mineOre();
                SFX.playSound("assets/images/SFX/pickaxe-sfx.wav"); 
            }
        });

        // Action listener for the 'Auto Cut' button
        autoCutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoMineOre();
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("world");
                SFX.stopSound();
                MusicPlayer.playMusic("assets/images/Music/Brilliant1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for automatic woodcutting process
        timer = new Timer(1200, new ActionListener() {
            int progress = 0;
            int ore = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 10) {
                    timer.stop();
                    progressBar.setValue(100);

                    if (ore % 5 == 0) {
                        JOptionPane.showMessageDialog(null, "Metal granted!"); 
                        int currentMetal = inventory.getResource("Metal");
                        // Increment metal resource variable
                        inventory.setResource("Metal", currentMetal + 1);
                        //update resource in inventory
                        inventory.updateResourceLabels();

                    } else {
                        JOptionPane.showMessageDialog(null, "Stone granted!");
                        int currentStone = inventory.getResource("Stone");
                        // Increment stone resource variable
                        inventory.setResource("Stone", currentStone + 1);
                        //update resource in inventory
                        inventory.updateResourceLabels();
                        }

                    progress = 0;
                    ore++;
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
        timer.start(); // Start the timer for woodcutting
    }

    // Method to start the automatic woodcutting process
    private void autoMineOre() {
        progressBar.setValue(0); // Reset progress bar
        timer.start(); // Start the timer for automatic woodcutting
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
