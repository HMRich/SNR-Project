package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class NullMove extends Move
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		throw new IllegalStateException("This method should not be called.");
	}
}
