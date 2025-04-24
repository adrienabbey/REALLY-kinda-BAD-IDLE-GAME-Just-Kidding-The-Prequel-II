/*
 * Credits Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.SwingConstants;
import java.awt.Image;

/*
 * Implementation for the "Credits" panel which can be accessed from the setting screen. Uss a timer to continously update the JLabel in order to achieve a rolling effect. The label houses all the credits for the game, including the credits to the developers, professor, and to the images, music, sound effects, and voiceovers used. 
 * 
 * TODO: double check credits all assets used and that all assets are free to use or the rights to use them have been acquired by getting the appropriate license. 
 */

public class Credits extends JPanel {

    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JLabel rollingText;
    final private int rollTextWidth = (int)(screenSize.width / 2.1);
    private Timer timer;
    public static int yPos;
    final static int xPos = screenSize.width / 2;
    private Image background;

    private Color goldenWoodColor = new Color(255, 190, 128);
    // private Color goldColor = Color.decode("#DAA520");

    /* Constructor */
    public Credits() {
        try{
            background = ImageIO.read(new File("assets/images/scene9.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot open background image.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        // Set the layout with vertical alignment and padding
        this.setLayout(null); // Use null layout for precise positioning

        // Create the 'Back' button 
        JButton back = new JButton("↩ Back");
        back.setFont(new Font("Serif", Font.BOLD, screenSize.width / 80));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color
        back.setFocusPainted(false); // Remove focus ring around the button
        
        // Create the rolling text label 
        rollingText = new JLabel("<html><div style='text-align: center; vertical-align: top;'>"
        + "B.A.D. Team. <br><br>" 

        + "Product Owner &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Adrien Abbey <br><br>" 

        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Scrum Master &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Muhammed Abushamma <br><br>"

        + "Programmers &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Adrien Abbey<br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Muhammed Abushamma <br>" 
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp; Luke Davidson <br>" 
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Brandon Walker <br><br>" 

        + "Project Managers &nbsp;&nbsp;&nbsp;&nbsp; Adrien Abbey <br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Muhammed Abushamma <br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp; Luke Davidsom <br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Brandon Walker <br><br>"

        + "Quality Assurance &nbsp;&nbsp;&nbsp;&nbsp; Adrien Abbey <br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Muhammed Abushamma <br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp; Luke Davidsom <br>"
        + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Brandon Walker <br><br><br>"

        + "Developed under the mentorship of Cogan Shimizu <br><br><br>" 
        
        + " Start screen background &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Luke Davidson <br><br>" 
        + " Monster designs &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Luke Davidson <br><br>" 
        + "Other images &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dall-E AI <br><br>" 
        + "Voiceovers &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; PlayHT <br><br><br><br>" 
        
        + "Background Music brought to you by: <br><br>" 
        + " Jay Man &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Kevin MacLeod <br><br>" 
        + " Alexander Nakarada &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Derek & Brandon Fiechter <br><br>" 
        + " Musical Discoveries &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Always Music <br><br>" 
        + " Turku, Nomads of the Silk Road &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; BrunuhVille <br><br>"
        + " David Fesliyan &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Fesliyan Studios <br><br>" 
        + " Medieval Path <br><br><br><br>"

        + "Sound Effects brought to you by: <br><br>" 
        + " Ivo Vicic &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; STEINE WERFEN <br><br>"
        + " rtisticdude &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Artninja <br><br>" 
        + " klankbeeld &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Benboncan <br><br>" 
        + " blaukreuz &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Dibko <br><br>"
        + " Relaxing World &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ambiance Magic <br><br>"
        + " Echo Cinematics &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; GFX Sounds <br><br>"
        + " freesound.org &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Epic Stock Media <br><br>"
        + " Opengameart.org &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Pixabay.com <br><br>"
        + " uppbeat.io &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Zapsplat.com <br><br><br>"
        
        ,SwingConstants.CENTER);


        // Set the position and size of the rolling text label
        rollingText.setBounds(xPos, yPos, (rollTextWidth), (int)(screenSize.height * 1.9));       
        rollingText.setFont(new Font("Serif", Font.PLAIN, screenSize.width / 96));
        rollingText.setForeground(new Color(255, 190, 128)); // Lighter wood color
        rollingText.setBackground(new Color(0, 0, 0)); // Set the background color to black
        rollingText.setOpaque(true); // Make the background visible
        Border yellowBorder = BorderFactory.createLineBorder(goldenWoodColor, 3);
        rollingText.setBorder(yellowBorder);
        
        // Set the position and size of the 'Back' button
        back.setBounds(10, 10, (back.getFont().getSize() * 6), (screenSize.height / 24));

        // Add components to the panel
        add(back);
        add(rollingText);


    /* Action Listeners*/

        // Action listener for the 'Back' button. Sets text to correct position after very opening. 
        back.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
            Driver.changePanel("settings");
        });

        // Timer for rolling text
        timer = new Timer(12, e -> {
            yPos--;
            int x = (getWidth() - rollingText.getWidth()) / 2;
            rollingText.setLocation(x, yPos);
            if (yPos + rollingText.getHeight() < 0) {
                yPos = ((int)screenSize.height);
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
    }
}
