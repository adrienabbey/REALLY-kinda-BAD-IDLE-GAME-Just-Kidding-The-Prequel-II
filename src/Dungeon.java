import javax.swing.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

class Dungeon extends JFrame {
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */
    public Dungeon() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        JPanel dungeon = new JPanel();

        this.getContentPane().add(dungeon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}