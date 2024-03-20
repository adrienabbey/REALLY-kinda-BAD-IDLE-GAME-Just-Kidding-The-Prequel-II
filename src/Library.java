import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Library extends JPanel {

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/library2.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

    public Library() {
        // Set the layout with vertical alignment and padding
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(400, 490, 400, 490)); // Add padding around the panel

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color
        back.setFocusPainted(false); // Remove focus ring around the button

        // Create the information label with custom styling
        JLabel info = new JLabel("<html><div style='text-align: center;'>Hello Traveler! Welcome to B.A.D Idle Game.<br>To start your journey you will need to create a new character using the New Game button.<br>From there you will choose your stats and start adventuring into the dungeon.<br>Stop by the town shop for potions to heal yourself.</div></html>", SwingConstants.CENTER);
        info.setFont(new Font("Serif", Font.ITALIC, 20));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setBackground(new Color(0, 0, 0, 192)); // Set the background color to black
        info.setOpaque(true); // Make the background visible

        // Add components to the panel
        add(back, BorderLayout.NORTH);
        add(info, BorderLayout.CENTER);

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            Driver.changePanel("town");
            MusicPlayer.playMusic("assets/Music/Village Consort.wav");
        });
    }
}
