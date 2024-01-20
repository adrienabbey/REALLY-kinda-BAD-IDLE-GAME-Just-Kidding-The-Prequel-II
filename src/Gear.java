/*
 * Player Class for REALLY (kinda) BAD IDLE GAME (Just Kidding) The Prequel II
 * Brandon Walker, et al., Jan. 2024
 */

class Gear{
    /* Variables */
    private int weapon;
    private int armor;
    private int accessory;

    /* Default Constructor */
    public Gear(){
        weapon = 0;
        armor = 0;
        accessory = 0;
    }

    /* Parameterized Constructor that we probably wont need, but have just in case */
    public Gear(int weapon, int armor, int accessory){
        this.weapon = weapon;
        this.armor = armor;
        this.accessory = accessory;
    }

    /* Setters */
    public void setWeapon(int weapon){ this.weapon = weapon;  }
    public void setArmor(int armor){ this.armor = armor;  }
    public void setAccessory(int accessory){ this.accessory = accessory;  }

    /* Getters */
    public int getWeapon(){ return weapon;  }
    public int getArmor(){ return armor;  }
    public int getAccessory(){ return accessory;  }
}