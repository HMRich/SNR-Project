package application.moves;

import application.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public abstract class Move
{
	private String mName;
	private int mTotalMovePoints;
	private MoveIds mMoveId;
	private boolean mDoesDamage;
	private boolean mIsPhysicalAttack;
	private double mAccuracyStat;
	private Type mType;

	public Move(String name, int totalMovePoints, MoveIds moveId, boolean doesDamage, boolean isPhysicalAttack, double accuracyStat, Type enumType)
	{
		mName = name;
		mTotalMovePoints = totalMovePoints;
		mMoveId = moveId;
		mDoesDamage = doesDamage;
		mIsPhysicalAttack = isPhysicalAttack;
		mAccuracyStat = accuracyStat;
		mType = enumType;
	}
	
	public String getName()
	{
		return mName;
	}

	public int getTotalMovePoints()
	{
		return mTotalMovePoints;
	}

	public MoveIds getMoveId()
	{
		return mMoveId;
	}

	public boolean doesDamage()
	{
		return mDoesDamage;
	}

	public double getAccuracy() 
	{
		return mAccuracyStat;
	}
	
	public Type getType() 
	{
		return mType;
	}
	
	public boolean isPhysicalAttack()
	{
		return mIsPhysicalAttack;
	}
	
	public abstract void activateMove(Anature source, Anature target);
}