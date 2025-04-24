
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


//
//Class houses UI components for buttons on right side of screen and manages these buttons: start/stop combat, attack/heal magic, Use Potion, and Leave Dungeon. 
//Also houses the functions for each button.
//
//Manages what monster appears. 

class Dungeon extends JPanel {
   
//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 82;
    final private Color customColorBeige = new Color(253, 236, 166);
    final private Color customColorBrown = new Color(102, 72, 54);
    final private Border actionBorder = BorderFactory.createLineBorder(customColorBeige, 1);

    private static Dice dice = new Dice(20);

     @Override
     protected void paintComponent(Graphics g) {
 
         super.paintComponent(g);
             try {
                 g.drawImage(ImageIO.read(new File("assets/images/Dungeon4.png")), 0, 0, getWidth(), getHeight(), this);
             } catch (IOException e) {
                 //Auto-generated catch block
                 e.printStackTrace();
             }
     }


//========================================================
// Constructor
//========================================================
    public Dungeon() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        // This is creating all the objects that will be displayed on the screen
        // Button to start combat, button to end combat, and seperate button to leave dungeon
        // Player flag to indicate activity the player is engaged in
        // This would allow "idle" play of one content at a time
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton start = new JButton("Start Combat");
        buttons.add(start);
        JButton magic = new JButton("Attack Magic");
        buttons.add(magic);
        JButton potion = new JButton("Use Potion");
        buttons.add(potion);
        JButton leave = new JButton("Leave Dungeon");
        buttons.add(leave);

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            Dimension buttonSize = new Dimension(
                ((int) buttons.get(i).getPreferredSize().getWidth() * 16 / 10), 
                ((int)buttons.get(i).getPreferredSize().getHeight() * 16 / 10));
            buttons.get(i).setMaximumSize(buttonSize);
            buttons.get(i).setPreferredSize(buttonSize);
            buttons.get(i).setBorder(actionBorder);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
        }

        // This is adding all objects to the screen, and controlling layout
        add(Box.createHorizontalGlue());
        add(start);
        add(Box.createRigidArea(new Dimension(width / 60, height / 50)));
        add(magic);
        add(Box.createRigidArea(new Dimension(width / 60, height / 50)));
        add(potion);
        add(Box.createRigidArea(new Dimension(width / 60, height / 50)));
        add(leave);
        add(Box.createHorizontalGlue());

        // control the layout of the buttons
        start.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            Combat.startCombat();
        });
        magic.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            Combat.toggleMagicType();
            if(Combat.magicType == Combat.MagicType.ATTACK){
                magic.setText("Attack Magic");
            }
            else{
                magic.setText("Heal Magic");
            }
        });
        potion.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.getPlayer().drinkPotion();
        });
        leave.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Combat.endCombat();
                Driver.changePanel("world");
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
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