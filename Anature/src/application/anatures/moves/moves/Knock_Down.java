package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Knock_Down extends Move
{
	private static final long serialVersionUID = 5611850619590440588L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempSpeed();
	}
}