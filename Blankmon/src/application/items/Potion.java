package application.items;

import application.Anature;
import application.Item;
import application.enums.ItemIds;

public class Potion extends Item{

	public Potion() {
		super(ItemIds.Potion, "Potion"); 
	}

	public void useItem(Anature target)
	{
		System.out.println("Use Item Potion"); // TODO
	}
}
