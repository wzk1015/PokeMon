package moves;

import pokemon.Pokemon;
import types.Type;
import types.TypeTable;
import utils.IO;
import utils.Utils;

public class Move {
    public Pokemon master;
    public String name;
    public int power;
    public Type type;
    public MoveType moveType;
    public int maxPP;
    public double hitRate;
    public int curPP;

    public Move(String name, int power, Type type, MoveType moveType, int pp, double hitRate) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.moveType = moveType;
        this.maxPP = pp;
        this.curPP = pp;
        this.hitRate = hitRate;
    }

    @Override
    public String toString() {
        return name;
    }

    public String fullInfo() {
        return name + " " + power + " " + type + " " + moveType + " " + hitRate + " " + curPP + "/" + maxPP;
    }

    public void noneAttackMoveUse() {
        Utils.raise("none attack move not implemented");
    }

    public void sideEffect() {

    }
}
