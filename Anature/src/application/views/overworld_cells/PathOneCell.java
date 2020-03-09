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
	public TrainerSprite mKelly;
	
	public PathOneCell(LoggerStartUp logger)
	{
		super(logger, 427, 468);
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
		addCollisionRectangle(0, 8, 1471, 90);
		addCollisionRectangle(1468, 87, 72, 1485);
		addCollisionRectangle(0, 452, 139, 1107);
		addCollisionRectangle(120, 1576, 820, 120);
		addCollisionRectangle(1197, 1576, 327, 120);

		// Signs
		addCollisionRectangle(1157, 1637, 17, 42);
		addCollisionRectangle(56, 191, 14, 46);

		// Trees
		addCollisionRectangle(1146, 605, 133, 92);
	}

	@Override
	protected void createGrassPatches()
	{
		addGrassPatchRectangle(133, 474, 1134, 279);
		addGrassPatchRectangle(378, 993, 1100, 116);
		addGrassPatchRectangle(378, 1108, 1016, 58);
		addGrassPatchRectangle(378, 1163, 959, 58);
	}

	@Override
	protected void createWarpPoints()
	{
		addWarpPoint(SceneType.Starter_Town, WarpPoints.Starter_Town_Path_1_Exit, 0, 256, 13, 207);
		addWarpPoint(SceneType.Starter_Town, WarpPoints.Starter_Town_House_1, 953, 1682, 183, 21); // TODO only for demo
	}

	@Override
	protected void createTrainers()
	{
		double[][] keyFrames = new double[][]
		{
			{1388, 177},
			{158, 177},
			{158, 133},
			{1388, 133},
		};
		
		Direction[] keyFrameDirections = new Direction[]
		{
			Direction.Down, Direction.Left, Direction.Up, Direction.Right
		};
		
		int startingFrame = 1;

		String[] dialogue = new String[] {"Hi there, my name is Kelly!", "Let's Battle!"};
		
		mKelly = new TrainerSprite(keyFrames[0][0], keyFrames[0][1], TrainerIds.Kelly, Direction.Down, mZoom, mShowCollision, keyFrames, keyFrameDirections, startingFrame, dialogue, true, "Kelly");
		addTrainer(mKelly);
	}
}
