package application.items;

import java.util.HashMap;

import application.controllers.LoggerController;
import application.enums.ItemIds;
import application.enums.LoggingTypes;

public class ItemPool
{
	private static HashMap<ItemIds, Item> mItems;

	public static Item getItem(ItemIds itemId)
	{
		if(mItems == null)
		{
			generateItems();
		}

		return mItems.get(itemId);
	}

	public static HealthPotion getHealthPotion(ItemIds itemId)
	{
		if(isHealthPotion(itemId))
		{
			return (HealthPotion) mItems.get(itemId);
		}
		return null;
	}

	private static void generateItems()
	{
		mItems = new HashMap<ItemIds, Item>();
		int potionHealAmount = 20;
		mItems.put(ItemIds.Potion, new Potion(potionHealAmount));
		int greatPotionHealAmount = 50;
		mItems.put(ItemIds.Great_Potion, new GreatPotion(greatPotionHealAmount));
		int ultraPotionHealAmount = 200;
		mItems.put(ItemIds.Ultra_Potion, new UltraPotion(ultraPotionHealAmount));
		int masterPotionHealAmount = Integer.MAX_VALUE;
		mItems.put(ItemIds.Master_Potion, new MasterPotion(masterPotionHealAmount));
	}

	private static boolean isHealthPotion(ItemIds itemId)
	{
		switch(itemId)
		{
			case Potion:
				return true;

			case Great_Potion:
				return true;

			case Ultra_Potion:
				return true;

			case Master_Potion:
				return true;

			default:
				return false;
		}
	}

	public static Item getItem(String input)
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
			LoggerController.logEvent(LoggingTypes.Error, "Try to get an item by an invalid String: " + input);
			return null;
		}

		try
		{
			id = ItemIds.valueOf(toCheck);
		}

		catch(Exception e)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Try to get an item by an invalid String: " + input);
			return null;
		}

		return getItem(id);
	}
}
