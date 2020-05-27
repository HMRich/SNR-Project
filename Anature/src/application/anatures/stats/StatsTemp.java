package application.anatures.stats;

import application.enums.TempStatsStages;
import application.interfaces.stats.IStatsTemp;

class StatsTemp implements IStatsTemp
{
	private TempStatsStages mTempAttack;
	private TempStatsStages mTempDefense;
	private TempStatsStages mTempSpecialAttack;
	private TempStatsStages mTempSpecialDefense;
	private TempStatsStages mTempSpeed;
	private TempStatsStages mTempAccuracy;
	private TempStatsStages mTempEvasion;

	StatsTemp()
	{
		resetTempStats();
	}

	/*
	 * PUBLIC GETS
	 */

	public TempStatsStages getTempAttack()
	{
		return mTempAttack;
	}

	public TempStatsStages getTempDefense()
	{
		return mTempDefense;
	}

	public TempStatsStages getTempSpecialAttack()
	{
		return mTempSpecialAttack;
	}

	public TempStatsStages getTempSpecialDefense()
	{
		return mTempSpecialDefense;
	}

	public TempStatsStages getTempSpeed()
	{
		return mTempSpeed;
	}

	public TempStatsStages getTempAccuracy()
	{
		return mTempAccuracy;
	}

	public TempStatsStages getTempEvasion()
	{
		return mTempEvasion;
	}

	/*
	 * PROTECTED METHODS
	 */

	public void increaseTempAttack()
	{
		mTempAttack = mTempAttack.incrementStage();
	}

	public void decreaseTempAttack()
	{
		mTempAttack = mTempAttack.decrementStage();
	}

	public void increaseTempDefense()
	{
		mTempDefense = mTempDefense.incrementStage();
	}

	public void decreaseTempDefense()
	{
		mTempDefense = mTempDefense.decrementStage();
	}

	public void increaseTempSpecialAttack()
	{
		mTempSpecialAttack = mTempSpecialAttack.incrementStage();
	}

	public void decreaseTempSpecialAttack()
	{
		mTempSpecialAttack = mTempSpecialAttack.decrementStage();
	}

	public void increaseTempSpecialDefense()
	{
		mTempSpecialDefense = mTempSpecialDefense.incrementStage();
	}

	public void decreaseTempSpecialDefense()
	{
		mTempSpecialDefense = mTempSpecialDefense.decrementStage();
	}

	public void increaseTempSpeed()
	{
		mTempSpeed = mTempSpeed.incrementStage();
	}

	public void decreaseTempSpeed()
	{
		mTempSpeed = mTempSpeed.decrementStage();
	}

	public void increaseTempAccuracy()
	{
		mTempAccuracy = mTempAccuracy.incrementStage();
	}

	public void decreaseTempAccuracy()
	{
		mTempAccuracy = mTempAccuracy.decrementStage();
	}

	public void increaseTempEvasion()
	{
		mTempEvasion = mTempEvasion.incrementStage();
	}

	public void decreaseTempEvaion()
	{
		mTempEvasion = mTempEvasion.decrementStage();
	}

	/*
	 * PUBLIC METHODS
	 */

	public void resetTempStats()
	{
		mTempAttack = TempStatsStages.zero;
		mTempDefense = TempStatsStages.zero;
		mTempSpecialAttack = TempStatsStages.zero;
		mTempSpecialDefense = TempStatsStages.zero;
		mTempSpeed = TempStatsStages.zero;
		mTempAccuracy = TempStatsStages.zero;
		mTempEvasion = TempStatsStages.zero;
	}
}
