import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class World extends JPanel{
    private Timer timer; 
    /**
     * This function hosts the world map screen with buttons to go to town or dungeon
     * @param player The player character object
     * @throws IOException
     */
    public World(){
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);

        JButton quit = new JButton("Quit");
        buttons.add(quit);
        JButton town = new JButton("Town");
        buttons.add(town);
        JButton mine = new JButton("Mineshaft");
        buttons.add(mine);
        JButton wood = new JButton("Forest");
        buttons.add(wood);
        JButton home = new JButton("Homestead");
        buttons.add(home);
        JButton dungeon = new JButton("Dungeon");
        buttons.add(dungeon);
        JButton leave = new JButton("Main Menu");
        buttons.add(leave);

        // This section adds the components and controls layout
        add(Box.createVerticalGlue());
        add(Box.createRigidArea(new Dimension(10, 20)));
        add(leave);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(town);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(mine);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(home);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(town);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(wood);
        add(Box.createRigidArea(new Dimension(100, 20)));
        add(dungeon);
        add(Box.createRigidArea(new Dimension(200, 20)));
        add(quit);
        add(Box.createVerticalGlue());

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setPreferredSize(new Dimension(130, 80));
            buttons.get(i).setMaximumSize(new Dimension(150, 80));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));

            // formatting quit button
            if (i == 0) {
                buttons.get(0).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
                buttons.get(i).setPreferredSize(new Dimension(150, 80));
                buttons.get(i).setMaximumSize(new Dimension(150, 80));
            }

            // formatting main menu button
            if (i == 6) {
                buttons.get(6).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(6).setPreferredSize(new Dimension(175, 80));
                buttons.get(6).setMaximumSize(new Dimension(175, 80));
            }
        }

        quit.setAlignmentX(BOTTOM_ALIGNMENT);
        town.setAlignmentX(BOTTOM_ALIGNMENT);
        dungeon.setAlignmentX(BOTTOM_ALIGNMENT);
        leave.setAlignmentX(BOTTOM_ALIGNMENT);
        
        // Quit button exits the game
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Town button takes you to the town
        town.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("town");
                MusicPlayer.playMusic("assets/Music/town-bgm.wav");
                SFX.playSound("assets/SFX/town-ambient-sfx2.wav", true);                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Dungeon button takes you to the dungeon
        dungeon.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("dungeon");
                MusicPlayer.playMusic("assets/Music/1-Song-of-the-North.wav");
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Home button takes you to the Home screen
        home.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("home");
                MusicPlayer.playMusic("assets/Music/homestead-bgm.wav");
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // WoodCutting button takes you to the Woodcutting screen
        wood.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("forest");
                MusicPlayer.playMusic("assets/Music/ambient-farmland-sounds.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Mining button takes you to the mining screen
        mine.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("mineshaft");
                MusicPlayer.playMusic("assets/Music/Lamento di Tristano [Medieval Song].wav");
                SFX.playSound("assets/SFX/mineshaft-ambience2.wav", true);
                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Leave button takes you to the start screen
        leave.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("start");              
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    
        // timer = new Timer(1000, new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if (p)
        //         SFX.stopAllSounds();
        //     }
        // });
        // timer.start();
    }
}

