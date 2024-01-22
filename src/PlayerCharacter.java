/*
 * Player Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey, et al., Jan. 2024
 */

class PlayerCharacter extends GameCharacter {
   /* Variables */
   // TODO: Determine base "starting" stats:
   private static final int startingMuscle = 3;
   private static final int startingBrain = 3;
   private static final int startingHeart = 3;

   /* Fields */
   private int statpoints;
   private int gold;
   private int potionBeltSize;
   private int potionCount;
   private boolean isAwake = true;
   private int timeToWake = 0;
   // TODO: Implement the "Gear" class so players can have equipment:
   // private Gear inventory;

   /* Constructors */

   // Simple constructor for starting player characters:
   public PlayerCharacter(String name) {
      super(name, startingMuscle, startingBrain, startingHeart);
   }

   // Constructor for loading player stats from a save file?
   // TODO: Include player Gear loading.
   public PlayerCharacter(String name, int muscle, int brain, int heart, int gold, int potionBeltSize,
         int potionCount) {
      super(name, muscle, brain, heart);
      this.gold = gold;
      this.potionBeltSize = potionBeltSize;
      this.potionCount = potionCount;
   }

   /* Methods */

   // Here, thou incestuous, murderous, damned Dane, Drink off this potion!

   public int getPotionCount() {
      return potionCount;
   }

   public int getPotionBeltSize() {
      return potionBeltSize;
   }

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
         // TODO - Wait, are we letting them buy more potions than they have room for and just throwing them away? ROFL
         potionCount = potionBeltSize;
         return potionCount;
      }
   }

   public boolean setPotion(int newPotionCount) {
      if (newPotionCount > 0 && newPotionCount < potionBeltSize) {
         potionCount = newPotionCount;
         return true;
      } else {
         System.err.println(
               "ERROR: When setting the number of potions being carried, it cannot be negative or more than the maximum potion count.");
         return false;
      }
   }

   public boolean drinkPotion() {
      if(!isAwake){
         isAwake = true;
         timeToWake = 0;
         potionCount--;
         return true;
      }else if (potionCount > 0) {
         
         potionCount--;
         return true;
      }
      // TODO: What does drinking a potion do?
      // TODO: What if there's no more potions to drink?
      return false;
   }

   // I LOVE GOOOOOOOLD!

   public int getGold() {
      return gold;
   }

   public int addGold(int collectedGold) {
      gold += collectedGold;
      return gold;
   }

   public int setGold(int newGoldBalance) {
      gold = newGoldBalance;
      return gold;
   }
}