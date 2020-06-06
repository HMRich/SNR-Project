package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class SmogWave extends Move
{
	private static final long serialVersionUID = -2287066231088848103L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempAccuracy();
	}

}
