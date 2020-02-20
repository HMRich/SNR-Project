package application.views;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public class AnatureSlotHpBar extends ProgressBar
{
	private DoubleProperty mCurrValue, mTotalValue;
	private Pane mToBindTo;
	
	public AnatureSlotHpBar(DoubleProperty currValue, DoubleProperty totalValue, Pane toBindTo)
	{
		mCurrValue = currValue;
		mTotalValue = totalValue;
		mToBindTo = toBindTo;
		
		progressProperty().bind(mCurrValue.divide(mTotalValue));
		prefWidthProperty().bind(mToBindTo.widthProperty().divide(2.5));
		prefHeightProperty().bind(mToBindTo.heightProperty().divide(4));
		
		getStylesheets().add("/resources/css/BattleStyle.css");
		getStyleClass().add("green-bar");
		mCurrValue.addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				double progress = newValue.doubleValue();

				getStyleClass().removeAll("red-bar", "orange-bar", "yellow-bar", "green-bar");
				if(progress < 20)
					getStyleClass().add("red-bar");
				
				else if(progress < 40)
					getStyleClass().add("orange-bar");
				
				else if(progress < 60)
					getStyleClass().add("yellow-bar");
				
				else
					getStyleClass().add("green-bar");
			}
		});
	}
	
	public void bindX(double toDivideBy)
	{
		layoutXProperty().bind(mToBindTo.widthProperty().divide(toDivideBy));
	}
	
	public void bindY(double toDivideBy)
	{
		layoutYProperty().bind(mToBindTo.heightProperty().divide(toDivideBy));
	}
}
