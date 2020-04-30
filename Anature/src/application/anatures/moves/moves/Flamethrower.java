package application.anatures.moves.moves;

import application.anatures.moves.MoveBase;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Flamethrower extends MoveBase
{
	private static int mDamageDone = 50;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(mDamageDone);
		target.updateStatus(StatusEffects.Burn);
	}
}
