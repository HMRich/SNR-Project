package application.anatures.moves;

import application.anatures.moves.moves.DoublePunch;
import application.anatures.moves.moves.Flail;
import application.anatures.moves.moves.Flamethrower;
import application.anatures.moves.moves.Grumble;
import application.anatures.moves.moves.NullMove;
import application.anatures.moves.moves.PocketSand;
import application.anatures.moves.moves.SkipTurn;
import application.anatures.moves.moves.Tackle;
import application.enums.MoveIds;
import application.enums.Type;
import application.interfaces.IBuilder;

public class Move<M extends MoveBase> implements IBuilder<M>
{
	private M mMove;

	public Move()
	{
		generateNewMove();
	}

	/*
	 * PUBLIC SETS
	 */

	public Move<M> withName(String name)
	{
		mMove.setName(name);
		return this;
	}

	public Move<M> withMoveId(MoveIds moveId)
	{
		mMove.setMoveId(moveId);
		return this;
	}

	public Move<M> withType(Type type)
	{
		mMove.setType(type);
		return this;
	}

	public Move<M> doesDamage(boolean doesDamage)
	{
		mMove.setDoesDamage(doesDamage);
		return this;
	}

	public Move<M> isPhysicalAttack(boolean isPhysicalAttack)
	{
		mMove.setIsPhysicalAttack(isPhysicalAttack);
		return this;
	}

	public Move<M> withTotalMovePoints(int totalMovePoints)
	{
		mMove.setTotalMovePoints(totalMovePoints);
		return this;
	}

	public Move<M> withAccuracy(double accuracy)
	{
		mMove.setAccuracy(accuracy);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public M create()
	{
		if(buildIsComplete())
		{
			M moveToReturn = mMove;

			generateNewMove();

			return moveToReturn;
		}

		throw new IllegalStateException("All the builder variables need to have a value before you create a Move.");
	}

	/*
	 * PRIVATE METHODS
	 */

	@SuppressWarnings("unchecked")
	private void generateNewMove()
	{
		if(mMove instanceof Grumble)
		{
			mMove = (M) new Grumble();
		}

		else if(mMove instanceof DoublePunch)
		{
			mMove = (M) new DoublePunch();
		}

		else if(mMove instanceof Flamethrower)
		{
			mMove = (M) new Flamethrower();
		}

		else if(mMove instanceof PocketSand)
		{
			mMove = (M) new PocketSand();
		}

		else if(mMove instanceof SkipTurn)
		{
			mMove = (M) new SkipTurn();
		}

		else if(mMove instanceof Flail)
		{
			mMove = (M) new Flail();
		}

		else if(mMove instanceof Tackle)
		{
			mMove = (M) new Tackle();
		}
		
		else if(mMove instanceof NullMove)
		{
			mMove = (M) new NullMove();
		}
		
		throw new IllegalStateException("The type \"M\" was not found. Please add it to the list.");
	}

	private boolean buildIsComplete()
	{
		return mMove.canCreate();
	}

}
