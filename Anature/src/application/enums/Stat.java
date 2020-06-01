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
	
	private final String name;
	
	private Stat(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}
}
