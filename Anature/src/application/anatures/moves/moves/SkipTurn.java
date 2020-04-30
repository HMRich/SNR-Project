package application.anatures.moves.moves;

import application.anatures.moves.MoveBase;
import application.interfaces.IAnature;

public class SkipTurn extends MoveBase
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		System.out.println("Turn skipped");
	}
}
