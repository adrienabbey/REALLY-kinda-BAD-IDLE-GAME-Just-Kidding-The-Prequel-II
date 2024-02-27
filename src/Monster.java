/* 
 * Player Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey et al. Feb. 2024
 */

/*
 * TODO: Add an image field?
 * TODO: Add a text description field?
 */

class Monster extends GameCharacter {

    /**
     * Predefined Monster name enum.
     */
    public enum MonsterName {
        SEWERRAT,
        WEAKGOBLIN,
        STRONGGOBLIN,
        GOBLINSHAMAN,
        GOBLINKING
    }

    /* Constructors */

    /**
     * DO NOT USE THIS CONSTRUCTOR! YOU ARE A VERY BAD PERSON IF YOU DO!
     */
    public Monster(String name, int muscle, int brain, int heart) {
        super(name, muscle, brain, heart);
    }

    /**
     * The REAL Monster Constructor.
     * Uses the MonsterName enum to constrict valid input.
     * 
     * @param name Name of a monster from the MonsterName enum.
     * 
     */
    public Monster(MonsterName name) {
        // Create a "temporary" monster that we forcibly change (painfully):
        super("FIXME", 999, 999, 999);

        // TODO: Balance these values.
        if (name == MonsterName.SEWERRAT) {
            this.setName("Sewer Rat");
            this.setMuscle(1);
            this.setBrain(1);
            this.setHealth(1);
        } else if (name == MonsterName.WEAKGOBLIN) {
            this.setName("Weak Goblin");
            this.setMuscle(2);
            this.setBrain(2);
            this.setHeart(2);
        } else if (name == MonsterName.STRONGGOBLIN) {
            this.setName("Strong Goblin");
            this.setMuscle(4);
            this.setBrain(2);
            this.setHealth(4);
        } else if (name == MonsterName.GOBLINSHAMAN) {
            this.setName("Goblin Shaman");
            this.setMuscle(3);
            this.setBrain(6);
            this.setHeart(4);
        } else if (name == MonsterName.GOBLINKING) {
            this.setName("The Goblin King");
            this.setMuscle(10);
            this.setBrain(10);
            this.setHeart(10);
        }
    }
}