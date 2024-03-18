import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Font;

public class Inventory extends JPanel {

    private Map<String, Integer> resources; // Map to store resource amounts
    private JLabel resourceLabel; // Label to display total space

    //     @Override
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //         try {
    //             g.drawImage(ImageIO.read(new File("assets/images/")), 0, 0, getWidth(), getHeight(), this);
    //         } catch (IOException e) {
    //             //Auto-generated catch block
    //             e.printStackTrace();
    //         }
    // }

    public Inventory() {
        setLayout(new BorderLayout());

        // Initialize resources map
        resources = new HashMap<>();
        resources.put("Gold", 0);
        resources.put("Wood", 0);
        resources.put("Metal", 0);
        resources.put("Stone", 0);

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
        for (String resourceName : resources.keySet()) {
            JLabel label = new JLabel(resourceName + ": " + resources.get(resourceName));
            resourcePanel.add(label);
        }
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
                Driver.changePanel("town"); 
            // MusicPlayer.playMusic("assets/images/Music/Village Consort.wav");
        });
    }

    // Method to set resource amount
    public void setResource(String resourceName, int amount) {
        if (resources.containsKey(resourceName)) {
            resources.put(resourceName, amount);
            updateResourceLabels();
        } else {
            System.err.println("Resource '" + resourceName + "' does not exist.");
        }
    }

    // Method to get resource amount
    public int getResource(String resourceName) {
        if (resources.containsKey(resourceName)) {
            return resources.get(resourceName);
        } else {
            System.err.println("Resource '" + resourceName + "' does not exist.");
            return 0;
        }
    }

    // Update resource labels
    public void updateResourceLabels() {
        // Update resource labels
        for (String resourceName : resources.keySet()) {
            for (Component component : ((JPanel) this.getComponent(1)).getComponents()) {
                if (component instanceof JLabel && ((JLabel) component).getText().startsWith(resourceName)) {
                    ((JLabel) component).setText(resourceName + ": " + resources.get(resourceName));
                }
            }
        }
    }
}

//   Example usage: Increment wood resource
//   int currentWood = inventory.getResource("Wood");
//   inventory.setResource("Wood", currentWood + 1);