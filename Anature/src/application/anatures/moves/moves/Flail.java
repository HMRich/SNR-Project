package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;

public class Flail extends MoveCore
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.takeDamage(10);
		target.takeDamage(20);
	}
}
