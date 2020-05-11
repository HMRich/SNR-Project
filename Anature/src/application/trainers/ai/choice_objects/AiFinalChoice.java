package application.trainers.ai.choice_objects;
import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;

public class AiFinalChoice
{
	private AiChoice mChoice;
	
	public AiFinalChoice(AiChoice choice)
	{
		setChoice(choice);
	}
	
	private void setChoice(AiChoice choice)
	{
		if(choice != null)
		{
			mChoice = choice;
		}
		
		else
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in AiMoveChoice.java, Method: setChoice(AiChoice choice), choice value was null.");
		}
	}
	
	public AiChoice getChoice()
	{
		return mChoice;
	}
	
	
}
