import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Town extends JPanel {
    /**
     * This function hosts the town screen with buttons to buy potions or leave
     * @param player The player character object
     * @throws IOException
     */
    public Town() throws IOException{
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel charPanel = new JPanel(); // Character panel where you see and set stats for your character
        charPanel.setLayout(new BoxLayout(charPanel, BoxLayout.X_AXIS));
        BufferedImage image = ImageIO.read(new File("assets/images/shopkeep.jpg"));
        JLabel pic = new JLabel(new ImageIcon(image));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton buy = new JButton("Buy");
        JButton leave = new JButton("Leave");

        // This section adds the components and controls layout
        buttons.add(Box.createVerticalGlue());
        buttons.add(buy);
        buttons.add(Box.createRigidArea(new Dimension(0, 20)));
        buttons.add(leave);
        buttons.add(Box.createVerticalGlue());

        buy.setAlignmentX(CENTER_ALIGNMENT);
        leave.setAlignmentX(CENTER_ALIGNMENT);

        charPanel.add(Box.createVerticalGlue());
        charPanel.add(pic);
        charPanel.add(Box.createVerticalGlue());

        add(Box.createVerticalGlue());
        add(charPanel);
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(buttons);
        add(Box.createVerticalGlue());

        charPanel.setAlignmentX(CENTER_ALIGNMENT);
        buttons.setAlignmentX(CENTER_ALIGNMENT);

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
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}