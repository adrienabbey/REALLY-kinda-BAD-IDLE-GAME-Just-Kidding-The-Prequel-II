import java.awt.Dimension;

import javax.swing.*;

class Dungeon extends JPanel {
    private Monster enemy;
    private PlayerCharacter player;
    private static Dice dice = new Dice(20);
    private Combat combat;
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */
    public Dungeon() {

        // TODO add the actual enemy generation and combat here
        enemy = getMonster();
        player = Driver.getPlayer();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // This is creating all the objects that will be displayed on the screen
        // Need something to explicitly start combat
        // Make entry to dungeon initiate?
        // Should leaving the dungeon stop combat
        // Or should the player be able to continue combat while at another screen
        // Button to start combat, button to end combat, and seperate button to leave dungeon
        // Player flag to indicate activity the player is engaged in
        // This would allow "idle" play of one content at a time
        JButton start = new JButton("Start Combat");
        JButton end = new JButton("End Combat");
        JButton leave = new JButton("Leave Dungeon");

        // This is adding all objects to the screen, and controlling layout
        add(Box.createVerticalGlue());
        add(start);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(end);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(leave);
        add(Box.createVerticalGlue());

        // control the layout of the buttons
        //run.setAlignmentX(CENTER_ALIGNMENT);

        // This button will be used to run away from combat
        start.addActionListener(e -> {
            combat = new Combat(player, enemy);
        });
        end.addActionListener(e -> {
            combat = null;
        });
        leave.addActionListener(e -> {
            try {
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void update(){
        this.repaint();
    }

    public static Monster getMonster(){
        if(dice.roll() > 10){
            return new Monster(Monster.MonsterName.PETROCK);
        }
        else{
            return new Monster(Monster.MonsterName.HOBOGOBLIN);
        }
    }
}