import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.Box; // Import the missing Box class

class LoadScreen extends JPanel{
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
                Driver.changePanel("start");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // this.dispose();
        });
        select.addActionListener(e -> {
            try {
                //TODO - Add load functionality
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // this.dispose();
        });
    }
}