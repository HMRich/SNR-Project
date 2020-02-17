package application.moves;

import application.Anature;
import application.enums.MoveIds;

public class Tackle extends Move
{
	private int mDamageDone;
	
	public Tackle()
	{
		super(25, MoveIds.Tackle, true);
		mDamageDone = 30;
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
