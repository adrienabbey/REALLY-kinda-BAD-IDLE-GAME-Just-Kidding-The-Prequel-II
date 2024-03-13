import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
// TODO Narrow the swing down to what we actually need


// This class is just the intro starting screen with the buttons to start a new game, load a game, read directions, or quit
class StartScreen extends JPanel{
    private static boolean isMute = true;

    @Override
    protected void paintComponent(Graphics g) {

    super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("assets/images/startScreenTest.jpg")), 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            //Auto-generated catch block
            e.printStackTrace();
        }
}
    // This function hosts the start menu with buttons to start a new game, load a game, read directions, or quit
    
    public StartScreen() throws IOException{

        // This block of code is all the physical gui elements and their properties
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton quit = new JButton("Quit");
        buttons.add(quit);
        JButton newGame = new JButton("New Game");
        buttons.add(newGame);
        JButton loadGame = new JButton("Load Game");
        buttons.add(loadGame);

        JButton instructions = new JButton("Instructions");
        buttons.add(instructions);

        JButton volume = new JButton("Mute Volume");
        buttons.add(volume);

        Color customColorBlue = new Color(46, 86, 161);
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        JLabel title = new JLabel("B.A.D Idle Game");
        title.setFont(new Font("Serif", Font.BOLD, 128));
        title.setForeground(customColorBlue);
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(newGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(loadGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(instructions);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quit);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(volume);
        add(Box.createVerticalGlue());


        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(new Dimension(60, 80));
            buttons.get(i).setMaximumSize(new Dimension(600, 500));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("serif", Font.BOLD, 32));

            // Formats quit button
            if (i == 0) {
                buttons.get(0).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
                buttons.get(i).setPreferredSize(new Dimension(60, 80));
                buttons.get(i).setMaximumSize(new Dimension(400, 500));
            }

            // Formats "Mute Volume" buttomn
            if (i == 4) {
                buttons.get(4).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(4).setBackground(Color.darkGray);
                buttons.get(4).setForeground(Color.WHITE);
                buttons.get(4).setPreferredSize(new Dimension(30, 40));
                buttons.get(4).setMaximumSize(new Dimension(235, 250));
                buttons.get(4).setFont(new Font("comic sans", Font.BOLD, 25));
                
            }
        }

        // Buttons to interact with the functions of the game
        // Create new Character
        newGame.addActionListener(e -> {
            try {
                Driver.changePanel("cc");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Load a saved game
        loadGame.addActionListener(e -> {
            Driver.changePanel("load");
        });

        //View Game instructions
        instructions.addActionListener(e -> {
            Driver.changePanel("instructions");
        });

        // Quit the game
        quit.addActionListener(e -> {
            System.exit(0);
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
    }
}