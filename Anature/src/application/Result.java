package application;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;

public abstract class Result
{
	private String mDialogue;

	public Result(String dialogue)
	{
		if(dialogue == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Null Dialogue was passed in ItemResult.");
			throw new IllegalArgumentException("Null Dialogue was passed in ItemResult.");
		}

		mDialogue = dialogue;
	}

	public final String getDialogue()
	{
		return mDialogue;
	}
}
