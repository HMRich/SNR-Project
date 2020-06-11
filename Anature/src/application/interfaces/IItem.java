package application.interfaces;

import java.io.Serializable;

import application.controllers.results.ItemResult;
import application.enums.ItemIds;

public interface IItem extends Serializable
{
	public ItemIds getItemId();

	public String getItemName();

	public ItemResult useItem(IAnature target);
}
