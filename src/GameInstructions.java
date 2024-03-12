import java.awt.*;
import javax.swing.*;

class GameInstructions extends JPanel{
    GameInstructions(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton back = new JButton("<- Back");
        JLabel info = new JLabel();
        add(back);
        add(info);
        back.setAlignmentX(LEFT_ALIGNMENT);
        info.setText("Hello Traveler! Welcome to B.A.D Idle Game. To start your journey you will need to create a new characrer using the New Game button. From there you will  ");



        back.addActionListener(e ->{
            Driver.changePanel("start");
        });
    }
}