import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class World extends JPanel{
    /**
     * This function hosts the world map screen with buttons to go to town or dungeon
     * @param player The player character object
     * @throws IOException
     */
    public World() throws IOException{
        // GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // GraphicsDevice device = env.getDefaultScreenDevice();
        // BufferedImage picture = ImageIO.read(new File("assets/images/World.png"));
        
        // // This is all the physical gui elements and their properties
        // JPanel world = new JPanel(){ // This code puts the world map image as the background to the panel
        //     @Override
        //     protected void paintComponent(Graphics g){
        //         super.paintComponent(g);
        //         g.drawImage(picture, 0, 0, getWidth(), getHeight(), this);
        //     }
        // };
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton quit = new JButton("Quit");
        JButton town = new JButton("Town");
        JButton dungeon = new JButton("Dungeon");

        // This section adds the components and controls layout
        add(Box.createVerticalGlue());
        add(quit);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(town);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(dungeon);
        add(Box.createVerticalGlue());

        quit.setAlignmentX(BOTTOM_ALIGNMENT);
        town.setAlignmentX(BOTTOM_ALIGNMENT);
        dungeon.setAlignmentX(BOTTOM_ALIGNMENT);

        // Quit button exits the game
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Town button takes you to the town
        town.addActionListener(e -> {
            try {
                new Town();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // this.dispose();
        });

        // Dungeon button takes you to the dungeon
        dungeon.addActionListener(e -> {
            try {
                new Dungeon();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // this.dispose();
        });

        // This section sets the properties of the JFrame, will soon be managed in driver likely
        // this.getContentPane().add(world);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(false);
        // this.setVisible(true);
        // device.setFullScreenWindow(this);
    }
}