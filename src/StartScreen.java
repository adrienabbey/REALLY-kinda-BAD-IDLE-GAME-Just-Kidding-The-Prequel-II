import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
//import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Font;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
// TODO Narrow the swing down to what we actually need

class StartScreen extends JFrame{
    public StartScreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        // TODO - Add a info button or something to explain the game
        JPanel start = new JPanel(); // Start panel where you select new game, load, or quit
        start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));
        Image iconImage = new ImageIcon("/assets/images/windowIcon.png").getImage();

        //ArrayList for all the buttons to make changing them less repetative
        ArrayList<JButton> buttonsArr = new ArrayList<JButton>();
        JButton newGame = new JButton("New Game");
        buttonsArr.add(newGame);
        JButton loadGame = new JButton("Load Game");
        buttonsArr.add(loadGame);
        JButton gameInstructions = new JButton("Instructions");
        buttonsArr.add(gameInstructions);
        JButton quit = new JButton("Quit");
        //Quit is Visually different so it is not added into the array
        quit.setForeground(Color.WHITE);
        quit.setBackground(Color.RED);

        //Set styling for each button
        for(int i = 0; i < buttonsArr.size(); i++){
            buttonsArr.get(i).setFont(new Font("Dialog", Font.BOLD, 12));
            buttonsArr.get(i).setBackground(Color.WHITE);
            buttonsArr.get(i).setForeground(Color.RED);
        }

        start.add(Box.createVerticalGlue());
        start.add(newGame);
        start.add(Box.createRigidArea(new Dimension(0, 10)));
        start.add(loadGame);
        start.add(Box.createRigidArea(new Dimension(0, 10)));
        start.add(gameInstructions);
        start.add(Box.createRigidArea(new Dimension(0, 10)));
        start.add(quit);
        start.add(Box.createVerticalGlue());

        newGame.setAlignmentX(CENTER_ALIGNMENT);
        loadGame.setAlignmentX(CENTER_ALIGNMENT);
        gameInstructions.setAlignmentX(CENTER_ALIGNMENT);
        quit.setAlignmentX(CENTER_ALIGNMENT);

        newGame.addActionListener(e -> {
            try {
                new CharacterCreation();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });
        loadGame.addActionListener(e -> {
            new LoadScreen();
            this.dispose();
        });
        gameInstructions.addActionListener(e -> {
            new GameInstructions();
            this.dispose();
        });
        quit.addActionListener(e -> {
            System.exit(0);
        });

        this.getContentPane().add(start);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}