package application;

import java.io.IOException;

import application.controllers.BattleController;
import application.enums.SceneType;
import application.enums.Species;
import application.enums.TrainerIds;
import application.trainers.Trainer;
import application.trainers.TrainerBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.logger.GameLogger;

public class Startup extends Application
{
	private static Stage mStage;
	private static Player mPlayer;
	private static Trainer mTrainer;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mPlayer = new Player(null); // TODO Remove Null
		mTrainer = null;
		
		mStage = primaryStage;
		mStage.setMinWidth(640);
		mStage.setMinHeight(360);
		
		mStage.setWidth(1280);
		mStage.setHeight(720);
		
		changeScene(SceneType.Intro);
		mStage.show();
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
					mStage.setScene(intro);
					break;
				
				case Battle:
					FXMLLoader loader = new FXMLLoader(Startup.class.getResource("/application/views/BattleView.fxml"));
					Parent root = loader.load();
					Scene scene = new Scene(root);				
					
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
		mTrainer = TrainerBuilder.createTrainer(TrainerIds.Kelly, 1, 13, 17);
		
		changeScene(SceneType.Battle);
	}
}
