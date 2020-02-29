package application;

import java.util.ArrayList;
import java.util.Random;

import application.controllers.LoggerController;
import application.enums.*;
import application.items.Item;

public class BaseAI
{
	public final AiChoice useItem(ArrayList<Item> items, Anature currentAnature, double healthThreshold)
	{
		if(healthThreshold > 1)
		{
			LoggerController.logEvent(LoggingTypes.Default, "Exception in BaseAI class, healthThreshold was above 1.0.");
			throw new IllegalArgumentException("healthThreshold was above the value 1.0, healthThrshold should be below 1.0.");
		}
		
		if(!items.isEmpty() && currentAnature.getHpPercent() < healthThreshold)
		{
			return AiChoice.Item_Consumed;
		}

		else
		{
			return AiChoice.No_Choice;
		}
	}

	public AiChoice switchAnature(ArrayList<Anature> anatures, Type[] types, int switchThreshold, Anature currentAnature)
	{
		if(!anatures.isEmpty() && isAnatureAtTypeDisadvantage(types) && willSwitch(switchThreshold))
		{
			return AiChoice.Switch_Anature;
		}

		return AiChoice.No_Choice;
	}

	public AiChoice chooseMove(MoveSet anatureMoves)
	{
		ArrayList<AiChoice> choiceArray = new ArrayList<AiChoice>();
		
		boolean hasMove1 = anatureMoves.getMove(0) != null;
		boolean hasMove2 = anatureMoves.getMove(1) != null;
		boolean hasMove3 = anatureMoves.getMove(2) != null;
		boolean hasMove4 = anatureMoves.getMove(3) != null;
		
		if(hasMove1)
			choiceArray.add(AiChoice.Move1);
		if(hasMove2)
			choiceArray.add(AiChoice.Move2);
		if(hasMove3)
			choiceArray.add(AiChoice.Move3);
		if(hasMove4)
			choiceArray.add(AiChoice.Move4);
		
		return choiceArray.get(new Random().nextInt(choiceArray.size()));
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
