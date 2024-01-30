import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.Box;

class CharacterScreen extends JPanel{

    /**
     * This function controls the screen that will display character stats and gear
     */
    public CharacterScreen(){
        PlayerCharacter player = Driver.getPlayer();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // This is creating all the objects that will be displayed on the screen
        JLabel name = new JLabel("Name: " + player.getName());
        // JLabel level = new JLabel("Level: " + player.getLevel());
        JLabel health = new JLabel("Health: " + player.getHealth() + "/" + player.getMaxHealth());
        JLabel magic = new JLabel("Magic: " + player.getMagic() + "/" + player.getMaxMagic());
        JLabel potions = new JLabel("Potions: " + player.getPotionCount() + "/" + player.getPotionBeltSize());
        JLabel gold = new JLabel("Gold: " + player.getGold());

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
}