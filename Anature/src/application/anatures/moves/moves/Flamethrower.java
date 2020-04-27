package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.MoveCore;
import application.enums.StatusEffects;

public class Flamethrower extends MoveCore
{
	private static int mDamageDone = 50;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
		target.updateStatus(StatusEffects.Burn);
	}
}
