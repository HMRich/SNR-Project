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
		mHealAmount = healAmount;
	}

	/*
	 * PUBLIC METHODS
	 */

	public ItemResult useItem(Anature target)
	{
		double oldHp = target.getCurrentHitPoints();
		String dialogue = target.healAnature(getHealAmount());
		double newHp = target.getCurrentHitPoints();

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
		if(getItemId().equals(ItemIds.Null))
		{
			throw new IllegalStateException("The \"itemId\" variable was never set during construction.");
		}

		if(getItemName().isEmpty())
		{
			throw new IllegalStateException("The \"itemName\" variable was never set during construction.");
		}

		if(getHealAmount() == -1)
		{
			throw new IllegalStateException("The \"healAmount\" variable was never set during construction.");
		}

		return true;
	}

}
