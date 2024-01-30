import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.util.ArrayList;
// TODO Narrow the swing down to what we actually need


// This class is just the intro starting screen with the buttons to start a new game, load a game, read directions, or quit
class StartScreen extends JPanel{
    /**
     * This function hosts the start menu with buttons to start a new game, load a game, read directions, or quit
     */ 
    public StartScreen(){

        // This block of code is all the physical gui elements and their properties
        // TODO - Add a info button or something to explain the game
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Icon newGameIcon = new ImageIcon("assets/images/newGame.PNG");
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton quit = new JButton("Quit");
        buttons.add(quit);
        JButton newGame = new JButton("New Game");
        buttons.add(newGame);
        newGame.setFont(new Font("Dialog", Font.BOLD, 12));
        JButton loadGame = new JButton("Load Game");
        buttons.add(loadGame);

        JButton instructions = new JButton("Instructions");
        buttons.add(instructions);

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(newGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(loadGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(instructions);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quit);
        add(Box.createVerticalGlue());


        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setBackground(Color.WHITE);
            buttons.get(i).setForeground(Color.RED);

            if (i == 0) {
                buttons.get(0).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
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
    }
}