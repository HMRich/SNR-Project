package application.trainers.ai.choice_objects;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.interfaces.AiChoiceObject;
import application.interfaces.IAnature;

public class AiSwitchChoice extends AiFinalChoice implements AiChoiceObject<IAnature>
{
	private IAnature mAnature;

	public AiSwitchChoice(IAnature anatureBase)
	{
		super(AiChoice.Item_Consumed);
		setAnature(anatureBase);
	}

	private void setAnature(IAnature anatureBase)
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

	private IAnature getAnature()
	{
		return mAnature;
	}

	@Override
	public IAnature getChoiceObject()
	{
		return getAnature();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
