package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Sludge_Missile extends Move
{
	private static final long serialVersionUID = 8733187858023697858L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(calculateDamage(source, target, true));
		target.getStats().decreaseTempAccuracy();
	}
}