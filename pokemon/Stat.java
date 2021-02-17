package pokemon;

//能力结构体

import utils.Utils;

public class Stat {
    public double maxHP;
    public double attack;
    public double spAttack;
    public double defense;
    public double spDefense;
    public double speed;

    public Stat(double maxHP, double attack, double spAttack, double defense, double spDefense, double speed) {
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

    public Stat(Stat other) {
        this(other.maxHP, other.attack, other.spAttack, other.defense, other.spDefense, other.speed);
    }

    public double get(StatType type) {
        switch (type) {
            case maxHP: return maxHP;
            case attack: return attack;
            case spAttack: return spAttack;
            case defense: return defense;
            case spDefense: return spDefense;
            case speed: return speed;
            default:
                Utils.raise("invalid stattype in get:" + type);
        }
        return -1;
    }

    public void set(StatType type, double value) {
        switch (type) {
            case maxHP: maxHP = (int) value;
            case attack: attack = (int) value;
            case spAttack: spAttack = (int) value;
            case defense: defense = (int) value;
            case spDefense: spDefense = (int) value;
            case speed: speed = (int) value;
            default:
                Utils.raise("invalid stattype in set:" + type);
        }
    }
}
