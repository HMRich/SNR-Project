package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.Startup;
import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.models.StarterTownModel;
import application.trainers.Trainer;
import application.views.overworld_cells.StarterTownCell;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class StarterTownController extends AbstractController
{
	private StarterTownCell mView;
	private Trainer mKellyTrainer;
	
	public StarterTownController(LoggerStartUp logger, StarterTownCell view, StarterTownModel model)
	{
		super(logger, view);
		
		if(model == null)
		{
			LoggerController.logEvent(LoggingTypes.Default, "Making Starter Town Model null.");
			throw new IllegalArgumentException("Making Starter Town Model null.");
		}
		
		mView = view;
		mKellyTrainer = model.getKelly();
	}

	@Override
	protected void timerHook()
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
	}

	@Override
	protected boolean xCollisionCheck()
	{
		Bounds left = mPlayer.getLeftBounds();
		Bounds right = mPlayer.getRightBounds();
		
		for(Rectangle toCheck : mView.getCollisions())
		{
			boolean rightCheck = right.intersects(toCheck.getBoundsInParent());
			boolean leftCheck = left.intersects(toCheck.getBoundsInParent());
			
			if((rightCheck && ! leftCheck) || (leftCheck && !rightCheck))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	protected boolean yCollisionCheck()
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

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		if(event.getCode() == KeyCode.E)
		{
			if(mView.mKelly.interact(mPlayer, mView.getPlayerFacing()) != null)
			{
//				System.out.println("ACTIVATE BATTLE");
//				mRight = false;
//				mLeft = false;
//				mDown = false;
//				mUp = false;
//				
//				Startup.changeScene(SceneType.Battle);
			}
		}
		
		else if(event.getCode() == KeyCode.I)
		{
			mView.mRight = false;
			mView.mLeft = false;
			mView.mDown = false;
			mView.mUp = false;
			
			Startup.startBattle(mKellyTrainer);
		}
	}
}
