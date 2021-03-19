package pokemon;

import battles.Player;
import battles.Status;
import moves.Move;
import moves.MoveType;
import types.Type;
import types.TypeTable;
import utils.IO;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pokemon.StatType.*;

//精灵个体

public class Pokemon extends PokemonSpecies {
    public Gender gender;
    public Character character;
    public int friendShip = 0;
    public int level;
    public int exp;
    public Stat individualStat = new Stat();
    public Stat effortStat = new Stat(0);

    private final Stat stat;
    public Player owner = null;
    public String name = speciesName;

    public int curHP;
    public Status status = Status.none;
    private Stat statModifier = new Stat(0);
    private Stat battleStat = new Stat(1);
    private int hitRateLevel = 0;
    private int evadeRateLevel = 0;
    public boolean isInfatuated = false;
    public boolean isConfused = false;
    public boolean isFlinched = false;
    public boolean isCaptured = false;
    public int leftSleepRound = 0;

    public Pokemon(String name, int id, Type type, ArrayList<Move> moves) {
        this.speciesName = name;
        this.name = name;
        this.pokemonId = id;
        this.type1 = type;
        this.moves = moves;

        this.gender = Gender.male;
        this.character = Character.hardy;
        this.level = 100;
        this.exp = 0;

        this.stat = new Stat(100);
        this.curHP = (int) stat.maxHP;
    }

    public boolean isAlive() {
        return curHP > 0;
    }

    public void loseHP(int hp, Pokemon attacker) {
        Utils.assertion(hp > 0, "lose hp <= 0");
        IO.println(name, " lose ", hp, "HP");
        curHP = Math.max(curHP - hp, 0);
        if (curHP == 0) {
            die();
        }
    }

    public void die() {
        IO.println(name, " is dead");
        if (owner.isAlive()) {
            owner.decidePokemon();
            while (owner.toExchangePokemon == null) {
                owner.decidePokemon();
            }
            owner.onStagePokemon = owner.toExchangePokemon;
            owner.toExchangePokemon = null;
            substituteOff();
        }
    }

    public void reset() {
        for (Move move : moves) {
            move.curPP = move.maxPP;
        }
        status = Status.none;
        curHP = (int) stat.maxHP;
        hitRateLevel = 0;
        evadeRateLevel = 0;
        substituteOff();
    }

    public void substituteOff() {
        isInfatuated = false;
        isConfused = false;
        isFlinched = false;
        battleStat = new Stat(1);
        statModifier = new Stat(0);
    }

    private void useAttackMove(Move move, Pokemon enemy) {
        Utils.assertion(move.moveType == MoveType.physical || move.moveType == MoveType.special,
                "invalid movetype in useAttackMove");

        double ratio = TypeTable.ratio(move.type, enemy.type1);
        if (enemy.type2 != null) {
            // 属性相克
            ratio *= TypeTable.ratio(move.type, enemy.type2);
        }
        if (type1 == move.type || type2 == move.type) {
            // 属性一致加成
            ratio *= 1.5;
        }
        //TODO: 其他加成

        int times = Utils.randint(move.minAttackTimes, move.maxAttackTimes);

        for (int i = 0; i < times; i++) {
            double damage = (2 * level + 10) / 250.0 * move.power;
            if (move.moveType == MoveType.physical) {
                damage = damage * getBattleStat(attack) / enemy.getBattleStat(defense) + 2;
            } else {
                damage = damage * getBattleStat(spAttack) / enemy.getBattleStat(spDefense) + 2;
            }
            damage *= ratio * Utils.randdouble(0.85, 1.00);
            damage = Math.max(damage, 1);
            enemy.loseHP((int) damage, this);
            move.sideEffect(this, enemy);
        }
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
        } else if (status == Status.freezing) {
            if (Utils.random() < 0.8) {
                IO.println(name, "被冻住无法行动");
                return;
            } else {
                IO.println(name, "冰冻解除了");
                status = Status.none;
            }
        } else if (status == Status.sleeping) {
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
        move.curPP -= 1;
        Utils.assertion(move.curPP >= 0, move + "pp < 0");


        int levelDiff = this.hitRateLevel - enemy.evadeRateLevel;
        double hitTarget = levelDiff == -6 ? 0.33 :
                levelDiff == -5 ? 0.36 :
                        levelDiff == -4 ? 0.43 :
                                levelDiff == -3 ? 0.50 :
                                        levelDiff == -2 ? 0.60 :
                                                levelDiff == -1 ? 0.75 :
                                                        levelDiff == 0 ? 1.00 :
                                                                levelDiff == 1 ? 1.33 :
                                                                        levelDiff == 2 ? 1.66 :
                                                                                levelDiff == 3 ? 2.00 :
                                                                                        levelDiff == 4 ? 2.33 :
                                                                                                levelDiff == 5 ? 2.50 : 3.00;
        hitTarget *= move.hitRate;
        //TODO: 道具修正、特性天气修正

        if (Utils.random() < hitTarget) {
            IO.println("命中");
            if (move.power > 0) {
                useAttackMove(move, enemy);
            } else {
                move.noneAttackMoveUse(this, enemy);
            }
        } else {
            IO.println(move.name, "没有命中");
        }

    }

    public Move randomMove() {
        ArrayList<Move> ms = new ArrayList<>();
        for (Move m : moves) {
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

        switch (status) {
            case burning:
                IO.println(this, "被烧伤了");
                break;
            case freezing:
                IO.println(this, "被冰冻了");
                break;
            case paralyzing:
                IO.println(this, "被麻痹了");
                break;
            case poisoning:
                IO.println(this, "中毒了");
                break;
            case sleeping:
                IO.println(this, "睡着了");
                break;
            case none:
                IO.println(this, "异常状态解除了");
                break;
        }

        if (status == Status.burning) {
            battleStat.attack *= 2;
        } else if (status == Status.paralyzing) {
            battleStat.speed *= 2;
        } else if (st == Status.sleeping) {
            leftSleepRound = 0;
        }

        status = st;

        if (st == Status.burning) {
            battleStat.attack *= 0.5;
        } else if (st == Status.paralyzing) {
            battleStat.speed *= 0.5;
        } else if (st == Status.sleeping) {
            leftSleepRound = Utils.randint(2, 5);
        }
    }

    public void roundEnd() {
        if (status == Status.poisoning) {
            curHP -= stat.maxHP / 8;
        } else if (status == Status.burning) {
            curHP -= stat.maxHP / 16;
        }
        isFlinched = false;
    }

    public void levelUp() {
        if (level == 100) {
            return;
        }
        level++;
        Stat old = new Stat(stat);
        stat.maxHP = (speciesStat.maxHP * 2 + individualStat.maxHP + effortStat.maxHP / 4) * level / 100 + 10 + level;
        List<StatType> statTypes = Arrays.asList(attack, defense, spAttack, spDefense, speed);
        for (StatType statType : statTypes) {
            double value = (speciesStat.get(statType) * 2 + individualStat.get(statType) +
                    effortStat.get(statType) / 4) * level / 100 + 5;
            value *= CharacterTable.characterRefine(character, statType);
            stat.set(statType, value);
        }

        statTypes.add(maxHP);
        for (StatType statType : statTypes) {
            int delta = (int) (stat.get(statType) - old.get(statType));
            IO.println(statType, "change by", delta > 0 ? "+" : "", delta, "to", stat.get(statType));
        }
    }

    public void statModifierChange(StatType s, int level) {
        Utils.assertion(s != maxHP, "change modifier of maxHP");
        int curLevel = s == hitRate ? hitRateLevel : s == evadeRate ? evadeRateLevel : (int) statModifier.get(s);
        Utils.assertion(curLevel >= -6 && curLevel <= 6, "invalid modifier level" + curLevel);
        int targetLevel = curLevel + level;
        targetLevel = targetLevel > 6 ? 6 : Math.max(targetLevel, -6);
        IO.println(this, s, "改变了", level, "级");
        if (s == hitRate) {
            hitRateLevel = targetLevel;
        } else if (s == evadeRate) {
            evadeRateLevel = targetLevel;
        } else {
            statModifier.set(s, targetLevel);
        }
    }

    public double getBattleStat(StatType s) {
        if (s == StatType.maxHP) {
            return stat.maxHP;
        }
        if (s == hitRate) {
            return hitRateLevel;
        }
        if (s == evadeRate) {
            return evadeRateLevel;
        }
        switch ((int) statModifier.get(s)) {
            case -6:
                return stat.get(s) * battleStat.get(s) * 0.25;
            case -5:
                return stat.get(s) * battleStat.get(s) * 0.29;
            case -4:
                return stat.get(s) * battleStat.get(s) * 0.33;
            case -3:
                return stat.get(s) * battleStat.get(s) * 0.40;
            case -2:
                return stat.get(s) * battleStat.get(s) * 0.50;
            case -1:
                return stat.get(s) * battleStat.get(s) * 0.67;
            case 0:
                return stat.get(s) * battleStat.get(s) * 1.00;
            case 1:
                return stat.get(s) * battleStat.get(s) * 1.50;
            case 2:
                return stat.get(s) * battleStat.get(s) * 2.00;
            case 3:
                return stat.get(s) * battleStat.get(s) * 2.50;
            case 4:
                return stat.get(s) * battleStat.get(s) * 3.00;
            case 5:
                return stat.get(s) * battleStat.get(s) * 3.50;
            case 6:
                return stat.get(s) * battleStat.get(s) * 4.00;
            default:
                Utils.raise("invalid modifier level" + (int) statModifier.get(s));
                return -1;
        }
    }

    public String fullInfo() {
        String ret = "---------------\n";
        ret += name + "Lv." + level + " HP: " + curHP + "/" + (int) stat.maxHP + "\n";
        if (status != Status.none)
            ret += status + "\n";
        return ret;
    }
}








