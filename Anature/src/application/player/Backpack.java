package application.player;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.interfaces.IHealthPotion;

public class Backpack
{
	private ArrayList<IHealthPotion> mPotionBag;

	public Backpack()
	{
		mPotionBag = new ArrayList<IHealthPotion>();
	}

	public void addItem(IHealthPotion toAdd)
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
			IHealthPotion iItem = mPotionBag.get(i);

			if(iItem.getItemId() == idToRemove)
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

	private int calculateCount(ArrayList<IHealthPotion> bag, ItemIds toCount)
	{
		int count = 0;

		for(IHealthPotion iItem : bag)
		{
			if(iItem.getItemId() == toCount)
				count++;
		}

		return count;
	}
}