package application.player;

import java.io.Serializable;
import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.interfaces.IHealthPotion;
import application.items.Anacube;

public class Backpack implements Serializable
{
	private static final long serialVersionUID = -2177964342378481427L;

	private ArrayList<IHealthPotion> mPotionBag;
	private ArrayList<Anacube> mAnacubeBag;

	public Backpack()
	{
		mPotionBag = new ArrayList<IHealthPotion>();
		mAnacubeBag = new ArrayList<Anacube>();
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

	public void addItem(Anacube toAdd)
	{
		if(toAdd == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Null Anacube cannot be added.");
			throw new IllegalArgumentException("Null Anacube cannot be added.");
		}

		mAnacubeBag.add(toAdd);
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

		for(int i = 0; i < mAnacubeBag.size(); i++)
		{
			Anacube iItem = mAnacubeBag.get(i);

			if(iItem.getItemId() == idToRemove)
			{
				mAnacubeBag.remove(i);
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

	public int getAnacubeCount(ItemIds toCount)
	{
		return calculateAnacubeCount(mAnacubeBag, toCount);
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

	private int calculateAnacubeCount(ArrayList<Anacube> bag, ItemIds toCount)
	{
		int count = 0;

		for(Anacube anacube : bag)
		{
			if(anacube.getItemId() == toCount)
			{
				count++;
			}
		}

		return count;
	}
}