import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class CharacterCreation extends JFrame{

    private int statPoints = 10;
    private int muscle = 0;
    private int brain = 0;
    private int heart = 0;

    public CharacterCreation() throws IOException{
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        // MyFrame frame = new MyFrame();

        JPanel create = new JPanel(); // Create panel where you create your character
        create.setLayout(new BoxLayout(create, BoxLayout.Y_AXIS));
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
        muscleLabel.setAlignmentX(RIGHT_ALIGNMENT);
        JButton dumber = new JButton("Dumber");
        JButton smarter = new JButton("Smarter");
        JLabel brainLabel = new JLabel("Brain: " + brain);
        brainLabel.setAlignmentX(RIGHT_ALIGNMENT);
        JButton softer = new JButton("Softer");
        JButton tougher = new JButton("Tougher");
        JLabel heartLabel = new JLabel("Heart: " + heart);
        heartLabel.setAlignmentX(RIGHT_ALIGNMENT);

        stats.add(weaker);
        stats.add(stronger);
        stats.add(muscleLabel);
        stats.add(dumber);
        stats.add(smarter);
        stats.add(brainLabel);
        stats.add(softer);
        stats.add(tougher);
        stats.add(heartLabel);

        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(pic);
        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(stats);
        charPanel.add(Box.createHorizontalGlue());

        create.add(Box.createVerticalGlue());
        create.add(name);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(points);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(message);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(charPanel);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(submit);
        create.add(Box.createVerticalGlue());

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
        submit.addActionListener(e -> {
            PlayerCharacter player = new PlayerCharacter(name.getText(), muscle, brain, heart, 10*statPoints, 1, 0);
            try {
                new World(player);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        });

        this.getContentPane().add(create);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        device.setFullScreenWindow(this);
    }
}