# Requirements Speficiations

1. The game will be operated via a graphical interface and buttons that will be on screen.
2. The user shall be able to choose between loading a save, or creating a new character.
    1. The user shall be able to save their character.
    2. A character shall consist of a name, muscle stat, brain stat, and heart stat.
3. The user will start at a world map screen upon picking a character, loaded or fresh.
    1. The map screen will have a selection for a town shop and a selection of dungeons of increasing difficulty.
4. The town shop will sell potions to heal the adventurer.  Towns are entered from the map screen.
    1. The player's potion belt can be upgraded in size.  This is the primary method of spending gold.
    2. The player can buy potions to refill their belt.
    3. The player can also use a potion to restore their health here.
5. The dungeon will be where the primary combat loop takes place.  Dungeons are selected from the map screen.
    1. Dungeons can only be entered when the player's health is above zero.
    2. The player can toggle between offensive and healing magic with a button.
        1. Offensive magic will randomly cast attack spells during combat.  This helps combat resolve faster.
        2. healing magic will randomly cast healing spells during combat.  This helps the player fight longer.
        3. Casting this way is based on a random dice roll, influenced by their brain statistic and their remaining mana points.
    3. Combat hits and damage will be affected by dice rolls.
        1. The player and the enemy monster take turns attacking each other.
    4. The player can click a button to use a potion from their belt to heal.
    5. If the players HP hits 0, they can't continue combat until either a certain amount of time, or until healed with a potion.
        1. This returns the player to the map screen, disabling dungeons.
    6. If the monster hits 0 HP, the player is rewarded with gold, and a chance at equipment upgrades.
        1. After defeating a monster, a new monster replaces it.
    7. The player can choose to withdraw from the dungeon at any time.
        1. However, any current combat must resolve first.  
        2. After the user clicks the withdraw button, the current combat will resolve normally.
        3. Withdrawing from a dungeon without dying will return the player to the map screen.
6. The player will have gear consisting of a weapon, armor and head-piece.
    1. This gear will be obtained by defeating enemies in the dungeon.
        1. The chance at an upgrade is based on the current dungeon level, current gear levels, and the disparity between those levels.
        2. Each combat will upgrade a single piece of equipment, at most.  Lower level gear has a higher chance to upgrade than those with higher levels.

## Minimal Viable Product [OLD]

Work in progress.  Based on whiteboard notes, Jan. 16, 2024

## UI

- Multi-window UI.  Bring it back into style.
- Permanent disclaimer window, always on top, disable minimize/close.
    - People should know what they've gotten themselves into, and the risks involved.

## Combat

- Dice roll for hit
- Back and forth
- Gear adds bonus for Combat
- If `HP=0`, you have to rest.
- Return button
- Group combat.
    - New window for *each* enemy being fought.  Fighting 5 enemies means 5 seperate windows.
    - Rare encounters with *lots* of enemies.  Really push modern hardware to the limits!

## Gear

- Weapon
- Armor
- Special / Accessory
- Find gear throughout adventure

## Stats

- Base stats (names of stats likely to change):
    - Muscle
    - Brain
    - Humors?
- Character status
    - Boolean `isBattleReady`
    - Timer to track how long before combat ready after injury
- Currency system

## World / Map

- Single dungeon to start
    - Implement more as time permits
- Dungeons require set number of battles before facing the boss.
- Shop / Town
