package application.trainers;

import java.util.ArrayList;

import application.anatures.Anature;
import application.enums.TrainerIds;
import application.interfaces.AiChoiceObject;
import application.interfaces.IHealthPotion;
import application.trainers.ai.AI;
import application.trainers.ai.choice_objects.AiHealthPotionChoice;
import application.trainers.ai.choice_objects.AiMoveChoice;
import application.trainers.ai.choice_objects.AiNoChoice;
import application.trainers.ai.choice_objects.AiSwitchChoice;
import javafx.scene.image.Image;

public class Trainer
{
	private TrainerIds mId;
	private String mName;
	private ArrayList<Anature> mAnatures;
	private ArrayList<IHealthPotion> mHealthPotions;
	private Anature mCurrentAnature;
	private AI mAI;

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

		if(name.trim().isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was an empty string.");
		}
		mName = name;
	}

	void setAnatureParty(ArrayList<Anature> anatures)
	{
		// TODO talk with team about if we should allow the trainer anatures variable to
		// be empty
		if(anatures == null)
		{
			throw new IllegalArgumentException("Passed value \"anatures\" was null.");
		}
		mAnatures = anatures;
	}

	void setHealthPotions(ArrayList<IHealthPotion> healthPotionBases)
	{
		if(healthPotionBases == null)
		{
			throw new IllegalArgumentException("Passed value \"potions\" was null.");
		}
		mHealthPotions = healthPotionBases;
	}

	void setCurrentAnature(Anature currentAnature)
	{
		if(currentAnature == null)
		{
			throw new IllegalArgumentException("Passed value \"anature\" was null.");
		}
		mCurrentAnature = currentAnature;
	}

	void setAI(AI ai)
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

	public ArrayList<Anature> getAnatureParty()
	{
		return mAnatures;
	}

	public ArrayList<IHealthPotion> getHealthPotions()
	{
		return mHealthPotions;
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

		return new Image(getClass().getResource("/resources/images/trainers/" + mId.toString().toLowerCase() + "/" + mId.toString() + ".png").toExternalForm(),
				1000.0, 1000.0, true, false);
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
			if(anature.getCurrentHitPoints() == 0)
			{
				result = true;
				break;
			}
		}

		return !result;
	}

	public final AiChoiceObject<?> useTurn(Anature playerAnature)
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

	private AiSwitchChoice chooseAnature(Anature playerAnature)
	{
		Anature anatureToSwitchTo = mAI.chooseNewAnature(mAnatures, mCurrentAnature, playerAnature);
		AiSwitchChoice switchChoice = new AiSwitchChoice(anatureToSwitchTo);
		return switchChoice;
	}

	private AiMoveChoice chooseMove(Anature playerAnature)
	{
		AiMoveChoice moveChoice = mAI.chooseMove(mCurrentAnature, playerAnature);
		return moveChoice;
	}
}
