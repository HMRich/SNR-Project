package application.enums;

public enum TypeEffectiveness
{
	No_Effect("No Effect", 0),
	Seriously_Not_Effective("Seriously Not Effective", 0.25),
	Not_Effective("Not Effective", 0.5),
	Normal("Noraml", 1),
	Effective("Effective", 2),
	Super_Effective("Super Effective", 4);
	
	private final String mName;
	private final double mEffectiveness;
	
	private TypeEffectiveness(String name, double effectiveness)
	{
		mName = name;
		mEffectiveness = effectiveness;
	}
	
	public String toString()
	{
		return mName;
	}
	
	public double getEffectivenes()
	{
		return mEffectiveness;
	}
}
