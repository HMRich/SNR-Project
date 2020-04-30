package application.anatures.stats;

class StatsIV extends StatsEV
{
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

	public int getIVHitPoints()
	{
		return mIVHitPoints;
	}

	public int getIVAttack()
	{
		return mIVAttack;
	}

	public int getIVDefense()
	{
		return mIVDefense;
	}

	public int getIVSpecialAttack()
	{
		return mIVSpecialAttack;
	}

	public int getIVSpecialDefnese()
	{
		return mIVSpecialDefense;
	}

	public int getIVSpeed()
	{
		return mIVSpeed;
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
		if(getIVHitPoints() == -1)
		{
			throw new IllegalStateException("The \"IVHitpoints\" variable was never set during construction.");
		}

		if(getIVAttack() == -1)
		{
			throw new IllegalStateException("The \"IVAttack\" variable was never set during construction.");
		}

		if(getIVDefense() == -1)
		{
			throw new IllegalStateException("The \"IVDefense\" variable was never set during construction.");
		}

		if(getIVSpecialAttack() == -1)
		{
			throw new IllegalStateException("The \"IVSpecialAttack\" variable was never set during construction.");
		}

		if(getIVSpecialDefnese() == -1)
		{
			throw new IllegalStateException("The \"IVSpecialDefnese\" variable was never set during construction.");
		}

		if(getIVSpeed() == -1)
		{
			throw new IllegalStateException("The \"IVSpeed\" variable was never set during construction.");
		}

		return true;
	}
}
