package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;

public class NullMove extends MoveBase
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		throw new IllegalStateException("This method should not be called.");
	}
}
