package application.items;

import application.Anature;
import application.ItemResult;
import application.enums.ItemIds;

public abstract class HealthPotion extends Item
{
	private int mHealAmount;
	
	public HealthPotion(ItemIds itemId, String itemName, int healAmount)
	{
		super(itemId, itemName);
		mHealAmount = healAmount;
	}
	
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

}
