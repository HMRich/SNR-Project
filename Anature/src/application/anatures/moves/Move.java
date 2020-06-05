package application.anatures.moves;

import java.util.Random;

import application.anatures.Anature;
import application.enums.MoveIds;
import application.enums.Stat;
import application.enums.Type;
import application.enums.TypeEffectiveness;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;

public class Move implements IMove
{
	private static Random randomObject = new Random();
	private String mName;
	private MoveIds mMoveId;
	private Type mType;
	private boolean mDoesDamage;
	private boolean mIsPhysicalAttack;
	private int mTotalMovePoints;
	private int mMovePower;
	private int mAccuracy;

	protected Move()
	{
		mName = "";
		mMoveId = MoveIds.NullMove;
		mType = Type.NotSet;
		mDoesDamage = false;
		mIsPhysicalAttack = false;
		mTotalMovePoints = -1;
		mMovePower = -1;
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

	void setMovePower(int movePower)
	{
		if(movePower < 0)
		{
			throw new IllegalArgumentException("Passed value \"movePower\" was less than 0.");
		}

		mMovePower = movePower;
	}

	void setAccuracy(int accuracy)
	{
		if(accuracy < 0)
		{
			throw new IllegalArgumentException("Passed value \"accuracy\" was less than 0.");
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

	public int getMovePower()
	{
		return mMovePower;
	}

	public double getAccuracy()
	{
		return mAccuracy;
	}

	/*
	 * PROTECTED METHODS
	 */

	protected int calculateDamage(Anature source, Anature target, boolean isSpecialMove)
	{
		IStats sourceStats = source.getStats();
		IStats targetStats = target.getStats();

		double levelCalculation = ((2.0 * (double) sourceStats.getLevel()) / 5.0) + 2.0;
		double movePower = (double) getMovePower();
		double attackStat = isSpecialMove ? sourceStats.getTotalStat(Stat.SpecialAttack) : sourceStats.getTotalStat(Stat.Attack);
		double defenseStat = isSpecialMove ? (double) targetStats.getTotalStat(Stat.SpecialDefense) : (double) targetStats.getTotalStat(Stat.Defense);
		double typeMatchCalculation = source.getTypes()
				.contains(getType()) ? 1.5 : 1.0;
		double typeAdvantageCalculation = TypeEffectiveness.typeEffectiveness(this, target)
				.getEffectivenes();
		double randomNumberCalculation = randomObject.nextInt(16) + 85;

		return (int) ((((levelCalculation * movePower * attackStat / defenseStat) / 50.0) + 2.0) * typeMatchCalculation * typeAdvantageCalculation
				* randomNumberCalculation / 100.0);
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean canCreate()
	{
		if(getName().isEmpty())
		{
			throw new IllegalStateException("The \"name\" variable was never set during construction.");
		}

		if(getMoveId().equals(MoveIds.NullMove))
		{
			throw new IllegalStateException("The \"moveId\" variable was never set during construction.");
		}

		if(getType().equals(Type.NotSet))
		{
			throw new IllegalStateException("The \"type\" variable was never set during construction.");
		}

		if(getTotalMovePoints() == -1)
		{
			throw new IllegalStateException("The \"totalMovePoints\" variable was never set during construction.");
		}

		if(getMovePower() == -1)
		{
			throw new IllegalStateException("The \"movePower\" variable was never set during construction.");
		}

		if(getAccuracy() == -1)
		{
			throw new IllegalStateException("The \"accuracy\" variable was never set during construction.");
		}
		return true;
	}

	/*
	 * PUBLIC METHODS
	 */

	public void activateMove(Anature source, Anature target)
	{
		throw new IllegalStateException("This method should not be called.");
	}

}