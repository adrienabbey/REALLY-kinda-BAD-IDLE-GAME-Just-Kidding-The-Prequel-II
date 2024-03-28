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
        CYCLOPIAN_GENTLEMAN,
        GEODUCK
    }

    /* Fields */
    private String description;
    private int goldRewarded;
    private boolean isBoss;
    private boolean castsMagic;
    private String imageFilePath;
    private int monsterLevel;

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
                setName("Rabid Pet Rock");
                setDescription(
                        "Someone glued googley eyes onto a pet rock, then abandoned it. Also, it has rabies and wants to kill you.");
                setMuscle(2);
                setBrain(1);
                setHealth(3);
                goldRewarded = 2;
                isBoss = false;
                castsMagic = false;
                imageFilePath = "assets/images/rabidPetRock.png";
                monsterLevel = 2;
                break;
            case HOBOGOBLIN:
                setName("Hobo Goblin");
                setDescription(
                        "Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from from all its wealth, health, and home.  Also, it has googley eyes.");
                setMuscle(6);
                setBrain(2);
                setHeart(4);
                goldRewarded = 4;
                isBoss = false;
                castsMagic = false;
                imageFilePath = "assets/images/hoboGoblin.png";
                monsterLevel = 4;
                break;
            case CYCLOPIAN_GENTLEMAN:
                setName("Cyclopian Gentleman");
                setDescription(
                        "This impecably dressed giant just radiates cultured sophistication.  He's even wearing a tophat with a monocle over his single, giant eye.  Unfortunately, you're too small to notice and he's about to step all over you.  Which is not to say he didn't actually see you, he just doesn't care.");
                setMuscle(10);
                setBrain(10);
                setHeart(10);
                goldRewarded = 20;
                isBoss = true;
                castsMagic = true;
                imageFilePath = "assets/images/cyclopianGentleman.png";
                monsterLevel = 8;
                break;
            case GEODUCK:
                setName("Creepy Geoduck");
                setDescription(
                        "Some people eat these.  No, really, like legitimate food.  Just think about that.");
                setMuscle(4);
                setBrain(8);
                setHeart(6);
                goldRewarded = 6;
                isBoss = false;
                castsMagic = true;
                imageFilePath = "assets/images/DrawAGeoduckLuke.png";
                monsterLevel = 6;
                break;
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

    /**
     * @return Returns a file path string for the monster's image file.
     */
    public String getImageFilePath() {
        return imageFilePath;
    }

    /**
     * @return Returns some arbitrary number relating to the monster level.
     */
    public int getMonsterLevel() {
        return monsterLevel;
    }
}