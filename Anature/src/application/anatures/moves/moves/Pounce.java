package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Pounce extends Move
{
	private static final long serialVersionUID = 4946376856133838327L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
	}
}
