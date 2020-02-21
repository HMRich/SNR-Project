package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class Tackle extends Move
{
	private int mDamageDone;
	
	public Tackle()
	{
		super(25, MoveIds.Tackle, true, true, 100, Type.Normal);
		mDamageDone = 30;
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
