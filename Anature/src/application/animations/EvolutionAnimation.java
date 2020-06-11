package application.animations;

import javafx.animation.Transition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class EvolutionAnimation extends Transition
{
	private ImageView mImageView;
	
	public EvolutionAnimation(ImageView imageView, Duration binkDuration)
	{
		mImageView = imageView;
		setCycleDuration(binkDuration);
	}
	
	@Override
	protected void interpolate(double frac)
	{
		double brightnessValue = frac < 0.5 ? frac * 2 : (1.0 - frac) * 2;
		
		ColorAdjust darken = new ColorAdjust();
		darken.setBrightness(brightnessValue);
		mImageView.setEffect(darken);
	}
}
