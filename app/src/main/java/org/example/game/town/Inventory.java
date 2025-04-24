/*
 * Inventory Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * Inventory class implements the backend inventory functionailty. A hashmap is used to store resource name and amount. 
 * 
 * Current resources the inventory can contain: Gold, Wood, Metal, Stone, Potions, Pelt, Meat, Magical Essence, Spleenwort, Tongue Fern, Legendary Potion of Lepus. 
 * 
 * TODO: Store equipment in inventory. Allow players to use potions from inventory and equip or unequip equipment. 
 */

public class Inventory implements Serializable {

    public Map<String, Integer> resources; // Map to store resource amounts
    public boolean backToHomestead = false; // flag that keeps trck if player accessed inventory from the homestead screen.
    public boolean backToTown = false; // flag that keeps trck if player accessed inventory from the town screen.

    public Inventory() {

        // Initialize resources map
        resources = new HashMap<>();
        // resources.put("Gold", 1000);
        resources.put("Wood", 250);
        resources.put("Metal", 100);
        resources.put("Stone", 250);
        resources.put("Pelt", 0);
        resources.put("Meat", 0);
        resources.put("Magical Essence", 0);
        resources.put("Spleenwort", 0);
        resources.put("Tongue Fern", 0);
        resources.put("Legendary Potion of Lepus", 0);
    }

    public Inventory(Inventory other) {
        this.resources = other.resources;
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