import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.awt.Point;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;

// This class is the introductory starting screen with buttons to start a new game, load a game, read directions, go to settings, or quit
class StartScreen extends JPanel{
    private Timer timer;
    private Image background;
    EyePhysics eye1 = new EyePhysics(); // Initialize the first EyePhysics object
    EyePhysics eye2 = new EyePhysics(); // Initialize the second EyePhysics object
    private boolean soundCooldown = false; // flag to track cooldown state fot sfx
    private int hoboSFX = 0; // used to loop through hobogoblin sfx 
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int titleSize = screenSize.width / 15;
    final Dimension BUTTON_GAP = new Dimension(0, screenSize.height / 100);
    final int buttonFont = screenSize.width / 60;
    final Dimension BUTTON_SIZE = new Dimension(screenSize.width / 7, screenSize.height / 18);

    final private Color customColorBlue = new Color(46, 86, 161);
    final private Color customColorBeige = new Color(253, 236, 166);
    final private Color customColorBrown = new Color(102, 72, 54);
    final Color customFireBrick = new Color(178, 34, 34);
    final private Border buttonBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

    public StartScreen() {

        try {
            background = ImageIO.read(new File("assets/images/FinalStartScreen.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot open background image.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;


        int eyeRadius = (int)((double) width * ((double) 18 / 1920));
        int irisRadius = (int)((double) width * ((double) 9 / 1920));
        int eye1Height = (int)((double) height * ((double) 571 / 1080));
        int eye2Height = (int)((double) height * ((double) 564 / 1080));
        int eye1X = (int)((double) width * ((double) 1636 / 1920));
        int eye2X = (int)((double) width * ((double) 1670 / 1920));

        try {
            // Set first eye's properties
            eye1.setEyePosition(new Point(eye1X, eye1Height));
            eye1.setEyeRadius(eyeRadius);
            eye1.setIrisPosition(new Point(eye1X, eye1Height));
            eye1.setIrisRadius(irisRadius);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Set second eye's properties
            eye2.setEyePosition(new Point(eye2X, eye2Height));
            eye2.setEyeRadius(eyeRadius);
            eye2.setIrisPosition(new Point(eye2X, eye2Height));
            eye2.setIrisRadius(irisRadius);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set up a timer to reset the soundCooldown flag for goblin sfx after the cooldown of 1.5 seconds
        Timer cooldownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundCooldown = false;
            }
        });
        // Start the cooldown timer
        cooldownTimer.start();

        // Set up a timer to update iris positions and repaint the panel
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update iris positions for both eyes based on mouse position
                eye1.updateIrisPosition(getMousePosition(), eye1);
                eye2.updateOppositeIrisPosition(getMousePosition(), eye2);
                
                // Calculate the midpoint between the two eyeballs
                int midX = (eye1.getEyePosition().x + eye2.getEyePosition().x) / 2;
                int midY = (eye1.getEyePosition().y + eye2.getEyePosition().y) / 2;

                // Get the cursor position
                Point cursorPosition = getMousePosition();

                // Define the margin of error
                int marginOfError = 50;

                // Check if the cursor is within the range of the midpoint with margin of error
                if (cursorPosition != null &&
                    cursorPosition.x >= midX - marginOfError && cursorPosition.x <= midX + marginOfError &&
                    cursorPosition.y >= midY - marginOfError && cursorPosition.y <= midY + marginOfError) {
                    // Play goblin sound if player's cursor gets too close.
                    if (!soundCooldown) {
                        if (hoboSFX == 0) {
                            SFX.playSound("assets/SFX/goblins/goblin-1.wav");
                        } else if (hoboSFX == 1) {
                            SFX.playSound("assets/SFX/goblins/goblin-8.wav"); 
                        } else if (hoboSFX == 2) {
                            SFX.playSound("assets/SFX/goblins/goblin-11.wav");
                        } else if (hoboSFX == 3) {
                            SFX.playSound("assets/SFX/goblins/goblin-12.wav");
                        }
                        hoboSFX++;
                        if (hoboSFX == 4) {
                            hoboSFX = 0;
                        }
                        // Set the soundCooldown flag to true to activate cooldown
                        soundCooldown = true;

                        // Restart the cooldown timer
                        cooldownTimer.restart();
                    }
                }
                repaint(); // Redraw the panel
            }
        });
        timer.start();

    // This function hosts the start menu with buttons to start a new game, load a game, read directions, or quit

        // This block of code is all the physical gui elements and their properties
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton quit = new JButton("Quit");
        buttons.add(quit);
        JButton newGame = new JButton("New Game");
        buttons.add(newGame);
        JButton loadGame = new JButton("📁 Load Game");
        buttons.add(loadGame);
        JButton instructions = new JButton("📖 Instructions");
        buttons.add(instructions);
        JButton settings = new JButton("⚙ Settings");
        buttons.add(settings);

        JLabel title = new JLabel("B.A.D Idle Game");
        title.setFont(new Font("Serif", Font.BOLD, 128));
        title.setForeground(customColorBlue);
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, screenSize.height / 54)));
        add(newGame);
        add(Box.createRigidArea(BUTTON_GAP));
        add(loadGame);
        add(Box.createRigidArea(BUTTON_GAP));
        add(instructions);
        add(Box.createRigidArea(BUTTON_GAP));
        add(settings);
        add(Box.createRigidArea(BUTTON_GAP));
        add(quit);
        add(Box.createVerticalGlue());


        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(BUTTON_SIZE);
            buttons.get(i).setMaximumSize(BUTTON_SIZE);
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setBorder(buttonBorder);
            buttons.get(i).setFont(new Font("serif", Font.BOLD, buttonFont));

            // Formats quit button
            if (i == 0) {
                buttons.get(0).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
                buttons.get(i).setPreferredSize(BUTTON_SIZE);
                buttons.get(i).setMaximumSize(BUTTON_SIZE);
                buttons.get(i).setBorder(buttonBorder);
            }
        }

        // Buttons to interact with the functions of the game
        // Create new Character
        newGame.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("cc");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Load a saved game
        loadGame.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("load");
        });

        //View Game instructions
        instructions.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("instructions");
        });

        // Quit the game
        quit.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            System.exit(0);
        });

        // Go to settings panel
        settings.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("settings");             
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        // Draw the first eye
        eye1.drawEye(g, eye1);
        // Draw the second eye
        eye2.drawEye(g, eye2);
    }
}