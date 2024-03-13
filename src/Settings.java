import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;


class Settings extends JPanel {
    private static boolean isMute = true;

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/world5.png")), 0, 0, getWidth(), getHeight(), this);
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
    public Settings() throws IOException{
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        
        JSlider slider = new JSlider(JSlider.HORIZONTAL, -70, 6, 0); // range from -70 to 6
        slider.setBackground(customColorBrown); // Set the background color

        JButton mute = new JButton("Mute Volume");
        buttons.add(mute);
        JButton adjust = new JButton("Adjust Volume");
        buttons.add(adjust);   
        JButton leave = new JButton("Back to Main Menu");
        buttons.add(leave);   
        JButton credits = new JButton("Credits");
        buttons.add(credits); 

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        // add(name);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(mute);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(adjust);
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(slider);
        add(Box.createRigidArea(new Dimension(100, 30)));
        add(credits);
        add(Box.createRigidArea(new Dimension(100, 60)));
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
            
            // Formats leave button
            if (i == 3) {
                buttons.get(2).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(2).setBackground(Color.GRAY);
                buttons.get(2).setForeground(Color.WHITE);
                buttons.get(2).setPreferredSize(new Dimension(70, 80));
                buttons.get(2).setMaximumSize(new Dimension(240, 500));
                buttons.get(2).setFont(new Font("Times New Roman", Font.BOLD, 24));
        }
    }
        this.setAlignmentX(CENTER_ALIGNMENT);

        //Format volume slider
        slider.setMaximumSize(new Dimension(200, 20));

        // Volume button mutes or unmutes master volume.
        mute.addActionListener(e -> {
            try {
                MusicPlayer.toggleMute();; 
                if (isMute) {
                    mute.setText("Unmute Volume");
                    isMute = false; 
                } 
                  else  {
                    mute.setText("Mute Volume");
                    isMute = true;
                }
            }           
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // TODO: add credits panel with text
        credits.addActionListener(e -> {
            try {
                Driver.changePanel("credits");
                MusicPlayer.playMusic("assets/images/Music/Chevalier, Mult Estes Guariz - French Crusade Song-[AudioTrimmer.com].wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        // Leave button takes you back to the world map
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("start");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Add slider implementation. Adjust volume based on slider value.
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
