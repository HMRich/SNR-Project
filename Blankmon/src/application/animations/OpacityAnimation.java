package application.animations;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class OpacityAnimation extends Transition
{
	private ImageView mImageView;
	private boolean mIsReversed;
	
	public OpacityAnimation(ImageView imageView, Duration duration, boolean isReversed)
	{
		mImageView = imageView;
		mIsReversed = isReversed;
		setCycleDuration(duration);
	}
	
	
	@Override
	protected void interpolate(double frac)
	{
		if(!mIsReversed)
			frac = 1 - frac;
		
		mImageView.setOpacity(frac);
	}
}
