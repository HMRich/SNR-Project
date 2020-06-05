package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class DoublePunch extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.applyDamage(calculateDamage(source, target, false));
	}
}
