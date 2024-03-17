import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Inventory extends JPanel {
    int prompt = 0;

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/tavern.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

    public Inventory() {
        // Set the layout with vertical alignment and padding
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(400, 570, 400, 570)); // Add padding around the panel

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color

        // Create the 'purchase' button with custom styling
        JButton talk = new JButton("Talk to townsfolk");
        talk.setFont(new Font("Serif", Font.BOLD, 24));
        talk.setForeground(new Color(255, 255, 255)); // White text
        talk.setBackground(new Color(139, 69, 19)); // Dark wood color

        // Create the mesage label with custom styling
        JLabel message = new JLabel("", SwingConstants.CENTER);
        message.setFont(new Font("Serif", Font.ITALIC, 21));
        message.setForeground(new Color(205, 133, 63)); // Light wood color
        message.setBackground(new Color(0, 0, 0)); // Set the background color to black
        message.setOpaque(true); // Make the background visible

        // Create the information label with custom styling
        JLabel info = new JLabel("", SwingConstants.CENTER);
        info.setFont(new Font("Serif", Font.ITALIC, 20));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setBackground(new Color(0, 0, 0, 192)); // Set the background color to black
        info.setOpaque(true); // Make the background visible

        // Add components to the panel
        add(back, BorderLayout.NORTH);
        add(info, BorderLayout.CENTER);
        add(talk, BorderLayout.SOUTH);

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            message.setText("");
            Driver.changePanel("town");
            MusicPlayer.playMusic("assets/images/Music/Village Consort.wav");
        });

        // Action listener for the 'Purchase' button
        talk.addActionListener(e -> {
            
            if (prompt == 0 ) {
                add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Markus: You can buy potions at the bazaar.</div></html>");
            }
            if (prompt == 1 ) {
                add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Guardsman: I heard you need a speacial set of armor to survive the monsters in the abandoned colosseum.</div></html>");
            }
            if (prompt == 2 ) {
                add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Miner: I can usually find one metal for every four stone when mining in the shaft out west.</div></html>");
            }
            if (prompt == 3 ) {
                add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>Aaron: There's a secret merchant that sells a powerful potion. Some say he only comes out to the bazaar once every three blue moons.");
            }
            prompt++;
            if (prompt == 4 ) {
                prompt = 0;
            }
        });
    }
}
