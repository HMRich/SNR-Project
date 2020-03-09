package application;

import java.util.ArrayList;
import java.util.Random;

import application.enums.AiChoice;
import application.enums.Type;
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

	public AiChoice chooseMove(Anature source)
	{
		ArrayList<AiChoice> choices = new ArrayList<AiChoice>();

		MoveSet moves = source.getMoves();

		if(moves.hasMove(1))
		{
			choices.add(AiChoice.Move1);
		}
		
		if(moves.hasMove(2))
		{
			choices.add(AiChoice.Move2);
		}
		
		if(moves.hasMove(3))
		{
			choices.add(AiChoice.Move3);
		}
		
		if(moves.hasMove(4))
		{
			choices.add(AiChoice.Move4);
		}

		return choices.get(new Random().nextInt(choices.size()));
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
