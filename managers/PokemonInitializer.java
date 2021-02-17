package managers;

//初始化游戏中所有精灵

import moves.Move;
import pokemon.Pokemon;
import types.Type;

import java.util.HashMap;

import static moves.MoveType.physical;
import static types.Type.*;

public class PokemonInitializer {
    public static HashMap<String, Pokemon> pokemons = new HashMap<>();
    public static int pokeId = 1;

    public static void add(String name, Type type, String... moves) {
        pokemons.put(name, new Pokemon(name, pokeId, type, MovesInitializer.getMoves(moves)));
        pokeId++;
    }

    public static void initialize() {
        add("鲤鱼王", water, "拍击");
        add("皮卡丘", electr, "空手劈");
    }
}
