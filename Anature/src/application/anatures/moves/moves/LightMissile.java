package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class LightMissile extends Move
{
	private static final long serialVersionUID = 1918813557032685941L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempSpecialDefense();
	}

}
