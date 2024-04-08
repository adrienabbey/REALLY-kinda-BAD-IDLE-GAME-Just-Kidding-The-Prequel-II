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
        GEODUCK,
        PIRATE_SKELETON
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
        super((switch (name){
            case PETROCK -> "Rabid Pet Rock";
            case HOBOGOBLIN -> "Hobo Goblin";
            case CYCLOPIAN_GENTLEMAN -> "Cyclopian Gentleman";
            case GEODUCK -> "Creepy Geoduck";
            case PIRATE_SKELETON -> "Cute Skelly Pirate";
        }), (switch (name){
            case PETROCK -> 2;
            case HOBOGOBLIN -> 3;
            case CYCLOPIAN_GENTLEMAN -> 10;
            case GEODUCK -> 4;
            case PIRATE_SKELETON -> 8;
        }), (switch (name){
            case PETROCK -> 1;
            case HOBOGOBLIN -> 1;
            case CYCLOPIAN_GENTLEMAN -> 10;
            case GEODUCK -> 8;
            case PIRATE_SKELETON -> 4;
        }), (switch (name){
            case PETROCK -> 3;
            case HOBOGOBLIN -> 2;
            case CYCLOPIAN_GENTLEMAN -> 10;
            case GEODUCK -> 6;
            case PIRATE_SKELETON -> 6;
        }));

        // TODO: Balance these values.

        switch (name) {
            case PETROCK:
                setDescription(
                        "Someone glued googley eyes onto a pet rock, then abandoned it. Also, it has rabies and wants to kill you.");
                goldRewarded = 2;
                isBoss = false;
                castsMagic = false;
                imageFilePath = "assets/images/Rock2.png";
                monsterLevel = 99;
                break;
            case HOBOGOBLIN:
                setDescription(
                        "Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from from all its wealth, health, and home.  Also, it has googley eyes.");
                goldRewarded = 2;
                isBoss = false;
                castsMagic = false;
                imageFilePath = "assets/images/hobogoblin.png";
                monsterLevel = 99;
                break;
            case CYCLOPIAN_GENTLEMAN:
                setDescription(
                        "This impecably dressed giant just radiates cultured sophistication.  He's even wearing a tophat with a monocle over his single, giant eye.  Unfortunately, you're too small to notice and he's about to step all over you.  Which is not to say he didn't actually see you, he just doesn't care.");
                goldRewarded = 20;
                isBoss = true;
                castsMagic = true;
                imageFilePath = "assets/images/Cyclops.png";
                monsterLevel = 8;
                break;
            case GEODUCK:
                setDescription(
                        "Some people eat these.  No, really, like legitimate food.  Just think about that.");
                goldRewarded = 6;
                isBoss = false;
                castsMagic = true;
                imageFilePath = "assets/images/Duck.png";
                monsterLevel = 6;
                break;
            case PIRATE_SKELETON:
                setDescription(
                        "This unbelievably cute skeleton is wearing a pirate outfit.  Despite its adorable demeanor, it still has a deep-seated hatred of the living.");
                goldRewarded = 6;
                isBoss = false;
                castsMagic = false;
                imageFilePath = "assets/images/skeleton.png";
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