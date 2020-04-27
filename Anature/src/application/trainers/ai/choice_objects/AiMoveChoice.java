package application.trainers.ai.choice_objects;

import application.anatures.moves.MoveCore;
import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.trainers.ai.choice_objects.AiFinalChoice;

public class AiMoveChoice extends AiFinalChoice implements AiChoiceObject<MoveCore>
{
	private MoveCore mMove;

	public AiMoveChoice(AiChoice choice, MoveCore moveCore)
	{
		super(choice);
		setMove(moveCore);
	}

	private void setMove(MoveCore moveCore)
	{
		if(moveCore != null)
		{
			mMove = moveCore;
		}

		else
		{
			LoggerController.logEvent(LoggingTypes.Error, "IllegalArgumentException in AiMoveChoice.java, Method: setMove(Move move), choice value was null.");
		}
	}

	private MoveCore getMove()
	{
		return mMove;
	}

	@Override
	public MoveCore getChoiceObject()
	{
		return getMove();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
