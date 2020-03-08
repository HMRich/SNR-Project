package application.views.overworld_cells;

import application.LoggerStartUp;
import application.enums.SceneType;
import application.enums.WarpPoints;
import application.views.elements.ImageLayer;
import javafx.scene.image.Image;

public class GrassTownCell extends AbstractCell
{
	public GrassTownCell(LoggerStartUp logger)
	{
		super(logger, 427, 468);
	}

	@Override
	protected void addToBackground()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addToForeground()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ImageLayer createBackground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/grass_town_background.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected ImageLayer createForeground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/grass_town_foreground.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected void createCollisons()
	{
		addCollisionRectangle(136, 290, 309, 145);
		addCollisionRectangle(1242, 382, 236, 105);
		addCollisionRectangle(860, 793, 377, 113);
		addCollisionRectangle(800, 1375, 252, 127);
		addCollisionRectangle(202, 1201, 236, 102);
		
		addCollisionRectangle(31, 0, 897, 119);
		addCollisionRectangle(1211, 0, 304, 119);
		addCollisionRectangle(1515, 100, 40, 1525);
		addCollisionRectangle(51, 1640, 1500, 60);
		addCollisionRectangle(0, 104, 51, 1540);
	}

	@Override
	protected void createGrassPatches()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createWarpPoints()
	{
		addWarpPoint(SceneType.Path_1, WarpPoints.Path_1_Grass_Town_Exit, 955, 0, 243, 60);
	}
}
