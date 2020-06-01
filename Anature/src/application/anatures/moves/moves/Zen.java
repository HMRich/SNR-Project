package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Zen extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempSpecialAttack();
		source.getStats().increaseTempSpecialDefense();
	}
}