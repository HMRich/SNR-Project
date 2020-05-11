package application.anatures.stats;

class StatsEV extends StatsLevel
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
