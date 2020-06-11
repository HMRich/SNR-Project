package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.controllers.LoggerController;
import application.enums.Direction;
import application.enums.LoggingTypes;
import application.enums.WarpPoints;
import application.models.PathOneModel;
import application.player.Player;
import application.views.elements.TrainerSprite;
import application.views.overworld_cells.PathOneCell;
import javafx.scene.input.KeyEvent;

public class PathOneController extends AbstractController
{
	public PathOneController(LoggerStartUp logger, PathOneCell view, PathOneModel model, Player playerModel)
	{
		super(logger, view, playerModel);

		if(model == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Making Path One Model null.");
			throw new IllegalArgumentException("Making Path One Model null.");
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
			case Path_1_Starter_Town_Exit:
				mPlayerView.setX(45);
				mPlayerView.setY(269);
				mView.setPlayerFacing(Direction.Right);
				break;
				
			case Path_1_Grass_Town_Exit:
				mPlayerView.setX(1131);
				mPlayerView.setY(1433);
				mView.setPlayerFacing(Direction.Up);
				break;

			default:
				break;
		}
	}
}
