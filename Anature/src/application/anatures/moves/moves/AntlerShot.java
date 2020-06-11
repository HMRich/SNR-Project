package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class AntlerShot extends Move
{
	private static final long serialVersionUID = -7429832239175947913L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.getStats().decreaseTempDefense();
		source.getStats().increaseTempAttack();
		target.applyDamage(calculateDamage(source, target, false));

	}

}
