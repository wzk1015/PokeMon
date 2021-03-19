package moves.sideeffects;

import battles.Status;
import pokemon.Pokemon;
import utils.Utils;

//以

public class StatusEffect extends SideEffect {
    public double prob;
    public Status status;

    public StatusEffect(double prob, Status status) {
        this.prob = prob;
        this.status = status;
    }

    @Override
    public void use(Pokemon user, Pokemon enemy) {
        if (Utils.random() < prob) {
            enemy.setStatus(status);
        }
    }
}
