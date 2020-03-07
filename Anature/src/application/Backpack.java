package application;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.items.ItemIds;
import application.items.Item;

public class Backpack
{
	private ArrayList<Item> mPotionBag;

	public Backpack()
	{
		mPotionBag = new ArrayList<Item>();
	}

	public void addItem(Item toAdd)
	{
		if(toAdd == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Null Item cannot be added.");
			throw new IllegalArgumentException("Null Item cannot be added.");
		}

		mPotionBag.add(toAdd);
	}

	public boolean removeItem(ItemIds idToRemove)
	{
		for(int i = 0; i < mPotionBag.size(); i++)
		{
			Item item = mPotionBag.get(i);

			if(item.getItemId() == idToRemove)
			{
				mPotionBag.remove(i);
				return true;
			}
		}

		return false;
	}

	public int getTotalPotionCount()
	{
		return mPotionBag.size();
	}

	public int getPotionCount()
	{
		return calculateCount(mPotionBag, ItemIds.Potion);
	}

	public int getGreatPotionCount()
	{
		return calculateCount(mPotionBag, ItemIds.Great_Potion);
	}

	public int getUltraPotionCount()
	{
		return calculateCount(mPotionBag, ItemIds.Ultra_Potion);
	}

	public int getMasterPotionCount()
	{
		return calculateCount(mPotionBag, ItemIds.Master_Potion);
	}

	private int calculateCount(ArrayList<Item> bag, ItemIds toCount)
	{
		int count = 0;

		for(Item item : bag)
		{
			if(item.getItemId() == toCount)
				count++;
		}

		return count;
	}
}