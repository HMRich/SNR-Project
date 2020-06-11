package application.enums;

public enum TempStatsStages
{
	minusSix("25%", 0.25),
	minusFive("29%", 0.29),
	minusFour("33%", 0.33),
	minusThree("40%", 0.4),
	minusTwo("50%", 0.5),
	minusOne("66%", 0.66),
	zero("0%", 1),
	plusOne("150%", 1.5),
	plusTwo("200%", 2.0),
	plusThree("250%", 2.5),
	plusFour("300%", 3.0),
	plusFive("350%", 3.5),
	plusSix("400%", 4.0);
	
	private final String mStringValue;
	private final double mModifier;
	
	private TempStatsStages(String stringValue, double modifier)
	{
		mStringValue = stringValue;
		mModifier = modifier;
	}
	
	public String toString()
	{
		return mStringValue;
	}
	
	public double getModifier()
	{
		return mModifier;
	}
	
	public TempStatsStages decrementStage()
	{
		switch(this)
		{
			case minusSix:
				return TempStatsStages.minusSix;
				
			case minusFive:
				return TempStatsStages.minusSix;
				
			case minusFour:
				return TempStatsStages.minusFive;
				
			case minusThree:
				return TempStatsStages.minusFour;
				
			case minusTwo:
				return TempStatsStages.minusThree;
				
			case minusOne:
				return TempStatsStages.minusTwo;
				
			case zero:
				return TempStatsStages.minusOne;
				
			case plusOne:
				return TempStatsStages.zero;
				
			case plusTwo:
				return TempStatsStages.plusOne;
				
			case plusThree:
				return TempStatsStages.plusTwo;
				
			case plusFour:
				return TempStatsStages.plusThree;
				
			case plusFive:
				return TempStatsStages.plusFour;
				
			case plusSix:
				return TempStatsStages.plusFive;
		}
		
		return this;
	}
	
	public TempStatsStages incrementStage()
	{
		switch(this)
		{
			case minusSix:
				return TempStatsStages.minusFive;
				
			case minusFive:
				return TempStatsStages.minusFour;
				
			case minusFour:
				return TempStatsStages.minusThree;
				
			case minusThree:
				return TempStatsStages.minusTwo;
				
			case minusTwo:
				return TempStatsStages.minusOne;
				
			case minusOne:
				return TempStatsStages.zero;
				
			case zero:
				return TempStatsStages.plusOne;
				
			case plusOne:
				return TempStatsStages.plusTwo;
				
			case plusTwo:
				return TempStatsStages.plusThree;
				
			case plusThree:
				return TempStatsStages.plusFour;
				
			case plusFour:
				return TempStatsStages.plusFive;
				
			case plusFive:
				return TempStatsStages.plusSix;
				
			case plusSix:
				return TempStatsStages.plusSix;
		}
		
		return this;
	}
}
