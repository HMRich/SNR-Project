package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.interfaces.IAnature;

public class Coil extends Move
{
	public void activateMove(IAnature source, IAnature target)
	{
		target.applyDamage(calculateDamage(source, target, false));
		
		if(Math.random() < 0.33)
		{
			target.getStats().decreaseTempSpeed();
		}
	}
}
