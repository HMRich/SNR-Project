package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class PocketSand extends Move
{
	private static final long serialVersionUID = 6387315253784923880L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempAccuracy();
	}

}
