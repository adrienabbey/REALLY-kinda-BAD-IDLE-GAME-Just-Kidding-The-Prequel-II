import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Dungeon extends JPanel {
    private Image backgroundImage;
    private Monster enemy;
    private PlayerCharacter player;
    private static Dice dice = new Dice(20);
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */

     @Override
     protected void paintComponent(Graphics g) {
 
         super.paintComponent(g);
             try {
                 g.drawImage(ImageIO.read(new File("assets/images/Dungeon5.png")), 0, 0, getWidth(), getHeight(), this);
             } catch (IOException e) {
                 //Auto-generated catch block
                 e.printStackTrace();
             }
     }
    public Dungeon() {
        // TODO add the actual enemy generation and combat here

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        

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
        JButton magic = new JButton("Attack Magic");
        JButton leave = new JButton("Leave Dungeon");

        // This is adding all objects to the screen, and controlling layout
        add(Box.createHorizontalGlue());
        add(start);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(magic);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(end);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(leave);
        add(Box.createHorizontalGlue());

        // control the layout of the buttons
        //run.setAlignmentX(CENTER_ALIGNMENT);
        start.addActionListener(e -> {
            Combat.startCombat();
        });
        magic.addActionListener(e -> {
            Combat.toggleMagicType();
            if(Combat.magicType == Combat.MagicType.ATTACK){
                magic.setText("Attack Magic");
            }
            else{
                magic.setText("Heal Magic");
            }
        });
        end.addActionListener(e -> {
            Combat.endCombat();
        });
        leave.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("world");
                MusicPlayer.playMusic("assets/Music/Brilliant1.wav");
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