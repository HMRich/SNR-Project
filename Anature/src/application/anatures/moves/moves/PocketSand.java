package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;

public class PocketSand extends MoveBase
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.adjustTempAccuracy(-0.1);
	}

}
