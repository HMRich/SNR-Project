package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class Tackle extends Move
{
	private static int mDamageDone = 30;
	private static double mAccuracyStat = 75; 
	private static boolean mDoesDamage = true;
	private static boolean mIsPhysicalAttack = true;
	
	public Tackle()
	{
		super("Tackle", 15, MoveIds.Tackle, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Normal);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
