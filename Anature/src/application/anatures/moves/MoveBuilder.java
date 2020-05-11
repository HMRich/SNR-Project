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

public class MoveBuilder<M extends Move> implements IBuilder<M>
{
	private M mMove;
	private MoveIds mMoveId;

	public MoveBuilder(MoveIds moveId)
	{
		setMoveType(moveId);
		generateNewMove();
	}

	/*
	 * PRIVATE SETS
	 */

	private void setMoveType(MoveIds moveId)
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

	/*
	 * PUBLIC SETS
	 */

	public MoveBuilder<M> withName(String name)
	{
		mMove.setName(name);
		return this;
	}

	public MoveBuilder<M> withType(Type type)
	{
		mMove.setType(type);
		return this;
	}

	public MoveBuilder<M> doesDamage(boolean doesDamage)
	{
		mMove.setDoesDamage(doesDamage);
		return this;
	}

	public MoveBuilder<M> isPhysicalAttack(boolean isPhysicalAttack)
	{
		mMove.setIsPhysicalAttack(isPhysicalAttack);
		return this;
	}

	public MoveBuilder<M> withTotalMovePoints(int totalMovePoints)
	{
		mMove.setTotalMovePoints(totalMovePoints);
		return this;
	}

	public MoveBuilder<M> withAccuracy(double accuracy)
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
		switch(mMoveId)
		{
			case Grumble:
				mMove = (M) new Grumble();
				break;

			case Double_Punch:
				mMove = (M) new DoublePunch();
				break;

			case Flamethrower:
				mMove = (M) new Flamethrower();
				break;

			case Pocket_Sand:
				mMove = (M) new PocketSand();
				break;

			case Skip_Turn:
				mMove = (M) new SkipTurn();
				break;

			case Flail:
				mMove = (M) new Flail();
				break;

			case Tackle:
				mMove = (M) new Tackle();
				break;

			case NullMove:
				mMove = (M) new NullMove();
				break;

			default:
				throw new IllegalStateException("The variable \"moveId\" was not found. Please add it to the list.");
		}

		mMove.setMoveId(mMoveId);
	}

	private boolean buildIsComplete()
	{
		return mMove.canCreate();
	}

}
