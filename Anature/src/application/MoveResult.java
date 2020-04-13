package application;

import java.util.ArrayList;

import application.abillities.AbilityResult;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;

public class MoveResult extends Result
{
	private double mDamageDone;
	private String mMpTxt;
	private int mMoveIndex; //NOTE: a moveIndex of -1 means that the move was skipped
	private boolean mIsPlayer, mDoesDamage;
	private AbilityResult mAbilityResult;
	
	public MoveResult(ArrayList<String> moveDialogue, AbilityResult abilityResult, int moveIndex, boolean isPlayer, boolean doesDamage)
	{
		super(moveDialogue);
		
		if(abilityResult == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "abilityResult in MoveResult constructor was null.");
			abilityResult = new AbilityResult(new ArrayList<String>(), false);
		}
		
		mAbilityResult = abilityResult;
		mDoesDamage = doesDamage; // TODO Change to be more specific using an enum (is it a kick? is it a negative effect)
		mIsPlayer = isPlayer;
		mMoveIndex = moveIndex;
	}
	
	public MoveResult(double damageDone, String dialogueTxt, int moveIndex, String mpTxt, boolean isPlayer)
	{
		super(dialogueTxt);

		if(mpTxt == null)
			throw new IllegalArgumentException("mpTxt was null");

		mDamageDone = damageDone;
		mMpTxt = mpTxt;
		mMoveIndex = moveIndex;
		mIsPlayer = isPlayer;
	}

	public double getDamageDone()
	{
		return mDamageDone;
	}

	public String getMpTxt()
	{
		return mMpTxt;
	}

	public int getMoveIndex()
	{
		return mMoveIndex;
	}

	public boolean isPlayer()
	{
		return mIsPlayer;
	}
	
	public boolean doesDamage()
	{
		return mDoesDamage;
	}
	
	public AbilityResult getAbilityResult() 
	{
		return mAbilityResult;
	}
}
