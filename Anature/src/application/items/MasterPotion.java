package application.items;

import application.enums.ItemIds;

public class MasterPotion extends HealthPotion
{
	public MasterPotion(int healAmount)
	{
		super(ItemIds.Master_Potion, "Master Potion", healAmount);
	}
}