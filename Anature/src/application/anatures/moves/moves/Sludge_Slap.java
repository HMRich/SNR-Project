package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Sludge_Slap extends Move
{
	private static final long serialVersionUID = 813234470082600378L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, true));
		target.getStats().decreaseTempAccuracy();
		target.getStats().decreaseTempAccuracy();
	}
}