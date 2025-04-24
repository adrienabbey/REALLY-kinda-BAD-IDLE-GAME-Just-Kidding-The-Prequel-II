/*
 * Equipment Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey, et al., Mar. 2024
 */

import java.io.Serializable;

/**
 * Implements equipment for the Player Character.
 * NOTE: This needs to become an object that belongs to the Player Character.
 * Currently, save/load functions simply save/load the PlayerCharacter object.
 */
public class Equipment implements Serializable {
    // TODO: Verify that equipment saves and loads properly.

    /* Fields */
    int weaponLevel;
    int armourLevel;
    int hatLevel;
    String weaponDescription;
    String armourDescription;
    String hatDescription;
    private Dice dice = new Dice(3);

    /* Constructor */

    /**
     * Creates the Equipment object, which tracks the equipment used by the
     * player. This also initializes the player's equipment for new characters.
     */
    public Equipment() {
        // NOTE: Do NOT set item levels to zero, unless you want to destroy
        // reality by attempting to divide-by-zero. Please don't do this. I
        // happen to live in this reality.
        weaponLevel = 1;
        weaponDescription = "Nothin' but Gumption, and a small twig you found.";
        armourLevel = 1;
        armourDescription = "Your Birthday Suit, which is kinda lacking.";
        hatLevel = 1;
        hatDescription = "Hair, unless you're bald.  Are you bald?";
    }

    public Equipment(Equipment other) {
        this.weaponLevel = other.weaponLevel;
        this.weaponDescription = other.weaponDescription;
        this.armourLevel = other.armourLevel;
        this.armourDescription = other.armourDescription;
        this.hatLevel = other.hatLevel;
        this.hatDescription = other.hatDescription;
    }

    /* Methods */

    /**
     * Checks to see if the player managed to find an upgrade for one of their
     * pieces of equipment. This is called every time an enemy is defeated.
     * Only one piece of equipment can be upgraded at a time. The chance of
     * upgrading a piece of equipment depends on the enemy's level and the
     * the current level of existing equipment, with a much higher chance if
     * the equipment's level is lower than the monster's level.
     * 
     * @param MonsterLevel The defeated enemy's level, which is used to
     *                     determine the chance of an equipment upgrade.
     * @param isBoss       Whether the defeated enemy is a boss or not. Bosses have
     *                     much better chances of upgrading a piece of equipment.
     * @return Returns true if a piece of equipment upgraded, false if not.
     */
    public String doUpgrade(int MonsterLevel, boolean isBoss, PlayerCharacter player) {
        // float weaponUpgradeChance;
        // float armourUpgradeChance;
        // float hatUpgradeChance;
        String itemUpgraded = "None";

        // TODO: Balance these numbers!
        // if (isBoss) {
        //     weaponUpgradeChance = (float) ((MonsterLevel - weaponLevel) * 0.3);
        //     armourUpgradeChance = (float) ((MonsterLevel - armourLevel) * 0.3);
        //     hatUpgradeChance = (float) ((MonsterLevel - hatLevel) * 0.3);
        // } else {
        //     weaponUpgradeChance = (float) ((MonsterLevel - weaponLevel) * 0.1);
        //     armourUpgradeChance = (float) ((MonsterLevel - armourLevel) * 0.1);
        //     hatUpgradeChance = (float) ((MonsterLevel - hatLevel) * 0.1);
        // }

        // TODO: Consider including weights of the player's initial stats in
        // these chances. For example, a character starting with really high
        // brains has a much higher chance of upgrading their hat, which can go
        // higher than the monster level. This lets players specialize.

        // Roll each chance to see if one upgrades:
        // boolean weaponUpgrades = (Math.random() < weaponUpgradeChance);
        // boolean armourUpgrades = (Math.random() < armourUpgradeChance);
        // boolean hatUpgrades = (Math.random() < hatUpgradeChance);
        // TODO: We might bring something back with this for weighted upgrades
        // for specializing a character.

        // TODO: Implement a weighted randomness algorithm to upgrade only a
        // single piece of equipment when multiple valid upgrades exist.

        dice.roll();

        if (dice.getLast() == 1) {
            weaponLevel += 1;
            weaponDescription = "A Big Stick +" + weaponLevel;
            itemUpgraded = "Weapon";
            player.setMuscle(player.getMuscle() + 1);
        }
        if (dice.getLast() == 2) {
            armourLevel += 1;
            armourDescription = "The Skin of Your Enemies +" + armourLevel;
            itemUpgraded = "Armour";
            player.setHeart(player.getHeart() + 1);
        }
        if (dice.getLast() == 3) {
            hatLevel += 1;
            armourDescription = "A tower of " + hatLevel + " hats.";
            itemUpgraded = "Hat";
            player.setBrain(player.getBrain() + 1);
        }

        // TODO: Consider implementing a more graceful player stat upgrade method.

        // TODO: Implement a randomized naming algorithm to add variety to
        // equipment descriptions.

        return itemUpgraded;
    }

    public String getWeaponDescription() {
        return weaponDescription;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public String getArmourDescription() {
        return armourDescription;
    }

    public int getArmourLevel() {
        return armourLevel;
    }

    public String getHatDescription() {
        return hatDescription;
    }

    public int getHatLevel() {
        return hatLevel;
    }
}