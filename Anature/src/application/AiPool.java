package application;

import java.util.HashMap;

import application.enums.AiIds;

public class AiPool
{
	private static HashMap<AiIds, BaseAI> mAis;

	public static BaseAI getAi(AiIds aiId)
	{
		if(mAis == null)
		{
			generateAis();
		}

		return mAis.get(aiId);
	}

	private static void generateAis()
	{
		mAis = new HashMap<AiIds, BaseAI>();
		mAis.put(AiIds.Base, new BaseAI());
	}
}
