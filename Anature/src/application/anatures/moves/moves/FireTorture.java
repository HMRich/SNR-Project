package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class FireTorture extends Move
{
	private static final long serialVersionUID = -5745909725599391439L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(calculateDamage(source, target, false));
		if(Math.random() < 0.20)
		{
			target.updateStatus(StatusEffects.Burn);
		}
	}

}
