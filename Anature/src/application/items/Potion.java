package application.items;

import application.enums.items.ItemIds;

public class Potion extends HealthPotion
{
	public Potion(int healAmount)
	{
		super(ItemIds.Potion, "Potion", healAmount);
	}
}
