package application.pools;

import java.util.HashMap;

import application.controllers.LoggerController;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.items.HealthPotion;
import application.items.HealthPotionBuilder;
import application.items.Item;

public class ItemPool
{
	private static HashMap<ItemIds, Item> mItems;

	/*
	 * PUBLIC METHODS
	 */

	public static Item getItem(ItemIds itemId)
	{
		return checkPool().get(itemId);
	}

	public static HealthPotion getHealthPotion(ItemIds itemId)
	{
		if(isHealthPotion(itemId))
		{
			return (HealthPotion) checkPool().get(itemId);
		}
		return null;
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

	/*
	 * PRIVATE METHODS
	 */

	private static HashMap<ItemIds, Item> checkPool()
	{
		if(mItems == null)
		{
			generateItems();
		}
		return mItems;
	}

	private static void generateItems()
	{
		mItems = new HashMap<ItemIds, Item>();

		mItems.put(ItemIds.Potion, new HealthPotionBuilder().setHealAmount(20)
				.setItemId(ItemIds.Potion)
				.setItemName("Potion")
				.create());

		mItems.put(ItemIds.Great_Potion, new HealthPotionBuilder().setHealAmount(50)
				.setItemId(ItemIds.Great_Potion)
				.setItemName("Great Potion")
				.create());

		mItems.put(ItemIds.Ultra_Potion, new HealthPotionBuilder().setHealAmount(200)
				.setItemId(ItemIds.Ultra_Potion)
				.setItemName("Ultra Potion")
				.create());

		mItems.put(ItemIds.Master_Potion, new HealthPotionBuilder().setHealAmount(Integer.MAX_VALUE)
				.setItemId(ItemIds.Master_Potion)
				.setItemName("Master Potion")
				.create());
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
}
