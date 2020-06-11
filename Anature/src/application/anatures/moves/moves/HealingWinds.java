package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class HealingWinds extends Move
{
	private static final long serialVersionUID = -2433512935797299902L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		int currentHealthPoints = source.getStats().getCurrentHitPoints();
		int healthToBeRestored = currentHealthPoints + currentHealthPoints / 2;

		source.applyHeal(healthToBeRestored);

	}

}
