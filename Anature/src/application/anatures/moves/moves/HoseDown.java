package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class HoseDown extends Move
{
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempSpecialDefense();
	}
}
