package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class AntlerShot extends Move
{
	private static final long serialVersionUID = -7429832239175947913L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.getStats().decreaseTempDefense();
		source.getStats().increaseTempAttack();
		target.applyDamage(calculateDamage(source, target, false));

	}

}
