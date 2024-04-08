## Final Requirement Specification Document
> Evaluation criteria
  
---
1. The game shall be operated via a graphical interface and buttons that shall be on screen.
    1. The whole interface shall be controlled from a central Driver to remove window swaps when changing screens and unify the screens.
    2. The game shall have a start screen for creating a new game, loading a saved game, reading instructions, or exiting.
    3. There will be a character screen so the user can see their character's condition.


> 1. Observe if the game has graphical user interface and that buttons are included.
>    1. Check whether the interface is controlled by the Driver by obersing the code. Check whether screen swaps are removed and screens are unified.   
>    2. Observe is a start screen is present, and if the new game, load game, instructions, and exit buttons are present and functional.       
>    3. Run game and check if a character screen is present that includes a summary of the character's condition. 

---

2. The user shall be able to choose between loading a save or creating a new character.
    1. The user shall be able to save and load their character.
    2. A character shall consist of a name, muscle stat, brain stat, and heart stat.
        1. The character will have equipment (weapon, armor, hat), and some amount of gold.
        2. The character will have a potion belt (upgradable) which can be filled with potions.
            1. The potion belt has a maximum number of potions that can be carried.
            2. Upgrading the potion belt will increase this maximum.
   

> 2. Test if the load, save, and character creation implementation is present and functional.
>    1. Observe whether yhe save button in the start screen saves player data to a file correctly, and that the load button will use this save file to load player data to the active game. Check whether the loaded player data matches with the saved player data.
>    2. Check if a name, muscle stat, brain stat, and heart stat are present for each player character info.
>        1. Check player's inventory to see if equipment and gold is current present or can be present in inventory after acquiring.
>        2. Check the player's inventory for a potion belt that contains potions and can be upgraded.
>            1. Observe if the potion belt can have a maximum number of potions.
>            2. Observe if this maximum is increased whenever the belt is upgraded.

---

3. The user shall start at a world map screen upon picking a character, loaded or fresh.
    1. The map screen will have a selection for a town shop and a dungeon. Dungeons will have an option for increasing their difficulty.

>3. Check if user gets to the world map screen after choosing player stats, loading a save file. or creating a new one.
>    1.  Check wether the map screen has buttons pretaining to a town and dungeon. Test whether the dungeon can be selected with increasing difficulty.

---

4. The town shop will sell potions to heal the adventurer. The town is entered from the map screen.
    1. The player's potion belt can be upgraded in size.  This is the primary method of spending gold.
    2. The player can buy potions to refill their belt.
    3. The player can also use a potion to restore their health in the inventory screen.

>4. Check if potions can be bought from the town shop screen. Check if the town screen can be accessed from the map screen.
>    1. Upgrade the potion belt size and check if more potions can be successfully added.  
>    2. Check whether a buy button is present and when pressed refills the user belt potion by a standarized amount.
>    3. Check whether a player can use a potion in their inventory screen.

---

5. The dungeon shall be where the primary combat loop takes place.  Dungeons are selected from the map screen.
    1. Dungeons can only be entered when the player's health is above zero.
        1. There are multiple dungeon areas to choose from, each with a different difficulty level.
        2. Each dungeon will have a list of monsters that can spawn in them.
    2. The player will be able to toggle between offensive and healing magic with a button.
    2. The player will be able to toggle between offensive and healing magic with a button.
        1. Casting this way is based on a random dice roll, influenced by their brain statistic and their remaining mana points.
    3. Combat hits and damage shall be affected by dice rolls.
    3. Combat hits and damage shall be affected by dice rolls.
        1. The player and the enemy monster take turns attacking each other.
    4. The player can click a button to use a potion from their belt to heal.
    5. If the players HP hits 0, they can't continue combat until either a certain amount of time or until healed with a potion.
    5. If the players HP hits 0, they can't continue combat until either a certain amount of time or until healed with a potion.
        1. This returns the player to the map screen, disabling dungeons.
    6. If the monster hits 0 HP, the player is rewarded with gold and a chance at equipment upgrades.
    6. If the monster hits 0 HP, the player is rewarded with gold and a chance at equipment upgrades.
        1. After defeating a monster, a new monster replaces it.
    7. The player can choose to withdraw from the dungeon at any time.
        1. After the user clicks the withdraw button, the current combat will resolve normally.
        2. Withdrawing from a dungeon without dying will return the player to the map screen.

> 5. Check whether the map screen contains a dungeon button and that the dungeon can be accessed by pressing it.
>    1. Check whether dungeons can only be accessed if the player's health is above zero.
>        1. Check whether the dungeon screen has multiple areas to choose from and that different difficulties are present.
>        2. Check wether monsters are generated once entering a dungeon and that each dungeon have at least one differing monster generated.
>    2. Check whether there is a button that toggles between offensive and healing magic. Observe if toggles tis button has the intended effect.
>        1. Check whether toggling this button casts the magic heal or attack based on a dice roll. Observe if output of magic casted is based in some way on the brain stat.
>    3. Observe if dice rolls affect combat damage recived and done.
>        1. Check whether combat is turn based.
>    4. Check whether a button is present in the attack screen that uses a potion from the potion belt. Check whethe using a potion in this way heals the player and consumes one potion from the belt.
>    5. The system correctly detects when the player's HP reaches 0. Combat is appropriately paused upon reaching 0 HP. There is a clear indication of how the player can resume combat (e.g., after a certain amount of time or using a healing potion). The healing process restores the player's HP to a functional level, allowing them to continue combat.
>    6. The system accurately detects when a monster's HP reaches 0. Gold and equipment upgrade rewards are appropriately given upon defeating the monster The chance for equipment upgrades occurs randomly and adheres to the specified probability. There is clear feedback to the player regarding the rewards they receive after defeating the monster.
>        1. Check whether a new monster is spawned in after defeating one.
>    7. A clearly visible option/button exists for the player to choose to withdraw from the dungeon. Clicking the withdraw button initiates the withdrawal process.
>        1. Combat resolves normally upon withdrawal, ensuring no inconsistencies or glitches.
>        2. Upon withdrawal, the player is correctly returned to the map screen, disabling further dungeon exploration.

---

6. The player will have gear consisting of a weapon, armor and head-piece.
    1. This gear will be obtained by defeating enemies in the dungeon.
        1. The chance at an upgrade is based on the current dungeon level, current gear levels, and the disparity between those levels.
        2. Each combat will upgrade a single piece of equipment, at most. Lower level gear has a higher chance to upgrade than those with higher levels.

> 6. Ensure that the player's gear includes a weapon, armor, and head-piece.
>    1. Gear is acquired through defeating enemies in the dungeon.
>        1. The upgrade chance calculation considers the current level of the dungeon. It also takes into account the levels of the player's current gear. The disparity between current gear levels and potential upgrade levels is calculated accurately.
>        2. Check whhether each combat session upgrades a single piece of equipment at most. Check wower-level gear has a higher chance to upgrade compared to higher-level gear.

7. The player will be able mine ore, scaveng area, cut trees, and hunt wildlife.
    1. Wood, stone, metal and various other resources will be stored in the player's inventory.
    2. The player can choose to either "mine ore" or "scavenge area" using buttons located in the mineshaft panel.
    3. Mining ore will allow the player to harvest stone or metal. Scavenging will allow the player to harvest magical essence, spleenwort, or tongue fern.
    5. The player can choose to either "cut wood" or "hunt wildlife" using buttons located in the forest panel.
    6. Cutting wood will allow the player to harvest wood. Hunting will allow the player to harvest pelt or meat.
    7. Harvest a resource will regenerate the player's health and magic stat by 10% of the maximum value of their health and magic stat. Regeneration will only occur if health and magic are below their maximum values.
> 7. Ensure there are buttons the player can use to mine ore, scavenege area, cut trees, and hunt wildlife.
>    1. Test the inventory system by ensuring that wood, stone, metal, and other resources are correctly added and stored.
>    2. Verify that clicking the "mine ore" button results in the acquisition of stone or metal, and clicking "scavenge area" yields magical essence, spleenwort, or tongue fern.
>    3. Check if selecting "cut wood" or "hunt wildlife" from the forest panel adds wood or pelt/meat to the player's inventory, respectively.
>    4. Test health and magic regeneration by harvesting a resource and ensuring that health and magic stats increase by 10% if below their maximum values.
>    5. Verify that health and magic regeneration does not occur if both stats are already at their maximum values.
>    6. Ensure buttons for actions (mining ore, scavenging area, cutting wood, hunting wildlife) are functional and respond appropriately to user input.
>    7. Test edge cases such as when resources are depleted or the player's health/magic is already at maximum to confirm robustness.

  
8. The player will be able to buy a homestead using the "but homestead" button located in the homestead screen. The homestead screen can be accessed from the world screen.
>   8. Verify the presence and functionality of the "buy homestead" button on the homestead screen, accessible from the world screen.

9. There will be a tavern, library, and bazaar screens that can be accessed using the buttons located on the town screen menu.
    1. The tavern screen will house the implementation for chatting with townsfolk. There will be various prompts for different NPCs.
        1. Prompts interate around a predetermined set of prompts whenever the player presses the "Talk to townsfolk" button. Prompts will loop.
        2. Each prompt will have an accompanying voice-over sound byte narrating the prompt text.
    2. The bazaar screen will allow the player to buy and sell items from their inventory. The bazaar will also allow the player to look into their
    inventory by clicking on the inventory button.
    3. The library screen will have the implementation for the "book of monsters" screen, which will contain the descriptions for monsters in the game.
       Accessing the page of a particular momnster will play their respective round effect and display the image correlated with that monster.
> 9. Ensure that buttons for accessing the tavern, library, and bazaar screens are present on the town screen menu.
>     1. Test the functionality of the tavern screen, confirming the ability to chat with townsfolk and encounter various prompts, each accompanied by a corresponding voice-over sound byte.
>     2. Verify that the bazaar screen enables the player to buy and sell items from their inventory, as well as view their inventory by clicking on the inventory button.
>     3. Test the library screen's implementation of the "book of monsters" feature, allowing players to access monster descriptions, hear round effects, and view associated images for each monster.
  
10. There will be a rolling credits screen that can be accessed from the settings panel that will credit the developers, professor, art, images, music, and sound effects used.
> 10. Confirm the existence and accessibility of a rolling credits screen within the settings panel, accurately crediting developers, professors, artists, images, music, and sound effects used in the game.

11. Most if not all panels will have an accompanying background image and music.
> 11. Validate that most, if not all, panels in the game incorporate accompanying background images and music to enhance the overall atmosphere and immersion for the player.
    
12. Most if not all of the buttons and will have an accompanying sound effect.
> 12. Ensure that most, if not all, buttons and interactive elements in the game are accompanied by corresponding sound effects to provide auditory feedback and enhance the interactive experience for the player.

13. There shall be a status bar button and panel present in the forest and mineshaft screens.
    1. The player will be able to view the status bar by clicking on the status bar button.
    2. The status bar panel will show the values of health, magic, and gold the player currently has.
> 13. Verify the presence of a status bar button and panel on both the forest and mineshaft screens.
>     1. Test the functionality of the status bar button to ensure it toggles the visibility of the status bar panel when clicked.
>     2. Confirm that the status bar panel accurately displays the player's current values for health, magic, and gold.
>     3. Ensure the status bar updates dynamically to reflect changes in the player's health, magic, and gold values during gameplay.
>     4. Evaluate the usability and visibility of the status bar button and panel within the forest and mineshaft screens.

14. There will be a secret merhcant screen that can be accessed from the bazaar screen.
    1. The button that takes the player to the secret merchant panel will appear every fourth bazaar visit.
    2. The secret merchant will sell a secret item.
> 14. Confirm the presence and accessibility of a secret merchant screen within the bazaar screen, with a button appearing every fourth visit, offering a secret item for sale.
   
15. Before the game loads up there will be a disclaimer message.
> 15. Verify the presence of a disclaimer message displayed before the game loads up, providing necessary information or warnings to the player.


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
- Adrien Abbey
- Luke Davidson
- 
- 
