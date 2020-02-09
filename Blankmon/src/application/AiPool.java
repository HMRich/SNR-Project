package application;

import java.util.HashMap;

import application.abillities.Intimidate;
import application.enums.AbilityIds;
import application.enums.AiIds;

public class AiPool
{
	private static HashMap<AiIds, BaseAI> mAis;

	public static BaseAI getAi(AiIds aiId)
	{
		if (mAis == null)
		{
			generateAis();
		}
		
		return mAis.get(aiId);
	}

	private static void generateAis()
	{
		aiId = new HashMap<AiIds, BaseAI>();
		aiId.put(AiIds.Base, new BaseAI());
	}
}
