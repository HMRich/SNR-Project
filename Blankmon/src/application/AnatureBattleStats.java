package application;

public class AnatureBattleStats
{
	private int mTempAttack, mTempSpecialAttack, mTempDefense, mTempSpecialDefense, mTempSpeed;

	public AnatureBattleStats()
	{
		resetTempStats();
	}

	public int getTempAttack()
	{
		return mTempAttack;
	}

	public void setTempAttack(int tempAttack)
	{
		mTempAttack = tempAttack;
	}

	public int getTempSpecialAttack()
	{
		return mTempSpecialAttack;
	}

	public void setTempSpecialAttack(int tempSpecialAttack)
	{
		mTempSpecialAttack = tempSpecialAttack;
	}

	public int getTempDefense()
	{
		return mTempDefense;
	}

	public void setTempDefense(int tempDefense)
	{
		mTempDefense = tempDefense;
	}

	public int getTempSpecialDefense()
	{
		return mTempSpecialDefense;
	}

	public void setTempSpecialDefense(int tempSpecialDefense)
	{
		mTempSpecialDefense = tempSpecialDefense;
	}

	public int getTempSpeed()
	{
		return mTempSpeed;
	}

	public void setTempSpeed(int tempSpeed)
	{
		mTempSpeed = tempSpeed;
	}

	public void resetTempStats()
	{
		mTempAttack = 0;
		mTempSpecialAttack = 0;
		mTempDefense = 0;
		mTempSpecialDefense = 0;
		mTempSpeed = 0;
	}
}
