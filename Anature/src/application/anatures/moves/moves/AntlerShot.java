package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class AntlerShot extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempDefense();
		source.getStats().increaseTempAttack();
		target.applyDamage(calculateDamage(source, target, false));

	}

}
