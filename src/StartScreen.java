import java.awt.Color;
import java.awt.Dimension;
<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> d0fbe75c7cdee48083d21238def60671ae045c1d
import java.awt.Font;


import javax.swing.*;
// TODO Narrow the swing down to what we actually need

class StartScreen extends JFrame{
    public StartScreen(){
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

        newGame.addActionListener(e -> {
            try {
                new CharacterCreation();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
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

        this.getContentPane().add(start);
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}