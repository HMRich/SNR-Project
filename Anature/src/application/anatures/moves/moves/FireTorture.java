package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class FireTorture extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		if(Math.random() < 0.20)
		{
			target.updateStatus(StatusEffects.Burn);
		}
	}

}
