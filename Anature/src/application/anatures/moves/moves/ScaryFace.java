package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class ScaryFace extends Move
{
	private static final long serialVersionUID = 996073700717004373L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempDefense();
	}

}
