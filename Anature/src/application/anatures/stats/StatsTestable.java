package application.anatures.stats;

import java.util.HashMap;

import application.enums.Stat;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import test.helpers.TestObjects;

public class StatsTestable extends Stats
{
	private static final long serialVersionUID = -3973885491828426036L;

	private boolean mResetTempStatsWasCalled;
	private boolean mGetHitPointsPercentWasCalled;
	private boolean mApplyHealWasCalledWithMaxIntValue;
	private boolean mApplyHealWasCalled;
	private boolean mApplyDamageWasCalled;

	private boolean mSetCurrentHitPointsWasCalled;
	private int mSetCurrentHitPointsWasCalledWithValue;

	private boolean mGetBaseStatWasCalled;
	private int mGetBaseWasCalledXTimes;

	private boolean mGetIvStatWasCalled;
	private int mGetIvStatWasCalledXTimes;

	private boolean mGetLevelWasCalled;
	private boolean mGetLevelingSpeedWasCalled;
	private boolean mGetNatureWasCalled;
	private boolean mGetEvRoadMapWAsCalled;

	public StatsTestable()
	{
		super.setLevel(TestObjects.getDefaultLevel());
		super.setLevelingSpeed(TestObjects.getDefaultLevelingSpeed());
		super.setNature(TestObjects.getDefaultNature());
		super.setBaseExperience(TestObjects.getDefaultBaseStat());
		super.setBaseHitPoints(TestObjects.getDefaultBaseStat());
		super.setBaseAttack(TestObjects.getDefaultBaseStat());
		super.setBaseDefense(TestObjects.getDefaultBaseStat());
		super.setBaseSpecialAttack(TestObjects.getDefaultBaseStat());
		super.setBaseSpecialDefense(TestObjects.getDefaultBaseStat());
		super.setBaseSpeed(TestObjects.getDefaultBaseStat());
		super.setBaseAccuracy(TestObjects.getDefaultBaseStat());
		super.setBaseEvasion(TestObjects.getDefaultBaseStat());

		super.setIVHitPoints(TestObjects.getDefaultBaseNonStat());
		super.setIVAttack(TestObjects.getDefaultBaseNonStat());
		super.setIVDefense(TestObjects.getDefaultBaseNonStat());
		super.setIVSpecialAttack(TestObjects.getDefaultBaseNonStat());
		super.setIVSpecialDefense(TestObjects.getDefaultBaseNonStat());
		super.setIVSpeed(TestObjects.getDefaultBaseNonStat());

		super.setCurrentHitPoints(0);
	}

	@Override
	public int getLevel()
	{
		mGetLevelWasCalled = true;
		return TestObjects.getDefaultLevel();
	}

	@Override
	public LevelingSpeed getLevelingSpeed()
	{
		mGetLevelingSpeedWasCalled = true;
		return TestObjects.getDefaultLevelingSpeed();
	}

	@Override
	public Natures getNature()
	{
		mGetNatureWasCalled = true;
		return TestObjects.getDefaultNature();
	}

	@Override
	public int getTotalStat(Stat stat)
	{
		return TestObjects.getDefaultBaseStat();
	}

	@Override
	public int getBaseStat(Stat stat)
	{
		mGetBaseStatWasCalled = true;
		mGetBaseWasCalledXTimes++;
		return getTotalStat(stat);
	}

	@Override
	public int getIvStat(Stat stat)
	{
		mGetIvStatWasCalled = true;
		mGetIvStatWasCalledXTimes++;
		return getTotalStat(stat);
	}

	@Override
	HashMap<Integer, EvChanged> getEvRoadMap()
	{
		mGetEvRoadMapWAsCalled = true;
		return new HashMap<Integer, EvChanged>();
	}

	@Override
	public void resetTempStats()
	{
		mResetTempStatsWasCalled = true;
	}

	@Override
	public double getHitPointsPercent()
	{
		mGetHitPointsPercentWasCalled = true;
		return 0.0;
	}

	@Override
	public String applyHeal(int healAmount)
	{
		if(healAmount == Integer.MAX_VALUE)
			mApplyHealWasCalledWithMaxIntValue = true;
		mApplyHealWasCalled = true;
		return "";
	}

	@Override
	public void applyDamage(int damage)
	{
		mApplyDamageWasCalled = true;
	}

	@Override
	public void setCurrentHitPoints(int hitPoints)
	{
		mSetCurrentHitPointsWasCalled = true;
		mSetCurrentHitPointsWasCalledWithValue = hitPoints;
	}

	public boolean resetTempStatsWasCalled()
	{
		return mResetTempStatsWasCalled;
	}

	public boolean getHitPointsPercentWasCalled()
	{
		return mGetHitPointsPercentWasCalled;
	}

	public boolean applyHealWasCalledWithMaxIntValue()
	{
		return mApplyHealWasCalledWithMaxIntValue;
	}

	public boolean applyHealWasCalled()
	{
		return mApplyHealWasCalled;
	}

	public boolean applyDamageWasCalled()
	{
		return mApplyDamageWasCalled;
	}

	public boolean setCurrentHitPointsWasCalled()
	{
		return mSetCurrentHitPointsWasCalled;
	}

	public int setCurrentHitPointsWasCalledWithValue()
	{
		return mSetCurrentHitPointsWasCalledWithValue;
	}

	public boolean getBaseStatWasCalled()
	{
		return mGetBaseStatWasCalled;
	}

	public int getBaseWasCalledXTimes()
	{
		return mGetBaseWasCalledXTimes;
	}

	public boolean getIvStatWasCalled()
	{
		return mGetIvStatWasCalled;
	}

	public int getIvStatWasCalledXTimes()
	{
		return mGetIvStatWasCalledXTimes;
	}

	public boolean getLevelWasCalled()
	{
		return mGetLevelWasCalled;
	}

	public boolean getLevelingSpeedWasCalled()
	{
		return mGetLevelingSpeedWasCalled;
	}

	public boolean getNatureWasCalled()
	{
		return mGetNatureWasCalled;
	}

	public boolean getEvRoadMapWAsCalled()
	{
		return mGetEvRoadMapWAsCalled;
	}
}
