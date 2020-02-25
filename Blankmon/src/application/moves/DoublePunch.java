package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class DoublePunch extends Move
{
	private static int mDamageDone = 25;
	private static double mAccuracyStat = 100;
	private static boolean mDoesDamage = true;
	private static boolean mIsPhysicalAttack = true;

	public DoublePunch()
	{
		super("Double Punch", 30, MoveIds.Double_Punch, mDoesDamage, mIsPhysicalAttack, mAccuracyStat, Type.Fighting);
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		target.takeDamage(mDamageDone);

	}
}
