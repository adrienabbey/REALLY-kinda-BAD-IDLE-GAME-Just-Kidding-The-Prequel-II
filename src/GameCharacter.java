/*
 * Character Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey, et al., Jan. 2024
 */

import java.io.Serializable;

class GameCharacter implements Serializable {
   /* Fields */
   private String name;
   private int muscle;
   private int brain;
   private int heart;
   private double health;
   private int maxHealth;
   private double magic;
   private int maxMagic;
   private Dice dice = new Dice(20);

   /**
    * Constructor for GameCharacter class
    * 
    * @param name   sets the name of the character
    * @param muscle how hard the character hits
    * @param brain  how strong the characters magic is
    * @param heart  how much health the character has
    */
   public GameCharacter(String name, int muscle, int brain, int heart) {
      this.name = name;
      this.muscle = muscle;
      this.brain = brain;
      this.heart = heart;
      this.maxHealth = 10 + (5 * heart);
      this.health = maxHealth;
      this.maxMagic = 10 + (5 * brain);
      this.magic = maxMagic;
      // TODO - Probably need to balance these stats.
   }

   public GameCharacter(GameCharacter other) {
      this.name = other.name;
      this.muscle = other.muscle;
      this.brain = other.brain;
      this.heart = other.heart;
      this.health = other.health;
      this.maxHealth = other.maxHealth;
      this.magic = other.magic;
      this.maxMagic = other.maxMagic;
   }

   /**
    * This function is used to attack another character
    * 
    * @param target the character that is being attacked
    */
   public void attack(GameCharacter target) {
      dice.roll();
      double damage = muscle * (((double) dice.getLast()) / 20);
      damage = Math.round(damage * 10.0) / 10.0; // Round to 1 decimal place
      dice.roll();
      // int resist = target.getSkin(); // This is for if we add complexity later
      if (dice.getLast() == 20) {
         target.health -= damage * 2;
         Combat.addLog(name + " critical hit " + target.getName() + " and they took " + damage * 2 + " damage!\n");
      } else if (dice.getLast() == 1) {
         Combat.addLog(name + " critical missed and took 1 damage!\n");
         health -= 1;
      } else {
         target.health -= damage;
         Combat.addLog(name + " hit " + target.getName() + " for " + damage + " damage!\n");
      }
      // Method used when a character attacks another character.
   }

   public void magicAttack(GameCharacter target) {
      dice.roll();
      double damage = brain * (((double) dice.getLast()) / 20);
      damage = Math.round(damage * 10.0) / 10.0; // Round to 1 decimal place
      dice.roll();
      if (dice.getLast() == 20) {
         Combat.addLog(
               name + "'s magic critical hit " + target.getName() + " and the took " + damage * 2 + " damage!\n");
         target.health -= damage * 2;
      } else if (dice.getLast() == 1) {
         Combat.addLog(name + "'s magic critical missed and they took 1 damage!\n");
         health -= 1;
      } else {
         target.health -= damage;
         Combat.addLog(name + "'s magic hit " + target.getName() + " for " + damage + " damage!\n");
      }
      magic -= 1;
   }

   public void magicHeal() {
      dice.roll();
      double heal = brain * (((double) dice.getLast()) / 20);
      heal = Math.round(heal * 10.0) / 10.0; // Round to 1 decimal place
      dice.roll();
      if (dice.getLast() == 20) {
         Combat.addLog(name + " critical heal's themselves for " + heal * 2 + " health!\n");
         setHealth(health + (heal * 2));
      }
      else if(dice.getLast() == 1) {
         Combat.addLog(name + " critical missed! a heal and took 1 damage!\n");
         health -= 1;
      } else {
         setHealth(health + (heal * 2));
         Combat.addLog(name + " healed themselves for " + heal + " health!\n");
      }
      magic -= 1;
   }

   // Getters and Setters
   public String getName() {
      return name;
   }

   public String setName(String newName) {
      return name = newName;
   }

   public int getMuscle() {
      return muscle;
   }

   public int setMuscle(int newMuscle) {
      return muscle = newMuscle;
   }

   public int getBrain() {
      return brain;
   }

   public int setBrain(int newBrain) {
      maxMagic = 10 + (5 * newBrain);
      return brain = newBrain;
   }

   public int getHeart() {
      return heart;
   }

   public int setHeart(int newHeart) {
      maxHealth = 10 + (5 * newHeart);
      return heart = newHeart;
   }

   public double getHealth() {
      return health;
   }

   /**
    * @param newHealth Sets this character's health to a new value, up to its
    *                  max health. Note: this CAN be negative.
    * @return Returns the new health value.
    */
   public double setHealth(double newHealth) {
      if (newHealth > maxHealth) {
         return health = maxHealth;
      } else {
         return health = newHealth;
      }
   }

   public int getMaxHealth() {
      return maxHealth;
   }

   public double getMagic() {
      return magic;
   }

   public double setMagic(double newMagic) {
      return magic = newMagic;
   }

   public int getMaxMagic() {
      return maxMagic;
   }
}