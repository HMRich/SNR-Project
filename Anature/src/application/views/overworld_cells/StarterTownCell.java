package application.views.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.SceneType;
import application.enums.WarpPoints;
import application.views.elements.ImageLayer;
import application.views.elements.TrainerSprite;
import javafx.scene.image.Image;

public class StarterTownCell extends AbstractCell
{
	public TrainerSprite mKelly;

	public StarterTownCell(LoggerStartUp logger)
	{
		super(logger, 468, 427);
	}

	@Override
	protected void addToBackground()
	{
		Image up = new Image(getClass().getResource("/resources/images/trainers/kelly/up_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		Image down = new Image(getClass().getResource("/resources/images/trainers/kelly/down_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		Image right = new Image(getClass().getResource("/resources/images/trainers/kelly/right_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		Image left = new Image(getClass().getResource("/resources/images/trainers/kelly/left_stand.png").toExternalForm(), 100.0, 100.0, true, false);
		mKelly = new TrainerSprite(427, 1310, down, up, left, right, Direction.Down, mZoom, mShowCollision);

		mCollisions.add(mKelly.getCollisionBox());
		mKelly.addToContainer(mBackground);
	}

	@Override
	protected void addToForeground()
	{
		// Nothing to add
	}

	@Override
	protected ImageLayer createBackground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/starter_town_background.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected ImageLayer createForeground()
	{
		Image bg = new Image(getClass().getResource("/resources/images/overworld/starter_town_foreground.png").toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected void createCollisons()
	{
		// Border
		addCollisionRectangleUsingCoords(279, 51, 1584, 99);
		addCollisionRectangleUsingCoords(1563, 75, 1702, 192);
		addCollisionRectangleUsingCoords(1563, 398, 1702, 1514);
		addCollisionRectangleUsingCoords(86, 1486, 1665, 1530);
		addCollisionRectangleUsingCoords(0, 564, 104, 1503);
		addCollisionRectangleUsingCoords(46, 538, 304, 590);
		addCollisionRectangleUsingCoords(220, 43, 304, 585);

		// Houses
		addCollisionRectangleUsingCoords(487, 464, 765, 583);
		addCollisionRectangleUsingCoords(1179, 721, 1455, 838);
		addCollisionRectangleUsingCoords(260, 1078, 534, 1196);
		
		// Tree
		addCollisionRectangleUsingCoords(1043, 1045, 1192, 1129);
	}

	@Override
	protected void createGrassPatches()
	{
		// None to add
	}

	@Override
	protected void createWarpPoints()
	{
		addWarpPoint(SceneType.Path_1, WarpPoints.Path_1_Starter_Town_Exit, 1667, 239, 33, 155);
	}
}
