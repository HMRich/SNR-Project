package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class SmogWave extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempAccuracy();
	}

}
