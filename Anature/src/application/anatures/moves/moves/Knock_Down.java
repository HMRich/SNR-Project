package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Knock_Down extends Move
{
	private static final long serialVersionUID = 5611850619590440588L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempSpeed();
	}
}