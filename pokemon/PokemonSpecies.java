package pokemon;

import abilities.Ability;
import moves.MoveTable;
import types.Type;

public class PokemonSpecies {
    public int pokemonId;
    public String speciesName;
    public Type type;
    public Ability ability;
    public MoveTable moveTable;
    public EvolutionChain evolutionChain;

    public Stat speciesStat;

    @Override
    public String toString() {
        return speciesName;
    }
}
