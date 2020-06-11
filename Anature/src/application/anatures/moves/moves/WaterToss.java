package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class WaterToss extends Move
{
	private static final long serialVersionUID = 3273829636173880712L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempAccuracy();
	}
}
