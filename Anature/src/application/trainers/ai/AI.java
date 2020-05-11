package application.trainers.ai;

import java.util.ArrayList;
import java.util.Random;

import application.TypeAdvantage;
import application.anatures.movesets.MoveSet;
import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.AttackEffectiveness;
import application.enums.LoggingTypes;
import application.interfaces.IAI;
import application.interfaces.IAnature;
import application.interfaces.IHealthPotion;
import application.interfaces.IMove;
import application.pools.ItemPool;
import application.trainers.ai.choice_objects.AiMoveChoice;

class AI implements IAI
{
	private double mHealthThreshold;
	private AttackEffectiveness mSwitchThreshold;
	private AttackEffectiveness mMoveThreshold;

	AI()
	{
		mHealthThreshold = -1;
		mSwitchThreshold = AttackEffectiveness.NoEffect;
		mMoveThreshold = AttackEffectiveness.NoEffect;
	}

	/*
	 * PACKAGE SETS
	 */

	void setHealthThreshold(double healthThreshold)
	{
		if(healthThreshold < 0 || healthThreshold > 1)
		{
			throw new IllegalArgumentException("Passed value \"healthThreshold\" needs to be a value between 0 and 1.");
		}
		mHealthThreshold = healthThreshold;
	}

	void setSwitchThreshold(AttackEffectiveness switchThreshold)
	{
		if(switchThreshold == null)
		{
			throw new IllegalArgumentException("Passed value \"switchThreshold\" was null.");
		}

		boolean notVaildChoice = switchThreshold.equals(AttackEffectiveness.Error) || switchThreshold.equals(AttackEffectiveness.NotSet);
		if(notVaildChoice)
		{
			throw new IllegalArgumentException("Passed value \"switchThreshold\" needs to be a valid value. Value was " + switchThreshold.toString() + ".");
		}
		mSwitchThreshold = switchThreshold;
	}

	void setMoveThreshold(AttackEffectiveness moveThreshold)
	{
		if(moveThreshold == null)
		{
			throw new IllegalArgumentException("Passed value \"moveThreshold\" was null.");
		}

		boolean notVaildChoice = moveThreshold.equals(AttackEffectiveness.Error) || moveThreshold.equals(AttackEffectiveness.NotSet);
		if(notVaildChoice)
		{
			throw new IllegalArgumentException("Passed value \"moveThreshold\" needs to be a valid value. Value was " + moveThreshold.toString() + ".");
		}
		mMoveThreshold = moveThreshold;
	}

	/*
	 * PUBLIC METHODS
	 */

	public boolean willUseHealthPotion(ArrayList<IHealthPotion> healthPotionBases, IAnature currentAnature)
	{
		if(healthPotionBases == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: willUseHealthPotion(ArrayList<HealthPotion> healthPotions, Anature currentAnature), \"healthPotions\" value was null.");
		}

		if(currentAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: willUseHealthPotion(ArrayList<HealthPotion> healthPotions, Anature currentAnature), \"currentAnature\" value was null.");
		}

		double anatureHpPercent = currentAnature.getHitPointsPercent();

		boolean currentAnatureAtThreshold = anatureHpPercent <= mHealthThreshold;
		boolean trainerHasHealthPotion = !healthPotionBases.isEmpty();

		return trainerHasHealthPotion && currentAnatureAtThreshold;
	}

	public IHealthPotion healthPotionToUse(ArrayList<IHealthPotion> healthPotionBases, IAnature currentAnature)
	{
		if(healthPotionBases == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: healthPotionToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), \"healthPotions\" value was null.");
		}

		if(currentAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: healthPotionToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), \"currentAnature\" value was null.");
		}

		double currentAnatureHpPercent = currentAnature.getHitPointsPercent();

		IHealthPotion healthPotionToUse = null;
		double anaturePercentAfterItemUse = 0;

		for(IHealthPotion healthPotionBase : healthPotionBases)
		{
			int itemHealAmount = ItemPool.getHealthPotion(healthPotionBase.getItemId())
					.getHealAmount();
			double healPercent = itemHealAmount / currentAnature.getTotalHitPoints();

			double anatureHpPercentIfItemUsed = currentAnatureHpPercent + healPercent;
			boolean willOverheal = willHealthPotionOverheal(anatureHpPercentIfItemUsed);

			// TODO there is some logic here that allows trainers to use master potion even
			// if there may be a better option that allows for less over healing.

			if(anaturePercentAfterItemUse == 0 || anatureHpPercentIfItemUsed > anaturePercentAfterItemUse && !willOverheal)
			{
				anaturePercentAfterItemUse = anatureHpPercentIfItemUsed;
				healthPotionToUse = healthPotionBase;
			}
		}

		if(healthPotionToUse == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalStateException in BaseAI.java, Method: itemToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), itemToUse value was null.");
		}

		return healthPotionToUse;
	}

	public boolean willSwitchAnature(ArrayList<IAnature> anatureBases, IAnature enemyAnature, IAnature currentAnature)
	{
		if(anatureBases == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: willSwitchAnature(ArrayList<Anature> anatures, Anature enemyAnature, Anature currentAnature), \"anatures\" value was null.");
		}

		if(enemyAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: willSwitchAnature(ArrayList<Anature> anatures, Anature enemyAnature, Anature currentAnature), \"enemyAnature\" value was null.");
		}

		if(currentAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: willSwitchAnature(ArrayList<Anature> anatures, Anature enemyAnature, Anature currentAnature), \"currentAnature\" value was null.");
		}

		boolean hasAnotherAnature = anatureBases.size() > 1;
		boolean currentAnatureNotAtThreshold = !isAnatureAtThreshold(currentAnature, enemyAnature);
		boolean hasAnatureAtThreshold = hasAnAnatureAtThreshold(anatureBases, currentAnature, enemyAnature);

		return hasAnotherAnature && currentAnatureNotAtThreshold && hasAnatureAtThreshold;
	}

	public AiMoveChoice chooseMove(IAnature source, IAnature target)
	{
		if(source == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: chooseMove(Anature source, Anature target), \"source\" value was null.");
		}

		if(target == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: chooseMove(Anature source, Anature target), \"target\" value was null.");
		}

		ArrayList<AiMoveChoice> choices = moveChoiceList(source, target);
		if(choices.isEmpty())
			return null;

		// TODO Do we want to also evaluate more details for which move to choose or
		// leave it random?
		Random rnJesus = new Random();
		int randomIndexSelection = rnJesus.nextInt(choices.size());
		AiMoveChoice randomAiMoveChoice = choices.get(randomIndexSelection);
		return randomAiMoveChoice;
	}

	public IAnature chooseNewAnature(ArrayList<IAnature> anatureBases, IAnature currentAnature, IAnature enemyAnature)
	{
		IAnature anatureToReturn = currentAnature;

		for(IAnature anatureBase : anatureBases)
		{
			if(isAnatureAtThreshold(anatureBase, enemyAnature))
			{
				anatureToReturn = anatureBase;
			}
		}

		return anatureToReturn;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		return mHealthThreshold != -1 && !mSwitchThreshold.equals(AttackEffectiveness.NotSet) && !mMoveThreshold.equals(AttackEffectiveness.NotSet);
	}

	/*
	 * PRIVATE METHODS
	 */

	private boolean willHealthPotionOverheal(double hpPercentAfterHeal)
	{
		return hpPercentAfterHeal > 1;
	}

	private boolean isAnatureAtThreshold(IAnature currentAnature, IAnature enemyAnature)
	{
		AttackEffectiveness anatureEffectiveness = TypeAdvantage.anatureEffectiveness(currentAnature, enemyAnature);
		return isAtOrAboveThreshold(anatureEffectiveness, mSwitchThreshold);
	}

	private boolean moveIsAtThreshold(IMove move, IAnature target)
	{
		AttackEffectiveness moveEffectiveness = TypeAdvantage.moveEffectiveness(move, target);
		return isAtOrAboveThreshold(moveEffectiveness, mMoveThreshold);
	}

	private boolean isAtOrAboveThreshold(AttackEffectiveness effectiveness, AttackEffectiveness threshold)
	{
		switch(threshold)
		{
			case NoEffect:
				return TypeAdvantage.isNoEffect(effectiveness);

			case NotEffective:
				return TypeAdvantage.isAboveNoEffect(effectiveness);

			case Normal:
				return TypeAdvantage.isAboveNoEffect(effectiveness);

			case SuperEffective:
				return TypeAdvantage.isAboveNormal(effectiveness);

			default:
				return false;
		}
	}

	private boolean hasAnAnatureAtThreshold(ArrayList<IAnature> anatureParty, IAnature currentAnature, IAnature enemyAnature)
	{
		for(IAnature anatureBase : anatureParty)
		{
			if(isAnatureAtThreshold(anatureBase, enemyAnature))
			{
				return true;
			}
		}
		return false;
	}

	private ArrayList<AiMoveChoice> moveChoiceList(IAnature source, IAnature target)
	{
		ArrayList<AiMoveChoice> choices = new ArrayList<AiMoveChoice>();
		AttackEffectiveness moveThreshold = mMoveThreshold;
		do
		{
			choices = moveChoiceListAtThreshold(source, target, moveThreshold);
			if(choices == null || choices.isEmpty())
			{
				moveThreshold = TypeAdvantage.decrementEffectiveness(moveThreshold);
			}
		} while(!moveThreshold.equals(AttackEffectiveness.NoEffect) && choices.isEmpty());

		return choices;
	}

	private ArrayList<AiMoveChoice> moveChoiceListAtThreshold(IAnature source, IAnature target, AttackEffectiveness threshold)
	{
		ArrayList<AiMoveChoice> choices = new ArrayList<AiMoveChoice>();

		MoveSet moveSet = source.getMoveSet();

		if(moveSet.hasMove(1) && moveIsAtThreshold(moveSet.getMove(1), target))
		{
			AiMoveChoice moveChoice1 = new AiMoveChoice(AiChoice.Move1, moveSet.getMove(1));
			choices.add(moveChoice1);
		}

		if(moveSet.hasMove(2) && moveIsAtThreshold(moveSet.getMove(2), target))
		{
			AiMoveChoice moveChoice2 = new AiMoveChoice(AiChoice.Move2, moveSet.getMove(2));
			choices.add(moveChoice2);
		}

		if(moveSet.hasMove(3) && moveIsAtThreshold(moveSet.getMove(3), target))
		{
			AiMoveChoice moveChoice3 = new AiMoveChoice(AiChoice.Move3, moveSet.getMove(3));
			choices.add(moveChoice3);
		}

		if(moveSet.hasMove(4) && moveIsAtThreshold(moveSet.getMove(4), target))
		{
			AiMoveChoice moveChoice4 = new AiMoveChoice(AiChoice.Move4, moveSet.getMove(4));
			choices.add(moveChoice4);
		}

		return choices;
	}
}
