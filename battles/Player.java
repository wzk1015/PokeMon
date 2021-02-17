package battles;

import items.Item;
import managers.PokemonInitializer;
import moves.Move;
import pokemon.Pokemon;
import utils.IO;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static battles.PlayerDecision.*;

//玩家

public class Player {
    public String name;
    public ArrayList<Pokemon> pokemons;
    public ArrayList<Item> items;

    public Pokemon onStagePokemon;
    public Move toUseMove = null;
    public Item toUseItem = null;
    public Pokemon toExchangePokemon = null;
    private PlayerDecision pd;

    public Player(String name) {
        this.name = name;
        pokemons = new ArrayList<>();
        items = new ArrayList<>();
        onStagePokemon = null;
    }

    public Player(String name, String firstPokemon) {
        this(name);
        setFirstPokemon(firstPokemon);
    }

    public void setFirstPokemon(String p) {
        onStagePokemon = PokemonInitializer.pokemons.get(p);
        pokemons.add(onStagePokemon);
        onStagePokemon.owner = this;
    }

    public void resetBattleStatus() {
        for (Pokemon p : pokemons) {
            p.reset();
        }
    }

    public boolean isAlive() {
        for (Pokemon p : pokemons) {
            if (p.isAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    public PlayerDecision takeTurn(boolean isWild) {
        IO.println(onStagePokemon, "做什么");
        PlayerDecision pd;
        while (true) {
            if (isWild)
                pd = chooseNoNull(useMove, useItem, changePokemon, runAway);
            else
                pd = chooseNoNull(useMove, useItem, changePokemon);
            if (pd == useMove)
                if (decideMove())
                    break;
            if (pd == useItem)
                if (decideItem(isWild))
                    break;
            if (pd == changePokemon)
                if (decidePokemon())
                    break;
            if (pd == runAway)
                return pd;
        }
        return pd;
    }

    public boolean decideMove() {
        ArrayList<Move> moves = new ArrayList<>();
        for (Move move : onStagePokemon.moves) {
            IO.println(move.fullInfo());
            if (move.curPP > 0) {
                moves.add(move);
            }
        }
        Move move = chooseFromProvided(moves);
        if (move == null) {
            return false;
        }
        toUseMove = move;
        return true;
    }

    public boolean decideItem(boolean isWild) {
//        for (Item item: items) {
//            IO.println(item.toString());
//        }
        Item item = chooseFromProvided(items);
        if (item == null) {
            return false;
        }
        toUseItem = item;
        return true;
    }

    public boolean decidePokemon() {
        for (Pokemon p : pokemons) {
            IO.println(p.toString());
        }
        Pokemon p = chooseFromProvided(pokemons);
        if (p == null) {
            return false;
        }
        toExchangePokemon = p;
        return true;
    }

    public String input(String s) {
        String ans = "";
        while (ans.isEmpty()) {
            IO.println(">>>" + s + " ");
            ans = IO.sn.nextLine().trim();
        }
        return ans;
    }

    @SafeVarargs
    public final <E> E chooseFromProvided(E... choices) {
        ArrayList<E> options = new ArrayList<>(Arrays.asList(choices));
        return chooseFromProvided(options);
    }

    public <E> E chooseFromProvided(List<E> choices) {
        if (choices.isEmpty()) {
            IO.println(this + " has nothing to choose");
            return null;
        }

        int i = 1;
        for (E choice : choices) {
            IO.println("【" + i++ + "】" + choice.toString() + "  ");
        }
        IO.println();
        try {
            String in = input(toString() + " make a choice, or 'q' to quit");
            if (in.equals("q")) {
                return null;
            }
            if (in.equals("help")) {
                IO.println("[make a choice]: input number to make your choice");
                return chooseFromProvided(choices);
            }
            int option = Integer.parseInt(in) - 1;
            return choices.get(option);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            IO.println("wrong choice");
            return chooseFromProvided(choices);
        }
    }

    @SafeVarargs
    public final <E> E chooseNoNull(E... choices) {
        return chooseNoNull(Arrays.asList(choices));
    }

    public <E> E chooseNoNull(List<E> choices) {
        E ans = null;
        do {
            ans = chooseFromProvided(choices);
        } while (ans == null);
        return ans;
    }
}
