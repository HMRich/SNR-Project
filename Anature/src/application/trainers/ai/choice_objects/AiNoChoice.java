package application.trainers.ai.choice_objects;

import application.anatures.moves.Move;
import application.anatures.moves.Struggle;
import application.enums.AiChoice;

public class AiNoChoice extends AiFinalChoice implements AiChoiceObject<Move>
{
	private Move mMove;
	
	public AiNoChoice()
	{
		super(AiChoice.No_Choice);
		mMove = new Struggle();
	}

	@Override
	public Move getChoiceObject()
	{
		return mMove;
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
	
}
