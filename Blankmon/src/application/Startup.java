package application;

import java.io.IOException;

import application.controllers.BattleController;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.SceneType;
import application.enums.Species;
import application.enums.TrainerIds;
import application.trainers.Trainer;
import application.trainers.TrainerBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Startup extends Application
{
	private LoggerStartUp mLogger;
	private static Stage mStage, mLoggerStage;
	private static Player mPlayer;
	private static Trainer mTrainer;
	private static EventHandler<KeyEvent> mKeyListener;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mPlayer = new Player(null); // TODO Remove Null
		mTrainer = null;
		mKeyListener = new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				if(event.getText().compareTo("`") == 0)
				{
					mLogger.toggleWindow();
				}
			}
		};

		mStage = primaryStage;
		mStage.setMinWidth(640);
		mStage.setMinHeight(360);

		mStage.setWidth(1280);
		mStage.setHeight(720);

		changeScene(SceneType.Intro);
		mStage.show();

		mLogger = new LoggerStartUp(mKeyListener);
		mLogger.init();
		mLoggerStage = new Stage();
		mLogger.start(mLoggerStage);
		mStage.setOnCloseRequest(event -> System.exit(-3000));
		LoggerController.logEvent(LoggingTypes.Default, "The logger has started.");
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void changeScene(SceneType type)
	{
		double width = mStage.getWidth();
		double height = mStage.getHeight();

		try
		{
			switch(type)
			{
				case Intro:
					FXMLLoader introLoader = new FXMLLoader(Startup.class.getResource("/application/views/IntroView.fxml"));
					Parent introRoot = introLoader.load();
					Scene intro = new Scene(introRoot);
					intro.setOnKeyReleased(mKeyListener);
					
					mStage.setScene(intro);
					break;

				case Battle:
					FXMLLoader loader = new FXMLLoader(Startup.class.getResource("/application/views/BattleView.fxml"));
					Parent root = loader.load();
					Scene scene = new Scene(root);
					scene.setOnKeyReleased(mKeyListener);

					BattleController controller = (BattleController) loader.getController();
					controller.setUpBindingsAndElements(scene);
					controller.updateElements(mPlayer, mTrainer);

					mStage.setScene(scene);
					break;
			}
		}

		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}

		mStage.setWidth(width);
		mStage.setHeight(height);
	}

	public static void createDemo()
	{
		mPlayer.addAnatures(AnatureBuilder.createAnature(Species.Null, 15));
		
		Anature second = AnatureBuilder.createAnature(Species.Null, 12);
		second.setName("Other Null");
		mPlayer.addAnatures(second);
		mTrainer = TrainerBuilder.createTrainer(TrainerIds.Kelly, 1, 13, 17);

		changeScene(SceneType.Battle);
	}
	
	public static String getPlayerName()
	{
		return mPlayer.getName();
	}
}
