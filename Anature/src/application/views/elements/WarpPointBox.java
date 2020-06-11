package application.views.elements;

import application.enums.SceneType;
import application.enums.WarpPoints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class WarpPointBox extends Rectangle
{
	private SceneType mSceneType;
	private WarpPoints mToWarpTo;
	
	public WarpPointBox(WarpPoints toWarpTo, SceneType sceneType, double x, double y, double width, double height)
	{
		super(x, y, width, height);
		
		setFill(Color.PURPLE);
		mSceneType = sceneType;
		mToWarpTo = toWarpTo;
	}
	
	public WarpPoints getWarpPoint()
	{
		return mToWarpTo;
	}
	
	public SceneType getSceneType()
	{
		return mSceneType;
	}
}
