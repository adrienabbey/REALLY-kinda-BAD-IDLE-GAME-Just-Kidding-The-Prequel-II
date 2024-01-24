import javax.swing.*;

class Dungeon extends JPanel {
    /**
     * This function hosts the dungeon screen with buttons to go to town or use a potion
     * @param player The player character object
     */
    public Dungeon() {

        // TODO add the actual enemy generation and combat here

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton run = new JButton("Run");

        add(Box.createVerticalGlue());
        add(run);
        add(Box.createVerticalGlue());

        run.setAlignmentX(CENTER_ALIGNMENT);

        run.addActionListener(e -> {
            try {
                Driver.changePanel("world");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}