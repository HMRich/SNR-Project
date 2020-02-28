package application.views.elements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;

public class HpBar extends ProgressBar
{
	private DoubleProperty mCurrValue, mTotalValue;
	private Scene mScene;

	public HpBar(DoubleProperty currValue, DoubleProperty totalValue, Scene sceneToBindTo)
	{
		mCurrValue = currValue;
		mTotalValue = totalValue;
		mScene = sceneToBindTo;

		progressProperty().bind(mCurrValue.divide(mTotalValue));
		prefWidthProperty().bind(mScene.widthProperty().divide(9));
		prefHeightProperty().bind(mScene.heightProperty().divide(55));

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
	}

	public void bindX(double toDivideBy)
	{
		layoutXProperty().bind(mScene.widthProperty().divide(toDivideBy));
	}

	public void bindY(double toDivideBy)
	{
		layoutYProperty().bind(mScene.heightProperty().divide(toDivideBy));
	}
}
