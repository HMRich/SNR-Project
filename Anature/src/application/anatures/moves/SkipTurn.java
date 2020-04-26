package application.anatures.moves;

import application.anatures.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public class SkipTurn extends Move
{
	private static int mDamageDone = 0;
	private static double mAccuracyStat = 100;
	private static boolean mDoesDamage = false;
	private static boolean mIsPhysicalAttack = false;

	public SkipTurn()
	{
		super("Skip Turn", 0, MoveIds.Skip_Turn, false, false, 100, Type.Normal); 
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		System.out.println("Turn skipped"); 
	}
}
