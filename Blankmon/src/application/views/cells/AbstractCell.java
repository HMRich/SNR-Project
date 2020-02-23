package application.views.cells;

import application.LoggerStartUp;
import application.Startup;
import application.enums.Direction;
import application.enums.SceneType;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
	protected int mHeight, mWidth;
	private final int mSpeed = 300; // pixels / second
	private final double mZoom = 2.6;
	private boolean mUp, mDown, mLeft ,mRight;
	private Scene mScene;
	private AnimationTimer mTimer;
	private ImageView mPlayer;
	private Direction mPcFacing;
	
	private boolean mCanMoveUp;
	
	private Image mWalkUpImg, mWalkDownImg, mWalkRightImg, mWalkLeftImg, mStandUpImg, mStandDownImg, mStandRightImg, mStandLeftImg;

	public AbstractCell(LoggerStartUp logger)
	{
		mCanMoveUp = true;
		mLogger = logger;
		mHeight = 468;
		mWidth = 427;
		
		mWalkUpImg = new Image(getClass().getResource("/resources/images/player/up_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkDownImg = new Image(getClass().getResource("/resources/images/player/down_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkRightImg = new Image(getClass().getResource("/resources/images/player/right_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkLeftImg = new Image(getClass().getResource("/resources/images/player/left_walk.gif").toExternalForm(), 100.0, 100.0, true, false);

		mStandUpImg = new Image(getClass().getResource("/resources/images/player/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandDownImg = new Image(getClass().getResource("/resources/images/player/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandRightImg = new Image(getClass().getResource("/resources/images/player/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandLeftImg = new Image(getClass().getResource("/resources/images/player/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		
		mPcFacing = Direction.Waiting;
		mPlayer = new ImageView(mStandDownImg);
		mPlayer.setFitHeight(29 * mZoom);
		mPlayer.setFitWidth(24 * mZoom);
		mPlayer.setX(485);
		mPlayer.setY(599);
		
		ImageView trainer = new ImageView(new Image(getClass().getResource("/resources/images/trainers/kelly/down_stand.png").toExternalForm(), 100.0, 100.0, true, false));
		trainer.setFitHeight(29 * mZoom);
		trainer.setFitWidth(24 * mZoom);
		trainer.setX(427);
		trainer.setY(1310);
		
		Pane background = createBackground();
		Pane foreground = createForeground();
		createCollisons(background);
		
		StackPane map = new StackPane(background, foreground);
		
		map.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		background.getChildren().addAll(trainer, mPlayer);

		mScene = new Scene(new BorderPane(map), 1280, 720, Color.BLACK);
		Rectangle clip = new Rectangle();
		clip.widthProperty().bind(mScene.widthProperty());
		clip.heightProperty().bind(mScene.heightProperty());

		clip.xProperty()
				.bind(Bindings.createDoubleBinding(
						() -> clampRange(mPlayer.getX() - mScene.getWidth() / 2, 0, map.getWidth() - mScene.getWidth()),
						mPlayer.xProperty(), mScene.widthProperty()));
		clip.yProperty()
				.bind(Bindings.createDoubleBinding(
						() -> clampRange(mPlayer.getY() - mScene.getHeight() / 2, 0, map.getHeight() - mScene.getHeight()),
						mPlayer.yProperty(), mScene.heightProperty()));

		map.setClip(clip);
		map.translateXProperty().bind(clip.xProperty().multiply(-1));
		map.translateYProperty().bind(clip.yProperty().multiply(-1));

		mScene.setOnKeyPressed(e -> processKey(e.getCode(), true));
		mScene.setOnKeyReleased(e -> processKey(e.getCode(), false));
		
		mScene.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				System.out.println(event.getSceneX() + " - " + event.getSceneY());
				System.out.println(mPlayer.getX() + " - " + mPlayer.getY());
			}
		});

		mTimer = new AnimationTimer()
		{
			private long lastUpdate = -1;

			@Override
			public void handle(long now)
			{
				long elapsedNanos = now - lastUpdate;
				if (lastUpdate < 0)
				{
					lastUpdate = now;
					return;
				}
				double elapsedSeconds = elapsedNanos / 1_000_000_000.0;
				double deltaX = 0;
				double deltaY = 0;
				if (mRight)
					deltaX += mSpeed;
				if (mLeft)
					deltaX -= mSpeed;
				if (mDown)
					deltaY += mSpeed;
				if (mUp && mCanMoveUp)
					deltaY -= mSpeed;
				
				updatePcSprite();
				
				mPlayer.setX(clampRange(mPlayer.getX() + deltaX * elapsedSeconds, 0, map.getWidth() - mPlayer.getFitWidth()));
				mPlayer.setY(clampRange(mPlayer.getY() + deltaY * elapsedSeconds, 0, map.getHeight() - mPlayer.getFitHeight()));
				
				lastUpdate = now;
				
				if(mPlayer.getBoundsInParent().intersects(trainer.getBoundsInParent())) // TODO Only for demo
				{
					Startup.changeScene(SceneType.Battle);

					mPlayer.setX(485);
					mPlayer.setY(599);
					
					mRight = false;
					mLeft = false;
					mDown = false;
					mUp = false;
				}
			}
		};

		mTimer.start();
	}

	private void createCollisons(Pane background)
	{
		Rectangle northTreeBox = new Rectangle(277, 37, 1151, 50);
		
		AnimationTimer collisionTimer = new AnimationTimer()
		{
			@Override
			public void handle(long now)
			{
				if(mPlayer.getBoundsInParent().intersects(northTreeBox.getBoundsInParent()))
					mCanMoveUp = false;
				
				else
					mCanMoveUp = true;
			}
		};
		
		collisionTimer.start();
		
		background.getChildren().add(northTreeBox);
	}

	private double clampRange(double value, double min, double max)
	{
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}

	private void processKey(KeyCode code, boolean on)
	{
		switch(code)
		{
			case LEFT:
				mLeft = on;
				break;
				
			case RIGHT:
				mRight = on;
				break;
				
			case UP:
				mUp = on;
				break;
				
			case DOWN:
				mDown = on;
				break;
				
			default:
				break;
		}
		
		if(code == KeyCode.BACK_QUOTE)
		{
			mLogger.toggleWindow();
		}
	}

	private Pane createBackground()
	{
		Canvas canvas = new Canvas(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(new Image(getClass().getResource("/resources/images/overworld/starter_town_background.png").toExternalForm(), 10000.0, 10000.0, true, false)
				, 0, 0, mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);

		Pane pane = new Pane(canvas);

		pane.setMinSize(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);
		pane.setPrefSize(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);
		pane.setMaxSize(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);

		return pane;
	}

	private Pane createForeground()
	{
		Canvas canvas = new Canvas(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(new Image(getClass().getResource("/resources/images/overworld/starter_town_foreground.png").toExternalForm(), 10000.0, 10000.0, true, false)
				, 0, 0, mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);

		StackPane pane = new StackPane();		
		pane.getChildren().add(canvas);

		pane.setMinSize(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);
		pane.setPrefSize(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);
		pane.setMaxSize(mWidth * mZoom * 1.4, mHeight * mZoom * 1.4);

		return pane;
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
