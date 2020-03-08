package application;

import application.enums.StatusEffects;

public class MoveResult extends Result
{
	private double mDamageDone;
	private String mMpTxt;
	private int mMoveIndex;//NOTE: a moveIndex of -1 means that the move was skipped
	private boolean mIsPlayer;
	
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
	
}
