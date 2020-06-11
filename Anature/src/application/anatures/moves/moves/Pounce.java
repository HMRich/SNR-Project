package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Pounce extends Move
{
	private static final long serialVersionUID = 4946376856133838327L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
	}
}
