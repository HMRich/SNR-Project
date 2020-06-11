package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Slow_Spore extends Move
{
	private static final long serialVersionUID = -1325517229224760218L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempSpeed();
	}
}