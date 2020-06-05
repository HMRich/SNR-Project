package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class HealingWinds extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		int currentHealthPoints = source.getStats().getCurrentHitPoints();
		int healthToBeRestored = currentHealthPoints + currentHealthPoints / 2;

		source.applyHeal(healthToBeRestored);

	}

}
