package application.animations;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class OpacityAnimation extends Transition
{
	private ImageView mImageView;
	
	public OpacityAnimation(ImageView imageView, Duration duration)
	{
		mImageView = imageView;
		setCycleDuration(duration);
	}
	
	
	@Override
	protected void interpolate(double frac)
	{
		mImageView.setOpacity(frac);
	}
}
