package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Flail extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.takeDamage(10);
		target.takeDamage(20);
	}
}
