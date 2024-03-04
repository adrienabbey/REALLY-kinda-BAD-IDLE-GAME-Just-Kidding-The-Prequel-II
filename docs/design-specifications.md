# Design Specification Documenation

1. 
    1. 
    2. 
    3. 
2. Saving and loading the Player Character is done by implementing serializable on the Player Character class.  This enables saving the Player Character object to a file.  Public functions to do this are implemented in the Driver class.  The GUI will enable loading or saving the character.
    1. Loading a character is done from the main menu.  Saving a character is done in-game.
        1. From the main menu, the player can optionally load an existing character.  This loads the Player Character object from the save file, which then proceeds to the map screen.
        2. Saving the character can be done any time outside a dungeon.  This will be done either by clicking a GUI button on the world map, or automatically whenever transitioning into/out of a dungeon (to be determined).
            1. Saving is done much like loading, where the Player Character object is serialized and written to the save file.
    2. The Player Character object includes fields for the character's name, its stats (muscle, brain, heart), current gold, as well as the following:
        1.  The player's equipment will consist of a weapon, armor, and a hat.  These will likely be simple integer fields.
        2.  The player's carried potion count will be integer field of the player character.
            1.  Likewise, the potion belt size will also be an integer field of the player character.
            2.  Upgrading the potion belt size will be done from the town screen, requiring an exponentially scaling sum of gold.
3. 
    1. 
4. The town shop is a seperate GUI window that uses the following functions:
    1. The player's potion belt is upgraded here.  The player can choose to spend a scaling amount of gold to upgrade their belt size.  Larger sizes require exponentially more gold.  This will be implemented as a Shop class function.  A UI button will call the funciton.  Exact costs TBD.
    2. The player can also buy potions here.  Each potion will have a fixed gold cost.  This will also be a Shop class function, called by a UI button.
    3. The player can also use a potion from their belt to heal themselves here.  The exact formula for how much a potion heals is TBD.
5. 
    1. 
        1. 
        2. 
    2. 
        1. 
    3. 
        1. 
    4. 
    5. 
        1. 
    6. 
        1. 
    7. 
        1. 
        2. 
6.  The player's gear will be implemented as simple integer fields of the Player Character object.
    1. Defeating monsters in dungeons will offer a chance to upgrade a piece of equipment.  This will be done using an Equipment Upgrade class with functions that determine the chance to upgrade after every fight.
        1. This function will compare the enemy's stats against the player's stats to determine this chance, where a stronger enemy will increase the chances, while a weaker enemy may reduce this chance to zero.  The exact algorithm is TBD.
        2.  If an upgrade is rewarded, it will upgrade a single piece of equipment.  Which piece of equipment is upgraded will be randomly chosen by another algorithm, weighted more favorably towards weaker items.

## Signatures

- 
- 
- 
- 