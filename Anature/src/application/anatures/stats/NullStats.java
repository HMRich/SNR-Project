package application.anatures.stats;

import application.interfaces.stats.IStats;

public class NullStats extends Stats
{
	private static IStats mNullStats = new NullStats();

	public static IStats getNullStats()
	{
		return mNullStats;
	}

	public String toString()
	{
		return "Null Stats";
	}
}
