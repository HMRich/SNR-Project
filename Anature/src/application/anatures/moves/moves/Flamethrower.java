package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveBase;
import application.enums.StatusEffects;

public class Flamethrower extends MoveBase
{
	private static int mDamageDone = 50;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
		target.updateStatus(StatusEffects.Burn);
	}
}
