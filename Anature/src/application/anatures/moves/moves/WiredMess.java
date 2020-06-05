package application.anatures.moves.moves;

import java.util.Random;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.interfaces.stats.IStats;

public class WiredMess extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		Random rng = new Random();
		int firstToDecrease = rng.nextInt(4) + 1;
		int secondToDecrease = findUnique(firstToDecrease, rng);
		
		decreaseStat(firstToDecrease, target);
		decreaseStat(secondToDecrease, target);
	}

	private int findUnique(int firstToDecrease, Random rng)
	{
		int toReturn = rng.nextInt(4) + 1;
		
		while(toReturn == firstToDecrease)
		{
			toReturn = rng.nextInt(4) + 1;
		}
		
		return toReturn;
	}

	private void decreaseStat(int statNumber, Anature toLower)
	{
		IStats stats = toLower.getStats();
		
		switch(statNumber)
		{
			case 1:
				stats.decreaseTempAttack();
				break;

			case 2:
				stats.decreaseTempSpecialAttack();
				break;

			case 3:
				stats.decreaseTempDefense();
				break;

			case 4:
				stats.decreaseTempSpecialDefense();
				break;

			default:
				break;
		}
	}
}
