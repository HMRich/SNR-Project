package application.controllers.results;

import java.util.ArrayList;

import application.anatures.moves.MoveCore;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.MoveIds;
import application.pools.MovePool;

public class MoveResult extends Result
{
	private MoveCore mMove;
	private int mMoveIndex; //NOTE: a moveIndex of -1 means that the move was skipped
	private boolean mIsPlayer;
	private AbilityResult mAbilityResult;
	
	public MoveResult(ArrayList<String> moveDialogue, AbilityResult abilityResult, int moveIndex, boolean isPlayer, MoveCore moveCore)
	{
		super(moveDialogue);
		
		if(abilityResult == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "abilityResult in MoveResult constructor was null.");
			abilityResult = new AbilityResult(new ArrayList<String>(), false);
		}
		
		else if(moveCore == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "move in MoveResult constructor was null.");
			moveCore = MovePool.getMove(MoveIds.Tackle);
		}
		
		mAbilityResult = abilityResult;
		mMove = moveCore;
		mIsPlayer = isPlayer;
		mMoveIndex = moveIndex;
	}

	public int getMoveIndex()
	{
		return mMoveIndex;
	}

	public boolean isPlayer()
	{
		return mIsPlayer;
	}
	
	public MoveCore getMove()
	{
		return mMove;
	}
	
	public AbilityResult getAbilityResult() 
	{
		return mAbilityResult;
	}
}
