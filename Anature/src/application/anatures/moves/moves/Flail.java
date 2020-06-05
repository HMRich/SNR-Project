package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.Stat;

public class Flail extends Move
{
	@Override
	public void activateMove(Anature source, Anature target)
	{
		int selfDamage = source.getStats().getTotalStat(Stat.HitPoints) / 4;
		source.applyDamage(selfDamage);
		target.applyDamage(calculateDamage(source, target, false));
	}
}
