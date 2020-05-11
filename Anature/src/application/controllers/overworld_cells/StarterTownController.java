package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.WarpPoints;
import application.models.StarterTownModel;
import application.player.Player;
import application.views.elements.TrainerSprite;
import application.views.overworld_cells.StarterTownCell;
import javafx.scene.input.KeyEvent;

public class StarterTownController extends AbstractController
{
	public StarterTownController(LoggerStartUp logger, StarterTownCell view, StarterTownModel model, Player playerModel)
	{
		super(logger, view, playerModel);

		if(model == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Making Starter Town Model null.");
			throw new IllegalArgumentException("Making Starter Town Model null.");
		}
		
		for(TrainerSprite trainer : mView.getTrainerSprites())
		{
			if(trainer.getName().compareTo("Kelly") == 0)
			{
				trainer.setTrainerModel(model.getKelly());
				break;
			}
		}
	}

	@Override
	protected void timerHook(double elapsedSeconds)
	{
		
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		
	}

	@Override
	public void movePlayer(WarpPoints warpPoint)
	{
		if(warpPoint == null)
			return;
		
		switch(warpPoint)
		{
			case Starter_Town_Path_1_Exit:
				mPlayerView.setX(1588);
				mPlayerView.setY(276);
				mView.setPlayerFacing(Direction.Left);
				break;
				
			case Starter_Town_House_1:
				mPlayerView.setX(545);
				mPlayerView.setY(542);
				mView.setPlayerFacing(Direction.Down);
				break;

			default:
				break;
		}
	}
}
