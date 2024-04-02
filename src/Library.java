import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Library extends JPanel {
    private int page = 1;

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/library3.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

    public Library() {
        // Set the layout with vertical alignment and padding
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(200, 420, 200, 420)); // Add padding around the panel

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color
        back.setFocusPainted(false); // Remove focus ring around the button

        // Create the 'Back' button with custom styling
        JButton nextPage = new JButton("Next Page");
        nextPage.setFont(new Font("Serif", Font.BOLD, 24));
        nextPage.setForeground(new Color(255, 255, 255)); // White text
        nextPage.setBackground(new Color(139, 69, 19)); // Dark wood color
        nextPage.setFocusPainted(false); // Remove focus ring around the button

        // Create the information label of monster with custom styling
        JLabel text = new JLabel("<html><div style='text-align: center;'>=== Book of Monsters: === <br></div></html>", SwingConstants.CENTER);
        text.setFont(new Font("Serif", Font.ITALIC, 24));
        text.setForeground(new Color(102, 72, 54)); // Light wood color
        text.setBackground(new Color(253, 236, 166)); // Set the background color
        text.setOpaque(true); // Make the background visible

        // Create the label with the monster image
        JLabel monsterImage = new JLabel();
        monsterImage.setBackground(new Color(253, 236, 166)); // Set the background color
        monsterImage.setOpaque(true); // Make the background visible

        // Add components to the panel
        add(back, BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);
        add(nextPage, BorderLayout.SOUTH);
        add(monsterImage, BorderLayout.WEST);

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            //Set text back to cover, set page counter accordingly, set monster image to null
            page = 1;
            text.setText("<html><div style='text-align: center;'>=== Book of Monsters: === <br></div></html>");
            monsterImage.setIcon(null);

            // play button sfx, change panel, and play town music. 
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("town");
            MusicPlayer.playMusic("assets/Music/Village Consort.wav");
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
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("assets/images/Rock2.png")));
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
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("assets/images/hobogoblin.png")));
                    monsterImage.setIcon(icon);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets/SFX/goblins/goblin-1.wav");
            }
            if (page == 3) {
                text.setText("<html><div style='text-align: center;'>=== Entry 003: Pirate Skeleton === <br><br> &nbsp; Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from all its wealth, &nbsp; health, and home. Also, it has googley eyes. <br><br> Gold Reward: 4  <br> Muscle: 4<br>Brain: 4<br> Heart: 4</div></html>");
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("assets/images/skeleton.png")));
                    monsterImage.setIcon(icon);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets/SFX/goblins/goblin-1.wav");
            }
            if (page == 4) {
                text.setText("<html><div style='text-align: center;'>=== Entry 004: Cyclops === <br><br> &nbsp; Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from all its wealth, &nbsp; health, and home. Also, it has googley eyes. <br><br> Gold Reward: 4  <br> Muscle: 4<br>Brain: 4<br> Heart: 4</div></html>");
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("assets/images/Cyclops.png")));
                    monsterImage.setIcon(icon);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets/SFX/goblins/goblin-1.wav");
            }
            if (page == 5) {
                text.setText("<html><div style='text-align: center;'>=== Entry 005: Geoduck === <br><br> &nbsp; Commonly misidentified as a hobgoblin (so rude!), this hobo goblin is obviously suffering after becoming emancipated from all its wealth, &nbsp; health, and home. Also, it has googley eyes. <br><br> Gold Reward: 4  <br> Muscle: 4<br>Brain: 4<br> Heart: 4</div></html>");
                try {
                    ImageIcon icon = new ImageIcon(ImageIO.read(new File("assets/images/Duck.png")));
                    monsterImage.setIcon(icon);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                SFX.playSound("assets/SFX/goblins/goblin-1.wav");
            }
            page++;
        });
    }
}
