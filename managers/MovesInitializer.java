package managers;

import moves.Move;
import moves.MoveType;
import types.Type;
import static types.Type.*;
import static moves.MoveType.*;


import java.util.HashMap;

public class MovesInitializer {
    public static HashMap<String, Move> moves;

    public static void add(String name, Type type, MoveType moveType, int power, int hitRate, int pp) {
        moves.put(name, new Move(name, power, type, moveType, pp, (double) hitRate / 100));
    }

    public static void initialize() {
        add("拍击", normal, physical, 40, 100, 35);
        add("空手劈", fight, physical, 50, 100, 25);
    }
}
