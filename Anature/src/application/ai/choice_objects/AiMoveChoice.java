package application.ai.choice_objects;

import application.ai.choice_objects.AiFinalChoice;
import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.moves.Move;

public class AiMoveChoice extends AiFinalChoice implements AiChoiceObject<Move>
{
	private Move mMove;

	public AiMoveChoice(AiChoice choice, Move move)
	{
		super(choice);
		setMove(move);
	}

	private void setMove(Move move)
	{
		if(move != null)
		{
			mMove = move;
		}

		else
		{
			LoggerController.logEvent(LoggingTypes.Error, "IllegalArgumentException in AiMoveChoice.java, Method: setMove(Move move), choice value was null.");
		}
	}

	private Move getMove()
	{
		return mMove;
	}

	@Override
	public Move getChoiceObject()
	{
		return getMove();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
