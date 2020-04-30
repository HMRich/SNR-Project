package application.anatures.moves.moves;

import application.anatures.moves.MoveBase;
import application.interfaces.IAnature;

public class Grumble extends MoveBase
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.adjustAttack(-0.1);
	}

}
