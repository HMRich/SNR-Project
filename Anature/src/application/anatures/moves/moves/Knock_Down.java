package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Knock_Down extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempSpeed();
	}
}