package application.enums.stats;

import java.util.ArrayList;

import application.enums.Stat;

public enum Natures
{
	Hardy(null, null),
	Lonely(Stat.Attack, Stat.Defense),
	Brave(Stat.Attack, Stat.Speed),
	Adamant(Stat.Attack, Stat.SpecialAttack),
	Naughty(Stat.Attack, Stat.SpecialDefense),
	Docile(null, null),
	Bold(Stat.Defense, Stat.Attack),
	Relaxed(Stat.Defense, Stat.Speed),
	Impish(Stat.Defense, Stat.SpecialAttack),
	Lax(Stat.Defense, Stat.SpecialDefense),
	Serious(null, null),
	Timid(Stat.Speed, Stat.Attack),
	Hasty(Stat.Speed, Stat.Defense),
	Jolly(Stat.Speed, Stat.SpecialAttack),
	Naive(Stat.Speed, Stat.SpecialDefense),
	Bashful(null, null),
	Modest(Stat.SpecialAttack, Stat.Attack),
	Mild(Stat.SpecialAttack, Stat.Defense),
	Quiet(Stat.SpecialAttack, Stat.Speed),
	Rash(Stat.SpecialAttack, Stat.SpecialDefense),
	Quirky(null, null),
	Calm(Stat.SpecialDefense, Stat.Attack),
	Gentle(Stat.SpecialDefense, Stat.Defense),
	Sassy(Stat.SpecialDefense, Stat.Speed),
	Careful(Stat.SpecialDefense, Stat.SpecialAttack),
	NotSet(null, null);

	private final Stat mIncreasedStat;
	private final Stat mDecreasedStat;

	private Natures(Stat increasedStat, Stat decreasedStat)
	{
		mIncreasedStat = increasedStat;
		mDecreasedStat = decreasedStat;
	}

	public Stat getIncreasedStat()
	{
		return mIncreasedStat;
	}

	public Stat getDecreasedStat()
	{
		return mDecreasedStat;
	}

	public static double modifier()
	{
		return 0.10;
	}

	public static ArrayList<Natures> getNatureList()
	{
		ArrayList<Natures> natures = new ArrayList<Natures>();
		Natures[] natureList = Natures.values();

		for(Natures nature : natureList)
		{
			if(!nature.equals(Natures.NotSet))
			{
				natures.add(nature);
			}
		}

		return natures;
	}
}
