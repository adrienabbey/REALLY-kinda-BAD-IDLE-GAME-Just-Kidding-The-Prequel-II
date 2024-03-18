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
   private double magic;
   private int maxMagic;
   //private int dice;
   private Dice dice = new Dice(20);

   // TODO: What do these stats do?
   // TODO: When do these stats increase, if at all?

   /**
    * Constructor for GameCharacter class
    * @param name sets the name of the character
    * @param muscle how hard the character hits
    * @param brain how strong the characters magic is
    * @param heart how much health the character has
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

   /**
    * This function is used to attack another character
    * @param target the character that is being attacked
    */
   public void attack(GameCharacter target) {
      dice.roll();
      int damage = muscle * (dice.getLast() / 20);
      dice.roll();
      // int resist = target.getSkin(); // This is for if we add complexity later
      System.out.println(name + " just attacked.");
      if(dice.getLast() == 20) {
         target.health -= damage * 2;
         Combat.addLog(name + " critical hit " + target.getName() + " and they took " + damage * 2 + " damage!");
      }
      else if(dice.getLast() == 1) {
         Combat.addLog(name + " critical missed and took 1 damage!");
         health -= 1;
      }
      else {
         target.health -= damage;
         Combat.addLog(name + " hit " + target.getName() + " for " + damage + " damage!");
      }
      // Method used when a character attacks another character.
      // TODO: Implementation.
   }

   public void magicAttack(GameCharacter target){
      dice.roll();
      int damage = brain * (dice.getLast() / 20);
      dice.roll();
      if(dice.getLast() == 20) {
         Combat.addLog(name + " critical hit " + target.getName() + " and the took " + damage * 2 + " damage!");
         target.health -= damage * 2;
      }
      else if(dice.getLast() == 1) {
         Combat.addLog(name + " critical missed and they took 1 damage!");
         health -= 1;
      }
      else {
         target.health -= damage;
         Combat.addLog(name + " hit " + target.getName() + " for " + damage + " damage!");
      }
      magic -= 1;
   }

   public void magicHeal(){
      dice.roll();
      int heal = brain * (dice.getLast() / 20);
      dice.roll();
      if(dice.getLast() == 20) {
         Combat.addLog(name + " critical heal's themselves for " + heal * 2 + " health!");
         health += heal * 2;
      }
      else if(dice.getLast() == 1) {
         Combat.addLog(name + " critical missed! a heal and took 1 damage!");
         health -= 1;
      }
      else {
         health += heal;
         Combat.addLog(name + " healed themselves for " + heal + " health!");
      }
      magic -= 1;
   }

   // Getters and Setters
   public String getName() {  return name;   }
   public String setName(String newName) {   return name = newName;  }
   public int getMuscle() {   return muscle; }
   public int setMuscle(int newMuscle) {  return muscle = newMuscle; }
   public int getBrain() { return brain;  }
   public int setBrain(int newBrain) { return brain = newBrain;   }
   public int getHeart() { return heart;  }
   public int setHeart(int newHeart) { return heart = newHeart;   }
   public double getHealth() { return health; }
   public double setHealth(double newHealth) { return health = newHealth; }
   public int getMaxHealth() { return maxHealth; }
   public double getMagic() { return magic; }
   public double setMagic(double newMagic) { return magic = newMagic; }
   public int getMaxMagic() { return maxMagic; }
}