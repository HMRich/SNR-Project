package application.items;

import application.Anature;
import application.ItemResult;
import application.enums.ItemIds;

public class GreatPotion extends Item
{
	private int mHealthPoints = 50;

	public GreatPotion()
	{
		super(ItemIds.Great_Potion, "Great Potion");
	}

	public ItemResult useItem(Anature target)
	{
		double oldHp = target.getCurrHp();
		String dialogue = target.healAnature(mHealthPoints);
		double newHp = target.getCurrHp();
		
		return new ItemResult(dialogue, newHp - oldHp);
	}
}
