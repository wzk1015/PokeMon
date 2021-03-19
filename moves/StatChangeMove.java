package moves;

import pokemon.Pokemon;
import pokemon.StatType;
import types.Type;

import java.util.ArrayList;

public class StatChangeMove extends Move {
    public int raiseLevel;
    public ArrayList<StatType> statTypes;
    public boolean useToSelf;

    public StatChangeMove(String name, Type type, int pp, int raiseLevel, StatType statType, boolean useToSelf) {
        super(name, 0, type, MoveType.variable, pp, 1);
        this.raiseLevel = raiseLevel;
        this.statTypes = new ArrayList<>();
        this.statTypes.add(statType);
        this.useToSelf = useToSelf;
    }

    @Override
    public void noneAttackMoveUse(Pokemon user, Pokemon enemy) {
        if (useToSelf) {
            for (StatType StatType : statTypes) {
                user.statModifierChange(StatType, raiseLevel);
            }
        }
        else {
            for (StatType StatType : statTypes) {
                enemy.statModifierChange(StatType, raiseLevel);
            }
        }

    }
}
