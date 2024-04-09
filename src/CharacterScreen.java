import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.Dimension;
import javax.swing.Box;
import java.util.ArrayList;

class CharacterScreen extends JPanel{
    private PlayerCharacter player;
    private JLabel health;
    private JLabel magic;
    private JLabel heart;
    private JLabel brain;
    private JLabel muscle;
    private JLabel potions;
    private JLabel gold;

    /**
     * This function controls the screen that will display character stats and gear
     */
    public CharacterScreen(){
        player = Driver.getPlayer();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        this.setBackground(customColorBrown);

        // This is creating all the objects that will be displayed on the screen
        JLabel name = new JLabel("Name: " + player.getName());
        labels.add(name);
        // JLabel level = new JLabel("Level: " + player.getLevel());
        health = new JLabel("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        labels.add(health);
        magic = new JLabel("Magic: " + player.getMagic() + "/" + player.getMaxMagic());
        labels.add(magic);
        brain = new JLabel("Brain: " + player.getBrain());
        labels.add(brain);
        muscle = new JLabel("Muscle: " + player.getMuscle());
        labels.add(muscle);
        heart = new JLabel("Heart: " + player.getHeart());
        labels.add(heart);
        potions = new JLabel("Potions: " + player.getPotionCount() + "/" + player.getPotionBeltSize());
        labels.add(potions);
        gold = new JLabel("Gold: " + player.getGold());
        labels.add(gold);

        //format the labels
        for (int i = 0; i < labels.size(); i++){
            labels.get(i).setForeground(customColorBeige);
            labels.get(i).setFont(new Font("Serif", Font.BOLD, 32));
        }

        // This is adding all objects to the screen, and controlling layout
        add(Box.createVerticalGlue());
        add(Box.createRigidArea(new Dimension(5, 0)));
        add(name);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(health);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(magic);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(brain);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(muscle);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(heart);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(potions);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(gold);
        add(Box.createVerticalGlue());
    }

    public void update(){
        health.setText(String.format("Health: %.1f/%.1f", (float)player.getHealth(), (float)player.getMaxHealth()));
        magic.setText(String.format("Magic: %.1f/%.1f", (float)player.getMagic(), (float)player.getMaxMagic()));
        brain.setText("Brain: " + player.getBrain());
        muscle.setText("Muscle: " + player.getMuscle());
        heart.setText("Heart: " + player.getHeart());
        potions.setText(String.format("Potions: %.1f/%.1f", (float)player.getPotionCount(), (float)player.getPotionBeltSize()));
        gold.setText("Gold: " + player.getGold());
        this.repaint();
    }
}