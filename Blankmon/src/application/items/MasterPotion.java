package application.items;

import application.Anature;
import application.ItemResult;
import application.enums.ItemIds;

public class MasterPotion extends Item
{
	public MasterPotion()
	{
		super(ItemIds.Master_Potion, "Master Potion");
	}

	public ItemResult useItem(Anature target)
	{
		double oldHp = target.getCurrHp();
		String dialogue = target.healAnature(999999999);
		double newHp = target.getCurrHp();
		
		return new ItemResult(dialogue, newHp - oldHp);
	}
}
