/*
 * Tavern Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/* 
 * Implementation for the "Tavern" panel which can be accessed from the Town screen. This class is used to talk to townsfolk. Once the "Talk to townsfolk" button is pressed an audio file is played used the SFX method that plays the respective audio file to go along with the prompt. The prompt is also displayed in text on the JLabel located inside the tavern screen. 
 */

class Tavern extends JPanel {

//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 94;
    
    private int prompt = 0;
    private Color customDarkWood = new Color(139, 69, 19); // Dark wood color
    private Color customLightWood = new Color(205, 133, 63); // Light wood color

//========================================================
// Constructor
//========================================================
    public Tavern() {

        this.setLayout(null);
        JPanel tavernPanel = new JPanel(new BorderLayout());

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton back = new JButton("↩ Back");
        buttons.add(back);
        JButton talk = new JButton("Talk to townsfolk");
        buttons.add(talk);

        //format buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
            buttons.get(i).setForeground(new Color(255, 255, 255)); // White text
            buttons.get(i).setPreferredSize(new Dimension(width / 3, height / 22));
            buttons.get(i).setMaximumSize(new Dimension(width / 3, height / 22));
            buttons.get(i).setBackground(customDarkWood);
        }

        // Create the mesage label with custom styling
        JLabel message = new JLabel("", SwingConstants.CENTER);
        message.setFont(new Font("Serif", Font.ITALIC, buttonFont));
        message.setForeground(customLightWood);
        message.setBackground(new Color(0, 0, 0)); // Set the background color to black
        message.setOpaque(true); // Make the background visible
        message.setBorder(new EmptyBorder(20, 30, 20, 30)); //padding so that text wont touch edge of text label

        // Create the information label 
        JLabel info = new JLabel("", SwingConstants.CENTER);
        info.setFont(new Font("Serif", Font.ITALIC, buttonFont));
        info.setForeground(customLightWood);
        info.setBackground(new Color(0, 0, 0)); // Set the background color to black and slighly transparent
        info.setOpaque(true); // Make the background visible

        //=======================================================
        //
        //
        //Relatively scaling and sizing world components
        //
        // To center component on screen use this formula: 
        // (screenWidth - componentWidth) / 2
        // Ex. Calculation
        //  ScreenWidth = x, componentWidth = x / 3
        // x - (x / 3) = (2x / 3). 
        // (2x / 3) / 2 = 2x / 6 = x / 3. 
        // To center the screen the x-position should be (screenWidth / 3). 
        tavernPanel.setBounds(screenSize.width / 3, screenSize.height / 3, width / 3, height / 3);
        //==========================================================

        // Add components to the panel
        tavernPanel.add(back, BorderLayout.NORTH);
        tavernPanel.add(info, BorderLayout.CENTER);
        tavernPanel.add(talk, BorderLayout.SOUTH);
        add(tavernPanel);

//========================================================
// Action Listeners
//========================================================

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            SFX.stopAllNonLoopingSounds();
            SFX.playSound("assets/SFX/interface1.wav");
            message.setText("");
            SFX.playSound("assets/SFX/door-close.wav");
            Driver.changePanel("town");
            MusicPlayer.playMusic("assets/Music/town-bgm.wav");
            SFX.playSound("assets/SFX/town-ambient-sfx2.wav", true);
        });

        // Action listener for the 'Purchase' button. Cycles through four different prompts. 
        talk.addActionListener(e -> {
            SFX.stopAllNonLoopingSounds();
            SFX.playSound("assets/SFX/interface1.wav");

            if (prompt == 0){
                tavernPanel.add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Markus: You can buy potions at the bazaar.</div></html>");
                SFX.playSound("assets/SFX/Voice-overs/Potions - Frederick1.wav");
            }else if(prompt == 1){
                tavernPanel.add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Guardsman: I heard you need a unique set of armor to survive the monsters in the abandoned colosseum.</div></html>");
                SFX.playSound("assets/SFX/Voice-overs/Unique set - Samuel.wav");
            }else if (prompt == 2){
                tavernPanel.add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Miner: I usually find one metal for every four stone when mining down in the mineshaft out west.</div></html>");
                SFX.playSound("assets/SFX/Voice-overs/Mining - Mark.wav");
            }else if(prompt == 3){
                tavernPanel.add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Aaron: There's a secret merchant that sells a powerful potion. Some say he only comes out to the bazaar once every three blue moons.");
                SFX.playSound("assets/SFX/Voice-overs/Secret merchant - george.wav");
            }
            prompt++;
            if(prompt == 4 ){
                prompt = 0;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            try{
                g.drawImage(ImageIO.read(new File("assets/images/tavern.png")), 0, 0, getWidth(), getHeight(), this);
            }catch (IOException e){
                //Auto-generated catch block
                e.printStackTrace();
            }
    }
}
