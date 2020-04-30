package application.anatures.moves.moves;

import application.anatures.moves.MoveBase;
import application.interfaces.IAnature;

public class Tackle extends MoveBase
{
	private static int mDamageDone = 30;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(mDamageDone);
	}
}
