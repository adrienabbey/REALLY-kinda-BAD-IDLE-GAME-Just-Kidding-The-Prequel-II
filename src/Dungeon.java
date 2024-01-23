import javax.swing.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

class Dungeon extends JFrame {
    public Dungeon(PlayerCharacter player) {
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