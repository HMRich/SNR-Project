package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class DoublePunch extends Move
{
	private static int mDamageDone = 25;

	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(mDamageDone);

	}
}
