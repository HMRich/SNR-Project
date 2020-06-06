package application.interfaces;

import java.io.Serializable;

import application.enums.AbilityIds;

public interface IAbility extends Serializable
{
	public AbilityIds getAbilityId();

	public String toString();

	public String getAbilityDescription();

	public boolean happensEveryTurn();
}
