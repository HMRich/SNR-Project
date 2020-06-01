package application.animations;

import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AnacubeThrow extends Transition
{
	private ImageView mImageView;
	private double mStartX, mStartY;
	private double mEndX, mEndY;
	
	public AnacubeThrow(ImageView imageView, Duration time)
	{
		mImageView = imageView;
		
		setCycleDuration(time);
		
		mStartX = 3.72; // 344, 440
		mStartY = 1.636;
		
		mEndX = 1.59; // 804, 207
		mEndY = 3.478;
	}
	
	@Override
	protected void interpolate(double frac)
	{
		mImageView.layoutXProperty().bind(mImageView.getScene().widthProperty().divide((mEndX - mStartX) * frac + mStartX));
		mImageView.layoutYProperty().bind(mImageView.getScene().heightProperty().divide((mEndY - mStartY) * frac + mStartY));
	}
}
