import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class World extends JPanel{
    PlayerCharacter player; // declare player object reference variable
    
    private JLabel dungeon_error_message; // Declare JLabel
    private boolean timerRunning = false; // flag for dungeon error message cooldown timer. 
    /**
     * This function hosts the world map screen with buttons to go to town or dungeon
     * @param player The player character object
     * @throws IOException
     */
    public World(){
        this.setLayout(null);
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);

        JButton quit = new JButton("Quit");
        buttons.add(quit);
        JButton town = new JButton("Town");
        buttons.add(town);
        JButton mine = new JButton("Mineshaft");
        buttons.add(mine);
        JButton wood = new JButton("Forest");
        buttons.add(wood);
        JButton home = new JButton("Homestead");
        buttons.add(home);
        JButton dungeon = new JButton("Dungeon");
        buttons.add(dungeon);
        JButton leave = new JButton("Main Menu");
        buttons.add(leave);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // gets dimensions of user's screen 
        int centerOfScreen = (int) screenSize.getHeight() / 2 - 40; // get the position of the center of the user's screen using relative scaling

        // Set position and size for all buttons. Used relative scaling for positioning. 
        leave.setBounds((int) (screenSize.getWidth() * 0.03), centerOfScreen,175,80);
        add(leave);

        mine.setBounds((int) (screenSize.getWidth() * 0.17), centerOfScreen,150,80);
        add(mine);

        home.setBounds((int) (screenSize.getWidth() * 0.31), centerOfScreen,150,80);
        add(home);

        town.setBounds((int) (screenSize.getWidth() * 0.45) , centerOfScreen,150,80);
        add(town);

        wood.setBounds((int) (screenSize.getWidth() * 0.59), centerOfScreen,150,80);
        add(wood);
        
        dungeon.setBounds((int) (screenSize.getWidth() * 0.73), centerOfScreen,150,80);
        add(dungeon);
        
        quit.setBounds((int) (screenSize.getWidth() * 0.87), centerOfScreen,150,80);
        add(quit);

        // Format dungeon error message label
        dungeon_error_message = new JLabel(); // initialize JLabel
        dungeon_error_message.setForeground(Color.red); // Set error message font color to red
        dungeon_error_message.setBackground(Color.BLACK); // Set error message background color to black
        dungeon_error_message.setFont(new Font("Serif", Font.BOLD, 24)); // Set font
        dungeon_error_message.setBounds((int) (screenSize.getWidth() / 4.5), (int) (screenSize.getHeight() * 0.70), 1150, 100); // set position and size of dungeon error message label
        add(dungeon_error_message); // Add error message label to  panel


        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));

            // formatting quit button
            if (i == 0) {
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
            }
        }

        // Quit button exits the game
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Town button takes you to the town
        town.addActionListener(e -> {
            try {
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
                Driver.changePanel("start");              
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}

