package application.controllers.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.WarpPoints;
import application.views.overworld_cells.GrassTownCell;
import javafx.scene.input.KeyEvent;

public class GrassTownController extends AbstractController
{
	public GrassTownController(LoggerStartUp logger, GrassTownCell view)
	{
		super(logger, view);
	}

	@Override
	protected void timerHook()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyPressHook(KeyEvent event)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePlayer(WarpPoints warpPoint)
	{
		if(warpPoint == null)
			return;
		
		switch(warpPoint)
		{
			case Grass_Town_Path_1:
				mPlayer.setX(1030);
				mPlayer.setY(25);
				mView.setPlayerFacing(Direction.Down);
				break;

			default:
				break;
		}
	}
}
