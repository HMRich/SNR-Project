package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoggerStartUp extends Application
{
	private static EventHandler<KeyEvent> mKeyListener;
	private Stage mStage;
	private boolean isShown;
	private double x, y;

	public LoggerStartUp(EventHandler<KeyEvent> keyListener)
	{
		mKeyListener = keyListener;
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader loggerLoader = new FXMLLoader(getClass().getResource("/application/views/LoggerView.fxml"));
		Parent root = loggerLoader.load();
		Scene logger = new Scene(root);
		logger.setOnKeyReleased(mKeyListener);
		primaryStage.setScene(logger);

		mStage = primaryStage;
		isShown = false;
		x = mStage.getX();
		y = mStage.getY();

		mStage.setOnCloseRequest(event -> close());
	}

	public void toggleWindow()
	{
		if(isShown)
		{
			mStage.hide();
			x = mStage.getX();
			y = mStage.getY();
		}

		else
		{
			mStage.show();
			mStage.setX(x);
			mStage.setY(y);
		}
		isShown = !isShown;
	}

	public void close()
	{
		x = mStage.getX();
		y = mStage.getY();
		isShown = !isShown;
	}

}
