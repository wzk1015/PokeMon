package pokemon;

import battles.Player;
import battles.Status;
import moves.Move;
import types.Type;
import types.TypeTable;
import utils.IO;
import utils.Utils;

import java.util.ArrayList;

public class Pokemon extends PokemonSpecies {
    public Gender gender;
    public Character character;
    public StatType addStatType;
    public StatType subStatType;
    public int friendShip;
    public int level;
    public int exp;
    public Stat individualStat;
    public Stat effortStat;
    public Stat stat;
    public Player owner;
    public String name = speciesName;

    public int curHP;
    public Status status = null;
    public boolean isInfatuated = false;
    public boolean isConfused = false;
    public boolean isFlinched = false;
    public boolean isCaptured = false;

    public boolean isAlive() {
        return curHP > 0;
    }

    public void loseHP(int hp, Pokemon attacker) {
        Utils.assertion(hp > 0, "lose hp <= 0");
        curHP = Math.max(curHP - hp, 0);
        if (curHP == 0) {
            die();
        }
    }

    public void die() {
        IO.println(name, " is dead");
        owner.decidePokemon();
        while (owner.toExchangePokemon == null) {
            owner.decidePokemon();
        }
        owner.onStagePokemon = owner.toExchangePokemon;
        owner.toExchangePokemon = null;
    }

    public void reset() {
        for (Move move: moves) {
            move.curPP = move.maxPP;
        }
        status = null;
        curHP = stat.HP;
    }

    public void substituteOff() {
        isInfatuated = false;
        isConfused = false;
        isFlinched = false;
    }

    public void useMove(Move move, Pokemon enemy) {
        if (isFlinched) {
            IO.println(name, "因害怕而不敢动弹");
            return;
        }
        if (isConfused) {
            IO.println(name, "混乱了");
            if (Utils.random() < 0.33) {
                IO.println(name, "在混乱中攻击了自己");
                //TODO
                return;
            }
        }
        if (isInfatuated) {
            IO.println(name, "着迷了");
            if (Utils.random() < 0.5) {
                IO.println(name, "因着迷而无法行动");
                return;
            }
        }
        assert Utils.contains(moves, move);
        if (move.power > 0) {
            if (Utils.random() < move.hitRate) {
                IO.println(move.name, "命中");
                double ratio = TypeTable.ratio(move.type, enemy.type1);
                if (enemy.type2 != null)
                    ratio *= TypeTable.ratio(move.type, enemy.type2);
                if (type1 == move.type || type2 == move.type) {
                    ratio *= 1.5;
                }
                //TODO: 计算威力
                enemy.loseHP(1, this);
                move.sideEffect();
            }
            move.curPP -= 1;
            Utils.assertion(move.curPP >= 0, "pp < 0");
        }
        else {
            move.noneAttackMoveUse();
        }
    }

    public Move randomMove() {
        ArrayList<Move> ms = new ArrayList<>();
        for (Move m: moves) {
            if (m.curPP > 0) {
                ms.add(m);
            }
        }
        return Utils.choice(ms);
    }
}
