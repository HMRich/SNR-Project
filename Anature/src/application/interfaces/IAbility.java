package application.interfaces;

import application.enums.AbilityIds;

public interface IAbility
{
	public AbilityIds getAbilityId();
	public String getAbilityName();
	public String getAbilityDescription();
	public boolean happensEveryTurn();
}
