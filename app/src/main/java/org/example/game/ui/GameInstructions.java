import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class GameInstructions extends JPanel {
    Image background;
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int infoFont = screenSize.width / 96;
    final Dimension LABEL_SIZE = new Dimension((int)(screenSize.width / 2.1), (int)(infoFont * 10));
    final int yPos = screenSize.height / 2 - LABEL_SIZE.height / 2;
    final int xPos = screenSize.width / 2 - LABEL_SIZE.width / 2;

    public GameInstructions() {
        try {
            background = ImageIO.read(new File("assets/images/town2.png"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Cannot open background image.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        setLayout(null);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 192));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        // Create the 'Back' button with custom styling
        JButton back = new JButton("🢐 Back");
        back.setFont(new Font("Serif", Font.BOLD, screenSize.width / 80));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Light wood color
        back.setFocusPainted(false); // Remove focus ring around the button

        // Create the information label with custom styling
        JLabel info = new JLabel("<html><div style='text-align: center;'>Hello Traveler! Welcome to B.A.D Idle Game.<br>To start your journey you will need to create a new character using the New Game button.<br>From there you will choose your stats and start adventuring into the dungeon.<br>Stop by the town bazaar for potions to heal yourself.</div></html>", SwingConstants.CENTER);
        info.setPreferredSize(LABEL_SIZE); // Set the preferred size of the label (80% of the panel width and height
        info.setFont(new Font("Serif", Font.ITALIC, infoFont));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setOpaque(false); // Make the background visible

        // Add components to the panel
        panel.add(back, BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        panel.setBounds(xPos, yPos, LABEL_SIZE.width, LABEL_SIZE.height);

        add(panel);

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
