package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class ElectroSonic extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(calculateDamage(source, target, false));
		if(Math.random() < 0.10) {
			target.updateStatus(StatusEffects.Paralysis);
		}
	}

}
