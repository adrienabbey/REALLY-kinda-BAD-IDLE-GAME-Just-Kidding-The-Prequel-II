import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;

class Combat extends JPanel{

    public enum MagicType{
        ATTACK,
        HEAL
    }
    Dice dice = new Dice(20);
    MagicType magicType;
    static boolean combatActive = false;
    static JTextArea logs = new JTextArea("Combat Log Area", 16, 58);

    public Combat(PlayerCharacter player, Monster enemy) throws InterruptedException{
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(logs);
        add(Box.createVerticalGlue());

        while(combatActive && player.getHealth() > 0){
            while(enemy.getHealth() > 0){
                // Player's turn
                int playerRoll = dice.roll();
                int enemyRoll = dice.roll();
                if(playerRoll > enemyRoll){
                    player.attack(enemy);
                    enemy.attack(player);
                }
                else{
                    enemy.attack(player);
                    player.attack(enemy);
                }
                if(player.getMagic() > 0){
                    if(magicType == MagicType.ATTACK){
                        player.magicAttack(enemy);
                        addLog(player.getName() + " is out of magic!");
                    }
                    else if(magicType == MagicType.HEAL){
                        player.magicHeal();
                    }
                }
                Thread.sleep(1500);
            }
            addLog(enemy.getName() + " has been defeated!" + player.getName() + " has gained " + enemy.getGoldReward() + " gold!");
            player.addGold(enemy.getGoldReward());
            enemy = Dungeon.getMonster();
        }
    }
    public static void addLog(String log){
        logs.append(log);
        logs.repaint();
    }

    public static void startCombat(){
        combatActive = true;
    }

    public static void endCombat(){
        combatActive = false;
    }
}