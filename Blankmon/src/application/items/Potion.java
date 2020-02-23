package application.items;

import application.Anature;
import application.ItemResult;
import application.enums.ItemIds;

public class Potion extends Item
{
	private int mHealthPoints = 20;

	public Potion()
	{
		super(ItemIds.Potion, "Potion");
	}

	public ItemResult useItem(Anature target)
	{
		double oldHp = target.getCurrHp();
		String dialogue = target.healAnature(mHealthPoints);
		double newHp = target.getCurrHp();
		
		return new ItemResult(dialogue, newHp - oldHp);
	}
}
