import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;

        // JPanel rightPanel = new JPanel();
        // rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        // rightPanel.setAlignmentX(CENTER_ALIGNMENT);
        // add(rightPanel);

// This class manages the screen for creating a new character
class CharacterCreation extends JPanel {

    public static int statPoints = 10;
    public static int muscle = 0;
    public static int brain = 0;
    public static int heart = 0;

    // These are used for formating the gui elements
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private Dimension BUTTON_SIZE = new Dimension(width / 8, height / 16);
    final private Dimension BUTTON_GAP = new Dimension(0, height / 100);
    final private int labelFont = width / 78;
    final private int buttonFont = width / 78;
    final private int labelWidth = width / 10; //Set a fixed width for stat labels to prevent shifting

    final int imageWidth = (int) (width / 4);
    final int imageHeight = -1; // set to negative 1 to scale only one way

    final private Color customColorBeige = new Color(253, 236, 166);
    final private Color customColorBrown = new Color(102, 72, 54);
    final private Color customColorBlue = new Color(46, 86, 161);
    final private Border buttonBorder = BorderFactory.createLineBorder(Color.BLACK, 2);


    /**
     * This function hosts the character creation screen
     * It lets you assign stats, name your character, and then instantiates a
     * PlayerCharacter object and launches to the map
     * 
     * @throws IOException
     */
    public CharacterCreation() throws IOException {

        // This is all the physical gui elements and their properties
        // this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        JLabel nameLabel = new JLabel("Name");
        labels.add(nameLabel);

        // Text Field for Player's characrter name
        JTextField name = new JTextField(18);
        Dimension nameFieldSize = new Dimension(
            ((int) name.getPreferredSize().getWidth() * 16 / 10), 
            ((int)name.getPreferredSize().getHeight() * 16 / 10));
        name.setMaximumSize(nameFieldSize);
        name.setBackground(customColorBrown);  // Set background color
        name.setForeground(customColorBeige); // Set text color
        name.setFont(new Font("Serif", Font.BOLD, buttonFont));

        namePanel.add(Box.createHorizontalGlue());
        namePanel.add(nameLabel);
        namePanel.add(Box.createHorizontalStrut(10));
        namePanel.add(name);
        namePanel.add(Box.createHorizontalGlue());

        JPanel pointsPanel = new JPanel();
        pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.X_AXIS));
        JLabel points = new JLabel("You have " + statPoints + " stat points left to spend.");
        labels.add(points);
        JLabel errNameLabel = new JLabel("");
        labels.add(errNameLabel);
        // points.setBounds((width * (3/4)), ((height / 6) + (labelFont / 2)), points.getPreferredSize().width, points.getPreferredSize().height);
        pointsPanel.add(Box.createHorizontalGlue());
        pointsPanel.add(points);
        pointsPanel.add(errNameLabel);
        pointsPanel.add(Box.createHorizontalGlue());

        // Set maximum length of the name field to 18 characters
        AbstractDocument doc = (AbstractDocument) name.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                errNameLabel.setText("");
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
                if (newText.length() <= 18) { 
                    errNameLabel.setText("");
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    errNameLabel.setText("  Name cannot exceed 18 characters."); //When the user writes more than 18 characters. 
                }
            }
        
            // Handles the case when the user deletes chars from name, and the name becomes less than the limit. Removes the error message in this case. 
            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                
                // Check length after removal
                if (currentText.length() <= 18) {
                    errNameLabel.setText(""); // Clear error message if within limit
                }
            }
        });
        

        JPanel splitPanel = new JPanel();
        splitPanel.setLayout(new BoxLayout(splitPanel, BoxLayout.X_AXIS));
        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        splitPanel.add(leftPanel);
        splitPanel.add(rightPanel);
        
        Image image = ImageIO.read(new File("assets/images/character12.png"));
        Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(scaledImage);
        JLabel charImage = new JLabel(imageIcon);
        // charImage.setAlignmentX(CENTER_ALIGNMENT);
        // charImage.setBounds(width / 4, height / 2, scaledImage.getWidth(charImage), scaledImage.getHeight(charImage));
        leftPanel.add(charImage);

        JLabel message = new JLabel(" ");
        labels.add(message);
        message.setBounds((width * (3/4)), ((height / 6) + (2*(labelFont / 2))), points.getPreferredSize().width, points.getPreferredSize().height);

        ArrayList<JButton> buttons = new ArrayList<JButton>();

        JPanel musclePanel = new JPanel();
        musclePanel.setLayout(new BoxLayout(musclePanel, BoxLayout.X_AXIS));
        JLabel muscleLabel = new JLabel(" 💪 Muscle: " + muscle);
        labels.add(muscleLabel);

        //previous formatting
        // muscleLabel.setBounds((height * (3/4)), ((height / 6) + (3*(labelFont / 2))), points.getPreferredSize().width, points.getPreferredSize().height);

        //new formatting: sets preferred width to prevent shifting.
        muscleLabel.setPreferredSize(new Dimension(labelWidth, muscleLabel.getPreferredSize().height));


        JButton weaker = new JButton("🡓 Weaker");
        buttons.add(weaker);
        JButton stronger = new JButton("🡑 Stronger");
        buttons.add(stronger);

        musclePanel.add(Box.createHorizontalGlue());
        musclePanel.add(weaker);
        musclePanel.add(Box.createHorizontalStrut(10));
        musclePanel.add(stronger);
        musclePanel.add(Box.createHorizontalStrut(10));
        musclePanel.add(muscleLabel);
        musclePanel.add(Box.createHorizontalGlue());

        JPanel brainPanel = new JPanel();
        brainPanel.setLayout(new BoxLayout(brainPanel, BoxLayout.X_AXIS));
        JLabel brainLabel = new JLabel(" 👤 Brain: " + brain);
        labels.add(brainLabel);

        //previous formatting
        // brainLabel.setBounds((width * (3/4)), ((height / 6) + (4*(labelFont / 2))), points.getPreferredSize().width, points.getPreferredSize().height);
        
        //new formatting
        brainLabel.setPreferredSize(new Dimension(labelWidth, brainLabel.getPreferredSize().height));
        
        JButton dumber = new JButton("🡓 Dumber");
        buttons.add(dumber);
        JButton smarter = new JButton("🡑 Smarter");
        buttons.add(smarter);

        brainPanel.add(Box.createHorizontalGlue());
        brainPanel.add(dumber);
        brainPanel.add(Box.createHorizontalStrut(10));
        brainPanel.add(smarter);
        brainPanel.add(Box.createHorizontalStrut(10));
        brainPanel.add(brainLabel);
        brainPanel.add(Box.createHorizontalGlue());

        JPanel heartPanel = new JPanel();
        heartPanel.setLayout(new BoxLayout(heartPanel, BoxLayout.X_AXIS));
        JLabel heartLabel = new JLabel(" ❤️ Heart: " + heart);
        labels.add(heartLabel);

        //previous formatting
        // heartLabel.setBounds((width * (3/4)), ((height / 6) + (5*(labelFont / 2))), points.getPreferredSize().width, points.getPreferredSize().height);

        //new formatting
        heartLabel.setPreferredSize(new Dimension(labelWidth, heartLabel.getPreferredSize().height));

        JButton softer = new JButton("🡓 Softer");
        buttons.add(softer);
        JButton tougher = new JButton("🡑 Tougher");
        buttons.add(tougher);

        heartPanel.add(Box.createHorizontalGlue());
        heartPanel.add(softer);
        heartPanel.add(Box.createHorizontalStrut(10));
        heartPanel.add(tougher);
        heartPanel.add(Box.createHorizontalStrut(10));
        heartPanel.add(heartLabel);
        heartPanel.add(Box.createHorizontalGlue());


        // TODO: fix issue of submit button shifting when one of the stat labels text increase in size (i.e. 9 to a 10). 
        JButton submit = new JButton("Submit");
        JButton leave = new JButton("↫ Back to Main Menu");
        // submit button formating
        submit.setAlignmentX(CENTER_ALIGNMENT);
        leave.setAlignmentX(CENTER_ALIGNMENT);
        // submit.setPreferredSize(new Dimension(200, 80));
        // submit.setMaximumSize(new Dimension(200, 80));
        submit.setFont(new Font("Serif", Font.BOLD, buttonFont));
        submit.setBackground(customColorBlue);
        submit.setForeground(Color.WHITE);
        submit.setBounds((width * (3/4)), ((height / 6) + (6*(labelFont / 2))), submit.getPreferredSize().width, submit.getPreferredSize().height);

        leave.setFont(new Font("Serif", Font.BOLD, buttonFont));
        leave.setBackground(customColorBlue);
        leave.setForeground(Color.WHITE);
        leave.setBounds(width / 10, height / 50, submit.getPreferredSize().width, submit.getPreferredSize().height);


        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(message);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(musclePanel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(brainPanel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(heartPanel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(submit);
        rightPanel.add(Box.createVerticalStrut(10));
        add(leave);
        rightPanel.add(Box.createVerticalGlue());

        this.add(Box.createVerticalGlue());
        this.add(namePanel);
        this.add(Box.createVerticalStrut(10));
        this.add(pointsPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(splitPanel);
        // this.add(Box.createVerticalStrut(10));
        // this.add(submit);
        this.add(Box.createVerticalGlue());


        // Label formating
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setAlignmentX(CENTER_ALIGNMENT);
            labels.get(i).setForeground(customColorBrown);
            labels.get(i).setFont(new Font("Serif", Font.BOLD, labelFont));
        }

        // format buttons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(BUTTON_SIZE);
            buttons.get(i).setMaximumSize(BUTTON_SIZE);
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setBorder(buttonBorder);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
            buttons.get(i).setBounds((width * (3/4)), ((height / 6) + (6*(labelFont / 2)) + (i * (BUTTON_SIZE.height + BUTTON_GAP.height))), buttons.get(i).getPreferredSize().width, buttons.get(i).getPreferredSize().height);
        }

        // This section allows the buttons to interact with the functions of the game

        // Reduce Strength
        weaker.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface6.wav");
            if (muscle > 0) {
                muscle--;
                statPoints++;
                points.setText("You have " + statPoints + " stat points left to spend.");
                muscleLabel.setText(" 💪 Muscle: " + muscle);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You cannot go below 0 in any stat.");
                this.repaint();
            }
        });

        // Increase Strength
        stronger.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface6.wav");
            if (statPoints > 0) {
                muscle++;
                statPoints--;
                points.setText("You have " + statPoints + " stat points left to spend.");
                muscleLabel.setText(" 💪 Muscle: " + muscle);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        // Reduce Intelligence
        dumber.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface6.wav");
            if (brain > 0) {
                brain--;
                statPoints++;
                points.setText("You have " + statPoints + " stat points left to spend.");
                brainLabel.setText(" 👤 Brain: " + brain);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You cannot go below 0 in any stat.");
                this.repaint();
            }
        });

        // Increase Intelligence
        smarter.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface6.wav");
            if (statPoints > 0) {
                brain++;
                statPoints--;
                points.setText("You have " + statPoints + " stat points left to spend.");
                brainLabel.setText(" 👤 Brain: " + brain);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        // Reduce Constitution
        softer.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface6.wav");
            if (heart > 0) {
                heart--;
                statPoints++;
                points.setText("You have " + statPoints + " stat points left to spend.");
                heartLabel.setText(" ❤️ Heart: " + heart);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You cannot go below 0 in any stat.");
                this.repaint();
            }
        });

        // Increase Constitution
        tougher.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface6.wav");
            if (statPoints > 0) {
                heart++;
                statPoints--;
                points.setText("You have " + statPoints + " stat points left to spend.");
                heartLabel.setText(" ❤️ Heart: " + heart);
                message.setText(" ");
                this.repaint();
            } else {
                message.setText("You do not have enough stat points to spend.");
                this.repaint();
            }
        });

        // Submit character
        submit.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            PlayerCharacter player = new PlayerCharacter(name.getText(), muscle, brain, heart, 10 * statPoints + 1000,
                    1, 1);
            try {
                //reset skill points
                statPoints = 10;
                muscle = 0;
                brain = 0;
                heart = 0;
                points.setText("You have " + statPoints + " stat points left to spend.");
                muscleLabel.setText(" 💪 Muscle: " + muscle);
                brainLabel.setText(" 👤 Brain: " + brain);
                heartLabel.setText(" ❤️ Heart: " + heart);
                Driver.setPlayer(player);
                Driver.addCharScreen();
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // leave to main menu
        leave.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            try {
                statPoints = 10;
                muscle = 0;
                brain = 0;
                heart = 0;
                points.setText("You have " + statPoints + " stat points left to spend.");
                muscleLabel.setText(" 💪 Muscle: " + muscle);
                brainLabel.setText(" 👤 Brain: " + brain);
                heartLabel.setText(" ❤️ Heart: " + heart);
                Driver.changePanel("start");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}