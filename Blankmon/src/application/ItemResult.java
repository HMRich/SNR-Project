package application;

public class ItemResult extends Result
{
	private double mHpGained;

	public ItemResult(String dialogue, double hpGained)
	{
		super(dialogue);

		mHpGained = hpGained;
	}

	public double getHpGained()
	{
		return mHpGained;
	}
}
