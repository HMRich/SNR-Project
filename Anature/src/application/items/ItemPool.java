package application.items;

import java.util.HashMap;

import application.enums.items.ItemIds;

public class ItemPool
{
//	private static HashMap<ItemIds, Item> mItems;
	private static HashMap<ItemIds, HealthPotion> mHealthPotions;

//	public static Item getItem(ItemIds itemId)
//	{
//		if(mItems == null)
//		{
//			generateItems();
//		}
//
//		return mItems.get(itemId);
//	}
	
	public static HealthPotion getHealthPotion(ItemIds itemId)
	{
		if(mHealthPotions == null)
		{
			generateHealthPotions();
		}
		
		return mHealthPotions.get(itemId);
	}
	
//	private static void generateItems()
//	{
//		mItems = new HashMap<ItemIds, Item>();
//	}

	private static void generateHealthPotions()
	{
		mHealthPotions = new HashMap<ItemIds, HealthPotion>();
		int potionHealAmount = 20;
		mHealthPotions.put(ItemIds.Potion, new Potion(potionHealAmount));
		int greatPotionHealAmount = 50;
		mHealthPotions.put(ItemIds.Great_Potion, new GreatPotion(greatPotionHealAmount));
		int ultraPotionHealAmount = 200;
		mHealthPotions.put(ItemIds.Ultra_Potion, new UltraPotion(ultraPotionHealAmount));
		int masterPotionHealAmount = Integer.MAX_VALUE;
		mHealthPotions.put(ItemIds.Master_Potion, new MasterPotion(masterPotionHealAmount));
	}
	
	private boolean isHealthPotion(ItemIds itemId)
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

//	public static Item getItems(String input)
//	{
//		String toCheck = input;
//		ItemIds id = null;
//
//		if(input.contains(" "))
//		{
//			String[] parts = input.split(" ");
//
//			if(parts.length == 3)
//			{
//				toCheck = parts[0] + "_" + parts[1];
//			}
//
//			else
//			{
//				toCheck = parts[0];
//			}
//
//			toCheck = toCheck.substring(0, toCheck.length() - 1);
//		}
//
//		else
//		{
//			LoggerController.logEvent(LoggingTypes.Error, "Try to get an item by an invalid String: " + input);
//			return null;
//		}
//
//		try
//		{
//			id = ItemIds.valueOf(toCheck);
//		}
//
//		catch(Exception e)
//		{
//			LoggerController.logEvent(LoggingTypes.Error, "Try to get an item by an invalid String: " + input);
//			return null;
//		}
//
//		return getItems(id);
//	}
}
