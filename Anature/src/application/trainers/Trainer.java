package application.trainers;

import java.util.ArrayList;

import application.Anature;
import application.BaseAI;
import application.enums.AiChoice;
import application.enums.TrainerIds;
import application.enums.Type;
import application.items.HealthPotion;
import application.items.Item;
import javafx.scene.image.Image;

public class Trainer
{
	private TrainerIds mId;
	private String mName;
	private ArrayList<Anature> mAnatures;
	private ArrayList<HealthPotion> mPotions;
	private Anature mCurrentAnature;
	private BaseAI mAi;
	private int mHealthThreshold;
	private int mSwitchThreshold;

	public Trainer(TrainerIds id, String name, ArrayList<Anature> anatures, ArrayList<HealthPotion> potions, Anature currentAnature, BaseAI ai, int healthThreshold,
			int switchThreshold)
	{
		if(id == null && name == null && anatures == null && potions == null && currentAnature == null && ai == null)
			throw new IllegalArgumentException("Null Parameter");

		mId = id;
		mName = name;
		mAnatures = anatures;
		mPotions = potions;
		mCurrentAnature = currentAnature;
		mAi = ai;
		mHealthThreshold = healthThreshold;
		mSwitchThreshold = switchThreshold;
	}

	/*
	 * PUBLIC METHODS
	 */
	public void switchAnature()
	{
		mAi.switchAnature(mAnatures, new Type[]
		{ mCurrentAnature.getPrimaryType(), mCurrentAnature.getSecondaryType() }, 25, mCurrentAnature);
	};

	public final AiChoice useTurn(Anature playerAnature)
	{
		AiChoice itemResult = mAi.willUseHealthPotion(mPotions, mCurrentAnature, mHealthThreshold);

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
				return mAi.chooseMove(mCurrentAnature.getMoves());
			}

			return switchResult;
		}

		return itemResult;
	}

	public String getName()
	{
		return mName;
	}

	public TrainerIds getId()
	{
		return mId;
	}

	public Image getBattleSprite()
	{
		if(mId == TrainerIds.Wild)
			return null;

		return new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + "/" + mId.toString() + ".png").toExternalForm(),
				1000.0, 1000.0, true, false);
	}

	public ArrayList<Anature> getAnatures()
	{
		return mAnatures;
	}

	public ArrayList<Item> getItmes()
	{
		return mPotions;
	}

	public Anature getCurrentAnature()
	{
		return mCurrentAnature;
	}

	public int getNextAnature(int index)
	{
		index++;
		if(index >= mAnatures.size())
		{
			index = 0;
		}
		return index;
	}

	public boolean canBattle()
	{
		if(mAnatures.size() == 0)
		{
			return false;
		}

		boolean result = false;
		for(Anature anature : mAnatures)
		{
			if(anature.getCurrHp() == 0)
			{
				result = true;
				break;
			}
		}

		return !result;
	}
}
