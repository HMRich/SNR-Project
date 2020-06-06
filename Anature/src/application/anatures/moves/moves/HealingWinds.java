package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class HealingWinds extends Move
{
	private static final long serialVersionUID = -2433512935797299902L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		int currentHealthPoints = source.getStats().getCurrentHitPoints();
		int healthToBeRestored = currentHealthPoints + currentHealthPoints / 2;

		source.applyHeal(healthToBeRestored);

	}

}
