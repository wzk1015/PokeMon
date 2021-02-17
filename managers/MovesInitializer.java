package managers;

import moves.Move;
import moves.MoveType;
import types.Type;
import utils.Utils;

import static types.Type.*;
import static moves.MoveType.*;

import java.util.ArrayList;
import java.util.HashMap;

//初始化游戏中所有招式

public class MovesInitializer {
    public static HashMap<String, Move> moves = new HashMap<>();

    public static void add(String name, Type type, MoveType moveType, int power, int hitRate, int pp) {
        moves.put(name, new Move(name, power, type, moveType, pp, hitRate / 100.0));
    }

    public static void initialize() {
        add("拍击", normal, physical, 40, 100, 35);
        add("空手劈", fight, physical, 50, 100, 25);
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
