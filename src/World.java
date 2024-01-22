import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class World extends JFrame{
    public World(PlayerCharacter player) throws IOException{
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        BufferedImage picture = ImageIO.read(new File("assets/images/World.png"));
        
        JPanel world = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(picture, 0, 0, getWidth(), getHeight(), this);
            }
        };
        world.setLayout(new BoxLayout(world, BoxLayout.X_AXIS));
        JButton quit = new JButton("Quit");
        JButton town = new JButton("Town");
        JButton dungeon = new JButton("Dungeon");

        world.add(Box.createVerticalGlue());
        world.add(quit);
        world.add(Box.createRigidArea(new Dimension(0, 20)));
        world.add(town);
        world.add(Box.createRigidArea(new Dimension(0, 20)));
        world.add(dungeon);
        world.add(Box.createVerticalGlue());

        quit.setAlignmentX(BOTTOM_ALIGNMENT);
        town.setAlignmentX(BOTTOM_ALIGNMENT);
        dungeon.setAlignmentX(BOTTOM_ALIGNMENT);

        quit.addActionListener(e -> {
            System.exit(0);
        });
        town.addActionListener(e -> {
            try {
                new Town(player);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });
        dungeon.addActionListener(e -> {
            try {
                new Dungeon(player);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });

        this.getContentPane().add(world);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}