package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class WaterToss extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempAccuracy();
	}
}
