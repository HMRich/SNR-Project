package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class Flamethrower extends Move
{
	private static final long serialVersionUID = 1699431073094715789L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.setStatus(StatusEffects.Burn);
	}
}
