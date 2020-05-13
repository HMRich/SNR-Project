package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class SkipTurn extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		// TODO Do we need this?
		System.out.println("Turn skipped");
	}
}
