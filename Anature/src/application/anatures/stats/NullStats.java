package application.anatures.stats;

import application.interfaces.stats.IStats;

public class NullStats
{
	private static IStats mNullStats = (IStats) new NullStats();
	
	public static IStats getNullStats()
	{
		return mNullStats;
	}

}
