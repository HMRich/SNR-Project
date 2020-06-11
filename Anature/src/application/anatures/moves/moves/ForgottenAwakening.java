package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class ForgottenAwakening extends Move
{
	private static final long serialVersionUID = 7987668078562046069L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		source.getStats().increaseTempAttack();
		source.getStats().increaseTempDefense();
	}

}
