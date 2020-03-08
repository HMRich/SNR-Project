package application.items;

import application.enums.ItemIds;

public class GreatPotion extends HealthPotion
{
	public GreatPotion(int healAmount)
	{
		super(ItemIds.Great_Potion, "Great Potion", healAmount);
	}
}
