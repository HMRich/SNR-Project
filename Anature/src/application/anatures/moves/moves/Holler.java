package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Holler extends Move
{
	private static final long serialVersionUID = 4928715898972072814L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempAttack();
		target.getStats().decreaseTempAttack();
	}

}
