import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

class Shop extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/shop2.png")), 0, 0, getWidth(), getHeight(), this);
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
    public Shop() throws IOException{
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
        JButton inventory = new JButton("Inventory");
        buttons.add(inventory);
        JButton sell = new JButton("Sell");
        buttons.add(sell);
        
        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        // add(name);
        add(Box.createRigidArea(new Dimension(100, 350)));
        add(buy);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(sell);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(inventory);
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
        
        // Sell button to sell equipment or potions for gold
        sell.addActionListener(e -> {
            // TODO: Add sell implementation
        });

        // Sell button to sell equipment or potions for gold
        inventory.addActionListener(e -> {
            try {
                Driver.changePanel("inventory");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            // TODO: Add inventory implementation
        });

        // Leave button takes you back to the town panel
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("town");
                MusicPlayer.playMusic("assets/images/Music/Village Consort.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}