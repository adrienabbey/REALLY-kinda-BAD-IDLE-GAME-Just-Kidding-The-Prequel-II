NOTE: This is intended to be merged with design documentation from the others working on this project!

# Design Specification Documenation
> This document should one for one describe how each requirement will be met through implementation details. Where appropriate provide graphics (e.g., mock-ups) that show what an interface should look like. In the coming days, we will also discuss different types of ways of describing interactions (e.g., data flow diagrams, swim lanes, sequence diagrams) which might be useful to include, as well.

## Adrien's Design Specs
### Saving and Loading the Player Character
- Players should be able to save and close the game without losing progression.
    - This should probably happen only outside dungeons.
    - This could also be done automatically when transitioning into/out of dungeons.
- Everything relevant to the player's character *should be* a part of the player character object.
- Thus, saving the player character object to the disk *should be* all that is needed.
    - This is done by implementing serializable on the Player Character class, and writing two functions to save and load that object to the disk.
    - These functions are written and currently exist in the Driver class.
    - The UI and/or driver class will need to call these functions when appropriate.
- Currently, the save file has a fixed name (meaning only one save file).
    - Stretch goal: Implement the ability to have more than one save file, choose the save location.

### Implement the Monster Class, Monster Design
- Combat with monsters is a key function of this game's design.
- Having a variety of monsters to fight helps not only add flavor to the game, but also creates a difficulty scale, where the player fights easy monsters to get strong enough to fight harder monsters, creating a sense of progression besides just bigger numbers.
- Monsters currently have predefined names, descriptions, statistics (muscle, brain, heart), and a fixed amount of gold they drop when defeated.
    - TODO: Add more monster designs.  Currently there's only two 'easy' monsters.
        - This includes unique names and descriptions, as well as stats.
            - Stats should have some uniqueness to them.  Ex: strong but fragile attackers, sturdy but weak tanks, etc.
    - TODO: Since we're implementing multiple dungeons, we need a variety of monster types for each dungeon.
    - TODO: Consider implementing "bosses".
        - As originally discussed, every X fights, the "boss" spawns and fights the player.
        - The boss is stronger than normal enemies, making it a harder (but more rewarding) fight.

### Implement Equipment
- The player's primary form of progression is in the form of their equipment.
    - This is because the player does NOT gain levels like many other games.
    - We have stretch goals to implement a skill system, but that is unlikely to be implemented before our current due date.
- Players 'find' equipment as they fight monsters in the dungeon.
    - This is completely automatic.  The player has zero control over this.
    - After every fight, there is a chance to find a better piece of equipment.
        - If the found item is better, it automatically equips in place of the old item.
        - The chance of the upgrade depends on the level difference between the player's equipment level and the defeated monster's level.
            - NOTE: Monster "level" is likely just going to be the sum of its stats, then normalized.  TBD.
            - A much lower level item is much more likely to upgrade.
            - Only a single item can upgrade each fight.
            - It's possible to have disparity in equipment levels.  It's more liklely to upgrade a lower level item than a higher level item, but not impossible.
            - Bosses, if implemented, will likely have better chances at item upgrades.
            - Fighting 'weak' enemies will likely eventually drop the chance of upgrade to zero due to level differences.