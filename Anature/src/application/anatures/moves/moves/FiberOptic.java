package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class FiberOptic extends Move
{
	private static final long serialVersionUID = -6301795800419974978L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempSpeed();
	}
}
