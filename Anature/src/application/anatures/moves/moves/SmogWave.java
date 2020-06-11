package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class SmogWave extends Move
{
	private static final long serialVersionUID = -2287066231088848103L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempAccuracy();
	}

}
