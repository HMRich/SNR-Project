package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class WaterToss extends Move
{
	private static final long serialVersionUID = 3273829636173880712L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempAccuracy();
	}
}
