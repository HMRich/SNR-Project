package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class ScaryFace extends Move
{
	private static final long serialVersionUID = 996073700717004373L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempDefense();
	}

}
