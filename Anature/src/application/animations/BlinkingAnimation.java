package application.animations;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.util.Duration;

public class BlinkingAnimation extends Transition
{
	private Node mNode;
	
	public BlinkingAnimation(Node node, Duration pauseDuration)
	{
		mNode = node;
		setCycleDuration(pauseDuration);
		setCycleCount(Animation.INDEFINITE);
	}
	
	@Override
	protected void interpolate(double frac)
	{
		if(frac >= 0.8)
		{
			mNode.setOpacity(0);
		}
		
		else
		{
			mNode.setOpacity(100);
		}
	}
}
