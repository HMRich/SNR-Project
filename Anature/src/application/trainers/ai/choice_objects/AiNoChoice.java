package application.trainers.ai.choice_objects;

import application.anatures.moves.MoveCollection;
import application.enums.AiChoice;
import application.enums.MoveIds;
import application.interfaces.AiChoiceObject;
import application.interfaces.IMove;

public class AiNoChoice extends AiFinalChoice implements AiChoiceObject<IMove>
{
	private IMove mMove;

	public AiNoChoice()
	{
		super(AiChoice.No_Choice);
		mMove = MoveCollection.getMoveContext(MoveIds.Flail);
	}

	@Override
	public IMove getChoiceObject()
	{
		return mMove;
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}

}
