import javax.swing.*;

class Dungeon extends JPanel {
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */
    public Dungeon() {

        // TODO add the actual enemy generation and combat here

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // This is creating all the objects that will be displayed on the screen
        JButton run = new JButton("Run");

        // This is adding all objects to the screen, and controlling layout
        add(Box.createVerticalGlue());
        add(run);
        add(Box.createVerticalGlue());

        // control the layout of the buttons
        run.setAlignmentX(CENTER_ALIGNMENT);

        // This button will be used to run away from combat
        run.addActionListener(e -> {
            try {
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}