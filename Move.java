public class Move {
    public String name;
    public int power;
    public Type type;
    public MoveType moveType;
    public SideEffect sideEffect;
    public int pp;
    public double hitRate;

    Move(String name, int power, Type type, MoveType moveType, int pp, double hitRate) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.moveType = moveType;
        this.pp = pp;
        this.hitRate = hitRate;
    }

    @Override
    public String toString() {
        return name;
    }
}
