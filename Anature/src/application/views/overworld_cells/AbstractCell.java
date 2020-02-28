package application.views.overworld_cells;

import java.util.ArrayList;

import application.LoggerStartUp;
import application.enums.Direction;
import application.views.elements.ImageLayer;
import application.views.elements.PlayerSprite;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
	private LoggerStartUp mLogger;
	protected double mHeight, mWidth;
	private final int mSpeed = 300; // pixels / second
	private double mSpeedMultiplier;
	protected final DoubleProperty mZoom;
	protected boolean mUp, mDown, mLeft, mRight;
	private Scene mScene;
	private AnimationTimer mTimer;
	protected static PlayerSprite mPlayer;
	private Direction mPcFacing;
	protected ImageLayer mBackground;
	protected ArrayList<Rectangle> mCollisions;
	protected BooleanProperty mShowCollision;

	private Image mWalkUpImg, mWalkDownImg, mWalkRightImg, mWalkLeftImg, mStandUpImg, mStandDownImg, mStandRightImg, mStandLeftImg;

	public AbstractCell(LoggerStartUp logger, double width, double height)
	{
		mZoom = new SimpleDoubleProperty(2.6);

		mShowCollision = new SimpleBooleanProperty(false);
		mCollisions = new ArrayList<Rectangle>();

		mLogger = logger;
		mHeight = height;
		mWidth = width;
		mSpeedMultiplier = 1;

		mWalkUpImg = new Image(getClass().getResource("/resources/images/player/up_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkDownImg = new Image(getClass().getResource("/resources/images/player/down_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkRightImg = new Image(getClass().getResource("/resources/images/player/right_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkLeftImg = new Image(getClass().getResource("/resources/images/player/left_walk.gif").toExternalForm(), 100.0, 100.0, true, false);

		mStandUpImg = new Image(getClass().getResource("/resources/images/player/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandDownImg = new Image(getClass().getResource("/resources/images/player/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandRightImg = new Image(getClass().getResource("/resources/images/player/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandLeftImg = new Image(getClass().getResource("/resources/images/player/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);

		mPcFacing = Direction.Waiting;
		mPlayer = new PlayerSprite(mStandDownImg, 485, 599, mZoom, mShowCollision);

		mBackground = createBackground();
		Pane foreground = createForeground();

		StackPane map = new StackPane(mBackground, foreground);

		map.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		mPlayer.addToContainer(mBackground);
		addToBackground();
		createCollisons();

		mScene = new Scene(new BorderPane(map), 1280, 720, Color.BLACK);
		Rectangle clip = new Rectangle();
		clip.widthProperty().bind(mScene.widthProperty());
		clip.heightProperty().bind(mScene.heightProperty());

		clip.xProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayer.getX() - mScene.getWidth() / 2, 0, map.getWidth() - mScene.getWidth()),
				mPlayer.xProperty(), mScene.widthProperty()));
		clip.yProperty().bind(Bindings.createDoubleBinding(() -> clampRange(mPlayer.getY() - mScene.getHeight() / 2, 0, map.getHeight() - mScene.getHeight()),
				mPlayer.yProperty(), mScene.heightProperty()));

		map.setClip(clip);
		map.translateXProperty().bind(clip.xProperty().multiply(-1));
		map.translateYProperty().bind(clip.yProperty().multiply(-1));

		mScene.setOnKeyPressed(e -> processKey(e, true));
		mScene.setOnKeyReleased(e -> processKey(e, false));

		mScene.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				mShowCollision.set(!mShowCollision.get());
			}
		});

		mTimer = new AnimationTimer()
		{
			private long mLastUpdate = -1;

			@Override
			public void handle(long now)
			{
				long elapsedNanoSeconds = now - mLastUpdate;

				if(mLastUpdate < 0)
				{
					mLastUpdate = now;
					return;
				}

				double elapsedSeconds = elapsedNanoSeconds / 1000000000.0;
				double deltaX = 0;
				double deltaY = 0;

				timerHook();

				if(mRight)
				{
					deltaX += mSpeed * mSpeedMultiplier;
				}

				if(mLeft)
				{
					deltaX -= mSpeed * mSpeedMultiplier;
				}

				if(mDown)
				{
					deltaY += mSpeed * mSpeedMultiplier;
				}

				if(mUp)
				{
					deltaY -= mSpeed * mSpeedMultiplier;
				}

				updatePcSprite();

				double oldX = mPlayer.getX();
				double oldY = mPlayer.getY();

				mPlayer.setX(clampRange(mPlayer.getX() + deltaX * elapsedSeconds, 0, map.getWidth() - mPlayer.getFitWidth()));
				mPlayer.setY(clampRange(mPlayer.getY() + deltaY * elapsedSeconds, 0, map.getHeight() - mPlayer.getFitHeight()));
				
				if(!xCollisionCheck())
				{
					mPlayer.setX(oldX);
				}
				
				if(!yCollisionCheck())
				{
					mPlayer.setY(oldY);
				}

				mLastUpdate = now;
			}
		};

		mTimer.start();
	}

	protected abstract void addToBackground();

	protected abstract void timerHook();

	protected abstract boolean xCollisionCheck();

	protected abstract boolean yCollisionCheck();

	protected abstract ImageLayer createBackground();

	protected abstract ImageLayer createForeground();

	protected abstract void createCollisons();

	protected abstract void keyPressHook(KeyEvent event);

	private double clampRange(double value, double min, double max)
	{
		if(value < min)
			return min;

		if(value > max)
			return max;

		return value;
	}

	private void processKey(KeyEvent event, boolean on)
	{
		switch(event.getCode())
		{
			case A:

			case LEFT:
				mLeft = on;
				break;

			case D:

			case RIGHT:
				mRight = on;
				break;

			case W:

			case UP:
				mUp = on;
				break;

			case S:

			case DOWN:
				mDown = on;
				break;

			default:
				break;
		}

		if(event.getCode() == KeyCode.BACK_QUOTE && !on)
		{
			mLogger.toggleWindow();
		}

		if(event.isShiftDown())
		{
			mSpeedMultiplier = 1.75;
		}

		else
		{
			mSpeedMultiplier = 1;
		}

		if(!on)
		{
			keyPressHook(event);
		}
	}

	public Scene getScene()
	{
		mRight = false;
		mLeft = false;
		mUp = false;
		mDown = false;

		mTimer.start();

		return mScene;
	}
	
	public Direction getPlayerFacing()
	{
		return mPcFacing; 
	}

	public void stopTimer()
	{
		mTimer.stop();
	}

	private void updatePcSprite()
	{
		if(mUp && mDown && mLeft && mRight)
		{
			if(mPlayer.getImage().equals(mStandDownImg))
				return;

			mPlayer.setImage(mStandDownImg);
			mPcFacing = Direction.Down;
		}

		else if(mRight && mLeft && mUp)
		{
			if(mPlayer.getImage().equals(mWalkUpImg))
				return;

			mPlayer.setImage(mWalkUpImg);
			mPcFacing = Direction.Up;
		}

		else if(mRight && mLeft && mDown)
		{
			if(mPlayer.getImage().equals(mWalkDownImg))
				return;

			mPlayer.setImage(mWalkDownImg);
			mPcFacing = Direction.Down;
		}

		else if(mUp && mDown && mRight)
		{
			if(mPlayer.getImage().equals(mWalkRightImg))
				return;

			mPlayer.setImage(mWalkRightImg);
			mPcFacing = Direction.Right;
		}

		else if(mUp && mDown && mLeft)
		{
			if(mPlayer.getImage().equals(mWalkLeftImg))
				return;

			mPlayer.setImage(mWalkLeftImg);
			mPcFacing = Direction.Left;
		}

		else if((mUp && mDown) || (mRight && mLeft))
		{
			mPlayer.setImage(mStandDownImg);
			mPcFacing = Direction.Down;
		}

		else if(mRight && !mPlayer.getImage().equals(mWalkRightImg))
		{
			mPlayer.setImage(mWalkRightImg);
			mPcFacing = Direction.Right;
		}

		else if(mLeft && !mPlayer.getImage().equals(mWalkLeftImg))
		{
			mPlayer.setImage(mWalkLeftImg);
			mPcFacing = Direction.Left;
		}

		else if(mDown && !mPlayer.getImage().equals(mWalkDownImg) && !mLeft && !mRight)
		{
			mPlayer.setImage(mWalkDownImg);
			mPcFacing = Direction.Down;
		}

		else if(mUp && !mPlayer.getImage().equals(mWalkUpImg) && !mLeft && !mRight)
		{
			mPlayer.setImage(mWalkUpImg);
			mPcFacing = Direction.Up;
		}

		else if(!mUp && !mDown && !mRight && !mLeft)
		{
			switch(mPcFacing)
			{
				case Up:
					if(!mPlayer.getImage().equals(mStandUpImg))
						mPlayer.setImage(mStandUpImg);
					break;

				case Right:
					if(!mPlayer.getImage().equals(mStandRightImg))
						mPlayer.setImage(mStandRightImg);
					break;

				case Left:
					if(!mPlayer.getImage().equals(mStandLeftImg))
						mPlayer.setImage(mStandLeftImg);
					break;

				default:
					if(!mPlayer.getImage().equals(mStandDownImg))
						mPlayer.setImage(mStandDownImg);
					break;
			}
		}
	}
}
