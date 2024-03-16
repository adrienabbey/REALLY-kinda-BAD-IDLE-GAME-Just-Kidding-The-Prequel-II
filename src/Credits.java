import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class Credits extends JPanel {
    private JLabel rollingText;
    private Timer timer;
    private int yPos = 1050;

        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/scene2.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }

    public Credits() {
        // Set the layout with vertical alignment and padding
        this.setLayout(null); // Use null layout for precise positioning

        // Create the 'Back' button with custom styling
        JButton back = new JButton("<- Back");
        back.setFont(new Font("Serif", Font.BOLD, 24));
        back.setForeground(new Color(255, 255, 255)); // White text
        back.setBackground(new Color(139, 69, 19)); // Dark wood color
        back.setFocusPainted(false); // Remove focus ring around the button

        // Create the rolling text label with custom styling
        rollingText = new JLabel("<html><div style='text-align: center; vertical-align: top;'>Developers:<br>- Adrien Abbey: PO, PM, Dev, QA<br>- Muhammed Abushamma: Docudementor, PM, Dev, QA<br>- Luke Davidson: PM, Dev, QA<br>- Brandon Walker: PM, Dev, QA<br><br>Credits & Licensing:<br>-StartScreen image: Drawn by Luke Davidson<br>- All other image used were AI generated<br>Music:<br> <br>-Title: Court and Page by Silent Partner <br>Genre and Mood: Ambient + Dark <br>License: You're free to use this song and monetize your videos. <br>Always No Copyright Music | Free Music for Everyone <br>Source:https://www.youtube.com/watch?v=eZ_r1H9vHkI&list=PLBTAnqebSfx20bAU<br>X-x5SR301sHbSLobY&index=4<br><br>-Title: Village Consort Genre: Country & Folk Mood: Dramatic<br> Source: https://www.youtube.com/watch?v=eZ_r1H9vHkI<br><br> <br>-Uskudara Gideriken by Turku, Nomads of the Silk Road <br> licensed under a Attribution License.<br>Source: https://freemusicarchive.org/music/Turku_Nomads_of_the_Silk_Road/Al<br>leys_of_Istanbul/01_-Uskudara_Gideriken/<br><br> - Now We Ride by Alexander Nakarada<br> | https://www.serpentsoundstudios.com <br>Music promoted by https://www.free-stock-music.com <br>Attribution 4.0 International (CC BY 4.0) https://creativecommons.org/licenses/... <br>Track description: Brilliant epic fantasy music by Alexander Nakarada.<br> Moods: Epic <br>Tags: soundtrack heroic epic movie fantasy film medieval world music cinematic celtic melodic <br>Artist: Alexander Nakarada <br>Website: <br>https://www.serpentsoundstudios.com <br>Licensing info:https://www.serpentsoundstudios.com/l... <br>License: Attribution 4.0 International (CC BY 4.0).<br><br> - Song: Song of the North <br>Artist: BrunuhVille <br>Album: Northwind Source: <br>https://www.youtube.com/watch?v=gRuggMzH3Gw&t=1s&ab_channel=BrunuhVille <br>License: https://docs.google.com/document/d/1AIpznS4s5r6Okj70zhl<br>qVpwC8E4jQoo2o-I8uOyu4gk/edit?pli=1#heading=h.sv74n9r37xsj</div></html>", SwingConstants.CENTER);
        rollingText.setFont(new Font("Serif", Font.PLAIN, 20));
        rollingText.setForeground(new Color(255, 190, 128)); // Lighter wood color
        rollingText.setBackground(new Color(0, 0, 0)); // Set the background color to black
        rollingText.setOpaque(true); // Make the background visible

        // Set the position and size of the 'Back' button
        back.setBounds(10, 10, 130, 50);
        
        // Calculate the position of the rolling text label to center it
        int labelWidth = 850; // Set your desired width
        int labelHeight = 1420; // Set your desired height
        int x = (getWidth() - labelWidth) / 2;
        int y = (getHeight() - labelHeight) / 2;

        // Set the position and size of the rolling text label
        rollingText.setBounds(x, y, labelWidth, labelHeight);

        // Add components to the panel
        add(back);
        add(rollingText);

        // Action listener for the 'Back' button. Sets text to correct position after very opening. 
        back.addActionListener(e -> {
            yPos=1050;
            MusicPlayer.playMusic("assets/images/Music/Brilliant1.wav");
            Driver.changePanel("settings");
        });

        // Timer for rolling text
        timer = new Timer(40, e -> {
            yPos--;
            rollingText.setLocation(520, yPos);
            if (yPos + rollingText.getHeight() < 0) {
                yPos = getHeight();
            }
        });
        timer.start();
    }
}
