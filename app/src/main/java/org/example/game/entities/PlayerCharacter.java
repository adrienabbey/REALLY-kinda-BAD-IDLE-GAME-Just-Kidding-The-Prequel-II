

/*
 * Player Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey, et al., Jan. 2024
 */

import java.util.Timer;
import java.util.TimerTask;

/**
 * Player Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * This class manages the player character's stats and some inventory for now
 */
class PlayerCharacter extends GameCharacter{

   /* Constants */
   private static final int POTION_HEAL = 20; // TODO: Balance this number!

   /* Fields */
   // private int statpoints;
   private int gold;
   private int potionBeltSize;
   private int potionCount;
   private boolean isAwake = true;
   private int timeToWake = 0;
   public Equipment equipment; // Contains all the player's equipment.
   public Inventory inventory;

   /* Constructors */

   // Simple constructor for starting player characters:
   // NOTE: NOT being used, I think.
   // public PlayerCharacter(String name) {
   // super(name, startingMuscle, startingBrain, startingHeart);
   // }

   // Constructor for loading player stats from a save file?
   public PlayerCharacter(String name, int muscle, int brain, int heart, int gold, int potionBeltSize,
         int potionCount) {
      super(name, muscle, brain, heart);
      this.gold = gold;
      this.potionBeltSize = potionBeltSize;
      this.potionCount = potionCount;
      this.equipment = new Equipment();
      this.inventory = new Inventory();
   }

   public PlayerCharacter(PlayerCharacter other) {
      super(other);
      this.gold = other.gold;
      this.potionBeltSize = other.potionBeltSize;
      this.potionCount = other.potionCount;
      this.isAwake = other.isAwake;
      this.timeToWake = other.timeToWake;
      this.equipment = other.equipment;
      this.inventory = other.inventory;
   }

   /* Methods */

   // Here, thou incestuous, murderous, damned Dane, Drink off this potion!

   /**
    * This function sets the size of the players potion belt
    * 
    * @param newPotionBeltSize the new size of the potion belt
    * @return true if the potion belt size is set, false if it is not
    */
   public boolean setPotionBeltSize(int newPotionBeltSize) {
      if (newPotionBeltSize > 0) {
         potionBeltSize = newPotionBeltSize;
         return true;
      } else {
         System.err.println(
               "ERROR: Who coded this?  You should be ashamed of yourself.  Why are you trying to set a *negative* potion belt size?  That's how you break reality.");
         return false;
      }
   }

   /**
    * This function adds potions to the players inventory
    * 
    * @param numPotions the number of potions to add
    * @return the number of potions in the players inventory
    */
   public int addPotion(int numPotions) {
      if (numPotions < 0) {
         // This is an ADD potions method, not a REMOVE potion method.
         System.err.println("ERROR: When adding potions, we only recognize positive numbers, you dunce.");
         return potionCount;
      } else if (numPotions + potionCount <= potionBeltSize) {
         potionCount = numPotions + potionCount;
         // No discarded potions, return true:
         return potionCount;
      } else {
         // Too many potions, discard any extras:
         // TODO - Wait, are we letting them buy more potions than they have room for and
         // just throwing them away? ROFL
         potionCount = potionBeltSize;
         return potionCount;
      }
   }

   /**
    * This function is used to drink a potion
    * 
    * @return true if the potion is drank, false if it is not
    */
   public boolean drinkPotion() {
      if (!isAwake) {
         isAwake = true;
         timeToWake = 0;
         potionCount--;
         return true;
      } else if (potionCount > 0) {
         potionCount--;
         this.setHealth(this.getHealth() + POTION_HEAL);
         return true;
      }
      return false;
   }

   // I LOVE GOOOOOOOLD!

   // Getters and Setters
   public int getPotionCount() {
      return potionCount;
   }

   public int getPotionBeltSize() {
      return potionBeltSize;
   }

   public int getGold() {
      return gold;
   }

   public int addGold(int collectedGold) {
      return gold += collectedGold;
   }

   public int setGold(int newGoldBalance) {
      return gold = newGoldBalance;
   }

   public void died() {
      isAwake = false;
      timeToWake = 5;
      this.wakeUp();
   }

   private void wakeUp() {
      Timer timer = new Timer();
      // TODO: integrate with harvesting regen
      TimerTask awake = new TimerTask() {
         public void run() {
            timeToWake--;
            if (timeToWake == 0) {
               isAwake = true;
               PlayerCharacter.this.setHealth(5);
               timer.cancel();
            }
         }
      };
     timer.schedule(awake, 0, 1200);
   }

   public boolean isAwake() {
      return isAwake;
   }

   public String doEquipmentUpgrade(int monsterLevel, boolean isBoss) {
      return equipment.doUpgrade(monsterLevel, isBoss, this);
   }
}
