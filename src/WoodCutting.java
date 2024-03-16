import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WoodCutting extends JPanel {

    private JProgressBar progressBar;
    private JButton cutButton;
    private JButton autoCutButton; // New button for automatic woodcutting
    private Timer timer;

    public WoodCutting() {
        // Set the layout with vertical alignment and padding
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Create the 'Cut Wood' button
        cutButton = new JButton("Cut Wood");
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
                cutWood();
            }
        });

        // Action listener for the 'Auto Cut' button
        autoCutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoCutWood();
            }
        });

        // Action listener for the 'Leave' button
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("world");
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
                    timer.stop();
                    progressBar.setValue(100);
                    JOptionPane.showMessageDialog(null, "Wood granted!");
                    progress = 0;
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
        timer.start(); // Start the timer for woodcutting
    }

    // Method to start the automatic woodcutting process
    private void autoCutWood() {
        progressBar.setValue(0); // Reset progress bar
        timer.start(); // Start the timer for automatic woodcutting
    }
}
