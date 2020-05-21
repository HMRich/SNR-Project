package application.pools;

import java.util.HashMap;

import application.enums.AiIds;
import application.enums.TypeEffectiveness;
import application.interfaces.IAI;
import application.trainers.ai.AIBuilder;

public class AiPool
{
	private static HashMap<AiIds, IAI> mAis;

	/*
	 * PUBLIC METHODS
	 */

	public static IAI getAi(AiIds aiId)
	{
		return checkPool().get(aiId);
	}

	/*
	 * PRIVATE METHODS
	 */

	private static HashMap<AiIds, IAI> checkPool()
	{
		if(mAis == null)
		{
			generateAis();
		}
		return mAis;
	}

	private static void generateAis()
	{
		mAis = new HashMap<AiIds, IAI>(); 

		mAis.put(AiIds.Balanced, new AIBuilder().withHealthThreshold(.35)
				.withMoveThreshold(TypeEffectiveness.Not_Effective)
				.withSwitchThreshold(TypeEffectiveness.Not_Effective)
				.create());

		mAis.put(AiIds.Dumb, new AIBuilder().withHealthThreshold(.05)
				.withMoveThreshold(TypeEffectiveness.No_Effect)
				.withSwitchThreshold(TypeEffectiveness.No_Effect)
				.create());

		mAis.put(AiIds.Smart, new AIBuilder().withHealthThreshold(0.25)
				.withMoveThreshold(TypeEffectiveness.Extremely_Effective)
				.withSwitchThreshold(TypeEffectiveness.Extremely_Effective)
				.create());
	}
}
