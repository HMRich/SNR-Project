package application.interfaces.stats;

import application.enums.Stat;

public interface IStatsEV extends IStatsLevel
{
	public int getEvStat(Stat stat);
	
	public int getEvReducedStat(Stat stat);
}
