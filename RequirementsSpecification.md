# Minimal Viable Product

- R1: The game will be operated via a graphical interface and buttons that will be on screen.
- R2: The user shall be able to choose between loading a save, or creating a new character.
- R2.1: The user shall be able to save their character.
- R2.2: A character shall consist of a name, muscle stat, brain stat, and heart stat.
- R3: The user will start at a world map screen upon picking a character, loaded or fresh.
- R3.1: The map screen will have a selection for a town shop and a dungeon.
- R4: The town shop will sell potions to heal the adventurer.
- R5: The dungeon will be where the primary combat loop takes place.
- R5.1: Combat hits and damage will be affected by dice rolls.
- R5.2: If the players HP hits 0, they can't continue combat until either a certain amount of time, or until healed with a potion.
- R5.3: Defeating enemies will drop gold.
- R6: The player will have gear consisting of a weapon, armor and head-piece.
- R6.1: This gear will be obtained by defeating enemies in the dungeon, and will be affected by dice rolls.

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
