package application;

import java.io.IOException;

import application.controllers.BattleController;
import application.enums.SceneType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application
{
	private static Stage mStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mStage = primaryStage;
		mStage.setMinWidth(640);
		mStage.setMinHeight(360);
		
		mStage.setWidth(1280);
		mStage.setHeight(720);
		
		changeScene(SceneType.Battle);
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
				case Battle:
					FXMLLoader loader = new FXMLLoader(Startup.class.getResource("/application/views/BattleView.fxml"));
					Parent root = (Parent) loader.load();
					Scene scene = new Scene(root);				
					
					BattleController controller = (BattleController) loader.getController();
					controller.setUpBindingsAndElements(scene);
					
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
}
