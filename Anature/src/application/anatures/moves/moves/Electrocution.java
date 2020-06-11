package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;

public class Electrocution extends Move
{
	private static final long serialVersionUID = 5030180131481086156L;

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.updateStatus(StatusEffects.Paralysis);
		target.getStats().decreaseTempDefense();
	}

}
