package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class DoublePunch extends Move
{
	private static final long serialVersionUID = -1750268943625364491L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.applyDamage(calculateDamage(source, target, false));
	}
}
