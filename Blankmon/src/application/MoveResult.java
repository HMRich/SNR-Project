package application;

public class MoveResult
{
	private double mDamageDone;
	private String mDialogueTxt, mMpTxt;
	private int mMoveIndex;
	private boolean mIsPlayer;
	
	public MoveResult(double damageDone, String dialogueTxt, int moveIndex, String mpTxt, boolean isPlayer)
	{
		if(dialogueTxt == null)
			throw new IllegalArgumentException("dialogueTxt was null");
		
		else if(mpTxt == null)
			throw new IllegalArgumentException("mpTxt was null");
		
		mDamageDone = damageDone;
		mDialogueTxt = dialogueTxt;
		mMpTxt = mpTxt;
		mMoveIndex = moveIndex;
		mIsPlayer = isPlayer;
	}

	public double getDamageDone()
	{
		return mDamageDone;
	}

	public String getDialogueTxt()
	{
		return mDialogueTxt;
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
