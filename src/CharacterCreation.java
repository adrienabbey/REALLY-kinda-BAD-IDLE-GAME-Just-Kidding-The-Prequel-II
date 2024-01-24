import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This class manages the screen for creating a new character
class CharacterCreation extends JPanel{

    private int statPoints = 10;
    private int muscle = 0;
    private int brain = 0;
    private int heart = 0;

    /**
     * This function hosts the character creation screen
     * It lets you assign stats, name your character, and then instantiates a PlayerCharacter object and launches to the map
     * @throws IOException
     */
    public CharacterCreation() throws IOException{
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        // This is all the physical gui elements and their properties
        // JPanel create = new JPanel(); // Create panel where you create your character
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // TODO - Make sure this is long enough to fit all the names we want to allow
        JTextField name = new JTextField(12);
        name.setMaximumSize(name.getPreferredSize());
        JLabel points = new JLabel("You have: " + statPoints + " stat points left to spend.");
        JLabel message = new JLabel(" ");
        JButton submit = new JButton("Submit");
        name.setAlignmentX(CENTER_ALIGNMENT);
        points.setAlignmentX(CENTER_ALIGNMENT);
        message.setAlignmentX(CENTER_ALIGNMENT);
        submit.setAlignmentX(CENTER_ALIGNMENT);

        JPanel charPanel = new JPanel(); // Character panel where you see and set stats for your character
        charPanel.setLayout(new BoxLayout(charPanel, BoxLayout.X_AXIS));
        BufferedImage image = ImageIO.read(new File("assets/images/character.jpg"));
        JLabel pic = new JLabel(new ImageIcon(image));

        JPanel stats = new JPanel(); // Stats panel where you select your stats
        stats.setLayout(new GridLayout(3, 3));
        JButton weaker = new JButton("Weaker");
        JButton stronger = new JButton("Stronger");
        JLabel muscleLabel = new JLabel("Muscle: " + muscle);
        JButton dumber = new JButton("Dumber");
        JButton smarter = new JButton("Smarter");
        JLabel brainLabel = new JLabel("Brain: " + brain);
        JButton softer = new JButton("Softer");
        JButton tougher = new JButton("Tougher");
        JLabel heartLabel = new JLabel("Heart: " + heart);

        // This section adds the components and controls layout
        stats.add(weaker);
        stats.add(stronger);
        stats.add(muscleLabel);
        stats.add(dumber);
        stats.add(smarter);
        stats.add(brainLabel);
        stats.add(softer);
        stats.add(tougher);
        stats.add(heartLabel);

        muscleLabel.setAlignmentX(RIGHT_ALIGNMENT);
        brainLabel.setAlignmentX(RIGHT_ALIGNMENT);
        heartLabel.setAlignmentX(RIGHT_ALIGNMENT);

        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(pic);
        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(stats);
        charPanel.add(Box.createHorizontalGlue());

        add(Box.createVerticalGlue());
        add(name);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(points);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(message);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(charPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(submit);
        add(Box.createVerticalGlue());

        name.setAlignmentX(CENTER_ALIGNMENT);
        points.setAlignmentX(CENTER_ALIGNMENT);
        message.setAlignmentX(CENTER_ALIGNMENT);
        submit.setAlignmentX(CENTER_ALIGNMENT);

        // This section allows the buttons to interact with the functions of the game

        // Reduce Strength
        weaker.addActionListener(e -> {
            if(muscle > 0){
                muscle--;
                statPoints++;
                points.setText("You have :" + statPoints + " stat points left to spend.");
                muscleLabel.setText("Muscle: " + muscle);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You cannot go below 0 in any stat.");
                this.repaint();
            }
        });

        // Increase Strength
        stronger.addActionListener(e -> {
            if(statPoints > 0){
                muscle++;
                statPoints--;
                points.setText("You have :" + statPoints + " stat points left to spend.");
                muscleLabel.setText("Muscle: " + muscle);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        // Reduce Intelligence
        dumber.addActionListener(e -> {
            if(brain > 0){
                brain--;
                statPoints++;
                points.setText("You have :" + statPoints + " stat points left to spend.");
                brainLabel.setText("Brain: " + brain);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You cannot go below 0 in any stat.");
                this.repaint();
            }
        });

        // Increase Intelligence
        smarter.addActionListener(e -> {
            if(statPoints > 0){
                brain++;
                statPoints--;
                points.setText("You have :" + statPoints + " stat points left to spend.");
                brainLabel.setText("Brain: " + brain);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        // Reduce Constitution
        softer.addActionListener(e -> {
            if(heart > 0){
                heart--;
                statPoints++;
                points.setText("You have :" + statPoints + " stat points left to spend.");
                heartLabel.setText("Heart: " + heart);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You cannot go below 0 in any stat.");
                this.repaint();
            }
        });

        // Increase Constitution
        tougher.addActionListener(e -> {
            if(statPoints > 0){
                heart++;
                statPoints--;
                points.setText("You have :" + statPoints + " stat points left to spend.");
                heartLabel.setText("Heart: " + heart);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        // Submit character
        submit.addActionListener(e -> {
            PlayerCharacter player = new PlayerCharacter(name.getText(), muscle, brain, heart, 10*statPoints, 1, 0);
            try {
                // JPanel world = new World();
                Driver.setPlayer(player);
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            //this.dispose();
        });

        // Properties of the screen itself, will soon be moved to a separate manager class
        // this.getContentPane().add(create);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(false);
        // this.setVisible(true);
        // device.setFullScreenWindow(this);
    }
}