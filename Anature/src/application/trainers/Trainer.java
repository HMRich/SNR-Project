package application.trainers;

import java.util.ArrayList;

import application.Anature;
import application.ai.AI;
import application.ai.choice_objects.AiChoiceObject;
import application.ai.choice_objects.AiHealthPotionChoice;
import application.ai.choice_objects.AiMoveChoice;
import application.ai.choice_objects.AiNoChoice;
import application.ai.choice_objects.AiSwitchChoice;
import application.enums.TrainerIds;
import application.items.HealthPotion;
import javafx.scene.image.Image;

public class Trainer
{
	private TrainerIds mId;
	private String mName;
	private ArrayList<Anature> mAnatures;
	private ArrayList<HealthPotion> mPotions;
	private Anature mCurrentAnature;
	private AI mAi;
	
	// TODO make default triner stats
	Trainer()
	{
		
	}

	/*
	 * PACKAGE SETS
	 */

	Trainer setTrainerId(TrainerIds trainerId)
	{
		if(trainerId == null)
		{
			throw new IllegalArgumentException("Passed value \"trainerId\" was null.");
		}
		mId = trainerId;
		return this;
	}

	Trainer setTrainerName(String name)
	{
		if(name == null || name.trim()
				.isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was null.");
		}
		mName = name;
		return this;
	}

	Trainer setAnatureParty(ArrayList<Anature> anatures)
	{
		// TODO talk with team about if we should allow the trainer anatures variable to
		// be empty
		if(anatures == null)
		{
			throw new IllegalArgumentException("Passed value \"anatures\" was null.");
		}
		mAnatures = anatures;
		return this;
	}

	Trainer setPotions(ArrayList<HealthPotion> potions)
	{
		if(potions == null)
		{
			throw new IllegalArgumentException("Passed value \"potions\" was null.");
		}
		mPotions = potions;
		return this;
	}

	Trainer setCurrentAnature(Anature anature)
	{
		if(anature == null)
		{
			throw new IllegalArgumentException("Passed value \"anature\" was null.");
		}
		mCurrentAnature = anature;
		return this;
	}

	Trainer setAi(AI ai)
	{
		if(ai == null)
		{
			throw new IllegalArgumentException("Passed value \"ai\" was null.");
		}
		mAi = ai;
		return this;
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

	public ArrayList<Anature> getAnatureParty()
	{
		return mAnatures;
	}

	public ArrayList<HealthPotion> getHealthPotions()
	{
		return mPotions;
	}

	public Anature getCurrentAnature()
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
	public int getAnatureIndex(Anature anature)
	{
		int index = 0;
		for(Anature currentAnature : mAnatures)
		{
			if(currentAnature.equals(anature))
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

	public final AiChoiceObject<?> useTurn(Anature playerAnature)
	{
		boolean willUseHealthPotion = mAi.willUseHealthPotion(mPotions, mCurrentAnature);

		if(willUseHealthPotion)
			return chooseHealthPotion();

		boolean willSwitchAnature = mAi.willSwitchAnature(mAnatures, playerAnature, mCurrentAnature);

		if(willSwitchAnature)
			return chooseAnature(playerAnature);

		AiMoveChoice moveChoice = chooseMove(playerAnature);
		if(moveChoice != null)
			return moveChoice;

		return new AiNoChoice();
	}

	/*
	 * PRIVATE METHODS
	 */

	private AiHealthPotionChoice chooseHealthPotion()
	{
		HealthPotion healthPotionToUse = mAi.healthPotionToUse(mPotions, mCurrentAnature);
		AiHealthPotionChoice healthPotionChoice = new AiHealthPotionChoice(healthPotionToUse);
		return healthPotionChoice;
	}

	private AiSwitchChoice chooseAnature(Anature playerAnature)
	{
		Anature anatureToSwitchTo = mAi.chooseNewAnature(mAnatures, mCurrentAnature, playerAnature);
		AiSwitchChoice switchChoice = new AiSwitchChoice(anatureToSwitchTo);
		return switchChoice;
	}

	private AiMoveChoice chooseMove(Anature playerAnature)
	{
		AiMoveChoice moveChoice = mAi.chooseMove(mCurrentAnature, playerAnature);
		return moveChoice;
	}
}
