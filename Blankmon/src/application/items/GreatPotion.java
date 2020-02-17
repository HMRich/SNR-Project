package application.items;

import application.Anature;
import application.Item;
import application.enums.ItemIds;

public class GreatPotion extends Item{

	public GreatPotion() {
		super(ItemIds.Great_Potion, "Great Potion"); 
	}

	public void useItem(Anature target)
	{
		System.out.println("Use Item Great Potion"); // TODO
	}
}
