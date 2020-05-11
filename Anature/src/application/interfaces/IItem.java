package application.interfaces;

import application.controllers.results.ItemResult;
import application.enums.ItemIds;

public interface IItem
{
	public ItemIds getItemId();

	public String getItemName();

	public ItemResult useItem(IAnature target);
}
