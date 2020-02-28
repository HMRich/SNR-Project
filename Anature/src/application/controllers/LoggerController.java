package application.controllers;

import java.time.LocalDateTime;

import application.enums.LoggingTypes;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoggerController
{
	@FXML private GridPane mLoggingGridPane;
	@FXML private HBox MRadipHBox;
	@FXML private RadioButton mMouseLocationRadio, mErrorLoggingRadio, mMiscLoggingRadio;
	@FXML private TextArea mLoggingTextArea;

	private static StringProperty mLoggingText;
	private static boolean mErrorLogging, mMiscLogging;
	private static BooleanProperty mMouseLocation;

	public void initialize()
	{
		mLoggingText = new SimpleStringProperty("");
		mLoggingTextArea.textProperty().bind(mLoggingText);
		
		mMouseLocation = new SimpleBooleanProperty();
		mMouseLocation.bind(mMouseLocationRadio.selectedProperty());
		
		onRadioToggle();
	}

	public void onRadioToggle()
	{
		mErrorLogging = mErrorLoggingRadio.isSelected();
		mMiscLogging = mMiscLoggingRadio.isSelected();
	}

	public synchronized static void logEvent(LoggingTypes logType, String toLog)
	{
		if(toLog == null)
		{
			throw new IllegalArgumentException("toLog was null.");
		}

		switch(logType)
		{
			case Mouse:
				if(!mMouseLocation.get())
				{
					return;
				}
				break;

			case Default:
				if(!mErrorLogging)
				{
					return;
				}
				break;

			case Misc:
				if(!mMiscLogging)
				{
					return;
				}
				break;

			default:
				throw new IllegalArgumentException("logType was ");

		}

		mLoggingText.set(mLoggingText.get() + LocalDateTime.now().toString().replace("T", " ").substring(0, 19) + ": " + toLog + "\n");
	}
	
	public static boolean isMouseLocationEnabled()
	{
		return mMouseLocation.get();
	}
}
