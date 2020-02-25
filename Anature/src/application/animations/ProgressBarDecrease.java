package application.animations;

import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class ProgressBarDecrease extends Transition
{
	private DoubleProperty mBoundValue;
	private double mNewValue, mOriginalValue;
	
	public ProgressBarDecrease(DoubleProperty boundValue, Duration duration, double newValue)
	{
		mBoundValue = boundValue;
		mOriginalValue = mBoundValue.get();
		mNewValue = newValue;
		setCycleDuration(duration);
	}
	
	
	@Override
	protected void interpolate(double frac)
	{
		double value = frac * mNewValue;
		mBoundValue.set(mOriginalValue - value);
	}
}
