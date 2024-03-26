/* 
 * Monster Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
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
        HOBOGOBLIN,
        CYCLOPIAN_GENTLEMAN
    }

    /* Fields */
    private String description;
    private int goldRewarded;
    private boolean isBoss;
    private boolean castsMagic;

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
                this.setBrain(1);
                this.setHealth(3);
                this.goldRewarded = 2;
                this.isBoss = false;
                this.castsMagic = false;
                break;
            case HOBOGOBLIN:
                this.setName("Hobo Goblin");
                this.setDescription(
                        "Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from from all its wealth, health, and home.  Also, it has googley eyes.");
                this.setMuscle(6);
                this.setBrain(2);
                this.setHeart(2);
                this.goldRewarded = 4;
                this.isBoss = false;
                this.castsMagic = false;
                break;
            case CYCLOPIAN_GENTLEMAN:
                this.setName("Cyclopian Gentleman");
                this.setDescription(
                        "This impecably dressed giant just radiates cultured sophistication.  He's even wearing a tophat with a monocle over his single, giant eye.  Unfortunately, you're too small to notice and he's about to step all over you.  Which is not to say he didn't actually see you, he just doesn't care.");
                this.setMuscle(10);
                this.setBrain(10);
                this.setHeart(10);
                this.goldRewarded = 20;
                this.isBoss = true;
                this.castsMagic = true;
        }
    }

    /* Functions */

    /**
     * @param description Monsters have feelings, and sometimes they just need
     *                    to let it all out. Or be told what they are. By
     *                    others. They're just monsters. They don't have
     *                    feelings. Be mean. Be descriptive. Tell the monster
     *                    what you think of it.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns a string description of the monster. Very descriptive.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Returns the amount of gold rewarded for slaying this creature.
     */
    public int getGoldReward() {
        return goldRewarded;
    }

    /**
     * @return Returns 'true' if this monster is a boss, 'false' if not.
     */
    public boolean isBoss() {
        return isBoss;
    }

    /**
     * @return Returns 'true' if this monster casts magic, 'false' if not.
     */
    public boolean castsMagic() {
        return castsMagic;
    }
}