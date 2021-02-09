package types;

import utils.Utils;

import java.util.*;

import static types.Type.*;


public class TypeTable {
    public static HashMap<Type, List<Type>> effective = new HashMap<Type, List<Type>>() {
        {
            put(normal, Collections.emptyList());
            put(fight, Arrays.asList(normal, rock, steel, ice, dark));
            put(flying, Arrays.asList(fight, bug, grass));
            put(poison, Arrays.asList(grass, fairy));
            put(ground, Arrays.asList(poison, rock, steel, fire, electr));
            put(rock, Arrays.asList(flying, bug, fire, ice));
            put(bug, Arrays.asList(grass, psychc, dark));
            put(ghost, Arrays.asList(ghost, psychc));
            put(steel, Arrays.asList(rock, ice, fairy));
            put(fire, Arrays.asList(bug, steel, grass, ice));
            put(water, Arrays.asList(ground, rock, fire));
            put(grass, Arrays.asList(ground, rock, water));
            put(electr, Arrays.asList(flying, water));
            put(psychc, Arrays.asList(fight, poison));
            put(ice, Arrays.asList(fight, ground, grass, dragon));
            put(dragon, Collections.singletonList(dragon));
            put(dark, Arrays.asList(ghost, psychc));
            put(fairy, Arrays.asList(fight, dragon, dark));
        }
    };

    public static HashMap<Type, List<Type>> resistance = new HashMap<Type, List<Type>>() {
        {
            put(normal, Arrays.asList(rock, steel));
            put(fight, Arrays.asList(flying, poison, bug, psychc, fairy));
            put(flying, Arrays.asList(rock, steel, electr));
            put(poison, Arrays.asList(poison, ground, rock, ghost));
            put(ground, Arrays.asList(bug, grass));
            put(rock, Arrays.asList(fight, ground, steel));
            put(bug, Arrays.asList(fight, flying, poison, ghost, steel, fire, fairy));
            put(ghost, Collections.singletonList(dark));
            put(steel, Arrays.asList(steel, fire, water, electr));
            put(fire, Arrays.asList(fire, water, dragon));
            put(water, Arrays.asList(water, grass, dragon));
            put(grass, Arrays.asList(flying, poison, bug, steel, fire, grass, dragon));
            put(electr, Arrays.asList(grass, electr, dragon));
            put(psychc, Arrays.asList(steel, psychc));
            put(ice, Arrays.asList(steel, fire, water, ice));
            put(dragon, Collections.singletonList(steel));
            put(dark, Arrays.asList(fight, dark, fairy));
            put(fairy, Arrays.asList(poison, steel, fire));
        }
    };

    public static HashMap<Type, List<Type>> immune = new HashMap<Type, List<Type>>() {
        {
            put(normal, Collections.singletonList(ghost));
            put(fight, Collections.singletonList(ghost));
            put(flying, Collections.emptyList());
            put(poison, Collections.singletonList(steel));
            put(ground, Collections.singletonList(flying));
            put(rock, Collections.emptyList());
            put(bug, Collections.emptyList());
            put(ghost, Collections.singletonList(normal));
            put(steel, Collections.emptyList());
            put(fire, Collections.emptyList());
            put(water, Collections.emptyList());
            put(grass, Collections.emptyList());
            put(electr, Collections.singletonList(ground));
            put(psychc, Collections.singletonList(dark));
            put(ice, Collections.emptyList());
            put(dragon, Collections.singletonList(fairy));
            put(dark, Collections.emptyList());
            put(fairy, Collections.emptyList());
        }
    };

    public static double ratio(Type attacker, Type defender) {
        if (Utils.contains(effective.get(attacker), defender)) {
            return 2;
        }

        if (Utils.contains(resistance.get(attacker), defender)) {
            return 0.5;
        }

        if (Utils.contains(immune.get(attacker), defender)) {
            return 0;
        }
        return 1;
    }
}
