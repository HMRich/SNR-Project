package application;

import java.util.ArrayList;
import java.util.Random;

import application.controllers.LoggerController;
import application.enums.*;
import application.items.HealthPotion;
import application.items.Item;
import application.items.ItemPool;

public class BaseAI
{
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

	public HealthPotion itemToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature)
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

		HealthPotion itemToUse = null;
		double anaturePercentAfterItemUse = 0;

		for(HealthPotion healthPotion : healthPotions)
		{
			int itemHealAmount = ItemPool.getHealthPotion(healthPotion.getItemId()).getHealAmount();
			double healPercent = itemHealAmount / currentAnature.getTotalHp();

			double anatureHpPercentIfItemUsed = currentAnatureHpPercent + healPercent;
			boolean willOverheal = willHealthPotionOverheal(anatureHpPercentIfItemUsed);

			// TODO there is some logic here that allows trainers to use master potion even
			// it there may be a better option that allows for less over healing.

			if(anaturePercentAfterItemUse == 0 || anatureHpPercentIfItemUsed > anaturePercentAfterItemUse && !willOverheal)
			{
				anaturePercentAfterItemUse = anatureHpPercentIfItemUsed;
				itemToUse = healthPotion;
			}
		}

		if(itemToUse == null)
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalStateException in BaseAI.java, Method: itemToUse(ArrayList<HealthPotion> healthPotions, Anature currentAnature), itemToUse value was null.");
		}

		return itemToUse;
	}

	private boolean willHealthPotionOverheal(double percentAfterHeal)
	{
		return percentAfterHeal > 1;
	}

	public AiChoice switchAnature(ArrayList<Anature> anatures, Type[] types, int switchThreshold, Anature currentAnature)
	{
		if(!anatures.isEmpty() && isAnatureAtTypeDisadvantage(types) && willSwitch(switchThreshold))
		{
			return AiChoice.Switch_Anature;
		}

		return AiChoice.No_Choice;
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

	private final boolean willSwitch(int switchThreshold)
	{
		return isAnatureAtThreshold(switchThreshold);
	}

	private boolean isAnatureAtTypeDisadvantage(Type[] types)
	{
		return false; // TODO We need to make Type Logic
	}

	private boolean isAnatureAtThreshold(int switchThreshold)
	{
		if(switchThreshold == 0)
			return false;

		Random r = new Random();
		return r.nextInt(101) <= switchThreshold;
	}
}
