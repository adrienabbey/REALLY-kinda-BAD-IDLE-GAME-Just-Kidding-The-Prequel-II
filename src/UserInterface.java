import javax.swing.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

class UserInterface extends JFrame{
    public UserInterface(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}