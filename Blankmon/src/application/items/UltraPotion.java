package application.items;

import application.Anature;
import application.Item;
import application.enums.ItemIds;

public class UltraPotion extends Item{

	public UltraPotion() {
		super(ItemIds.Ultra_Potion, "Ultra Potion"); 
	}

	public void useItem(Anature target)
	{
		System.out.println("Use Item Ultra Potion"); // TODO
	}

}
