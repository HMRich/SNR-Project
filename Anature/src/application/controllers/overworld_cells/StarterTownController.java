package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.Startup;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.WarpPoints;
import application.models.StarterTownModel;
import application.trainers.Trainer;
import application.views.overworld_cells.StarterTownCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StarterTownController extends AbstractController
{
	private StarterTownCell mView;
	private Trainer mKellyTrainer;

	public StarterTownController(LoggerStartUp logger, StarterTownCell view, StarterTownModel model)
	{
		super(logger, view);

		if(model == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Making Starter Town Model null.");
			throw new IllegalArgumentException("Making Starter Town Model null.");
		}

		mView = view;
		mKellyTrainer = model.getKelly();
	}

	@Override
	protected void timerHook(double elapsedSeconds)
	{
		int trainerIndex = mView.mKelly.getIndex(mView.getBackground());
		int playerIndex = mPlayer.getIndex(mView.getBackground());

		if(mPlayer.getBoxY() > mView.mKelly.getCollisionY() && playerIndex < trainerIndex)
		{
			mPlayer.removeFromContainer(mView.getBackground());
			mPlayer.addToContainer(mView.getBackground(), trainerIndex + 1);
		}

		else if(mPlayer.getBoxY() <= mView.mKelly.getCollisionY() && playerIndex > trainerIndex)
		{
			mPlayer.removeFromContainer(mView.getBackground());
			mPlayer.addToContainer(mView.getBackground(), trainerIndex);
		}
		
		mView.mKelly.update(mPlayer, mSpeed, elapsedSeconds);
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		if(event.getCode() == KeyCode.E)
		{
			if(mView.mKelly.interact(mPlayer, mView.getPlayerFacing()) && mClickQueue.isEmpty())
			{
				mView.mCanMove = false;

				if(mKellyTrainer.canBattle())
				{
					mView.showDialogue("Hi there, my name is Kelly!");
					mClickQueue.enqueue(() -> mView.showDialogue("Let's battle!"));
					mClickQueue.enqueue(() ->
					{
						mView.mRight = false;
						mView.mLeft = false;
						mView.mDown = false;
						mView.mUp = false;
						mView.mCanMove = true;
						mView.hideDialogue();
						
						Startup.startBattle(mKellyTrainer);
					});
				}
				
				else
				{
					mView.showDialogue("Nice battle!");
					mClickQueue.enqueue(() ->
					{
						mView.hideDialogue();
						mView.mCanMove = true;
					});
				}
			}
			
			else
			{
				Runnable toRun = mClickQueue.dequeue();
				
				if(toRun != null)
				{
					toRun.run();
				}
			}
		}
	}

	@Override
	public void movePlayer(WarpPoints warpPoint)
	{
		if(warpPoint == null)
			return;
		
		switch(warpPoint)
		{
			case Starter_Town_Path_1_Exit:
				mPlayer.setX(1456);
				mPlayer.setY(298);
				mView.setPlayerFacing(Direction.Left);
				break;
				
			case Starter_Town_House_1:
				mPlayer.setX(485);
				mPlayer.setY(599);
				mView.setPlayerFacing(Direction.Down);
				break;

			default:
				break;
		}
	}
}
