package application.ai.choice_objects;

import application.enums.AiChoice;

public class AiNoChoice extends AiFinalChoice implements AiChoiceObject<NullObject>
{
	public AiNoChoice()
	{
		super(AiChoice.No_Choice);
	}

	@Override
	public NullObject getChoiceObject()
	{
		return null;
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
	
}

class NullObject
{
	
}
