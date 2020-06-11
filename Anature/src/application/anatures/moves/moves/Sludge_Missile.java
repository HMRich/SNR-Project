package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Sludge_Missile extends Move
{
	private static final long serialVersionUID = 8733187858023697858L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, true));
		target.getStats().decreaseTempAccuracy();
	}
}