public class Pokemon {
    public String name;
    public Type type;
    public int level;

    public int maxHP;
    public int attack;
    public int defense;
    public int spAttack;
    public int spDefense;
    public int speed;

    @Override
    public String toString() {
        return name;
    }
}
