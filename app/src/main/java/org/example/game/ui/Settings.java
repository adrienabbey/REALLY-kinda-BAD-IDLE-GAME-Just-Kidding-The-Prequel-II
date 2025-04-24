/*
 * Settings Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.Box;

/* 
 * Implementation for the "Settings" panel which can be accessed fron the start screen. 
 * Houses the buttons and sliders used to mute music volume, adjust music volume, adjust sfx volume, and to access the credits panel. 
 */

class Settings extends JPanel {
    /* Fields */
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static boolean isMute = true;
    final Font BUTTON_FONT = new Font("Serif", Font.BOLD, screenSize.width / 70);
    final int buttonWidth = screenSize.width / 5;
    final Dimension BUTTON_SIZE = new Dimension(buttonWidth, screenSize.height / 18);
    final Dimension BUTTON_GAP = new Dimension(0, screenSize.height / 54);
    final Dimension SLIDER_SIZE = new Dimension(buttonWidth - 1, screenSize.height / 39);

    final Color customColorBeige = new Color(253, 236, 166);
    final Color customColorBrown = new Color(102, 72, 54);
    final Color customLightGray = new Color(220, 220, 220);
    final Color customCrimson = new Color(220, 20, 60); 
    final Color customForestGreen = new Color(34, 139, 34);  
    final Color customDarkBlue = new Color(0, 0, 139);
    final Color customRedOrange = new Color(255, 69, 0); 
    final Color customSeaGreen = new Color(60, 179, 113);
    final Color customBeigeTan = new Color(245, 245, 220);
    final Color customFireBrick = new Color(178, 34, 34);
    final Color customPaleYellow = new Color(255, 255, 224);
    final Color customLimeGreen = new Color(50, 205, 50); 
    
    private Image background;

    /* Constructor */
    public Settings() throws IOException{

        try {
            background = ImageIO.read(new File("assets/images/World5.1.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot open background image.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        
        // slider ranges from -70 to 5, starts at -4. The max allowable float value of the master volume is 6.0206, but set to 5 here just to ensure there are no distortions generated. Having it a 6 and increasing and decreasing the floatcontrol repeatedly gives the opportunity for distortative spikes in audio output to occur. 
        JSlider slider = new JSlider(JSlider.HORIZONTAL, -70, 5, -4); 
        
        slider.setBackground(customColorBrown); // Set the background color

        JSlider sliderSFX = new JSlider(JSlider.HORIZONTAL, -70, 5, -5); // range from -70 to 6, starts at -5
        sliderSFX.setBackground(customColorBrown); // Set the background color

        JButton mute = new JButton("Mute Music Volume");
        buttons.add(mute);
        JButton adjust = new JButton("Adjust Music Volume");
        buttons.add(adjust);   
        JButton sfx = new JButton("Adjust SFX Volume");
        buttons.add(sfx);   
        JButton leave = new JButton("🢐 Back to Main Menu");
        buttons.add(leave);   
        JButton credits = new JButton("📜 Credits");
        buttons.add(credits); 
        JButton gremlin = new JButton("👿 Let out the Code Gremlin?");
        buttons.add(gremlin); 

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(mute);
        add(Box.createRigidArea(BUTTON_GAP));
        add(adjust);
        add(slider);
        add(Box.createRigidArea(BUTTON_GAP));
        add(sfx);
        add(sliderSFX);
        add(Box.createRigidArea(BUTTON_GAP));
        add(credits);
        add(Box.createRigidArea(BUTTON_GAP));
        add(gremlin);
        add(Box.createRigidArea(BUTTON_GAP));
        add(leave);
        add(Box.createVerticalGlue());

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(BUTTON_SIZE);
            buttons.get(i).setMaximumSize(BUTTON_SIZE);
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(BUTTON_FONT);
            buttons.get(i).setBorder(Driver.buttonBorder);
            if (buttons.get(i).equals(gremlin)){
                buttons.get(i).setBackground(customColorBrown);
                buttons.get(i).setForeground(customColorBeige);
            }
        }

        this.setAlignmentX(CENTER_ALIGNMENT);

        //Format music volume slider
        slider.setMaximumSize(SLIDER_SIZE);

        //Format sfx volume slider
        sliderSFX.setMaximumSize(SLIDER_SIZE);

        
        /* Methods */

        // Volume button mutes or unmutes master volume.
        mute.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                MusicPlayer.toggleMute(); 
                // SFX.toggleMuteSFX();
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

        credits.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Credits.yPos = ((int)screenSize.height); // set rolling text box in correct position;
                long startTime = System.currentTimeMillis();
                long waitTime = 75; // Wait for 75 milliseconds (0.075 seconds), so that credits text is properly set to correct location without previous text's location being seen.
                
                while (System.currentTimeMillis() - startTime < waitTime) {
                    // Busy-wait loop
                    // This loop repeatedly checks the current time until the desired wait time has passed
                }
        
                MusicPlayer.playMusic("assets/Music/3-Song-of-the-North.wav");
                Driver.changePanel("credits");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        // Unleashes the code gremlin
        gremlin.addActionListener(e -> {
            try {
               if(!Driver.gremlinOn){
                SFX.playSound("assets/SFX/interface1.wav");
                SFX.playSound("assets/SFX/goblins/goblin-cackle-87566.wav");
                gremlin.setText("😈 Code Gremlin Unleashed!");
                gremlin.setBackground(customFireBrick);
                Driver.gremlinOn = true;
        
            }else{
                SFX.playSound("assets/SFX/interface1.wav");
                SFX.playSound("assets/SFX/goblins/goblin-scream-87564 (1).wav");
                gremlin.setText("👿 Let out the Code Gremlin?");
                gremlin.setBackground(customColorBrown);
                Driver.gremlinOn = false;
            }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // "Back to main menu button" takes you back to the start screen
        leave.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("start");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Slider Implementation. Adjusts music volume based on slider value. Adds change listener and method statsChanged sets volume of MusicPlayer.  
        slider.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    float volume = (float)source.getValue();
                    MusicPlayer.setVolume(volume);
                }
            }
        });

        // Slider Implementation. Adjusts SFX volume based on slider value. Adds change listener and method statsChanged sets volume of SFX.java.  
        sliderSFX.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider sourceSFX = (JSlider)e.getSource();
                    if (!sourceSFX.getValueIsAdjusting()) {
                        float volumeSFX = (float)sourceSFX.getValue();
                        SFX.setcurrentVolumeSFX(volumeSFX);
                    }
                }
            });
    }

    // draw image to backgroud
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
    }
    }
