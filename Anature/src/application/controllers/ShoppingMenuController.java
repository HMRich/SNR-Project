package application.controllers;

import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.interfaces.IHealthPotion;
import application.player.Player;
import application.pools.ItemPool;
import application.views.elements.ShoppingMenu;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class ShoppingMenuController
{
	private ShoppingMenu mView;
	private Player mPlayer;
	private Image mItemPotion, mItemGreatPotion, mItemUltraPotion, mItemMasterPotion;
	private Runnable mDequeue;

	public ShoppingMenuController(ShoppingMenu view)
	{
		if(view == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried passing a null ShoppingMenu View to the ShoppingMenuController.");
			view = new ShoppingMenu();
		}

		mView = view;
		mView.setOnSelectItem(() -> onItemSelect());
		mView.setOnBuyItems(() -> buyItems());

		mItemPotion = new Image(getClass().getResource("/resources/images/items/Potion.png").toExternalForm());
		mItemGreatPotion = new Image(getClass().getResource("/resources/images/items/Great_Potion.png").toExternalForm());
		mItemUltraPotion = new Image(getClass().getResource("/resources/images/items/Ultra_Potion.png").toExternalForm());
		mItemMasterPotion = new Image(getClass().getResource("/resources/images/items/Master_Potion.png").toExternalForm());
	}

	public void updateBinds(DoubleBinding yProp, DoubleBinding widthProp, DoubleBinding heightProp, ObjectProperty<Font> fontProperty,
			BooleanProperty visibleProp)
	{
		mView.bindTextFont(fontProperty);
		mView.setLayoutX(0);
		mView.layoutYProperty().bind(yProp);
		mView.prefWidthProperty().bind(widthProp);
		mView.prefHeightProperty().bind(heightProp);
		mView.visibleProperty().bind(visibleProp);
	}

	public void updateItems()
	{
		ObservableList<String> items = mView.getItemList();
		items.clear();

		items.add("Potions");
		items.add("Great Potions");
		items.add("Ultra Potions");
		items.add("Master Potions");

		mView.setItemList(items);
		mView.setSelectedItem(0);
		onItemSelect();
	}

	public void onItemSelect()
	{
		String selectedItem = mView.getSelectedItemName();

		if(selectedItem == null)
			return;

		if(selectedItem.startsWith("Potions"))
		{
			mView.updateSelectedItem(mItemPotion, "Potion");
		}

		else if(selectedItem.startsWith("Great"))
		{
			mView.updateSelectedItem(mItemGreatPotion, "Great Potion");
		}

		else if(selectedItem.startsWith("Ultra"))
		{
			mView.updateSelectedItem(mItemUltraPotion, "Ultra Potion");
		}

		else if(selectedItem.startsWith("Master"))
		{
			mView.updateSelectedItem(mItemMasterPotion, "Master Potion");
		}
	}

	public void assignPlayer(Player player, Runnable dequeue)
	{
		if(player == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried assigning a null player to shop.");
		}

		else
		{
			mPlayer = player;
			mDequeue = dequeue;
		}
	}
	
	private void buyItems()
	{
		String selectedItem = mView.getSelectedItemName();
		ItemIds potionId = null;
		IHealthPotion potionToAdd = null;
		
		if(selectedItem == null)
			return;

		if(selectedItem.startsWith("Potions"))
		{
			potionId = ItemIds.Potion;
		}

		else if(selectedItem.startsWith("Great"))
		{
			potionId = ItemIds.Great_Potion;
		}

		else if(selectedItem.startsWith("Ultra"))
		{
			potionId = ItemIds.Ultra_Potion;
		}

		else if(selectedItem.startsWith("Master"))
		{
			potionId = ItemIds.Master_Potion;
		}
		
		potionToAdd = ItemPool.getHealthPotion(potionId);
		
		for(int i = 0; i < mView.getAmount(); i++)
		{
			mPlayer.getBackpack().addItem(potionToAdd);
		}
		
		mDequeue.run();
	}
}
