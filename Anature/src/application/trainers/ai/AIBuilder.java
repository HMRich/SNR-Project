package application.trainers.ai;

import application.enums.TypeEffectiveness;
import application.interfaces.IAI;
import application.interfaces.IBuilder;

public class AIBuilder implements IBuilder<IAI>
{
	private AI mAI;

	public AIBuilder()
	{
		generateNewAI();
	}

	/*
	 * PUBLIC SETS
	 */

	public AIBuilder withHealthThreshold(double healthThreshold)
	{
		mAI.setHealthThreshold(healthThreshold);
		return this;
	}

	public AIBuilder withSwitchThreshold(TypeEffectiveness switchThreshold)
	{
		mAI.setSwitchThreshold(switchThreshold);
		return this;
	}

	public AIBuilder withMoveThreshold(TypeEffectiveness moveThreshold)
	{
		mAI.setMoveThreshold(moveThreshold);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public IAI create()
	{
		if(!buildIsComplete())
		{
			throw new IllegalStateException("All the builder variables need to have a value before you create a AI.");
		}

		IAI aiToReturn = mAI;

		generateNewAI();

		return aiToReturn;
	}

	/*
	 * PRIVATE METHODS
	 */

	private void generateNewAI()
	{
		mAI = new AI();
	}

	private boolean buildIsComplete()
	{
		return mAI.canCreate();
	}
}
