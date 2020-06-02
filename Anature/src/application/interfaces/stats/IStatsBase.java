package application.interfaces.stats;

import application.enums.Stat;

public interface IStatsBase extends IStatsIV
{
	public int getBaseExperience();
	
	public int getBaseStat(Stat stat);
}
