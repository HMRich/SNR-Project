package application.views.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.SceneType;
import application.enums.TrainerIds;
import application.enums.WarpPoints;
import application.views.elements.ImageLayer;
import application.views.elements.TrainerSprite;
import javafx.scene.image.Image;

public class StarterTownCell extends AbstractCell
{
	public StarterTownCell(LoggerStartUp logger)
	{
		super(logger, 427, 468);
	}

	@Override
	protected void addToBackground()
	{
		// Nothing to add
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
		addCollisionRectangle(277, 55, 1151, 70);
		addCollisionRectangle(1428, 55, 125, 160);
		addCollisionRectangle(1428, 440, 125, 1213);
		addCollisionRectangle(101, 1640, 1352, 15);
		addCollisionRectangle(90, 680, 15, 1000);
		addCollisionRectangle(90, 660, 200, 15);
		addCollisionRectangle(277, 37, 15, 620);

		// Houses
		addCollisionRectangle(446, 510, 250, 127);
		addCollisionRectangle(1077, 794, 250, 127);
		addCollisionRectangle(236, 1180, 250, 127);
		
		// Tree
		addCollisionRectangle(948, 1144, 137, 92);
	}

	@Override
	protected void createGrassPatches()
	{
		// None to add
	}

	@Override
	protected void createWarpPoints()
	{
		addWarpPoint(SceneType.Path_1, WarpPoints.Path_1_Starter_Town_Exit, 1518, 267, 35, 168);
	}

	@Override
	protected void createTrainers()
	{
		String[] dialogue = new String[] {"Hi there, my name is Kelly!", "Let's Battle!"};
		
		TrainerSprite mKelly = new TrainerSprite(427, 1310, TrainerIds.Kelly, Direction.Down, mZoom, mShowCollision, new double[0][], 0, dialogue, true, "Kelly");
		addTrainer(mKelly);
	}
}
