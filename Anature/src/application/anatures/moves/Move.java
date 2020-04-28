package application.anatures.moves;

import application.Builder;
import application.anatures.moves.moves.NullMove;
import application.enums.MoveIds;
import application.enums.Type;

public class CreateMove<M extends MoveCore> implements Builder<MoveCore>
{
	private M mMove;

	public CreateMove()
	{
		generateNewMove();
	}

	/*
	 * PUBLIC SETS
	 */

	public CreateMove<M> withName(String name)
	{
		mMove.setName(name);
		return this;
	}

	public CreateMove<M> withMoveId(MoveIds moveId)
	{
		mMove.setMoveId(moveId);
		return this;
	}

	public CreateMove<M> withType(Type type)
	{
		mMove.setType(type);
		return this;
	}

	public CreateMove<M> doesDamage(boolean doesDamage)
	{
		mMove.setDoesDamage(doesDamage);
		return this;
	}

	public CreateMove<M> isPhysicalAttack(boolean isPhysicalAttack)
	{
		mMove.setIsPhysicalAttack(isPhysicalAttack);
		return this;
	}

	public CreateMove<M> withTotalMovePoints(int totalMovePoints)
	{
		mMove.setTotalMovePoints(totalMovePoints);
		return this;
	}

	public CreateMove<M> withAccuracy(double accuracy)
	{
		mMove.setAccuracy(accuracy);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public M create()
	{
		if(!buildIsComplete())
		{
			throw new IllegalStateException("All the builder variables need to have a value before you create a Move.");
		}

		M moveToReturn = mMove;

		generateNewMove();

		return moveToReturn;
	}

	/*
	 * PRIVATE METHODS
	 */

	@SuppressWarnings("unchecked")
	private void generateNewMove()
	{
		mMove = (M) new NullMove();
	}

	private boolean buildIsComplete()
	{
		return mMove.canCreate();
	}

}
