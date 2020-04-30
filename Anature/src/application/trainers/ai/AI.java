package application.trainers.ai;

import application.enums.AttackEffectiveness;
import application.interfaces.IAI;
import application.interfaces.IBuilder;

public class AI implements IBuilder<IAI>
{
	private AIBase mAI;

	public AI()
	{
		generateNewAI();
	}

	/*
	 * PUBLIC SETS
	 */

	public AI withHealthThreshold(double healthThreshold)
	{
		mAI.setHealthThreshold(healthThreshold);
		return this;
	}

	public AI withSwitchThreshold(AttackEffectiveness switchThreshold)
	{
		mAI.setSwitchThreshold(switchThreshold);
		return this;
	}

	public AI withMoveThreshold(AttackEffectiveness moveThreshold)
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
		mAI = new AIBase();
	}

	private boolean buildIsComplete()
	{
		return mAI.canCreate();
	}
}
