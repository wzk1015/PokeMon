package pokemon;

import battles.Status;

public class Pokemon extends PokemonSpecies {
    public Gender gender;
    public Character character;
    public StatType addStatType;
    public StatType subStatType;
    public int friendShip;
    public int level;
    public int exp;

    public Stat individualStat;
    public Stat effortStat;

    public int curHP;
    public Status status = null;
    public boolean isInfatuated = false;
    public boolean isConfused = false;
    public boolean isFlinched = false;

}
