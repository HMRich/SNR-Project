package application.trainers.ai.choice_objects;

import application.anatures.moves.MoveCore;
import application.anatures.moves.moves.Flail;
import application.enums.AiChoice;

public class AiNoChoice extends AiFinalChoice implements AiChoiceObject<MoveCore>
{
	private MoveCore mMove;
	
	public AiNoChoice()
	{
		super(AiChoice.No_Choice);
		mMove = new Flail();
	}

	@Override
	public MoveCore getChoiceObject()
	{
		return mMove;
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
	
}
