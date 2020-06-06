package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Slow_Spore extends Move
{
	private static final long serialVersionUID = -1325517229224760218L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempSpeed();
	}
}