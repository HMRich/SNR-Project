package application.enums.stats;

public enum SpecificIVs
{
	Best("Best", 31, 31),
	Fantastic("Fantastic", 30, 30),
	Very_Good("Very Good", 26, 29),
	Pretty_Good("Pretty Good", 16, 25),
	Decent("Decent", 1, 15),
	No_Good("No Good", 0, 0),
	NotSet("Not Set", -1, -1);
	
	private final String mName;
	private final int mLowerBound;
	private final int mUpperBound;
	
	private SpecificIVs(String name, int lowerBound, int upperBound)
	{
		mName = name;
		mLowerBound = lowerBound;
		mUpperBound = upperBound;
	}
	
	public String toString()
	{
		return mName;
	}
	
	public int getLowerBound()
	{
		return mLowerBound;
	}
	
	public int getUpperBound()
	{
		return mUpperBound;
	}
	
	public String getStatement(int IVStatValue)
	{
		SpecificIVs statement = SpecificIVs.NotSet;
		
		if(IVStatValue == 0)
		{
			statement = SpecificIVs.No_Good;
		}
		
		else if(IVStatValue < )
		{
			
		}
	}
}
