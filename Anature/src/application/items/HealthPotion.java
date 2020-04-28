package application.items;

import application.enums.ItemIds;
import application.interfaces.IBuilder;

public class HealthPotion implements IBuilder<HealthPotionBase>
{
	private HealthPotionBase mHealthPotion;

	public HealthPotion()
	{
		generateNewHealthPotion();
	}

	/*
	 * PUBLIC SETS
	 */

	public HealthPotion withItemId(ItemIds itemId)
	{
		mHealthPotion.setItemId(itemId);
		return this;
	}

	public HealthPotion withItemName(String itemName)
	{
		mHealthPotion.setItemName(itemName);
		return this;
	}

	public HealthPotion withHealAmount(int healAmount)
	{
		mHealthPotion.setHealAmount(healAmount);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public HealthPotionBase create()
	{
		if(buildIsComplete())
		{
			HealthPotionBase healthPotionToReturn = mHealthPotion;

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
		mHealthPotion = new HealthPotionBase();
	}

	private boolean buildIsComplete()
	{
		return mHealthPotion.canCreate();
	}
}
