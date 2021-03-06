package application.views.elements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public class AnatureSlotHpBar extends ProgressBar
{
	private DoubleProperty mCurrValue, mTotalValue;
	private Pane mToBindTo;

	public AnatureSlotHpBar(double currValue, double totalValue, Pane toBindTo)
	{
		mCurrValue = new SimpleDoubleProperty(currValue);
		mTotalValue = new SimpleDoubleProperty(totalValue);
		mToBindTo = toBindTo;

		prefWidthProperty().bind(mToBindTo.widthProperty().divide(2.5));
		prefHeightProperty().bind(mToBindTo.heightProperty().divide(4));

		getStylesheets().add("/resources/css/BattleStyle.css");
		getStyleClass().add("green-bar");
		progressProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				double progress = newValue.doubleValue() * 100;

				getStyleClass().removeAll("red-bar", "orange-bar", "yellow-bar", "green-bar");
				if(progress < 20)
				{
					getStyleClass().add("red-bar");
				}

				else if(progress < 40)
				{
					getStyleClass().add("orange-bar");
				}

				else if(progress < 60)
				{
					getStyleClass().add("yellow-bar");
				}

				else
				{
					getStyleClass().add("green-bar");
				}
			}
		});
		
		ChangeListener<Number> hpListener = (observable, oldValue, newValue) ->
		{
			setProgress(mCurrValue.divide(mTotalValue).get());
		};
		
		mCurrValue.addListener(hpListener);
		mTotalValue.addListener(hpListener);
	}

	public void bindX(double toDivideBy)
	{
		layoutXProperty().bind(mToBindTo.widthProperty().divide(toDivideBy));
	}

	public void bindY(double toDivideBy)
	{
		layoutYProperty().bind(mToBindTo.heightProperty().divide(toDivideBy));
	}

	public void updateProgress(double currValue, double totalValue)
	{
		mTotalValue.set(totalValue);
		mCurrValue.set(currValue);
	}
}
