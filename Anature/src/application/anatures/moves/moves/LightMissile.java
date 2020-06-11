package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class LightMissile extends Move
{
	private static final long serialVersionUID = 1918813557032685941L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.getStats().decreaseTempSpecialDefense();
	}

}
