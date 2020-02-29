package application.trainers;

import java.util.ArrayList;

import application.Anature;
import application.BaseAI;
import application.enums.AiChoice;
import application.enums.TrainerIds;
import application.enums.Type;
import application.items.Item;

public class Trainer
{
	private TrainerIds mId;
	private String mName;
	private ArrayList<Anature> mAnatures;
	private ArrayList<Item> mItems;
	private Anature mCurrentAnature;
	private BaseAI mAi;
	private int mHealthThreshold;
	private int mSwitchThreshold;
	private int tokens = 100;

	public Trainer(TrainerIds id, String name, ArrayList<Anature> anatures, ArrayList<Item> items, Anature currentAnature, BaseAI ai, int healthThreshold,
			int switchThreshold)
	{
		if(id == null && name == null && anatures == null && items == null && currentAnature == null && ai == null)
			throw new IllegalArgumentException("Null Parameter");

		mId = id;
		mName = name;
		mAnatures = anatures;
		mItems = items;
		mCurrentAnature = currentAnature;
		mAi = ai;
		mHealthThreshold = healthThreshold;
		mSwitchThreshold = switchThreshold;
	}

	public void switchAnature()
	{
		mAi.switchAnature(mAnatures, new Type[]
		{ mCurrentAnature.getPrimaryType(), mCurrentAnature.getSecondaryType() }, 25, mCurrentAnature);
	};

	public final AiChoice useTurn(Anature playerAnature)
	{
		AiChoice itemResult = mAi.useItem(mItems, mCurrentAnature, mHealthThreshold);

		if(itemResult.equals(AiChoice.No_Choice))
		{
			Type[] types = null;

			if(playerAnature.getSecondaryType() != null)
				types = new Type[]
				{ playerAnature.getPrimaryType(), playerAnature.getSecondaryType() };

			else
				types = new Type[]
				{ playerAnature.getPrimaryType() };

			AiChoice switchResult = mAi.switchAnature(mAnatures, types, mSwitchThreshold, mCurrentAnature);

			if(switchResult.equals(AiChoice.No_Choice))
			{
				return mAi.chooseMove();
			}

			return switchResult;
		}

		return itemResult;
	}

	public String getName()
	{
		return mName;
	}

	public String getSpritePath()
	{
		return "/resources/images/trainers/" + mId.toString() + ".png";
	}

	public ArrayList<Anature> getAnatures()
	{
		return mAnatures;
	}
	
	public int getTokens() 
	{
		return tokens;
	}
}
