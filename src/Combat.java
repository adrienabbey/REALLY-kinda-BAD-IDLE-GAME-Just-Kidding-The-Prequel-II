import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.Box;

class Combat extends JPanel{

    public enum MagicType{
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

    public Combat(PlayerCharacter player, Monster enemy){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Combat.player = player;
        Combat.enemy = enemy;
        JScrollPane scroll = new JScrollPane(logs);
        add(Box.createVerticalGlue());
        add(scroll);
        add(Box.createVerticalGlue());
    }

    public static void addLog(String log){
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

    public static void startCombat(){
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

    public static void endCombat(){
        combatActive = false;
    }

    public static void combatLoop() throws InterruptedException{
        while(combatActive && player.getHealth() > 0){
            if(enemy.getHealth() > 0){
                // Player's turn
                int playerRoll = dice.roll();
                int enemyRoll = dice.roll();
                if(playerRoll > enemyRoll){
                    player.attack(enemy);
                    enemy.attack(player);
                } else {
                    enemy.attack(player);
                    player.attack(enemy);
                }
                if(player.getMagic() > 0){
                    if(magicType == MagicType.ATTACK){
                        player.magicAttack(enemy);
                    }
                    else if(magicType == MagicType.HEAL){
                        player.magicHeal();
                    }
                } else {
                    addLog(player.getName() + " is out of magic!\n");
                }
                Thread.sleep(1500);
            } else {
                addLog(enemy.getName() + " has been defeated!\n" + player.getName() + " has gained " + enemy.getGoldReward() + " gold!\n");
                player.addGold(enemy.getGoldReward());
                enemy = Dungeon.getMonster();
            }
            Driver.charScreen.update();
        }
        if(player.getHealth() <= 0){
            addLog(player.getName() + " has been defeated!\n");
            player.died();
        }
    }
}