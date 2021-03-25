package managers;

import battles.Status;
import moves.*;
import moves.sideeffects.FlinchEffect;
import moves.sideeffects.StatusEffect;
import pokemon.StatType;
import types.Type;
import utils.Utils;

import static types.Type.*;
import static moves.MoveType.*;
import static battles.Status.*;
import static pokemon.StatType.*;

import java.util.ArrayList;
import java.util.HashMap;

//初始化游戏中所有招式

public class MovesInitializer {
    public static HashMap<String, Move> moves = new HashMap<>();

    public static void add(String name, Type type, MoveType moveType, int power, int hitRate, int pp) {
        moves.put(name, new Move(name, power, type, moveType, pp, hitRate / 100.0));
    }

    public static void add(String name, Type type, MoveType moveType, int power, int hitRate, int pp, int minTimes, int maxTimes) {
        Move move = new Move(name, power, type, moveType, pp, hitRate / 100.0);
        move.minAttackTimes = minTimes;
        move.maxAttackTimes = maxTimes;
        moves.put(name, move);
    }

    public static void add(String name, Type type, MoveType moveType, int power, int hitRate, int pp, double prob, Status status) {
        moves.put(name, new Move(name, power, type, moveType, pp, hitRate / 100.0, new StatusEffect(prob, status)));
    }

    public static void add(String name, Type type, MoveType moveType, int power, int hitRate, int pp, int minTimes, int maxTimes, double prob, Status status) {
        Move move = new Move(name, power, type, moveType, pp, hitRate / 100.0, new StatusEffect(prob, status));
        move.minAttackTimes = minTimes;
        move.maxAttackTimes = maxTimes;
        moves.put(name, move);
    }

    public static void addSCM(String name, Type type, int pp, int raiseLevel, StatType statType, boolean useToSelf) {
        moves.put(name, new StatChangeMove(name, type, pp, raiseLevel, statType, useToSelf));
    }

    public static void addFlinch(String name, Type type, MoveType moveType, int power, int hitRate, int pp, double prob) {
        moves.put(name, new Move(name, power, type, moveType, pp, hitRate / 100.0, new FlinchEffect(prob)));
    }

    public static void initialize() {
        add("拍击", normal, physical, 40, 100, 35);
        add("空手劈", fight, physical, 50, 100, 25);
        add("连环巴掌", fight, physical, 15, 85, 10, 2, 5);
        add("连续拳", fight, physical, 18, 85, 15, 2, 5);
        add("百万吨重拳", fight, physical, 80, 85, 20);

        add("聚宝功", fight, physical, 40, 100, 20); //TODO
        add("火焰拳", fire, physical, 75, 100, 15, 0.1, burning);
        add("冰冻拳", ice, physical, 75, 100, 15, 0.1, freezing);
        add("雷电拳", electr, physical, 75, 100, 15, 0.1, paralyzing);
        add("抓", normal, physical, 40, 100, 35);

        add("夹住", normal, physical, 55, 100, 30);
        moves.put("断头钳", new OneHKOMove("断头钳", normal, 5));
        //TODO:旋风力
        addSCM("剑舞", normal, 20, 2, attack, true);
        add("居合斩", normal, physical, 50, 95, 30);

        add("起风", flying, special, 40, 100, 35);
        add("翅膀攻击", flying, physical, 60, 100, 35);
        //TODO:吹飞
        //TODO:飞翔
        //TODO:绑紧

        add("摔打", normal, physical, 80, 75, 20);
        add("藤鞭", grass, physical, 45, 100, 25);
        addFlinch("踩踏", normal, physical, 65, 100, 20, 0.3);
        add("二连踢", fight, physical, 30, 100, 30, 2, 2);
        add("百万吨重踢", fight, physical, 120, 75, 5);

        moves.put("飞踢", new MissRecoilMove("飞踢", 95, fight, physical, 10, 95, 0.5));
        addFlinch("回旋踢", fight, physical, 60, 85, 15, 0.3);
        addSCM("泼沙", ground, 15, -1, hitRate, false);
        addFlinch("头锤", fight, physical, 70, 100, 15, 0.3);
        add("角撞", normal, physical, 65, 100, 25);

        add("乱击", normal, physical, 15, 85, 20);
        moves.put("角钻", new OneHKOMove("角钻", normal, 5));
        add("撞击", normal, physical, 40, 100, 35);
        add("泰山压顶", normal, physical, 85, 100, 15, 0.3, paralyzing);
        //TODO:紧束

        //TODO:猛撞
        //TODO:大闹一番
        //TODO:舍身冲撞
        addSCM("摇尾巴", normal, 30, -1, defense, false);
        add("毒针", poison, physical, 15, 100, 35, 0.3, poisoning);

        add("双针", bug, physical, 25, 100, 20, 2, 2, 0.2, poisoning);
        add("飞弹针", bug, physical, 25, 95, 20, 2, 5);
        addSCM("瞪眼", normal, 30, -1, defense, false);
        addFlinch("咬住", dark, physical, 60, 100, 25, 0.3);
        addSCM("叫声", normal, 40, -1, attack, false);

    }

    public static ArrayList<Move> getMoves(String... moveNames) {
        ArrayList<Move> ret = new ArrayList<>();
        for (String name: moveNames) {
            Utils.assertion(moves.containsKey(name), "move name " + name + " not in moves");
            ret.add(moves.get(name));
        }
        return ret;
    }
}
