import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Dungeon extends JPanel {
    private Image backgroundImage;
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */
    public Dungeon() {
        try {
            backgroundImage = ImageIO.read(new File("assets/images/Dungeon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO add the actual enemy generation and combat here

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        

        // This is creating all the objects that will be displayed on the screen
        JButton run = new JButton("Run");

        // This is adding all objects to the screen, and controlling layout
        add(Box.createVerticalGlue());
        add(run);
        add(Box.createVerticalGlue());

        // control the layout of the buttons
        run.setAlignmentX(CENTER_ALIGNMENT);

        // This button will be used to run away from combat
        run.addActionListener(e -> {
            try {
                Driver.changePanel("world");
                MusicPlayer.playMusic("assets/images/Music/Court and Page - Silent Partner.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
    }
}