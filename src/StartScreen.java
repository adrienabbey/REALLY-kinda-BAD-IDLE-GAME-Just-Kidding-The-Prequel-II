import java.awt.Dimension;
import java.io.IOException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
// TODO Narrow the swing down to what we actually need

class StartScreen extends JFrame{
    public StartScreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        // TODO - Add a info button or something to explain the game
        JPanel start = new JPanel(); // Start panel where you select new game, load, or quit
        start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));
        JButton newGame = new JButton("New Game");
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}