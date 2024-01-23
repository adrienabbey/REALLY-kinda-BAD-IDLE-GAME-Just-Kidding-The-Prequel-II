import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GameInstructions extends JFrame {
    //frame and Panel 
    public GameInstructions(){
        JFrame frame = new JFrame("Instructions");
        JPanel root = new JPanel();
        Image iconImage = new ImageIcon("windowIcon").getImage();








        frame.add(root);
        frame.setIconImage(iconImage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(670, 300);
        frame.setVisible(true);
    }
}