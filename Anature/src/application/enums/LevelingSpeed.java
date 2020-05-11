package application.enums;

public enum LevelingSpeed
{
	Erratic("Erratic", 0.6),
	Fast("Fast", 0.8),
	MediumFast("Medium Fast", 0.95),
	Normal("Normal", 1.0),
	MediumSlow("Medium Slow", 1.059),
	Slow("Slow", 1.25),
	Fluctuating("Fluctuating", 1.64),
	NotSet("Not Set", 0.0);
	
	private final String mName;
	private final double mSpeedModifier;
	
	private LevelingSpeed(String name, double speedModifier)
	{
		mName = name;
		mSpeedModifier = speedModifier;
	}
	
	public String toString()
	{
		return mName;
	}
	
	public double getModifier()
	{
		return mSpeedModifier;
	}
}
