/*
 * Library Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/* Implementation for the libray class which can be accessed from the Town screen. Contains the Book of Monsters feature which allows the playe to flip though pages and see the descriptions of monsters in the game. Each page has the monster image, description, and monster sfx when entering page.
 * 
 * TODO: add more books to the library, such as one that dispenses lore. Make Book of Monsters look like an actual book.  
*/

class Library extends JPanel {
//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 86;
    final int imageWidth = (int) (width / 6);
    final int imageHeight = -1; // set to negative 1 to scale only one way
    
    private int page = 1;
    private Color customDarkWood = new Color(139, 69, 19); // Dark wood color
    private Color customLightWood = new Color(205, 133, 63); // Light wood color

//========================================================
// Constructor
//========================================================
    public Library() {
        // Set the layout with vertical alignment and padding
        this.setLayout(null);
        JPanel libraryPanel = new JPanel(new BorderLayout());

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton back = new JButton("↩ Back");
        buttons.add(back);
        JButton nextPage = new JButton("Next Page");
        buttons.add(nextPage);

        //format buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
            buttons.get(i).setForeground(new Color(255, 255, 255)); // White text
            buttons.get(i).setBackground(new Color(139, 69, 19)); // Dark wood color
            buttons.get(i).setFocusPainted(false); // Remove focus ring 
            buttons.get(i).setPreferredSize(new Dimension(width / 3, height / 22));
            buttons.get(i).setMaximumSize(new Dimension(width / 3, height / 22));
        }

        // Create the information label of monster 
        JLabel text = new JLabel("<html><div style='text-align: center;'>=== Book of Monsters: === <br></div></html>", SwingConstants.CENTER);
        
        text.setFont(new Font("Serif", Font.ITALIC, buttonFont));
        text.setForeground(new Color(102, 72, 54)); // Light wood color
        text.setBackground(new Color(253, 236, 166)); // Set the background color
        text.setOpaque(true); // Make the background visible
        text.setBorder(new EmptyBorder(20, 30, 20, 30)); //padding so that text wont touch edge of text label

        // Create the label with the monster image
        JLabel monsterImage = new JLabel();
        monsterImage.setBackground(new Color(253, 236, 166)); // Set the background color
        monsterImage.setOpaque(true); // Make the background visible
        

        //=======================================================
        //
        //
        //Relatively scaling and sizing world components
        //
        // 
        libraryPanel.setBounds(width / 4, height / 4, width / 2, height / 2);
        //==========================================================

        // Add components to the panel
        libraryPanel.add(back, BorderLayout.NORTH);
        libraryPanel.add(text, BorderLayout.CENTER);
        libraryPanel.add(nextPage, BorderLayout.SOUTH);
        libraryPanel.add(monsterImage, BorderLayout.WEST);
        add(libraryPanel);


//========================================================
// Action Listeners
//========================================================

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            //Set text back to cover, set page counter accordingly, set monster image to null
            page = 1;
            text.setText("<html><div style='text-align: center;'>=== Book of Monsters: === <br></div></html>");
            monsterImage.setIcon(null);

            // play button sfx, change panel, and play town music. 
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("town");
            MusicPlayer.playMusic("assets/Music/town-bgm.wav");
            SFX.playSound("assets/SFX/town-ambient-sfx2.wav", true);
            // SFX.playSound("assets/SFX/town-ambient-sfx2.wav", true);  
        });

        // Action listener for the 'Back' button
        nextPage.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            // Prompts
            if (page == 0) {
                text.setText("<html><div style='text-align: center;'>=== Book of Monsters: === <br></div></html>");
                monsterImage.setIcon(null);
            }
            if (page == 1) {
                text.setText("<html><div style='text-align: center;'>=== Entry 001: Rabid Pet Rock === <br><br> &nbsp; Someone glued googley eyes onto a pet rock, then abandoned it. Also, it &nbsp; has rabies and wants to kill you. <br><br> Gold Reward: 2 <br> Muscle: 2<br>Brain: 2<br> Heart: 2</div></html>");
                try {
                    // retrieve and scale monster image
                    Image image = ImageIO.read(new File("assets/images/Rock2.png"));
                    Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    monsterImage.setIcon(icon);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets/SFX/stone-gathering-sfx.wav");
            }
            //page++;
            if (page == 2) {
                text.setText("<html><div style='text-align: center;'>=== Entry 002: Hobo Goblin === <br><br> &nbsp; Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from all its wealth, &nbsp; health, and home. Also, it has googley eyes. <br><br> Gold Reward: 4  <br> Muscle: 4<br>Brain: 4<br> Heart: 4</div></html>");
                try {
                    // retrieve and scale monster image
                    Image image = ImageIO.read(new File("assets/images/hobogoblin.png"));
                    Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon2 = new ImageIcon(scaledImage);
                    monsterImage.setIcon(icon2);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets/SFX/goblins/goblin-1.wav");
            }
            if (page == 3) {
                text.setText("<html><div style='text-align: center;'>=== Entry 003: Pirate Skeleton === <br><br> &nbsp; This unbelievably cute skeleton is wearing a pirate outfit.  Despite its adorable demeanor, it still has a deep-seated hatred of the living.. <br><br> Gold Reward: 4  <br> Muscle: 4<br>Brain: 4<br> Heart: 4</div></html>");
                try {
                    // retrieve and scale monster image
                    Image image = ImageIO.read(new File("assets/images/skeleton.png"));
                    Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon3 = new ImageIcon(scaledImage);
                    monsterImage.setIcon(icon3);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets\\SFX\\RPG Sound Pack\\NPC\\shade\\shade6.wav"); // skeleton sfx
            }
            if (page == 4) {
                text.setText("<html><div style='text-align: center;'>=== Entry 004: Cyclopian Gentleman === <br><br> &nbsp; This impecably dressed giant just radiates cultured sophistication.  He's even wearing a tophat with a monocle over his single, giant eye.  Unfortunately, you're too small to notice and he's about to step all over you.  Which is not to say he didn't actually see you, he just doesn't care.<br><br> Gold Reward: 4  <br> Muscle: 10<br>Brain: 10<br> Heart: 10</div></html>");
                try {
                    // retrieve and scale monster image
                    Image image = ImageIO.read(new File("assets/images/Cyclops.png"));
                    Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon4 = new ImageIcon(scaledImage);
                    monsterImage.setIcon(icon4);
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets\\SFX\\RPG Sound Pack\\NPC\\giant\\giant5.wav"); // cyclops sfx
            }
            if (page == 5) {
                text.setText("<html><div style='text-align: center;'>=== Entry 005: Creepy Geoduck === <br><br> &nbsp; Its a duck made out of rocks. Very scary. <br><br> Gold Reward: 4  <br> Muscle: 4<br>Brain: 4<br> Heart: 4</div></html>");
                try {
                    // retrieve and scale monster image
                    Image image = ImageIO.read(new File("assets/images/Duck.png"));
                    Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
                    ImageIcon icon5 = new ImageIcon(scaledImage);
                    monsterImage.setIcon(icon5);
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets\\SFX\\RPG Sound Pack\\NPC\\gutteral beast\\mnstr15.wav"); // geoduck sfx
                page = -1; // reset page to start when at the end of book
            }
            page++;
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/library5.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

}
