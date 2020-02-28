package application.controllers.overworld_cells;

import application.views.overworld_cells.AbstractCell;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AbstractController
{
	private AbstractCell mView;
	private final int mSpeed = 300; // pixels / second
	private double mSpeedMultiplier;
	private AnimationTimer mTimer;
	
	public AbstractController()
	{
		mSpeedMultiplier = 1;
		
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
}
