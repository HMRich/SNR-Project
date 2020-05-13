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
		SpecificIVs statement = NotSet;

		if(IVStatValue <= No_Good.getUpperBound())
		{
			statement = No_Good;
		}

		else if(IVStatValue <= Decent.getUpperBound())
		{
			statement = Decent;
		}

		else if(IVStatValue <= Pretty_Good.getUpperBound())
		{
			statement = Pretty_Good;
		}

		else if(IVStatValue <= Very_Good.getUpperBound())
		{
			statement = Very_Good;
		}

		else if(IVStatValue <= Fantastic.getUpperBound())
		{
			statement = Fantastic;
		}

		else if(IVStatValue == Best.getUpperBound())
		{
			statement = Best;
		}

		if(statement.equals(NotSet))
		{
			throw new IllegalArgumentException("Passed argument \"\" was not within range " + No_Good.getLowerBound() + " - " + Best.getUpperBound() + ".");
		}

		return statement.toString();
	}
}
