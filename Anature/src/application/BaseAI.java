package application;

import java.util.ArrayList;
import java.util.Random;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.AttackEffectiveness;
import application.enums.LoggingTypes;
import application.items.HealthPotion;
import application.items.ItemPool;
import application.moves.Move;

public class BaseAI
{
	
	 /*
	  *  PUBLIC METHODS
	  */
	public final boolean willUseHealthPotion(ArrayList<HealthPotion> healthPotions, Anature currentAnature, double healthThreshold)
	{
		if(healthPotions == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: useItem(ArrayList<Item> items, Anature currentAnature, double healthThreshold), items value was null.");
		}

		if(currentAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: useItem(ArrayList<Item> items, Anature currentAnature, double healthThreshold), currentAnature value was null.");
		}

		if(healthThreshold > 1)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: useItem(ArrayList<Item> items, Anature currentAnature, double healthThreshold), healthThreshold value was above 1.0.");
		}

		double anatureHpPercent = currentAnature.getHpPercent();

		boolean currentAnatureAtThreshold = anatureHpPercent <= healthThreshold;
		boolean trainerHasHealthPotion = !healthPotions.isEmpty();

		return trainerHasHealthPotion && currentAnatureAtThreshold;
	}
	

	public HealthPotion healthPotionToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature)
	{
		if(healthPotions == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: itemToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), items value was null.");
		}

		if(currentAnature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in BaseAI.java, Method: itemToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), currentAnature value was null.");
		}

		double currentAnatureHpPercent = currentAnature.getHpPercent();

		HealthPotion healthPotionToUse = null;
		double anaturePercentAfterItemUse = 0;

		for(HealthPotion healthPotion : healthPotions)
		{
			int itemHealAmount = ItemPool.getHealthPotion(healthPotion.getItemId()).getHealAmount();
			double healPercent = itemHealAmount / currentAnature.getTotalHp();

			double anatureHpPercentIfItemUsed = currentAnatureHpPercent + healPercent;
			boolean willOverheal = willHealthPotionOverheal(anatureHpPercentIfItemUsed);

			// TODO there is some logic here that allows trainers to use master potion even
			// if there may be a better option that allows for less over healing.

			if(anaturePercentAfterItemUse == 0 || anatureHpPercentIfItemUsed > anaturePercentAfterItemUse && !willOverheal)
			{
				anaturePercentAfterItemUse = anatureHpPercentIfItemUsed;
				healthPotionToUse = healthPotion;
			}
		}

		if(healthPotionToUse == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalStateException in BaseAI.java, Method: itemToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), itemToUse value was null.");
		}

		return healthPotionToUse;
	}
	

	public boolean willSwitchAnature(ArrayList<Anature> anatures, Anature enemyAnature, Anature currentAnature, AttackEffectiveness switchThreshold)
	{
		boolean hasOtherAnature = !anatures.isEmpty() && anatures.size() > 1;
		boolean currentAnatureIsAtTypeDisavantage = isAnatureAtTypeDisadvantage(currentAnature, enemyAnature, switchThreshold);
		boolean hasAnatureWithTypeAdvantage = hasAnAnatureWithTypeAdvantage(anatures, currentAnature, enemyAnature, switchThreshold);
		
		return hasOtherAnature && currentAnatureIsAtTypeDisavantage && hasAnatureWithTypeAdvantage;
	}
	
	
	// TODO Add a advantage threshold??
	public AiMoveChoice chooseMove(Anature source, Anature target)
	{
		ArrayList<AiMoveChoice> choices = new ArrayList<AiMoveChoice>();
		
		MoveSet moveSet = source.getMoveSet();
		
		if(moveSet.hasMove(1))
		{
			AiMoveChoice moveChoice1 = new AiMoveChoice(AiChoice.Move1, moveSet.getMove(1));
			choices.add(moveChoice1);
		}
		
		if(moveSet.hasMove(2))
		{
			AiMoveChoice moveChoice2 = new AiMoveChoice(AiChoice.Move2, moveSet.getMove(2));
			choices.add(moveChoice2);
		}
		
		if(moveSet.hasMove(3))
		{
			AiMoveChoice moveChoice3 = new AiMoveChoice(AiChoice.Move3, moveSet.getMove(3));
			choices.add(moveChoice3);
		}
		
		if(moveSet.hasMove(4))
		{
			AiMoveChoice moveChoice4 = new AiMoveChoice(AiChoice.Move4, moveSet.getMove(4));
			choices.add(moveChoice4);
		}
		
		// TODO Swap out random for something better
		Random rnJesus = new Random();
		int randomIndexSelection = rnJesus.nextInt(choices.size());
		AiMoveChoice randomAiMoveChoice = choices.get(randomIndexSelection);
		
		return randomAiMoveChoice;
	}
	
	
	public Anature chooseNewAnature(ArrayList<Anature> anatures, Anature currentAnature, Anature enemyAnature, AttackEffectiveness switchThreshold)
	{
		Anature anatureToReturn = currentAnature;
		AttackEffectiveness anatureEffectiveness = TypeAdvantage.advantageType(currentAnature, enemyAnature);
		
		for(Anature anature : anatures)
		{
			if(isAnatureAtTypeAdvantage(anature, enemyAnature, anatureEffectiveness))
			{
				anatureToReturn = anature;
				anatureEffectiveness = TypeAdvantage.advantageType(anatureToReturn, enemyAnature);
			}
		}
		return currentAnature;
	}
	
	/*
	 *  PRIVATE METHODS
	 */
	private boolean willHealthPotionOverheal(double percentAfterHeal)
	{
		return percentAfterHeal > 1;
	}

	private boolean isAnatureAtTypeDisadvantage(Anature currentAnature, Anature enemyAnature, AttackEffectiveness switchThreshold)
	{
		AttackEffectiveness effectiveness = TypeAdvantage.advantageType(currentAnature, enemyAnature);
		boolean isEffectivenessNotAboveNotEffective = !isAboveEffectivenessThreshold(effectiveness, switchThreshold);
		
		return isEffectivenessNotAboveNotEffective;
	}
	
	
	private boolean isAnatureAtTypeAdvantage(Anature currentAnature, Anature enemyAnature, AttackEffectiveness switchThreshold)
	{
		return !isAnatureAtTypeDisadvantage(currentAnature, enemyAnature, switchThreshold);
	}
	
	
	private boolean isAboveEffectivenessThreshold(AttackEffectiveness effectiveness, AttackEffectiveness switchThreshold)
	{
		switch (switchThreshold)
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
				LoggerController.logEvent(LoggingTypes.Error,
						"IllegalArgumentException in BaseAI.java, Method: isAboveEffectivenessThreshold(AttackEffectiveness effectiveness, AttackEffectiveness switchThreshold), items value was " + switchThreshold.toString() + ".");
				return false;
		}
	}
	

	private boolean hasAnAnatureWithTypeAdvantage(ArrayList<Anature> anatures, Anature currentAnature, Anature enemyAnature, AttackEffectiveness switchThreshold)
	{
		AttackEffectiveness currentEffectiveness = TypeAdvantage.advantageType(currentAnature, enemyAnature);
		
		for(Anature anature : anatures)
		{
			if(isAnatureAtTypeAdvantage(anature, enemyAnature, currentEffectiveness))
			{
				return true;
			}
		}
		return false;
	}

}
