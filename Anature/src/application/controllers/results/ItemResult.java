package application.controllers.results;

import application.enums.AnacubeResults;

public class ItemResult extends Result
{
	private double mHpGained;
	private AbilityResult mEnemyAbilityResult;
	private AbilityResult mPlayerAbilityResult;
	private AnacubeResults mCatchResults;

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

	public AnacubeResults getCatchResults()
	{
		return mCatchResults;
	}

	public void setCatchResults(AnacubeResults catchResults)
	{
		mCatchResults = catchResults;
	}
}
