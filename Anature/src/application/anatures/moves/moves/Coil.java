package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;

public class Coil extends Move
{
	public void activateMove(Anature source, Anature target)
	{
		target.applyDamage(calculateDamage(source, target, false));

		if(Math.random() < 0.33)
		{
			target.getStats().decreaseTempSpeed();
		}
	}
}
