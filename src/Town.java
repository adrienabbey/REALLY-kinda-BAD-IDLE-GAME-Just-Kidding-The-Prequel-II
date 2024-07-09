import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

class Town extends JPanel {

//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 70;
    final private Color customColorBeige = new Color(253, 236, 166);
    final private Color customColorBrown = new Color(102, 72, 54);

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("assets/images/town3.png")), 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This function hosts the town screen with buttons to buy potions or leave
     * 
     * @param player The player character object
     * @throws IOException
     */
    public Town() throws IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
      
        JButton bazaar = new JButton("💰 Bazaar");
        buttons.add(bazaar);
        JButton leave = new JButton("← Leave");
        buttons.add(leave);
        JButton tavern = new JButton("🍺 Tavern");
        buttons.add(tavern);
        JButton library = new JButton("📚 Library");
        buttons.add(library);
        JButton inventory = new JButton("👜 Inventory");
        buttons.add(inventory);

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(bazaar);
        add(Box.createRigidArea(new Dimension(100, buttonFont * 2)));
        add(tavern);
        add(Box.createRigidArea(new Dimension(100, buttonFont * 2)));
        add(library);
        add(Box.createRigidArea(new Dimension(100, buttonFont * 2)));
        add(inventory);
        add(Box.createRigidArea(new Dimension(100, buttonFont * 2)));
        add(leave);
        add(Box.createVerticalGlue());

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(new Dimension(width / 8, height / 18));
            buttons.get(i).setMaximumSize(new Dimension(width / 8, height / 18));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setBorder(Driver.buttonBorder);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
        }

        this.setAlignmentX(CENTER_ALIGNMENT);


//========================================================
// Action Listeners
//========================================================
        // Button that takes player to bazaar panel
        bazaar.addActionListener(e -> {
            try{
                SFX.stopAllSounds();
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("bazaar");
                MusicPlayer.playMusic("assets/Music/Turku, Nomads of the Silk Road - -Uskudara Gideriken.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Button that takes player to tavern panel
        tavern.addActionListener(e -> {
            try{
                SFX.stopAllSounds();
                SFX.playSound("assets/SFX/interface1.wav");
                SFX.playSound("assets/SFX/door-open.wav");
                Driver.changePanel("tavern");
                MusicPlayer.playMusic("assets/Music/alexander-nakarada-tavern-loop-one.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Button that takes player to library panel
        library.addActionListener(e -> {
            try{
                SFX.stopAllSounds();
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("library");
                MusicPlayer.playMusic("assets/Music/Heaven_-_David_Fesliyan.wav");
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

        // Button that takes player to inventory panel
        inventory.addActionListener(e -> {
            try {
                Driver.player.inventory.backToTown = true;
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("inventory");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Button that takes player to world panel
        leave.addActionListener(e -> {
            try{
                SFX.stopAllSounds();
                Driver.player.inventory.backToTown = false;
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("world");
                MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
                SFX.stopAllSounds();
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }
}