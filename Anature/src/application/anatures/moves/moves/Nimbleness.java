package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Nimbleness extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempSpeed();
		source.getStats().increaseTempSpeed();
	}
}