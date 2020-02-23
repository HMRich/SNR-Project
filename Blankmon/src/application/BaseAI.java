package application;

import java.util.ArrayList;
import java.util.Random;
import application.enums.*;
import application.items.Item;

public class BaseAI
{
	public final AiChoice useItem(ArrayList<Item> items, Anature currentAnature, int healthThreshold)
	{
		if(items.isEmpty())
		{
			return AiChoice.No_Choice;
		}
		
		else if(currentAnature.getTotalHp() < healthThreshold)
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

	public AiChoice chooseMove()
	{
		double random = Math.random();
		if(random < 0.25)
		{
			return AiChoice.move0;
		}
		
		else if(random > 0.25 && random <= 0.50)
		{
			return AiChoice.move1;
		}
		
		else if(random > 0.50 && random <= 0.75)
		{
			return AiChoice.move2;
		}
		
		else
		{
			return AiChoice.move3;
		}
	}

	private final boolean willSwitch(int switchThreshold)
	{
		return isAnatureAtThreshold(switchThreshold);
	}

	private boolean isAnatureAtTypeDisadvantage(Type[] types)
	{
		return false;
	}

	private boolean isAnatureAtThreshold(int switchThreshold)
	{
		if(switchThreshold == 0)
			return false;

		Random r = new Random();
		return r.nextInt(101) <= switchThreshold;
	}
}
