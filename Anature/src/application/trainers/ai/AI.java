package application.trainers.ai;

import application.enums.AttackEffectiveness;
import application.interfaces.IBuilder;

public class AIBuilder implements IBuilder<AI>
{
	private AI mAI;

	public AIBuilder()
	{
		generateNewAI();
	}

	/*
	 * PUBLIC SETS
	 */

	public AIBuilder setHealthThreshold(double healthThreshold)
	{
		mAI.setHealthThreshold(healthThreshold);
		return this;
	}

	public AIBuilder setSwitchThreshold(AttackEffectiveness switchThreshold)
	{
		mAI.setSwitchThreshold(switchThreshold);
		return this;
	}

	public AIBuilder setMoveThreshold(AttackEffectiveness moveThreshold)
	{
		mAI.setMoveThreshold(moveThreshold);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public AI create()
	{
		if(!buildIsComplete())
		{
			throw new IllegalStateException("All the builder variables need to have a value before you create a AI.");
		}

		AI aiToReturn = mAI;

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
