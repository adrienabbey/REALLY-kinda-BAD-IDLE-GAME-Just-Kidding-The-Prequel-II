## Final Requirement Specification Document
- E = Evaluation criteria
  
---
1. The game shall be operated via a graphical interface and buttons that shall be on screen.
    1. The whole interface shall be controlled from a central Driver to remove window swaps when changing screens and unify the screens.
    2. The game shall have a start screen for creating a new game, loading a saved game, reading instructions, or exiting.
    3. There will be a character screen so the user can see their character's condition.

```
1E. Observe if the game has graphical user interface and that buttons are 
    included.

    1e. Check whether the interface is controlled by the Driver by obersing the 
        code. Check whether screen swaps are removed and screens are unified.
       
    2e. Observe is a start screen is present, and if the new game, load game, 
        instructions, and exit buttons are present and functional.
       
    3e. Run game and check if a character screen is present that includes a 
        summary of the character's condition. 
```
---

2. The user shall be able to choose between loading a save or creating a new character.
    1. The user shall be able to save and load their character.
    2. A character shall consist of a name, muscle stat, brain stat, and heart stat.
        1. The character will have equipment (weapon, armor, hat), and some amount of gold.
        2. The character will have a potion belt (upgradable) which can be filled with potions.
            1. The potion belt has a maximum number of potions that can be carried.
            2. Upgrading the potion belt will increase this maximum.
   
```
2E. Test if the load, save, and character creation implementation is present and 
    functional.

    ie. Observe whether yhe save button in the start screen saves player data to 
        a file correctly, and that the load button will use this save file to 
        load player data to the active game. Check whether the loaded player 
        data matches with the saved player data.

    iie. Check if a name, muscle stat, brain stat, and heart stat are present 
        for each player character info.

        Ae. Check player's inventory to see if equipment and gold is current 
            present or can be present in inventory after acquiring.

        Be. Check the player's inventory for a potion belt that contains potions 
            and can be upgraded.

            1e. Observe if the potion belt can have a maximum number of potions.
            
            2e. Observe if this maximum is increased whenever the belt is 
                upgraded.
```
---

3. The user shall start at a world map screen upon picking a character, loaded or fresh.
    1. The map screen will have a selection for a town shop and a dungeon. Dungeons will have an option for increasing their difficulty.
```
3E. Check if user gets to the world map screen after choosing player stats, 
    loading a save file. or creating a new one.

    ie.  Check wether the map screen has buttons pretaining to a town and 
        dungeon. Test whether the dungeon can be selected with increasing 
        difficulty.
```
---

4. The town shop will sell potions to heal the adventurer. The town is entered from the map screen.
    1. The player's potion belt can be upgraded in size.  This is the primary method of spending gold.
    2. The player can buy potions to refill their belt.
    3. The player can also use a potion to restore their health in the inventory screen.
```
4E. Check if potions can be bought from the town shop screen. Check if the town 
    screen can be accessed from the map screen.

    ie. Upgrade the potion belt size and check if more potions can be 
        successfully added.  

    iie. Check whether a buy button is present and when pressed refills the user 
        belt potion by a standarized amount.

    iiie. Check whether a player can use a potion in their inventory screen.
```
---

5. The dungeon shall be where the primary combat loop takes place.  Dungeons are selected from the map screen.
    1. Dungeons can only be entered when the player's health is above zero.
        1. There are multiple dungeon areas to choose from, each with a different difficulty level.
        2. Each dungeon will have a list of monsters that can spawn in them.
    2. The player will be able to toggle between offensive and healing magic with a button.
        1. Casting this way is based on a random dice roll, influenced by their brain statistic and their remaining mana points.
    3. Combat hits and damage shall be affected by dice rolls.
        1. The player and the enemy monster take turns attacking each other.
    4. The player can click a button to use a potion from their belt to heal.
    5. If the players HP hits 0, they can't continue combat until either a certain amount of time or until healed with a potion.
        1. This returns the player to the map screen, disabling dungeons.
    6. If the monster hits 0 HP, the player is rewarded with gold and a chance at equipment upgrades.
        1. After defeating a monster, a new monster replaces it.
    7. The player can choose to withdraw from the dungeon at any time.
        1. After the user clicks the withdraw button, the current combat will resolve normally.
        2. Withdrawing from a dungeon without dying will return the player to the map screen.
```
5E. Check whether the map screen contains a dungeon button and that the dungeon 
    can be accessed by pressing it.

    ie. Check whether dungeons can only be accessed if the player's health is 
        above zero.

        Ae. Check whether the dungeon screen has multiple areas to choose from 
            and that different difficulties are present.

        Be. Check wether monsters are generated once entering a dungeon and that 
            each dungeon have at least one differing monster generated.

    iie. Check whether there is a button that toggles between offensive and 
        healing magic. Observe if toggles tis button has the intended effect.
        
        Ae. Check whether toggling this button casts the magic heal or attack 
            based on a dice roll. Observe if output of magic casted is based in 
            some way on the brain stat.

    iiie. Observe if dice rolls affect combat damage recived and done.

        Ae. Check whether combat is turn based.

    IVe. Check whether a button is present in the attack screen that uses a 
        potion from the potion belt. Check whethe using a potion in this way 
        heals the player and consumes one potion from the belt.

    Ve. The system correctly detects when the player's HP reaches 0. Combat is 
        appropriately paused upon reaching 0 HP. There is a clear indication of 
        how the player can resume combat (e.g., after a certain amount of time 
        or using a healing potion). The healing process restores the player's HP 
        to a functional level, allowing them to continue combat.

    VIe. The system accurately detects when a monster's HP reaches 0. Gold and 
        equipment upgrade rewards are appropriately given upon defeating the 
        monster The chance for equipment upgrades occurs randomly and adheres to 
        the specified probability. There is clear feedback to the player 
        regarding the rewards they receive after defeating the monster.

        Ae. Check whether a new monster is spawned in after defeating one.

    VIIe. A clearly visible option/button exists for the player to choose to 
        withdraw from the dungeon. Clicking the withdraw button initiates the 
        withdrawal process.

        Ae. Combat resolves normally upon withdrawal, ensuring no 
            inconsistencies or glitches.

        Be. Upon withdrawal, the player is correctly returned to the map screen, 
            disabling further dungeon exploration.
```
---

6. The player will have gear consisting of a weapon, armor and head-piece.
    1. This gear will be obtained by defeating enemies in the dungeon.
        1. The chance at an upgrade is based on the current dungeon level, current gear levels, and the disparity between those levels.
        2. Each combat will upgrade a single piece of equipment, at most. Lower level gear has a higher chance to upgrade than those with higher levels.
```
6E. Ensure that the player's gear includes a weapon, armor, and head-piece.

    ie. Gear is acquired through defeating enemies in the dungeon.

        Ae. The upgrade chance calculation considers the current level of the 
            dungeon. It also takes into account the levels of the player's 
            current gear. The disparity between current gear levels and 
            potential upgrade levels is calculated accurately.

        Be. Check whhether each combat session upgrades a single piece of 
            equipment at most. Check wower-level gear has a higher chance to 
            upgrade compared to higher-level gear.
```
---

## Terms:
1. Character: The playable and modifiable object that the player will use throughout the game. A character object includes things like stats, a name, and equipment.
2. Stat: A stat, or statistic, is the modifable values a character object contains.. These values affect the in-game behaviors of the character, such as with combat.
3. Muscle, brain, and heart stat: These stats modifies the players damage, mana points (MP), and heath points (HP) respectively.
4. Gold: The in-game monatery system that lets the player purchase potions. Gold can be acquired through combat in the dungeon.
5. Dungeon: Area where combat will take place. Contains monsters.
6. Monsters: Enemy objects that the player must fight.
7. Potion: Item that heals the player. Can be purchased from the town area.
8. Equipment: Items in te game that can be eqipped to the character and modify stats. Can be acquired through combat in the dungeon. 

## Signatures
- 
- 
- 
- 