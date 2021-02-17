package pokemon;

import abilities.Ability;
import moves.Move;
import moves.MoveTable;
import types.Type;

import java.util.ArrayList;
import java.util.Arrays;

//精灵种族

public abstract class PokemonSpecies {
    public int pokemonId;
    public String speciesName;
    public Type type1;
    public Type type2 = null;
    public Ability ability;
    public MoveTable moveTable;
    public EvolutionChain evolutionChain;
    public ArrayList<Move> moves;

    public Stat speciesStat;

    @Override
    public String toString() {
        return speciesName;
    }
}
