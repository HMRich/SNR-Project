package application.pools;

import java.util.HashMap;

import application.enums.AiIds;
import application.enums.AttackEffectiveness;
import application.trainers.ai.AI;
import application.trainers.ai.AIBuilder;

public class AiPool
{
	private static HashMap<AiIds, AI> mAis;

	/*
	 * PUBLIC METHODS
	 */

	public static AI getAi(AiIds aiId)
	{
		return checkPool().get(aiId);
	}

	/*
	 * PRIVATE METHODS
	 */

	private static HashMap<AiIds, AI> checkPool()
	{
		if(mAis == null)
		{
			generateAis();
		}
		return mAis;
	}

	private static void generateAis()
	{
		mAis = new HashMap<AiIds, AI>();

		mAis.put(AiIds.Balanced, new AIBuilder().setHealthThreshold(.35)
				.setMoveThreshold(AttackEffectiveness.NotEffective)
				.setSwitchThreshold(AttackEffectiveness.NotEffective)
				.create());

		mAis.put(AiIds.Dumb, new AIBuilder().setHealthThreshold(.05)
				.setMoveThreshold(AttackEffectiveness.NoEffect)
				.setSwitchThreshold(AttackEffectiveness.NoEffect)
				.create());

		mAis.put(AiIds.Smart, new AIBuilder().setHealthThreshold(0.25)
				.setMoveThreshold(AttackEffectiveness.SuperEffective)
				.setSwitchThreshold(AttackEffectiveness.SuperEffective)
				.create());
	}
}
