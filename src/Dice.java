class Dice{
    private int sides;
    private int dice;

    public Dice(){
        this.sides = 20;
        dice = (int) (Math.random() * sides) + 1;
    }

    public Dice(int sides){
        this.sides = sides;
        dice = (int) (Math.random() * sides) + 1;
    }

    public int roll(){
        return (int) (Math.random() * sides) + 1;
    }

    public int getLast(){
        return dice;
    }
}
