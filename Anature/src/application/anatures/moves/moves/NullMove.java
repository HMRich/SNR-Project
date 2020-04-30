package application.anatures.moves.moves;

import application.anatures.moves.MoveBase;
import application.interfaces.IAnature;

public class NullMove extends MoveBase
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		throw new IllegalStateException("This method should not be called.");
	}
}
