package pokemon;

import utils.Utils;

import java.util.ArrayList;
import java.util.List;

//特定精灵的进化链（可进化的目标及相应等级）

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
        Utils.assertion(canEvolve(curLevel), "cannot evolve");
        levels.remove(0);
        PokemonSpecies ret = species.get(0);
        species.remove(0);
        return ret;
    }
}
