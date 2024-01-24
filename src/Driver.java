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

    public static void main(String[] args) throws Exception{
        new Driver();
    }

    public Driver() throws Exception{
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        BufferedImage picture = ImageIO.read(new File("assets/images/World.png"));

        // This panel will be for any screens before a character has been loaded, which will be a solo screen at a time
        StartScreen start = new StartScreen();
        LoadScreen load = new LoadScreen();
        CharacterCreation cc = new CharacterCreation();
        //JPanel charPanel = new CharacterPanel();
        //JPanel dice = new BigDiceEnergy();
        //JPanel inventory = new Inventory();
        World world = new World(){ // This code puts the world map image as the background to the panel
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(picture, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Town town = new Town();
        //JPanel dungeon = new Dungeon();

        driverPanel.setLayout(cardLayout);
        driverPanel.add(start, "start");
        driverPanel.add(load, "load");
        driverPanel.add(cc, "cc");
        // driverPanel.add(charPanel, "charPanel");
        //driverPanel.add(dice, "dice");
        //driverPanel.add(inventory, "inventory");
        driverPanel.add(world, "world");
        driverPanel.add(town, "town");
        //driverPanel.add(dungeon, "dungeon");

        cardLayout.show(driverPanel, "start");

        getContentPane().add(driverPanel);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }

    public static void changePanel(String panel){
        cardLayout.show(driverPanel, panel);
    }

    public static void setPlayer(PlayerCharacter player){
        Driver.player = player;
    }
}