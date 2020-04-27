package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;

public class DoublePunch extends MoveCore
{
	private static int mDamageDone = 25;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);

	}
}
