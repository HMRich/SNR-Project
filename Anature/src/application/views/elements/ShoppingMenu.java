package application.views.elements;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ShoppingMenu extends Pane
{
	private ImageView mBgImg, mSelectedItemImg, mBuyBtn;
	private ImageView mTabOneBg, mTabTwoBg;
	private ImageView mTabOneIcon, mTabTwoIcon;
	private TextField mSelectedItemName;
	private ListView<String> mItemList;
	private Spinner<Integer> mItemAmountSpinner;
	
	public ShoppingMenu()
	{
		initialize();
		setUpBindings();
		setUpImgs();
		getChildren().addAll(mBgImg, mSelectedItemImg, mBuyBtn, mTabOneBg, mTabTwoBg, mTabOneIcon, mTabTwoIcon, mSelectedItemName, mItemList, mItemAmountSpinner);
	}
	
	private void initialize()
	{
		mBgImg = new ImageView();
		mSelectedItemImg = new ImageView();
		mBuyBtn = new ImageView();
		mTabOneBg = new ImageView();
		mTabTwoBg = new ImageView();
		mTabOneIcon = new ImageView();
		mTabTwoIcon = new ImageView();
		
		mSelectedItemName = new TextField("Text");
		mSelectedItemName.setEditable(false);
		mSelectedItemName.getStylesheets().add(getClass().getResource("/resources/css/Shopping.css").toExternalForm());
		mSelectedItemName.setAlignment(Pos.CENTER);
		
		mItemList = new ListView<String>();
		mItemList.getItems().add("Potion");
		
		mItemAmountSpinner = new Spinner<Integer>();
		IntegerSpinnerValueFactory factory = new IntegerSpinnerValueFactory(1, 99);
		mItemAmountSpinner.setValueFactory(factory);
	}

	private void setUpBindings()
	{
		mBgImg.fitWidthProperty().bind(prefWidthProperty().divide(1.069));
		mBgImg.fitHeightProperty().bind(prefHeightProperty());

		mSelectedItemImg.fitWidthProperty().bind(prefWidthProperty().divide(3.4375));
		mSelectedItemImg.fitHeightProperty().bind(prefHeightProperty().divide(3.4375));
		mSelectedItemImg.layoutXProperty().bind(prefWidthProperty().divide(3.235));
		mSelectedItemImg.layoutYProperty().bind(prefHeightProperty().divide(7.065));

		mBuyBtn.fitWidthProperty().bind(prefWidthProperty().divide(2.5));
		mBuyBtn.fitHeightProperty().bind(prefHeightProperty().divide(13.06));
		mBuyBtn.layoutXProperty().bind(prefWidthProperty().divide(3.85));
		mBuyBtn.layoutYProperty().bind(prefHeightProperty().divide(1.137));

		mItemList.prefWidthProperty().bind(prefWidthProperty().divide(1.327));
		mItemList.prefHeightProperty().bind(prefHeightProperty().divide(3.056));
		mItemList.layoutXProperty().bind(prefWidthProperty().divide(11.323));
		mItemList.layoutYProperty().bind(prefHeightProperty().divide(2.22));

		mItemAmountSpinner.prefWidthProperty().bind(prefWidthProperty().divide(5.42));
		mItemAmountSpinner.prefHeightProperty().bind(prefHeightProperty().divide(33.15));
		mItemAmountSpinner.layoutXProperty().bind(prefWidthProperty().divide(2.09));
		mItemAmountSpinner.layoutYProperty().bind(prefHeightProperty().divide(1.24));

		mTabOneBg.fitWidthProperty().bind(prefWidthProperty().divide(8.19));
		mTabOneBg.fitHeightProperty().bind(prefHeightProperty().divide(9.17));
		mTabOneBg.layoutXProperty().bind(prefWidthProperty().divide(1.145));
		mTabOneBg.layoutYProperty().bind(prefHeightProperty().divide(22.68));

		mTabTwoBg.fitWidthProperty().bind(prefWidthProperty().divide(8.19));
		mTabTwoBg.fitHeightProperty().bind(prefHeightProperty().divide(9.17));
		mTabTwoBg.layoutXProperty().bind(prefWidthProperty().divide(1.145));
		mTabTwoBg.layoutYProperty().bind(prefHeightProperty().divide(6.73));

		mTabOneIcon.fitWidthProperty().bind(prefWidthProperty().divide(12.03));
		mTabOneIcon.fitHeightProperty().bind(prefHeightProperty().divide(13.46));
		mTabOneIcon.layoutXProperty().bind(prefWidthProperty().divide(1.12));
		mTabOneIcon.layoutYProperty().bind(prefHeightProperty().divide(15.96));

		mTabTwoIcon.fitWidthProperty().bind(prefWidthProperty().divide(12.03));
		mTabTwoIcon.fitHeightProperty().bind(prefHeightProperty().divide(13.46));
		mTabTwoIcon.layoutXProperty().bind(prefWidthProperty().divide(1.12));
		mTabTwoIcon.layoutYProperty().bind(prefHeightProperty().divide(5.986));

		mSelectedItemName.prefWidthProperty().bind(prefWidthProperty().divide(1.734));
		mSelectedItemName.prefHeightProperty().bind(prefHeightProperty().divide(17.24));
		mSelectedItemName.layoutXProperty().bind(prefWidthProperty().divide(6.015));
		mSelectedItemName.layoutYProperty().bind(prefHeightProperty().divide(25));
	}
	
	private void setUpImgs()
	{
		mBgImg.setImage(new Image(getClass().getResource("/resources/images/overworld/shopping/Shopping_Background.png").toExternalForm()));
		mSelectedItemImg.setImage(new Image(getClass().getResource("/resources/images/items/Potion.png").toExternalForm()));
		mBuyBtn.setImage(new Image(getClass().getResource("/resources/images/overworld/shopping/Buy_Items_Btn.png").toExternalForm()));
		mTabOneBg.setImage(new Image(getClass().getResource("/resources/images/battle/items/White_Tab.png").toExternalForm()));
		mTabTwoBg.setImage(new Image(getClass().getResource("/resources/images/battle/items/Grey_Tab.png").toExternalForm()));
		mTabOneIcon.setImage(new Image(getClass().getResource("/resources/images/items/Potion.png").toExternalForm()));
		mTabTwoIcon.setImage(new Image(getClass().getResource("/resources/images/items/AnaCube.png").toExternalForm()));
	}
	
	public void bindTextFont(ObjectProperty<Font> fontProperty)
	{
		mSelectedItemName.fontProperty().bind(fontProperty);
	}
	
	public String getSelectedItemName()
	{
		return mItemList.getSelectionModel().getSelectedItem();
	}
	
	public ObservableList<String> getItemList()
	{
		return mItemList.getItems();
	}
	
	public void setItemList(ObservableList<String> items)
	{
		if(items != null)
		{
			mItemList.setItems(items);
		}
	}
	
	public void updateSelectedItem(Image image, String txt)
	{
		if(image != null && txt != null)
		{
			mSelectedItemImg.setImage(image);
			mSelectedItemName.setText(txt);
		}
		
		else
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried updating the selected item for purchase, but was null.");
		}
	}
	
	public void setSelectedItem(int index)
	{
		mItemList.getSelectionModel().select(index);
	}
	
	public void setOnSelectItem(Runnable toRun)
	{
		if(toRun == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried setting the on Item Select runnable to null.");
			return;
		}
		
		mItemList.setOnMouseClicked(event -> toRun.run());
	}

	public void setOnBuyItems(Runnable toRun)
	{
		if(toRun == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried setting the on Item Buy runnable to null.");
			return;
		}
		
		mBuyBtn.setOnMouseClicked(event -> toRun.run());
	}
	
	public int getAmount()
	{
		return mItemAmountSpinner.getValue();
	}
}
