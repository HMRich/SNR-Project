package application.items;

import application.enums.ItemIds;
import application.interfaces.IBuilder;

public class HealthPotionBuilder implements IBuilder<HealthPotion>
{
	private HealthPotion mHealthPotion;

	public HealthPotionBuilder()
	{
		generateNewHealthPotion();
	}

	/*
	 * PUBLIC SETS
	 */

	public HealthPotionBuilder setItemId(ItemIds itemId)
	{
		mHealthPotion.setItemId(itemId);
		return this;
	}

	public HealthPotionBuilder setItemName(String itemName)
	{
		mHealthPotion.setItemName(itemName);
		return this;
	}

	public HealthPotionBuilder setHealAmount(int healAmount)
	{
		mHealthPotion.setHealAmount(healAmount);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public HealthPotion create()
	{
		if(buildIsComplete())
		{
			HealthPotion healthPotionToReturn = mHealthPotion;

			generateNewHealthPotion();

			return healthPotionToReturn;
		}

		throw new IllegalStateException("All the builder variables need to have a value before you create a HealthPotion.");
	}

	/*
	 * PRIVATE METHODS
	 */

	private void generateNewHealthPotion()
	{
		mHealthPotion = new HealthPotion();
	}

	private boolean buildIsComplete()
	{
		return mHealthPotion.canCreate();
	}
}
