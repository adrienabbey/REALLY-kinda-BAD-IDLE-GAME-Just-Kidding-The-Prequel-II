import java.awt.*;
import javax.swing.*;

class GameInstructions extends JPanel{
    GameInstructions(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton back = new JButton("<- Back");
        JLabel info = new JLabel();
        add(Box.createVerticalGlue());
        add(back);
        add(Box.createRigidArea(new Dimension(0, 10)));
        back.setAlignmentX(LEFT_ALIGNMENT);
        info.setText("Hello Traveler!");



        back.addActionListener(e ->{
            Driver.changePanel("start");
        });



    }
}