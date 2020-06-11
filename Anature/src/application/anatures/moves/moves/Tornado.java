package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Tornado extends Move
{
	private static final long serialVersionUID = -9124568042942133391L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempAccuracy();
	}
}
