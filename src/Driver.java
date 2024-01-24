import javax.swing.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;

class Driver extends JFrame{
    private static JPanel driverPanel = new JPanel();
    private static CardLayout cardLayout = new CardLayout();
    private static PlayerCharacter player;
    private static JPanel world = new JPanel();
    private static JPanel dungeon = new JPanel();
    private static Dungeon combat = new Dungeon();
    // private static BufferedImage picture = ImageIO.read(new File("assets/images/World.png"));
    private static World map = new World(){ // This code puts the world map image as the background to the panel
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/World.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // g.drawImage(ImageIO.read(new File("assets/images/World.png")), 0, 0, getWidth(), getHeight(), this);
        }
    };

    public static void main(String[] args) throws Exception{
        new Driver();
    }

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

    public static void addCharScreen(){
        CharacterScreen charScreen = new CharacterScreen();
        world.add(charScreen);
        world.add(map);
        dungeon.add(charScreen);
        dungeon.add(combat);
        driverPanel.add(new CharacterScreen(), "charScreen");
    }
    public static PlayerCharacter getPlayer(){ return player;  }
    public static void changePanel(String panel){   cardLayout.show(driverPanel, panel);    }
    public static void setPlayer(PlayerCharacter player){   Driver.player = player; }
}