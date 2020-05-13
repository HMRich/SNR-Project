package application.anatures.stats;

import application.interfaces.stats.IStatsEV;

class StatsEV extends StatsLevel implements IStatsEV
{
	private int mEVHitPoints;
	private int mEVAttack;
	private int mEVDefense;
	private int mEVSpecialAttack;
	private int mEVSpecialDefense;
	private int mEVSpeed;

	StatsEV()
	{
		mEVHitPoints = 0;
		mEVAttack = 0;
		mEVDefense = 0;
		mEVSpecialAttack = 0;
		mEVSpecialDefense = 0;
		mEVSpeed = 0;
	}

	/*
	 * PACKAGE SETS
	 */

	void setEVHitPoints(int EVHitPoints)
	{
		if(EVHitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVHitPoints\" was below 0.");
		}

		if(EVHitPoints > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVHitPoints\" was above 255.");
		}

		mEVHitPoints = EVHitPoints;
	}

	void setEVAttack(int EVAttack)
	{
		if(EVAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVAttack\" was below 0.");
		}

		if(EVAttack > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVAttack\" was above 255.");
		}

		mEVAttack = EVAttack;
	}

	void setEVDefense(int EVDefense)
	{
		if(EVDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVDefense\" was below 0.");
		}

		if(EVDefense > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVDefense\" was above 255.");
		}

		mEVDefense = EVDefense;
	}

	void setEVSpecialAttack(int EVSpecialAttack)
	{
		if(EVSpecialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialAttack\" was below 0.");
		}

		if(EVSpecialAttack > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialAttack\" was above 255.");
		}

		mEVSpecialAttack = EVSpecialAttack;
	}

	void setEVSpecialDefense(int EVSpecialDefense)
	{
		if(EVSpecialDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialDefense\" was below 0.");
		}

		if(EVSpecialDefense > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialDefense\" was above 255.");
		}

		mEVSpecialDefense = EVSpecialDefense;
	}

	void setEVSpeed(int EVSpeed)
	{
		if(EVSpeed < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVSpeed\" was below 0.");
		}

		if(EVSpeed > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVSpeed\" was above 255.");
		}

		mEVSpeed = EVSpeed;
	}

	/*
	 * PACKAGE METHODS
	 */

	public int getEVHitPoints()
	{
		return mEVHitPoints;
	}

	public int getEVAttack()
	{
		return mEVAttack;
	}

	public int getEVDefense()
	{
		return mEVDefense;
	}

	public int getEVSpecialAttack()
	{
		return mEVSpecialAttack;
	}

	public int getEVSpecialDefense()
	{
		return mEVSpecialDefense;
	}

	public int getEVSpeed()
	{
		return mEVSpeed;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean addEVHitPoint()
	{
		if(mEVHitPoints != 252)
		{
			mEVHitPoints += 1;
			return true;
		}
		return false;
	}

	boolean addEVAttack()
	{
		if(mEVAttack != 252)
		{
			mEVAttack += 1;
			return true;
		}
		return false;
	}

	boolean addEVDefense()
	{
		if(mEVDefense != 252)
		{
			mEVDefense += 1;
			return true;
		}
		return false;
	}

	boolean addEVSpecialAttack()
	{
		if(mEVSpecialAttack != 252)
		{
			mEVSpecialAttack += 1;
			return true;
		}
		return false;
	}

	boolean addEVSpecialDefense()
	{
		if(mEVSpecialDefense != 252)
		{
			mEVSpecialDefense += 1;
			return true;
		}
		return false;
	}

	boolean addEVSpeed()
	{
		if(mEVSpeed != 252)
		{
			mEVSpeed += 1;
			return true;
		}
		return false;
	}

	/*
	 * PUBLIC METHODS
	 */

	public int getEVHitPointsReduced()
	{
		return mEVHitPoints / 4;
	}

	public int getEVAttackReduced()
	{
		return mEVAttack / 4;
	}

	public int getEVDefenseReduced()
	{
		return mEVDefense / 4;
	}

	public int getEVSpecialAttackReduced()
	{
		return mEVSpecialAttack / 4;
	}

	public int getEVSpecialDefenseReduced()
	{
		return mEVSpecialDefense / 4;
	}

	public int getEVSpeedReduced()
	{
		return mEVSpeed / 4;
	}
}
