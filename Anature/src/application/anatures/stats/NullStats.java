package application.anatures.stats;

import application.interfaces.stats.IStats;

public class NullStats extends Stats
{
	private static final long serialVersionUID = -8571933077831350839L;

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
