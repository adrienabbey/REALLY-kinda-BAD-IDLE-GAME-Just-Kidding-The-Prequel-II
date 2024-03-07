class Combat{

    public enum MagicType{
        ATTACK,
        HEAL
    }
    Dice dice = new Dice(20);
    MagicType magicType;

    public Combat(PlayerCharacter player, Monster enemy){
        while(player.getHealth() > 0){
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
                    }
                    else if(magicType == MagicType.HEAL){
                        player.magicHeal();
                    }
                }
            }
            enemy = Dungeon.getMonster();
        }
    }
}