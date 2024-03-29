# Player Character

- Every player creates a character, for which progress is saved.

## Class

- Class-based or Classless?
  - Class Idea:
    - Choose a race (determines base stats and stat growth)
    - Have race select how fast stats grow rather than just a starting bonus, it can just be a modifier applied during exp gain.
    - Choose a weapon type (influenced by race selection)
    - Class is automatically determined (labeled) by this combination.
    - Maybe do classless and just be you can do anything, but char total combat level chooses enemy level, so spreading too thin will kill your char?
    - Ex: Human with Sword + Shield = Fighter.  Elf + Bow = Ranger.
    - Chosen at creation, or unlocked at a certain level?
    - Locked after chosen, or changable?

## Stats

- Character levels, or leveless design?
  - Determines where emphasis of game design is.
    - Leveless leaves all "progression" to be determined by stats and equipment.
    - Leveled allows for simpler balancing
  - Primary stats?
    - Strength (damage multiplier? stamina modifier)
    - Agility (atk speed, dodge chance)
    - Dexterity (chance to hit, atk speed secondary modifier)
    - Constitution ( Hitpoints and stamina total and regen)
    - Willpower (health modifier and magic regen)
    - Intelligence (Magic amount and power)

## Skills

- Talent tree?
  - More complicated, but more "normal"
  - List of skills, each with a skill level?
    - Ex: Sword, Block, Dodge, Endurance, etc.
    - Skill levels could unlock "actions"
    - Lends more favorably with classless and/or levelless systems
    - Opens possibility of possibly doing "everything", giving a long-term goal for players
    - Magic system, or typical low-fantasy stuff?

## Inventory

- Do characters have equipment?
  - What equipment slots, if any?
    - I would say start with just "armor" slot, and "weapon" slot, and then maybe add it to be chest, off-hand, etc
  - Do characters have an inventory system for unequipped items?
    -Brandon votes yes inventory, but not part of Min Val Product
  - What does equipment provide?  Simple bonuses to stats?
    -Brandon votes again start with just simple adding dmg reduction, then adding bonuses to stats or skills, or even special effects.
- This is actually where I think it will be easiest to add lots late game, making more equipment is just adding its info

### A very basic equipment system idea

- Equipment provides a simple, flat bonus based on equipment type:
  - Weapons determine base damage
  - Armor determines damage reduction
  - Equipment automatically upgrades as "loot" is found or crafted.
  - This leaves open the opportunity to implement a proper inventory system
    - Brandon ( I love this as a MVP point)

## Quests

- Very basic introductory quests to direct players in how to play the game.
  - Basic long-term goals with rewards (giving directed progression)
  - This  open the door for more interesting quest implementation later, which could introduce lore and stories.
    - These seem more like when we have MVP out, maybe when we near MVP completion we should compile a list of things we wanna add, and then we can vote on an order, or maybe we can rotate taking turns picking a feature to add

## Activities

- A system where the players decide what their character does over a period of time.
  - Lets players direct character progression.  Examples:
    - Explore (find new activities or locations)
    - Delve (enter a dungeon for combat and loot)
    - Train (increase certain skills or stats much faster)
    - Craft (create better equipment)
    - Maybe different "gathering" activities like mining, farming etc
   
## Economy 
- Money System that buys items/gear. 
- Shop/Merchant 
- Town area 

## Implementation Ideas

- Minimize to system tray (optionally).
- Event log.  Be able to see what happened while minimized.
- Events still happen in real-time when running.
- It's an idle game.  It plays itself (sorta).

## Ideas to be expanded
* The player's idle progress could be calculated by tracking their starting time, active Jobs, and end time. When they start again we track the time and calculate what would have been gained.
* Exploration of the human condition.
  * The player is *the disclaimer window*.  This isn't obvious at first.
  * Start with no equipment.  Discover later that you're like characters from the Wizard of Oz, looking to find intellect, courage, and such.  And the best way to do that is to gather the best organs from slain enemies and equip them.

 ## STRETCH Goals
* 2nd Mode(Interactive)
* Party of character
* Server, Web Interface
* Friends, Parties
* Live dungeon feature
* Thesaurus feature
* Presentation mode
* Downgrades
  * Debt Traps
* Scripted Events
* Art Mode
