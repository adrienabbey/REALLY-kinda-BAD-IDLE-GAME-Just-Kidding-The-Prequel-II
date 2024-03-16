import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

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

        // This is all the physical gui elements and their properties
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        // TODO - Make sure this is long enough to fit all the names we want to allow
        JLabel nameLabel = new JLabel("Name");
        labels.add(nameLabel);
        JTextField name = new JTextField(12);
        name.setMaximumSize(name.getPreferredSize());
        JLabel points = new JLabel("You have: " + statPoints + " stat points left to spend.");
        labels.add(points);
        JLabel message = new JLabel(" ");
        labels.add(message);
        JButton submit = new JButton("Submit");
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        name.setAlignmentX(CENTER_ALIGNMENT);
        points.setAlignmentX(CENTER_ALIGNMENT);
        message.setAlignmentX(CENTER_ALIGNMENT);
        submit.setAlignmentX(CENTER_ALIGNMENT);

        JPanel charPanel = new JPanel(); // Character panel where you see and set stats for your character
        charPanel.setLayout(new BoxLayout(charPanel, BoxLayout.X_AXIS));
        BufferedImage image = ImageIO.read(new File("assets/images/character12.png"));
        JLabel pic = new JLabel(new ImageIcon(image));

        JPanel stats = new JPanel(); // Stats panel where you select your stats
        stats.setLayout(new GridLayout(3, 3, 10, 10));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        Color customColorBlue = new Color(46, 86, 161);
        JButton weaker = new JButton("Weaker");
        buttons.add(weaker);
        JButton stronger = new JButton("Stronger");
        buttons.add(stronger);
        JLabel muscleLabel = new JLabel("Muscle: " + muscle);
        labels.add(muscleLabel);
        JButton dumber = new JButton("Dumber");
        buttons.add(dumber);
        JButton smarter = new JButton("Smarter");
        buttons.add(smarter);
        JLabel brainLabel = new JLabel("Brain: " + brain);
        labels.add(brainLabel);
        JButton softer = new JButton("Softer");
        buttons.add(softer);
        JButton tougher = new JButton("Tougher");
        buttons.add(tougher);
        JLabel heartLabel = new JLabel("Heart: " + heart);
        labels.add(heartLabel);

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

        //format buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);

            buttons.get(i).setPreferredSize(new Dimension(200, 40));
            buttons.get(i).setMaximumSize(new Dimension(200, 40));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));
        }

        //submit button formating
        submit.setPreferredSize(new Dimension(200, 80));
        submit.setMaximumSize(new Dimension(200, 80));
        submit.setBackground(customColorBlue);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Serif", Font.BOLD, 24));

        //Label formating
        for (int i = 0; i < labels.size(); i++){
            labels.get(i).setForeground(customColorBrown);
            labels.get(i).setFont(new Font("Serif", Font.BOLD, 18));
        }


        muscleLabel.setAlignmentX(RIGHT_ALIGNMENT);
        brainLabel.setAlignmentX(RIGHT_ALIGNMENT);
        heartLabel.setAlignmentX(RIGHT_ALIGNMENT);

        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(pic);
        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(stats);
        charPanel.add(Box.createHorizontalGlue());
        charPanel.add(submit);

        add(Box.createVerticalGlue());
        add(nameLabel);
        add(name);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(points);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(message);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(charPanel);
        add(Box.createRigidArea(new Dimension(0, 35)));
        add(Box.createVerticalGlue());

        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
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
                Driver.setPlayer(player);
                Driver.addCharScreen();
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}