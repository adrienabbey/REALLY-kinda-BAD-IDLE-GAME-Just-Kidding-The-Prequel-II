import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class GameInstructions extends JPanel {
    Image background;

    public GameInstructions() {
        try {
            background = ImageIO.read(new File("assets/images/town2.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot open background image.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        // Set the layout with vertical alignment and padding
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(400, 400, 400, 400)); // Add padding around the panel

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Light wood color
        back.setFocusPainted(false); // Remove focus ring around the button

        // Create the information label with custom styling
        JLabel info = new JLabel("<html><div style='text-align: center;'>Hello Traveler! Welcome to B.A.D Idle Game.<br>To start your journey you will need to create a new character using the New Game button.<br>From there you will choose your stats and start adventuring into the dungeon.<br>Stop by the town bazaar for potions to heal yourself.</div></html>", SwingConstants.CENTER);
        info.setPreferredSize(new Dimension((int) (width * .8), (int)(height * .8))); // Set the preferred size of the label (80% of the panel width and height
        info.setFont(new Font("Serif", Font.ITALIC, 20));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setBackground(new Color(0, 0, 0, 192)); // Set the background color to black
        info.setOpaque(true); // Make the background visible

        // Add components to the panel
        add(Box.createRigidArea(new Dimension(0, 20))); // Add space between the top of the panel and the button
        add(back);
        add(Box.createRigidArea(new Dimension(0, 20))); // Add space between the button and the label
        add(info);
        add(Box.createRigidArea(new Dimension(0, 20))); // Add space between the label and the bottom of the panel

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("start");
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
