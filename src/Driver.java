import javax.swing.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.CardLayout;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.GridLayout;

class Driver extends JFrame{

    // These are the classwide variables, these need to be class wide
    // because they are used in multiple functions and need to be accessed
    private static JPanel driverPanel = new JPanel();
    private static CardLayout cardLayout = new CardLayout();
    private static PlayerCharacter player;
    private static JPanel world = new JPanel();
    private static JPanel dungeon = new JPanel();
    private static Dungeon combat = new Dungeon();
    private static World map = new World(){ // This code puts the world map image as the background to the panel
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/World.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws Exception{
        new Driver();
    }

    /**
     * This is the constructor for the driver class,
     * it sets up the JFrame and adds the panels to the cardLayout
     * This function handles all other screens to reduce code duplication
     * and increase readability/organization of the code.
     * @throws Exception
     */
    public Driver() throws Exception{
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        // This panel will be for any screens before a character has been loaded, which will be a solo screen at a time
        StartScreen start = new StartScreen();
        LoadScreen load = new LoadScreen();
        CharacterCreation cc = new CharacterCreation();
        // JPanel charPanel = new CharacterScreen();
        //JPanel dice = new BigDiceEnergy();
        //JPanel inventory = new Inventory();
        // JPanel world = new JPanel();
        Town town = new Town();
        // JPanel dungeon = new JPanel();
        // Dungeon combat = new Dungeon();

        world.setLayout(new GridLayout(2, 2));
        dungeon.setLayout(new GridLayout(2, 2));


        driverPanel.setLayout(cardLayout);
        driverPanel.add(start, "start");
        driverPanel.add(load, "load");
        driverPanel.add(cc, "cc");
        // driverPanel.add(charPanel, "charPanel");
        //driverPanel.add(dice, "dice");
        //driverPanel.add(inventory, "inventory");
        driverPanel.add(world, "world");
        driverPanel.add(town, "town");
        driverPanel.add(dungeon, "dungeon");

        cardLayout.show(driverPanel, "start");

        getContentPane().add(driverPanel);
        setLocationRelativeTo(null);
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
    public static void addCharScreen(){
        CharacterScreen charScreen = new CharacterScreen();
        world.add(charScreen);
        world.add(map);
        dungeon.add(charScreen);
        dungeon.add(combat);
        // driverPanel.add(new CharacterScreen(), "charScreen");
    }


    /**
     * This function is to change what display is showing, it relies on the panel being added to the driverPanel
     * @param panel the name of the panel to be shown
     */
    public static void changePanel(String panel){   cardLayout.show(driverPanel, panel);    }

    // Getters and Setters
    public static PlayerCharacter getPlayer(){ return player;  }
    public static void setPlayer(PlayerCharacter player){   Driver.player = player; }
}