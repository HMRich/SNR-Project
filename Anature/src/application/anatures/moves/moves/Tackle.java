package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;

public class Tackle extends MoveCore implements Move
{
	private static int mDamageDone = 30;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
