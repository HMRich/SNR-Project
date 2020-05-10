package application.views.overworld_cells;

import java.util.ArrayList;

import application.LoggerStartUp;
import application.animations.BlinkingAnimation;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.SceneType;
import application.enums.WarpPoints;
import application.views.elements.ImageLayer;
import application.views.elements.PlayerSprite;
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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public abstract class AbstractCell
{
	protected double mHeight, mWidth;
	protected final DoubleProperty mZoom;
	public boolean mUp, mDown, mLeft, mRight, mCanMove;
	private Scene mScene;
	protected PlayerSprite mPlayer;
	private Direction mPcFacing;
	protected ImageLayer mBackground;
	protected ArrayList<Rectangle> mCollisions, mGrassPatches;
	protected ArrayList<WarpPointBox> mWarpPoints;
	protected ArrayList<TrainerSprite> mTrainerSprites;
	protected BooleanProperty mShowCollision;
	private StackPane mMap;
	protected SceneType mId;

	private StringProperty mDialogueTxtProperty;
	private BooleanProperty mShowDialogueProperty;
	private Image mStandDownImg;

	public AbstractCell(LoggerStartUp logger, double width, double height, SceneType id)
	{
		mZoom = new SimpleDoubleProperty(2.6);
		mShowDialogueProperty = new SimpleBooleanProperty(false);
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
		mPlayer = new PlayerSprite(mStandDownImg, 485, 599, mZoom, mShowCollision);

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

		mPlayer.addToContainer(mBackground);
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

		clip.xProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayer.getX() - mScene.getWidth() / 2, 0, mMap.getWidth() - mScene.getWidth()),
				mPlayer.xProp(), mScene.widthProperty()));
		clip.yProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayer.getY() - mScene.getHeight() / 2, 0, mMap.getHeight() - mScene.getHeight()),
				mPlayer.yProp(), mScene.heightProperty()));

		mMap.setClip(clip);
		mMap.translateXProperty().bind(clip.xProperty().multiply(-1));
		mMap.translateYProperty().bind(clip.yProperty().multiply(-1));
		
		setUpDialogueBox(cell);
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
		ImageView dialogueBox = new ImageView(
				new Image(getClass().getResource("/resources/images/overworld/dialogue/Blue_Dialogue_Box.png").toExternalForm(), 1000.0, 1000.0, true, false));
		
		dialogueBox.fitWidthProperty().bind(mScene.widthProperty().divide(1.022));
		dialogueBox.fitHeightProperty().bind(mScene.heightProperty().divide(4.5569));
		dialogueBox.layoutXProperty().bind(mScene.widthProperty().divide(91.428));
		dialogueBox.layoutYProperty().bind(mScene.heightProperty().divide(1.306));
		dialogueBox.visibleProperty().bind(mShowDialogueProperty);

		ImageView dialogueClickIndicator = new ImageView(
				new Image(getClass().getResource("/resources/images/battle/BattleScreen_ClickIndicator.png").toExternalForm(), 1000.0, 1000.0, true, false));
		
		dialogueClickIndicator.fitWidthProperty().bind(mScene.widthProperty().divide(35.55));
		dialogueClickIndicator.fitHeightProperty().bind(mScene.heightProperty().divide(25.714));
		dialogueClickIndicator.layoutXProperty().bind(mScene.widthProperty().divide(1.0987));
		dialogueClickIndicator.layoutYProperty().bind(mScene.heightProperty().divide(1.0746));
		dialogueClickIndicator.visibleProperty().bind(mShowDialogueProperty);
		
		BlinkingAnimation blinkAnimation = new BlinkingAnimation(dialogueClickIndicator, Duration.seconds(1.5));
		blinkAnimation.play();
		
		TextArea dialogueTxt = new TextArea();
		dialogueTxt.setEditable(false);
		dialogueTxt.setWrapText(true);
		dialogueTxt.setFocusTraversable(false);
		
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), 75);
		ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);

		mScene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / 75)));

		mScene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / 75)));
		
		dialogueTxt.getStylesheets().add("/resources/css/BattleStyle.css");
		dialogueTxt.textProperty().bind(mDialogueTxtProperty);
		dialogueTxt.visibleProperty().bind(dialogueBox.visibleProperty());
		dialogueTxt.fontProperty().bind(fontProperty);
		
		dialogueTxt.prefWidthProperty().bind(mScene.widthProperty().divide(1.14));
		dialogueTxt.prefHeightProperty().bind(mScene.heightProperty().divide(5.29));
		dialogueTxt.layoutXProperty().bind(mScene.widthProperty().divide(16.202));
		dialogueTxt.layoutYProperty().bind(mScene.heightProperty().divide(1.28));
		
		Pane overlay = new Pane(dialogueBox, dialogueTxt, dialogueClickIndicator);
		cell.getChildren().add(overlay);
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
		mPlayer.hideEmote();
		
		return mScene;
	}

	public PlayerSprite getPlayer()
	{
		return mPlayer;
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
}
