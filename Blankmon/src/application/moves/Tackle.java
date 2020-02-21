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
		super(mDamageDone, MoveIds.Tackle, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Fighting);
		

	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
