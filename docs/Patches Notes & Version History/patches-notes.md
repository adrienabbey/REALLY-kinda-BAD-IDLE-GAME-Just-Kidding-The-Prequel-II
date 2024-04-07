### Patches and Bugfixes 3/26/24:

Fixed issue where gold would not be added to inventory after defeating a monster in the dungeon.
Added colors to buy, sell, and merchant UIs. Redid secret merchant panel.
Added a basic book of monsters feature.
Added googley eyes to startscreen goblin.
Added logic for homestead purchase, added buttons for farming and crafting (wont be expanded on until after course ends).
Added hunting and scavenging to forest and mineshaft respectively. Sets up the resources to be used later for cooking, crafting, alchemy, etc.
Optimized forest and mineshaft:
- made it so grant messages go away after sometime.
- made progress bar fill up more smoothly
- cant play multiple sfx at once when clicking resource gathering buttons anymore
- leaving the mineshaft or forest panel will stop all currently playing sfx.
- made progress bar reset is leaving mineshaft/forest panel
- made progress bar pause when stopping any process. will resume at same value if player doesnt leave panel.

---
### Patch / bugfix notes for 3/27/24 - 4/4/24
0. Added a bunch of comments summarizing the class does and how they work for a 
   majority of the classes in the source code. 

1. Changed background image for library and homestead.
 
2. Changed background music for town, credits, homestead, library, and secret merchant. 
   Edited background music using a combination of audacity and clipchamp, Used the 
   equalizer in audacity to fine-tune background music and create greater immersion.
   
   - Town music            -> Fantasy medieval village
   - Homestead music       -> Old Pine Village
   - secret merchant music -> Industrious Ferret
   - Library music         -> Wonder and Magic
   - Credits music         -> Song of the North
   
3. Added ambient noise to town, mineshaft, and forest. 

4. Added sound effect to hunting
   Used clipchamp to overlay different recording to create hunting-sfx.
   
5. Appended references to credits and licensing with all newly added music and sfx. 

6. Fixed issue with ambient sound effects getting interrupted or not playing. 
   Also made it so ambient sfx loop along with the backgrounnd music. 
   
7. Overloaded playSound() method in the SFX class so it can handle looped sound effects. 
   Added an inner wrapper class to tag clips with loop flag. 
   Modifed stopAllSounds() so that it properly releases resources
   for stopped clips. 
   
8. Renamed Shop -> Bazaar, Woodcutting -> Forest, Mining -> Mineshaft, Home -> Homestead.
   I think having the class names be the in-game names makes it more understandable when
   looking through the source code. 
 
9. Experimented with animated backgrounds by using the VLCJ framework to play a video from an embedded media player.
   Couldnt get working so put it on the backburner for now. Did get some semi-decent ai animations working though. 
   Animation attempts are located in the `videos` directory in assets. Used a combination of `Runway` to animate still images 
   and edit videos, `Ezgif` to reverse and speed up / slow down videos, and `Vmake` and `Tensorpix` to enhance video quality. 
 
10. Volume Optimization: Highered default volumes of SFX and Music. (altered the `currentVolume` 
    variable in each class to do this). Lowered dB of interface (button clicks), voice overs, and other sfx
    in audacity. Should be balanced decently well now.  

Known bugs:
1. Ambient sfx will not be stopped proerly every so often after leaving an area and will continue playing. This happens rarely (like every 5-10 panel changes) so it should not affecrt snapshot videos. 

---
### Patch / bugfix notes for 4/1/24:

* Added button that drops down character status bar in forest panel
* Status bar curently shows health, magic, and gold
* Harvesting resources regens 10% of max health and magic.
* Added relative scaling to buttons in World screen
* Added dungeon error message that pops up when the player tries to enter
the dungeon with low health. Explains how to regen health. Disappears after 2 seconds.

--- 
### Patch Notes / Bug Fixes for 4/7/24

1. Minor improvements:
   - Added entries to the credits
   - Added bgm w/ ambient bird noises in forest
   - Made certain resources appear more commonly
   - Added sfx to scavenging, tongue fern, spleenwort, pelt, meat, and magical essence. 
   - Added fullscreen (for my system), corrected relative positioning in forest panel to match fullscreen resolution.

2. Added saving/loading to UI and automatic saving:
    - added parameter for save/load methods in Driver to allow file paths to be specified.
    - Added buttons for 2 save states. 
    - Save file 1 is automatically saved by default whenever the user changes panels (enetering and leaving all panels from World, and enteirng and leaving the bazaar). 

Known bugs:
* player cant load their save file from a fresh new game, load button doesnt work. Probably something with panels not being initialized properly. 
* save button doesnt seem to work after creating a new game and going back to the main menu to save your game.

---
