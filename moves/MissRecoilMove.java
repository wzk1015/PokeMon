package moves;

import pokemon.Pokemon;
import pokemon.StatType;
import types.Type;

public class MissRecoilMove extends Move {
    public double ratio;

    public MissRecoilMove(String name, int power, Type type, MoveType moveType, int pp, double hitRate, double ratio) {
        super(name, power, type, moveType, pp, hitRate);
    }

    @Override
    public void missEffect(Pokemon user) {
        user.loseHP((int) (ratio * user.getBattleStat(StatType.maxHP)), null);
    }
}
