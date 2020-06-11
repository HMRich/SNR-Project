package application.trainers.ai.choice_objects;

import application.anatures.Anature;
import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.interfaces.AiChoiceObject;

public class AiSwitchChoice extends AiFinalChoice implements AiChoiceObject<Anature>
{
	private Anature mAnature;

	public AiSwitchChoice(Anature anatureBase)
	{
		super(AiChoice.Switch_Anature);
		setAnature(anatureBase);
	}

	private void setAnature(Anature anatureBase)
	{
		if(anatureBase != null)
		{
			mAnature = anatureBase;
		}

		else
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in AiMoveChoice.java, Method: setAnature(Anature anature), potion value was null.");
		}
	}

	private Anature getAnature()
	{
		return mAnature;
	}

	@Override
	public Anature getChoiceObject()
	{
		return getAnature();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
