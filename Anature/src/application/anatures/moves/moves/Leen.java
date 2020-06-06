package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Leen extends Move
{
	private static final long serialVersionUID = -2940370698938364613L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		// lower target defense by 1 stage
		target.getStats().decreaseTempDefense();
	}
}
