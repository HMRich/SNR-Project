package application.enums;

public enum Stat
{
	HitPoints("Hit Points"),
	Attack("Attack"),
	Defense("Defense"),
	SpecialAttack("Special Attack"),
	SpecialDefense("Special Defense"),
	Speed("Speed"),
	Accuracy("Accuracy"),
	Evasion("Evasion");
	
	private final String mName;
	
	private Stat(String name)
	{
		mName = name;
	}
	
	public String toString()
	{
		return mName;
	}
}
