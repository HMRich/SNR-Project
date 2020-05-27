package application.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SideMenuController
{
	@FXML Pane mPane;
	@FXML GridPane mGrid;
	@FXML ImageView mBg, mSelectImg;
	@FXML Label mDexTxt, mAnatureTxt, mBackpackTxt, mSettingsTxt, mSaveTxt, mQuitTxt;
	
	public void updateBinds(Scene scene, ObjectProperty<Font> fontProperty, BooleanProperty visibilityProperty)
	{
		mBg.fitWidthProperty().bind(mPane.prefWidthProperty());
		mBg.fitHeightProperty().bind(mPane.prefHeightProperty());
		
		mGrid.prefWidthProperty().bind(mBg.fitWidthProperty().divide(1.042));
		mGrid.prefHeightProperty().bind(mBg.fitHeightProperty().divide(1.043));
		mGrid.layoutXProperty().bind(mBg.fitWidthProperty().divide(22.857));
		mGrid.layoutYProperty().bind(mBg.fitHeightProperty().divide(44.142));
		
		ChangeListener<Number> selectedImgSize = new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				if(mGrid.getLayoutX() < 11)
				{
					double toUse = mGrid.getLayoutX() * 20 / 7;

					mSelectImg.setFitWidth(toUse);
					mSelectImg.setFitHeight(toUse);
				}
				
				else
				{
					double toUse = mBg.getFitWidth() / 4.324;
					
					if(mBg.getFitHeight() / 6.118 < toUse)
					{
						toUse = mBg.getFitHeight() / 6.118;
					}
					
					mSelectImg.setFitWidth(toUse);
					mSelectImg.setFitHeight(toUse);
				}
			}
		};

		mBg.fitWidthProperty().addListener(selectedImgSize);
		mBg.fitHeightProperty().addListener(selectedImgSize);
		
		mPane.prefWidthProperty().bind(scene.widthProperty().divide(3.55));
		mPane.prefHeightProperty().bind(scene.heightProperty().divide(1.16));
		
		ChangeListener<Number> panePos = new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
			{
				mPane.setLayoutX(scene.getWidth() - mPane.getPrefWidth());
				mPane.setLayoutY(scene.getHeight() / 14.4);
			}
		};
		
		mPane.prefWidthProperty().addListener(panePos);
		mPane.prefHeightProperty().addListener(panePos);
		mPane.visibleProperty().bind(visibilityProperty);
		
		mDexTxt.fontProperty().bind(fontProperty);
		mAnatureTxt.fontProperty().bind(fontProperty);
		mBackpackTxt.fontProperty().bind(fontProperty);
		mSettingsTxt.fontProperty().bind(fontProperty);
		mSaveTxt.fontProperty().bind(fontProperty);
		mQuitTxt.fontProperty().bind(fontProperty);

		mSelectImg.setVisible(false);
		
		int index = 0;
		tabAttributes(mDexTxt, index++, () -> System.out.println("Dex"));
		tabAttributes(mAnatureTxt, index++, () -> System.out.println("Anature"));
		tabAttributes(mBackpackTxt, index++, () -> System.out.println("Backpack"));
		tabAttributes(mSettingsTxt, index++, () -> System.out.println("Settings"));
		tabAttributes(mSaveTxt, index++, () -> System.out.println("Save"));
		tabAttributes(mQuitTxt, index++, () -> System.out.println("Quit"));
	}
	
	private void tabAttributes(Label txt, int index, Runnable onClick)
	{
		txt.setOnMouseEntered(value -> 
		{
			txt.setUnderline(true);
			
			mGrid.getChildren().remove(mSelectImg);
			mGrid.add(mSelectImg, 0, index);
			
			mSelectImg.setVisible(true);
			mSelectImg.setOnMouseClicked(mouseEvent -> onClick.run());
		});
		txt.setOnMouseExited(value -> txt.setUnderline(false));
		txt.setOnMouseClicked(value -> onClick.run());
		
		txt.minWidthProperty().bind(mBg.fitWidthProperty().divide(1.4285));
	}
}
