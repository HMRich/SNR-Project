package application.anatures.stats;

import application.interfaces.stats.IStatsBase;

class StatsBase extends StatsIV implements IStatsBase
{
	private int mBaseExperience;
	private int mBaseHitPoints;
	private int mBaseAttack;
	private int mBaseDefense;
	private int mBaseSpecialAttack;
	private int mBaseSpecialDefense;
	private int mBaseSpeed;
	private int mBaseAccuracy;
	private int mBaseEvasion;

	StatsBase()
	{
		mBaseExperience = -1;
		mBaseHitPoints = -1;
		mBaseAttack = -1;
		mBaseDefense = -1;
		mBaseSpecialAttack = -1;
		mBaseSpecialDefense = -1;
		mBaseSpeed = -1;
		mBaseAccuracy = -1;
		mBaseEvasion = -1;
	}

	/*
	 * PUBLIC GETS
	 */
	
	public int getBaseExperience()
	{
		return mBaseExperience;
	}

	public int getBaseHitPoints()
	{
		return mBaseHitPoints;
	}

	public int getBaseAttack()
	{
		return mBaseAttack;
	}

	public int getBaseDefense()
	{
		return mBaseDefense;
	}

	public int getBaseSpecialAttack()
	{
		return mBaseSpecialAttack;
	}

	public int getBaseSpecialDefense()
	{
		return mBaseSpecialDefense;
	}

	public int getBaseSpeed()
	{
		return mBaseSpeed;
	}

	public int getBaseAccuracy()
	{
		return mBaseAccuracy;
	}

	public int getBaseEvasion()
	{
		return mBaseEvasion;
	}

	/*
	 * PACKAGE SETS
	 */
	
	void setBaseExperience(int baseExperience)
	{
		if(baseExperience < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseExperience\" was below 0.");
		}
		mBaseExperience = baseExperience;
	}

	void setBaseHitPoints(int baseHitPoints)
	{
		if(baseHitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseHitPoints\" was below 0.");
		}

		mBaseHitPoints = baseHitPoints;
	}

	void setBaseAttack(int baseAttack)
	{
		if(baseAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseAttack\" was below 0.");
		}

		mBaseAttack = baseAttack;
	}

	void setBaseDefense(int baseDefense)
	{
		if(baseDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseDefense\" was below 0.");
		}

		mBaseDefense = baseDefense;
	}

	void setBaseSpecialAttack(int baseSpecialAttack)
	{
		if(baseSpecialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseSpecialAttack\" was below 0.");
		}

		mBaseSpecialAttack = baseSpecialAttack;
	}

	void setBaseSpecialDefense(int baseSpecialDefense)
	{
		if(baseSpecialDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseSpecialDefense\" was below 0.");
		}

		mBaseSpecialDefense = baseSpecialDefense;
	}

	void setBaseSpeed(int baseSpeed)
	{
		if(baseSpeed < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseSpeed\" was below 0.");
		}

		mBaseSpeed = baseSpeed;
	}

	void setBaseAccuracy(int baseAccuracy)
	{
		if(baseAccuracy < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseAccuracy\" was below 0.");
		}

		mBaseAccuracy = baseAccuracy;
	}

	void setBaseEvasion(int baseEvasion)
	{
		if(baseEvasion < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseEvasion\" was below 0.");
		}

		mBaseEvasion = baseEvasion;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreateStatsBase()
	{
		if(getBaseExperience() == -1)
		{
			throw new IllegalStateException("The \"baseExperience\" variable was never set during construction.");
		}
		if(getBaseHitPoints() == -1)
		{
			throw new IllegalStateException("The \"baseHitPoints\" variable was never set during construction.");
		}

		if(getBaseAttack() == -1)
		{
			throw new IllegalStateException("The \"baseAttack\" variable was never set during construction.");
		}

		if(getBaseDefense() == -1)
		{
			throw new IllegalStateException("The \"baseDefense\" variable was never set during construction.");
		}

		if(getBaseSpecialAttack() == -1)
		{
			throw new IllegalStateException("The \"baseSpecialAttack\" variable was never set during construction.");
		}

		if(getBaseSpecialDefense() == -1)
		{
			throw new IllegalStateException("The \"baseSpecialDefense\" variable was never set during construction.");
		}

		if(getBaseSpeed() == -1)
		{
			throw new IllegalStateException("The \"baseSpeed\" variable was never set during construction.");
		}

		if(getBaseAccuracy() == -1)
		{
			throw new IllegalStateException("The \"baseAccuracy\" variable was never set during construction.");
		}

		if(getBaseEvasion() == -1)
		{
			throw new IllegalStateException("The \"baseEvasion\" variable was never set during construction.");
		}

		return true;
	}
}
