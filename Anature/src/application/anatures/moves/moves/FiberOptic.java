package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class FiberOptic extends Move
{
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempSpeed();
	}
}
