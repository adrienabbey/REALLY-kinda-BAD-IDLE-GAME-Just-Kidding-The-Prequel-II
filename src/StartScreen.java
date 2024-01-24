import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import javax.swing.*;
// TODO Narrow the swing down to what we actually need


// This class is just the intro starting screen with the buttons to start a new game, load a game, read directions, or quit
class StartScreen extends JPanel{
    /**
     * This function hosts the start menu with buttons to start a new game, load a game, read directions, or quit
     */ 
    public StartScreen(){
        // GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // GraphicsDevice device = env.getDefaultScreenDevice();

        // This block of code is all the physical gui elements and their properties
        // TODO - Add a info button or something to explain the game
        //JPanel start = new JPanel(); // Start panel where you select new game, load, or quit
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Icon newGameIcon = new ImageIcon("assets/images/newGame.PNG");
        JButton newGame = new JButton("New Game");
        newGame.setFont(new Font("Dialog", Font.BOLD, 12));
        newGame.setBackground(Color.WHITE);
        newGame.setForeground(Color.RED);
        JButton loadGame = new JButton("Load Game");
        JButton quit = new JButton("Quit");

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(newGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(loadGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quit);
        add(Box.createVerticalGlue());

        newGame.setAlignmentX(CENTER_ALIGNMENT);
        loadGame.setAlignmentX(CENTER_ALIGNMENT);
        quit.setAlignmentX(CENTER_ALIGNMENT);

        // Buttons to interact with the functions of the game

        // Create new Character
        newGame.addActionListener(e -> {
            try {
                //JPanel cc = new CharacterCreation();
                Driver.changePanel("cc");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            //this.dispose();
        });

        // Load a saved game
        loadGame.addActionListener(e -> {
            new LoadScreen();
            // this.dispose();
        });

        // Quit the game
        quit.addActionListener(e -> {
            System.exit(0);
        });

        // Properties of the screen itself, will soon be moved to a separate manager class
        // this.getContentPane().add(start);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(false);
        // this.setVisible(true);
        // device.setFullScreenWindow(this);
    }
}