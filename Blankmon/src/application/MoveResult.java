package application;

public class MoveResult
{
	private double mDamageDone;
	private String mDialogueTxt;
	
	public MoveResult(double damageDone, String dialogueTxt)
	{
		if(dialogueTxt == null)
			throw new IllegalArgumentException("dialogueTxt was null");
		
		mDamageDone = damageDone;
		mDialogueTxt = dialogueTxt;
	}

	public double getDamageDone()
	{
		return mDamageDone;
	}

	public String getDialogueTxt()
	{
		return mDialogueTxt;
	}
	
}
