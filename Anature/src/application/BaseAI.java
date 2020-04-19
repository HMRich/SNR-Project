package application;

import java.util.ArrayList;
import java.util.Random;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.AttackEffectiveness;
import application.enums.LoggingTypes;
import application.items.HealthPotion;
import application.items.ItemPool;

public class BaseAI
{
	/*
	 * PUBLIC METHODS
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

	public boolean willSwitchAnature(ArrayList<Anature> anatures, AttackEffectiveness switchThreshold, Anature currentAnature, Anature enemyAnature)
	{
		boolean hasOtherAnature = !anatures.isEmpty() && anatures.size() > 1;
		boolean currentAnatureIsAtTypeDisavantage = isAnatureAtTypeDisadvantage(currentAnature, enemyAnature, switchThreshold);
		boolean hasAnatureWithTypeAdvantage = hasAnAnatureWithTypeAdvantage(anatures, currentAnature, enemyAnature, switchThreshold);
		
		return hasOtherAnature && currentAnatureIsAtTypeDisavantage && hasAnatureWithTypeAdvantage;
	}
	
	// TODO Add a advantage threshold??
	public AiChoice chooseMove(Anature source, Anature target)
	{
		ArrayList<AiChoice> choices = new ArrayList<AiChoice>();
		
		MoveSet moves = source.getMoves();
		
		if(moves.hasMove(1))
			choices.add(AiChoice.Move1);
		if(moves.hasMove(2))
			choices.add(AiChoice.Move2);
		if(moves.hasMove(3))
			choices.add(AiChoice.Move3);
		if(moves.hasMove(4))
			choices.add(AiChoice.Move4);
		
		return choices.get(new Random().nextInt(choices.size()));
	}
	
	/*
	 * PRIVATE METHODS
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
