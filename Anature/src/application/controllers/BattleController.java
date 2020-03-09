package application.controllers;

import java.util.ArrayList;
import java.util.Random;

import application.Anature;
import application.Backpack;
import application.FightManager;
import application.ItemResult;
import application.MoveResult;
import application.MoveSet;
import application.Player;
import application.Startup;
import application.animations.*;
import application.enums.*;
import application.items.Item;
import application.items.ItemPool;
import application.moves.Move;
import application.trainers.Trainer;
import application.views.elements.AnatureSlot;
import application.views.elements.HpBar;
import application.views.elements.ResizableImage;
import application.views.elements.XpBar;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BattleController
{
	@FXML private Pane mPane;
	@FXML private ImageView mBgImage, mDialogueImage, mHpImage;
	@FXML private ImageView mPlayerImage, mTrainerImage;
	@FXML private ImageView mPlayerGroundImage, mTrainerGroundImage;
	@FXML private ImageView mAnatureFront, mAnatureBack;
	@FXML private Text mPlayerNameTxt, mEnemyNameTxt, mPlayerHpTxt, mEnemyHpTxt, mPlayerLvlTxt, mEnemyLvlTxt;
	@FXML private ImageView mPlayerGender, mEnemyGender;
	@FXML private TextArea mDialogueTxtArea;
	@FXML private ImageView mClickIndicatorImg;
	@FXML private Button mTestBtn;

	@FXML private ImageView mSwitchSelection, mSwitchDialogue, mSwitchBtn, mSwitchBackBtn, mSwitchSelectedImg, mSwitchSelectedTypeOne, mSwitchSelectedTypeTwo,
			mSwitchPageLeft, mSwitchPageRight;
	@FXML private Text mSwitchSelectedCatalogNum, mSwitchSelectedName, mSwitchSelectedOwner, mSwitchSelectedCurrXp, mSwitchSelectedNextXp, mSwitchPageTxt;
	@FXML private Text mSwitchSelectedHp, mSwitchSelectedAtk, mSwitchSelectedSpAtk, mSwitchSelectedDef, mSwitchSelectedSpDef, mSwitchSelectedSpeed,
			mSwitchSelectedAbilityName, mSwitchSelectedAbilityDesc;
	@FXML private ImageView mStatusIconPlayer, mStatusIconEnemy;
	private Image mSwitchPageOneImg, mSwitchPageTwoImg, mItemTabSelected, mItemTabDeselected, mItemPotion, mItemGreatPotion, mItemUltraPotion,
			mItemMasterPotion, mBurnStatusIcon, mParalyzedStatusIcon, mSleepStatusIcon;
	

	@FXML private ImageView mItemSelectionBg, mItemDialogue, mSelectedItem, mItemBackBtn, mItemUseBtn, mItemPotionsTab, mItemAnaCubeTab, mStatusTab;
	@FXML private ListView<String> mItemList;
	@FXML private Rectangle mItemListBg;
	@FXML private ImageView mItemPotionTabImg, mItemAnaCubeTabImg, mSelectedItemImg;
	@FXML private Text mSelectedItemName;

	@FXML private ImageView mAttackDialogue, mAttackSeOne, mAttackSeTwo, mAttackSeThree, mAttackSeFour, mAttackBackBtn;
	@FXML private ImageView mAttackImgOne, mAttackImgTwo, mAttackImgThree, mAttackImgFour;
	@FXML private Text mAttackNameOne, mAttackMpOne, mAttackNameTwo, mAttackMpTwo, mAttackNameThree, mAttackMpThree, mAttackNameFour, mAttackMpFour;
	@FXML private Group mMoveSeGroup;

	private DoubleProperty mEnemyHp, mEnemyHpTotal;
	private DoubleProperty mPlayerHp, mPlayerHpTotal;
	private DoubleProperty mPlayerXp, mPlayerXpTotal;
	private IntegerProperty mEnemyLvl, mPlayerLvl;
	private BooleanProperty mShowItemSelection, mShowSwitch, mShowPlayerBars, mShowSwitchPageOne, mCanClick;
	private StringProperty mDialogueTxt, mPlayerName, mEnemyName, mSelectedItemTxt;
	private BooleanProperty mShowBtns, mShowMoveSelection, mShowMoveSe, mShowMoveOne, mShowMoveTwo, mShowMoveThree, mShowMoveFour, mShowMoveSeOne,
			mShowMoveSeTwo, mShowMoveSeThree, mShowMoveSeFour, mSwitchSlotOne, mSwitchSlotTwo, mSwitchSlotThree, mSwitchSlotFour, mSwitchSlotFive,
			mSwitchSlotSix;
	private StringProperty mAttackNameOneTxt, mAttackMpOneTxt, mAttackNameTwoTxt, mAttackMpTwoTxt, mAttackNameThreeTxt, mAttackMpThreeTxt, mAttackNameFourTxt,
			mAttackMpFourTxt;

	private FightManager mFightManager;
	private Trainer mEnemyTrainer;
	private Player mPlayer;
	private ClickQueue mClickQueue;
	private AnatureSlot mSlotOne, mSlotTwo, mSlotThree, mSlotFour, mSlotFive, mSlotSix;
	private int mSwitchPageNum, mSwitchIndexSelected;
	private boolean mToEnd;

	public void initialize()
	{
		mEnemyHp = new SimpleDoubleProperty(100);
		mEnemyHpTotal = new SimpleDoubleProperty(100);
		mPlayerHp = new SimpleDoubleProperty(100);
		mPlayerHpTotal = new SimpleDoubleProperty(100);
		mPlayerXp = new SimpleDoubleProperty(0);
		mPlayerXpTotal = new SimpleDoubleProperty(100);
		mDialogueTxt = new SimpleStringProperty("1\n2\n3");
		mPlayerName = new SimpleStringProperty("Player Name");
		mEnemyName = new SimpleStringProperty("Enemy Name");

		mSelectedItemTxt = new SimpleStringProperty("Item Name");

		mEnemyLvl = new SimpleIntegerProperty(100);
		mPlayerLvl = new SimpleIntegerProperty(100);

		mAttackNameOneTxt = new SimpleStringProperty("Attack 1");
		mAttackNameTwoTxt = new SimpleStringProperty("Attack 2");
		mAttackNameThreeTxt = new SimpleStringProperty("Attack 3");
		mAttackNameFourTxt = new SimpleStringProperty("Attack 4");

		mAttackMpOneTxt = new SimpleStringProperty("10/10");
		mAttackMpTwoTxt = new SimpleStringProperty("10/10");
		mAttackMpThreeTxt = new SimpleStringProperty("10/10");
		mAttackMpFourTxt = new SimpleStringProperty("10/10");

		mSwitchSlotOne = new SimpleBooleanProperty(false);
		mSwitchSlotTwo = new SimpleBooleanProperty(false);
		mSwitchSlotThree = new SimpleBooleanProperty(false);
		mSwitchSlotFour = new SimpleBooleanProperty(false);
		mSwitchSlotFive = new SimpleBooleanProperty(false);
		mSwitchSlotSix = new SimpleBooleanProperty(false);

		mShowSwitchPageOne = new SimpleBooleanProperty(true);
		mShowPlayerBars = new SimpleBooleanProperty(true);
		mShowSwitch = new SimpleBooleanProperty(false);
		mShowItemSelection = new SimpleBooleanProperty(false);
		mShowMoveSelection = new SimpleBooleanProperty(false);
		mShowMoveOne = new SimpleBooleanProperty(false);
		mShowMoveTwo = new SimpleBooleanProperty(false);
		mShowMoveThree = new SimpleBooleanProperty(false);
		mShowMoveFour = new SimpleBooleanProperty(false);
		mShowMoveSe = new SimpleBooleanProperty(true);
		mShowMoveSeOne = new SimpleBooleanProperty(false);
		mShowMoveSeTwo = new SimpleBooleanProperty(false);
		mShowMoveSeThree = new SimpleBooleanProperty(false);
		mShowMoveSeFour = new SimpleBooleanProperty(false);

		mShowBtns = new SimpleBooleanProperty(false);
		mFightManager = null;
		mEnemyTrainer = null;
		mClickQueue = new ClickQueue();
		mCanClick = new SimpleBooleanProperty(false);
		mSwitchPageNum = 1;
		mSwitchIndexSelected = 0;
		mToEnd = false;

		mSwitchPageOneImg = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Selection_Panel_Page1.png").toExternalForm());
		mSwitchPageTwoImg = new Image(getClass().getResource("/resources/images/battle/switching/Switch_Selection_Panel_Page2.png").toExternalForm());

		mItemTabSelected = new Image(getClass().getResource("/resources/images/battle/items/White_Tab.png").toExternalForm());
		mItemTabDeselected = new Image(getClass().getResource("/resources/images/battle/items/Grey_Tab.png").toExternalForm());

		mItemPotion = new Image(getClass().getResource("/resources/images/items/Potion.png").toExternalForm());
		mItemGreatPotion = new Image(getClass().getResource("/resources/images/items/Great_Potion.png").toExternalForm());
		mItemUltraPotion = new Image(getClass().getResource("/resources/images/items/Ultra_Potion.png").toExternalForm());
		mItemMasterPotion = new Image(getClass().getResource("/resources/images/items/Master_Potion.png").toExternalForm());
	
	
		mBurnStatusIcon = new Image(getClass().getResource("/resources/images/statuses/Burn.png").toExternalForm());
		mParalyzedStatusIcon = new Image(getClass().getResource("/resources/images/statuses/Paralyzed.png").toExternalForm());
		mSleepStatusIcon = new Image(getClass().getResource("/resources/images/statuses/Burn.png").toExternalForm());
	}

	public void setUpBindingsAndElements(Scene scene)
	{
		setUpBgImages(scene);
		setUpAnatureImgs(scene);
		setUpAnatureNames(scene);
		setUpAnatureHpTxt(scene);
		setUpAnatureLvlTxt(scene);
		setUpAnatureHpAndXpBars(scene);
		setUpAnatureGenders(scene);
		setUpBtnGrid(scene);
		setUpDialogue(scene);
		setUpTestBtn(scene);
		 
		setUpClickTracker(scene);
	

		setUpGround(scene);
		setUpHumanSprites(scene);

		setUpSwitchElements(scene);
		setUpItemElements(scene);
		setUpMoveSelection(scene);
		
		setUpStatuses(scene);
	}

	private void setUpBgImages(Scene scene)
	{
		createBindsImageView(mBgImage, scene, 1, 1);
		createBindsImageView(mDialogueImage, scene, 1, 1);
		createBindsImageView(mHpImage, scene, 1, 1);
		createBindsImageView(mClickIndicatorImg, scene, 2.03, 1.095, 40, 30, mCanClick);

		BlinkingAnimation blinkAnimation = new BlinkingAnimation(mClickIndicatorImg, Duration.seconds(1.5));
		blinkAnimation.play();
	}

	private void setUpHumanSprites(Scene scene)
	{
		createBindsImageView(mPlayerImage, scene, 4.5, 3, 1.9);
		createBindsImageView(mTrainerImage, scene, 13, 5, 3);
	}

	private void setUpGround(Scene scene)
	{
		createBindsImageView(mPlayerGroundImage, scene, 1.65, 2.4, 5);
		createBindsImageView(mTrainerGroundImage, scene, 3.5, 3, 6);
	}

	private void setUpAnatureImgs(Scene scene)
	{
		createBindsImageView(mAnatureFront, scene, 1.75, 7.5, 5.5, 3.5);
		createBindsImageView(mAnatureBack, scene, 5, 2.9, 4, 2.5);
		mAnatureBack.setOpacity(0);
	}

	private void setUpAnatureNames(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(55, scene);

		createBindsTxt(mPlayerNameTxt, scene, 1.75, 2.08, fontProperty, mPlayerName);
		createBindsTxt(mEnemyNameTxt, scene, 4.9, 9.7, fontProperty, mEnemyName);
	}

	private void setUpAnatureHpTxt(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(85, scene);

		StringProperty playerHpTxt = new SimpleStringProperty(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue());
		mPlayerHp.addListener(
				(observable, oldValue, newValue) -> playerHpTxt.set(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue()));

		StringProperty enemyHpTxt = new SimpleStringProperty(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue());
		mEnemyHp.addListener((observable, oldValue, newValue) -> enemyHpTxt.set(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue()));

		createBindsTxt(mPlayerHpTxt, scene, 1.41, 1.83, fontProperty, playerHpTxt);
		createBindsTxt(mEnemyHpTxt, scene, 4.7, 5.8, fontProperty, enemyHpTxt);
	}

	private void setUpAnatureLvlTxt(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(85, scene);

		StringProperty playerLvlTxt = new SimpleStringProperty("Lvl " + mPlayerLvl.get());
		mPlayerLvl.addListener((observable, oldValue, newValue) -> playerLvlTxt.set("Lvl " + mPlayerLvl.get()));

		StringProperty enemyLvlTxt = new SimpleStringProperty("Lvl " + mEnemyLvl.get());
		mEnemyLvl.addListener((observable, oldValue, newValue) -> enemyLvlTxt.set("Lvl " + mEnemyLvl.get()));

		createBindsTxt(mPlayerLvlTxt, scene, 1.71, 1.83, fontProperty, playerLvlTxt);
		createBindsTxt(mEnemyLvlTxt, scene, 2.61, 5.8, fontProperty, enemyLvlTxt);
	}

	private void setUpAnatureHpAndXpBars(Scene scene)
	{
		HpBar playerHpBar = new HpBar(mPlayerHp, mPlayerHpTotal, scene);
		playerHpBar.bindX(1.509);
		playerHpBar.bindY(1.995);
		playerHpBar.progressProperty().bind(mPlayerHp.divide(mPlayerHpTotal));
		playerHpBar.visibleProperty().bind(mShowPlayerBars);

		mPane.getChildren().add(playerHpBar);

		HpBar enemyHpBar = new HpBar(mEnemyHp, mEnemyHpTotal, scene);
		enemyHpBar.bindX(4.15);
		enemyHpBar.bindY(7.95);
		enemyHpBar.progressProperty().bind(mEnemyHp.divide(mEnemyHpTotal));

		mPane.getChildren().add(enemyHpBar);

		XpBar playerXpBar = new XpBar(mPlayerXp, mPlayerXpTotal, scene);
		playerXpBar.bindX(1.723);
		playerXpBar.bindY(1.78);
		playerXpBar.visibleProperty().bind(mShowPlayerBars);

		mPane.getChildren().add(playerXpBar);
	}

	private void setUpAnatureGenders(Scene scene)
	{
		createBindsImageView(mPlayerGender, scene, 1.79, 1.93, 57, 31);
		createBindsImageView(mEnemyGender, scene, 2.77, 7, 57, 31);
	}

	private void setUpBtnGrid(Scene scene)
	{
		GridPane grid = new GridPane();

		ColumnConstraints colConst = new ColumnConstraints();
		colConst.setPercentWidth(100.0 / 2);
		grid.getColumnConstraints().add(colConst);
		grid.getColumnConstraints().add(colConst);

		RowConstraints rowConst = new RowConstraints();
		rowConst.setPercentHeight(100.0 / 2);
		grid.getRowConstraints().add(rowConst);
		grid.getRowConstraints().add(rowConst);

		grid.prefWidthProperty().bind(scene.widthProperty().divide(4.5));
		grid.prefHeightProperty().bind(scene.heightProperty().divide(5.8));
		grid.layoutXProperty().bind(scene.widthProperty().divide(1.84));
		grid.layoutYProperty().bind(scene.heightProperty().divide(1.296));
		grid.setHgap(5);
		grid.setVgap(10);
		mPane.getChildren().add(grid);

		ResizableImage atkImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Attack_Btn.png").toExternalForm()));
		atkImage.setOnAction(event -> onAttackBtn());
		grid.addColumn(0, atkImage);

		ResizableImage anatureImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Anature_Btn.png").toExternalForm()));
		anatureImage.setOnAction(event -> onSwitchBtn());
		grid.addColumn(1, anatureImage);

		ResizableImage bagImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Bag_Btn.png").toExternalForm()));
		bagImage.setOnAction(event -> onBagBtn());
		grid.add(bagImage, 0, 1);

		ResizableImage escapeImage = new ResizableImage(new Image(getClass().getResource("/resources/images/battle/Escape_Btn.png").toExternalForm()));
		escapeImage.setOnAction(event -> Startup.changeScene(null, null)); // TODO for demo
		grid.add(escapeImage, 1, 1);

		grid.visibleProperty().bind(mShowBtns);
	}

	private void setUpTestBtn(Scene scene)
	{
		mTestBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				if(mPlayerXp.get() < 100)
				{
					mPlayerXp.set(mPlayerXp.add(10).doubleValue());
				}

				else
				{
					mPlayerXp.set(0);
				}
			}
		});
	}

	private void setUpDialogue(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(75, scene);

		mDialogueTxtArea.textProperty().bind(mDialogueTxt);
		mDialogueTxtArea.getStylesheets().add("/resources/css/BattleStyle.css");
		mDialogueTxtArea.prefWidthProperty().bind(scene.widthProperty().divide(3.2));
		mDialogueTxtArea.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mDialogueTxtArea.layoutYProperty().bind(scene.heightProperty().divide(1.32));
		mDialogueTxtArea.layoutXProperty().bind(scene.widthProperty().divide(4.6));
		mDialogueTxtArea.fontProperty().bind(fontProperty);
	}

	private void setUpClickTracker(Scene scene)
	{
		scene.setOnMouseClicked(new EventHandler<Event>()
		{
			@Override
			public void handle(Event event)
			{
				
				if(mCanClick.get())
				{
					Runnable toRun = mClickQueue.dequeue();

					if(mToEnd)
					{
						toRun.run();
					}

					else if(toRun != null)
					{
						mCanClick.set(false);

						if(mPlayerHp.get() <= 0) // TODO Just for Demo. Change to do swapping here.
						{
							mDialogueTxt.set(mFightManager.getPlayerTeam().get(0).getName() + " has been defeated!");

							mShowBtns.set(false);

							mClickQueue.clear();
							mClickQueue.enqueue(() -> Startup.changeScene(null, null));

							mCanClick.set(true);
							mToEnd = true;
						}

						else if(mEnemyHp.get() == 0)
						{
							mDialogueTxt.set(mFightManager.getEnemyTeam().get(0).getName() + " has been defeated!");

							mShowBtns.set(false);

							mClickQueue.clear();
							mClickQueue.enqueue(() -> Startup.changeScene(null, null));

							mCanClick.set(true);
							mToEnd = true;
						}

						else
							toRun.run();
					}
				}
			}
		});
	}

	private void setUpSwitchElements(Scene scene)
	{
		ObjectProperty<Font> fontProperty = getFontProperty(65, scene);
		ObjectProperty<Font> pageFontProperty = getFontProperty(65, scene);

		createBindsImageView(mSwitchSelection, scene, 1, 1, mShowSwitch);
		createBindsImageView(mSwitchDialogue, scene, 1, 1, mShowSwitch);

		createBindsImageView(mSwitchBtn, scene, 2.71, 1.5, 8.31, 21.818, mShowSwitch);
		mSwitchBtn.setOnMouseClicked(event -> activateTurn(BattleChoice.Switch));

		createBindsImageView(mSwitchBackBtn, scene, 2.25, 1.16, 9, 11, mShowSwitch);
		mSwitchBackBtn.setOnMouseClicked(event ->
		{
			onBackBtn();
			mSwitchIndexSelected = mPlayer.getSelectedIndex();
		});

		createBindsImageView(mSwitchSelectedImg, scene, 2.889, 3.396, 5.638, 3.03, mShowSwitch);

		setUpSwitchPageOne(scene, fontProperty);
		setUpSwitchPageTwo(scene, fontProperty);

		createBindsTxt(mSwitchPageTxt, scene, 1.16, 5.4, pageFontProperty, mShowSwitch);

		createBindsImageView(mSwitchPageLeft, scene, 1.245, 6.99, 46.23, 18.46, mShowSwitch);
		createBindsImageView(mSwitchPageRight, scene, 1.043, 6.99, 46.23, 18.46, mShowSwitch);

		setUpAnatureTabs(scene);
	}

	private void setUpStatuses(Scene scene)
	{
		createBindsImageView(mStatusIconPlayer, scene, 1.278, 1.909, 26.122, 34.285);
		mStatusIconPlayer.setImage(null);

		createBindsImageView(mStatusIconEnemy, scene, 3.122, 6.79, 26.122, 34.285);
		mStatusIconEnemy.setImage(null);
	}
	
	private void setUpSwitchPageOne(Scene scene, ObjectProperty<Font> fontTracking)
	{
		ObjectProperty<Font> nameFontTracking = getFontProperty(75, scene);

		createBindsTxt(mSwitchSelectedCatalogNum, scene, 1.2895, 3.7, fontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedName, scene, 1.2895, 2.8, fontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedOwner, scene, 1.2895, 1.93, nameFontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedCurrXp, scene, 1.2895, 1.65, fontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedNextXp, scene, 1.2895, 1.45, fontTracking, mShowSwitchPageOne.and(mShowSwitch));

		createBindsImageView(mSwitchSelectedTypeOne, scene, 1.298, 2.52, 10.578, 18, mShowSwitchPageOne.and(mShowSwitch));
		createBindsImageView(mSwitchSelectedTypeTwo, scene, 1.1469, 2.52, 10.578, 18, mShowSwitchPageOne.and(mShowSwitch));
	}

	private void setUpSwitchPageTwo(Scene scene, ObjectProperty<Font> fontTracking)
	{
		ObjectProperty<Font> abilityDescFontTracking = getFontProperty(85, scene);

		createBindsTxt(mSwitchSelectedHp, scene, 1.4, 3.7, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedAtk, scene, 1.4, 3, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedSpAtk, scene, 1.4, 2.55, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedDef, scene, 1.4, 2.2, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedSpDef, scene, 1.4, 1.94, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedSpeed, scene, 1.4, 1.73, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedAbilityName, scene, 1.4, 1.57, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedAbilityDesc, scene, 1.4, 1.48, 3.71, abilityDescFontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
	}

	private void setUpAnatureTabs(Scene scene)
	{
		Image anatureImg = new Image(getClass().getResource("/resources/images/anatures/Null_Front.png").toExternalForm());

		mSlotOne = new AnatureSlot(scene, true, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotOne, 100.0, true);
		mSlotTwo = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotTwo, 100.0, false);
		mSlotThree = new AnatureSlot(scene, false, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotThree, 100.0, false);
		mSlotFour = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotFour, 100.0, false);
		mSlotFive = new AnatureSlot(scene, false, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotFive, 100.0, false);
		mSlotSix = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotSix, 100.0, false);

		createBindsAnatureslot(mSlotOne, scene, 85, 4.2, 3.7, 15.1, 0);
		createBindsAnatureslot(mSlotTwo, scene, 85, 3.157, 3.7, 15.1, 1);
		createBindsAnatureslot(mSlotThree, scene, 85, 2.54, 3.7, 15.1, 2);
		createBindsAnatureslot(mSlotFour, scene, 85, 2.114, 3.7, 15.1, 3);
		createBindsAnatureslot(mSlotFive, scene, 85, 1.82, 3.7, 15.1, 4);
		createBindsAnatureslot(mSlotSix, scene, 85, 1.59, 3.7, 15.1, 5);

		ArrayList<Node> slots = new ArrayList<Node>();
		slots.add(mSlotOne);
		slots.add(mSlotTwo);
		slots.add(mSlotThree);
		slots.add(mSlotFour);
		slots.add(mSlotFive);
		slots.add(mSlotSix);

		mPane.getChildren().addAll(mPane.getChildren().size() - 15, slots);
	}

	private void setUpItemElements(Scene scene)
	{
		createBindsImageView(mItemSelectionBg, scene, 1, 1, mShowItemSelection);
		createBindsImageView(mItemDialogue, scene, 1, 1, mShowItemSelection);
		createBindsImageView(mSelectedItem, scene, 1, 1, mShowItemSelection);

		createBindsImageView(mItemBackBtn, scene, 2.3, 1.19, 7, 9, mShowItemSelection);
		mItemBackBtn.setOnMouseClicked(event -> onBackBtn());

		createBindsImageView(mItemUseBtn, scene, 20, 1.52, 7, 17, mShowItemSelection);
		mItemUseBtn.setOnMouseClicked(event -> activateTurn(BattleChoice.Item));

		createBindsImageView(mItemPotionsTab, scene, 4.07, 4.71, 23, 13, mShowItemSelection);
		mItemPotionsTab.setOnMouseClicked(event -> onItemTab(true));

		createBindsImageView(mItemPotionTabImg, scene, 4.03, 4.71, 26, 13, mShowItemSelection);
		mItemPotionTabImg.setOnMouseClicked(event -> onItemTab(true));

		createBindsImageView(mItemAnaCubeTab, scene, 4.06, 3.5, 23, 13, mShowItemSelection);
		mItemAnaCubeTab.setOnMouseClicked(event -> onItemTab(false));

		createBindsImageView(mItemAnaCubeTabImg, scene, 4.07, 3.5, 23, 13, mShowItemSelection);
		mItemAnaCubeTabImg.setOnMouseClicked(event -> onItemTab(false));

		mItemList.layoutXProperty().bind(scene.widthProperty().divide(32));
		mItemList.layoutYProperty().bind(scene.heightProperty().divide(2.23));
		mItemList.prefWidthProperty().bind(scene.widthProperty().divide(5.5));
		mItemList.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mItemList.visibleProperty().bind(mShowItemSelection);
		mItemList.setOnMouseClicked(event -> onItemSelect());

		mItemListBg.layoutXProperty().bind(scene.widthProperty().divide(32));
		mItemListBg.layoutYProperty().bind(scene.heightProperty().divide(2.23));
		mItemListBg.widthProperty().bind(scene.widthProperty().divide(5.5));
		mItemListBg.heightProperty().bind(scene.heightProperty().divide(5));
		mItemListBg.visibleProperty().bind(mShowItemSelection);

		ObjectProperty<Font> fontProperty = getFontProperty(85, scene);

		createBindsTxt(mSelectedItemName, scene, 28.44, 4.0, 5.8479, fontProperty, mShowItemSelection);
		mSelectedItemName.textProperty().bind(mSelectedItemTxt);

		createBindsImageView(mSelectedItemImg, scene, 14.1, 4, 10, 5, mShowItemSelection);

		mShowItemSelection.set(false);
	}

	private void setUpMoveSelection(Scene scene)
	{
		createBindsImageView(mAttackDialogue, scene, 1, 1, mShowMoveSelection);
		createBindsImageView(mAttackSeOne, scene, 1, 1, mShowMoveSeOne);
		createBindsImageView(mAttackSeTwo, scene, 1, 1, mShowMoveSeTwo);
		createBindsImageView(mAttackSeThree, scene, 1, 1, mShowMoveSeThree);
		createBindsImageView(mAttackSeFour, scene, 1, 1, mShowMoveSeFour);

		mMoveSeGroup.visibleProperty().bind(mShowMoveSe);

		createBindsImageView(mAttackBackBtn, scene, 2.25, 1.16, 9, 11, mShowMoveSelection);
		mAttackBackBtn.setOnMouseClicked(event -> onBackBtn());

		ObjectProperty<Font> moveNameFontProperty = getFontProperty(85, scene);
		ObjectProperty<Font> moveMpFontProperty = getFontProperty(95, scene);

		createBindsImageView(mAttackImgOne, scene, 4.5, 1.31, 8.5, 11, mShowMoveSelection.and(mShowMoveOne));
		createBindsTxt(mAttackNameOne, scene, 4.39, 1.24, 9.36, moveNameFontProperty, mShowMoveSelection.and(mShowMoveOne), mAttackNameOneTxt);
		createBindsTxt(mAttackMpOne, scene, 4.39, 1.19, 9.36, moveMpFontProperty, mShowMoveSelection.and(mShowMoveOne), mAttackMpOneTxt);
		mAttackImgOne.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_1); event.consume();});
		mAttackNameOne.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_1); event.consume();});
		mAttackMpOne.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_1); event.consume();});

		createBindsImageView(mAttackImgTwo, scene, 4.5, 1.158, 8.5, 11, mShowMoveSelection.and(mShowMoveTwo));
		createBindsTxt(mAttackNameTwo, scene, 4.39, 1.103, 9.36, moveNameFontProperty, mShowMoveSelection.and(mShowMoveTwo), mAttackNameTwoTxt);
		createBindsTxt(mAttackMpTwo, scene, 4.39, 1.063, 9.36, moveMpFontProperty, mShowMoveSelection.and(mShowMoveTwo), mAttackMpTwoTxt);
		mAttackImgTwo.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_2); event.consume();});
		mAttackNameTwo.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_2); event.consume();});
		mAttackMpTwo.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_2); event.consume();});

		createBindsImageView(mAttackImgThree, scene, 1.515, 1.31, 8.5, 11, mShowMoveSelection.and(mShowMoveThree));
		createBindsTxt(mAttackNameThree, scene, 1.507, 1.24, 9.36, moveNameFontProperty, mShowMoveSelection.and(mShowMoveThree), mAttackNameThreeTxt);
		createBindsTxt(mAttackMpThree, scene, 1.507, 1.19, 9.36, moveMpFontProperty, mShowMoveSelection.and(mShowMoveThree), mAttackMpThreeTxt);
		mAttackImgThree.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_3); event.consume();});
		mAttackNameThree.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_3); event.consume();});
		mAttackMpThree.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_3); event.consume();});

		createBindsImageView(mAttackImgFour, scene, 1.515, 1.158, 8.5, 11, mShowMoveSelection.and(mShowMoveFour));
		createBindsTxt(mAttackNameFour, scene, 1.507, 1.103, 9.36, moveNameFontProperty, mShowMoveSelection.and(mShowMoveFour), mAttackNameFourTxt);
		createBindsTxt(mAttackMpFour, scene, 1.507, 1.063, 9.36, moveMpFontProperty, mShowMoveSelection.and(mShowMoveFour), mAttackMpFourTxt);
		mAttackImgFour.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_4); event.consume();});
		mAttackNameFour.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_4); event.consume();});
		mAttackMpFour.setOnMouseClicked(event -> {activateTurn(BattleChoice.Attack_4); event.consume();});
	}

	private double getFontSize(Scene scene)
	{
		double value = scene.getWidth();

		if(scene.getHeight() < 464)
		{
			value = scene.getHeight() / 0.45;
		}

		if(scene.getWidth() >= 1940)
		{
			value = value - (scene.getWidth() - 1940);
		}

		return value;
	}

	public void updateElements(Player player, Trainer enemyTrainer)
	{
		Anature enemyCurr = enemyTrainer.getAnatures().get(0);
		Anature playerCurr = player.getAnatures().get(0);

		mEnemyName.set(enemyCurr.getName());

		mEnemyHp.set(enemyCurr.getCurrHp());
		mEnemyHpTotal.set(enemyCurr.getTotalHp());

		mEnemyLvl.set(enemyCurr.getLevel());

		mPlayer = player;
		mEnemyTrainer = enemyTrainer;

		mTrainerImage.setImage(enemyTrainer.getBattleSprite());
		updatePlayerAnature(playerCurr);
		updateMoves(playerCurr);
		updateSwitch(player.getAnatures(), player.getSelectedIndex());
		updateBagMenu();
		
		mAnatureFront.setImage(enemyCurr.getFrontSprite());
		
		startInto(player, enemyTrainer, enemyCurr);

		mFightManager = new FightManager(player.getAnatures(), enemyTrainer.getAnatures(), player.getName(), enemyTrainer.getName());
	}
	
	private void startInto(Player player, Trainer enemyTrainer, Anature enemyCurr)
	{
		mClickQueue.enqueue(new Runnable()
		{
			@Override
			public void run()
			{
				PlayerAnimation playerAnimation = new PlayerAnimation(mPlayerImage);
				playerAnimation.isFinished.addListener(new ChangeListener<Boolean>()
				{
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
					{
						OpacityAnimation back = new OpacityAnimation(mAnatureBack, Duration.millis(200), true);
						back.setOnFinished(event -> mShowBtns.set(true));
						back.play();

						mPlayerImage.setVisible(false);
						mTrainerImage.setVisible(false);
					}
				});

				playerAnimation.play();

				if(enemyTrainer.getId() != TrainerIds.Wild)
				{
					OpacityAnimation trainerFade = new OpacityAnimation(mTrainerImage, Duration.millis(1000), false);
					trainerFade.setOnFinished(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent actionEvent)
						{
							OpacityAnimation back = new OpacityAnimation(mAnatureFront, Duration.millis(200), true);
							back.play();
						}
					});

					trainerFade.play();
				}
			}
		});
		
		if(enemyTrainer.getId() == TrainerIds.Wild)
		{
			mAnatureFront.setOpacity(100);
			startIntroSlides(true);
			mDialogueTxt.set(player.getName() + " has encountered a wild " + enemyCurr.getName() + "!");
		}
		
		else
		{
			mAnatureFront.setOpacity(0);
			startIntroSlides(false);
			mDialogueTxt.set(enemyTrainer.getName() + " has started a battle with " + player.getName() + "!");
		}
	}
	
	private void startIntroSlides(boolean isWild)
	{
		XSlideAnimation playerSlide = new XSlideAnimation(mPlayerImage, Duration.millis(1500), 1, 7);
		playerSlide.setOnFinished(event -> mCanClick.set(true));
		playerSlide.play();
		

		XSlideAnimation xPlayerGroundSlide = new XSlideAnimation(mPlayerGroundImage, Duration.millis(1500), 1.1, 8);
		xPlayerGroundSlide.play();
		
		if(!isWild)
		{
			XSlideAnimation trainerSlide = new XSlideAnimation(mTrainerImage, Duration.millis(1500), 1, 1.8);
			trainerSlide.play();

			XSlideAnimation xTrainerGroundSlide = new XSlideAnimation(mTrainerGroundImage, Duration.millis(1500), 1.05, 2.05);
			xTrainerGroundSlide.play();
		}
		
		else
		{
			mTrainerGroundImage.layoutXProperty().bind(mTrainerGroundImage.getScene().widthProperty().divide(2.05));
		}
	}

	private void updatePlayerAnature(Anature playerCurr)
	{
		mPlayerName.set(playerCurr.getName());

		mPlayerHp.set(playerCurr.getCurrHp());
		mPlayerHpTotal.set(playerCurr.getTotalHp());

		mPlayerXp.set(playerCurr.getCurrentXp());
		mPlayerXpTotal.set(100); // TODO change to a standard

		mPlayerLvl.set(playerCurr.getLevel());
		
		mAnatureBack.setImage(playerCurr.getBackSprite());

		updateMoves(playerCurr);
	}

	private void updateMoves(Anature playerCurr)
	{
		MoveSet moves = playerCurr.getMoves(); // TODO Make move btn color change based on move type
		Move move1 = moves.getMove(0);
		Move move2 = moves.getMove(1);
		Move move3 = moves.getMove(2);
		Move move4 = moves.getMove(3);

		updateMove(move1, moves.getMovePoints(0), mShowMoveOne, mAttackNameOneTxt, mAttackMpOneTxt);
		updateMove(move2, moves.getMovePoints(1), mShowMoveTwo, mAttackNameTwoTxt, mAttackMpTwoTxt);
		updateMove(move3, moves.getMovePoints(2), mShowMoveThree, mAttackNameThreeTxt, mAttackMpThreeTxt);
		updateMove(move4, moves.getMovePoints(3), mShowMoveFour, mAttackNameFourTxt, mAttackMpFourTxt);
	}

	private void updateMove(Move moveToCheck, int currMp, BooleanProperty showMove, StringProperty nameTxt, StringProperty mpTxt)
	{
		if(moveToCheck != null)
		{
			showMove.set(true);
			nameTxt.set(moveToCheck.getName());
			mpTxt.set(currMp + "/" + moveToCheck.getTotalMovePoints());
		}

		else
		{
			showMove.set(false);
		}
	}

	private void updateSwitch(ArrayList<Anature> party, int selectedIndex)
	{
		Image anatureImg = new Image(getClass().getResource("/resources/images/anatures/Null_Front.png").toExternalForm());
		boolean isSelected = false;

		switch(party.size())
		{
			case 6:
				isSelected = false;

				if(selectedIndex == 5)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(5), anatureImg, mSwitchSlotSix, mSlotSix, isSelected);

			case 5:
				isSelected = false;

				if(selectedIndex == 4)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(4), anatureImg, mSwitchSlotFive, mSlotFive, isSelected);

			case 4:
				isSelected = false;

				if(selectedIndex == 3)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(3), anatureImg, mSwitchSlotFour, mSlotFour, isSelected);

			case 3:
				isSelected = false;

				if(selectedIndex == 2)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(2), anatureImg, mSwitchSlotThree, mSlotThree, isSelected);

			case 2:
				isSelected = false;

				if(selectedIndex == 1)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(1), anatureImg, mSwitchSlotTwo, mSlotTwo, isSelected);

			case 1:
				isSelected = false;

				if(selectedIndex == 0)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(0), anatureImg, mSwitchSlotOne, mSlotOne, isSelected);
		}

		updateSwitchSelected(mPlayer.getSelectedIndex());
	}

	private void updateSwitchSelected(int selectedIndex)
	{
		mSlotOne.setIsSelected(false);
		mSlotTwo.setIsSelected(false);
		mSlotThree.setIsSelected(false);
		mSlotFour.setIsSelected(false);
		mSlotFive.setIsSelected(false);
		mSlotSix.setIsSelected(false);

		switch(selectedIndex)
		{
			case 0:
				mSlotOne.setIsSelected(true);
				break;

			case 1:
				mSlotTwo.setIsSelected(true);
				break;

			case 2:
				mSlotThree.setIsSelected(true);
				break;

			case 3:
				mSlotFour.setIsSelected(true);
				break;

			case 4:
				mSlotFive.setIsSelected(true);
				break;

			case 5:
				mSlotSix.setIsSelected(true);
				break;
		}

		mSwitchIndexSelected = selectedIndex;

		ArrayList<Anature> party = mPlayer.getAnatures();
		Anature selected = party.get(selectedIndex);

		mSwitchSelectedCatalogNum.setText(String.format("%03d", selected.getIndexNum()));
		mSwitchSelectedName.setText(selected.getName());
		mSwitchSelectedOwner.setText(selected.getOwner());
		mSwitchSelectedCurrXp.setText(selected.getCurrentXp() + "");
		mSwitchSelectedNextXp.setText((100 - selected.getCurrentXp()) + "");

		mSwitchSelectedHp.setText(selected.getTotalHp() + "");
		mSwitchSelectedAtk.setText(selected.getAttack() + "");
		mSwitchSelectedSpAtk.setText(selected.getSpecialAttack() + "");
		mSwitchSelectedDef.setText(selected.getDefense() + "");
		mSwitchSelectedSpDef.setText(selected.getSpecialDefense() + "");

		mSwitchSelectedAbilityName.setText(selected.getAbility().getAbilityName());
		mSwitchSelectedAbilityDesc.setText(selected.getAbility().getAbilityDescription());

		// TODO Add different type imgs
	}

	private void updateSwitchSlot(Anature curr, Image anatureImg, BooleanProperty visibleProp, AnatureSlot slot, boolean isSelected)
	{
		visibleProp.set(true);
		slot.updateSlot(isSelected, anatureImg, curr.getGender(), curr.getName(), "Lvl " + curr.getLevel(), curr.getCurrHp() + "/" + curr.getTotalHp(),
				mShowSwitch.get(), visibleProp.get(), curr.getCurrHp());
	}

	private void updateBagMenu()
	{
		ObservableList<String> items = mItemList.getItems();
		items.clear();

		Backpack backpack = mPlayer.getBackpack();

		int potionCount = backpack.getPotionCount();
		int greatPotionCount = backpack.getGreatPotionCount();
		int ultraPotionCount = backpack.getUltraPotionCount();
		int masterPotionCount = backpack.getMasterPotionCount();

		if(potionCount > 0)
		{
			items.add("Potions " + potionCount + "x");
		}

		if(greatPotionCount > 0)
		{
			items.add("Great Potions " + greatPotionCount + "x");
		}

		if(ultraPotionCount > 0)
		{
			items.add("Ultra Potions " + ultraPotionCount + "x");
		}

		if(masterPotionCount > 0)
		{
			items.add("Master Potions " + masterPotionCount + "x");
		}

		mItemList.getSelectionModel().select(0);
		onItemSelect();

		mItemList.setItems(items);
	}

	private void onItemSelect()
	{
		String selectedItem = mItemList.getSelectionModel().getSelectedItem();

		if(selectedItem == null)
			return;

		if(selectedItem.startsWith("Potions"))
		{
			mSelectedItemImg.setImage(mItemPotion);
			mSelectedItemTxt.set("Potion");
		}

		else if(selectedItem.startsWith("Great"))
		{
			mSelectedItemImg.setImage(mItemGreatPotion);
			mSelectedItemTxt.set("Great Potion");
		}

		else if(selectedItem.startsWith("Ultra"))
		{
			mSelectedItemImg.setImage(mItemUltraPotion);
			mSelectedItemTxt.set("Ultra Potion");
		}

		else if(selectedItem.startsWith("Master"))
		{
			mSelectedItemImg.setImage(mItemMasterPotion);
			mSelectedItemTxt.set("Master Potion");
		}
	}

	@FXML
	public void onPageChange()
	{
		mSwitchPageNum++;

		if(mSwitchPageNum == 2)
		{
			mSwitchSelection.setImage(mSwitchPageTwoImg);
			mShowSwitchPageOne.set(false);
		}

		else
		{
			mSwitchSelection.setImage(mSwitchPageOneImg);
			mShowSwitchPageOne.set(true);
			mSwitchPageNum = 1;
		}

		mSwitchPageTxt.setText("Page " + mSwitchPageNum);
	}

	private void onItemTab(boolean isPotion)
	{
		if(isPotion)
		{
			mItemPotionsTab.setImage(mItemTabSelected);
			mItemAnaCubeTab.setImage(mItemTabDeselected);
		}

		else
		{
			mItemPotionsTab.setImage(mItemTabDeselected);
			mItemAnaCubeTab.setImage(mItemTabSelected);
		}
	}

	private void activateTurn(BattleChoice choice)
	{
		mShowBtns.set(false);
		Anature enemyCurr = mFightManager.getEnemyTeam().get(0);
		Anature playerCurr = mFightManager.getPlayerTeam().get(0);

		AiChoice enemyTurn = mEnemyTrainer.useTurn(playerCurr);

		int whoGoesFirst = playerCurr.getSpeed() - enemyCurr.getSpeed();

		if(whoGoesFirst == 0) // Will either add 0 or 1 to the total
		{
			Random r = new Random();
			whoGoesFirst += r.nextInt(2);
		}

		if(choice == BattleChoice.Switch || choice == BattleChoice.Item)
		{
			whoGoesFirst = 0;
		}

		if(whoGoesFirst == 0) // Player goes first
		{
			playerTurn(choice);
			enemyTurn(enemyTurn);
		}

		else // Enemy goes first
		{
			enemyTurn(enemyTurn);
			playerTurn(choice);
		}

		mClickQueue.enqueue(new Runnable()
		{
			@Override
			public void run()
			{
				mShowBtns.set(true);
				mDialogueTxt.set("What will you do?");
			}
		});

		onBackBtn();
		mShowBtns.set(false);
		mClickQueue.dequeue().run();
	}

	private void playerTurn(BattleChoice choice)
	{
		switch(choice)
		{
			case Attack_1:
				//mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(0), mEnemyHp));
				statusEffectForTurn(mFightManager.getPlayerTeam().get(0), true, 0, BattleChoice.Attack_1, new Runnable()
				{
					
					@Override
					public void run()
					{
						healthDrainMove(mFightManager.attackEnemy(0), mEnemyHp); 
						
					}
				});
				break;

			case Attack_2:
				//mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(1), mEnemyHp));
				statusEffectForTurn(mFightManager.getPlayerTeam().get(0), true, 1, BattleChoice.Attack_2, new Runnable()
				{
					
					@Override
					public void run()
					{
						healthDrainMove(mFightManager.attackEnemy(1), mEnemyHp); 
						
					}
				});
				break;

			case Attack_3:
				//mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(2), mEnemyHp));
				statusEffectForTurn(mFightManager.getPlayerTeam().get(0), true, 2, BattleChoice.Attack_3, new Runnable()
				{
					
					@Override
					public void run()
					{
						healthDrainMove(mFightManager.attackEnemy(2), mEnemyHp); 
						
					}
				});
				break;

			case Attack_4:
				//mClickQueue.enqueue(() -> healthDrainMove(mFightManager.attackEnemy(3), mEnemyHp));
				statusEffectForTurn(mFightManager.getPlayerTeam().get(0), true, 3, BattleChoice.Attack_4, new Runnable()
				{
					
					@Override
					public void run()
					{
						healthDrainMove(mFightManager.attackEnemy(3), mEnemyHp); 
						
					}
				});
				break;

			case Item:
				statusEffectForTurn(mFightManager.getPlayerTeam().get(0), true, -1, BattleChoice.Item, new Runnable()
				{
					
					@Override
					public void run()
					{
						Item selectedItem = ItemPool.getItems(mItemList.getSelectionModel().getSelectedItem());

						ItemResult result = mFightManager.itemUse(true, mPlayer.getSelectedIndex(), selectedItem); // TODO Change it so u can use items on other
																													// anatures
						healthGain(result, mPlayerHp);

						mPlayer.getBackpack().removeItem(selectedItem.getItemId());
						updateBagMenu(); 
						
					}
				});
				break;

			case Escape:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mDialogueTxt.set("You clicked on Escape!\nThat has yet to be implemented!");
						mCanClick.set(true);
					}
				});
				break;

			case Switch:
				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mFightManager.setPlayerSelectedIndex(mSwitchIndexSelected);
						Anature oldAnature = mPlayer.getAnatures().get(mPlayer.getSelectedIndex());
						mPlayer.setSelectedIndex(mSwitchIndexSelected);
						Anature newAnature = mPlayer.getAnatures().get(mPlayer.getSelectedIndex());

						OpacityAnimation fadeOld = new OpacityAnimation(mAnatureBack, Duration.millis(400), false);
						fadeOld.setOnFinished(new EventHandler<ActionEvent>()
						{
							@Override
							public void handle(ActionEvent event)
							{
								updatePlayerAnature(newAnature);

								try
								{
									Thread.sleep(500);
								}

								catch(InterruptedException e)
								{
									LoggerController.logEvent(LoggingTypes.Error, e.getMessage());
								}

								OpacityAnimation fadeInNew = new OpacityAnimation(mAnatureBack, Duration.millis(400), true);
								fadeInNew.setOnFinished(actionEvent -> mCanClick.set(true));
								fadeInNew.play();
							}
						});

						fadeOld.play();
						mDialogueTxt.set("Come on back " + oldAnature.getName() + ".");
					}
				});
				break;
		}
	}

	private void enemyTurn(AiChoice enemyTurn)
	{
//			switch(enemyTurn)
//			{
//				case Move1:
//					statusEffectForTurn(mFightManager.getEnemyTeam().get(0), false, 0, BattleChoice.Attack_1, new Runnable()
//					{
//						@Override
//						public void run()
//						{
//							healthDrainMove(mFightManager.attackPlayer(0), mPlayerHp);
//							
//						}
//					});
//					break;
//
//				case Move2:
//					statusEffectForTurn(mFightManager.getEnemyTeam().get(0), false, 1, BattleChoice.Attack_2, new Runnable()
//					{
//						@Override
//						public void run()
//						{
//							healthDrainMove(mFightManager.attackPlayer(1), mPlayerHp);
//							
//						}
//					});
//					break;
//
//				case Move3:
//					statusEffectForTurn(mFightManager.getEnemyTeam().get(0), false, 2, BattleChoice.Attack_3, new Runnable()
//					{
//						@Override
//						public void run()
//						{
//							healthDrainMove(mFightManager.attackPlayer(2), mPlayerHp);
//							
//						}
//					});
//					break;
//
//				case Move4:
//					statusEffectForTurn(mFightManager.getEnemyTeam().get(0), false, 3, BattleChoice.Attack_4, new Runnable()
//					{
//						@Override
//						public void run()
//						{
//							healthDrainMove(mFightManager.attackPlayer(3), mPlayerHp);
//							
//						}
//					});
//					break;
//
//				default:
//					return;
			
//		}
		statusEffectForTurn(mFightManager.getEnemyTeam().get(0), false, 1, BattleChoice.Attack_4, new Runnable()
				{
					@Override
					public void run()
					{
						healthDrainMove(mFightManager.attackPlayer(1), mPlayerHp);
						
					}
				});
	}

	private void healthDrainMove(MoveResult result, DoubleProperty toChange)
	{
		double damageDone = result.getDamageDone(); 
		if(result.isPlayer() && damageDone > mEnemyHp.doubleValue()) {
			
			damageDone = mEnemyHp.doubleValue(); 
			
		} else if(damageDone > mPlayerHp.doubleValue()) {
			damageDone = mPlayerHp.doubleValue(); 
			
		}
		
		mDialogueTxt.set(result.getDialogue());
		ProgressBarDecrease decrease = new ProgressBarDecrease(toChange, Duration.millis(3000), damageDone);
		decrease.setOnFinished(event -> mCanClick.set(true));
		decrease.play();

		if(result.isPlayer())
		{
			StringProperty mpTxt = null;
			switch(result.getMoveIndex())
			{
				case 0:
					mpTxt = mAttackMpOneTxt;
					break;

				case 1:
					mpTxt = mAttackMpTwoTxt;
					break;

				case 2:
					mpTxt = mAttackMpThreeTxt;
					break;

				case 3:
					mpTxt = mAttackMpFourTxt;
					break;

				default:
					return;
			}

			mpTxt.set(result.getMpTxt());
		}
	}
	
	private void healthDrainStatus(String statusDialogue, Double damageDone, Boolean isPlayer)
	{
		mDialogueTxt.set(statusDialogue);
		ProgressBarDecrease decrease;
		if(isPlayer) {
			decrease = new ProgressBarDecrease(mPlayerHp, Duration.millis(3000), damageDone);
		} else {
			decrease = new ProgressBarDecrease(mEnemyHp, Duration.millis(3000), damageDone);
		}
		
		decrease.setOnFinished(event -> mCanClick.set(true));
		decrease.play();
	}

	private void healthGain(ItemResult result, DoubleProperty toChange)
	{
		double duration = 2000;

		if(result.getHpGained() < 5)
			duration = 100;

		mDialogueTxt.set(result.getDialogue());
		ProgressBarIncrease increase = new ProgressBarIncrease(toChange, Duration.millis(duration), result.getHpGained());
		increase.setOnFinished(event -> mCanClick.set(true));
		increase.play();
	}

	private void onSwitchBtn()
	{
		updateSwitch(mPlayer.getAnatures(), mSwitchIndexSelected);
		mShowSwitch.set(true);
		mShowBtns.set(false);
		mShowPlayerBars.set(false);
	}

	private void onBagBtn()
	{
		mShowItemSelection.set(true);
		mShowBtns.set(false);
	}

	private void onAttackBtn()
	{
		mShowMoveSelection.set(true);
		mShowMoveSe.set(true);
		mShowBtns.set(false);
	}

	private void onBackBtn()
	{
		mShowPlayerBars.set(true);
		mShowSwitch.set(false);
		mShowItemSelection.set(false);
		mShowMoveSelection.set(false);
		mShowMoveSe.set(false);
		mShowBtns.set(true);
	}
	
	private void statusEffectForTurn(Anature anature, boolean isPlayer, int indexOfMove, BattleChoice battleChoice, Runnable runnable) {
		StatusEffects anatureStatus = anature.getStatus();
		
		
		switch (anatureStatus) {
			case Sleep:
				if(battleChoice == BattleChoice.Item) {
					mClickQueue.enqueue(runnable);
				}
				mClickQueue.enqueueToFront(new Runnable() {
	
					@Override
					public void run() {
						if(isPlayer) {
							mStatusIconPlayer.setImage(mSleepStatusIcon);
						} else {
							mStatusIconEnemy.setImage(mSleepStatusIcon);
						}
						
						mDialogueTxt.set(anature.getName() +  " is fast asleep!");
					}
				});
				break;
	
			case Paralysis:
				
				if(battleChoice == BattleChoice.Item) {
					mClickQueue.enqueue(runnable);
				} else if(Math.random() > 0.25) {
					mClickQueue.enqueue(runnable);  
				} 
				
				mClickQueue.enqueueToFront(new Runnable() {
	
					@Override
					public void run() {
						if(isPlayer) {
							mStatusIconPlayer.setImage(mParalyzedStatusIcon);
						} else {
							mStatusIconEnemy.setImage(mParalyzedStatusIcon);
						}
						
						mDialogueTxt.set(anature.getName() +  " is paralysed! It may not be able to move!");
						
					}
				});
				
				break;
				
			case Burn:
				mClickQueue.enqueue(runnable);
				
				mClickQueue.enqueueToFront(new Runnable() {
					
					@Override
					public void run() {
						
							healthDrainStatus(anature.getName() + " is hurt because it is burned!", 
									(double) anature.getTotalHp()/16, 
									isPlayer);
					}
					
				});
				
				if(isPlayer && mStatusIconPlayer.getImage() != mBurnStatusIcon) {
					mClickQueue.enqueueToFront(new Runnable() {
						
						@Override
						public void run() {
							
							mStatusIconPlayer.setImage(mBurnStatusIcon);
							
							mDialogueTxt.set(anature.getName() +  " is burned! ");
							mCanClick.set(true);
							
						}
						
					});
				} else if(!isPlayer && mStatusIconEnemy.getImage() != mBurnStatusIcon) {
					mClickQueue.enqueueToFront(new Runnable() {
						
						@Override
						public void run() {
							
							mStatusIconEnemy.setImage(mBurnStatusIcon);
							
							mDialogueTxt.set(anature.getName() +  " is burned! ");
							mCanClick.set(true);
							
						}
						
					});
				}
				
				break;
				
			default:
				mClickQueue.enqueue(runnable);
				
				break; 
		}
		
		
	}

	private ObjectProperty<Font> getFontProperty(int toDivideBy, Scene scene)
	{
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), toDivideBy);
		ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);

		scene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / toDivideBy)));

		scene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / toDivideBy)));

		return fontProperty;
	}

	private void createBindsImageView(ImageView img, Scene scene, double widthToDivide, double heightToDivide)
	{
		img.fitWidthProperty().bind(scene.widthProperty().divide(widthToDivide));
		img.fitHeightProperty().bind(scene.heightProperty().divide(heightToDivide));
	}

	private void createBindsImageView(ImageView img, Scene scene, double xToDivide, double yToDivide, double widthToDivide, double heightToDivide)
	{
		createBindsImageView(img, scene, widthToDivide, heightToDivide);
		img.layoutXProperty().bind(scene.widthProperty().divide(xToDivide));
		img.layoutYProperty().bind(scene.heightProperty().divide(yToDivide));
	}

	private void createBindsImageView(ImageView img, Scene scene, double xToDivide, double yToDivide, double widthToDivide, double heightToDivide,
			BooleanProperty visibleProp)
	{
		img.visibleProperty().bind(visibleProp);
		createBindsImageView(img, scene, xToDivide, yToDivide, widthToDivide, heightToDivide);
	}

	private void createBindsImageView(ImageView img, Scene scene, double xToDivide, double yToDivide, double widthToDivide, double heightToDivide,
			BooleanBinding visibleProp)
	{
		img.visibleProperty().bind(visibleProp);
		createBindsImageView(img, scene, xToDivide, yToDivide, widthToDivide, heightToDivide);
	}

	private void createBindsImageView(ImageView img, Scene scene, double widthToDivide, double heightToDivide, BooleanProperty visibleProp)
	{
		createBindsImageView(img, scene, widthToDivide, heightToDivide);
		img.visibleProperty().bind(visibleProp);
	}

	private void createBindsImageView(ImageView img, Scene scene, double yToDivide, double widthToDivide, double heightToDivide)
	{
		createBindsImageView(img, scene, widthToDivide, heightToDivide);
		img.layoutYProperty().bind(scene.heightProperty().divide(yToDivide));
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, ObjectProperty<Font> fontProp)
	{
		txt.layoutXProperty().bind(scene.widthProperty().divide(xToDivide));
		txt.layoutYProperty().bind(scene.heightProperty().divide(yToDivide));
		txt.fontProperty().bind(fontProp);
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, ObjectProperty<Font> fontProp, StringProperty stringProp)
	{
		createBindsTxt(txt, scene, xToDivide, yToDivide, fontProp);
		txt.textProperty().bind(stringProp);
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, ObjectProperty<Font> fontProp, BooleanProperty visibleProp)
	{
		createBindsTxt(txt, scene, xToDivide, yToDivide, fontProp);
		txt.visibleProperty().bind(visibleProp);
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, ObjectProperty<Font> fontProp, BooleanBinding visibleProp)
	{
		createBindsTxt(txt, scene, xToDivide, yToDivide, fontProp);
		txt.visibleProperty().bind(visibleProp);
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, double wrapToDivide, ObjectProperty<Font> fontProp,
			BooleanProperty visibleProp)
	{
		createBindsTxt(txt, scene, xToDivide, yToDivide, fontProp, visibleProp);
		txt.wrappingWidthProperty().bind(scene.widthProperty().divide(wrapToDivide));
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, double wrapToDivide, ObjectProperty<Font> fontProp,
			BooleanBinding visibleProp)
	{
		createBindsTxt(txt, scene, xToDivide, yToDivide, fontProp, visibleProp);
		txt.wrappingWidthProperty().bind(scene.widthProperty().divide(wrapToDivide));
	}

	private void createBindsTxt(Text txt, Scene scene, double xToDivide, double yToDivide, double wrapToDivide, ObjectProperty<Font> fontProp,
			BooleanBinding visibleProp, StringProperty stringProp)
	{
		createBindsTxt(txt, scene, xToDivide, yToDivide, wrapToDivide, fontProp, visibleProp);
		txt.textProperty().bind(stringProp);
	}

	private void createBindsAnatureslot(AnatureSlot slot, Scene scene, double xToDivide, double yToDivide, double widthToDivide, double heightToDivide,
			int slotIndex)
	{
		slot.layoutXProperty().bind(scene.widthProperty().divide(xToDivide));
		slot.layoutYProperty().bind(scene.heightProperty().divide(yToDivide));
		slot.prefWidthProperty().bind(scene.widthProperty().divide(widthToDivide));
		slot.prefHeightProperty().bind(scene.heightProperty().divide(heightToDivide));
		slot.setOnMouseClick(event -> updateSwitchSelected(slotIndex));
	}
}