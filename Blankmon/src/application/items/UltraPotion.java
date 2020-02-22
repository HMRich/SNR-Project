package application.items;

import application.Anature;
import application.Item;
import application.enums.ItemIds;

public class UltraPotion extends Item{
	private int mHealthPoints = 200; 

	public UltraPotion() {
		super(ItemIds.Ultra_Potion, "Ultra Potion"); 
	}

	public void useItem(Anature target)
	{
		target.healAnature(mHealthPoints);
	}

}
