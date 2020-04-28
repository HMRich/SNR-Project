package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;

public class SkipTurn extends MoveBase
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		System.out.println("Turn skipped");
	}
}
