import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;

class CharacterScreen extends JPanel{
    private PlayerCharacter player;
    private JLabel health;
    private JLabel magic;
    private JLabel potions;
    private JLabel gold;

    /**
     * This function controls the screen that will display character stats and gear
     */
    public CharacterScreen(){
        player = Driver.getPlayer();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // This is creating all the objects that will be displayed on the screen
        JLabel name = new JLabel("Name: " + player.getName());
        // JLabel level = new JLabel("Level: " + player.getLevel());
        health = new JLabel("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        magic = new JLabel("Magic: " + player.getMagic() + "/" + player.getMaxMagic());
        potions = new JLabel("Potions: " + player.getPotionCount() + "/" + player.getPotionBeltSize());
        gold = new JLabel("Gold: " + player.getGold());

        // This is adding all objects to the screen, and controlling layout
        add(Box.createVerticalGlue());
        add(name);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(health);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(magic);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(potions);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(gold);
        add(Box.createVerticalGlue());
    }

    public void update(){
        health.setText(String.format("Health: %.1f/%.1f", (float)player.getHealth(), (float)player.getMaxHealth()));
        magic.setText(String.format("Magic: %.1f/%.1f", (float)player.getMagic(), (float)player.getMaxMagic()));
        potions.setText(String.format("Potions: %.1f/%.1f", (float)player.getPotionCount(), (float)player.getPotionBeltSize()));
        gold.setText("Gold: " + player.getGold());
        this.repaint();
    }
}