package application.views.overworld_cells;

import java.util.ArrayList;

import application.LoggerStartUp;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.views.elements.ImageLayer;
import application.views.elements.PlayerSprite;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public abstract class AbstractCell
{
	protected double mHeight, mWidth;
	protected final DoubleProperty mZoom;
	public boolean mUp, mDown, mLeft, mRight;
	private Scene mScene;
	protected static PlayerSprite mPlayer;
	private Direction mPcFacing;
	protected ImageLayer mBackground;
	protected ArrayList<Rectangle> mCollisions;
	protected BooleanProperty mShowCollision;
	private StackPane mMap;

	private Image mStandDownImg;

	public AbstractCell(LoggerStartUp logger, double width, double height)
	{
		mZoom = new SimpleDoubleProperty(2.6);

		mShowCollision = new SimpleBooleanProperty(false);
		mCollisions = new ArrayList<Rectangle>();

		mHeight = height;
		mWidth = width;

		mStandDownImg = new Image(getClass().getResource("/resources/images/player/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);

		mPcFacing = Direction.Waiting;
		mPlayer = new PlayerSprite(mStandDownImg, 485, 599, mZoom, mShowCollision);

		mBackground = createBackground();
		Pane foreground = createForeground();

		mMap = new StackPane(mBackground, foreground);
		mMap.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		mPlayer.addToContainer(mBackground);
		addToBackground();
		createCollisons();

		BorderPane cell = new BorderPane(mMap);
		
		mScene = new Scene(cell, 1280, 720, Color.BLACK);
		Rectangle clip = new Rectangle();
		clip.widthProperty().bind(mScene.widthProperty());
		clip.heightProperty().bind(mScene.heightProperty());

		clip.xProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayer.getX() - mScene.getWidth() / 2, 0, mMap.getWidth() - mScene.getWidth()),
				mPlayer.xProperty(), mScene.widthProperty()));
		clip.yProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayer.getY() - mScene.getHeight() / 2, 0, mMap.getHeight() - mScene.getHeight()),
				mPlayer.yProperty(), mScene.heightProperty()));

		mMap.setClip(clip);
		mMap.translateXProperty().bind(clip.xProperty().multiply(-1));
		mMap.translateYProperty().bind(clip.yProperty().multiply(-1));

		mScene.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				mShowCollision.set(!mShowCollision.get());
			}
		});
	}

	protected abstract void addToBackground();

	protected abstract ImageLayer createBackground();

	protected abstract ImageLayer createForeground();

	protected abstract void createCollisons();

	public double clampRange(double value, double min, double max)
	{
		if(value < min)
			return min;

		if(value > max)
			return max;

		return value;
	}

	public Scene getScene()
	{
		mRight = false;
		mLeft = false;
		mUp = false;
		mDown = false;

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
			LoggerController.logEvent(LoggingTypes.Default, "Tried making Player Direction null.");
			return;
		}

		mPcFacing = direction;
	}

	public void setSceneOnKeyboardPressed(EventHandler<KeyEvent> event)
	{
		if(event == null)
		{
			LoggerController.logEvent(LoggingTypes.Default, "Tried making Scene on Keyboard Press null.");
			return;
		}

		mScene.setOnKeyPressed(event);
	}

	public void setSceneOnKeyboardReleased(EventHandler<KeyEvent> event)
	{
		if(event == null)
		{
			LoggerController.logEvent(LoggingTypes.Default, "Tried making Scene on Keyboard Released null.");
			return;
		}

		mScene.setOnKeyReleased(event);
	}

	public ArrayList<Rectangle> getCollisions()
	{
		return mCollisions;
	}
}
