package application.anatures.stats;

import application.interfaces.stats.IStatsTemp;

class StatsTemp implements IStatsTemp
{
	private int mTempAttack;
	private int mTempDefense;
	private int mTempSpecialAttack;
	private int mTempSpecialDefense;
	private int mTempSpeed;
	private int mTempAccuracy;
	private int mTempEvasion;

	StatsTemp()
	{
		resetTempStats();
	}

	/*
	 * PUBLIC GETS
	 */

	public int getTempAttack()
	{
		return mTempAttack;
	}

	public int getTempDefense()
	{
		return mTempDefense;
	}

	public int getTempSpecialAttack()
	{
		return mTempSpecialAttack;
	}

	public int getTempSpecialDefense()
	{
		return mTempSpecialDefense;
	}

	public int getTempSpeed()
	{
		return mTempSpeed;
	}

	public int getTempAccuracy()
	{
		return mTempAccuracy;
	}

	public int getTempEvasion()
	{
		return mTempEvasion;
	}

	/*
	 * PROTECTED METHODS
	 */

	void adjustTempAttack(int tempAttack)
	{
		mTempAttack += tempAttack;
	}

	void adjustTempDefense(int tempDefense)
	{
		mTempDefense += tempDefense;
	}

	void adjustTempSpecialAttack(int tempSpecialAttack)
	{
		mTempSpecialAttack += tempSpecialAttack;
	}

	void adjustTempSpecialDefense(int tempSpecialDefense)
	{
		mTempSpecialDefense += tempSpecialDefense;
	}

	void adjustTempSpeed(int tempSpeed)
	{
		mTempSpeed += tempSpeed;
	}

	void adjustTempAccuracy(int tempAccuracy)
	{
		mTempAccuracy += tempAccuracy;
	}

	void adjustTempEvaion(int tempEvasion)
	{
		mTempEvasion += tempEvasion;
	}
	
	/*
	 * PUBLIC METHODS
	 */

	public void resetTempStats()
	{
		mTempAttack = 0;
		mTempDefense = 0;
		mTempSpecialAttack = 0;
		mTempSpecialDefense = 0;
		mTempSpeed = 0;
		mTempAccuracy = 0;
		mTempEvasion = 0;
	}
}
