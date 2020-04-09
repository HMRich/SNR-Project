package application.abillities;

import application.Result;

public class AbilityResult extends Result 
{
	private boolean mIsActivated;
	
	public AbilityResult(String dialogue, boolean isActivated) 
	{
		super(dialogue);
		mIsActivated = isActivated;
	}

	
	
	public boolean isActiviated()
	{
		return mIsActivated;
	}

}