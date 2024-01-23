import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Font;

import javax.swing.*;
// TODO Narrow the swing down to what we actually need


// This class is just the intro starting screen with the buttons to start a new game, load a game, read directions, or quit
class StartScreen extends JFrame{
    public StartScreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        // This block of code is all the physical gui elements and their properties
        // TODO - Add a info button or something to explain the game
        JPanel start = new JPanel(); // Start panel where you select new game, load, or quit
        start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));
        //Icon newGameIcon = new ImageIcon("assets/images/newGame.PNG");
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Dialog", Font.BOLD, 12));
        newGame.setBackground(Color.WHITE);
        newGame.setForeground(Color.RED);
        JButton loadGame = new JButton("Load Game");
        JButton quit = new JButton("Quit");

        // Adding the buttons to the start panel and controlling layout
        start.add(Box.createVerticalGlue());
        start.add(newGame);
        start.add(Box.createRigidArea(new Dimension(0, 10)));
        start.add(loadGame);
        start.add(Box.createRigidArea(new Dimension(0, 10)));
        start.add(quit);
        start.add(Box.createVerticalGlue());

        newGame.setAlignmentX(CENTER_ALIGNMENT);
        loadGame.setAlignmentX(CENTER_ALIGNMENT);
        quit.setAlignmentX(CENTER_ALIGNMENT);

        // Buttons to interact with the functions of the game
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
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Properties of the screen itself, will soon be moved to a separate manager class
        this.getContentPane().add(start);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}