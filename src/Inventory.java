
// Singleton class so that there's only one instance of it throughout the game.
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends JPanel {

    public Map<String, Integer> resources; // Map to store resource amounts
    public boolean backToHomestead = false; // flag that keeps trck if player accessed inventory from the
                                            // homestead screen.
    public boolean backToTown = false; // flag that keeps trck if player accessed inventory from the town screen.

    public Inventory() {

        // Initialize resources map
        resources = new HashMap<>();
        // resources.put("Gold", 1000);
        resources.put("Wood", 250);
        resources.put("Metal", 100);
        resources.put("Stone", 250);
        resources.put("Potions", 0);
        resources.put("Pelt", 0);
        resources.put("Meat", 0);
        resources.put("Magical Essense", 0);
        resources.put("Spleenwort", 0);
        resources.put("Tongue Fern", 0);
        resources.put("Legendary Potion of Lepus", 0);

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

    // Method to set resource amount
    public void setResource(String resourceName, int amount) {
        if (resources.containsKey(resourceName)) {
            resources.put(resourceName, amount);
            Driver.inventoryUI.updateResourceLabels();
        } else {
            System.err.println("Resource '" + resourceName + "' does not exist.");
        }
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

}