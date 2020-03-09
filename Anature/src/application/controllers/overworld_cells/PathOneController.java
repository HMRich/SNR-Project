package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.WarpPoints;
import application.views.overworld_cells.PathOneCell;
import javafx.scene.input.KeyEvent;

public class PathOneController extends AbstractController
{
	public PathOneController(LoggerStartUp logger, PathOneCell view)
	{
		super(logger, view);
	}

	@Override
	protected void timerHook()
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
				mPlayer.setX(45);
				mPlayer.setY(269);
				mView.setPlayerFacing(Direction.Right);
				break;

			default:
				break;
		}
	}
}
