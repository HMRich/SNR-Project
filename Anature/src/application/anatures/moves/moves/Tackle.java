package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Tackle extends Move
{
	private static int mDamageDone = 30;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(mDamageDone);
	}
}
