package application.items;

import application.enums.items.ItemIds;

public class UltraPotion extends HealthPotion
{
	public UltraPotion(int healAmount)
	{
		super(ItemIds.Ultra_Potion, "Ultra Potion", healAmount);
	}
}
