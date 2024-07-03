import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

public class InventoryUI extends JPanel {
    private JLabel resourceLabel; // Label to display total space
    private int resourcePanelint = 1;

    public InventoryUI() {
        setLayout(new BorderLayout());
        // Inventory panel
        JPanel inventoryPanel = new JPanel(new GridLayout(2, 4));
        // Initialize item labels (you can modify this part as needed)
        for (int i = 0; i < 8; i++) {
            JLabel itemLabel = new JLabel("Empty");
            inventoryPanel.add(itemLabel);
        }
        add(inventoryPanel, BorderLayout.CENTER);

        // Resources panel
        JPanel resourcePanel = new JPanel(new GridLayout(4, 1));
        // Initialize resource labels

        resourceLabel = new JLabel("Total Space: 0/8");
        resourcePanel.add(resourceLabel);
        add(resourcePanel, BorderLayout.SOUTH);

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color
        back.setFocusPainted(false); // Remove focus ring around the button

        // Add component to the panel
        add(back, BorderLayout.NORTH);

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            if (resourcePanelint == 1) {
                if (Driver.player != null) {
                    for (String resourceName : Driver.player.inventory.resources.keySet()) {
                        JLabel label = new JLabel(resourceName + ": " + Driver.player.inventory.resources.get(resourceName));
                        resourcePanel.add(label);
                    }
                }
                resourcePanelint++;
            } else {
                updateResourceLabels();
            }
            
            SFX.playSound("assets/SFX/interface1.wav");
            // if player accessed inventory from homestead, town, or bazaar go back to
            // respective screen
            if (Driver.player.inventory.backToHomestead == true) {
                Driver.changePanel("home");
            } else if (Driver.player.inventory.backToTown == true) {
                Driver.changePanel("town");
                Driver.player.inventory.backToTown = false;
            } else {
                Driver.changePanel("bazaar");
            }
        });
    }

    // Update resource labels
    public void updateResourceLabels() {
        // Update resource labels
        for (String resourceName : Driver.player.inventory.resources.keySet()) {
            for (Component component : ((JPanel) this.getComponent(1)).getComponents()) {
                if (component instanceof JLabel && ((JLabel) component).getText().startsWith(resourceName)) {
                    ((JLabel) component)
                            .setText(resourceName + ": " + Driver.player.inventory.resources.get(resourceName));
                }
            }
        }
    }
}
