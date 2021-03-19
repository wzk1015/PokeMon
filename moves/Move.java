package moves;

import moves.sideeffects.SideEffect;
import pokemon.Pokemon;
import types.Type;
import utils.Utils;

//技能

public class Move {
    public Pokemon master;
    public String name;
    public int power;
    public Type type;
    public MoveType moveType;
    public int maxPP;
    public double hitRate;
    public int curPP;
    public int minAttackTimes = 1;
    public int maxAttackTimes = 1;
    public SideEffect sideEffect;

    public Move(String name, int power, Type type, MoveType moveType, int pp, double hitRate) {
        this(name, power, type, moveType, pp, hitRate, null);
    }

    public Move(String name, int power, Type type, MoveType moveType, int pp, double hitRate, SideEffect sideEffect) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.moveType = moveType;
        this.maxPP = pp;
        this.curPP = pp;
        this.hitRate = hitRate;
        this.sideEffect = sideEffect;
    }

    @Override
    public String toString() {
        return name;
    }

    public String fullInfo() {
        return name + " " + power + " " + type + " " + moveType + " " + hitRate + " " + curPP + "/" + maxPP;
    }

    public void noneAttackMoveUse(Pokemon user, Pokemon enemy) {
        Utils.raise("none attack move not implemented");
    }

    public void sideEffect(Pokemon user, Pokemon enemy) {
        if (sideEffect == null) {
            return;
        }
        sideEffect.use(user, enemy);
    }

    public void missEffect(Pokemon user) {
    }

    public boolean canUse() {
        return true;
    }
}
