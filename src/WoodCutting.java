import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WoodCutting extends JPanel {

    private Inventory inventory; // Reference to the Inventory object

    private JProgressBar progressBar;
    private JButton cutButton;
    private JButton autoCutButton; // New button for automatic woodcutting
    private Timer timer;
    private Image bgImage;
    private JLabel woodGrantedLabel; // Label to display wood granted message
    private boolean auto = false;

    public WoodCutting(Inventory inventory) { // Accepts an Inventory object
        this.inventory = inventory; // Assign the provided Inventory object to the local variable

        // Load the background image
        try {
            bgImage = ImageIO.read(new File("assets/images/forest2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the layout with vertical alignment and padding
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(950, 20, 0, 20)); // Add padding around the panel

        // Create the 'Cut Wood' button
        cutButton = new JButton("Cut Tree");
        cutButton.setFont(new Font("Serif", Font.BOLD, 24));
        cutButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Auto Cut' button
        autoCutButton = new JButton("Auto Cut");
        autoCutButton.setFont(new Font("Serif", Font.BOLD, 24));
        autoCutButton.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Leave' button
        JButton leave = new JButton("Leave");
        leave.setFont(new Font("Serif", Font.BOLD, 24));
        leave.setFocusPainted(false); // Remove focus ring around the button

        // Create the progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(10, 20)); // Set the preferred size of the progress bar

        // Create label for wood granted message
        woodGrantedLabel = new JLabel("");
        woodGrantedLabel.setFont(new Font("Serif", Font.BOLD, 22));
        woodGrantedLabel.setForeground(Color.GREEN); // Green color for wood granted message

        // Add components to the panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.setOpaque(false); // Make the button panel transparent
        buttonPanel.add(cutButton);
        buttonPanel.add(autoCutButton);
        buttonPanel.add(leave);
        add(buttonPanel, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.CENTER);
        add(woodGrantedLabel, BorderLayout.NORTH); // Add wood granted label to the panel

        // Action listener for the 'Cut Wood' button
        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cutWood(); // Start woodcutting process
                SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play woodcutting sound effect everytime button is pressed
            }
        });

        // Action listener for the 'Auto Cut' button
        autoCutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoCutWood(); // Start/Stop auto woodcutting process
                SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play woodcutting sound effect everytime button is pressed
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                auto = false; // stop auto mining if left panel
                Driver.changePanel("world");
                SFX.stopSound();
                MusicPlayer.playMusic("assets/Music/Brilliant1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Timer for automatic woodcutting process
        timer = new Timer(1000, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress >= 10) {
                    progressBar.setValue(100);
                    woodGrantedLabel.setText("Wood granted!"); // Update wood granted label

                    int currentWood = inventory.getResource("Wood");
                    // Increment wood resource variable
                    inventory.setResource("Wood", currentWood + 1);
                    // Update resource in inventory
                    inventory.updateResourceLabels();

                    progress = 0;
                    if (!auto) {
                        timer.stop();
                    } else {
                        SFX.playSound("assets/SFX/woodcutting-sfx.wav"); // play sfx again if auto-mining
                    }
                } else {
                    progress++;
                    progressBar.setValue(progress * 10);
                }
            }
        });
    }

    // Method to start the woodcutting process
    private void cutWood() {
        progressBar.setValue(0); // Reset progress bar
        woodGrantedLabel.setText(""); // Clear wood granted label
        timer.start(); // Start the timer for woodcutting
    }

    // Method to start/stop the automatic woodcutting process
    private void autoCutWood() {
        if (!auto) {
            auto = true; // Start auto woodcutting
            autoCutButton.setText("Stop Auto Cutting...");
            woodGrantedLabel.setText(""); 
            timer.start(); // Start the timer for auto woodcutting
        } else {
            auto = false; // Stop auto woodcutting
            autoCutButton.setText("Auto Cut");
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
