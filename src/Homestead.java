import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Homestead extends JPanel {

    private Inventory inventory; // Reference to the Inventory object
    private boolean purchaseConfirmation = false;

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

    public Homestead(Inventory inventory) {
        this.inventory = inventory; // Assign the Inventory object to the local variable
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        // Set the layout with vertical alignment and padding
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(400, 490, 300, 490)); // Add padding around the panel

        // add buttons to "button" ArrayList.
        JButton back = new JButton("<- Back");
        buttons.add(back);
        JButton farm  = new JButton("Farm");
        buttons.add(farm);
        JButton craft = new JButton("Craft");
        buttons.add(craft);
        JButton purchase = new JButton("Purchase Homestead");
        buttons.add(purchase);
        JButton inventory1 = new JButton("Inventory");
        buttons.add(inventory1);
        
        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
            // buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setBackground(new Color(139, 69, 19)); // Dark wood color
            buttons.get(i).setForeground(new Color(255, 255, 255)); // White text
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, 26));
        }

        this.setAlignmentX(CENTER_ALIGNMENT);

        farm.setPreferredSize(new Dimension(200, 180));
        farm.setMaximumSize(new Dimension(200, 180));
        craft.setPreferredSize(new Dimension(200, 180));
        craft.setMaximumSize(new Dimension(200, 180));
        inventory1.setPreferredSize(new Dimension(200, 180));
        inventory1.setMaximumSize(new Dimension(200, 80));
        back.setFont(new Font("serif", Font.BOLD, 24));

        // Create the information label with custom styling
        JLabel info = new JLabel("<html><div style='text-align: center;'> Property for sale: <br> - 1000 Gold Pieces<br> - 250 wood<br> - 250 Stone<br> - 100 Metal<br><br> The above resources will be taken out from your inventory once purchased. <br>Having a home will increase your inventory space and unlock farming and crafting.</div></html>", SwingConstants.CENTER);
        info.setFont(new Font("Serif", Font.ITALIC, 21));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setBackground(new Color(0, 0, 0)); // Set the background color to black
        info.setOpaque(true); // Make the background visible

        // Add components to the panel
        add(back, BorderLayout.NORTH);
        add(info, BorderLayout.CENTER);
        add(purchase, BorderLayout.SOUTH);

        // Action listener for the 'Purchase' button
        purchase.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            
            // checks is player has enough resources to buy homestead
            if (inventory.getResource("Gold") >= 1000 && inventory.getResource("Wood") >= 250 && inventory.getResource("Stone") >= 250 && inventory.getResource("Metal") >= 100) {

                // Confirm purchase from player.
                if (purchaseConfirmation == false) {
                    info.setText("<html><div style='text-align: center;'> Are you sure you want to purchase the homestead?</div></html>");
                    info.setFont(new Font("Serif", Font.ITALIC, 26));
                    purchaseConfirmation = true;
                    return; // Exit the action listener to wait for confirmation
                }

                // play all resource sound effects when purchased
                SFX.playSound("assets/SFX/coin3.wav"); // play coin sfx
                SFX.playSound("assets/SFX/wood-gathering-sfx.wav");
                SFX.playSound("assets/SFX/stone-gathering-sfx.wav");
                SFX.playSound("assets/SFX/metal-ringing1.wav");
                
                // Remove resources used to purchase homestead from inventory
                inventory.setResource("Gold", inventory.getResource("Gold") - 1000);
                inventory.setResource("Wood", inventory.getResource("Wood") - 250);
                inventory.setResource("Stone", inventory.getResource("Stone") -250);
                inventory.setResource("Metal", inventory.getResource("Metal") - 100);

                //Update resource labels
                inventory.updateResourceLabels();

                // Remove the purchase button and info label
                remove(purchase);
                remove(info);
        
                back.setPreferredSize(new Dimension(200, 80));
                back.setMaximumSize(new Dimension(200, 80));

                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                // add buttons for farming, crafting, inventory, and back
                add(Box.createVerticalGlue());
                add(Box.createRigidArea(new Dimension(200, 150)));
                add(farm);
                add(Box.createRigidArea(new Dimension(200, 60)));
                add(craft);
                add(Box.createRigidArea(new Dimension(200, 60)));
                add(inventory1);
                add(Box.createRigidArea(new Dimension(200, 60)));
                add(back);
                add(Box.createVerticalGlue());  
                
                back.setFont(new Font("serif", Font.BOLD, 28));

                revalidate();
                repaint();
                // TODO - increase player's inventory space here. 

            } else {
                // if player does not have enough resources output error message.  
                info.setText("<html><div style='text-align: center;'>You do not have enough resources to purchase the homestead.</div></html>");
                info.setFont(new Font("Serif", Font.ITALIC, 26));
            }
        });

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            Inventory.backToHomestead = false;
            // set labels and flag back to default
            info.setText("<html><div style='text-align: center;'> Property for sale: <br> - 1000 Gold Pieces<br> - 250 wood<br> - 250 Stone<br> - 100 Metal<br><br> The above resources will be taken out from your inventory once purchased. <br>Having a home will increase your inventory space and unlock farming and crafting.</div></html>");
            info.setFont(new Font("Serif", Font.ITALIC, 21));
            purchaseConfirmation = false;
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("world");

        });

        // Takes player to farm screen
        farm.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("farm");
        
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

        // Takes player to craft screen
        craft.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("craft");

            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

        // Takes player to inventory screen
        inventory1.addActionListener(e -> {
            try {
                Inventory.backToHomestead = true; 
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("inventory");

            } catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }
}
