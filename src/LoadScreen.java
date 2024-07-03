import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;

class LoadScreen extends JPanel{

        // These are used for formating the gui elements
        final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final private int width = screenSize.width;
        final private int height = screenSize.height;

        final private Dimension PANEL_SIZE = new Dimension(width * 80 / 100, height / 17); // width and hight of save-detail-panel and save-button. 
        final private int xCord = (width - (PANEL_SIZE.width)) / 2; //used for positioning, centers component to screen
        final private int yCord = 2 * (height / 7); 
        final private Dimension BACK_SIZE = new Dimension(width / 10, height / 10); 
        

        final private Dimension BUTTON_SIZE = new Dimension(width / 8, height / 16);
        final private Dimension BUTTON_GAP = new Dimension(0, height / 100);
        final private int labelFont = width / 78;
        final private int buttonFont = width / 78;
        final private Dimension LABEL_SIZE = new Dimension(width / 8, height / 16); 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/World8.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

//===============================================================
// Constructor
//===============================================================s
    public LoadScreen(){
        this.setLayout(null);
        // this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        Color customColorGreen = new Color(0, 100, 0);
        Color customColorTan = new Color(139, 69, 19);

        JButton save1 = new JButton("Save State 1 - None");
        buttons.add(save1);
        JButton load1 = new JButton("Load State 1");
        buttons.add(load1);
        JButton save2 = new JButton("Save State 2 - None");
        buttons.add(save2);
        JButton load2 = new JButton("Load State 2");
        buttons.add(load2);
        JButton back = new JButton("<- Back");
        buttons.add(back);

        // // Adding the buttons to the start panel and controlling layout
        // add(Box.createVerticalGlue());
        // add(Box.createRigidArea(new Dimension(100, 0)));
        // add(save1);
        // add(Box.createRigidArea(new Dimension(100, 0)));
        // add(load1);
        // add(Box.createRigidArea(new Dimension(100, 40)));
        // add(save2);
        // add(Box.createRigidArea(new Dimension(100, 0)));
        // add(load2);
        // add(Box.createRigidArea(new Dimension(100, 50)));
        // add(back);
        // add(Box.createVerticalGlue());

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setMaximumSize(new Dimension(BACK_SIZE.width, BACK_SIZE.height));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));
        }
        back.setBackground(customColorTan);

        // this.setAlignmentX(CENTER_ALIGNMENT);

        
        //===============================================================================
        //
        // Relatively scaling and sizing components
        //
        //
        //
        JPanel buttonPanelSave1= new JPanel(new GridLayout()); // components for save save 1
        JPanel buttonPanelsave2= new JPanel(new GridLayout()); // components for save save 2

        // Sizing for Save File 1 components and panel
        save1.setBounds(xCord, (yCord - PANEL_SIZE.height), PANEL_SIZE.width, PANEL_SIZE.height); 
        buttonPanelSave1.setBounds(xCord, yCord, PANEL_SIZE.width, PANEL_SIZE.height); 

        // Sizing for Save File 2 components and panel
        save2.setBounds(xCord, ((2 * yCord) - PANEL_SIZE.height), PANEL_SIZE.width, PANEL_SIZE.height); 
        buttonPanelsave2.setBounds(xCord, (2 * yCord), PANEL_SIZE.width, PANEL_SIZE.height);

        back.setBounds((width - BACK_SIZE.width) / 99, (height - BACK_SIZE.height) / 99, back.getPreferredSize().width, back.getPreferredSize().height); // formatting back button, x-cord, y-cord, width, and height
        //================================================================================
        buttonPanelSave1.add(load1);
        buttonPanelsave2.add(load2);
        add(save1);
        add(buttonPanelSave1);
        add(save2);
        add(buttonPanelsave2);
        add(back);


//==================================================================
// Methods for action listeners
//==================================================================

        save1.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // Save player object to save file 1
                boolean saveSuccessful = Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav");
                if (saveSuccessful) {
                    save1.setText("Saved File 1");
                    save1.setBackground(customColorGreen);
                } else {
                    System.out.println("Error: Could not save to file 1.");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        load1.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // load player from save file 1
                PlayerCharacter loadedPlayer = Driver.loadPlayer("save-files/saveFile1.sav");
                if (loadedPlayer != null) {
                    Driver.setPlayer(loadedPlayer); // Update the player instance 
                } else {
                    System.out.println("Error: Could not load save-file 1.");
                }
                Driver.addCharScreen();
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        save2.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // Save player object to save file 2
                boolean saveSuccessful = Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile2.sav");
                if (saveSuccessful) {
                    save2.setText("Saved File 2");
                    save2.setBackground(customColorGreen);
                } else {
                    System.out.println("Error: Could not save to file 2.");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        load2.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // load player from save file 2
                PlayerCharacter loadedPlayer = Driver.loadPlayer("save-files/saveFile2.sav");
                if (loadedPlayer != null) {
                    Driver.setPlayer(loadedPlayer); // Update the player instance 
                } else {
                    System.out.println("Error: Could not load save-file 2.");
                }
                Driver.addCharScreen();
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("start");

                // reset save button texts and color to default after leaving 
                save1.setText("Save File 1"); 
                save1.setBackground(customColorBrown);
                save2.setText("Save File 2"); 
                save2.setBackground(customColorBrown);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}