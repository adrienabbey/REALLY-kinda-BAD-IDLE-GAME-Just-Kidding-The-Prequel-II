import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

class Town extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/town3.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }
    /**
     * This function hosts the town screen with buttons to buy potions or leave
     * @param player The player character object
     * @throws IOException
     */
    public Town() throws IOException{
        JPanel buttonPanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);

        //Whenever calling a getter for the player, it breaks it.
        JLabel name = new JLabel("Name: ");

        JButton buy = new JButton("Buy");
        buttons.add(buy);
        JButton leave = new JButton("Leave");
        buttons.add(leave);
        JButton button3 = new JButton("button");
        buttons.add(button3);
        
        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        // add(name);
        add(Box.createRigidArea(new Dimension(100, 350)));
        add(buy);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(button3);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(leave);
        add(Box.createVerticalGlue());

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);

            buttons.get(i).setPreferredSize(new Dimension(200, 80));
            buttons.get(i).setMaximumSize(new Dimension(200, 80));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));
        }

        this.setAlignmentX(CENTER_ALIGNMENT);

        // Buy button adds a potion to the player's inventory
        buy.addActionListener(e -> {
            try{
                // player.addPotion(1);
            } catch (Exception e1){
                // TODO - Make this notify the user in window
                e1.printStackTrace();
            }
        });

        // Leave button takes you back to the world map
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("world");
                MusicPlayer.playMusic("assets/images/Music/Brilliant1.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Button for other things dont know what yet
        leave.addActionListener(e -> {

        });
    }
}