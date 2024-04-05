import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Dungeon extends JPanel {
    private static Dice dice = new Dice(20);
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */

     @Override
     protected void paintComponent(Graphics g) {
 
         super.paintComponent(g);
             try {
                 g.drawImage(ImageIO.read(new File("assets/images/dungeon4.png")), 0, 0, getWidth(), getHeight(), this);
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
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        JButton start = new JButton("Start Combat");
        buttons.add(start);
        JButton end = new JButton("End Combat");
        buttons.add(end);
        JButton magic = new JButton("Attack Magic");
        buttons.add(magic);
        JButton potion = new JButton("Use Potion");
        buttons.add(potion);
        JButton leave = new JButton("Leave Dungeon");
        buttons.add(leave);

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setPreferredSize(new Dimension(170, 80));
            buttons.get(i).setMaximumSize(new Dimension(170, 80));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 20));
        }

        // This is adding all objects to the screen, and controlling layout
        add(Box.createHorizontalGlue());
        add(start);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(magic);
        add(Box.createRigidArea(new Dimension(20, 20)));
        add(potion);
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
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
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