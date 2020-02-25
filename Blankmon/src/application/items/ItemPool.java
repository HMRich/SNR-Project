package application.items;

import java.util.HashMap;

import application.controllers.LoggerController;
import application.enums.ItemIds;
import application.enums.LoggingTypes;

public class ItemPool
{
	private static HashMap<ItemIds, Item> mItems;

	public static Item getItems(ItemIds itemId)
	{
		if(mItems == null)
		{
			generateItems();
		}

		return mItems.get(itemId);
	}

	public static Item getItems(String input)
	{
		String toCheck = input;
		ItemIds id = null;

		if(input.contains(" "))
		{
			String[] parts = input.split(" ");

			if(parts.length == 3)
			{
				toCheck = parts[0] + "_" + parts[1];
			}

			else
			{
				toCheck = parts[0];
			}

			toCheck = toCheck.substring(0, toCheck.length() - 1);
		}

		else
		{
			LoggerController.logEvent(LoggingTypes.Default, "Try to get an item by an invalid String: " + input);
			return null;
		}

		try
		{
			id = ItemIds.valueOf(toCheck);
		}

		catch(Exception e)
		{
			LoggerController.logEvent(LoggingTypes.Default, "Try to get an item by an invalid String: " + input);
			return null;
		}

		return getItems(id);
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
