package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class PocketSand extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.adjustAccuracy(-0.1);
	}

}
