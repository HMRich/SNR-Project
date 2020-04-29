package application.pools;

import java.util.HashMap;

import application.controllers.LoggerController;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.interfaces.IHealthPotion;
import application.interfaces.IItem;
import application.items.HealthPotion;
import application.items.HealthPotionBase;

public class ItemPool
{
	private static HashMap<ItemIds, IItem> mItems;

	/*
	 * PUBLIC METHODS
	 */

	public static IItem getItem(ItemIds itemId)
	{
		return checkPool().get(itemId);
	}

	public static IHealthPotion getHealthPotion(ItemIds itemId)
	{
		if(isHealthPotion(itemId))
		{
			return (HealthPotionBase) checkPool().get(itemId);
		}
		return null;
	}

	public static IItem getItem(String input)
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

	private static HashMap<ItemIds, IItem> checkPool()
	{
		if(mItems == null)
		{
			generateItems();
		}
		return mItems;
	}

	private static void generateItems()
	{
		mItems = new HashMap<ItemIds, IItem>();

		mItems.put(ItemIds.Potion, new HealthPotion().withHealAmount(20)
				.withItemId(ItemIds.Potion)
				.withItemName("Potion")
				.create());

		mItems.put(ItemIds.Great_Potion, new HealthPotion().withHealAmount(50)
				.withItemId(ItemIds.Great_Potion)
				.withItemName("Great Potion")
				.create());

		mItems.put(ItemIds.Ultra_Potion, new HealthPotion().withHealAmount(200)
				.withItemId(ItemIds.Ultra_Potion)
				.withItemName("Ultra Potion")
				.create());

		mItems.put(ItemIds.Master_Potion, new HealthPotion().withHealAmount(Integer.MAX_VALUE)
				.withItemId(ItemIds.Master_Potion)
				.withItemName("Master Potion")
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
