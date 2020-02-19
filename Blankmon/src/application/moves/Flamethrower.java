package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class Flamethrower extends Move
{
	private static int mDamageDone = 50;
	private static double mAccuracyStat = 75; 
	private static boolean mDoesDamage = true;
	private static boolean mIsPhysicalAttack = false;
	
	public Flamethrower()
	{
		super(mDamageDone, MoveIds.Tackle, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Fire);
		
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);
	}
}
