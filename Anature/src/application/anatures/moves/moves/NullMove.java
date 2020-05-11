package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class NullMove extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		throw new IllegalStateException("This method should not be called.");
	}
}
