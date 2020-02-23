package application.items;

import application.Anature;
import application.ItemResult;
import application.enums.ItemIds;

public class UltraPotion extends Item
{
	private int mHealthPoints = 200;

	public UltraPotion()
	{
		super(ItemIds.Ultra_Potion, "Ultra Potion");
	}

	public ItemResult useItem(Anature target)
	{
		double oldHp = target.getCurrHp();
		String dialogue = target.healAnature(mHealthPoints);
		double newHp = target.getCurrHp();
		
		return new ItemResult(dialogue, newHp - oldHp);
	}
}
