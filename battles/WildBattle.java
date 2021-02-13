package battles;

//野生精灵对战

import pokemon.Pokemon;

public class WildBattle {
    public Player player;
    public Pokemon wild;

    public WildBattle(Player p, Pokemon w) {
        player = p;
        wild = w;
    }

    public boolean run() {
        while (player.isAlive() && wild.isAlive()) {
            PlayerDecision pd = player.takeTurn(true);
            if (pd == PlayerDecision.changePokemon) {
                player.onStagePokemon = player.toExchangePokemon;
                player.toExchangePokemon = null;
            }
            if (pd == PlayerDecision.useItem) {
                player.toUseItem.use();
                player.toUseItem = null;
            }
            if (!player.isAlive() || !wild.isAlive() || wild.isCaptured) {
                wild.isCaptured = false;
                break;
            }

            if (player.onStagePokemon.stat.speed >= wild.stat.speed) {
                if (pd == PlayerDecision.useMove) {
                    player.onStagePokemon.useMove(player.toUseMove, wild);
                    player.toUseMove = null;
                }
                if (!player.isAlive() || !wild.isAlive())
                    break;
                wild.useMove(wild.randomMove(), player.onStagePokemon);
                if (!player.isAlive() || !wild.isAlive())
                    break;
                player.onStagePokemon.roundEnd();
                if (!player.isAlive() || !wild.isAlive())
                    break;
                wild.roundEnd();
            }
            else {
                wild.useMove(wild.randomMove(), player.onStagePokemon);
                if (!player.isAlive() || !wild.isAlive())
                    break;
                if (pd == PlayerDecision.useMove) {
                    player.onStagePokemon.useMove(player.toUseMove, wild);
                    player.toUseMove = null;
                }
                wild.roundEnd();
                if (!player.isAlive() || !wild.isAlive())
                    break;
                player.onStagePokemon.roundEnd();
            }


        }

        if (player.isAlive()) {
            player.resetBattleStatus();
            wild.substituteOff();
            return true;
        } else {
            player.resetBattleStatus();
            wild.substituteOff();
            return false;
        }
    }
}
