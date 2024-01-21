/*
 * Character Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey, et al., Jan. 2024
 */

class GameCharacter {
   /* Fields */
   private String name;
   private int muscle;
   private int brain;
   private int heart;
   private double health;
   private int maxHealth;
   private double mana;
   private int maxMana;
   private int dice;

   // TODO: What do these stats do?
   // TODO: When do these stats increase, if at all?

   /* Constructor */
   public GameCharacter(String name, int muscle, int brain, int heart) {
      this.name = name;
      this.muscle = muscle;
      this.brain = brain;
      this.heart = heart;
      this.maxHealth = 10 + (5 * heart);
      this.health = maxHealth;
      this.maxMana = 10 + (5 * brain);
      this.mana = maxMana;
      // TODO - Probably need to balance these stats.
   }

   /* Methods */
   public void attack(GameCharacter target) {
      dice = (int) (Math.random() * 20) + 1;
      int damage = muscle * (dice / 20);
      dice = (int) (Math.random() * 20) + 1;
      // int resist = target.getSkin(); // This is for if we add complexity later
      if(dice == 20) {
         System.out.println("Critical Hit!");
         target.health -= damage * 2;
      }
      else if(dice == 1) {
         System.out.println("Critical Miss!");
         health -= 1;
      }
      else {
         target.health -= damage;
      }
      // Method used when a character attacks another character.
      // TODO: Implementation.
   }

   // Facts are stubborn things, but stats are pliable.

   public String getName() {
      return name;
   }

   public String setName(String newName) {
      name = newName;
      return name;
   }

   public int getMuscle() {
      return muscle;
   }

   public int setMuscle(int newMuscle) {
      muscle = newMuscle;
      return muscle;
   }

   public int getBrain() {
      return brain;
   }

   public int setBrain(int newBrain) {
      brain = newBrain;
      return brain;
   }

   public int getHeart() {
      return heart;
   }

   public int setHeart(int newHeart) {
      heart = newHeart;
      return heart;
   }
}