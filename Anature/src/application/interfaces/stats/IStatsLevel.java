package application.interfaces.stats;

import application.enums.Stat;

public interface IStatsLevel extends IStatsTemp
{	
	public int getLevelStat(Stat stat);
}
