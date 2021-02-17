import battles.Player;
import battles.SingleBattle;
import managers.MovesInitializer;
import managers.PokemonInitializer;
import utils.IO;

public class Main {
    public static void main(String[] args) {
        MovesInitializer.initialize();
        PokemonInitializer.initialize();
        Player p1 = new Player("霸霸","鲤鱼王");
        Player p2 = new Player("孙子", "皮卡丘");
        SingleBattle battle = new SingleBattle(p1, p2);
        Player winner = battle.run();
        IO.println("Winner is ", winner, "!");
    }
}
