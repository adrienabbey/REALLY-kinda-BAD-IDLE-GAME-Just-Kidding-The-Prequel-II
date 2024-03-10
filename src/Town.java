import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
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
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColor = new Color(46, 86, 161);

        JButton buy = new JButton("Buy");
        buttons.add(buy);
        JButton leave = new JButton("Leave");
        buttons.add(leave);


        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(new Dimension(60, 80));
            buttons.get(i).setMaximumSize(new Dimension(600, 500));
            buttons.get(i).setBackground(customColor);
            buttons.get(i).setForeground(Color.WHITE);
            buttons.get(i).setFont(new Font("Arial", Font.BOLD, 32));
        }








        // This section adds the components and controls layout
        add(Box.createVerticalGlue());
        add(buy);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(leave);
        add(Box.createVerticalGlue());

        buy.setAlignmentX(CENTER_ALIGNMENT);
        leave.setAlignmentX(CENTER_ALIGNMENT);

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
                MusicPlayer.playMusic("assets/images/Music/Court and Page - Silent Partner.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}