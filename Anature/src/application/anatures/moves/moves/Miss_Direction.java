package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Miss_Direction extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
			target.takeDamage(calculateDamage(source, target, true));
	}
}