import java.util.*;

public class TypeTable {
    public static HashMap<Type, List<Type>> effective = new HashMap<Type, List<Type>>() {
        {
            put(Type.Normal, Collections.emptyList());
            put(Type.Fight, Arrays.asList(Type.Normal, Type.Rock, Type.Steel, Type.Ice, Type.Dark));
            put(Type.Flying, Arrays.asList(Type.Fight, Type.Bug, Type.Grass));
            put(Type.Poison, Arrays.asList(Type.Grass, Type.Fairy));
            put(Type.Ground, Arrays.asList(Type.Poison, Type.Rock, Type.Steel, Type.Fire, Type.Electr));
            put(Type.Rock, Arrays.asList(Type.Flying, Type.Bug, Type.Fire, Type.Ice));
            put(Type.Bug, Arrays.asList(Type.Grass, Type.Psychc, Type.Dark));
            put(Type.Ghost, Arrays.asList(Type.Ghost, Type.Psychc));
            put(Type.Steel, Arrays.asList(Type.Rock, Type.Ice, Type.Fairy));
            put(Type.Fire, Arrays.asList(Type.Bug, Type.Steel, Type.Grass, Type.Ice));
            put(Type.Water, Arrays.asList(Type.Ground, Type.Rock, Type.Fire));
            put(Type.Grass, Arrays.asList(Type.Ground, Type.Rock, Type.Water));
            put(Type.Electr, Arrays.asList(Type.Flying, Type.Water));
            put(Type.Psychc, Arrays.asList(Type.Fight, Type.Poison));
            put(Type.Ice, Arrays.asList(Type.Fight, Type.Ground, Type.Grass, Type.Dragon));
            put(Type.Dragon, Collections.singletonList(Type.Dragon));
            put(Type.Dark, Arrays.asList(Type.Ghost, Type.Psychc));
            put(Type.Fairy, Arrays.asList(Type.Fight, Type.Dragon, Type.Dark));
        }
    };

    public static HashMap<Type, List<Type>> resistance = new HashMap<Type, List<Type>>() {
        {
            put(Type.Normal, Arrays.asList(Type.Rock, Type.Steel));
            put(Type.Fight, Arrays.asList(Type.Flying, Type.Poison, Type.Bug, Type.Psychc, Type.Fairy));
            put(Type.Flying, Arrays.asList(Type.Rock, Type.Steel, Type.Electr));
            put(Type.Poison, Arrays.asList(Type.Poison, Type.Ground, Type.Rock, Type.Ghost));
            put(Type.Ground, Arrays.asList(Type.Bug, Type.Grass));
            put(Type.Rock, Arrays.asList(Type.Fight, Type.Ground, Type.Steel));
            put(Type.Bug, Arrays.asList(Type.Fight, Type.Flying, Type.Poison, Type.Ghost, Type.Steel, Type.Fire, Type.Fairy));
            put(Type.Ghost, Collections.singletonList(Type.Dark));
            put(Type.Steel, Arrays.asList(Type.Steel, Type.Fire, Type.Water, Type.Electr));
            put(Type.Fire, Arrays.asList(Type.Fire, Type.Water, Type.Dragon));
            put(Type.Water, Arrays.asList(Type.Water, Type.Grass, Type.Dragon));
            put(Type.Grass, Arrays.asList(Type.Flying, Type.Poison, Type.Bug, Type.Steel, Type.Fire, Type.Grass, Type.Dragon));
            put(Type.Electr, Arrays.asList(Type.Grass, Type.Electr, Type.Dragon));
            put(Type.Psychc, Arrays.asList(Type.Steel, Type.Psychc));
            put(Type.Ice, Arrays.asList(Type.Steel, Type.Fire, Type.Water, Type.Ice));
            put(Type.Dragon, Collections.singletonList(Type.Steel));
            put(Type.Dark, Arrays.asList(Type.Fight, Type.Dark, Type.Fairy));
            put(Type.Fairy, Arrays.asList(Type.Poison, Type.Steel, Type.Fire));
        }
    };

    public static HashMap<Type, List<Type>> immune = new HashMap<Type, List<Type>>() {
        {
            put(Type.Normal, Collections.singletonList(Type.Ghost));
            put(Type.Fight, Collections.singletonList(Type.Ghost));
            put(Type.Flying, Collections.emptyList());
            put(Type.Poison, Collections.singletonList(Type.Steel));
            put(Type.Ground, Collections.singletonList(Type.Flying));
            put(Type.Rock, Collections.emptyList());
            put(Type.Bug, Collections.emptyList());
            put(Type.Ghost, Collections.singletonList(Type.Normal));
            put(Type.Steel, Collections.emptyList());
            put(Type.Fire, Collections.emptyList());
            put(Type.Water, Collections.emptyList());
            put(Type.Grass, Collections.emptyList());
            put(Type.Electr, Collections.singletonList(Type.Ground));
            put(Type.Psychc, Collections.singletonList(Type.Dark));
            put(Type.Ice, Collections.emptyList());
            put(Type.Dragon, Collections.singletonList(Type.Fairy));
            put(Type.Dark, Collections.emptyList());
            put(Type.Fairy, Collections.emptyList());
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
