import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.Box;

class LoadScreen extends JPanel{
    
            @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/World8.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

    public LoadScreen(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton back = new JButton("Back");
        JButton select = new JButton("Select");

        add(Box.createVerticalGlue());
        add(select);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(back);
        add(Box.createVerticalGlue());

        select.setAlignmentX(CENTER_ALIGNMENT);
        back.setAlignmentX(CENTER_ALIGNMENT);

        back.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("start");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        select.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                //TODO - Add load functionality
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}