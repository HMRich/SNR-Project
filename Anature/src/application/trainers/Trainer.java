package application.trainers;

import java.util.ArrayList;

import application.AiChoiceObject;
import application.AiHealthPotionChoice;
import application.AiMoveChoice;
import application.AiSwitchChoice;
import application.Anature;
import application.BaseAI;
import application.enums.AttackEffectiveness;
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
	private BaseAI mAi;
	private double mHealthThreshold;
	private AttackEffectiveness mSwitchThreshold;

	public Trainer(TrainerIds id, String name, ArrayList<Anature> anatures, ArrayList<HealthPotion> potions, Anature currentAnature, BaseAI ai, double healthThreshold,
			AttackEffectiveness switchThreshold)
	{
		if(id == null || name == null || anatures == null || potions == null || currentAnature == null || ai == null)
			throw new IllegalArgumentException("Null Parameter");

		setTrainerId(id);
		setTrainerName(name);
		setAnatures(anatures);
		mPotions = potions;
		mCurrentAnature = currentAnature;
		mAi = ai;
		mHealthThreshold = healthThreshold;
		mSwitchThreshold = switchThreshold;
	}
	
	/*
	 * Gets, Sets
	 */
	private void setTrainerId(TrainerIds trainerId)
	{
		if(trainerId == null)
		{
			throw new IllegalArgumentException("Passed value \"trainerId\" was null.");
		}
		mId = trainerId;
	}
	
	private void setTrainerName(String name)
	{
		if(name == null || name.trim().isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was null.");
		}
		mName = name;
	}
	
	private void setAnatureParty(ArrayList<Anature> anatures)
	{
		// TODO talk with team about if we should allow the trainer anatures variable to be empty
		if(anatures == null)
		{
			throw new IllegalArgumentException("Passed value \"anatures\" was null.");
		}
		mAnatures = anatures;
	}
	
	private void set

	/*
	 * PUBLIC METHODS
	 */
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
	
	// TODO get rid of this method. It most likely does not belong here
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
	
	public ArrayList<HealthPotion> getItmes()
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
	
	public final AiChoiceObject<?> useTurn(Anature playerAnature)
	{
		boolean currentAnatureHasFainted = mCurrentAnature.getCurrHp() == 0;
		
		if(currentAnatureHasFainted)
			return chooseAnature(playerAnature);
		
		boolean willUseHealthPotion = mAi.willUseHealthPotion(mPotions, mCurrentAnature, mHealthThreshold);
		
		if(willUseHealthPotion)
			return chooseHealthPotion();
		
		boolean willSwitchAnature = mAi.willSwitchAnature(mAnatures, playerAnature, mCurrentAnature, mSwitchThreshold);
	
		if(willSwitchAnature)
			return chooseAnature(playerAnature);
		
		return chooseMove(playerAnature);
	}

	private AiHealthPotionChoice chooseHealthPotion()
	{
		HealthPotion healthPotionToUse = mAi.healthPotionToUse(mPotions, mCurrentAnature);
		AiHealthPotionChoice healthPotionChoice = new AiHealthPotionChoice(healthPotionToUse);
		return healthPotionChoice;
	}
	
	private AiSwitchChoice chooseAnature(Anature playerAnature)
	{
		Anature anatureToSwitchTo = mAi.chooseNewAnature(mAnatures, mCurrentAnature, playerAnature, mSwitchThreshold);
		AiSwitchChoice switchChoice = new AiSwitchChoice(anatureToSwitchTo);
		return switchChoice;
	}
	
	private AiMoveChoice chooseMove(Anature playerAnature)
	{
		AiMoveChoice moveChoice = mAi.chooseMove(mCurrentAnature, playerAnature);
		return moveChoice;
	}
}
