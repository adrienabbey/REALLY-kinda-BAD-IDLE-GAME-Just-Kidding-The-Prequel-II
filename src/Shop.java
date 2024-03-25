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


class Shop extends JPanel {

    private Inventory inventory; // Reference to the Inventory object
    private boolean sellScreenOpen = false; // Flag to track if the sell screen is open
    private boolean buyScreenOpen = false; // Flag to track if the buy screen is open
    private boolean secretMerchantScreenOpen = false; // Flag to track if the secret merchant screen is open. 
    private int secretIncrement = 1; // variable that determines when the secret merchant will appear.
    private JPanel mainPanel1; // Declare buy at class level
    private JPanel mainPanel2; // Declare sell panel at class level
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
            try {
                g.drawImage(ImageIO.read(new File("assets/images/shop2.png")), 0, 0, getWidth(), getHeight(), this);
            } catch (IOException e) {
                //Auto-generated catch block
                e.printStackTrace();
            }
    }
    /**
     * This function hosts the town screen with buttons to buy potions or leave
     * @param player The player character object
     * @throws IOException
     */
    public Shop(Inventory inventory) { // Accepts an Inventory object
        this.inventory = inventory; // Assign the provided Inventory object to the local variable
        JPanel buttonPanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);

        //Whenever calling a getter for the player, it breaks it.
        JLabel name = new JLabel("Name: ");

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
        // add(name);
        add(Box.createRigidArea(new Dimension(100, 330)));
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

        //For loop that formats all the buttons
        for (int i = 0; i < buttons.size(); i++){
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
        // Buy button adds a potion to the player's inventory


        buy.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            if (buyScreenOpen == false) { // Check if buy screen is not already open
                buyScreenOpen = true; // Set buy screen as open
            
            // Remove existing buttons
            // this.removeAll();
            // this.revalidate();
            // this.repaint();

            // Create a panel to hold labels and the buy panel
            mainPanel1 = new JPanel(new BorderLayout());

            // Create a label to display the player's gold count
            JLabel goldLabel = new JLabel("  Gold: " + inventory.getResource("Gold") + "  ");
            goldLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
            goldLabel.setAlignmentX(CENTER_ALIGNMENT);
            // format gold label
            goldLabel.setForeground(new Color(253, 236, 166)); // set color of text
            goldLabel.setBackground(new Color(139, 69, 19)); //set color of background
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
                repaint(); //  repaints the GUI components
            });

            // Create buy label message
            JLabel buy_label = new JLabel(" Buy ");
            buy_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
            //format buy label
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
            buyPanel.setBackground(new Color(0,0,0, 192)); 
            buyPanel.setOpaque(true); 
        
            // Add all items from inventory to shop. 
            for (String resourceName : inventory.getResources().keySet()) {
                if (!(resourceName == "Legendary Potion of Lepus") && !(resourceName == "Gold")) { // remove Lepus potion and Gold from buy list
                    
                    JButton buyItemButton = new JButton("Buy " + resourceName + " (" + inventory.getResource(resourceName) + ")");
                    // Format buttons
                    buyItemButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    // format gold label
                    buyItemButton.setForeground(new Color(253, 236, 166)); 
                    buyItemButton.setBackground(new Color(102, 72, 54)); 
                    buyItemButton.setOpaque(true); 
                    
                    buyItemButton.addActionListener(sellEvent -> {
                        
                        // If Gold is greater than 0 then buy resource, else output error message. 
                        if ((inventory.getResource("Gold") > 0)) {
                            err_message.setText("");
                            inventory.setResource(resourceName, inventory.getResource(resourceName)  + 1); // add the resource from inventory
                            int currentGold = inventory.getResource("Gold"); // update current resource amount
                            inventory.setResource("Gold", currentGold - 1); // decrease gold 
                            inventory.updateResourceLabels(); // Update the labels
                            goldLabel.setText("  Gold: " + inventory.getResource("Gold") + "  "); // Update the gold label
                            buyItemButton.setText("Buy " + resourceName + " (" + (inventory.getResource(resourceName)) + ")"); // Update the buy button label
                            SFX.playSound("assets/SFX/coin3.wav");
                            
                        } else {
                            err_message.setText(" Cannot buy item, no more gold.");
                        }
                    });
                    buyPanel.add(buyItemButton);
                }}
        
            scrollPane.setViewportView(buyPanel);
            scrollPane.setPreferredSize(new Dimension(400, 400));

                // Add components to the main panel
                mainPanel1.add(closeButtonPanel, BorderLayout.NORTH);
                mainPanel1.add(goldLabel, BorderLayout.EAST);
                mainPanel1.add(scrollPane, BorderLayout.CENTER);
                mainPanel1.add(err_message, BorderLayout.SOUTH);
                mainPanel1.add(buy_label, BorderLayout.WEST);
        
            // Add the scroll pane to the center of the Shop panel
            add(mainPanel1, BorderLayout.CENTER);

            // tells the layout manager to recalculate the layout of the component. This is necessary when adding or removing components, or when changing the size or position of a component.
            revalidate();

            // tells the component to redraw itself. This is necessary when the appearance of the component has changed, such as when the text in a label is changed, or when the image in an image icon is changed.
            repaint();

        } else {
            // Remove the buy panel and the close button
            remove(mainPanel1);
            buyScreenOpen = false; // Set sell screen as closed
            revalidate(); // recalculate the layout of the components within the container
            repaint(); //  repaints the GUI components
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
        // Sell button to sell equipment or potions for gold

        
        sell.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav");
            if (sellScreenOpen == false) { // Check if sell screen is not already open
                sellScreenOpen = true; // Set sell screen as open

            // Remove existing buttons
            // this.removeAll();
            // this.revalidate();
            // this.repaint();

            // Create a panel to hold labels and the sell panel
            mainPanel2 = new JPanel(new BorderLayout());

            // Create a label to display the player's gold count
            JLabel goldLabel = new JLabel("  Gold:  " + inventory.getResource("Gold") + "  ");
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
                repaint(); //  repaints the GUI components
            });

            // Create sell label message
            JLabel sell_label = new JLabel(" Sell ");
            sell_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
            //format sell label
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
            sellPanel.setBackground(new Color(0,0,0, 192)); 
            sellPanel.setOpaque(true); 

            // Add items from the inventory to the sell panel
            for (String resourceName : inventory.getResources().keySet()) {
                if (!(resourceName == "Gold")) { // remove Gold from sell list
                    
                // if the resource amount exceeds 1 from your inventory, add it to the sell menu as a possible item to sell.
                if (inventory.getResource(resourceName) > 0) {
                    JButton sellItemButton = new JButton("Sell " + resourceName + " (" + inventory.getResource(resourceName) + ")");
                    // format buttons
                    sellItemButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    sellItemButton.setForeground(new Color(253, 236, 166)); 
                    sellItemButton.setBackground(new Color(102, 72, 54)); 
                    sellItemButton.setOpaque(true); 
                    
                    sellItemButton.addActionListener(sellEvent -> {
                        // If resource is greater than 0, it is able to sell, else output error message. 
                        if ((inventory.getResource(resourceName) > 0)) {
                            err_message.setText("");
                            inventory.setResource(resourceName, inventory.getResource(resourceName)  - 1); // minus the resource from inventory
                            int currentGold = inventory.getResource("Gold"); // update current resource amount
                            inventory.setResource("Gold", currentGold + 1); // increase gold 
                            inventory.updateResourceLabels(); // Update the labels
                            goldLabel.setText("  Gold: " + inventory.getResource("Gold") + "  "); // Update the gold label
                            sellItemButton.setText("Sell " + resourceName + " (" + (inventory.getResource(resourceName)) + ")"); // Update the sell button label
                            SFX.playSound("assets/SFX/coin3.wav");
                        } else {
                            err_message.setText("Cannot sell item, no items left.");
                        }
                    
                    });
                    sellPanel.add(sellItemButton);
                }}
            }
        
            scrollPane.setViewportView(sellPanel);
            scrollPane.setPreferredSize(new Dimension(400, 400));

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
        // Buy button adds a potion to the player's inventory
        secretMerchant.addActionListener(e -> {
            SFX.playSound("assets/SFX/interface1.wav"); // play button sound effect

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
                    Image backgroundImage = ImageIO.read(new File("assets/images/cat1.png"));
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
            JLabel goldLabel = new JLabel("    Gold: " + inventory.getResource("Gold") + " ");
            goldLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
            goldLabel.setAlignmentX(CENTER_ALIGNMENT);
            // format gold label
            goldLabel.setPreferredSize(new Dimension(140,65));
            goldLabel.setPreferredSize(new Dimension(140,65));
            goldLabel.setForeground(new Color(253, 236, 166)); 
            goldLabel.setBackground(new Color(0,0,0)); 
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

                // Adding the buttons back to shop panel.
                add(Box.createVerticalGlue());
                add(Box.createRigidArea(new Dimension(100, 330)));
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
                repaint(); //  repaints the GUI components
            });

            // Create buy label message
            JLabel buy_label = new JLabel("                                               Secret Merchant Shop ");
            //buy_label.setFont(new Font("Lucida Console", Font.ITALIC, 28));
            buy_label.setFont(new Font("Times New Roman", Font.BOLD, 28));
            buy_label.setPreferredSize(new Dimension(170,40));
            buy_label.setPreferredSize(new Dimension(170,40));
            // format gold label
            buy_label.setForeground(new Color(253, 236, 166)); 
            buy_label.setBackground(new Color(0,0,0)); 
            buy_label.setOpaque(true); 

            // Create error message
            JLabel err_message = new JLabel("");
            err_message.setFont(new Font("Times New Roman", Font.BOLD, 24));
            // format label
            err_message.setPreferredSize(new Dimension(170,40));
            err_message.setPreferredSize(new Dimension(170,40));
            err_message.setForeground(new Color(253, 236, 166)); 
            err_message.setBackground(new Color(0,0,0)); 
            err_message.setOpaque(true); 

            // Add the close button to the top right corner
            JPanel closeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            closeButtonPanel.add(closeButton);
            closeButton.setPreferredSize(new Dimension(90,30));
            closeButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
            // format panel
            closeButtonPanel.setForeground(new Color(253, 236, 166)); 
            closeButtonPanel.setBackground(new Color(0,0,0)); 
            closeButtonPanel.setOpaque(true); 
        
            // Create a menu for buying items
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setForeground(new Color(253, 236, 166)); 
            scrollPane.setBackground(new Color(139, 69, 19)); 
            scrollPane.setOpaque(true); 

            JPanel buyPanel = new JPanel();
            buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.Y_AXIS));
            // format panel
            buyPanel.setBackground(new Color(0,0,0, 192)); 
            buyPanel.setOpaque(true); 
        
            // Add all items from inventory to shop. 
            for (String resourceName : inventory.getResources().keySet()) {
                if (!(resourceName == "Gold")) {

                    JButton buyItemButton = new JButton(" Buy " + resourceName + " (" + inventory.getResource(resourceName) + ")");
                    // Format buttons
                    buyItemButton.setFont(new Font("Times New Roman", Font.PLAIN, 27));
                    buyItemButton.setForeground(new Color(253, 236, 166)); 
                    buyItemButton.setBackground(new Color(102, 72, 54)); 
                    buyItemButton.setOpaque(true); 

                    // Code for when a buy button is pressed.
                    buyItemButton.addActionListener(sellEvent -> {
                        // If Gold is greater than 0 then buy resource,, else output error message. 
                        if ((inventory.getResource("Gold") > 0)) {
                            err_message.setText("");
                            inventory.setResource(resourceName, inventory.getResource(resourceName)  + 1); // add the resource from inventory
                            int currentGold = inventory.getResource("Gold"); // update current resource amount
                            inventory.setResource("Gold", currentGold - 1); // decrease gold 
                            inventory.updateResourceLabels(); // Update the labels
                            goldLabel.setText("    Gold: " + inventory.getResource("Gold") + " "); // Update the gold label
                            buyItemButton.setText("Buy " + resourceName + " (" + (inventory.getResource(resourceName)) + ")"); // Update the buy button label
                            SFX.playSound("assets/SFX/cat-purring-and-meow-5928.wav");
                        } else {
                            err_message.setText("                                                  Cannot buy item, no more gold.  ");
                        }
                    });
                    buyPanel.add(buyItemButton);
                }}
        
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
        } else {
            // JOptionPane.showMessageDialog(this, "Only one secret screen can be opened at a time.", "Secret Merchant Screen Error", JOptionPane.ERROR_MESSAGE);
        }
    });


        // Goes to inventory
        inventory1.addActionListener(e -> {
            try {
                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("inventory");
                 // TODO: Add inventory implementation
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Leave button takes you back to the town panel
        leave.addActionListener(e -> {
            try {

                secretIncrement++; // increment scrent merchant counter. 
                if (secretIncrement % 4 == 0) { // adds secret merchant screen every 3 bazaar visits. 
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

                // tells the layout manager to recalculate the layout of the component. This is necessary when adding or removing components, or when changing the size or position of a component.
                revalidate();

                // tells the component to redraw itself. This is necessary when the appearance of the component has changed, such as when the text in a label is changed, or when the image in an image icon is changed.
                repaint();

                SFX.playSound("assets/SFX/interface1.wav");
                Driver.changePanel("town");
                MusicPlayer.playMusic("assets/Music/Village Consort.wav");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
