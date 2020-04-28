package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;

public class DoublePunch extends MoveBase
{
	private static int mDamageDone = 25;

	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);

	}
}
