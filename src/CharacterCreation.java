import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

class CharacterCreation extends JFrame{

    private int statPoints = 10;
    private int muscle = 0;
    private int brain = 0;
    private int heart = 0;

    public CharacterCreation(){

        JPanel create = new JPanel(); // Create panel where you create your character
        create.setLayout(new BoxLayout(create, BoxLayout.Y_AXIS));
        // TODO - Make sure this is long enough to fit all the names we want to allow
        JTextField name = new JTextField(12);
        name.setMaximumSize(name.getPreferredSize());
        JLabel points = new JLabel("You have :" + statPoints + " stat points left to spend.");
        JLabel message = new JLabel("");
        JButton submit = new JButton("Submit");
        name.setAlignmentX(CENTER_ALIGNMENT);
        points.setAlignmentX(CENTER_ALIGNMENT);
        message.setAlignmentX(CENTER_ALIGNMENT);
        submit.setAlignmentX(CENTER_ALIGNMENT);

        JPanel stats = new JPanel(); // Stats panel where you select your stats
        stats.setLayout(new GridLayout(3, 2));
        JButton weaker = new JButton("Weaker");
        JButton stronger = new JButton("Stronger");
        JButton dumber = new JButton("Dumber");
        JButton smarter = new JButton("Smarter");
        JButton softer = new JButton("Softer");
        JButton tougher = new JButton("Tougher");

        stats.add(weaker);
        stats.add(stronger);
        stats.add(dumber);
        stats.add(smarter);
        stats.add(softer);
        stats.add(tougher);

        create.add(Box.createVerticalGlue());
        create.add(name);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(points);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(message);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(stats);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(submit);
        create.add(Box.createVerticalGlue());

        weaker.addActionListener(e -> {
            if(muscle > 0){
                muscle--;
                statPoints++;
                message.setText("You have :" + statPoints + " stat points left to spend.");
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
                message.setText("You have :" + statPoints + " stat points left to spend.");
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
                message.setText("You have :" + statPoints + " stat points left to spend.");
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
                message.setText("You have :" + statPoints + " stat points left to spend.");
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
                message.setText("You have :" + statPoints + " stat points left to spend.");
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
                message.setText("You have :" + statPoints + " stat points left to spend.");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        this.getContentPane().add(create);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}