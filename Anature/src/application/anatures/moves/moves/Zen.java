package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Zen extends Move
{
	private static final long serialVersionUID = 9179817564591903970L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		source.getStats().increaseTempSpecialAttack();
		source.getStats().increaseTempSpecialDefense();
	}
}