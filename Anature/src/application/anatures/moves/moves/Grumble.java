package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Grumble extends Move
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempAttack();
	}

}
