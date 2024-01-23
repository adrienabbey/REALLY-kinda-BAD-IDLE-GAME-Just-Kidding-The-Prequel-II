import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Town extends JFrame {
    public Town(PlayerCharacter player) throws IOException{
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        JPanel town = new JPanel();
        town.setLayout(new BoxLayout(town, BoxLayout.X_AXIS));

        JPanel charPanel = new JPanel(); // Character panel where you see and set stats for your character
        charPanel.setLayout(new BoxLayout(charPanel, BoxLayout.X_AXIS));
        BufferedImage image = ImageIO.read(new File("assets/images/shopkeep.jpg"));
        JLabel pic = new JLabel(new ImageIcon(image));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton buy = new JButton("Buy");
        JButton leave = new JButton("Leave");

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

        town.add(Box.createVerticalGlue());
        town.add(charPanel);
        town.add(Box.createRigidArea(new Dimension(20, 0)));
        town.add(buttons);
        town.add(Box.createVerticalGlue());

        buy.addActionListener(e -> {
            try{
                player.addPotion(1);
            } catch (Exception e1){
                // TODO - Make this notify the user in window
                e1.printStackTrace();
            }
        });
        leave.addActionListener(e -> {
            try {
                new World(player);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });

        this.getContentPane().add(town);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}