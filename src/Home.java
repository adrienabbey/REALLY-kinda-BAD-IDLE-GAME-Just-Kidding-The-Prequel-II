import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Home extends JPanel {
    PlayerCharacter player = new PlayerCharacter(getName());

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/home.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

    public Home() {
        // Set the layout with vertical alignment and padding
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(400, 490, 300, 490)); // Add padding around the panel

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color

        // Create the 'purchase' button with custom styling
        JButton purchase = new JButton("Purchase Homestead");
        purchase.setFont(new Font("Serif", Font.BOLD, 24));
        purchase.setForeground(new Color(255, 255, 255)); // White text
        purchase.setBackground(new Color(139, 69, 19)); // Dark wood color

        // Create the mesage label with custom styling
        JLabel message = new JLabel("", SwingConstants.CENTER);
        message.setFont(new Font("Serif", Font.ITALIC, 20));
        message.setForeground(new Color(205, 133, 63)); // Light wood color
        message.setBackground(new Color(0, 0, 0)); // Set the background color to black
        message.setOpaque(true); // Make the background visible

        // Create the information label with custom styling
        JLabel info = new JLabel("<html><div style='text-align: center;'> Property for sale: <br> - 1000 Gold Pieces<br> - 250 wood<br> - 250 Stone<br> - 100 Metal<br><br> The above resources will be taken out from your inventory once purchased. <br>Having a home will increase your inventory and unlock farming.</div></html>", SwingConstants.CENTER);
        info.setFont(new Font("Serif", Font.ITALIC, 20));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setBackground(new Color(0, 0, 0, 192)); // Set the background color to black
        info.setOpaque(true); // Make the background visible

        // Add components to the panel
        add(back, BorderLayout.NORTH);
        add(info, BorderLayout.CENTER);
        add(purchase, BorderLayout.SOUTH);

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            message.setText("");
            Driver.changePanel("world");
        });

        // Action listener for the 'Purchase' button
        purchase.addActionListener(e -> {
            if (player.getGold() < 1000) {
                add(message, BorderLayout.CENTER);
                message.setText("<html><div style='text-align: center;'>You do not have enough Gold to purchase the homestead.</div></html>");
            }
            //TODO: Add house purchasing logic here. Check inventory for items,
            // take items away, increase inventory capacity to 20, change home panel
            // to the unlocked home panel w/ farming functionality. 
        });
    }
}
