package pokemon;

import battles.Player;
import battles.Status;
import moves.Move;
import types.Type;
import types.TypeTable;
import utils.IO;
import utils.Utils;

import java.util.ArrayList;

//精灵个体

public class Pokemon extends PokemonSpecies {
    public Gender gender;
    public Character character;
    public int friendShip;
    public int level;
    public int exp;
    public Stat individualStat;
    public Stat effortStat = new Stat(0);;
    public Stat stat;
    public Player owner;
    public String name = speciesName;

    public int curHP;
    public Status status = Status.none;
    public Stat battleStat = new Stat(1);
    public boolean isInfatuated = false;
    public boolean isConfused = false;
    public boolean isFlinched = false;
    public boolean isCaptured = false;
    public int leftSleepRound = 0;

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
        substituteOff();
    }

    public void reset() {
        for (Move move: moves) {
            move.curPP = move.maxPP;
        }
        status = Status.none;
        curHP = (int) stat.maxHP;
        substituteOff();
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
        if (status == Status.paralyzing) {
            if (Utils.random() < 0.25) {
                IO.println(name, "因麻痹而无法行动");
                return;
            }
        }
        else if (status == Status.freezing) {
            if (Utils.random() < 0.8) {
                IO.println(name, "被冻住无法行动");
                return;
            } else {
                IO.println(name, "冰冻解除了");
                status = Status.none;
            }
        }
        else if (status == Status.sleeping) {
            leftSleepRound -= 1;
            if (leftSleepRound == 0) {
                IO.println(name, "睡醒了");
                status = Status.none;
            } else {
                IO.println(name, "睡得很沉");
                return;
            }
        }

        assert Utils.contains(moves, move);
        IO.println(name, "使用了", move.name);
        if (move.power > 0) {
            if (Utils.random() < move.hitRate) {
                IO.println("命中");
                double ratio = TypeTable.ratio(move.type, enemy.type1);
                if (enemy.type2 != null)
                    ratio *= TypeTable.ratio(move.type, enemy.type2);
                if (type1 == move.type || type2 == move.type) {
                    ratio *= 1.5;
                }
                //TODO: 计算威力
                enemy.loseHP(1, this);
                move.sideEffect();
            } else {
                IO.println(move.name, "没有命中对方");
            }

            move.curPP -= 1;
            Utils.assertion(move.curPP >= 0, move + "pp < 0");
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

    public void setStatus(Status st) {
        if (st == status) {
            return;
        }
        if (status == Status.burning) {
            battleStat.attack *= 2;
        }
        else if (status == Status.paralyzing) {
            battleStat.speed *= 2;
        }
        else if (st == Status.sleeping) {
            leftSleepRound = 0;
        }

        status = st;

        if (st == Status.burning) {
            battleStat.attack *= 0.5;
        }
        else if (st == Status.paralyzing) {
            battleStat.speed *= 0.5;
        }
        else if (st == Status.sleeping) {
            leftSleepRound = Utils.randint(2, 5);
        }
    }

    public double getStat(StatType st) {
        if (st == StatType.maxHP) {
            return stat.maxHP;
        }
        return stat.getByEnum(st) * battleStat.getByEnum(st);
    }

    public void roundEnd() {
        if (status == Status.poisoning) {
            curHP -= stat.maxHP / 8;
        }
        else if (status == Status.burning) {
            curHP -= stat.maxHP / 16;
        }
    }
}
