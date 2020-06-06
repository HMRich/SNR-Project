package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Electrocution extends Move
{
	private static final long serialVersionUID = 5030180131481086156L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		target.updateStatus(StatusEffects.Paralysis);
		target.getStats().decreaseTempDefense();
	}

}
