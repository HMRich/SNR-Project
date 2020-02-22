package application;

import application.enums.AbilityIds;

public interface Ability
{
	public AbilityIds getAbilityId();
	public String getAbilityName();
	public boolean happensEveryTurn();
}