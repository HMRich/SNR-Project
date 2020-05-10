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
		super(logger, 468, 427, SceneType.Grass_Town);
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
		addCollisionRectangle(150, 263, 341, 136);
		addCollisionRectangle(1361, 347, 257, 98);
		addCollisionRectangle(942, 722, 416, 104);
		addCollisionRectangle(221, 1096, 259, 92);
		addCollisionRectangle(878, 1256, 275, 116);
		
		addCollisionRectangle(16, 0, 1006, 120);
		addCollisionRectangle(1327, 0, 369, 120);
		addCollisionRectangle(1662, 76, 41, 1405);
		addCollisionRectangle(51, 1491, 1642, 41);
		addCollisionRectangle(0, 74, 60, 1417);
	}

	@Override
	protected void createGrassPatches()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createWarpPoints()
	{
		addWarpPoint(SceneType.Path_1, WarpPoints.Path_1_Grass_Town_Exit, 1021, 0, 301, 60);
		addWarpPoint(SceneType.Rest_Station_Grass_Town, WarpPoints.Rest_Station_Grass_Town, 278, 391.7, 355 - 278, 410 - 391);
	}

	@Override
	protected void createTrainers()
	{
		// TODO Auto-generated method stub
		
	}
}
