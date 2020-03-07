package application.controllers.overworld_cells;

import java.util.Random;

import application.LoggerStartUp;
import application.Startup;
import application.controllers.ClickQueue;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.TrainerIds;
import application.enums.WarpPoints;
import application.trainers.Trainer;
import application.trainers.TrainerBuilder;
import application.views.elements.PlayerSprite;
import application.views.elements.WarpPointBox;
import application.views.overworld_cells.AbstractCell;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public abstract class AbstractController
{
	private LoggerStartUp mLogger;
	protected AbstractCell mView;
	private final int mSpeed = 300; // pixels / second
	private double mSpeedMultiplier;
	private AnimationTimer mTimer;
	protected PlayerSprite mPlayer;
	protected ClickQueue mClickQueue;

	protected Image mWalkUpImg, mWalkDownImg, mWalkRightImg, mWalkLeftImg, mStandUpImg, mStandDownImg, mStandRightImg, mStandLeftImg;
	
	public AbstractController(LoggerStartUp logger, AbstractCell view)
	{
		if(view == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Making Starter Town view null.");
			throw new IllegalArgumentException("Making Starter Town view null.");
		}
		
		mView = view;
		mPlayer = mView.getPlayer();
		mLogger = logger;
		mSpeedMultiplier = 1;
		mClickQueue = new ClickQueue();
		
		mWalkUpImg = new Image(getClass().getResource("/resources/images/player/up_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkDownImg = new Image(getClass().getResource("/resources/images/player/down_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkRightImg = new Image(getClass().getResource("/resources/images/player/right_walk.gif").toExternalForm(), 100.0, 100.0, true, false);
		mWalkLeftImg = new Image(getClass().getResource("/resources/images/player/left_walk.gif").toExternalForm(), 100.0, 100.0, true, false);

		mStandUpImg = new Image(getClass().getResource("/resources/images/player/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandDownImg = new Image(getClass().getResource("/resources/images/player/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandRightImg = new Image(getClass().getResource("/resources/images/player/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mStandLeftImg = new Image(getClass().getResource("/resources/images/player/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		
		mView.setSceneOnKeyboardPressed(e -> processKey(e, true));
		mView.setSceneOnKeyboardReleased(e -> processKey(e, false));
		
		activateTimer();
	}

	protected abstract void timerHook();

	protected abstract void keyPressHook(KeyEvent event);
	
	public abstract void movePlayer(WarpPoints warpPoint);

	private boolean checkGrassPatch()
	{
		Bounds player = mPlayer.getBoxBounds();
		boolean result = false;
		
		for(Rectangle toCheck : mView.getGrassPatches())
		{
			result = player.intersects(toCheck.getBoundsInParent());
			
			if(result)
				break;
		}
		
		return result;
	}

	private WarpPointBox checkWarpPoints()
	{
		Bounds player = mPlayer.getBoxBounds();
		
		for(WarpPointBox toCheck : mView.getWarpPoints())
		{
			if(player.intersects(toCheck.getBoundsInParent()))
			{
				return toCheck;
			}
		}
		
		return null;
	}

	private boolean xCollisionCheck()
	{
		Bounds left = mPlayer.getLeftBounds();
		Bounds right = mPlayer.getRightBounds();

		for(Rectangle toCheck : mView.getCollisions())
		{
			boolean rightCheck = right.intersects(toCheck.getBoundsInParent());
			boolean leftCheck = left.intersects(toCheck.getBoundsInParent());

			if((rightCheck && !leftCheck) || (leftCheck && !rightCheck))
			{
				return false;
			}
		}

		return true;
	}

	private boolean yCollisionCheck()
	{
		Bounds top = mPlayer.getTopBounds();
		Bounds bot = mPlayer.getBotBounds();

		for(Rectangle toCheck : mView.getCollisions())
		{
			boolean topCheck = top.intersects(toCheck.getBoundsInParent());
			boolean botCheck = bot.intersects(toCheck.getBoundsInParent());

			if((topCheck && !botCheck) || (botCheck && !topCheck))
			{
				return false;
			}
		}

		return true;
	}
	
	private void activateTimer()
	{
		mTimer = new AnimationTimer()
		{
			private long mLastUpdate = -1;
			private double mLastWildX = 0, mLastWildY = 0;

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

				if(mView.mCanMove)
				{
					if(mView.mRight)
					{
						deltaX += mSpeed * mSpeedMultiplier;
					}

					if(mView.mLeft)
					{
						deltaX -= mSpeed * mSpeedMultiplier;
					}

					if(mView.mDown)
					{
						deltaY += mSpeed * mSpeedMultiplier;
					}

					if(mView.mUp)
					{
						deltaY -= mSpeed * mSpeedMultiplier;
					}

					updatePcSprite();

					double oldX = mPlayer.getX();
					double oldY = mPlayer.getY();

					mPlayer.setX(mView.clampRange(mPlayer.getX() + deltaX * elapsedSeconds, 0, mView.getMapWidth() - mPlayer.getFitWidth()));
					mPlayer.setY(mView.clampRange(mPlayer.getY() + deltaY * elapsedSeconds, 0, mView.getMapHeight() - mPlayer.getFitHeight()));
					
					if(LoggerController.isCollisionEnabled())
					{
						if(!xCollisionCheck())
						{
							mPlayer.setX(oldX);
						}
						
						if(!yCollisionCheck())
						{
							mPlayer.setY(oldY);
						}
					}
					
					if(checkGrassPatch())
					{
						double currX = mPlayer.getX();
						double currY = mPlayer.getY();
						
						if(currX > mLastWildX + 100 || currX < mLastWildX - 100 || currY > mLastWildY + 100 || currY < mLastWildY - 100)
						{
							mLastWildX = mPlayer.getX();
							mLastWildY = mPlayer.getY();
							
							Random r = new Random();

							if(r.nextInt(100) > 85) // TODO modify encounter rate calculations
							{
								LoggerController.logEvent(LoggingTypes.Misc, "Player has encountered a wild Anature.");
								Trainer wildEncounter = TrainerBuilder.createTrainer(TrainerIds.Wild, 1, 3, 6);
								Startup.startBattle(wildEncounter);
								
								mView.mCanMove = false;
							}
						}
					}
					
					WarpPointBox warpCheck = checkWarpPoints();
					
					if(warpCheck != null)
					{
						LoggerController.logEvent(LoggingTypes.Misc, "Player has entered a warp point.");
						Startup.changeScene(warpCheck.getSceneType(), warpCheck.getWarpPoint());
						
						mView.mCanMove = false;
					}
				}

				mLastUpdate = now;
			}
		};

		mTimer.start();
	}

	private void processKey(KeyEvent event, boolean on)
	{
		switch(event.getCode())
		{
			case A:

			case LEFT:
				mView.mLeft = on;
				break;

			case D:

			case RIGHT:
				mView.mRight = on;
				break;

			case W:

			case UP:
				mView.mUp = on;
				break;

			case S:

			case DOWN:
				mView.mDown = on;
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

	private void updatePcSprite()
	{
		if(mView.mUp && mView.mDown && mView.mLeft && mView.mRight)
		{
			if(mPlayer.getImage().equals(mStandDownImg))
				return;

			mPlayer.setImage(mStandDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mRight && mView.mLeft && mView.mUp)
		{
			if(mPlayer.getImage().equals(mWalkUpImg))
				return;

			mPlayer.setImage(mWalkUpImg);
			mView.setPlayerFacing(Direction.Up);
		}

		else if(mView.mRight && mView.mLeft && mView.mDown)
		{
			if(mPlayer.getImage().equals(mWalkDownImg))
				return;

			mPlayer.setImage(mWalkDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mUp && mView.mDown && mView.mRight)
		{
			if(mPlayer.getImage().equals(mWalkRightImg))
				return;

			mPlayer.setImage(mWalkRightImg);
			mView.setPlayerFacing(Direction.Right);
		}

		else if(mView.mUp && mView.mDown && mView.mLeft)
		{
			if(mPlayer.getImage().equals(mWalkLeftImg))
				return;

			mPlayer.setImage(mWalkLeftImg);
			mView.setPlayerFacing(Direction.Left);
		}

		else if((mView.mUp && mView.mDown) || (mView.mRight && mView.mLeft))
		{
			mPlayer.setImage(mStandDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mRight && !mPlayer.getImage().equals(mWalkRightImg))
		{
			mPlayer.setImage(mWalkRightImg);
			mView.setPlayerFacing(Direction.Right);
		}

		else if(mView.mLeft && !mPlayer.getImage().equals(mWalkLeftImg))
		{
			mPlayer.setImage(mWalkLeftImg);
			mView.setPlayerFacing(Direction.Left);
		}

		else if(mView.mDown && !mPlayer.getImage().equals(mWalkDownImg) && !mView.mLeft && !mView.mRight)
		{
			mPlayer.setImage(mWalkDownImg);
			mView.setPlayerFacing(Direction.Down);
		}

		else if(mView.mUp && !mPlayer.getImage().equals(mWalkUpImg) && !mView.mLeft && !mView.mRight)
		{
			mPlayer.setImage(mWalkUpImg);
			mView.setPlayerFacing(Direction.Up);
		}

		else if(!mView.mUp && !mView.mDown && !mView.mRight && !mView.mLeft)
		{
			switch(mView.getPlayerFacing())
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
