package pokemon;

//能力结构体

public class Stat {
    public double maxHP;
    public double attack;
    public double spAttack;
    public double defense;
    public double spDefense;
    public double speed;

    public Stat(int maxHP, int attack, int spAttack, int defense, int spDefense, int speed) {
        this.maxHP = maxHP;
        this.attack = attack;
        this.spAttack = spAttack;
        this.defense = defense;
        this.spDefense = spDefense;
        this.speed = speed;
    }

    public Stat(double constant) {
        this.maxHP = constant;
        this.attack = constant;
        this.spAttack = constant;
        this.defense = constant;
        this.spDefense = constant;
        this.speed = constant;
    }

    public double getByEnum(StatType type) {
        switch (type) {
            case maxHP: return maxHP;
            case attack: return attack;
            case spAttack: return spAttack;
            case defense: return defense;
            case spDefense: return spDefense;
            case speed: return speed;
            default:
        }
        return -1;
    }

    public double setByEnum(StatType type, double value) {
        switch (type) {
            case maxHP: maxHP = value;
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
