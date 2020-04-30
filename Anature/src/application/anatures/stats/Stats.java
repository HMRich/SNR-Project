package application.anatures.stats;

import application.enums.LevelingSpeed;
import application.enums.Natures;
import application.interfaces.IBuilder;
import application.interfaces.IStats;

public class Stats implements IBuilder<IStats>
{
	private StatsCore mStatsCore;

	public Stats()
	{
		generateNewStatsCore();
	}

	/*
	 * PUBLIC SETS
	 */

	public Stats withLevlingSpeed(LevelingSpeed levelingSpeed)
	{
		mStatsCore.setLevelingSpeed(levelingSpeed);
		return this;
	}

	public Stats withNature(Natures nature)
	{
		mStatsCore.setNature(nature);
		return this;
	}

	public Stats withBaseHitPoints(int baseHitPoints)
	{
		mStatsCore.setBaseHitPoints(baseHitPoints);
		return this;
	}

	public Stats withBaseAttack(int baseAttack)
	{
		mStatsCore.setBaseAttack(baseAttack);
		return this;
	}

	public Stats withBaseDefense(int baseDefense)
	{
		mStatsCore.setBaseDefense(baseDefense);
		return this;
	}

	public Stats withBaseSpecialAttack(int baseSpecialAttack)
	{
		mStatsCore.setBaseSpecialAttack(baseSpecialAttack);
		return this;
	}

	public Stats withBaseSpecialDefense(int baseSpecialDefense)
	{
		mStatsCore.setBaseSpecialDefense(baseSpecialDefense);
		return this;
	}

	public Stats withBaseSpeed(int baseSpeed)
	{
		mStatsCore.setBaseSpeed(baseSpeed);
		return this;
	}

	public Stats withBaseAccuracy(int baseAccuracy)
	{
		mStatsCore.setBaseAccuracy(baseAccuracy);
		return this;
	}

	public Stats withBaseEvasion(int baseEvasion)
	{
		mStatsCore.setBaseEvasion(baseEvasion);
		return this;
	}

	public Stats withIVHitPoints(int IVHitpoints)
	{
		mStatsCore.setIVHitPoints(IVHitpoints);
		return this;
	}

	public Stats withIVAttack(int IVAttack)
	{
		mStatsCore.setIVAttack(IVAttack);
		return this;
	}

	public Stats withIVDefense(int IVDefense)
	{
		mStatsCore.setIVDefense(IVDefense);
		return this;
	}

	public Stats withIVSpecialAttack(int IVSpecialAttack)
	{
		mStatsCore.setIVSpecialAttack(IVSpecialAttack);
		return this;
	}

	public Stats withIVSpecialDefense(int IVSpecialDefnese)
	{
		mStatsCore.setIVSpecialDefense(IVSpecialDefnese);
		return this;
	}

	public Stats withIVSpeed(int IVSpeed)
	{
		mStatsCore.setIVSpeed(IVSpeed);
		return this;
	}

	public Stats atLevel(int level)
	{
		mStatsCore.setLevel(level);
		return this;
	}

//public Stats  {
//	
//}

	/*
	 * PUBLIC METHODS
	 */

	public IStats create()
	{
		if(mStatsCore.canCreateStatsCore())
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
		mStatsCore = new StatsCore();
	}
}
