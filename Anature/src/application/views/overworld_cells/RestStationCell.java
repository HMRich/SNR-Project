package application.views.overworld_cells;

import application.LoggerStartUp;
import application.enums.Direction;
import application.enums.SceneType;
import application.enums.TrainerIds;
import application.enums.WarpPoints;
import application.views.elements.ImageLayer;
import application.views.elements.TrainerSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RestStationCell extends AbstractCell
{
	private Rectangle mHealBox, mClerkBox;
	
	public RestStationCell(LoggerStartUp logger, SceneType restStationId)
	{
		super(logger, 468 / 2, 427 / 2, restStationId);
	}

	@Override
	protected void createTrainers()
	{
		String[] nurseDialogue = new String[] {"Hi there, I'm the local nurse!", "I'll heal your Anatures!"};
		
		TrainerSprite nurse = new TrainerSprite(118, 283, TrainerIds.Nurse, Direction.Down, mZoom, mShowCollision, new double[0][], 0, nurseDialogue, false, "Nurse");
		addTrainer(nurse);
		
		String[] clerkDialogue = new String[] {"Hi there, I'm the local store clerk!", "Are you interested in some of my wares?"};
		
		TrainerSprite clerk = new TrainerSprite(678, 283, TrainerIds.Clerk, Direction.Down, mZoom, mShowCollision, new double[0][], 0, clerkDialogue, false, "Clerk");
		addTrainer(clerk);
	}

	@Override
	protected void addToBackground()
	{
		mHealBox = new Rectangle(121, 393, 176 - 121, 412 - 393);
		mHealBox.visibleProperty().bind(mShowCollision);
		mHealBox.setFill(Color.RED);
		
		mClerkBox = new Rectangle(681, 393, 176 - 121, 412 - 393);
		mClerkBox.visibleProperty().bind(mShowCollision);
		mClerkBox.setFill(Color.RED);
		
		mBackground.getChildren().addAll(mHealBox, mClerkBox);
	}

	@Override
	protected void addToForeground()
	{

	}

	@Override
	protected ImageLayer createBackground()
	{
		String path = "/resources/images/overworld/rest_station/Rest_Station_Background.png";
		Image bg = new Image(getClass().getResource(path).toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected ImageLayer createForeground()
	{
		String path = "/resources/images/overworld/rest_station/Rest_Station_Foreground.png";
		Image bg = new Image(getClass().getResource(path).toExternalForm(), 1000.0, 1000.0, true, false);
		return new ImageLayer(mWidth, mHeight, mZoom, bg);
	}

	@Override
	protected void createCollisons()
	{
		addCollisionRectangle(13, 668, 845 - 13, 687 - 668); // Down Border
		addCollisionRectangle(836, 190, 846 - 836, 687 - 190); // Right Border
		addCollisionRectangle(301, 242, 551 - 301, 276 - 242); // Up Border

		addCollisionRectangle(390, 248, 457 - 390, 309 - 248); // AC Box
		
		addCollisionRectangleUsingCoords(12, 358, 303, 400); // Red Counter Bot
		addCollisionRectangleUsingCoords(273, 248, 303, 376); // Red Counter Right
		addCollisionRectangleUsingCoords(549, 345, 846, 400); // Green Counter Bot
		addCollisionRectangleUsingCoords(549, 248, 578, 350); // Green Counter Right
	}

	@Override
	protected void createGrassPatches()
	{
		
	}

	@Override
	protected void createWarpPoints()
	{
		if(mId == null)
		{
			mId = SceneType.Rest_Station_Grass_Town;
		}
		
		SceneType sceneToChangeTo = null;
		WarpPoints warpPoint = null;
		
		switch(mId)
		{
			case Rest_Station_Grass_Town:
				sceneToChangeTo = SceneType.Grass_Town;
				warpPoint = WarpPoints.Rest_Station_Grass_Town;
				break;
			
			default:
				break;
		}
		
		addWarpPoint(sceneToChangeTo, warpPoint, 366, 658, 483 - 366, 670 - 658);
	}
	
	public boolean interactHealStation()
	{
		return mPlayerSprite.getBoxBounds().intersects(mHealBox.getBoundsInLocal());
	}
	
	public boolean interactClerkStation()
	{
		return mPlayerSprite.getBoxBounds().intersects(mClerkBox.getBoundsInLocal());
	}
}
