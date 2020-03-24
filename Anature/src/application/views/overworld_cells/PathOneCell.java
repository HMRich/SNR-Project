package application.views.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.SceneType;
import application.enums.TrainerIds;
import application.enums.WarpPoints;
import application.views.elements.ImageLayer;
import application.views.elements.TrainerSprite;
import javafx.scene.image.Image;

public class PathOneCell extends AbstractCell
{
	public PathOneCell(LoggerStartUp logger)
	{
		super(logger, 468, 427);
	}

	@Override
	protected void addToBackground()
	{
		
	}

	@Override
	protected void addToForeground()
	{
		
	}

	@Override
	protected ImageLayer createBackground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/path1/path_1_background.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected ImageLayer createForeground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/path1/path_1_foreground.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected void createCollisons()
	{
		// Border
		addCollisionRectangleUsingCoords(0, 5, 1642, 90);
		addCollisionRectangleUsingCoords(1603, 50, 1701, 1506);
		addCollisionRectangleUsingCoords(1312, 1445, 1691, 1554);
		addCollisionRectangleUsingCoords(113, 1447, 1022, 1553);
		addCollisionRectangleUsingCoords(0, 414, 151, 1471);

		// Signs
		addCollisionRectangleUsingCoords(60, 171, 77, 218);
		addCollisionRectangleUsingCoords(1271, 1488, 1285, 1532);

		// Trees
		addCollisionRectangleUsingCoords(1258, 546, 1406, 616);
	}

	@Override
	protected void createGrassPatches()
	{
		addGrassPatchRectangle(146, 436, 1385, 685);
		
		addGrassPatchRectangle(414, 911, 1590, 1010);
		addGrassPatchRectangle(414, 998, 1527, 1060);
		addGrassPatchRectangle(414, 1053, 1467, 1108);
	}

	@Override
	protected void createWarpPoints()
	{
		addWarpPoint(SceneType.Starter_Town, WarpPoints.Starter_Town_Path_1_Exit, 0, 235, 23, 161);
		addWarpPoint(SceneType.Grass_Town, WarpPoints.Grass_Town_Path_1, 1038, 1537, 209, 16);
	}

	@Override
	protected void createTrainers()
	{
		double[][] keyFrames = new double[][]
		{
			{1388, 177},
			{158, 177},
			{158, 63},
			{1388, 63},
		};
		
		int startingFrame = 1;

		String[] dialogue = new String[] {"Hi there, my name is Kelly!", "Let's Battle!"};
		
		TrainerSprite mKelly = new TrainerSprite(keyFrames[0][0], keyFrames[0][1], TrainerIds.Kelly, Direction.Down, mZoom, mShowCollision, keyFrames, startingFrame, dialogue, true, "Kelly");
		addTrainer(mKelly);
	}
}
