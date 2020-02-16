package application;

import application.enums.AbilityIds;

public abstract class Ability
{
	private AbilityIds mAbilityId;
	private String mAbilityName;
	private boolean mHappensEveryTurn;

	public Ability(AbilityIds abilityId, String abilityName, boolean happensEveryTurn)
	{
		mAbilityId = abilityId;
		mAbilityName = abilityName;
		mHappensEveryTurn = happensEveryTurn;
	}

	public AbilityIds getAbilityId()
	{
		return mAbilityId;
	}

	public String getAbilityName()
	{
		return mAbilityName;
	}

	public boolean happensEveryTurn()
	{
		return mHappensEveryTurn;
	}

	public abstract void activateAbility(Anature user, Anature target);

}