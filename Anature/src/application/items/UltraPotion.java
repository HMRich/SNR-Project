package application.items;

import application.enums.ItemIds;

public class UltraPotion extends HealthPotion
{
	public UltraPotion(int healAmount)
	{
		super(ItemIds.Ultra_Potion, "Ultra Potion", healAmount);
	}
}
