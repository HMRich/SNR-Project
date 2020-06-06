package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Nimbleness extends Move
{
	private static final long serialVersionUID = -5410023720875199560L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempSpeed();
		source.getStats().increaseTempSpeed();
	}
}