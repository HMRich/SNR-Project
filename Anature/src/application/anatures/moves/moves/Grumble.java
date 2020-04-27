package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;

public class Grumble extends MoveCore
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.adjustTempAttack(-0.1);
	}

}
