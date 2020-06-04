package application.anatures.stats;

import application.enums.Stat;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.IBuilder;
import application.interfaces.stats.IStats;

public class StatsBuilder implements IBuilder<IStats>
{
	private Stats mStats;

	public StatsBuilder()
	{
		generateNewStatsCore();
	}

	/*
	 * PUBLIC SETS
	 */

	public StatsBuilder withLevlingSpeed(LevelingSpeed levelingSpeed)
	{
		mStats.setLevelingSpeed(levelingSpeed);
		return this;
	}

	public StatsBuilder withNature(Natures nature)
	{
		mStats.setNature(nature);
		return this;
	}

	public StatsBuilder withBaseExperience(int baseExperience)
	{
		mStats.setBaseExperience(baseExperience);
		return this;
	}

	public StatsBuilder withBaseHitPoints(int baseHitPoints)
	{
		mStats.setBaseHitPoints(baseHitPoints);
		return this;
	}

	public StatsBuilder withBaseAttack(int baseAttack)
	{
		mStats.setBaseAttack(baseAttack);
		return this;
	}

	public StatsBuilder withBaseDefense(int baseDefense)
	{
		mStats.setBaseDefense(baseDefense);
		return this;
	}

	public StatsBuilder withBaseSpecialAttack(int baseSpecialAttack)
	{
		mStats.setBaseSpecialAttack(baseSpecialAttack);
		return this;
	}

	public StatsBuilder withBaseSpecialDefense(int baseSpecialDefense)
	{
		mStats.setBaseSpecialDefense(baseSpecialDefense);
		return this;
	}

	public StatsBuilder withBaseSpeed(int baseSpeed)
	{
		mStats.setBaseSpeed(baseSpeed);
		return this;
	}

	public StatsBuilder withBaseAccuracy(int baseAccuracy)
	{
		mStats.setBaseAccuracy(baseAccuracy);
		return this;
	}

	public StatsBuilder withBaseEvasion(int baseEvasion)
	{
		mStats.setBaseEvasion(baseEvasion);
		return this;
	}
	
	public StatsBuilder withEVHitPoints(int EVHitpoints)
	{
		mStats.setIVHitPoints(EVHitpoints);
		return this;
	}

	public StatsBuilder withEVAttack(int EVAttack)
	{
		mStats.setIVAttack(EVAttack);
		return this;
	}

	public StatsBuilder withEVDefense(int EVDefense)
	{
		mStats.setIVDefense(EVDefense);
		return this;
	}

	public StatsBuilder withEVSpecialAttack(int EVSpecialAttack)
	{
		mStats.setIVSpecialAttack(EVSpecialAttack);
		return this;
	}

	public StatsBuilder withEVSpecialDefense(int EVSpecialDefnese)
	{
		mStats.setIVSpecialDefense(EVSpecialDefnese);
		return this;
	}

	public StatsBuilder withEVSpeed(int EVSpeed)
	{
		mStats.setIVSpeed(EVSpeed);
		return this;
	}

	public StatsBuilder withIVHitPoints(int IVHitpoints)
	{
		mStats.setIVHitPoints(IVHitpoints);
		return this;
	}

	public StatsBuilder withIVAttack(int IVAttack)
	{
		mStats.setIVAttack(IVAttack);
		return this;
	}

	public StatsBuilder withIVDefense(int IVDefense)
	{
		mStats.setIVDefense(IVDefense);
		return this;
	}

	public StatsBuilder withIVSpecialAttack(int IVSpecialAttack)
	{
		mStats.setIVSpecialAttack(IVSpecialAttack);
		return this;
	}

	public StatsBuilder withIVSpecialDefense(int IVSpecialDefnese)
	{
		mStats.setIVSpecialDefense(IVSpecialDefnese);
		return this;
	}

	public StatsBuilder withIVSpeed(int IVSpeed)
	{
		mStats.setIVSpeed(IVSpeed);
		return this;
	}

	public StatsBuilder atLevel(int level)
	{
		mStats.setLevel(level);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public IStats create()
	{
		if(mStats.canCreateStats())
		{
			mStats.levelUpStats();

			IStats statsToReturn = mStats;
			
			statsToReturn.setCurrentHitPoints(statsToReturn.getTotalStat(Stat.HitPoints));

			generateNewStatsCore();

			return statsToReturn;
		}

		throw new IllegalStateException("All the builder variables need to have a value before you create a Stats.");
	}

	/*
	 * PRIVATE METHODS
	 */

	private void generateNewStatsCore()
	{
		mStats = new Stats();
	}
}
