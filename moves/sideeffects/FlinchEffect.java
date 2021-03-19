package moves.sideeffects;

import battles.Status;
import pokemon.Pokemon;
import utils.Utils;

public class FlinchEffect extends SideEffect {
    public double prob;

    public FlinchEffect(double prob) {
        this.prob = prob;
    }

    @Override
    public void use(Pokemon user, Pokemon enemy) {
        if (Utils.random() < prob) {
            enemy.isFlinched = true;
        }
    }
}
