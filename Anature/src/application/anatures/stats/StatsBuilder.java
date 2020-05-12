package application.anatures.stats;

import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.IBuilder;
import application.interfaces.stats.IStats;

public class StatsBuilder implements IBuilder<IStats>
{
	private Stats mStatsCore;

	public StatsBuilder()
	{
		generateNewStatsCore();
	}

	/*
	 * PUBLIC SETS
	 */

	public StatsBuilder withLevlingSpeed(LevelingSpeed levelingSpeed)
	{
		mStatsCore.setLevelingSpeed(levelingSpeed);
		return this;
	}

	public StatsBuilder withNature(Natures nature)
	{
		mStatsCore.setNature(nature);
		return this;
	}

	public StatsBuilder withBaseExperience(int baseExperience)
	{
		mStatsCore.setBaseExperience(baseExperience);
		return this;
	}

	public StatsBuilder withBaseHitPoints(int baseHitPoints)
	{
		mStatsCore.setBaseHitPoints(baseHitPoints);
		return this;
	}

	public StatsBuilder withBaseAttack(int baseAttack)
	{
		mStatsCore.setBaseAttack(baseAttack);
		return this;
	}

	public StatsBuilder withBaseDefense(int baseDefense)
	{
		mStatsCore.setBaseDefense(baseDefense);
		return this;
	}

	public StatsBuilder withBaseSpecialAttack(int baseSpecialAttack)
	{
		mStatsCore.setBaseSpecialAttack(baseSpecialAttack);
		return this;
	}

	public StatsBuilder withBaseSpecialDefense(int baseSpecialDefense)
	{
		mStatsCore.setBaseSpecialDefense(baseSpecialDefense);
		return this;
	}

	public StatsBuilder withBaseSpeed(int baseSpeed)
	{
		mStatsCore.setBaseSpeed(baseSpeed);
		return this;
	}

	public StatsBuilder withBaseAccuracy(int baseAccuracy)
	{
		mStatsCore.setBaseAccuracy(baseAccuracy);
		return this;
	}

	public StatsBuilder withBaseEvasion(int baseEvasion)
	{
		mStatsCore.setBaseEvasion(baseEvasion);
		return this;
	}

	public StatsBuilder withIVHitPoints(int IVHitpoints)
	{
		mStatsCore.setIVHitPoints(IVHitpoints);
		return this;
	}

	public StatsBuilder withIVAttack(int IVAttack)
	{
		mStatsCore.setIVAttack(IVAttack);
		return this;
	}

	public StatsBuilder withIVDefense(int IVDefense)
	{
		mStatsCore.setIVDefense(IVDefense);
		return this;
	}

	public StatsBuilder withIVSpecialAttack(int IVSpecialAttack)
	{
		mStatsCore.setIVSpecialAttack(IVSpecialAttack);
		return this;
	}

	public StatsBuilder withIVSpecialDefense(int IVSpecialDefnese)
	{
		mStatsCore.setIVSpecialDefense(IVSpecialDefnese);
		return this;
	}

	public StatsBuilder withIVSpeed(int IVSpeed)
	{
		mStatsCore.setIVSpeed(IVSpeed);
		return this;
	}

	public StatsBuilder atLevel(int level)
	{
		mStatsCore.setLevel(level);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public IStats create()
	{
		if(mStatsCore.canCreateStats())
		{
			mStatsCore.levelUpStats();

			IStats statsToReturn = mStatsCore;

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
		mStatsCore = new Stats();
	}
}
