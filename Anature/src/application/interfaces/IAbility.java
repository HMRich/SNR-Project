package application.interfaces;

import application.enums.AbilityIds;

public interface IAbility
{
	public AbilityIds getAbilityId();

	public String toString();

	public String getAbilityDescription();

	public boolean happensEveryTurn();
}
