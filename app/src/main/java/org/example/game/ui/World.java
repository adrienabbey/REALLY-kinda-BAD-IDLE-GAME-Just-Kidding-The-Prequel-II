import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Random;

public class World extends JPanel{

    //========================================================
    // Fields
    //========================================================

    // Components and variables used in world screen
    PlayerCharacter player; // declare player object reference variable
    private JLabel dungeon_error_message; // Declare JLabel
    private boolean timerRunning = false; // flag for dungeon error message cooldown timer. 
    private JButton quit, town, mine, wood, home, dungeon, leave;
    final private Color customColorBeige = new Color(253, 236, 166);
    final private Color customColorBrown = new Color(102, 72, 54);
    final private Border buttonBorder = BorderFactory.createLineBorder(customColorBeige, 1);

    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 76;
    private Random random = new Random(System.currentTimeMillis()); // for the code gremlin's randomized rearranging

    private int quitWidth, quitHeight, leaveWidth, leaveHeight , townWidth, townHeight, mineWidth, mineHeight, woodWidth, woodHeight, homeWidth, homeHeight, dungeonWidth, dungeonHeight;

     //While Rectangle primarily represents a rectangle's position (x and y coordinates) and size (width and height), it can be adapted to store xpos, ypos, width, and height by extending its functionality or using it directly
    Rectangle quitBounds, townBounds, mineBounds, woodBounds, homeBounds, dungeonBounds, leaveBounds, dunErrMesBounds;


    //========================================================
    // Constructor
    //========================================================
    /**
     * This function hosts the world map screen with buttons to go to town or dungeon
     * @param player The player character object
     * @throws IOException
     */
    public World(){
        this.setLayout(null);
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        quit = new JButton("Quit");
        buttons.add(quit);
        town = new JButton("⛪ Town");
        buttons.add(town);
        mine = new JButton("🔥 Mineshaft");
        buttons.add(mine);
        wood = new JButton("🌲 Forest");
        buttons.add(wood);
        home = new JButton("🏠 Homestead");
        buttons.add(home);
        dungeon = new JButton("🏰 Dungeon");
        buttons.add(dungeon);
        leave = new JButton("☰ Main Menu");
        buttons.add(leave);

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setBorder(buttonBorder);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));

            // formatting quit button
            if (i == 0) {
                buttons.get(0).setBackground(new Color(178, 34, 34));
                buttons.get(0).setForeground(Color.WHITE);
                buttons.get(i).setBorder(buttonBorder);
            }
        }

        // Format dungeon error message label
        dungeon_error_message = new JLabel(); // initialize JLabel
        dungeon_error_message.setForeground(Color.red); // Set error message font color to red
        dungeon_error_message.setBackground(Color.BLACK); // Set error message background color to black
        dungeon_error_message.setFont(new Font("Serif", Font.BOLD, 24)); // Set font

        //=======================================================
        //
        //Relatively scaling and sizing world components
        //
        // 
        quitWidth = quit.getPreferredSize().width * 14 / 10;
        quitHeight = quit.getPreferredSize().height * 12 / 10;;
        leaveWidth = leave.getPreferredSize().width * 14 / 10;;
        leaveHeight = leave.getPreferredSize().height * 12 / 10;;
        townWidth = town.getPreferredSize().width * 14 / 10;;
        townHeight = town.getPreferredSize().height * 12 / 10;;
        mineWidth = mine.getPreferredSize().width * 14 / 10;;
        mineHeight = mine.getPreferredSize().height * 12 / 10;;
        woodWidth = wood.getPreferredSize().width * 14 / 10;;
        woodHeight = wood.getPreferredSize().height * 12 / 10;;
        homeWidth = home.getPreferredSize().width * 14 / 10;;
        homeHeight = home.getPreferredSize().height * 12 / 10;;
        dungeonWidth = dungeon.getPreferredSize().width * 14 / 10;;
        dungeonHeight = dungeon.getPreferredSize().height * 12 / 10;;

        townBounds = new Rectangle(width * 7 / 11, height * 4 / 9, townWidth, townHeight);
        mineBounds = new Rectangle(width * 3 / 10, height * 4 / 10, mineWidth, mineHeight);
        woodBounds = new Rectangle(width * 3 / 9, height * 7 / 10, woodWidth, woodHeight);
        homeBounds = new Rectangle(width * 6 / 11, height * 8 / 10, homeWidth, homeHeight);
        dungeonBounds = new Rectangle(width * 5 / 10, height * 2 / 10, dungeonWidth, dungeonHeight);
        leaveBounds = new Rectangle(leaveHeight, leaveHeight, leaveWidth, leaveHeight);
        quitBounds = new Rectangle((int) leaveBounds.getLocation().getX() + leaveWidth, (int)leaveBounds.getLocation().getY(), quitWidth, quitHeight);
        dunErrMesBounds = new Rectangle(width * 2 / 10, height / 2, width * 6 / 10, height / 20); //centered in screen

        // quitBounds = new Rectangle(width - (width * 1 / 15), height - (height * 1 / 15), quitWidth, quitHeight);
        
        quit.setBounds(quitBounds); //bottom-right corner
        leave.setBounds(leaveBounds); //top-left corner
        town.setBounds(townBounds);
        mine.setBounds(mineBounds);
        wood.setBounds(woodBounds);
        home.setBounds(homeBounds);
        dungeon.setBounds(dungeonBounds);
        dungeon_error_message.setBounds(dunErrMesBounds); //centered and middle of screen

        add(quit);
        add(leave);
        add(town);
        add(mine);
        add(wood);
        add(home);
        add(dungeon);
        add(dungeon_error_message);
        //================================================================


    //========================================================
    //Action Listeners
    //========================================================

        // Quit button exits the game
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Town button takes you to the town
        town.addActionListener(e -> {
            try {
                if(Driver.gremlinOn){
                    rearrangeButtons();
                }
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("town");
                MusicPlayer.playMusic("assets/Music/town-bgm.wav");
                SFX.playSound("assets/SFX/town-ambient-sfx2.wav", true);                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Dungeon button takes you to the dungeon
        dungeon.addActionListener(e -> {
            try {
                if(Driver.gremlinOn){
                    rearrangeButtons();
                }
                player = Driver.getPlayer();
                if (player.getHealth() > 0) {  // can't enter dungeon if low health
                    Driver.dungeonUpdate();
                    SFX.playSound("assets/SFX/interface1.wav");
                    Driver.changePanel("dungeon");
                    MusicPlayer.playMusic("assets/Music/Fantasy Medieval Music - Song of the North.wav");
                    dungeon_error_message.setText(""); // Clear error message
                    dungeon_error_message.setOpaque(false);
                } else {
                    dungeon_error_message.setOpaque(isOpaque());
                    dungeon_error_message.setText(" Health must exceed 0 to enter dungeon. Heal yourself with potions or regenerate while gathering resources.");

                    if (!timerRunning) { // if timer is not running, set new timer
                        // Clear error message after 2 seconds
                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dungeon_error_message.setText(""); // Clear error message
                                dungeon_error_message.setOpaque(false);
                                timerRunning = false;
                            }
                        });
                        timer.setRepeats(false); // Execute only once
                        timerRunning = true;
                        timer.start();
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Home button takes you to the Home screen
        home.addActionListener(e -> {
            try {
                if(Driver.gremlinOn){
                    rearrangeButtons();
                }
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("home");
                MusicPlayer.playMusic("assets/Music/homestead-bgm.wav");
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // WoodCutting button takes you to the Woodcutting screen
        wood.addActionListener(e -> {
            try {
                if(Driver.gremlinOn){
                    rearrangeButtons();
                }
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("forest");
                MusicPlayer.playMusic("assets/Music/Lamento di Tristano [Medieval Song]-lowered.wav");
                SFX.playSound("assets/Music/ambient-farmland-sounds.wav", true);
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Mining button takes you to the mining screen
        mine.addActionListener(e -> {
            try {
                if(Driver.gremlinOn){
                    rearrangeButtons();
                }
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("mineshaft");
                MusicPlayer.playMusic("assets/Music/Lamento di Tristano [Medieval Song]-lowered.wav");
                SFX.playSound("assets/SFX/mineshaft-ambience2.wav", true);
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Leave button takes you to the start screen
        leave.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.removeCharScreen();
                Driver.changePanel("start");              
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Rearrenges world map buttons randomly.
     * One of the many rampant bugs of the code gremlin.
     * 
     * Requires Gremlin to first be unleashed in the settings menu. 
     */
    private void rearrangeButtons() {
        int[] ranNums = new int[12];
        // Populate the array with random numbers
        for (int i = 0; i < ranNums.length; i++){
            ranNums[i] = random.nextInt(10); // Generates a random number between 0 and 9
        }
        
        //setbounds for each button with randomized parameters
        quit.setBounds(width - quitWidth - (width * ranNums[0] / 30), height - quitHeight - (height * ranNums[1] / 30), quitWidth, quitHeight);
        leave.setBounds(2 * leaveHeight, 2 * leaveHeight, leaveWidth, leaveHeight);
        town.setBounds(width * ranNums[2] / 11, height * ranNums[3] / 9, townWidth, townHeight);
        mine.setBounds(width * ranNums[4] / 10, height * ranNums[5] / 10, mineWidth, mineHeight);
        wood.setBounds(width * ranNums[6] / 9, height * ranNums[7] / 10, woodWidth, woodHeight);
        home.setBounds(width * ranNums[8] / 11, height * ranNums[9] / 10, homeWidth, homeHeight);
        dungeon.setBounds(width * ranNums[10] / 10, height * ranNums[11] / 10, dungeonWidth, dungeonHeight);
        dungeon_error_message.setBounds(width * 2 / 10, height / 2, width * 6 / 10, height / 20);
    }

    /**
     * Arrenges world map buttons to its proper original placemnet.
     * Someone finally hunted down that gremlin.
     * 
     * Requires gremlin to be leashed back in throgh the settings menu. 
     */
    public void resetButtonArrangment() {
        quit.setBounds(quitBounds); //bottom-right corner
        leave.setBounds(leaveBounds); //top-left corner
        town.setBounds(townBounds);
        mine.setBounds(mineBounds);
        wood.setBounds(woodBounds);
        home.setBounds(homeBounds);
        dungeon.setBounds(dungeonBounds);
        dungeon_error_message.setBounds(dunErrMesBounds); //centered and middle of screen
    }
}




