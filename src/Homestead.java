/*
 * Homestead Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/*
 * Implements the "Homestead" panel accessed through the World screen. Allows the player to purchase a homestead. Unlocking the homestead unlocks a greater inventory space, the farming functionaility, and crafting functionality. The inventory, farming, and crafting panels can be accessed directly from the homestead screen. 
 * 
 * Current cost in rsources to purchase: 1000 Gold, 250 Wood, 250 Stone, 100 Metal. Can tweak and optimize amounts later. 
 * 
 * TODO: Increase player inventory after purchasing homestead. Implement the crafting and farming classes. 
 * 
 * 
 */

class Homestead extends JPanel {

//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 78;
    final private Border lineBorder = BorderFactory.createLineBorder(new Color(255, 255, 255));
    private boolean purchaseConfirmation = false;


//========================================================
// Constructor
//========================================================
    public Homestead() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        // Set the layout with vertical alignment and padding
        this.setLayout(null);
        JPanel purchasePanel = new JPanel(new BorderLayout());

        // add buttons to "button" ArrayList.
        JButton back = new JButton("↩ Back");
        buttons.add(back);
        JButton farm = new JButton("🌾 Farm");
        buttons.add(farm);
        JButton craft = new JButton("⚒ Craft");
        buttons.add(craft);
        JButton purchase = new JButton("💰 Purchase Homestead");
        buttons.add(purchase);
        JButton inventory1 = new JButton("🛄 Inventory");
        buttons.add(inventory1);

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackground(new Color(46, 86, 161)); // blue color
            buttons.get(i).setForeground(new Color(255, 255, 255)); // White text
            buttons.get(i).setPreferredSize(new Dimension(width / 8, height / 22));
            buttons.get(i).setMaximumSize(new Dimension(width / 8, height / 22));
            buttons.get(i).setBorder(lineBorder);
            buttons.get(i).setFont(new Font("Serif", Font.BOLD, buttonFont));
        }

        this.setAlignmentX(CENTER_ALIGNMENT);

        back.setBackground(new Color(139, 69, 19)); // light wood color
        purchase.setBackground(new Color(139, 69, 19)); // light wood color
        back.setFont(new Font("serif", Font.BOLD, buttonFont));
        purchase.setFont(new Font("Serif", Font.BOLD, buttonFont));

        // Create the information label with custom styling
        JLabel info = new JLabel(
                "<html><div style='text-align: center;'> Property for sale: <br> - 1000 Gold Pieces<br> - 250 wood<br> - 250 Stone<br> - 100 Metal<br><br> The above resources will be taken out from your inventory once purchased. <br>Having a home will unlock farming and crafting.</div></html>",
                SwingConstants.CENTER);
        info.setFont(new Font("Serif", Font.ITALIC, buttonFont));
        info.setForeground(new Color(205, 133, 63)); // Light wood color
        info.setBackground(new Color(0, 0, 0)); // Set the background color to black
        info.setOpaque(true); // Make the background visible

        //=======================================================
        //
        //
        //Relatively scaling and sizing world components
        //
        // 
        purchasePanel.setBounds(width / 4, height / 4, width / 2, height / 2);
        //==========================================================

        // Add components to the panel
        purchasePanel.add(back, BorderLayout.NORTH);
        purchasePanel.add(info, BorderLayout.CENTER);
        purchasePanel.add(purchase, BorderLayout.SOUTH);
        add(purchasePanel);


//========================================================
// Action Listeners
//========================================================

        // Action listener for the 'Purchase' button
        purchase.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");

            // checks is player has enough resources to buy homestead
            if (Driver.player.getGold() >= 1000 && Driver.player.inventory.getResource("Wood") >= 250
                    && Driver.player.inventory.getResource("Stone") >= 250
                    && Driver.player.inventory.getResource("Metal") >= 100) {

                // Confirm purchase from player.
                if (purchaseConfirmation == false) {
                    info.setText(
                            "<html><div style='text-align: center;'> Are you sure you want to purchase the homestead?</div></html>");
                    info.setFont(new Font("Serif", Font.ITALIC, buttonFont));
                    purchaseConfirmation = true;
                    return; // Exit the action listener to wait for confirmation
                }

                // play all resource sound effects when purchased
                SFX.playSound("assets/SFX/coin3.wav"); // play coin sfx
                SFX.playSound("assets/SFX/wood-gathering-sfx.wav");
                SFX.playSound("assets/SFX/stone-gathering-sfx.wav");
                SFX.playSound("assets/SFX/metal-ringing1.wav");

                // Remove resources used to purchase homestead from inventory
                Driver.player.setGold(Driver.player.getGold() - 1000);
                Driver.player.inventory.setResource("Wood", Driver.player.inventory.getResource("Wood") - 250);
                Driver.player.inventory.setResource("Stone", Driver.player.inventory.getResource("Stone") - 250);
                Driver.player.inventory.setResource("Metal", Driver.player.inventory.getResource("Metal") - 100);

                // Update resource labels
                Driver.inventoryUI.updateResourceLabels();

                // Remove the purchase button and info label
                remove(purchasePanel);
                
                back.setFont(new Font("serif", Font.BOLD, buttonFont));
                back.setPreferredSize(new Dimension(width / 10, height / 22));

                //=======================================================
                //
                //Relatively scaling and sizing world components
                //
                // 
                int farmWidth = farm.getPreferredSize().width;
                int farmHeight = farm.getPreferredSize().height;
                int inventory1Width = inventory1.getPreferredSize().width;
                int inventory1Height = inventory1.getPreferredSize().height;
                int craftWidth = craft.getPreferredSize().width;
                int craftHeight = craft.getPreferredSize().height;
                int backWidth = back.getPreferredSize().width;
                int backHeight = back.getPreferredSize().height;

                farm.setBounds((width - farmWidth)/2, 2*(height / 7), farmWidth, farmHeight);
                craft.setBounds((width - craftWidth)/2, 3*(height / 7), craftWidth, craftHeight);
                inventory1.setBounds((width - inventory1Width)/2, 4*(height / 7), inventory1Width, inventory1Height); 
                back.setBounds(backHeight / 2, backHeight / 2, backWidth, backHeight);
                //================================================================
                add(farm);
                add(craft);
                add(inventory1);
                add(back);
                
                revalidate();
                repaint();
                // TODO - increase player's inventory space here.

            } else {
                // if player does not have enough resources output error message.
                info.setText(
                        "<html><div style='text-align: center;'>You do not have enough resources to purchase the homestead.</div></html>");
                info.setFont(new Font("Serif", Font.ITALIC, buttonFont));
            }
        });

        // Action listener for the 'Back' button
        back.addActionListener(e -> {
            Driver.player.inventory.backToHomestead = false;
            // set labels and flag back to default
            info.setText(
                    "<html><div style='text-align: center;'> Property for sale: <br> - 1000 Gold Pieces<br> - 250 wood<br> - 250 Stone<br> - 100 Metal<br><br> The above resources will be taken out from your inventory once purchased. <br>Having a home will increase your inventory space and unlock farming and crafting.</div></html>");
            info.setFont(new Font("Serif", Font.ITALIC, buttonFont));
            purchaseConfirmation = false;
            SFX.playSound("assets/SFX/interface1.wav");
            Driver.changePanel("world");
            MusicPlayer.playMusic("assets/Music/now-we-ride.wav");
            Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default
        });

        // Takes player to farm screen
        farm.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("farm");

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Takes player to craft screen
        craft.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("craft");

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Takes player to inventory screen
        inventory1.addActionListener(e -> {
            try {
                Driver.player.inventory.backToHomestead = true;
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("inventory");
                
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/homestead5.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }
}
