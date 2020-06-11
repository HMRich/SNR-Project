package application.anatures.stats;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.stats.IStatsLevel;

class StatsLevel extends StatsTemp implements IStatsLevel
{
	private static final long serialVersionUID = 1801636159245227092L;

	private int mLevelHitPoints;
	private int mLevelAttack;
	private int mLevelDefense;
	private int mLevelSpecialAttack;
	private int mLevelSpecialDefense;
	private int mLevelSpeed;

	StatsLevel()
	{
		mLevelHitPoints = 0;
		mLevelAttack = 0;
		mLevelDefense = 0;
		mLevelSpecialAttack = 0;
		mLevelSpecialDefense = 0;
		mLevelSpeed = 0;
	}

	/*
	 * PUBLIC GETS
	 */

	public int getLevelStat(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
				return mLevelHitPoints;

			case Attack:
				return mLevelAttack;

			case Defense:
				return mLevelDefense;

			case SpecialAttack:
				return mLevelSpecialAttack;

			case SpecialDefense:
				return mLevelSpecialDefense;

			case Speed:
				return mLevelSpeed;

			default:
				LoggerController.logEvent(LoggingTypes.Error, "Tried getting Ev Stat with non applicable enum.");
				return -1;
		}
	}

	/*
	 * PACKAGE METHODS
	 */

	void addLevelHitPoints(int levelHitPoints)
	{
		if(levelHitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"levelHitPoints\" was below 0.");
		}

		mLevelHitPoints += levelHitPoints;
	}

	void addLevelAttack(int levelAttack)
	{
		if(levelAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"levelAttack\" was below 0.");
		}

		mLevelAttack += levelAttack;
	}

	void addLevelDefnse(int levelDefense)
	{
		if(levelDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"levelDefense\" was below 0.");
		}

		mLevelDefense += levelDefense;
	}

	void addLevelSpecialAttack(int levelSpecialAttack)
	{
		if(levelSpecialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"levelSpecialAttack\" was below 0.");
		}

		mLevelSpecialAttack += levelSpecialAttack;
	}

	void addLevelSpecialDefense(int levelSpecialDefense)
	{
		if(levelSpecialDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"levelSpecialDefense\" was below 0.");
		}

		mLevelSpecialDefense += levelSpecialDefense;
	}

	void addLevelSpeed(int levelSpeed)
	{
		if(levelSpeed < 0)
		{
			throw new IllegalArgumentException("Passed value \"levelSpeed\" was below 0.");
		}

		mLevelSpeed += levelSpeed;
	}
}
