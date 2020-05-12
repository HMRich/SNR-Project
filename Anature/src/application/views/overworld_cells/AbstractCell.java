package application.views.overworld_cells;

import java.io.IOException;
import java.util.ArrayList;

import application.LoggerStartUp;
import application.controllers.DialogueBoxController;
import application.controllers.LoggerController;
import application.controllers.ShoppingMenuController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.SceneType;
import application.enums.WarpPoints;
import application.player.Player;
import application.views.elements.ImageLayer;
import application.views.elements.PlayerSprite;
import application.views.elements.ShoppingMenu;
import application.views.elements.TrainerSprite;
import application.views.elements.WarpPointBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public abstract class AbstractCell
{
	protected double mHeight, mWidth;
	protected final DoubleProperty mZoom;
	public boolean mUp, mDown, mLeft, mRight, mCanMove;
	private Scene mScene;
	protected PlayerSprite mPlayerSprite;
	private Direction mPcFacing;
	protected ImageLayer mBackground;
	protected ArrayList<Rectangle> mCollisions, mGrassPatches;
	protected ArrayList<WarpPointBox> mWarpPoints;
	protected ArrayList<TrainerSprite> mTrainerSprites;
	protected BooleanProperty mShowCollision;
	private StackPane mMap;
	protected SceneType mId;
	
	private ShoppingMenuController mShoppingController;

	private StringProperty mDialogueTxtProperty;
	private BooleanProperty mShowDialogueProperty, mShowShoppingProperty;
	private Image mStandDownImg;

	public AbstractCell(LoggerStartUp logger, double width, double height, SceneType id)
	{
		mZoom = new SimpleDoubleProperty(2.6);
		mShowDialogueProperty = new SimpleBooleanProperty(false);
		mShowShoppingProperty = new SimpleBooleanProperty(false);
		
		mDialogueTxtProperty = new SimpleStringProperty("Sample Text");
		mShowCollision = LoggerController.getCollisionBoxProperty();
		mCollisions = new ArrayList<Rectangle>();
		mGrassPatches = new ArrayList<Rectangle>();
		mWarpPoints = new ArrayList<WarpPointBox>();
		mTrainerSprites = new ArrayList<TrainerSprite>();
		mCanMove = true;
		mId = id;

		mHeight = height;
		mWidth = width;

		mStandDownImg = new Image(getClass().getResource("/resources/images/player/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);

		mPcFacing = Direction.Waiting;
		mPlayerSprite = new PlayerSprite(mStandDownImg, 485, 599, mZoom, mShowCollision);

		mBackground = createBackground();
		Pane foreground = createForeground();

		if(foreground == null)
		{
			mMap = new StackPane(mBackground);
		}
		
		else
		{
			mMap = new StackPane(mBackground, foreground);
		}
		
		mMap.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		mPlayerSprite.addToContainer(mBackground);
		addToBackground();
		addToForeground();
		createCollisons();
		createGrassPatches();
		createWarpPoints();
		createTrainers();
		
		for(TrainerSprite trainer : mTrainerSprites)
		{
			mCollisions.add(trainer.getCollisionBox());
			trainer.addToContainer(mBackground);
		}

		mBackground.getChildren().addAll(mWarpPoints);
		mBackground.getChildren().addAll(mGrassPatches);
		mBackground.getChildren().addAll(mCollisions);

		BorderPane cell = new BorderPane(mMap);
		
		mScene = new Scene(cell, 1280, 720, Color.BLACK);
		Rectangle clip = new Rectangle();
		clip.widthProperty().bind(mScene.widthProperty());
		clip.heightProperty().bind(mScene.heightProperty());

		clip.xProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayerSprite.getX() - mScene.getWidth() / 2, 0, mMap.getWidth() - mScene.getWidth()),
				mPlayerSprite.xProp(), mScene.widthProperty()));
		clip.yProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayerSprite.getY() - mScene.getHeight() / 2, 0, mMap.getHeight() - mScene.getHeight()),
				mPlayerSprite.yProp(), mScene.heightProperty()));

		mMap.setClip(clip);
		mMap.translateXProperty().bind(clip.xProperty().multiply(-1));
		mMap.translateYProperty().bind(clip.yProperty().multiply(-1));
		
		setUpDialogueBox(cell);
		setUpShoppingMenu(cell);
	}
	
	protected abstract void createTrainers();

	protected abstract void addToBackground();

	protected abstract void addToForeground();

	protected abstract ImageLayer createBackground();

	protected abstract ImageLayer createForeground();

	protected abstract void createCollisons();

	protected abstract void createGrassPatches();

	protected abstract void createWarpPoints();
	
	private void setUpDialogueBox(BorderPane cell)
	{
		try
		{
			Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 75);
			ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);

			mScene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> fontProperty
					.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / 75)));

			mScene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> fontProperty
					.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / 75)));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/elements/DialogueBox.fxml"));
			Parent root = loader.load();
			
			DialogueBoxController controller = loader.getController();
			controller.updateBinds(mScene, mBackground, mShowDialogueProperty, fontProperty, mDialogueTxtProperty);
			cell.getChildren().add(root);
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void setUpShoppingMenu(BorderPane cell)
	{
		// Font Prop
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 75);
		ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);

		mScene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / 75)));

		mScene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / 75)));		
		
		// Shopping Menu
		ShoppingMenu shoppingMenu = new ShoppingMenu();
		mShoppingController = new ShoppingMenuController(shoppingMenu);
		
		mShoppingController.updateBinds(mScene.heightProperty().divide(5), 
				mScene.widthProperty().divide(3.28), mScene.heightProperty().divide(1.67), fontProperty, mShowShoppingProperty);
		
		cell.getChildren().add(shoppingMenu);
	}

	public double clampRange(double value, double min, double max)
	{
		if(value < min)
			return min;

		if(value > max)
			return max;

		return value;
	}
	
	private double getFontSize()
	{
		double value = mScene.getWidth();

		if(mScene.getHeight() < 464)
		{
			value = mScene.getHeight() / 0.45;
		}

		if(mScene.getWidth() >= 1940)
		{
			value = value - (mScene.getWidth() - 1940);
		}

		return value;
	}

	protected void addCollisionRectangle(double x, double y, double width, double height)
	{
		mCollisions.add(createRectangle(x, y, width, height, Color.LIGHTGREY));
	}
	
	protected void addCollisionRectangleUsingCoords(double upperLeftX, double upperLeftY, double lowerRightX, double lowerRightY)
	{
		mCollisions.add(createRectangle(upperLeftX, upperLeftY, lowerRightX - upperLeftX, lowerRightY - upperLeftY, Color.LIGHTGREY));
	}

	protected void addGrassPatchRectangle(double upperLeftX, double upperLeftY, double lowerRightX, double lowerRightY)
	{
		Rectangle rect = createRectangle(upperLeftX, upperLeftY, lowerRightX - upperLeftX, lowerRightY - upperLeftY, Color.DARKGREEN);
		mGrassPatches.add(rect);
	}

	protected void addWarpPoint(SceneType sceneType, WarpPoints warpTo, double x, double y, double width, double height)
	{
		WarpPointBox warp = new WarpPointBox(warpTo, sceneType, x, y, width, height);
		warp.visibleProperty().bind(mShowCollision);
		mWarpPoints.add(warp);
	}
	
	private Rectangle createRectangle(double x, double y, double width, double height, Color color)
	{
		Rectangle rect = new Rectangle(x, y, width, height);
		rect.visibleProperty().bind(mShowCollision);
		rect.setFill(color);
		
		return rect;
	}
	
	protected void addTrainer(TrainerSprite trainer)
	{
		if(trainer == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried to add a trainer spite that was null.");
			return;
		}
		
		mTrainerSprites.add(trainer);
	}

	public Scene getScene()
	{
		mRight = false;
		mLeft = false;
		mUp = false;
		mDown = false;
		mCanMove = true;
		mPlayerSprite.hideEmote();
		
		return mScene;
	}

	public PlayerSprite getPlayer()
	{
		return mPlayerSprite;
	}

	public ImageLayer getBackground()
	{
		return mBackground;
	}

	public double getMapWidth()
	{
		return mMap.getWidth();
	}

	public double getMapHeight()
	{
		return mMap.getHeight();
	}

	public Direction getPlayerFacing()
	{
		return mPcFacing;
	}

	public void setPlayerFacing(Direction direction)
	{
		if(direction == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried making Player Direction null.");
			return;
		}

		mPcFacing = direction;
	}

	public void setSceneOnKeyboardPressed(EventHandler<KeyEvent> event)
	{
		if(event == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried making Scene on Keyboard Press null.");
			return;
		}

		mScene.setOnKeyPressed(event);
	}

	public void setSceneOnKeyboardReleased(EventHandler<KeyEvent> event)
	{
		if(event == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried making Scene on Keyboard Released null.");
			return;
		}

		mScene.setOnKeyReleased(event);
	}
	
	public void setSceneOnMouseClicked(EventHandler<MouseEvent> event)
	{
		if(event == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried making Scene on Mouse Clicked null.");
			return;
		}

		mScene.setOnMouseClicked(event);
	}

	public ArrayList<Rectangle> getCollisions()
	{
		return mCollisions;
	}

	public ArrayList<Rectangle> getGrassPatches()
	{
		return mGrassPatches;
	}

	public ArrayList<WarpPointBox> getWarpPoints()
	{
		return mWarpPoints;
	}
	
	public ArrayList<TrainerSprite> getTrainerSprites()
	{
		return mTrainerSprites;
	}
	
	public void showDialogue(String txt)
	{
		if(txt == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried to show overworld dialogue that was null");
			return;
		}
		
		mDialogueTxtProperty.set(txt);
		mShowDialogueProperty.set(true);
	}
	
	public void hideDialogue()
	{
		mShowDialogueProperty.set(false);
	}
	
	public void showshopMenu()
	{
		mShoppingController.updateItems();
		mShowShoppingProperty.set(true);
	}
	
	public void hideShopMenu()
	{
		mShowShoppingProperty.set(false);
	}
	
	public void assignPlayerForShop(Player player, Runnable dequeue)
	{
		if(player == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried assigning a null player to cell.");
		}
		
		else
		{
			mShoppingController.assignPlayer(player, dequeue);
		}
	}
}
