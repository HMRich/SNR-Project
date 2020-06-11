package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Leen extends Move
{
	private static final long serialVersionUID = -2940370698938364613L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		// lower target defense by 1 stage
		target.getStats().decreaseTempDefense();
	}
}
