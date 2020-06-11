package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Optional;

import application.anatures.Anature;
import application.anatures.NewAnatureBuilder;
import application.controllers.BattleController;
import application.controllers.IntroController;
import application.controllers.LoggerController;
import application.controllers.menus.AnatureSummaryController;
import application.controllers.menus.EvolutionController;
import application.controllers.overworld_cells.AbstractController;
import application.controllers.overworld_cells.GrassTownController;
import application.controllers.overworld_cells.PathOneController;
import application.controllers.overworld_cells.RestStationController;
import application.controllers.overworld_cells.StarterTownController;
import application.controllers.results.BattleResult;
import application.enums.Direction;
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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	private static AbstractController mCurrentController;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mPlayer = new Player(null);
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

					IntroController introController = introLoader.getController();
					introController.updateBinds(intro, mKeyListener);

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
					mCurrentController = mStarterTownController;

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
					mCurrentController = mPathOneController;

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
					mCurrentController = mGrassTownController;

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
					mCurrentController = mRestStationGrassController;

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
			LoggerController.logEvent(LoggingTypes.Error, "Exception when starting battle. \n" + e.getMessage());
		}
	}

	public static void endBattle(BattleResult result)
	{
		if(result.hasEvolutions())
		{
			HashMap<IAnature, Species> anaturesToEvolve = result.getAnaturesToEvolve();

			Iterator<Entry<IAnature, Species>> evolveIterator = anaturesToEvolve.entrySet().iterator();
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

		IAnature third = AnatureBuilder.createAnature(Species.Sardino, 14);
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

	/*
	 * SAVING FUNCTIONALITY
	 */

	private interface SavableItem
	{
		public Object getItem();

		public void setItem(Object object);
	}

	private enum SaveItem implements SavableItem
	{
		Player
		{
			@Override
			public Object getItem()
			{
				return mPlayer;
			}

			@Override
			public void setItem(Object object)
			{
				mPlayer = (Player) object;
			}
		},
		StarterTownModel
		{
			@Override
			public Object getItem()
			{
				return mStarterTownModel;
			}

			@Override
			public void setItem(Object object)
			{
				mStarterTownModel = (StarterTownModel) object;
			}
		},
		PathOneModel
		{
			@Override
			public Object getItem()
			{
				return mPathOneModel;
			}

			@Override
			public void setItem(Object object)
			{
				mPathOneModel = (PathOneModel) object;
			}
		},
		CurrentSceneType
		{
			@Override
			public Object getItem()
			{
				return mCurrSceneType;
			}

			@Override
			public void setItem(Object object)
			{
				mCurrSceneType = (SceneType) object;
				changeScene(mCurrSceneType, null);
			}
		},
		CurrentPlayerDirection
		{
			@Override
			public Object getItem()
			{
				return mCurrentCell.getPlayerFacing();
			}

			@Override
			public void setItem(Object object)
			{
				mCurrentCell.setPlayerFacing((Direction) object);
			}
		},
		CurrentPlayerXCoordinate
		{
			@Override
			public Object getItem()
			{
				return mCurrentCell.getPlayer().getX();
			}

			@Override
			public void setItem(Object object)
			{
				mCurrentCell.getPlayer().setX((double) object);
			}
		},
		CurrentPlayerYCoordinate
		{
			@Override
			public Object getItem()
			{
				return mCurrentCell.getPlayer().getY();
			}

			@Override
			public void setItem(Object object)
			{
				mCurrentCell.getPlayer().setY((double) object);
			}
		}
	}

	public static boolean save()
	{
		boolean result = false;

		File saveFile = selectFileToSave();

		if(saveFile != null)
		{
			ArrayList<Object> itemsToSave = new ArrayList<Object>();

			for(SaveItem item : SaveItem.values())
			{
				itemsToSave.add(item.getItem());
			}

			try
			{
				FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

				out.writeObject(itemsToSave);
				out.close();
				fileOutputStream.close();

				result = true;
			}

			catch(IOException e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Error when saving: " + e.getMessage());
			}
		}

		if(mCurrentController != null)
		{
			mCurrentController.saveLoadUpdates();
		}

		return result;
	}

	private static File selectFileToSave()
	{
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("MM-dd-yyyy--HH-mm-ss");
		Date date = new Date();
		String dateTime = dateTimeFormatter.format(date);

		File saveDir = new File("./Saves");

		if(!saveDir.exists())
		{
			saveDir.mkdir();
		}

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Save Game File");
		chooser.setInitialDirectory(saveDir);
		chooser.setInitialFileName("Save-" + dateTime);
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Save Files", "*.save"));

		return chooser.showSaveDialog(mStage);
	}

	@SuppressWarnings("unchecked")
	public static boolean load(boolean showConfirmation)
	{
		boolean result = false;

		if(showConfirmation && !showLoadConfirmation())
		{
			mCurrentController.saveLoadUpdates();
			return result;
		}

		File saveFile = selectFileToLoad();

		if(saveFile != null)
		{
			LoggerController.logEvent(LoggingTypes.Misc, "Loading save file.");

			ArrayList<Object> objRead = new ArrayList<Object>();
			FileInputStream fileInStream;
			ObjectInputStream in;

			if(mCurrentController != null)
			{
				mCurrentController.saveLoadUpdates();
			}

			try
			{
				fileInStream = new FileInputStream(saveFile);
				in = new ObjectInputStream(fileInStream);

				objRead = (ArrayList<Object>) in.readObject();

				for(SaveItem item : SaveItem.values())
				{
					item.setItem(objRead.remove(0));
				}

				in.close();
				fileInStream.close();

				result = true;
			}

			catch(Exception e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Error when loading save: " + e.getMessage());
			}
		}

		if(mCurrentController != null)
		{
			mCurrentController.saveLoadUpdates();
		}

		return result;
	}

	private static boolean showLoadConfirmation()
	{
		LoggerController.logEvent(LoggingTypes.Misc, "Asking if user wants to save before loading.");

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Current progress not saved");
		alert.setHeaderText("Would you like to save your current progress first?");

		ButtonType buttonTypeYes = new ButtonType("Yes");
		ButtonType buttonTypeNo = new ButtonType("No");

		alert.getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
		ObservableList<ButtonType> btns = alert.getButtonTypes();
		btns.removeAll(ButtonType.OK);

		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == buttonTypeNo)
		{
			return true;
		}

		if(result.get() == buttonTypeYes)
		{
			return save();
		}

		return false;
	}

	private static File selectFileToLoad()
	{
		LoggerController.logEvent(LoggingTypes.Misc, "Selecting file to load.");
		File saveDir = new File(System.getProperty("user.dir") + "/Saves");
		LoggerController.logEvent(LoggingTypes.Misc, "Making saves folder at: " + saveDir.getAbsolutePath());

		if(!saveDir.exists())
		{

			try
			{
				if(saveDir.mkdir())
				{
					LoggerController.logEvent(LoggingTypes.Misc, "Maked saves folder at: " + saveDir.getAbsolutePath());
				}

				else
				{
					throw new Exception("Making of Save Directory returned as false.");
				}
			}

			catch(Exception e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Something went wrong when making the Saves Directory. " + e.toString());

				for(StackTraceElement element : e.getStackTrace())
				{
					LoggerController.logEvent(LoggingTypes.Error, element.toString());
				}

				saveDir = null;
			}
		}

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Save File");
		chooser.setInitialDirectory(saveDir);
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Save Files", "*.save"));

		return chooser.showOpenDialog(mStage);
	}

	public static void newGame()
	{
		// TODO remove starter, tokens, and items after professor is added
		IAnature starter = AnatureBuilder.createAnature(Species.Sardino, 5);
		mPlayer.addAnatures(starter);
		mPlayer.addTokens(500);

		for(int i = 0; i < 5; i++)
		{
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Anacube));
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Super_Anacube));
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Hyper_Anacube));
			mPlayer.getBackpack().addItem((Anacube) ItemPool.getItem(ItemIds.Max_Anacube));
		}

		LoggerController.logEvent(LoggingTypes.Misc, "Generated New Game");
		changeScene(SceneType.Starter_Town, WarpPoints.Starter_Town_House_1);
	}
}