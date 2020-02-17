package application;


import java.util.HashMap;

import application.enums.ItemIds;
import application.items.*;

public class ItemPool
{
	private static HashMap<ItemIds, Item> mItems;

	public static Item getItems(ItemIds itemId)
	{
		if (mItems == null)
		{
			generateItems();
		}
		return mItems.get(itemId);
	}

	private static void generateItems()
	{
		mItems = new HashMap<ItemIds, Item>();
		mItems.put(ItemIds.Potion, new Potion());
		mItems.put(ItemIds.Great_Potion, new GreatPotion());
		mItems.put(ItemIds.Ultra_Potion, new UltraPotion());
		mItems.put(ItemIds.Master_Potion, new MasterPotion());
	}
}
