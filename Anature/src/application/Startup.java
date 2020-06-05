package application;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import application.anatures.Anature;
import application.anatures.NewAnatureBuilder;
import application.controllers.BattleController;
import application.controllers.LoggerController;
import application.controllers.menus.AnatureSummaryController;
import application.controllers.menus.EvolutionController;
import application.controllers.overworld_cells.GrassTownController;
import application.controllers.overworld_cells.PathOneController;
import application.controllers.overworld_cells.RestStationController;
import application.controllers.overworld_cells.StarterTownController;
import application.controllers.results.BattleResult;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.enums.SceneType;
import application.enums.Species;
import application.enums.WarpPoints;
import application.interfaces.ITrainer;
import application.items.Anacube;
import application.models.PathOneModel;
import application.models.StarterTownModel;
import application.player.Player;
import application.pools.ItemPool;
import application.views.overworld_cells.AbstractCell;
import application.views.overworld_cells.GrassTownCell;
import application.views.overworld_cells.PathOneCell;
import application.views.overworld_cells.RestStationCell;
import application.views.overworld_cells.StarterTownCell;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Startup extends Application
{
	private static LoggerStartUp mLogger;
	private static Stage mStage, mLoggerStage;
	private static Player mPlayer;
	private static EventHandler<KeyEvent> mKeyListener;
	private static SceneType mLastSceneType, mCurrSceneType;
	private static Deque<SceneType> mSceneStack;

	private static Scene mAnatureSummaryView;
	private static AnatureSummaryController mAnatureSummaryController;

	private static EvolutionController mEvolutionController;

	private static StarterTownModel mStarterTownModel;
	private static StarterTownCell mStarterTownView;
	private static StarterTownController mStarterTownController;

	private static PathOneModel mPathOneModel;
	private static PathOneCell mPathOneView;
	private static PathOneController mPathOneController;

//	private static GrassTownModel mGrassTownModel;
	private static GrassTownCell mGrassTownView;
	private static GrassTownController mGrassTownController;

//	private static RestStationModel mRestStationGrassModel;
	private static RestStationCell mRestStationGrassView;
	private static RestStationController mRestStationGrassController;

	private static AbstractCell mCurrentCell;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mPlayer = new Player(null); // TODO Remove Null
		mKeyListener = new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent event)
			{
				if(event.getText()
						.compareTo("`") == 0)
				{
					mLogger.toggleWindow();
				}
			}
		};

		mStage = primaryStage;
		mStage.getIcons()
				.add(new Image(Startup.class.getResourceAsStream("/resources/images/Icon.png")));
		mStage.setTitle("Anature");

		mStage.setMinWidth(640);
		mStage.setMinHeight(360);

		mStage.setWidth(1280);
		mStage.setHeight(720);

		mLogger = new LoggerStartUp(mKeyListener);
		mLogger.init();
		mLoggerStage = new Stage();
		mLogger.start(mLoggerStage);
		mStage.setOnCloseRequest(event -> System.exit(-3000));
		LoggerController.logEvent(LoggingTypes.Error, "The logger has started.");

		mLastSceneType = SceneType.Intro;
		mCurrSceneType = SceneType.Intro;
		mSceneStack = new ArrayDeque<SceneType>();
		changeScene(SceneType.Intro, null);
		mStage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	public static void changeScene(SceneType type, WarpPoints warpPoint)
	{
		double width = mStage.getWidth();
		double height = mStage.getHeight();

		if(type == null)
		{
			type = mLastSceneType;
		}

		try
		{
			switch(type)
			{
				case Intro:
					FXMLLoader introLoader = new FXMLLoader(Startup.class.getResource("/application/views/IntroView.fxml"));
					Parent introRoot = introLoader.load();
					Scene intro = new Scene(introRoot);
					intro.setOnKeyReleased(mKeyListener);

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Intro");
					mStage.setScene(intro);
					break;

				case Anature_Summary:
					if(mAnatureSummaryView == null
							|| mAnatureSummaryController == null)
					{
						FXMLLoader summaryLoader = new FXMLLoader(Startup.class.getResource("/application/views/AnatureSummaryView.fxml"));
						Parent summaryRoot = summaryLoader.load();
						mAnatureSummaryView = new Scene(summaryRoot);
						mAnatureSummaryView.setOnKeyReleased(mKeyListener);

						mAnatureSummaryController = summaryLoader.getController();
						mAnatureSummaryController.updateBinds(mAnatureSummaryView);
					}

					mAnatureSummaryController.displayParty(mPlayer.getAnatures());

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Anature Summary");
					mStage.setScene(mAnatureSummaryView);
					break;

				case Evolution:
					FXMLLoader evolutionLoader = new FXMLLoader(Startup.class.getResource("/application/views/EvolutionView.fxml"));
					Parent evolutionRoot = evolutionLoader.load();
					Scene evolutionView = new Scene(evolutionRoot);
					evolutionView.setOnKeyReleased(mKeyListener);

					mEvolutionController = evolutionLoader.getController();
					mEvolutionController.updateBinds(evolutionView);

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Evolution Page");
					mStage.setScene(evolutionView);
					break;

				case Starter_Town:
					if(mStarterTownModel == null)
					{
						mStarterTownModel = new StarterTownModel();
					}

					if(mStarterTownView == null)
					{
						mStarterTownView = new StarterTownCell(mLogger);
					}

					if(mStarterTownController == null)
					{
						mStarterTownController = new StarterTownController(mLogger, mStarterTownView, mStarterTownModel, mPlayer);
					}

					Scene townScene = mStarterTownView.getScene();

					if(mCurrentCell != null)
					{
						mStarterTownView.setKeysPressed(mCurrentCell.getKeysPressed());
					}

					mStarterTownController.movePlayer(warpPoint);
					mCurrentCell = mStarterTownView;

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Starter Town");
					mStage.setScene(townScene);
					break;

				case Path_1:
					if(mPathOneModel == null)
					{
						mPathOneModel = new PathOneModel();
					}

					if(mPathOneView == null)
					{
						mPathOneView = new PathOneCell(mLogger);
					}

					if(mPathOneController == null)
					{
						mPathOneController = new PathOneController(mLogger, mPathOneView, mPathOneModel, mPlayer);
					}

					Scene pathOneScene = mPathOneView.getScene();

					if(mCurrentCell != null)
					{
						mPathOneView.setKeysPressed(mCurrentCell.getKeysPressed());
					}

					mPathOneController.movePlayer(warpPoint);
					mCurrentCell = mPathOneView;

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Path 1");
					mStage.setScene(pathOneScene);
					break;

				case Grass_Town:
//					if(mGrassTownModel == null)
//					{
//						mGrassTownModel = new StarterTownModel();
//					}

					if(mGrassTownView == null)
					{
						mGrassTownView = new GrassTownCell(mLogger);
					}

					if(mGrassTownController == null)
					{
						mGrassTownController = new GrassTownController(mLogger, mGrassTownView, mPlayer);
					}

					if(mCurrentCell != null)
					{
						mGrassTownView.setKeysPressed(mCurrentCell.getKeysPressed());
					}

					Scene grassTownScene = mGrassTownView.getScene();
					mGrassTownController.movePlayer(warpPoint);
					mCurrentCell = mGrassTownView;

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Grass Town");
					mStage.setScene(grassTownScene);
					break;

				case Rest_Station_Grass_Town:
//					if(mRestStationGrassModel == null)
//					{
//						mRestStationGrassModel = new RestStationModel();
//					}

					if(mRestStationGrassView == null)
					{
						mRestStationGrassView = new RestStationCell(mLogger, SceneType.Rest_Station_Grass_Town);
					}

					if(mRestStationGrassController == null)
					{
						mRestStationGrassController = new RestStationController(mLogger, mRestStationGrassView, mPlayer);
					}

					if(mCurrentCell != null)
					{
						mRestStationGrassView.setKeysPressed(mCurrentCell.getKeysPressed());
					}

					Scene restStationScene = mRestStationGrassView.getScene();
					mRestStationGrassController.movePlayer(warpPoint);
					mCurrentCell = mRestStationGrassView;

					LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Rest Station in Grass Town");
					mStage.setScene(restStationScene);
					break;

				default:
					break;
			}

			mLastSceneType = mCurrSceneType;
			mCurrSceneType = type;
		}

		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}

		mStage.setWidth(width);
		mStage.setHeight(height);
	}

	public static void startBattle(ITrainer toBattle)
	{
		try
		{
			double width = mStage.getWidth();
			double height = mStage.getHeight();

			FXMLLoader loader = new FXMLLoader(Startup.class.getResource("/application/views/BattleView.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.setOnKeyReleased(mKeyListener);

			BattleController controller = (BattleController) loader.getController();
			controller.setUpBindingsAndElements(scene);
			controller.updateElements(mPlayer, toBattle);

			mLastSceneType = mCurrSceneType;

			LoggerController.logEvent(LoggingTypes.Misc, "Changing Scene to Battle");
			mStage.setScene(scene);

			mStage.setWidth(width);
			mStage.setHeight(height);
		}

		catch(IOException e)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Exception when starting battle. \n" + e.getStackTrace());
		}
	}

	public static void endBattle(BattleResult result)
	{
		if(result.hasEvolutions())
		{
			HashMap<Anature, Species> anaturesToEvolve = result.getAnaturesToEvolve();

			Iterator<Entry<Anature, Species>> evolveIterator = anaturesToEvolve.entrySet()
					.iterator();
			while(evolveIterator.hasNext())
			{
				Entry<Anature, Species> evolveEntry = evolveIterator.next();

				if(evolveIterator.hasNext())
				{
					mSceneStack.push(SceneType.Evolution);
				}

				changeScene(SceneType.Evolution, null);
				mEvolutionController.startEvolution(mPlayer.getAnatures(), evolveEntry.getKey(), evolveEntry.getValue(), () -> nextScene());
			}
		}

		else
		{
			changeScene(null, null);
		}
	}

	private static void nextScene()
	{
		if(mSceneStack.size() < 1)
		{
			changeScene(null, null);
		}

		else
		{
			changeScene(mSceneStack.pop(), null);
		}
	}

	public static void createDemo()
	{
		Anature first = NewAnatureBuilder.createAnature(getPlayerName(), Species.Null, 54);
		first.setName("Main Null");
		mPlayer.addAnatures(first);
		mPlayer.getAnatures()
				.get(0)
				.getStats()
				.addExperience(14601);

		Anature second = NewAnatureBuilder.createAnature(getPlayerName(), Species.Null, 12);
		second.setName("Other Null");
		mPlayer.addAnatures(second);

		Anature third = NewAnatureBuilder.createAnature(getPlayerName(), Species.Sardino, 14);
		mPlayer.addAnatures(third);
		mPlayer.getAnatures()
				.get(2)
				.getStats()
				.addExperience(630);

		mPlayer.getBackpack()
				.addItem(ItemPool.getHealthPotion(ItemIds.Potion));
		mPlayer.getBackpack()
				.addItem(ItemPool.getHealthPotion(ItemIds.Great_Potion));
		mPlayer.getBackpack()
				.addItem(ItemPool.getHealthPotion(ItemIds.Ultra_Potion));
		mPlayer.getBackpack()
				.addItem(ItemPool.getHealthPotion(ItemIds.Master_Potion));

		mPlayer.getBackpack()
				.addItem((Anacube) ItemPool.getItem(ItemIds.Anacube));
		mPlayer.getBackpack()
				.addItem((Anacube) ItemPool.getItem(ItemIds.Super_Anacube));
		mPlayer.getBackpack()
				.addItem((Anacube) ItemPool.getItem(ItemIds.Hyper_Anacube));
		mPlayer.getBackpack()
				.addItem((Anacube) ItemPool.getItem(ItemIds.Max_Anacube));

		LoggerController.logEvent(LoggingTypes.Misc, "Generated Demo Player");

		changeScene(SceneType.Starter_Town, WarpPoints.Starter_Town_House_1);
	}

	public static String getPlayerName()
	{
		return mPlayer.getName();
	}
}
