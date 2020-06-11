package application.anatures.movesets;

import application.enums.BattleAnimationType;
import application.interfaces.IMove;
import test.helpers.TestObjects;

public class MoveSetTestable extends MoveSet
{
	private static final long serialVersionUID = -7932645834027803262L;

	public MoveSetTestable(IMove move1, IMove move2, IMove move3, IMove move4)
	{
		super(move1, move2, move3, move4);
	}

	public MoveSetTestable()
	{
		super(TestObjects.getDefaultTackle(), TestObjects.getDefaultTackle(), TestObjects.getDefaultTackle(), TestObjects.getDefaultTackle());
	}

	private boolean mRefreshAllMovePointsWasCalled;
	private boolean mGetMoveAnimationTypeWasCalled;

	@Override
	public void refreshAllMovePoints()
	{
		mRefreshAllMovePointsWasCalled = true;
	}

	@Override
	public BattleAnimationType getMoveAnimationType(int moveIdex)
	{
		mGetMoveAnimationTypeWasCalled = true;
		return null;
	}

	public boolean refreshAllMovePointsWasCalled()
	{
		return mRefreshAllMovePointsWasCalled;
	}

	public boolean getMoveAnimationTypeWasCalled()
	{
		return mGetMoveAnimationTypeWasCalled;
	}

}
