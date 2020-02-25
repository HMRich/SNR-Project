package application.abillities;

import application.Anature;
import application.enums.AbilityIds;

public class Grumble implements Ability
{
	public static void activateAbility(Anature targetAnature, int turnNumber)
	{
		if(turnNumber == 1)
			targetAnature.setTempAttack((int) - (targetAnature.getAttack() * 0.1));
	}

	@Override
	public AbilityIds getAbilityId()
	{
		return AbilityIds.Grumble;
	}

	@Override
	public String getAbilityName()
	{
		return "Grumble";
	}

	@Override
	public String getAbilityDescription()
	{
		return "";
	}

	@Override
	public boolean happensEveryTurn()
	{
		return false;
	}
}
