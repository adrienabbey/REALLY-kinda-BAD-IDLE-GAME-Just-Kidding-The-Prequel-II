# Meeting Minutes
**Date: 7/8/24**

## Agenda
1. Move github repo to organization
2. Finalize save and load functionality.
3. Add character image, monster image, and healths bars for both in the dungeon screen. Flip through images of attacked-monsters when the enemy or player is attacked. 
4. Add a very basic craft/farm mechanic, can copy from bazaar implemenation. 
5. Finalize inventory layout. Can copy from bazaar code. Have three icons up top that shows the level of gear. 
6. Include progression through the dungeon, add floors/levels to dungeon and make it get progressively harder. Use all monsters. 
7. Either deploy project to browser using webswing, or create a website to host the game files users can download.
   1. If going the webswing route: figure out how to use webswing to deploy on broswer and make publically accessible.
   2. If going the downloadable game file route: create the BAD IDLE game website, deploy using something like netlify or on our own server, and add the link to the game file. 
   3. Incorporate CI/CD during development and deployment of project.

## Notes
1. Response to Item 4:
   * Since Swing is used to build desktop applications, it's unfeasible to mix and match the web-based UI with framweorks such as angular, since swing apps can't be natively deployed on the web. Swing apps can be deployed on a broswer indirectly through webswing, which is achieved through running the app on clusters and then virtually displaying the output through video. Other frameworks such as JavaFX can be mixed and matched with Swing, and possibly LWJGL as well though maybe not seamlessly. JavaFX provides a special JFXPanel class that allows you to embed JavaFX content into a Swing application. Similarly, SwingNode can be used to embed Swing content into a JavaFX application. JavaFX provides a more functionality than swing and allows video and more complex animation to be achieved. 

## Action Items
* Muhammed:
    * Work on item 2.
    * Work on item 3.
    * Work on item 4.
    * Work on item 5.
 
* Brandon:
    * Redesign character screen 
    * Work on item 6.
 
* Adrien:
    * Work on item 1.
 
* Luke:
   * Create attacked-monsters images

## Contributors
* Muhammed Abushamma
*
*
*
