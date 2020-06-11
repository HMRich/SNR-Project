package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Holler extends Move
{
	private static final long serialVersionUID = 4928715898972072814L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempAttack();
		target.getStats().decreaseTempAttack();
	}

}
