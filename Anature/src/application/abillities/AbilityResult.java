package application.abillities;

import java.util.ArrayList;

import application.Result;

public class AbilityResult extends Result 
{
	private boolean mIsActivated;
	
	public AbilityResult(String dialogue, boolean isActivated) 
	{
		super(dialogue);
		mIsActivated = isActivated;
	}
	
	public AbilityResult(ArrayList<String> dialogue, boolean isActivated) 
	{
		super(dialogue);
		mIsActivated = isActivated;
	}
	
	public boolean isActiviated()
	{
		return mIsActivated;
	}
	
	public void setActivated(boolean isActivated)
	{
		mIsActivated = isActivated;
	}
}