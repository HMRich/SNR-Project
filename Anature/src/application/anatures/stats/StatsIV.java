package application.anatures.stats;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.stats.IStatsIV;

class StatsIV extends StatsEV implements IStatsIV
{
	private static final long serialVersionUID = -3556023284775856239L;

	private int mIVHitPoints;
	private int mIVAttack;
	private int mIVDefense;
	private int mIVSpecialAttack;
	private int mIVSpecialDefense;
	private int mIVSpeed;

	StatsIV()
	{
		mIVHitPoints = -1;
		mIVAttack = -1;
		mIVDefense = -1;
		mIVSpecialAttack = -1;
		mIVSpecialDefense = -1;
		mIVSpeed = -1;
	}

	/*
	 * PUBLIC GETS
	 */

	public int getIvStat(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
				return mIVHitPoints;

			case Attack:
				return mIVAttack;

			case Defense:
				return mIVDefense;

			case SpecialAttack:
				return mIVSpecialAttack;

			case SpecialDefense:
				return mIVSpecialDefense;

			case Speed:
				return mIVSpeed;

			default:
				LoggerController.logEvent(LoggingTypes.Error, "Tried getting Iv Stat with non applicable enum.");
				return -1;
		}
	}

	/*
	 * PACKAGE SETS
	 */

	void setIVHitPoints(int IVHitpoints)
	{
		if(IVHitpoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"IVHitpoints\" was below 0.");
		}

		mIVHitPoints = IVHitpoints;
	}

	void setIVAttack(int IVAttack)
	{
		if(IVAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"IVAttack\" was below 0.");
		}

		mIVAttack = IVAttack;
	}

	void setIVDefense(int IVDefense)
	{
		if(IVDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"IVDefense\" was below 0.");
		}

		mIVDefense = IVDefense;
	}

	void setIVSpecialAttack(int IVSpecialAttack)
	{
		if(IVSpecialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"IVSpecialAttack\" was below 0.");
		}

		mIVSpecialAttack = IVSpecialAttack;
	}

	void setIVSpecialDefense(int IVSpecialDefnese)
	{
		if(IVSpecialDefnese < 0)
		{
			throw new IllegalArgumentException("Passed value \"IVSpecialDefnese\" was below 0.");
		}

		mIVSpecialDefense = IVSpecialDefnese;
	}

	void setIVSpeed(int IVSpeed)
	{
		if(IVSpeed < 0)
		{
			throw new IllegalArgumentException("Passed value \"IVSpeed\" was below 0.");
		}

		mIVSpeed = IVSpeed;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreateStatsIV()
	{
		if(getIvStat(Stat.HitPoints) == -1)
		{
			throw new IllegalStateException("The \"IVHitpoints\" variable was never set during construction.");
		}

		if(getIvStat(Stat.Attack) == -1)
		{
			throw new IllegalStateException("The \"IVAttack\" variable was never set during construction.");
		}

		if(getIvStat(Stat.Defense) == -1)
		{
			throw new IllegalStateException("The \"IVDefense\" variable was never set during construction.");
		}

		if(getIvStat(Stat.SpecialAttack) == -1)
		{
			throw new IllegalStateException("The \"IVSpecialAttack\" variable was never set during construction.");
		}

		if(getIvStat(Stat.SpecialDefense) == -1)
		{
			throw new IllegalStateException("The \"IVSpecialDefnese\" variable was never set during construction.");
		}

		if(getIvStat(Stat.Speed) == -1)
		{
			throw new IllegalStateException("The \"IVSpeed\" variable was never set during construction.");
		}

		return true;
	}
}
