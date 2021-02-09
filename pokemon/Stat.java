package pokemon;

public class Stat {
    public int HP;
    public int attack;
    public int spAttack;
    public int defense;
    public int spDefense;
    public int speed;

    public int getByEnum(StatType type) {
        switch (type) {
            case HP: return HP;
            case attack: return attack;
            case spAttack: return spAttack;
            case defense: return defense;
            case spDefense: return spDefense;
            case speed: return speed;
            default:
        }
        return -1;
    }

    public int setByEnum(StatType type, int value) {
        switch (type) {
            case HP: HP = value;
            case attack: attack = value;
            case spAttack: spAttack = value;
            case defense: defense = value;
            case spDefense: spDefense = value;
            case speed: speed = value;
            default:
        }
        return -1;
    }
}
