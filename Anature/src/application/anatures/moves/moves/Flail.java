package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;

public class Flail extends MoveBase
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.takeDamage(10);
		target.takeDamage(20);
	}
}
