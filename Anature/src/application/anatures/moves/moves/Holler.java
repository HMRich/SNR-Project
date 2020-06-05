package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Holler extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempAttack();
		target.getStats().decreaseTempAttack();
	}

}
