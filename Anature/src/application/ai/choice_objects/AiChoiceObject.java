package application.ai.choice_objects;

import application.enums.AiChoice;

public interface AiChoiceObject<C>
{
	public C getChoiceObject();
	public AiChoice getAiChoice();
}
