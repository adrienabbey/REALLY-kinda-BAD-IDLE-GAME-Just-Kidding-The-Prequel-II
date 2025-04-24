import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.CardLayout;
import java.awt.Color;
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


//========================================================
// Fields
//========================================================
    // These are the classwide variables, these need to be class wide
    // because they are used in multiple functions and need to be accessed
    private static JPanel driverPanel = new JPanel();
    private static CardLayout cardLayout = new CardLayout();
    public static PlayerCharacter player;
    private static JPanel world = new JPanel();
    private static JPanel dungeon = new JPanel();
    private static JPanel dungeonInfo = new JPanel();
    public static InventoryUI inventoryUI = new InventoryUI();
    private static Dungeon combat = new Dungeon();
    private static Combat logs;
    public static CharacterScreen charScreen;
    public static World map = new World() { // This code puts the world map image as the

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

    public static boolean gremlinOn = false;
    public static Color customColorBeige = new Color(253, 236, 166); 
    public static Border buttonBorder = BorderFactory.createLineBorder(customColorBeige, 1);

//========================================================
// Main Method
//========================================================
    public static void main(String[] args) throws Exception {
        new Driver();
    }

//========================================================
// Constructor
//========================================================
    /**
     * The constructor for the driver class
     * sets up the JFrame and adds the panels to the cardLayout
     * This function handles all other screens to reduce code duplication
     * and increase readability/organization of the code.
     * 
     * @throws Exception
     */
    public Driver() throws Exception {
        // Set the JFrame to be undecorated, removes title bar and borders
        setUndecorated(true);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
        // This panel will be for any screens before a character has been loaded, which
        // will be a solo screen at a time
        StartScreen start = new StartScreen();
        LoadScreen load = new LoadScreen();
        CharacterCreation cc = new CharacterCreation();
        GameInstructions instructions = new GameInstructions();
        Settings settings = new Settings();
        Credits credits = new Credits();
        Homestead home = new Homestead();
        Bazaar bazaar = new Bazaar();
        Forest forest = new Forest();
        Mineshaft mineshaft = new Mineshaft();
        Tavern tavern = new Tavern();
        Library library = new Library();
        // Farm farm = new Farm();
        // Craft craft = new Craft();
        Town town = new Town();

        world.setLayout(new GridLayout(1, 2));
        dungeon.setLayout(new GridLayout(1, 2));
        dungeonInfo.setLayout(new GridLayout(2, 1));
        driverPanel.setLayout(cardLayout);

        driverPanel.add(settings, "settings");
        driverPanel.add(bazaar, "bazaar");
        driverPanel.add(start, "start");
        driverPanel.add(load, "load");
        driverPanel.add(cc, "cc");
        driverPanel.add(instructions, "instructions");
        driverPanel.add(credits, "credits");
        driverPanel.add(home, "home");
        driverPanel.add(forest, "forest");
        driverPanel.add(mineshaft, "mineshaft");
        driverPanel.add(tavern, "tavern");
        driverPanel.add(library, "library");
        driverPanel.add(inventoryUI, "inventory");
        driverPanel.add(world, "world");
        driverPanel.add(town, "town");
        driverPanel.add(dungeon, "dungeon");

        cardLayout.show(driverPanel, "start");

        getContentPane().add(driverPanel);
        setLocationRelativeTo(null);
        ImageIcon iconImage = new ImageIcon("assets/images/windowIcon.png");
        this.setIconImage(iconImage.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }


//========================================================
// Methods
//========================================================
    /**
     * This function is necessary because charScreen causes an error
     * that makes the program crash before it even launches.
     * This function adds the character screen to the world and dungeon panels.
     * 
     * @throws InterruptedException
     */
    public static void addCharScreen() throws InterruptedException {
        charScreen = new CharacterScreen();
        world.add(charScreen);
        world.add(map);
        dungeonInfo.add(charScreen);
        logs = new Combat(player, Dungeon.getMonster());
        dungeonInfo.add(logs);
        dungeon.add(dungeonInfo);
        dungeon.add(combat);
    }

    public static void removeCharScreen() {
        world.remove(charScreen);
        world.remove(map);
        dungeonInfo.remove(charScreen);
        dungeonInfo.remove(logs);
        dungeon.remove(dungeonInfo);
        dungeon.remove(combat);
    }

    public static CharacterScreen getCharScreen() {
        return charScreen;
    }

    /**
     * This function is to change what display is showing, it relies on the panel
     * being added to the driverPanel
     * 
     * @param panel the name of the panel to be shown
     */
    public static void changePanel(String panel) {
        if(panel.equals("inventory")){
            inventoryUI.updateResourceLabels(); // whenever inventory is opened, update labels
        }else if(panel.equals("world")){
            if(!gremlinOn){
            map.resetButtonArrangment(); // if the gremlin isn't let out then reset buttons to otiginal layout whenever the world map screen is changed to. 
            }
        }
        cardLayout.show(driverPanel, panel);
    }

    // Getters and Setters
    public static PlayerCharacter getPlayer() {
        return player;
    }

    public static void setPlayer(PlayerCharacter player) {
        Driver.player = player;
    }

    /**
     * Saves the specified player object to a file.
     * 
     * @param playerCharacter The player object to be saved.
     * @param saveFilePath    The file path where the player data will be saved.
     * @return Returns true if save is successful, false if not.
     */
    public static boolean savePlayer(PlayerCharacter playerCharacter, String saveFilePath) {
        // Syntax referenced from:
        // https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk
        try {
            // TODO: Enable loading and saving more than a single player.
            // TODO: Enable saving to a different filename/directory location.
            // System.out.println("Attempting to save a file.");
            FileOutputStream saveFile = new FileOutputStream(saveFilePath);
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
     * @param saveFilePath The file path where the player data will be loaded from.
     * @return Returns the loaded player character if successful. Will return
     *         NULL objects if this process fails for any reason.
     */
    public static PlayerCharacter loadPlayer(String saveFilePath) {
        // Syntax referenced from:
        // https://reintech.io/blog/java-serialization-saving-restoring-objects-to-from-disk

        // TODO: Enable loading from more than a single save file.

        // Create a player object to return:
        try {
            // System.out.println("Attempting to load a file.");
            FileInputStream loadFile = new FileInputStream(saveFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(loadFile);
            PlayerCharacter loadedCharacter = (PlayerCharacter) inputStream.readObject();
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

    public static void dungeonUpdate() {
        charScreen.update();
        logs.update();
    }
}