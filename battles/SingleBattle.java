package battles;

//一对一对战

import pokemon.StatType;
import utils.IO;

public class SingleBattle {
    public Player player1;
    public Player player2;

    public SingleBattle(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
    }

    public Player run() {
        while (player1.isAlive() && player2.isAlive()) {
            Player first = player1.onStagePokemon.getBattleStat(StatType.speed) >=
                    player2.onStagePokemon.getBattleStat(StatType.speed) ?
                    player1 : player2;
            Player second = first == player1 ? player2 : player1;

            PlayerDecision pd1 = first.takeTurn(false);
            PlayerDecision pd2 = second.takeTurn(false);
            if (pd1 == PlayerDecision.changePokemon) {
                first.onStagePokemon = first.toExchangePokemon;
                first.toExchangePokemon = null;
            }
            if (pd2 == PlayerDecision.changePokemon) {
                second.onStagePokemon = second.toExchangePokemon;
                second.toExchangePokemon = null;
            }
            if (pd1 == PlayerDecision.useItem) {
                first.toUseItem.use();
                first.toUseItem = null;
            }
            if (pd2 == PlayerDecision.useItem) {
                second.toUseItem.use();
                second.toUseItem = null;
            }
            if (!first.isAlive() || !second.isAlive())
                break;
            if (pd1 == PlayerDecision.useMove) {
                first.onStagePokemon.useMove(first.toUseMove, second.onStagePokemon);
                first.toUseMove = null;
            }
            if (!first.isAlive() || !second.isAlive())
                break;
            if (pd2 == PlayerDecision.useMove) {
                second.onStagePokemon.useMove(second.toUseMove, first.onStagePokemon);
                second.toUseMove = null;
            }
            if (!first.isAlive() || !second.isAlive())
                break;
            first.onStagePokemon.roundEnd();
            if (!first.isAlive() || !second.isAlive())
                break;
            second.onStagePokemon.roundEnd();
        }
        if (player1.isAlive()) {
            player1.resetBattleStatus();
            player2.resetBattleStatus();
            return player1;
        } else {
            player1.resetBattleStatus();
            player2.resetBattleStatus();
            return player2;
        }
    }


}
