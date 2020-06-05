package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.interfaces.IAnature;

public class Electrocution extends Move
{

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.takeDamage(calculateDamage(source, target, false));
		target.updateStatus(StatusEffects.Paralysis);
		target.getStats().decreaseTempDefense();
	}
	
}
