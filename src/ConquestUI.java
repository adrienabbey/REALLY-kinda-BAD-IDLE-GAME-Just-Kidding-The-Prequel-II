/*
 * Conquest Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Jul. 2024
 */

// Class will house UI implementation for Conquest screen. The conquest section of the game is where the story vignettes through combat occurs (See brainstorming-ideas.md in jounral). 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ConquestUI extends JPanel{

//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 82;
    final private Color customColorBeige = new Color(253, 236, 166);
    final private Color customColorBrown = new Color(102, 72, 54);
    final private Border buttonBorder = BorderFactory.createLineBorder(customColorBeige, 1);

    // GUI elements
    private JButton leave;

     //Rectangle object is adapted to store the x-pos, y-pos, width, and height of GUI components.
    Rectangle leaveBounds;
    private int leaveWidth, leaveHeight;


//========================================================
// Constructor
//========================================================
    public ConquestUI() {
        setLayout(null);
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        leave = new JButton("<html>Return to World<html>");
        buttons.add(leave);

        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
            buttons.get(i).setForeground(customColorBeige); 
            buttons.get(i).setBackground(customColorBrown); 
            buttons.get(i).setFocusPainted(false);
            buttons.get(i).setBorder(buttonBorder);
            // buttons.get(i).setPreferredSize(new Dimension(width / 3, height / 22));
            // buttons.get(i).setMaximumSize(new Dimension(width / 3, height / 22));
        }

        //=======================================================
        //
        //Relatively scaling and sizing world components
        //
        // 
        leaveWidth = leave.getPreferredSize().width * 14 / 10;
        leaveHeight = leave.getPreferredSize().height * 12 / 10;
        leaveBounds = new Rectangle((width - leaveWidth) / 2, (height - leaveHeight) / 2, leaveWidth, leaveHeight);
        leave.setBounds(leaveBounds); //middle of screen
        add(leave);
        //
        //
        //================================================================

        leave.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/World9.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }
}
