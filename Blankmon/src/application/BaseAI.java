package application;

import java.util.ArrayList;
import java.util.Random;
import application.enums.*;
import application.items.Item;

public class BaseAI
{
	public final String useItem(ArrayList<Item> items, Anature currentAnature, int healthThreshold)
	{
		if(items.isEmpty())
		{
			return "";
		}
		
		else if(currentAnature.getTotalHp() < healthThreshold)
		{
			return "Item Consumed";
		}
		
		else
		{
			return "";
		}
	}

	public String switchAnature(ArrayList<Anature> anatures, Type[] types, int switchThreshold, Anature currentAnature)
	{
		if(!anatures.isEmpty() && isAnatureAtTypeDisadvantage(types) && willSwitch(switchThreshold))
		{
			return "Switch Anature";
		}

		return "";
	}

	public String chooseMove()
	{
		double random = Math.random();
		if(random < 0.25)
		{
			return "Move0";
		}
		
		else if(random > 0.25 && random <= 0.50)
		{
			return "Move1";
		}
		
		else if(random > 0.50 && random <= 0.75)
		{
			return "Move2";
		}
		
		else
		{
			return "Move3";
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
