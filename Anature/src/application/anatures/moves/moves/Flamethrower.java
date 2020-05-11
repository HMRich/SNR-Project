package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Flamethrower extends Move
{
	private static int mDamageDone = 50;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(mDamageDone);
		target.updateStatus(StatusEffects.Burn);
	}
}
