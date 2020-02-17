package application;

import application.enums.ItemIds;

public abstract class Item {
	private ItemIds mItemId;
	private String mItemName;
	
	

	public Item(ItemIds mItemId, String mItemName) {
		mItemId = this.mItemId;
		mItemName = this.mItemName;
	}

	public String getItemName() {
		return mItemName;
	}


	public ItemIds getItemId() {
		return mItemId;
	}


	public abstract void useItem(Anature target);
	
}
