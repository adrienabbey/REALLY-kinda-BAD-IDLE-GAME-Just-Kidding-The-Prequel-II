/*
 * Bazaar class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Muhammed Abushamma, et al., Mar. 2024
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;

/*
 * Implements the "Bazaar" panel which is accessed throgh the Town screen. 
 * The bazaar houses the implementation for the Buy and Sell screens, where 
 * the player can purchase and sell items from their inventory. The inventory
 *  panel can also be accessed from the bazaar. Any item or equipment the player
 *  has can be sold, though if they would like to buy it back the item will be at
 *  a much steeper price than the palyer sold it for. A fourth screen can be 
 * accessed from the bazaar titled "Secret Merchant", which allows the player 
 * to buy speacial items. The button that takes the player to this screen only 
 * appear after every three bazaar visits. 
 * 
 * Current items that can be bought at the bazaar: Wood, Stone, Metal, Meat, Pelt, Potion,
 * Tongue Fern, Spleenwort, Magical Essence...
 * 
 Current items that can be bought at the secret merchant: Wood, Stone, Metal, Meat, Pelt, 
 Potion, Tongue Fern, Spleenwort, Magical Essence, Lengendary Potion of Lepus...
 *
 * TODO: add prices to items. 
 */

class Bazaar extends JPanel {

    private boolean sellScreenOpen = false; // Flag to track if the sell screen is open
    private boolean buyScreenOpen = false; // Flag to track if the buy screen is open
    private boolean secretMerchantScreenOpen = false; // Flag to track if the secret merchant screen is open.
    private int secretIncrement = 1; // variable that determines when the secret merchant will appear.
    private JPanel mainPanel1; // Declare buy panel at class level
    private JPanel mainPanel2; // Declare sell panel at class level

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("assets/images/Shop2.png")), 0, 0, getWidth(), getHeight(), this);
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This function hosts the town screen with buttons to buy potions or leave
     * 
     * @param player The player character object
     * @throws IOException
     */
    public Bazaar() { // Accepts an Inventory object
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);

        JButton buy = new JButton("Buy");
        buttons.add(buy);
        JButton leave = new JButton("Leave");
        buttons.add(leave);
        JButton inventory1 = new JButton("Inventory");
        buttons.add(inventory1);
        JButton sell = new JButton("Sell");
        buttons.add(sell);
        JButton secretMerchant = new JButton("Secret Merchant");
        buttons.add(secretMerchant);

        // Adding the buttons to the shop panel and controlling layout
        add(Box.createVerticalGlue());
        add(buy);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(sell);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(inventory1);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(leave);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(secretMerchant);
        add(Box.createVerticalGlue());

        // For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setAlignmentX(CENTER_ALIGNMENT);
            buttons.get(i).setPreferredSize(new Dimension(200, 80));
            buttons.get(i).setMaximumSize(new Dimension(200, 80));
            buttons.get(i).setBackground(customColorBrown);
            buttons.get(i).setForeground(customColorBeige);
            buttons.get(i).setFont(new Font("Times New Roman", Font.BOLD, 24));
        }

        secretMerchant.setMaximumSize(new Dimension(250, 80));
        secretMerchant.setMaximumSize(new Dimension(250, 80));

        this.setAlignmentX(CENTER_ALIGNMENT);

        remove(secretMerchant);

        /*
         * 
         * 
         * Buy Screen
         * 
         * 
         * 
         * 
         */
        // Buy button opens the UI for the buy screen
        buy.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            if (buyScreenOpen == false) { // Check if buy screen is not already open
                buyScreenOpen = true; // Set buy screen as open

                // check is sell screen is open, if yes then close it
                if (sellScreenOpen) {
                    remove(mainPanel2); // remove sell screen if open
                    sellScreenOpen = false; // adjust flag accordingly
                    revalidate();
                    repaint();
                }

                // Create a panel to hold labels and the buy panel
                mainPanel1 = new JPanel(new BorderLayout());
                mainPanel1.setMaximumSize(new Dimension(getWidth(), 500));

                // Create a label to display the player's gold count
                JLabel goldLabel = new JLabel("  Gold: " + Driver.player.getGold() + "  ");
                goldLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
                goldLabel.setAlignmentX(CENTER_ALIGNMENT);
                // format gold label
                goldLabel.setForeground(new Color(253, 236, 166)); // set color of text
                goldLabel.setBackground(new Color(139, 69, 19)); // set color of background
                goldLabel.setOpaque(true); // background is visible

                // Create a close button
                JButton closeButton = new JButton("Close");
                // format label
                closeButton.setForeground(new Color(139, 69, 19));
                closeButton.setBackground(new Color(253, 236, 166));
                closeButton.setOpaque(true);
                closeButton.addActionListener(closeEvent -> {
                    SFX.playSound("assets/SFX/interface1.wav");
                    // Remove the buy panel and the close button
                    remove(mainPanel1);
                    buyScreenOpen = false; // Set sell screen as closed
                    revalidate(); // recalculate the layout of the components within the container
                    repaint(); // repaints the GUI components
                });

                // Create buy label message
                JLabel buy_label = new JLabel(" Buy ");
                buy_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
                // format buy label
                buy_label.setForeground(new Color(253, 236, 166));
                buy_label.setBackground(new Color(139, 69, 19));
                buy_label.setOpaque(true);

                // Create error message
                JLabel err_message = new JLabel("");
                err_message.setFont(new Font("Times New Roman", Font.BOLD, 24));
                err_message.setForeground(new Color(253, 236, 166));
                err_message.setBackground(new Color(139, 69, 19));
                err_message.setOpaque(true);

                // Add the close button to the top right corner
                JPanel closeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                closeButtonPanel.add(closeButton);
                // format panel
                closeButtonPanel.setForeground(new Color(253, 236, 166));
                closeButtonPanel.setBackground(new Color(139, 69, 19));
                closeButtonPanel.setOpaque(true);

                // Create a menu for buying items
                JScrollPane scrollPane = new JScrollPane();
                // format panel
                scrollPane.setForeground(new Color(253, 236, 166));
                scrollPane.setBackground(new Color(139, 69, 19));
                scrollPane.setOpaque(true);

                // format Panel
                JPanel buyPanel = new JPanel();
                buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.Y_AXIS));
                buyPanel.setBackground(new Color(0, 0, 0, 192));
                buyPanel.setOpaque(true);


                /* Add resources from inventory to shop. */

                // Items each cost 5 gold. 
                for (String resourceName : Driver.player.inventory.getResources().keySet()) {
                    if (!(resourceName == "Legendary Potion of Lepus")) { // remove Lepus potion  from buy list
                        JButton buyItemButton = new JButton(
                                "5 Gold - Buy " + resourceName + " (" + Driver.player.inventory.getResource(resourceName) + ")");
                        // Format buttons
                        buyItemButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                        // format gold label
                        buyItemButton.setForeground(new Color(253, 236, 166));
                        buyItemButton.setBackground(new Color(102, 72, 54));
                        buyItemButton.setOpaque(true);

                        buyItemButton.addActionListener(sellEvent -> {

                            // If Gold is greater than 0 then buy resource, else output error message.
                            if ((Driver.player.getGold() >= 5)) {
                                err_message.setText("");
                                Driver.player.inventory.setResource(resourceName,
                                        Driver.player.inventory.getResource(resourceName) + 1); // add the resource from inventory
                                Driver.player.setGold(Driver.player.getGold() - 5); // decrease gold
                                Driver.inventoryUI.updateResourceLabels(); // Update the labels
                                goldLabel.setText("  Gold: " + Driver.player.getGold() + "  "); // Update the gold label
                                buyItemButton.setText(
                                        "5 Gold - Buy " + resourceName + " ("
                                                + (Driver.player.inventory.getResource(resourceName)) + ")"); // Update buy button label
                                SFX.playSound("assets/SFX/coin3.wav");

                            } else {
                                err_message.setText(" Cannot buy item, not enough gold.");
                            }
                        });
                        buyPanel.add(buyItemButton);
                    }
                }

                /* Adding ability to buy potions from bazaar */

                // Potions cost 10 gold and is limited by the potion belt size.
                JButton buyPotionButton = new JButton("10 Gold - Buy Potion (" + Driver.player.getPotionCount() + ")");
                buyPotionButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                buyPotionButton.setForeground(new Color(253, 236, 166));
                buyPotionButton.setBackground(new Color(102, 72, 54));
                buyPotionButton.setOpaque(true);

                buyPotionButton.addActionListener(event -> {
                    if (Driver.player.getGold() >= 10 && Driver.player.getPotionCount() < Driver.player.getPotionBeltSize()) {
                        err_message.setText("");
                        Driver.player.addPotion(1);
                        Driver.player.setGold(Driver.player.getGold() - 10);
                        Driver.inventoryUI.updateResourceLabels();
                        goldLabel.setText("  Gold: " + Driver.player.getGold() + "  ");
                        SFX.playSound("assets/SFX/coin3.wav");
                        buyPotionButton.setText("10 Gold - Buy Potion (" + Driver.player.getPotionCount() + ")");
                    } else {
                        if (Driver.player.getPotionCount() >= Driver.player.getPotionBeltSize()) {
                            err_message.setText("Cannot buy potion, potion belt is full.");
                        } else {
                            err_message.setText("Cannot buy potion, not enough gold.");
                        }
                    }
                });
                buyPanel.add(buyPotionButton);

                /* Add the ability to upgrade potion belt size to buy screen */ 
                
                // Each upgrade cost 100 gold times the current belt level. 
                JButton upgradePotionBeltSizeButton = new JButton("100 Gold - Upgrade Potion Belt: Current Level (" + Driver.player.getPotionBeltSize() + ")");
                upgradePotionBeltSizeButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                upgradePotionBeltSizeButton.setForeground(new Color(253, 236, 166));
                upgradePotionBeltSizeButton.setBackground(new Color(102, 72, 54));
                upgradePotionBeltSizeButton.setOpaque(true);
                
                upgradePotionBeltSizeButton.addActionListener(event -> {
                    if (Driver.player.getGold() >= Driver.player.getPotionBeltSize() * 100) {
                        err_message.setText("");
                        Driver.player.setPotionBeltSize(Driver.player.getPotionBeltSize() + 1);
                        Driver.player.setGold(Driver.player.getGold() - (Driver.player.getPotionBeltSize() * 100));
                        Driver.inventoryUI.updateResourceLabels();
                        goldLabel.setText("  Gold: " + Driver.player.getGold() + "  ");
                        SFX.playSound("assets/SFX/coin3.wav");
                        upgradePotionBeltSizeButton.setText(Driver.player.getPotionBeltSize() * 100 + " Gold - Upgrade Potion Belt: Current Level (" + Driver.player.getPotionBeltSize() + ")");
                    } else {
                            err_message.setText("Cannot upgrade potion belt, not enough gold.");
                        }
                });
                buyPanel.add(upgradePotionBeltSizeButton);

                scrollPane.setViewportView(buyPanel);

                // Add components to the main panel
                mainPanel1.add(closeButtonPanel, BorderLayout.NORTH);
                mainPanel1.add(goldLabel, BorderLayout.EAST);
                mainPanel1.add(scrollPane, BorderLayout.CENTER);
                mainPanel1.add(err_message, BorderLayout.SOUTH);
                mainPanel1.add(buy_label, BorderLayout.WEST);

                // Add the scroll pane to the center of the Shop panel
                add(mainPanel1);

                // tells the layout manager to recalculate the layout of the component. This is
                // necessary when adding or removing components, or when changing the size or
                // position of a component.
                revalidate();

                // tells the component to redraw itself. This is necessary when the appearance
                // of the component has changed, such as when the text in a label is changed, or
                // when the image in an image icon is changed.
                repaint();

            } else {
                // Remove the buy panel and the close button
                remove(mainPanel1);
                buyScreenOpen = false; // Set sell screen as closed
                revalidate(); // recalculate the layout of the components within the container
                repaint(); // repaints the GUI components
            }
        });

        /*
         * 
         * Sell Screen
         * 
         * 
         * 
         * 
         */
        // sell button opens the UI for sell screen        
        sell.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            if (sellScreenOpen == false) { // Check if sell screen is not already open
                sellScreenOpen = true; // Set sell screen as open

            // check if buy screen is opne, if so close it
            if (buyScreenOpen) {
                remove(mainPanel1); // remove buy screen if open
                buyScreenOpen = false; // adjust flag accordingly
                revalidate();
                repaint();
                }

                // Create a panel to hold labels and the sell panel
                mainPanel2 = new JPanel(new BorderLayout());
                mainPanel2.setMaximumSize(new Dimension(getWidth(), 500));

                // Create a label to display the player's gold count
                JLabel goldLabel = new JLabel("  Gold:  " + Driver.player.getGold() + "  ");
                goldLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
                goldLabel.setAlignmentX(CENTER_ALIGNMENT);
                // format gold label
                goldLabel.setForeground(new Color(253, 236, 166));
                goldLabel.setBackground(new Color(139, 69, 19));
                goldLabel.setOpaque(true);

                // Create a close button
                JButton closeButton = new JButton("Close");
                // format close button
                closeButton.setForeground(new Color(139, 69, 19));
                closeButton.setBackground(new Color(253, 236, 166));
                closeButton.setOpaque(true);

                closeButton.addActionListener(closeEvent -> {
                    SFX.playSound("assets/SFX/interface1.wav");
                    // Remove the buy panel and the close button
                    remove(mainPanel2);
                    sellScreenOpen = false; // Set sell screen as closed
                    revalidate(); // recalculate the layout of the components within the container
                    repaint(); // repaints the GUI components
                });

                // Create sell label message
                JLabel sell_label = new JLabel(" Sell ");
                sell_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
                // format sell label
                sell_label.setForeground(new Color(253, 236, 166));
                sell_label.setBackground(new Color(139, 69, 19));
                sell_label.setOpaque(true);

                // Create error message
                JLabel err_message = new JLabel("");
                err_message.setFont(new Font("Times New Roman", Font.BOLD, 24));
                err_message.setForeground(new Color(253, 236, 166));
                err_message.setBackground(new Color(139, 69, 19));
                err_message.setOpaque(true);

                // Add the close button to the top right corner
                JPanel closeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                closeButtonPanel.add(closeButton);
                // format panel
                closeButtonPanel.setForeground(new Color(253, 236, 166));
                closeButtonPanel.setBackground(new Color(139, 69, 19));
                closeButtonPanel.setOpaque(true);

                // Create a menu for buying items
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setForeground(new Color(253, 236, 166));
                scrollPane.setBackground(new Color(139, 69, 19));
                scrollPane.setOpaque(true);

                JPanel sellPanel = new JPanel();
                sellPanel.setLayout(new BoxLayout(sellPanel, BoxLayout.Y_AXIS));
                sellPanel.setBackground(new Color(0, 0, 0, 192));
                sellPanel.setOpaque(true);

                // Add items from the inventory to the sell panel
                for (String resourceName : Driver.player.inventory.getResources().keySet()) {
                    if (!(resourceName == "Gold")) { // remove Gold from sell list

                        // if the resource amount exceeds 1 from your inventory, add it to the sell menu
                        // as a possible item to sell.
                        if (Driver.player.inventory.getResource(resourceName) > 0) {
                            JButton sellItemButton = new JButton(
                                    "Sell " + resourceName + " (" + Driver.player.inventory.getResource(resourceName)
                                            + ")");
                            // format buttons
                            sellItemButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                            sellItemButton.setForeground(new Color(253, 236, 166));
                            sellItemButton.setBackground(new Color(102, 72, 54));
                            sellItemButton.setOpaque(true);

                            sellItemButton.addActionListener(sellEvent -> {
                                // If resource is greater than 0, it is able to sell, else output error message.
                                if ((Driver.player.inventory.getResource(resourceName) > 0)) {
                                    err_message.setText("");
                                    Driver.player.inventory.setResource(resourceName,
                                            Driver.player.inventory.getResource(resourceName) - 1); // minus resource from inventory
                                    Driver.player.setGold(Driver.player.getGold() + 1);
                                    Driver.inventoryUI.updateResourceLabels(); // Update the labels
                                    goldLabel.setText("  Gold: " + Driver.player.getGold() + "  "); // Update the gold label
                                    sellItemButton.setText("Sell " + resourceName + " ("
                                            + (Driver.player.inventory.getResource(resourceName)) + ")"); // Update the sell button label
                                    SFX.playSound("assets/SFX/coin3.wav");
                                } else {
                                    err_message.setText("Cannot sell item, no items left.");
                                }

                            });
                            sellPanel.add(sellItemButton);
                        }
                    }
                }

                scrollPane.setViewportView(sellPanel);

                // Add components to the main panel
                mainPanel2.add(closeButtonPanel, BorderLayout.NORTH);
                mainPanel2.add(goldLabel, BorderLayout.EAST);
                mainPanel2.add(scrollPane, BorderLayout.CENTER);
                mainPanel2.add(err_message, BorderLayout.SOUTH);
                mainPanel2.add(sell_label, BorderLayout.WEST);

                // Add the scroll pane to the center of the Shop panel
                add(mainPanel2, BorderLayout.CENTER);
                revalidate();
                repaint();
            } else {
                // Remove the sell panel
                remove(mainPanel2);
                sellScreenOpen = false; // Set sell screen as closed
                revalidate(); // recalculate the layout of the components within the container
                repaint(); // repaints the GUI components
            }
        });

        /*
         * 
         * 
         * 
         * 
         * Implementation for the secret merchant subpanel.
         * TODO: Add secret items.
         * 
         * 
         */
        // secret merchant button opens the secret merchant screen, removes all of bazaar components when doing so, re-adds and repaints original bazaar components after leaving secret merhcnat screen. 
        secretMerchant.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav"); // play button sound effect
            MusicPlayer.playMusic("assets/Music/secret-merchant-bgm.wav");

            // Check if secret merchant screen is not already open
            if (!secretMerchantScreenOpen) {
                secretMerchantScreenOpen = true;

                // Remove existing buttons
                this.removeAll();
                this.revalidate();
                this.repaint();    
                
                // Create a panel for the secret merchant screen
                JPanel secretMerchantPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    try {
                        // Load and draw the background image
                        Image backgroundImage = ImageIO.read(new File("assets\\images\\1920x1080-black-solid-color-background.jpg"));
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }};
                secretMerchantPanel.setLayout(new BorderLayout());

                // Create a JLabel to hold the cat image
                JLabel catLabel = new JLabel();
                try {
                    // Load the image
                    BufferedImage catImage = ImageIO.read(new File("assets/images/cat1.png"));
                    // Set the image on the label
                    catLabel.setIcon(new ImageIcon(catImage));
                    // Set the preferred size of the label to the size of the image
                    catLabel.setPreferredSize(new Dimension(catImage.getWidth(), catImage.getHeight()));

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                // Add the cat label to the panel
                secretMerchantPanel.add(catLabel, BorderLayout.WEST);

                // Create a panel to hold the main conte(excluding background)
                JPanel mainContentPanel = new JPanel(new BorderLayout());

                // Create a panel to hold the gold count label and the sell panel
                JPanel mainPanel = new JPanel(new BorderLayout());

                // Create a label to display the player's gold count
                JLabel goldLabel = new JLabel("    Gold: " + Driver.player.getGold() + " ");
                goldLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
                goldLabel.setAlignmentX(CENTER_ALIGNMENT);
                // format gold label
                goldLabel.setPreferredSize(new Dimension(140, 65));
                goldLabel.setPreferredSize(new Dimension(140, 65));
                goldLabel.setForeground(new Color(253, 236, 166));
                goldLabel.setBackground(new Color(0, 0, 0));
                goldLabel.setOpaque(true);

                // Create a close button
                JButton closeButton = new JButton("Close");
                closeButton.setForeground(new Color(139, 69, 19));
                closeButton.setBackground(new Color(253, 236, 166));
                closeButton.setOpaque(true);

                closeButton.addActionListener(closeEvent -> {
                    SFX.playSound("assets/SFX/interface1.wav");

                    // Remove the sell panel and the close button
                    remove(mainPanel);
                    remove(secretMerchantPanel);

                    // Adding the buttons to the shop panel and controlling layout
                    add(Box.createVerticalGlue());
                    add(buy);
                    add(Box.createRigidArea(new Dimension(0, 20)));
                    add(sell);
                    add(Box.createRigidArea(new Dimension(0, 20)));
                    add(inventory1);
                    add(Box.createRigidArea(new Dimension(0, 20)));
                    add(leave);
                    add(Box.createRigidArea(new Dimension(0, 20)));
                    add(secretMerchant);
                    add(Box.createVerticalGlue());

                    secretMerchantScreenOpen = false; // Set sell screen as closed
                    revalidate(); // recalculate the layout of the components within the container
                    repaint(); // repaints the GUI components
                    MusicPlayer.playMusic("assets/Music/Turku, Nomads of the Silk Road - -Uskudara Gideriken.wav");
                });

                // Create buy label message
                JLabel buy_label = new JLabel("                                               Secret Merchant Shop ");
                // buy_label.setFont(new Font("Lucida Console", Font.ITALIC, 28));
                buy_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
                buy_label.setPreferredSize(new Dimension(170, 40));
                buy_label.setPreferredSize(new Dimension(170, 40));
                // format gold label
                buy_label.setForeground(new Color(253, 236, 166));
                buy_label.setBackground(new Color(0, 0, 0));
                buy_label.setOpaque(true);

                // Create error message
                JLabel err_message = new JLabel("");
                err_message.setFont(new Font("Times New Roman", Font.BOLD, 24));
                // format label
                err_message.setPreferredSize(new Dimension(170, 40));
                err_message.setPreferredSize(new Dimension(170, 40));
                err_message.setForeground(new Color(253, 236, 166));
                err_message.setBackground(new Color(0, 0, 0));
                err_message.setOpaque(true);

                // Add the close button to the top right corner
                JPanel closeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                closeButtonPanel.add(closeButton);
                closeButton.setPreferredSize(new Dimension(90, 30));
                closeButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
                // format panel
                closeButtonPanel.setForeground(new Color(253, 236, 166));
                closeButtonPanel.setBackground(new Color(0, 0, 0));
                closeButtonPanel.setOpaque(true);

                // Create a menu for buying items
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setForeground(new Color(253, 236, 166));
                scrollPane.setBackground(new Color(139, 69, 19));
                scrollPane.setOpaque(true);

                JPanel buyPanel = new JPanel();
                buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.Y_AXIS));
                // format panel
                buyPanel.setBackground(new Color(0, 0, 0, 192));
                buyPanel.setOpaque(true);

                // Add all items from inventory to shop.
                for (String resourceName : Driver.player.inventory.getResources().keySet()) {
                    if (!(resourceName == "Gold")) {

                        JButton buyItemButton = new JButton(
                                " Buy " + resourceName + " (" + Driver.player.inventory.getResource(resourceName)
                                        + ")");
                        // Format buttons
                        buyItemButton.setFont(new Font("Times New Roman", Font.PLAIN, 27));
                        buyItemButton.setForeground(new Color(253, 236, 166));
                        buyItemButton.setBackground(new Color(102, 72, 54));
                        buyItemButton.setOpaque(true);

                        // Code for when a buy button is pressed.
                        buyItemButton.addActionListener(sellEvent -> {
                            // If Gold is greater than 0 then buy resource,, else output error message.
                            if ((Driver.player.getGold() > 0)) {
                                err_message.setText("");
                                Driver.player.inventory.setResource(resourceName,
                                        Driver.player.inventory.getResource(resourceName) + 1); // add the rsource to inventory
                                Driver.player.setGold(Driver.player.getGold() - 1);
                                Driver.inventoryUI.updateResourceLabels(); // Update the labels
                                goldLabel.setText("    Gold: " + Driver.player.getGold() + " "); // Update the gold
                                buyItemButton.setText(
                                        "Buy " + resourceName + " ("
                                                + (Driver.player.inventory.getResource(resourceName)) + ")"); // Update buy button label
                                if (resourceName == "Legendary Potion of Lepus") {
                                    SFX.playSound("assets/SFX/cat-purring-and-meow-5928.wav");
                                } else {
                                    SFX.playSound("assets/SFX/coin3.wav");
                                }

                            } else {
                                err_message.setText(
                                        "                                                  Cannot buy item, no more gold.  ");
                            }
                        });
                        buyPanel.add(buyItemButton);
                    }
                }

                scrollPane.setViewportView(buyPanel);
                scrollPane.setPreferredSize(new Dimension(660, 200));

                // Add components to the main panel
                mainPanel.add(closeButtonPanel, BorderLayout.EAST);
                mainPanel.add(goldLabel, BorderLayout.WEST);
                mainPanel.add(scrollPane, BorderLayout.CENTER);
                mainPanel.add(err_message, BorderLayout.SOUTH);
                mainPanel.add(buy_label, BorderLayout.NORTH);

            // Add the main panel to the main content panel
            mainContentPanel.add(mainPanel);

            // Add the main content panel to the secret merchant panel
            secretMerchantPanel.add(mainContentPanel, BorderLayout.EAST);

            // Add background image to secret merchant panel
             add(secretMerchantPanel);

            revalidate();
            repaint();
            SFX.playSound("assets/SFX/meow-01-86859.wav"); // play meow sound effect when entering secret shop
        }
    });

        // Goes to inventory
        inventory1.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("inventory");
                 // TODO: Add proper inventory UI implementation
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Leave button takes you back to the town panel
        leave.addActionListener(e -> {
            try {

                secretIncrement++; // increment scrent merchant counter. 
                if (secretIncrement % 4 == 0) { // adds secret merchant screen after every 4 fourth visit. 
                    add(secretMerchant);
                } else {
                    remove(secretMerchant); // removes secert merchant screen after leaving.
                }

                if (buyScreenOpen) {
                    remove(mainPanel1); // remove buy screen if open
                    buyScreenOpen = false; // adjust flag accordingly
                    revalidate();
                    repaint();
                }

                if (sellScreenOpen) {
                    remove(mainPanel2); // remove sell screen if open
                    sellScreenOpen = false; // adjust flag accordingly
                    revalidate();
                    repaint();
                }

                // tells the layout manager to recalculate the layout of the component.
                revalidate();

                // tells the component to redraw itself. 
                repaint();
                SFX.playSound("assets/SFX/interface1.wav");
                SFX.stopAllNonLoopingSounds();
                Driver.changePanel("town");
                MusicPlayer.playMusic("assets/Music/town-bgm.wav");
                SFX.playSound("assets/SFX/town-ambient-sfx2.wav", true); 
                Driver.savePlayer(Driver.getPlayer(), "save-files/saveFile1.sav"); // save player data to save slot 1 by default 
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
