package application.enums;

import java.util.ArrayList;

public enum Natures
{
	Hardy(NatureStats.None, NatureStats.None),
	Lonely(NatureStats.Attack, NatureStats.Defense),
	Brave(NatureStats.Attack, NatureStats.Speed),
	Adamant(NatureStats.Attack, NatureStats.SpecialAttack),
	Naughty(NatureStats.Attack, NatureStats.SpecialDefense),
	Docile(NatureStats.None, NatureStats.None),
	Bold(NatureStats.Defense, NatureStats.Attack),
	Relaxed(NatureStats.Defense, NatureStats.Speed),
	Impish(NatureStats.Defense, NatureStats.SpecialAttack),
	Lax(NatureStats.Defense, NatureStats.SpecialDefense),
	Serious(NatureStats.None, NatureStats.None),
	Timid(NatureStats.Speed, NatureStats.Attack),
	Hasty(NatureStats.Speed, NatureStats.Defense),
	Jolly(NatureStats.Speed, NatureStats.SpecialAttack),
	Naive(NatureStats.Speed, NatureStats.SpecialDefense),
	Bashful(NatureStats.None, NatureStats.None),
	Modest(NatureStats.SpecialAttack, NatureStats.Attack),
	Mild(NatureStats.SpecialAttack, NatureStats.Defense),
	Quiet(NatureStats.SpecialAttack, NatureStats.Speed),
	Rash(NatureStats.SpecialAttack, NatureStats.SpecialDefense),
	Quirky(NatureStats.None, NatureStats.None),
	Calm(NatureStats.SpecialDefense, NatureStats.Attack),
	Gentle(NatureStats.SpecialDefense, NatureStats.Defense),
	Sassy(NatureStats.SpecialDefense, NatureStats.Speed),
	Careful(NatureStats.SpecialDefense, NatureStats.SpecialAttack),
	NotSet(NatureStats.None, NatureStats.None);

	private final NatureStats mIncreasedStat;
	private final NatureStats mDecreasedStat;

	private Natures(NatureStats increasedStat, NatureStats decreasedStat)
	{
		mIncreasedStat = increasedStat;
		mDecreasedStat = decreasedStat;
	}

	public NatureStats getIncreasedStat()
	{
		return mIncreasedStat;
	}

	public NatureStats getDecreasedStat()
	{
		return mDecreasedStat;
	}
	
	public double getModifier()
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
	
	public enum NatureStats
	{
		Attack("Attack"),
		Defense("Defense"),
		SpecialAttack("Special Attack"),
		SpecialDefense("Special Defense"),
		Speed("Speed"),
		None("None");
		
		private final String name;
		
		private NatureStats(String name)
		{
			this.name = name;
		}
		
		public String toString()
		{
			return this.name;
		}
	}
}
