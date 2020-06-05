package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Sludge_Slap extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, true));
		target.getStats().decreaseTempAccuracy();
		target.getStats().decreaseTempAccuracy();
	}
}