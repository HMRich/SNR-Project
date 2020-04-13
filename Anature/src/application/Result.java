package application;

import java.util.ArrayList;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;

public abstract class Result
{
	private ArrayList<String> mDialogue;

	public Result(ArrayList<String> dialogue)
	{
		if(dialogue == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Null Dialogue was passed in Result ArrayList constructor.");
			dialogue = new ArrayList<String>();
			dialogue.add("ERROR: No result dialogue");
		}
		
		mDialogue = dialogue;
	}
	
	public Result(String dialogue)
	{
		if(dialogue == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Null Dialogue was passed in Result String constructor.");
			throw new IllegalArgumentException("Null Dialogue was passed in Result String constructor.");
		}

		mDialogue = new ArrayList<String>();
		mDialogue.add(dialogue);
	}

	public final ArrayList<String> getDialogue()
	{
		return mDialogue;
	}
}
