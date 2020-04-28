package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;

public class Grumble extends MoveBase
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.adjustTempAttack(-0.1);
	}

}
