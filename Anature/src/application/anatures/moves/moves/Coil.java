package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Coil extends Move
{
	private static final long serialVersionUID = -9080529416887418112L;

	@Override
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		
		if(Math.random() < 0.33)
		{
			target.getStats().decreaseTempSpeed();
		}
	}
}
