package application.anatures.moves.moves;

import application.anatures.moves.MoveBase;
import application.interfaces.IAnature;

public class PocketSand extends MoveBase
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.adjustAccuracy(-0.1);
	}

}
