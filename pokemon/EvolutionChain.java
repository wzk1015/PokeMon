package pokemon;

import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class EvolutionChain {
    private final List<Integer> levels;
    private final List<PokemonSpecies> species;

    EvolutionChain(List<Integer> levels, List<PokemonSpecies> species) {
        this.levels = levels;
        this.species = species;
    }

    public boolean canEvolve(int curLevel) {
        return curLevel > levels.get(0);
    }

    public PokemonSpecies nextEvolution(int curLevel) {
        assert canEvolve(curLevel);
        levels.remove(0);
        PokemonSpecies ret = species.get(0);
        species.remove(0);
        return ret;
    }
}
