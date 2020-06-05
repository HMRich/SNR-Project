package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class HoseDown extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempDefense();
		target.getStats().decreaseTempSpecialDefense();
	}
}
