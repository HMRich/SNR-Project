package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;

public class PocketSand extends MoveCore
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.adjustTempAccuracy(-0.1);
	}

}
