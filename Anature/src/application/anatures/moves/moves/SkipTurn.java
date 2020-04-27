package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;

public class SkipTurn extends MoveCore
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		System.out.println("Turn skipped");
	}
}
