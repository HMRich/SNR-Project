package application.anatures.moves;

import application.enums.MoveIds;
import application.enums.Type;
import application.interfaces.IAnature;
import application.interfaces.IMove;

public class MoveBase implements IMove
{
	private String mName;
	private MoveIds mMoveId;
	private Type mType;
	private boolean mDoesDamage;
	private boolean mIsPhysicalAttack;
	private int mTotalMovePoints;
	private double mAccuracy;

	protected MoveBase()
	{
		mName = "";
		mMoveId = MoveIds.NullMove;
		mType = Type.NotSet;
		mDoesDamage = false;
		mIsPhysicalAttack = false;
		mTotalMovePoints = -1;
		mAccuracy = -1;
	}

	/*
	 * PACKAGE SETS
	 */

	void setName(String name)
	{
		if(name == null)
		{
			throw new IllegalArgumentException("Passed value \"name\" was null.");
		}

		if(name.trim()
				.isEmpty())
		{
			throw new IllegalArgumentException("Passed value \"name\" was an empty string.");
		}

		mName = name;
	}

	void setMoveId(MoveIds moveId)
	{
		if(moveId == null)
		{
			throw new IllegalArgumentException("Passed value \"moveId\" was null.");
		}

		if(moveId.equals(MoveIds.NullMove))
		{
			throw new IllegalArgumentException("Passed value \"moveId\" was equal to " + moveId.toString() + ".");
		}

		mMoveId = moveId;
	}

	void setType(Type type)
	{
		if(type == null)
		{
			throw new IllegalArgumentException("Passed value \"type\" was null.");
		}

		if(type.equals(Type.NotSet))
		{
			throw new IllegalArgumentException("Passed value \"type\" was equal to " + type.toString() + ".");
		}

		mType = type;
	}

	void setDoesDamage(boolean doesDamage)
	{
		mDoesDamage = doesDamage;
	}

	void setIsPhysicalAttack(boolean isPhysicalAttack)
	{
		mIsPhysicalAttack = isPhysicalAttack;
	}

	void setTotalMovePoints(int totalMovePoints)
	{
		if(totalMovePoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"totalMovePoints\" was less than 0.");
		}

		mTotalMovePoints = totalMovePoints;
	}

	void setAccuracy(double accuracy)
	{
		if(accuracy < 0 || accuracy > 1)
		{
			throw new IllegalArgumentException("Passed value \"\" was less than zero or above 1.");
		}

		mAccuracy = accuracy;
	}

	/*
	 * PUBLIC GETS
	 */

	public String getName()
	{
		return mName;
	}

	public MoveIds getMoveId()
	{
		return mMoveId;
	}

	public Type getType()
	{
		return mType;
	}

	public boolean doesDamage()
	{
		return mDoesDamage;
	}

	public boolean isPhysicalAttack()
	{
		return mIsPhysicalAttack;
	}

	public int getTotalMovePoints()
	{
		return mTotalMovePoints;
	}

	public double getAccuracy()
	{
		return mAccuracy;
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		if(mName.isEmpty())
		{
			throw new IllegalStateException("The \"name\" variable was never set during construction.");
		}

		if(mMoveId.equals(MoveIds.NullMove))
		{
			throw new IllegalStateException("The \"moveId\" variable was never set during construction.");
		}

		if(mType.equals(Type.NotSet))
		{
			throw new IllegalStateException("The \"type\" variable was never set during construction.");
		}

		if(mTotalMovePoints == -1)
		{
			throw new IllegalStateException("The \"totalMovePoints\" variable was never set during construction.");
		}

		if(mAccuracy == -1)
		{
			throw new IllegalStateException("The \"accuracy\" variable was never set during construction.");
		}
		return true;
	}

	/*
	 * PUBLIC METHODS
	 */

	public void activateMove(IAnature source, IAnature target)
	{
		throw new IllegalStateException("This method should not be called.");
	}

}