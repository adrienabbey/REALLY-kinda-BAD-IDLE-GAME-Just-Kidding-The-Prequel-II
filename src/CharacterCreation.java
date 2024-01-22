import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

class CharacterCreation extends JFrame{
    public CharacterCreation(){
        int statPoints = 10;
        JPanel create = new JPanel(); // Create panel where you create your character
        create.setLayout(new BoxLayout(create, BoxLayout.Y_AXIS));
        // TODO - Make sure this is long enough to fit all the names we want to allow
        JTextField name = new JTextField(12);
        name.setMaximumSize(name.getPreferredSize());
        JLabel message = new JLabel("You have :" + statPoints + " stat points left to spend.");
        name.setAlignmentX(CENTER_ALIGNMENT);
        message.setAlignmentX(CENTER_ALIGNMENT);

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
        create.add(message);
        create.add(Box.createRigidArea(new Dimension(0, 10)));
        create.add(stats);
        create.add(Box.createVerticalGlue());

        this.getContentPane().add(create);
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}