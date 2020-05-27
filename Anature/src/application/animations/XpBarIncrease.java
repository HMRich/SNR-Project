package application.animations;

import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.util.Duration;

public class XpBarIncrease extends Transition
{
	private DoubleProperty mBoundValue;
	private double mNewValue, mOriginalValue;
	
	public XpBarIncrease(DoubleProperty boundValue, Duration duration, double newValue)
	{
		mBoundValue = boundValue;
		mOriginalValue = mBoundValue.get();
		mNewValue = newValue;
		setCycleDuration(duration);
	}
	
	
	@Override
	protected void interpolate(double frac)
	{
		double toIncreaseBy = mNewValue - mOriginalValue;		
		double value = frac * toIncreaseBy;
		mBoundValue.set(mOriginalValue + value);
	}
}
