package pokemon;

import java.util.HashMap;

import static pokemon.Character.*;
import static pokemon.StatType.*;

//性格表，查询增减的能力

public class CharacterTable {
    public static HashMap<Character, StatType> add = new HashMap<Character, StatType>() {
        {
            put(hardy, null);
            put(docile, null);
            put(bashful, null);
            put(quirky, null);
            put(serious, null);

            put(lonely, attack);
            put(adamant, attack);
            put(naughty, attack);
            put(brave, attack);

            put(bold, defense);
            put(impish, defense);
            put(lax, defense);
            put(relaxed, defense);

            put(modest, spAttack);
            put(mild, spAttack);
            put(rash, spAttack);
            put(quiet, spAttack);

            put(calm, spDefense);
            put(gentle, spDefense);
            put(careful, spDefense);
            put(sassy, spDefense);

            put(timid, speed);
            put(hasty, speed);
            put(jolly, speed);
            put(naive, speed);
        }
    };

    public static HashMap<Character, StatType> sub = new HashMap<Character, StatType>() {
        {
            put(hardy, null);
            put(docile, null);
            put(bashful, null);
            put(quirky, null);
            put(serious, null);

            put(lonely, defense);
            put(adamant, spAttack);
            put(naughty, spDefense);
            put(brave, speed);

            put(bold, attack);
            put(impish, spAttack);
            put(lax, spDefense);
            put(relaxed, speed);

            put(modest, attack);
            put(mild, defense);
            put(rash, spDefense);
            put(quiet, speed);

            put(calm, attack);
            put(gentle, defense);
            put(careful, spAttack);
            put(sassy, speed);

            put(timid, attack);
            put(hasty, defense);
            put(jolly, spAttack);
            put(naive, spDefense);
        }
    };

    public static double characterRefine(Character c, StatType s) {
        if (s == add.get(c)) {
            return 1.1;
        }
        else if (s == sub.get(c)) {
            return 0.9;
        }
        return 1.0;
    }
}
