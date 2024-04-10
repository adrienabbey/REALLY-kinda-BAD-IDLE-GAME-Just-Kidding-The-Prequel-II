/*
 * MovingCloud Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

 // Class used to draw moving graphics such as clouds onto panels. 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class MovingCloud {
    Toolkit toolkit = Toolkit.getDefaultToolkit(); // get awt's toolkit
    Dimension screenSize = toolkit.getScreenSize(); // get screen size
    int screenWidth = screenSize.width; // get screenwidth

    private int cloudX = 0; // Initial x-coordinate of the cloud
    private int cloudY = 100; // Initial y-coordinate of the cloud
    private final int cloudWidth = 100; // Width of the cloud
    private final int cloudHeight = 50; // Height of the cloud
    private final int cloudSpeed = 1; // Speed of the cloud
    private Timer timer; // Timer for animation
    private int panelWidth; // Width of the panel where the cloud will be drawn

    public MovingCloud(int panelWidth) {
        this.panelWidth = panelWidth;
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCloud();
            }
        }); // Create a timer that fires every 50 milliseconds
        timer.start(); // Start the timer
    }

    public void moveCloud() {
        // Move the cloud to the right
        cloudX += cloudSpeed;
        // If the cloud moves out of the panel, reset its position
        if (cloudX > panelWidth) {
            cloudX = -cloudWidth;
        }
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    

    public void drawCloud(Graphics g) {
        // Draw the cloud
        g.setColor(new Color (255, 255, 255, 150));
        g.fillOval(cloudX, cloudY, cloudWidth, cloudHeight);
    }
}
