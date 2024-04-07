import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;

class LoadScreen extends JPanel{

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

    public LoadScreen(){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        Color customColorGreen = new Color(0, 100, 0);

        JButton Save1 = new JButton("Save State 1");
        buttons.add(Save1);
        JButton Load1 = new JButton("Load State 1");
        buttons.add(Load1);
        JButton Save2 = new JButton("Save State 2");
        buttons.add(Save2);
        JButton Load2 = new JButton("Load State 2");
        buttons.add(Load2);
        JButton back = new JButton("<- Back");
        buttons.add(back);

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(Save1);
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(Load1);
        add(Box.createRigidArea(new Dimension(100, 40)));
        add(Save2);
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(Load2);
        add(Box.createRigidArea(new Dimension(100, 50)));
        add(back);
        add(Box.createVerticalGlue());

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);

            buttons.get(i).setPreferredSize(new Dimension(200, 80));
            buttons.get(i).setMaximumSize(new Dimension(200, 80));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 24));
        }

        this.setAlignmentX(CENTER_ALIGNMENT);

        Save1.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // Save player object to save file 1
                boolean saveSuccessful = Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav");
                if (saveSuccessful) {
                    Save1.setText("Saved File 1");
                    Save1.setBackground(customColorGreen);
                } else {
                    // TODO: maybe add handling for the case where the save operation failed
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Load1.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // load player from save file 1
                PlayerCharacter loadedPlayer = Driver.loadPlayer("save-files/saveFile1.sav");
                if (loadedPlayer != null) {
                    Driver.setPlayer(loadedPlayer); // Update the player instance 
                } else {
                    // TODO: Handle the case where loading the player data fails
                }
                Driver.addCharScreen();
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Save2.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // Save player object to save file 2
                boolean saveSuccessful = Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile2.sav");
                if (saveSuccessful) {
                    Save2.setText("Saved File 2");
                    Save2.setBackground(customColorGreen);
                } else {
                    // TODO: handle the case where the save operation failed
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Load2.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // load player from save file 2
                PlayerCharacter loadedPlayer = Driver.loadPlayer("save-files/saveFile2.sav");
                if (loadedPlayer != null) {
                    Driver.setPlayer(loadedPlayer); // Update the player instance 
                } else {
                    // TODO: Handle the case where loading the player data fails
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
                Save1.setText("Save File 1"); 
                Save1.setBackground(customColorBrown);
                Save2.setText("Save File 2"); 
                Save2.setBackground(customColorBrown);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}