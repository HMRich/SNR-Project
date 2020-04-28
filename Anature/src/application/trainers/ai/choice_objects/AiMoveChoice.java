package application.trainers.ai.choice_objects;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.interfaces.AiChoiceObject;
import application.interfaces.IMove;

public class AiMoveChoice extends AiFinalChoice implements AiChoiceObject<IMove>
{
	private IMove mMove;

	public AiMoveChoice(AiChoice choice, IMove move)
	{
		super(choice);
		setMove(move);
	}

	private void setMove(IMove move)
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

	private IMove getMove()
	{
		return mMove;
	}

	@Override
	public IMove getChoiceObject()
	{
		return getMove();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
