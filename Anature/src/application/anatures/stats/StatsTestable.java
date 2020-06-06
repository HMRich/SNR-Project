package application.anatures.stats;

public class StatsTestable extends Stats
{
	private static final long serialVersionUID = -3973885491828426036L;

	private boolean mResetTempStatsWasCalled;
	private boolean mGetHitPointsPercentWasCalled;
	private boolean mApplyHealWasCalledWithMaxIntValue;
	private boolean mApplyHealWasCalled;
	private boolean mApplyDamageWasCalled;

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
}
