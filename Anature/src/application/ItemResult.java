package application;

import application.abillities.AbilityResult;

public class ItemResult extends Result
{
	private double mHpGained;
	private AbilityResult mEnemyAbilityResult;
	private AbilityResult mPlayerAbilityResult;

	public ItemResult(String dialogue, double hpGained)
	{
		super(dialogue);

		mHpGained = hpGained;
	}

	public double getHpGained()
	{
		return mHpGained;
	}
	public AbilityResult getEnemyAbilityResult() 
	{
		return mEnemyAbilityResult;
	}

	public AbilityResult getPlayerAbilityResult() 
	{
		return mPlayerAbilityResult;
	}
}
