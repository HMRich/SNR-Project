package application.views.elements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;

public class XpBar extends ProgressBar
{
	private DoubleProperty mCurrValue, mTotalValue;
	private Scene mScene;

	public XpBar(DoubleProperty currValue, DoubleProperty totalValue, Scene sceneToBindTo)
	{
		mCurrValue = currValue;
		mTotalValue = totalValue;
		mScene = sceneToBindTo;

		ChangeListener<Number> xpChange = new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				double progress = mCurrValue.getValue() / mTotalValue.getValue();
				setProgress(progress);
			}
		};
		
		mCurrValue.addListener(xpChange);
		mTotalValue.addListener(xpChange);
		
		prefWidthProperty().bind(mScene.widthProperty().divide(5.4));
		prefHeightProperty().bind(mScene.heightProperty().divide(65));

		getStylesheets().add("/resources/css/BattleStyle.css");
		getStyleClass().add("deep-sky-blue-bar");
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
