package moves;

import java.util.List;

//特定精灵的技能表（可学习的技能及相应等级）

public class MoveTable {
    private final List<Integer> levels;
    private final List<Move> moves;

    public MoveTable(List<Integer> levels, List<Move> moves) {
        this.levels = levels;
        this.moves = moves;
    }

    public boolean canLearn(int curLevel) {
        return curLevel == levels.get(0);
    }

    public Move nextEvolution(int curLevel) {
        assert canLearn(curLevel);
        levels.remove(0);
        Move ret = moves.get(0);
        moves.remove(0);
        return ret;
    }
}
