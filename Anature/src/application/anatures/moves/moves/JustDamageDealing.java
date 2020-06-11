package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class JustDamageDealing extends Move
{
	private static final long serialVersionUID = 4418390010746315936L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, !isPhysicalAttack()));
	}
}
