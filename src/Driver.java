import javax.swing.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.GridLayout;

class Driver extends JFrame {

    // These are the classwide variables, these need to be class wide
    // because they are used in multiple functions and need to be accessed
    private static JPanel driverPanel = new JPanel();
    private static CardLayout cardLayout = new CardLayout();
    private static PlayerCharacter player;
    private static JPanel world = new JPanel();
    private static JPanel dungeon = new JPanel();
    private static Dungeon combat = new Dungeon();
    private static World map = new World() { // This code puts the world map image as the background to the panel
   
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/World7.jpg")), 0, 0, getWidth(), getHeight(), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws Exception {
        new Driver();
    }

    /**
     * This is the constructor for the driver class,
     * it sets up the JFrame and adds the panels to the cardLayout
     * This function handles all other screens to reduce code duplication
     * and increase readability/organization of the code.
     * 
     * @throws Exception
     */
    public Driver() throws Exception {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        MusicPlayer.playMusic("assets/Music/Brilliant1.wav");
        MusicPlayer.setVolume(5.0f);

        // This panel will be for any screens before a character has been loaded, which
        // will be a solo screen at a time
        StartScreen start = new StartScreen();
        LoadScreen load = new LoadScreen();
        CharacterCreation cc = new CharacterCreation();
        GameInstructions instructions = new GameInstructions();
        Settings settings = new Settings();
        Credits credits = new Credits();
        Home home = new Home();
        Inventory inventory = new Inventory();
        Shop shop = new Shop(inventory);
        WoodCutting woodCutting = new WoodCutting(inventory);
        Mining mining = new Mining(inventory);
        Tavern tavern = new Tavern();
        Library library = new Library();
        // JPanel charPanel = new CharacterScreen();
        // JPanel dice = new Dice();
        // JPanel inventory = new Inventory();
        // JPanel world = new JPanel();
        Town town = new Town();
        // JPanel dungeon = new JPanel();
        // Dungeon combat = new Dungeon();

        world.setLayout(new GridLayout(1, 1));
        dungeon.setLayout(new GridLayout(1, 1));

        driverPanel.setLayout(cardLayout);
        driverPanel.add(settings, "settings");
        driverPanel.add(shop, "shop");
        driverPanel.add(start, "start");
        driverPanel.add(load, "load");
        driverPanel.add(cc, "cc");
        driverPanel.add(instructions, "instructions");
        driverPanel.add(credits, "credits");
        driverPanel.add(home, "home");
        driverPanel.add(woodCutting, "wood");
        driverPanel.add(mining, "mine");
        driverPanel.add(tavern, "tavern");
        driverPanel.add(library, "library");
        driverPanel.add(inventory, "inventory");
        // driverPanel.add(charPanel, "charPanel");
        // driverPanel.add(dice, "dice");
        // driverPanel.add(inventory, "inventory");
        driverPanel.add(world, "world");
        driverPanel.add(town, "town");
        driverPanel.add(dungeon, "dungeon");

        cardLayout.show(driverPanel, "start");

        getContentPane().add(driverPanel);
        setLocationRelativeTo(null);
        ImageIcon iconImage = new ImageIcon("assets/images/windowIcon.png");
        this.setIconImage(iconImage.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }

    /**
     * This function is necessary because charScreen causes an error
     * that makes the program crash before it even launches.
     * This function adds the character screen to the world and dungeon panels.
     */
    public static void addCharScreen() {
        CharacterScreen charScreen = new CharacterScreen();
        world.add(charScreen);
        world.add(map);
        dungeon.add(charScreen);
        dungeon.add(combat);
        
        // driverPanel.add(new CharacterScreen(), "charScreen");
    }

    /**
     * This function is to change what display is showing, it relies on the panel
     * being added to the driverPanel
     * 
     * @param panel the name of the panel to be shown
     */
    public static void changePanel(String panel) {
        cardLayout.show(driverPanel, panel);
    }

    // Getters and Setters
    public static PlayerCharacter getPlayer() {
        return player;
    }

    public static void setPlayer(PlayerCharacter player) {
        Driver.player = player;
    }

    /* Save and Load functions for the Player object */

    /**
     * Saves the specified player object to a file.
     * 
     * @param playerCharacter The player object to be saved.
     * @return Returns true if save is successful, false if not.
     */
    public static boolean savePlayer(PlayerCharacter playerCharacter) {
        // Syntax referenced from:
        // https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk
        try {
            // TODO: Enable loading and saving more than a single player.
            // TODO: Enable saving to a different filename/directory location.
            FileOutputStream saveFile = new FileOutputStream("saveFile.sav");
            ObjectOutputStream outputStream = new ObjectOutputStream(saveFile);
            outputStream.writeObject(playerCharacter);
            outputStream.close();
            saveFile.close();
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // TODO: There's a good chance a save file may not yet exist.
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Loads a player character from a save file.
     * 
     * @return Returns the loaded player character if successful. Will return
     *         NULL objects if this process fails for any reason.
     */
    public static PlayerCharacter loadPlayer() {
        // Syntax referenced from:
        // https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk

        // TODO: Enable loading from more than a single save file.

        // Create a player object to return:
        PlayerCharacter loadedCharacter = null;
        try {

            FileInputStream loadFile = new FileInputStream("saveFile.sav");
            ObjectInputStream inputStream = new ObjectInputStream(loadFile);
            loadedCharacter = (PlayerCharacter) inputStream.readObject();
            inputStream.close();
            loadFile.close();
            return loadedCharacter;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // TODO: Gracefully handle attempting to load a non-existant file.
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}