package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class TailBlock extends Move
{
	public void activateMove(IAnature source, IAnature target)
	{
		// TODO Add in a protect invisible status and remove these stat changes
		source.getStats().increaseTempDefense();
		source.getStats().increaseTempSpecialDefense();
	}
}
