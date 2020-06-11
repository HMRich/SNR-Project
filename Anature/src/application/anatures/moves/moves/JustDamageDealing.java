package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class JustDamageDealing extends Move
{
	private static final long serialVersionUID = 4418390010746315936L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, !isPhysicalAttack()));
	}
}
