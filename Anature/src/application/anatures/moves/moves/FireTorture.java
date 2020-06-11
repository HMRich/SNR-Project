package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class FireTorture extends Move
{
	private static final long serialVersionUID = -5745909725599391439L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		if(Math.random() < 0.20)
		{
			target.updateStatus(StatusEffects.Burn);
		}
	}

}
