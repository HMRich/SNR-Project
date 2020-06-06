package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Grumble extends Move
{
	private static final long serialVersionUID = 4786683482738040341L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempAttack();
	}

}
