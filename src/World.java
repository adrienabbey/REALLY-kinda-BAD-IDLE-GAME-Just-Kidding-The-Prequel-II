import java.awt.Dimension;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java .awt.BorderLayout;

class World extends JPanel{
    private static boolean isMute = true;
    /**
     * This function hosts the world map screen with buttons to go to town or dungeon
     * @param player The player character object
     * @throws IOException
     */
    public World(){
        JPanel buttonPanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, -70, 6, 0); // range from -70 to 6

        // Add the button panel and the slider to the main panel
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(slider, BorderLayout.SOUTH);
        slider.setBackground(Color.red); // Set the background color

        JButton quit = new JButton("Quit");
        buttons.add(quit);
        JButton town = new JButton("Town");
        buttons.add(town);
        JButton dungeon = new JButton("Dungeon");
        buttons.add(dungeon);
        JButton leave = new JButton("Leave");
        buttons.add(leave);
        JButton volume = new JButton("Mute Volume");
        buttons.add(leave);
        JButton adjust = new JButton("Adjust Volume");

        // This section adds the components and controls layout
        add(Box.createVerticalGlue());
        add(Box.createRigidArea(new Dimension(10, 20)));
        add(leave);
        add(Box.createRigidArea(new Dimension(235, 20)));
        add(town);
        add(Box.createRigidArea(new Dimension(235, 20)));
        add(dungeon);
        add(Box.createRigidArea(new Dimension(235, 20)));
        add(quit);
        add(Box.createRigidArea(new Dimension(150, 20)));
        add(volume);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(adjust);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(slider);
        add(Box.createVerticalGlue());

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setPreferredSize(new Dimension(200, 80));
            buttons.get(i).setMaximumSize(new Dimension(200, 80));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));

            if (i == 0) {
                buttons.get(0).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
                buttons.get(i).setPreferredSize(new Dimension(200, 80));
                buttons.get(i).setMaximumSize(new Dimension(200, 80));
            }
            volume.setBackground(Color.LIGHT_GRAY); 
        }

        quit.setAlignmentX(BOTTOM_ALIGNMENT);
        town.setAlignmentX(BOTTOM_ALIGNMENT);
        dungeon.setAlignmentX(BOTTOM_ALIGNMENT);
        leave.setAlignmentX(BOTTOM_ALIGNMENT);
        volume.setAlignmentX(BOTTOM_ALIGNMENT);;

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

        // Leave button takes you to the start screen
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("start");              
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        
        // Volume button mutes or unmutes master volume.
        volume.addActionListener(e -> {
            try {
                MusicPlayer.toggleMute();; 
                if (isMute) {
                    volume.setText("Unmute Volume");
                    isMute = false; 
                } 
                  else  {
                    volume.setText("Mute Volume");
                    isMute = true;
                }
                }           
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Add a ChangeListener to the slider
        slider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    float volume = (float)source.getValue();
                    MusicPlayer.setVolume(volume);
                }
            }
        });
    }
}
