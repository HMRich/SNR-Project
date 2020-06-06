package application.items;

import java.io.Serializable;

import application.controllers.results.ItemResult;
import application.enums.ItemIds;
import application.interfaces.IAnature;
import application.interfaces.IHealthPotion;

public class HealthPotionBase extends ItemBase implements IHealthPotion, Serializable
{
	private static final long serialVersionUID = 287737002500225848L;

	private int mHealAmount;

	HealthPotionBase()
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

	@Override
	public ItemResult useItem(IAnature target)
	{
		double oldHp = target.getStats().getCurrentHitPoints();
		String dialogue = target.healAnature(getHealAmount());
		double newHp = target.getStats().getCurrentHitPoints();

		return new ItemResult(dialogue, newHp - oldHp);
	}

	@Override
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
