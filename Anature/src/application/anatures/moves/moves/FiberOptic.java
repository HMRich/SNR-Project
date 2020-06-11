package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class FiberOptic extends Move
{
	private static final long serialVersionUID = -6301795800419974978L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().increaseTempSpeed();
	}
}
