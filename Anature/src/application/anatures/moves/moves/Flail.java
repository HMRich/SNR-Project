package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Flail extends Move
{
	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		int selfDamage = source.getStats().getTotalHitPoints() / 4;
		source.applyDamage(selfDamage);
		target.applyDamage(calculateDamage(source, target, false));
	}
}
