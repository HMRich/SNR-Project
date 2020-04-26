package application.items;

import application.anatures.Anature;
import application.controllers.results.ItemResult;
import application.enums.ItemIds;

public abstract class Item
{
	private ItemIds mItemId;
	private String mItemName;

	Item()
	{
		mItemId = ItemIds.Null;
		mItemName = "";
	}

	/*
	 * PACKAGE SETS
	 */

	void setItemId(ItemIds itemId)
	{
		if(itemId == null)
		{
			throw new IllegalArgumentException("Passed value \"itemId\" was null.");
		}

		mItemId = itemId;
	}

	void setItemName(String itemName)
	{
		if(itemName == null)
		{
			throw new IllegalArgumentException("Passed value \"itemName\" was null.");
		}

		if(itemName.trim()
				.isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"itemName\" was anempty string.");
		}

		mItemName = itemName;
	}

	/*
	 * PUBLIC METHODS
	 */

	public ItemIds getItemId()
	{
		return mItemId;
	}

	public String getItemName()
	{
		return mItemName;
	}

	public abstract ItemResult useItem(Anature target);

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		return !mItemId.equals(ItemIds.Null) && !mItemName.isEmpty();
	}
}
