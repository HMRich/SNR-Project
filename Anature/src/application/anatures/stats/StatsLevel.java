package application.anatures.stats;

import application.interfaces.stats.IStatsLevel;

class StatsLevel extends StatsTemp implements IStatsLevel
{
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

	public int getLevelHitPoints()
	{
		return mLevelHitPoints;
	}

	public int getLevelAttack()
	{
		return mLevelAttack;
	}

	public int getLevelDefense()
	{
		return mLevelDefense;
	}

	public int getLevelSpecialAttack()
	{
		return mLevelSpecialAttack;
	}

	public int getLevelSpecialDefense()
	{
		return mLevelSpecialDefense;
	}

	public int getLevelSpeed()
	{
		return mLevelSpeed;
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
