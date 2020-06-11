package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Nimbleness extends Move
{
	private static final long serialVersionUID = -5410023720875199560L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().increaseTempSpeed();
		source.getStats().increaseTempSpeed();
	}
}