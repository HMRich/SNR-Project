package application.anatures.stats;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.stats.IStatsBase;

class StatsBase extends StatsIV implements IStatsBase
{
	private static final long serialVersionUID = 2705402880181321706L;

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

	public int getBaseStat(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
				return mBaseHitPoints;

			case Attack:
				return mBaseAttack;

			case Defense:
				return mBaseDefense;

			case SpecialAttack:
				return mBaseSpecialAttack;

			case SpecialDefense:
				return mBaseSpecialDefense;

			case Speed:
				return mBaseSpeed;

			case Accuracy:
				return mBaseAccuracy;

			case Evasion:
				return mBaseEvasion;

			default:
				LoggerController.logEvent(LoggingTypes.Error, "Tried getting Base Stat with non applicable enum.");
				return -1;
		}
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

		if(baseExperience > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseExperience\" was above 255.");
		}

		mBaseExperience = baseExperience;
	}

	void setBaseHitPoints(int baseHitPoints)
	{
		if(baseHitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseHitPoints\" was below 0.");
		}

		if(baseHitPoints > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseHitPoints\" was above 255.");
		}

		mBaseHitPoints = baseHitPoints;
	}

	void setBaseAttack(int baseAttack)
	{
		if(baseAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseAttack\" was below 0.");
		}

		if(baseAttack > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseAttack\" was above 255.");
		}

		mBaseAttack = baseAttack;
	}

	void setBaseDefense(int baseDefense)
	{
		if(baseDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseDefense\" was below 0.");
		}

		if(baseDefense > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseDefense\" was above 255.");
		}

		mBaseDefense = baseDefense;
	}

	void setBaseSpecialAttack(int baseSpecialAttack)
	{
		if(baseSpecialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseSpecialAttack\" was below 0.");
		}

		if(baseSpecialAttack > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseSpecialAttack\" was above 255.");
		}

		mBaseSpecialAttack = baseSpecialAttack;
	}

	void setBaseSpecialDefense(int baseSpecialDefense)
	{
		if(baseSpecialDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseSpecialDefense\" was below 0.");
		}

		if(baseSpecialDefense > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseSpecialDefense\" was above 255.");
		}

		mBaseSpecialDefense = baseSpecialDefense;
	}

	void setBaseSpeed(int baseSpeed)
	{
		if(baseSpeed < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseSpeed\" was below 0.");
		}

		if(baseSpeed > 255)
		{
			throw new IllegalArgumentException("Passed value \"baseSpeed\" was above 255.");
		}

		mBaseSpeed = baseSpeed;
	}

	void setBaseAccuracy(int baseAccuracy)
	{
		if(baseAccuracy < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseAccuracy\" was below 0.");
		}

		if(baseAccuracy > 100)
		{
			throw new IllegalArgumentException("Passed value \"baseAccuracy\" was above 100.");
		}

		mBaseAccuracy = baseAccuracy;
	}

	void setBaseEvasion(int baseEvasion)
	{
		if(baseEvasion < 0)
		{
			throw new IllegalArgumentException("Passed value \"baseEvasion\" was below 0.");
		}

		if(baseEvasion > 100)
		{
			throw new IllegalArgumentException("Passed value \"baseEvasion\" was above 100.");
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
		if(getBaseStat(Stat.HitPoints) == -1)
		{
			throw new IllegalStateException("The \"baseHitPoints\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.Attack) == -1)
		{
			throw new IllegalStateException("The \"baseAttack\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.Defense) == -1)
		{
			throw new IllegalStateException("The \"baseDefense\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.SpecialAttack) == -1)
		{
			throw new IllegalStateException("The \"baseSpecialAttack\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.SpecialDefense) == -1)
		{
			throw new IllegalStateException("The \"baseSpecialDefense\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.Speed) == -1)
		{
			throw new IllegalStateException("The \"baseSpeed\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.Accuracy) == -1)
		{
			throw new IllegalStateException("The \"baseAccuracy\" variable was never set during construction.");
		}

		if(getBaseStat(Stat.Evasion) == -1)
		{
			throw new IllegalStateException("The \"baseEvasion\" variable was never set during construction.");
		}

		return true;
	}
}
