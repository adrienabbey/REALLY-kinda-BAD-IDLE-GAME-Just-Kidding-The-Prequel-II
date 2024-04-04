import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.*;

class Combat extends JPanel {
    // static Inventory inventory = Inventory.getInstance();

    public enum MagicType {
        ATTACK,
        HEAL
    }

    static PlayerCharacter player;
    static Monster enemy;
    static Dice dice = new Dice(20);
    static MagicType magicType = MagicType.ATTACK;
    private static Thread combatThread;
    static boolean combatActive = false;
    static JTextArea logs = new JTextArea("Combat Log Area\n", 25, 58);

    public Combat(PlayerCharacter player, Monster enemy) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color customColorBlue = new Color(46, 86, 161);
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        logs.setBackground(customColorBrown);
        logs.setForeground(customColorBeige);
        logs.setFont(new Font("Serif", Font.BOLD, 22));
        Combat.player = player;
        Combat.enemy = enemy;
        JScrollPane scroll = new JScrollPane(logs);
        add(Box.createVerticalGlue());
        add(scroll);
        add(Box.createVerticalGlue());
    }

    public static void addLog(String log) {
        logs.append(log);
        logs.setCaretPosition(logs.getDocument().getLength());
    }

    public static void toggleMagicType() {
        if (magicType == MagicType.ATTACK) {
            magicType = MagicType.HEAL;
        } else {
            magicType = MagicType.ATTACK;
        }
    }

    public static void startCombat() {
        combatActive = true;
        combatThread = new Thread(() -> {
            try {
                combatLoop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        combatThread.start();
    }

    public static void endCombat() {
        combatActive = false;
    }

    public static void combatLoop() throws InterruptedException {
        while (combatActive && player.getHealth() > 0) {
            if (enemy.getHealth() > 0) {
                // Player's turn
                int playerRoll = dice.roll();
                int enemyRoll = dice.roll();
                if (playerRoll > enemyRoll) {
                    player.attack(enemy);
                    enemy.attack(player);
                } else {
                    enemy.attack(player);
                    player.attack(enemy);
                }
                if (player.getMagic() > 0) {
                    if (magicType == MagicType.ATTACK) {
                        player.magicAttack(enemy);
                    } else if (magicType == MagicType.HEAL) {
                        player.magicHeal();
                    }
                } else {
                    addLog(player.getName() + " is out of magic!\n");
                }
                Thread.sleep(1500);
            } else {
                addLog("\n" + enemy.getName() + " has been defeated " + player.getName() + " has gained "
                        + enemy.getGoldReward() + " gold!\n\n");
                player.addGold(enemy.getGoldReward());// Update gold resource in inventory

                // Check for equipment upgrades:
                player.doEquipmentUpgrade(enemy.getMonsterLevel(), enemy.isBoss());

                enemy = Dungeon.getMonster();
            }
            Driver.charScreen.update();
        }
        if (player.getHealth() <= 0) {
            logs.setForeground(Color.RED);
            logs.setBackground(Color.BLACK);
            addLog("\n" + player.getName() + " has been defeated!\n");
            player.died();
        }
    }
}