package application.interfaces.stats;

import application.anatures.stats.StatsBuilder;
import application.enums.Stat;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;

public interface IStats extends IStatsBase
{
	/*
	 * PUBLIC GETS
	 */

	public int getLevel();

	public int getTotalExperiencePoints();

	public int getCurrentHitPoints();

	public LevelingSpeed getLevelingSpeed();

	public Natures getNature();
	
	public int getTotalStat(Stat stat);
	
	public int getNatureModifierValue(Stat stat);
	
	public Stat getLargestStat();

	/*
	 * PUBLIC SETS
	 */

	public void setCurrentHitPoints(int hitPoints);

	public int healAnature(int healAmount);

	/*
	 * PUBLIC METHODS
	 */

	public int addExperience(int expeienceGain);

	public int getExperienceProgression();

	public int getRequiredExperience();
	
	public double getHitPointsPercent();
	
	public void applyDamage(int damage);

	public String applyHeal(int healAmount);

	public StatsBuilder getClone();
	
	public boolean addEv(Stat statToAdd, int level);
	
	public boolean deepEquals(IStats stats);
}
