package application.items;

import java.io.Serializable;

import application.enums.ItemIds;
import application.interfaces.IItem;

public abstract class ItemBase implements IItem, Serializable
{
	private static final long serialVersionUID = -8921793567635067569L;

	private ItemIds mItemId;
	private String mItemName;

	ItemBase()
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

		if(itemName.trim().isEmpty())
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
}
