package application;

import java.util.ArrayList;

import application.abillities.AbilityResult;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.MoveIds;
import application.moves.Move;
import application.moves.MovePool;

public class MoveResult extends Result
{
	private Move mMove;
	private int mMoveIndex; //NOTE: a moveIndex of -1 means that the move was skipped
	private boolean mIsPlayer;
	private AbilityResult mAbilityResult;
	
	public MoveResult(ArrayList<String> moveDialogue, AbilityResult abilityResult, int moveIndex, boolean isPlayer, Move move)
	{
		super(moveDialogue);
		
		if(abilityResult == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "abilityResult in MoveResult constructor was null.");
			abilityResult = new AbilityResult(new ArrayList<String>(), false);
		}
		
		else if(move == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "move in MoveResult constructor was null.");
			move = MovePool.getMove(MoveIds.Tackle);
		}
		
		mAbilityResult = abilityResult;
		mMove = move;
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
	
	public Move getMove()
	{
		return mMove;
	}
	
	public AbilityResult getAbilityResult() 
	{
		return mAbilityResult;
	}
}
