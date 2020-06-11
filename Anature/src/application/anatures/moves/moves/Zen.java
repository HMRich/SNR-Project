package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Zen extends Move
{
	private static final long serialVersionUID = 9179817564591903970L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		source.getStats().increaseTempSpecialAttack();
		source.getStats().increaseTempSpecialDefense();
	}
}