package application.trainers;

import java.io.Serializable;
import java.util.ArrayList;

import application.enums.TrainerIds;
import application.interfaces.AiChoiceObject;
import application.interfaces.IAI;
import application.interfaces.IAnature;
import application.interfaces.IHealthPotion;
import application.interfaces.ITrainer;
import application.trainers.ai.choice_objects.AiHealthPotionChoice;
import application.trainers.ai.choice_objects.AiMoveChoice;
import application.trainers.ai.choice_objects.AiNoChoice;
import application.trainers.ai.choice_objects.AiSwitchChoice;
import javafx.scene.image.Image;

class Trainer implements ITrainer, Serializable
{
	private static final long serialVersionUID = 5701627089126907149L;

	private TrainerIds mId;
	private String mName;
	private int mRewardForDefeat;
	private ArrayList<IAnature> mAnatures;
	private ArrayList<IHealthPotion> mHealthPotions;
	private IAnature mCurrentAnature;
	private IAI mAI;

	Trainer()
	{
		mId = TrainerIds.Null;
		mName = "";
		mRewardForDefeat = -1;
		mAnatures = null;
		mHealthPotions = null;
		mCurrentAnature = null;
		mAI = null;
	}

	/*
	 * PACKAGE SETS
	 */

	void setTrainerId(TrainerIds trainerId)
	{
		if(trainerId == null)
		{
			throw new IllegalArgumentException("Passed value \"trainerId\" was null.");
		}

		mId = trainerId;
	}

	void setTrainerName(String name)
	{
		if(name == null)
		{
			throw new IllegalArgumentException("Passed value \"name\" was null.");
		}

		if(name.trim().isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was an empty string.");
		}

		mName = name;
	}

	void setRewardForDefeat(int rewardAmount)
	{
		if(rewardAmount < 0)
		{
			throw new IllegalArgumentException("Passed value \"rewardAmount\" was below 0.");
		}

		mRewardForDefeat = rewardAmount;
	}

	void setAnatureParty(ArrayList<IAnature> anatureBases)
	{
		// TODO talk with team about if we should allow the trainer anatures variable to
		// be empty
		if(anatureBases == null)
		{
			throw new IllegalArgumentException("Passed value \"anatures\" was null.");
		}

		mAnatures = anatureBases;
	}

	void setHealthPotions(ArrayList<IHealthPotion> healthPotionBases)
	{
		if(healthPotionBases == null)
		{
			throw new IllegalArgumentException("Passed value \"potions\" was null.");
		}

		mHealthPotions = healthPotionBases;
	}

	void setAI(IAI ai)
	{
		if(ai == null)
		{
			throw new IllegalArgumentException("Passed value \"ai\" was null.");
		}

		mAI = ai;
	}

	/*
	 * PUBLIC GETS
	 */

	@Override
	public TrainerIds getId()
	{
		return mId;
	}

	@Override
	public String getName()
	{
		return mName;
	}

	@Override
	public int getRewardForDefeat()
	{
		return mRewardForDefeat;
	}

	@Override
	public ArrayList<IAnature> getAnatureParty()
	{
		return mAnatures;
	}

	@Override
	public ArrayList<IHealthPotion> getHealthPotions()
	{
		return mHealthPotions;
	}

	@Override
	public IAnature getCurrentAnature()
	{
		return mCurrentAnature;
	}

	/*
	 * PRIVATE GETS
	 */

	private IAI getTrainerAI()
	{
		return mAI;
	}

	/*
	 * PUBLIC METHODS
	 */

	@Override
	public Image getBattleSprite()
	{
		if(mId == TrainerIds.Wild)
			return null;

		return new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + "/" + mId.toString() + ".png").toExternalForm(),
				1000.0, 1000.0, true, false);
	}

	// TODO We need to get rid of this method
	@Override
	public int getNextAnature(int index)
	{
		index++;
		if(index >= mAnatures.size())
		{
			index = 0;
		}
		return index;
	}

	// TODO We need to move this method. It most likely does not belong here
	@Override
	public int getAnatureIndex(IAnature anatureBase)
	{
		int index = 0;
		for(IAnature currentAnature : mAnatures)
		{
			if(currentAnature.equals(anatureBase))
			{
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public boolean canBattle()
	{
		if(mAnatures.size() == 0)
		{
			return false;
		}

		boolean result = false;
		for(IAnature anatureBase : mAnatures)
		{
			if(anatureBase.getStats().getCurrentHitPoints() == 0)
			{
				result = true;
				break;
			}
		}

		return !result;
	}

	@Override
	public AiChoiceObject<?> useTurn(IAnature playerAnature)
	{
		boolean willUseHealthPotion = mAI.willUseHealthPotion(mHealthPotions, mCurrentAnature);

		if(willUseHealthPotion)
			return chooseHealthPotion();

		boolean willSwitchAnature = mAI.willSwitchAnature(mAnatures, playerAnature, mCurrentAnature);

		if(willSwitchAnature)
			return chooseAnature(playerAnature);

		AiMoveChoice moveChoice = chooseMove(playerAnature);
		if(moveChoice != null)
			return moveChoice;

		return new AiNoChoice();
	}

	@Override
	public AiSwitchChoice chooseAnature(IAnature playerAnature)
	{
		IAnature anatureToSwitchTo = mAI.chooseNewAnature(mAnatures, mCurrentAnature, playerAnature);
		AiSwitchChoice switchChoice = new AiSwitchChoice(anatureToSwitchTo);
		return switchChoice;
	}

	@Override
	public void setCurrentAnature(IAnature currentAnature)
	{
		if(currentAnature == null)
		{
			throw new IllegalArgumentException("Passed value \"anature\" was null.");
		}

		mCurrentAnature = currentAnature;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canComplete()
	{
		if(mId.equals(TrainerIds.Null))
		{
			throw new IllegalStateException("The \"trainerId\" variable was never set during construction.");
		}

		if(getName().isEmpty())
		{
			throw new IllegalStateException("The \"trainerName\" variable was never set during construction.");
		}

		if(getRewardForDefeat() == -1)
		{
			throw new IllegalStateException("The \"rewardForDefeat\" variable was never set during construction.");
		}

		if(getAnatureParty() == null)
		{
			throw new IllegalStateException("The \"anatureParty\" variable was never set during construction.");
		}

		if(getHealthPotions() == null)
		{
			throw new IllegalStateException("The \"healthPotions\" variable was never set during construction.");
		}

		if(getCurrentAnature() == null)
		{
			throw new IllegalStateException("The \"currentAnature\" variable was never set during construction.");
		}

		if(getTrainerAI() == null)
		{
			throw new IllegalStateException("The \"Ai\" variable was never set during construction.");
		}

		return true;
	}

	/*
	 * PRIVATE METHODS
	 */

	private AiHealthPotionChoice chooseHealthPotion()
	{
		IHealthPotion healthPotionToUse = mAI.healthPotionToUse(mHealthPotions, mCurrentAnature);
		AiHealthPotionChoice healthPotionChoice = new AiHealthPotionChoice(healthPotionToUse);
		return healthPotionChoice;
	}

	private AiMoveChoice chooseMove(IAnature playerAnature)
	{
		AiMoveChoice moveChoice = mAI.chooseMove(mCurrentAnature, playerAnature);
		return moveChoice;
	}
}
