package moves;

import pokemon.Pokemon;
import pokemon.StatType;
import types.Type;
import utils.IO;
import utils.Utils;

public class OneHKOMove extends Move {
    public OneHKOMove(String name, Type type, int pp) {
        super(name, 0, type, MoveType.variable, pp, 1);
    }

    @Override
    public void noneAttackMoveUse(Pokemon user, Pokemon enemy) {
        if (user.getBattleStat(StatType.speed) < enemy.getBattleStat(StatType.speed)) {
            IO.println(this, "因速度太慢无法使用");
            return;
        }
        if (Utils.random() < user.level - enemy.level + 30) {
            IO.println(this, "命中");
            enemy.loseHP(enemy.curHP, user);
        }
        IO.println(this, "没有命中");
    }
}
