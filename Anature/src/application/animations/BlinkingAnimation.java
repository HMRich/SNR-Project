package application.animations;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BlinkingAnimation extends Transition
{
	private ImageView mImageView;
	
	public BlinkingAnimation(ImageView imageView, Duration pauseDuration)
	{
		mImageView = imageView;
		setCycleDuration(pauseDuration);
		setCycleCount(Animation.INDEFINITE);
	}
	
	@Override
	protected void interpolate(double frac)
	{
		if(frac >= 0.8)
		{
			mImageView.setOpacity(0);
		}
		
		else
		{
			mImageView.setOpacity(100);
		}
	}
}
