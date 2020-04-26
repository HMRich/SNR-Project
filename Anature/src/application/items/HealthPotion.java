package application.items;

import application.anatures.Anature;
import application.controllers.results.ItemResult;
import application.enums.ItemIds;

public class HealthPotion extends Item
{
	private int mHealAmount;

	HealthPotion()
	{
		mHealAmount = -1;
	}

	/*
	 * PACKAGE SETS
	 */

	void setHealAmount(int healAmount)
	{
		if(healAmount < 0)
		{
			throw new IllegalArgumentException("Passed value \"healAmount\" was below 0.");
		}
	}

	/*
	 * PUBLIC METHODS
	 */

	public ItemResult useItem(Anature target)
	{
		double oldHp = target.getCurrHp();
		String dialogue = target.healAnature(getHealAmount());
		double newHp = target.getCurrHp();

		return new ItemResult(dialogue, newHp - oldHp);
	}

	public int getHealAmount()
	{
		return mHealAmount;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		return super.canCreate() && mHealAmount != -1;
	}

}
