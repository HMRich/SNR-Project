package application.trainers;

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

class Trainer implements ITrainer
{
	private TrainerIds mId;
	private String mName;
	private ArrayList<IAnature> mAnatures;
	private ArrayList<IHealthPotion> mHealthPotions;
	private IAnature mCurrentAnature;
	private IAI mAI;

	Trainer()
	{
		mId = TrainerIds.Null;
		mName = "";
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

		if(name.trim()
				.isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was an empty string.");
		}
		mName = name;
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

	void setCurrentAnature(IAnature currentAnature)
	{
		if(currentAnature == null)
		{
			throw new IllegalArgumentException("Passed value \"anature\" was null.");
		}
		mCurrentAnature = currentAnature;
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

	public TrainerIds getId()
	{
		return mId;
	}

	public String getName()
	{
		return mName;
	}

	public ArrayList<IAnature> getAnatureParty()
	{
		return mAnatures;
	}

	public ArrayList<IHealthPotion> getHealthPotions()
	{
		return mHealthPotions;
	}

	public IAnature getCurrentAnature()
	{
		return mCurrentAnature;
	}

	/*
	 * PUBLIC METHODS
	 */

	public Image getBattleSprite()
	{
		if(mId == TrainerIds.Wild)
			return null;

		return new Image(getClass().getResource("/resources/images/trainers/" + mId.toString()
				.toLowerCase() + "/" + mId.toString() + ".png")
				.toExternalForm(), 1000.0, 1000.0, true, false);
	}

	// TODO We need to get rid of this method
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

	public boolean canBattle()
	{
		if(mAnatures.size() == 0)
		{
			return false;
		}

		boolean result = false;
		for(IAnature anatureBase : mAnatures)
		{
			if(anatureBase.getCurrentHitPoints() == 0)
			{
				result = true;
				break;
			}
		}

		return !result;
	}

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

	/*
	 * PACKAGE METHODS
	 */

	boolean canComplete()
	{
		return mId != TrainerIds.Null && !mName.isEmpty() && mAnatures != null && mHealthPotions != null && mCurrentAnature != null && mAI != null;
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

	private AiSwitchChoice chooseAnature(IAnature playerAnature)
	{
		IAnature anatureToSwitchTo = mAI.chooseNewAnature(mAnatures, mCurrentAnature, playerAnature);
		AiSwitchChoice switchChoice = new AiSwitchChoice(anatureToSwitchTo);
		return switchChoice;
	}

	private AiMoveChoice chooseMove(IAnature playerAnature)
	{
		AiMoveChoice moveChoice = mAI.chooseMove(mCurrentAnature, playerAnature);
		return moveChoice;
	}
}
