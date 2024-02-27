/* 
 * Player Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Adrien Abbey et al. Feb. 2024
 */

/*
 * TODO: Add an image field?
 */

class Monster extends GameCharacter {

    /**
     * Predefined Monster name enum.
     */
    public enum MonsterName {
        PETROCK,
        HOBOGOBLIN
    }

    /* Fields */
    String description;

    /* Constructor */

    /**
     * The REAL Monster Constructor.
     * Uses the MonsterName enum to constrict valid inputs.
     * 
     * @param name Name of a monster from the MonsterName enum.
     * 
     */
    public Monster(MonsterName name) {
        // Create a "temporary" monster that we forcibly change (painfully):
        super("FIXME!", 999, 999, 999);

        // TODO: Balance these values.

        switch (name) {
            case PETROCK:
                this.setName("Rabid Pet Rock");
                this.setDescription(
                        "Someone glued googley eyes onto a pet rock, then abandoned it. Also, it has rabies and wants to kill you.");
                this.setMuscle(2);
                this.setBrain(2);
                this.setHealth(2);
                break;
            case HOBOGOBLIN:
                this.setName("Hobo Goblin");
                this.setDescription(
                        "Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from from all its wealth, health, and home.  Also, it has googley eyes.");
                this.setMuscle(4);
                this.setBrain(4);
                this.setHeart(4);
                break;
        }
    }

    /* Functions */

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}