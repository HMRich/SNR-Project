package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Tornado extends Move
{
	private static final long serialVersionUID = -9124568042942133391L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempAccuracy();
	}
}
