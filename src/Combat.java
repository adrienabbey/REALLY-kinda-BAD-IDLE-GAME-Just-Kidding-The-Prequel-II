import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Box;



//
//Class houses UI implmentation for scroll logs that shows details regarding combat. 
//Also houses functions on starting/stopping combat and managing the combat loop. 
//

class Combat extends JPanel {

//========================================================
// Fields
//========================================================
    //These are used for formating the gui elements   
    final private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final private int width = screenSize.width;
    final private int height = screenSize.height;
    final private int buttonFont = width / 72;
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


//========================================================
// Constructor
//========================================================
    public Combat(PlayerCharacter player, Monster enemy) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Color customColorBeige = new Color(253, 236, 166);
        Color customColorBrown = new Color(102, 72, 54);
        logs.setBackground(customColorBrown);
        logs.setForeground(customColorBeige);
        logs.setFont(new Font("Serif", Font.BOLD, buttonFont));
        Combat.player = player;
        Combat.enemy = enemy;
        JScrollPane scroll = new JScrollPane(logs);
        add(Box.createVerticalGlue());
        add(scroll);
        add(Box.createVerticalGlue());
    }


//========================================================
// Methods
//========================================================
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
                double enemyHealth = enemy.getHealth();
                enemyHealth = Math.round(enemyHealth * 10.0) / 10.0; 
                addLog(enemy.getName() + " has " + enemyHealth + " health remaining.\n\n");
                Thread.sleep(750);
            } else {

                addLog("\n" + enemy.getName() + " has been defeated!\n" + player.getName() + " has gained "
                        + enemy.getGoldReward() + " gold!\n");
                player.addGold(enemy.getGoldReward());// Update gold resource in inventory

                // Check for equipment upgrades:
                addLog("Player's " + player.doEquipmentUpgrade(enemy.getMonsterLevel(), enemy.isBoss()) + " upgraded!\n\n\n");

                enemy = Dungeon.getMonster();
                Thread.sleep(1500);
              
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

    public void update(){
        logs.setForeground(new Color(253, 236, 166));
        logs.setBackground(new Color(102, 72, 54));
    }
}