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

   // TODO: What do these stats do?
   // TODO: When do these stats increase, if at all?

   /* Constructor */
   public GameCharacter(String name, int muscle, int brain, int heart) {
      this.name = name;
      this.muscle = muscle;
      this.brain = brain;
      this.heart = heart;
   }

   /* Methods */
   public void attack(GameCharacter target) {
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