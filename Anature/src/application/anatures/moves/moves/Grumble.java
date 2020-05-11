package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Grumble extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.adjustAttack(-0.1);
	}

}
