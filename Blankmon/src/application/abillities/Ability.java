package application.abillities;

import application.enums.AbilityIds;

public interface Ability
{
	public AbilityIds getAbilityId();
	public String getAbilityName();
	public String getAbilityDescription();
	public boolean happensEveryTurn();
}
