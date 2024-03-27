import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

// This class is the introductory starting screen with buttons to start a new game, load a game, read directions, go to settings, or quit
class StartScreen extends JPanel{
    // private EyePhysics eyePhysics;
    // private Point eyePosition;
    // private int eyeRadius;
    // private Point irisPosition;
    // private int irisRadius;
    private Timer timer;

    // private EyePhysics eyePhysics2;
    // private Point eyePosition2;
    // private int eyeRadius2;
    // private Point irisPosition2;
    // private int irisRadius2;
    EyePhysics eye1 = new EyePhysics();
    EyePhysics eye2 = new EyePhysics();
    
    private boolean soundCooldown = false; // flag to track cooldown state fot sfx
    private int hoboSFX = 0; // used to loop through hobogoblin sfx 

    public StartScreen() {
        try {
            // Initialize the first EyePhysics instance and eye properties
            eye1.setEyePosition(new Point(1620, 550));
            eye1.setEyeRadius(23);
            eye1.setIrisPosition(new Point(1620, 550));
            eye1.setIrisRadius(16);

            // eyePhysics = new EyePhysics();
            // eyePosition = new Point(1620, 550); // position of the eye
            // eyeRadius = 23; // radius of the eye
            // irisPosition = new Point(1620, 550); // Initial position of iris same as eye
            // irisRadius = 16; // radius of the iris
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Initialize the second EyePhysics instance and eye properties
            eye2.setEyePosition(new Point(1670, 550));
            eye2.setEyeRadius(23);
            eye2.setIrisPosition(new Point(1670, 550));
            eye2.setIrisRadius(16);
            // eyePhysics2 = new EyePhysics();
            // eyePosition2 = new Point(1670, 550); // position of the eye
            // eyeRadius2 = 23; // radius of the eye
            // irisPosition2 = new Point(1670, 550); // position of iris same as eye
            // irisRadius2 = 16; // Example radius of the iris
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set up a timer to reset the soundCooldown flag after the cooldown of 2 seconds
        Timer cooldownTimer = new Timer(2000, new ActionListener() {
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
                updateIrisPosition(getMousePosition(), eye1);
                updateIrisPosition(getMousePosition(), eye2);
                
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
        JButton loadGame = new JButton("Load Game");
        buttons.add(loadGame);
        JButton instructions = new JButton("Instructions");
        buttons.add(instructions);
        JButton settings = new JButton("Settings");
        buttons.add(settings);

        Color customColorBlue = new Color(46, 86, 161);
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        JLabel title = new JLabel("B.A.D Idle Game");
        title.setFont(new Font("Serif", Font.BOLD, 128));
        title.setForeground(customColorBlue);
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Adding the buttons to the start panel and controlling layout
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(newGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(loadGame);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(instructions);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(settings);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(quit);
        add(Box.createVerticalGlue());


        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(new Dimension(60, 80));
            buttons.get(i).setMaximumSize(new Dimension(600, 500));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("serif", Font.BOLD, 32));

            // Formats quit button
            if (i == 0) {
                buttons.get(0).setAlignmentX(CENTER_ALIGNMENT);
                buttons.get(0).setBackground(Color.RED);
                buttons.get(0).setForeground(Color.WHITE);
                buttons.get(i).setPreferredSize(new Dimension(60, 80));
                buttons.get(i).setMaximumSize(new Dimension(400, 500));
            }
        }
        newGame.setFocusPainted(false); // Remove focus ring around the button

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
        try {
            g.drawImage(ImageIO.read(new File("assets/images/startScreenTest.jpg")), 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Draw the left eye
        drawEye(g, eye1.getEyePosition(), eye1.getEyeRadius(), eye1.getIrisPosition(), eye1.getIrisRadius());
        
        // Draw right eye
        drawEye(g, eye2.getEyePosition(), eye2.getEyeRadius(), eye2.getIrisPosition(), eye2.getIrisRadius());
    }

    // Method calculates the iris position based on the mouse position and the respective eye position
    private void updateIrisPosition(Point mousePosition, EyePhysics eye) {
        if (mousePosition != null) {
            if (eye == eye1) { // if eye is first eye
            eye1.setIrisPosition(eye.calculateIrisPosition(mousePosition, eye1.getEyePosition(), eye1.getEyeRadius(), eye1.getIrisRadius()));
            } else { // if eye is second eye
                eye2.setIrisPosition(eye.calculateIrisPosition(mousePosition, eye2.getEyePosition(), eye2.getEyeRadius(), eye2.getIrisRadius()));
            }
        }
    }

    private void drawEye(Graphics g, Point eyePosition, int eyeRadius, Point irisPosition, int irisRadius) {
        // Draw the eye
        g.setColor(Color.WHITE);
        g.fillOval(eyePosition.x - eyeRadius, eyePosition.y - eyeRadius, 2 * eyeRadius, 2 * eyeRadius);

        // Draw the iris
        g.setColor(Color.BLACK);
        g.fillOval(irisPosition.x - irisRadius, irisPosition.y - irisRadius, 2 * irisRadius, 2 * irisRadius);
    }
}


