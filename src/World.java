import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;

class World extends JPanel{
    /**
     * This function hosts the world map screen with buttons to go to town or dungeon
     * @param player The player character object
     * @throws IOException
     */
    public World(){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JButton quit = new JButton("Quit");
        JButton town = new JButton("Town");
        JButton dungeon = new JButton("Dungeon");
        JButton leave = new JButton("<- Leave");

        // This section adds the components and controls layout
        add(Box.createVerticalGlue());
        add(quit);
        add(Box.createRigidArea(new Dimension(600, 20)));
        add(town);
        add(Box.createRigidArea(new Dimension(400, 20)));
        add(dungeon);
        add(Box.createRigidArea(new Dimension(400, 20)));
        add(leave);
        add(Box.createVerticalGlue());

        quit.setAlignmentX(BOTTOM_ALIGNMENT);
        town.setAlignmentX(BOTTOM_ALIGNMENT);
        dungeon.setAlignmentX(BOTTOM_ALIGNMENT);
        leave.setAlignmentX(BOTTOM_ALIGNMENT);;

        // Quit button exits the game
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Town button takes you to the town
        town.addActionListener(e -> {
            try {
                Driver.changePanel("town");
                MusicPlayer.playMusic("assets/images/Music/Village Consort.wav");
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Dungeon button takes you to the dungeon
        dungeon.addActionListener(e -> {
            try {
                Driver.changePanel("dungeon");
                MusicPlayer.playMusic("assets/images/Music/Day Of Recon - Max Surla_Media Right Productions.wav");
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}