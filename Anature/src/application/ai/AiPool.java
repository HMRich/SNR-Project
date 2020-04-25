package application.ai;

import java.util.HashMap;

import application.enums.AiIds;
import application.enums.AttackEffectiveness;

public class AiPool
{
	private static HashMap<AiIds, AI> mAis;

	public static AI getAi(AiIds aiId)
	{
		if(mAis == null)
		{
			generateAis();
		}

		return mAis.get(aiId);
	}

	private static void generateAis()
	{
		mAis = new HashMap<AiIds, AI>();

		mAis.put(AiIds.Base, new AI().setHealthThreshold(.2)
				.setMoveThreshold(AttackEffectiveness.NotEffective)
				.setSwitchThreshold(AttackEffectiveness.NotEffective));
	}
}
