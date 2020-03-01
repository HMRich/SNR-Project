package application;

import application.abillities.AbilityResult;

public class MoveResult extends Result
{
	private double mDamageDone;
	private String mMpTxt;
	private int mMoveIndex;
	private boolean mIsPlayer;
	private AbilityResult mEnemyAbilityResult;
	private AbilityResult mPlayerAbilityResult;

	public MoveResult(double damageDone, String dialogueTxt, int moveIndex, String mpTxt, boolean isPlayer, AbilityResult EnemyAbilityResult, AbilityResult PlayerAbilityResult)
	{
		super(dialogueTxt);

		if(mpTxt == null)
			throw new IllegalArgumentException("mpTxt was null");

		mDamageDone = damageDone;
		mMpTxt = mpTxt;
		mMoveIndex = moveIndex;
		mIsPlayer = isPlayer;
		mEnemyAbilityResult = EnemyAbilityResult;
		mPlayerAbilityResult = PlayerAbilityResult;
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
	
	public AbilityResult getEnemyAbilityResult() {
		return mEnemyAbilityResult;
	}

	public AbilityResult getPlayerAbilityResult() {
		return mPlayerAbilityResult;
	}
}
