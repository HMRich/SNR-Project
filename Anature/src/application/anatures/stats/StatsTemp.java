package application.anatures.stats;

import application.enums.Stat;
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
		decreaseTempStat(Stat.Attack);
	}

	public void increaseTempDefense()
	{
		mTempDefense = mTempDefense.incrementStage();
	}

	public void decreaseTempDefense()
	{
		decreaseTempStat(Stat.Defense);
	}

	public void increaseTempSpecialAttack()
	{
		mTempSpecialAttack = mTempSpecialAttack.incrementStage();
	}

	public void decreaseTempSpecialAttack()
	{
		decreaseTempStat(Stat.SpecialAttack);
	}

	public void increaseTempSpecialDefense()
	{
		mTempSpecialDefense = mTempSpecialDefense.incrementStage();
	}

	public void decreaseTempSpecialDefense()
	{
		decreaseTempStat(Stat.SpecialDefense);
	}

	public void increaseTempSpeed()
	{
		mTempSpeed = mTempSpeed.incrementStage();
	}

	public void decreaseTempSpeed()
	{
		decreaseTempStat(Stat.Speed);
	}

	public void increaseTempAccuracy()
	{
		mTempAccuracy = mTempAccuracy.incrementStage();
	}

	public void decreaseTempAccuracy()
	{
		decreaseTempStat(Stat.Accuracy);
	}

	public void increaseTempEvasion()
	{
		mTempEvasion = mTempEvasion.incrementStage();
	}

	public void decreaseTempEvasion()
	{
		decreaseTempStat(Stat.Evasion);
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

	public void decreaseTempStat(Stat stat)
	{
		switch(stat)
		{
			case Accuracy:
				mTempAccuracy = mTempAccuracy.decrementStage();
				break;

			case Attack:
				mTempAttack = mTempAttack.decrementStage();
				break;

			case Defense:
				mTempDefense = mTempDefense.decrementStage();
				break;

			case Evasion:
				mTempEvasion = mTempEvasion.decrementStage();
				break;

			case SpecialAttack:
				mTempSpecialAttack = mTempSpecialAttack.decrementStage();
				break;

			case SpecialDefense:
				mTempSpecialDefense = mTempSpecialDefense.decrementStage();
				break;

			case Speed:
				mTempSpeed = mTempSpeed.decrementStage();
				break;

			default:
				throw new IllegalArgumentException("Value \"stat\" was " + stat.toString() + " and is not a supported Temp stat.");
		}
	}

	public void increaseTempStat(Stat stat)
	{
		switch(stat)
		{
			case Accuracy:
				mTempAccuracy = mTempAccuracy.incrementStage();
				break;

			case Attack:
				mTempAttack = mTempAttack.incrementStage();
				break;

			case Defense:
				mTempDefense = mTempDefense.incrementStage();
				break;

			case Evasion:
				mTempEvasion = mTempEvasion.incrementStage();
				break;

			case SpecialAttack:
				mTempSpecialAttack = mTempSpecialAttack.incrementStage();
				break;

			case SpecialDefense:
				mTempSpecialDefense = mTempSpecialDefense.incrementStage();
				break;

			case Speed:
				mTempSpeed = mTempSpeed.incrementStage();
				break;

			default:
				throw new IllegalArgumentException("Value \"stat\" was " + stat.toString() + " and is not a supported Temp stat.");
		}
	}

	public void increaceTempStats(Stat[] stats)
	{
		for(Stat stat : stats)
		{
			increaseTempStat(stat);
		}
	}

	public void decreaseTempStats(Stat[] stats)
	{
		for(Stat stat : stats)
		{
			decreaseTempStat(stat);
		}
	}

	/*
	 * PACKAGE METHODS
	 */

	double getTempStatModifier(Stat stat)
	{
		switch(stat)
		{
			case Attack:
				return getTempAttack().getModifier();

			case Defense:
				return getTempDefense().getModifier();

			case SpecialAttack:
				return getTempSpecialAttack().getModifier();

			case SpecialDefense:
				return getTempSpecialDefense().getModifier();

			case Speed:
				return getTempSpeed().getModifier();

			default:
				return 1.0;

		}
	}
}
