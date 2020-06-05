package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Flamethrower extends Move
{
	private static final long serialVersionUID = 1699431073094715789L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(calculateDamage(source, target, false));
		target.updateStatus(StatusEffects.Burn);
	}
}
