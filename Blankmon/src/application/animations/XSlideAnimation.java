package application.animations;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class XSlideAnimation extends Transition
{
	private ImageView mImageView;
	private double mStart, mEnd;
	
	public XSlideAnimation(ImageView imageView, Duration duration, double start, double end)
	{
		mImageView = imageView;
		setCycleDuration(duration);
		
		mStart = start;
		mEnd = end;
	}
	
	
	@Override
	protected void interpolate(double frac)
	{
		mImageView.layoutXProperty().bind(mImageView.getScene().widthProperty().divide((mEnd - mStart) * frac + mStart));
	}
}
