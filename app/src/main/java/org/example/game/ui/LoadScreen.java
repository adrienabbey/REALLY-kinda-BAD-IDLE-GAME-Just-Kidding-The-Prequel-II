import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;

class LoadScreen extends JPanel{

//===============================================================
// Constructor
//===============================================================
    /*------------------------
    *These variables are used for formating the GUI elements.
    *
    */
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;

    final private Dimension PANEL_SIZE = new Dimension(width * 80 / 100, height / 17); // width and hight of save-detail-panel and save-button. 
    final private int xCord = (width - (PANEL_SIZE.width)) / 2; //used for positioning, centers component to screen
    final private int yCord = 2 * (height / 7); 
    final private Dimension BACK_SIZE = new Dimension(width / 10, height / 10); 
    final private int buttonFont = width / 106;
    /*------------------------*/


    // variables used to set save-file date time
    private LocalDateTime currentDateTime; //get the current date and time
    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a MM/dd/yyy"); // Format the current date and time 
    private String formattedDateTime;

//===============================================================
// Constructor
//===============================================================
    public LoadScreen(){
        this.setLayout(null);
        // this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        Color customColorGreen = new Color(0, 100, 0);
        Color customColorTan = new Color(139, 69, 19);

        //
        //
        // Initializing buttons and progress bars for save file components
        //
        //
    //==============================================================
    // save file 1
    //==============================================================
        JButton save1 = new JButton("Save Game 1 - None - (This save state has automatic saving)");
        buttons.add(save1);
        JButton characterName1 = new JButton("Charactername");
        buttons.add(characterName1);
        JButton fileDate1 = new JButton("-/-/-");
        buttons.add(fileDate1);
        JButton gold1 = new JButton("Gold: ");
        buttons.add(gold1);
        JProgressBar health1 = new JProgressBar(0);
        health1.setValue(75); // Example health value
        health1.setStringPainted(true); // Enable string painting
        health1.setString("Health: " + health1.getValue()); // Set initial string
        JProgressBar mana1 = new JProgressBar(0);
        mana1.setValue(90);
        mana1.setStringPainted(true); // Enable string painting
        mana1.setString("Mana: " + mana1.getValue()); // Set initial string
        JButton delete1 = new JButton("Delete File");
        buttons.add(delete1);
        JButton load1 = new JButton("Load Game 1");
        buttons.add(load1);

    //==============================================================
    // save file 2
    //==============================================================
        JButton save2 = new JButton("Save State 2 - None");
        buttons.add(save2);
        JButton characterName2 = new JButton("Charactername");
        buttons.add(characterName2);
        JButton fileDate2 = new JButton("-/-/-");
        buttons.add(fileDate2);
        JButton gold2 = new JButton("Gold: ");
        buttons.add(gold2);
        JProgressBar health2 = new JProgressBar(0);
        JProgressBar mana2 = new JProgressBar(0);
        JButton delete2 = new JButton("Delete File");
        buttons.add(delete2);
        JButton load2 = new JButton("Load Game 2");
        buttons.add(load2);

    //==============================================================
    // save file 3
    //==============================================================
        JButton save3 = new JButton("Save State 3 - None");
        buttons.add(save3);
        JButton characterName3 = new JButton("Charactername");
        buttons.add(characterName3);
        JButton fileDate3 = new JButton("-/-/-");
        buttons.add(fileDate3);
        JButton gold3 = new JButton("Gold: ");
        buttons.add(gold3);
        JProgressBar health3 = new JProgressBar(0);
        JProgressBar mana3 = new JProgressBar(0);
        JButton delete3 = new JButton("Delete File");
        buttons.add(delete3);
        JButton load3 = new JButton("Load Game 3");
        buttons.add(load3);

        JButton back = new JButton("↩ Back");
        buttons.add(back);

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setMaximumSize(new Dimension(BACK_SIZE.width, BACK_SIZE.height));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setBorder(Driver.buttonBorder);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
        }
        back.setBackground(customColorTan);
        Dimension buttonSize = new Dimension(
            ((int) back.getPreferredSize().getWidth() * 18 / 10), 
            ((int)back.getPreferredSize().getHeight() * 14 / 10));
        back.setPreferredSize(buttonSize);

        // this.setAlignmentX(CENTER_ALIGNMENT);

        
        //===============================================================================
        //
        // Relatively scaling and sizing components
        //
        //
        //
        JPanel buttonPanelSave1= new JPanel(new GridLayout()); // components for save save 1
        JPanel buttonPanelSave2= new JPanel(new GridLayout()); // components for save save 2
        JPanel buttonPanelSave3= new JPanel(new GridLayout()); // components for save save 3

        // Sizing for Save File 1 components and panel
        save1.setBounds(xCord, (yCord - PANEL_SIZE.height), PANEL_SIZE.width, PANEL_SIZE.height); 
        buttonPanelSave1.setBounds(xCord, yCord, PANEL_SIZE.width, PANEL_SIZE.height); 

        // Sizing for Save File 2 components and panel
        save2.setBounds(xCord, ((2 * yCord) - PANEL_SIZE.height), PANEL_SIZE.width, PANEL_SIZE.height); 
        buttonPanelSave2.setBounds(xCord, (2 * yCord), PANEL_SIZE.width, PANEL_SIZE.height);

        save3.setBounds(xCord, ((3 * yCord) - PANEL_SIZE.height), PANEL_SIZE.width, PANEL_SIZE.height); 
        buttonPanelSave3.setBounds(xCord, (3 * yCord), PANEL_SIZE.width, PANEL_SIZE.height);

        back.setBounds((width - BACK_SIZE.width) / 99, (height - BACK_SIZE.height) / 99, back.getPreferredSize().width, back.getPreferredSize().height); // formatting back button, x-cord, y-cord, width, and height
        //================================================================================
        buttonPanelSave1.add(characterName1);
        buttonPanelSave1.add(fileDate1);
        buttonPanelSave1.add(gold1);
        buttonPanelSave1.add(health1);
        buttonPanelSave1.add(mana1);
        buttonPanelSave1.add(delete1);
        buttonPanelSave1.add(load1);

        buttonPanelSave2.add(characterName2);
        buttonPanelSave2.add(fileDate2);
        buttonPanelSave2.add(gold2);
        buttonPanelSave2.add(health2);
        buttonPanelSave2.add(mana2);
        buttonPanelSave2.add(delete2);
        buttonPanelSave2.add(load2);

        buttonPanelSave3.add(characterName3);
        buttonPanelSave3.add(fileDate3);
        buttonPanelSave3.add(gold3);
        buttonPanelSave3.add(health3);
        buttonPanelSave3.add(mana3);
        buttonPanelSave3.add(delete3);
        buttonPanelSave3.add(load3);

        add(save1);
        add(buttonPanelSave1);
        add(save2);
        add(buttonPanelSave2);
        add(save3);
        add(buttonPanelSave3);
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
                    save1.setText("Successfully Saved Game to File 1");
                    fileDate1.setText(getCurrentDatTime()); //set file to current time
                    save1.setBackground(customColorGreen);
                } else {
                    System.out.println("Error: Could not save to file 1.");
                }
            }catch (Exception e1){
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
                    save2.setText("Successfully Saved Game to File 2");
                    fileDate2.setText(getCurrentDatTime()); //set file to current time
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

        save3.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // Save player object to save file 3
                boolean saveSuccessful = Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile3.sav");
                if (saveSuccessful) {
                    save3.setText("Successfully Saved Game to File 3");
                    fileDate3.setText(getCurrentDatTime()); //set file to current time
                    save3.setBackground(customColorGreen);
                } else {
                    System.out.println("Error: Could not save to file 3.");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        load3.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                // load player from save file 3
                PlayerCharacter loadedPlayer = Driver.loadPlayer("save-files/saveFile3.sav");
                if (loadedPlayer != null) {
                    Driver.setPlayer(loadedPlayer); // Update the player instance 
                } else {
                    System.out.println("Error: Could not load save-file 3.");
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
                save3.setText("Save File 3"); 
                save3.setBackground(customColorBrown);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

//==================================================================
// Class Methods
//==================================================================

    /**
     * Gets current date andtime in the format "h:mm a MM/dd/yyy"
     * @return formattedDateTime
     */
    private String getCurrentDatTime(){
        currentDateTime = LocalDateTime.now();
        formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
            try{
                g.drawImage(ImageIO.read(new File("assets/images/World8.png")), 0, 0, getWidth(), getHeight(), this);
            }catch (IOException e){
                //Auto-generated catch block
                e.printStackTrace();
            }
    }
}