package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class PocketSand extends Move
{
	private static final long serialVersionUID = 6387315253784923880L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempAccuracy();
	}

}
