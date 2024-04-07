/*
 * Inventory Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import java.util.HashMap;
import java.util.Map;

/*
 * Inventory class implements the inventory panel and functionailty. Inventory panel can be accessed from various screens in the game, such as the bazaar, homestead, and town. The inventory stores resources and equipment. The player can equip equipment and and use potions from the inventory. A hashmap is used to store resource name and amount. It is a singleton class so that there's only one instance of it throughout the game. 
 * 
 * Current resources the inventory can contain: Gold, Wood, Metal, Stone, Potions, Pelt, Meat, Magical Essence, Spleenwort, Tongue Fern, Legendary Potion of Lepus. 
 * 
 * TODO: Store equipment in inventory. Allow players to use potions from inventory and equip or unequip equipment. 
 */

public class Inventory {

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
        // resources.put("Potions", 0);
        resources.put("Pelt", 0);
        resources.put("Meat", 0);
        resources.put("Magical Essence", 0);
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

    // // Method to add to Gold to inventory when defeating monsters
    // public void addGold(int amount) {
    // int currentGold = resources.get("Gold");
    // resources.put("Gold", currentGold + amount);
    // updateResourceLabels(); // Update the UI to reflect the changes
    // }

    // // Update resource labels
    // public void updateResourceLabels() {
    // // Update resource labels
    // for (String resourceName : resources.keySet()) {
    // for (Component component : ((JPanel) this.getComponent(1)).getComponents()) {
    // if (component instanceof JLabel && ((JLabel)
    // component).getText().startsWith(resourceName)) {
    // ((JLabel) component).setText(resourceName + ": " +
    // resources.get(resourceName));
    // }
    // }
    // }
    // }

    public Map<String, Integer> getResources() {
        return resources;
    }

}