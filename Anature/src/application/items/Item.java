package application.items;

import application.Anature;
import application.ItemResult;
import application.enums.items.ItemIds;

public abstract class Item
{
	private ItemIds mItemId;
	private String mItemName;

	public Item(ItemIds itemId, String itemName)
	{
		mItemId = itemId;
		mItemName = itemName;
	}

	public String getItemName()
	{
		return mItemName;
	}

	public ItemIds getItemId()
	{
		return mItemId;
	}

	public abstract ItemResult useItem(Anature target);
}
