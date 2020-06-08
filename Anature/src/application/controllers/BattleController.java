package application.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import application.EvolutionManager;
import application.FightManager;
import application.Startup;
import application.anatures.movesets.MoveSet;
import application.animations.AnacubeThrow;
import application.animations.BlinkingAnimation;
import application.animations.ImageViewBounce;
import application.animations.OpacityAnimation;
import application.animations.PlayerAnimation;
import application.animations.ProgressBarDecrease;
import application.animations.ProgressBarIncrease;
import application.animations.ThreeFrameAnimation;
import application.animations.XSlideAnimation;
import application.animations.XpBarIncrease;
import application.controllers.results.AbilityResult;
import application.controllers.results.BattleResult;
import application.controllers.results.ItemResult;
import application.controllers.results.MoveResult;
import application.enums.AiChoice;
import application.enums.AnacubeResults;
import application.enums.BattleAnimationType;
import application.enums.BattleChoice;
import application.enums.BattleEndMethods;
import application.enums.Gender;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.enums.Species;
import application.enums.Stat;
import application.enums.StatusEffects;
import application.enums.TrainerIds;
import application.enums.Type;
import application.interfaces.AiChoiceObject;
import application.interfaces.IAnature;
import application.interfaces.IItem;
import application.interfaces.IMove;
import application.interfaces.ITrainer;
import application.items.HealthPotionBase;
import application.player.Backpack;
import application.player.Player;
import application.pools.ItemPool;
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
import javafx.scene.effect.ColorAdjust;
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
	@FXML private ImageView mClickIndicatorImg, mThrownAnacubeImg;
	@FXML private Button mTestBtn;

	@FXML private ImageView mSwitchSelection, mSwitchDialogue, mSwitchBtn, mSwitchBackBtn, mSwitchSelectedImg;
	@FXML private ImageView mSwitchSelectedTypeOne, mSwitchSelectedTypeTwo, mSwitchPageLeft, mSwitchPageRight;
	@FXML private Text mSwitchSelectedCatalogNum, mSwitchSelectedName, mSwitchSelectedOwner, mSwitchSelectedCurrXp, mSwitchSelectedNextXp, mSwitchPageTxt;
	@FXML private Text mSwitchSelectedHp, mSwitchSelectedAtk, mSwitchSelectedSpAtk, mSwitchSelectedDef, mSwitchSelectedSpDef;
	@FXML private Text mSwitchSelectedSpeed, mSwitchSelectedAbilityName, mSwitchSelectedAbilityDesc;
	@FXML private ImageView mStatusIconPlayer, mStatusIconEnemy;
	@FXML private ImageView mFightAnimationPlayer, mFightAnimationEnemy;

	private Image mSwitchPageOneImg, mSwitchPageTwoImg, mItemTabSelected, mItemTabDeselected;
	private Image mItemPotion, mItemGreatPotion, mItemUltraPotion, mItemMasterPotion;
	private Image mItemAnacube, mItemSuperAnacube, mItemHyperAnacube, mItemMaxAnacube;
	private Image mBurnStatusIcon, mParalyzedStatusIcon, mSleepStatusIcon;
	private Image mMaleIcon, mFemaleIcon;
	private Image mFistTopRightIcon, mFistBottomLeftIcon, mFistCenterIcon;
	private Image mSpecialAttackLowerCenterIcon, mSpecialAttackLowerTopRightIcon, mSpecialAttackLowerBottomLeftIcon;
	private Image mSpecialAttackRaiseBottomLeftIcon, mSpecialAttackRaiseCenterIcon, mSpecialAttackRaiseTopRightIcon;
	private Image mSpecialAttackBottomLeftIcon, mSpecialAttackCenterIcon, mSpecialAttackTopRightIcon;

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
	private BooleanProperty mShowBtns, mShowMoveSelection, mShowMoveSe, mShowMoveOne, mShowMoveTwo, mShowMoveThree, mShowMoveFour;
	private BooleanProperty mShowMoveSeOne, mShowMoveSeTwo, mShowMoveSeThree, mShowMoveSeFour;
	private BooleanProperty mSwitchSlotOne, mSwitchSlotTwo, mSwitchSlotThree, mSwitchSlotFour, mSwitchSlotFive, mSwitchSlotSix, mShowSwitchBackBtn;
	private StringProperty mAttackNameOneTxt, mAttackMpOneTxt, mAttackNameTwoTxt, mAttackMpTwoTxt;
	private StringProperty mAttackNameThreeTxt, mAttackMpThreeTxt, mAttackNameFourTxt, mAttackMpFourTxt;
	private ObjectProperty<Font> m55FontProperty, m65FontProperty, m75FontProperty, m85FontProperty, m115FontProperty;

	private FightManager mFightManager;
	private ITrainer mEnemyTrainer;
	private Player mPlayer;
	private ClickQueue mClickQueue;
	private AnatureSlot mSlotOne, mSlotTwo, mSlotThree, mSlotFour, mSlotFive, mSlotSix;
	private int mSwitchPageNum, mSwitchIndexSelected;
	private boolean mToEnd, mPlayerFaintSequenceActive;
	private boolean mShowPotionTab;

	public void initialize()
	{
		mFightManager = null;
		mEnemyTrainer = null;
		mClickQueue = new ClickQueue();
		mSwitchPageNum = 1;
		mSwitchIndexSelected = 0;
		mToEnd = false;
		mPlayerFaintSequenceActive = false;
		mShowPotionTab = true;

		initializeIntegersPorperties();
		initializeDoublePorperties();
		initializeStringPorperties();
		initializeBooleanProperties();
		initializeImages();
	}

	private void initializeIntegersPorperties()
	{
		mEnemyLvl = new SimpleIntegerProperty(100);
		mPlayerLvl = new SimpleIntegerProperty(100);
	}

	private void initializeDoublePorperties()
	{
		mEnemyHp = new SimpleDoubleProperty(100);
		mEnemyHpTotal = new SimpleDoubleProperty(100);
		mPlayerHp = new SimpleDoubleProperty(100);
		mPlayerHpTotal = new SimpleDoubleProperty(100);
		mPlayerXp = new SimpleDoubleProperty(0);
		mPlayerXpTotal = new SimpleDoubleProperty(100);
	}

	private void initializeStringPorperties()
	{
		mDialogueTxt = new SimpleStringProperty("1\n2\n3");
		mPlayerName = new SimpleStringProperty("Player Name");
		mEnemyName = new SimpleStringProperty("Enemy Name");

		mSelectedItemTxt = new SimpleStringProperty("Item Name");

		mAttackNameOneTxt = new SimpleStringProperty("Attack 1");
		mAttackNameTwoTxt = new SimpleStringProperty("Attack 2");
		mAttackNameThreeTxt = new SimpleStringProperty("Attack 3");
		mAttackNameFourTxt = new SimpleStringProperty("Attack 4");

		mAttackMpOneTxt = new SimpleStringProperty("10/10");
		mAttackMpTwoTxt = new SimpleStringProperty("10/10");
		mAttackMpThreeTxt = new SimpleStringProperty("10/10");
		mAttackMpFourTxt = new SimpleStringProperty("10/10");
	}

	private void initializeBooleanProperties()
	{
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
		mShowSwitchBackBtn = new SimpleBooleanProperty(false);

		mCanClick = new SimpleBooleanProperty(false);
	}

	private void initializeImages()
	{
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
		mSleepStatusIcon = new Image(getClass().getResource("/resources/images/statuses/Sleep.png").toExternalForm());

		mMaleIcon = new Image(getClass().getResource("/resources/images/battle/Male_Symbol.png").toExternalForm());
		mFemaleIcon = new Image(getClass().getResource("/resources/images/battle/Female_Symbol.png").toExternalForm());

		mItemAnacube = new Image(getClass().getResource("/resources/images/items/Anacube.png").toExternalForm());
		mItemSuperAnacube = new Image(getClass().getResource("/resources/images/items/Super_Anacube.png").toExternalForm());
		mItemHyperAnacube = new Image(getClass().getResource("/resources/images/items/Hyper_Anacube.png").toExternalForm());
		mItemMaxAnacube = new Image(getClass().getResource("/resources/images/items/Max_Anacube.png").toExternalForm());

		initializeBattleAnimationImgs();
	}

	private void initializeBattleAnimationImgs()
	{
		mSpecialAttackLowerCenterIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/MinusCenter.png").toExternalForm());
		mSpecialAttackLowerTopRightIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/MinusTopRight.png").toExternalForm());
		mSpecialAttackLowerBottomLeftIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/MinusBottomLeft.png").toExternalForm());

		mSpecialAttackRaiseCenterIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/PlusCenter.png").toExternalForm());
		mSpecialAttackRaiseBottomLeftIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/PlusBottomLeft.png").toExternalForm());
		mSpecialAttackRaiseTopRightIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/PlusTopRight.png").toExternalForm());

		mFistCenterIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/FistCenter.png").toExternalForm());
		mFistBottomLeftIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/FistBottomLeft.png").toExternalForm());
		mFistTopRightIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/FistTopRight.png").toExternalForm());

		mSpecialAttackCenterIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/SpecialAttackCenter.png").toExternalForm());
		mSpecialAttackBottomLeftIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/SpecialAttackBottomLeft.png").toExternalForm());
		mSpecialAttackTopRightIcon = getHighQualityImg(getClass().getResource("/resources/images/battle/SpecialAttackTopRight.png").toExternalForm());
	}

	public void setUpBindingsAndElements(Scene scene)
	{
		setUpFontProperties(scene);
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
		setUpAnatureAnimations(scene);
		setUpAnacubeImg(scene);
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

	private void setUpAnatureAnimations(Scene scene)
	{
		createBindsImageView(mFightAnimationPlayer, scene, 1.75, 7.5, 5.5, 3.5);
		createBindsImageView(mFightAnimationEnemy, scene, 5, 2.9, 4, 2.5);

		mFightAnimationPlayer.setImage(null);
		mFightAnimationEnemy.setImage(null);
	}

	private void setUpAnacubeImg(Scene scene)
	{
		createBindsImageView(mThrownAnacubeImg, scene, 20.98, 11.8);
	}

	private void setUpAnatureNames(Scene scene)
	{
		createBindsTxt(mPlayerNameTxt, scene, 1.75, 2.08, m55FontProperty, mPlayerName);
		createBindsTxt(mEnemyNameTxt, scene, 4.9, 9.7, m55FontProperty, mEnemyName);
	}

	private void setUpAnatureHpTxt(Scene scene)
	{
		StringProperty playerHpTxt = new SimpleStringProperty(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue());
		ChangeListener<Number> player = (observable, oldValue, newValue) -> playerHpTxt
				.set(mPlayerHp.getValue().intValue() + " / " + mPlayerHpTotal.getValue().intValue());
		mPlayerHp.addListener(player);
		mPlayerHpTotal.addListener(player);

		StringProperty enemyHpTxt = new SimpleStringProperty(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue());
		ChangeListener<Number> enemy = (observable, oldValue, newValue) -> enemyHpTxt
				.set(mEnemyHp.getValue().intValue() + " / " + mEnemyHpTotal.getValue().intValue());
		mEnemyHp.addListener(enemy);
		mEnemyHpTotal.addListener(enemy);

		createBindsTxt(mPlayerHpTxt, scene, 1.41, 1.83, m85FontProperty, playerHpTxt);
		createBindsTxt(mEnemyHpTxt, scene, 4.7, 5.8, m85FontProperty, enemyHpTxt);
	}

	private void setUpAnatureLvlTxt(Scene scene)
	{
		StringProperty playerLvlTxt = new SimpleStringProperty("Lvl " + mPlayerLvl.get());
		mPlayerLvl.addListener((observable, oldValue, newValue) -> playerLvlTxt.set("Lvl " + mPlayerLvl.get()));

		StringProperty enemyLvlTxt = new SimpleStringProperty("Lvl " + mEnemyLvl.get());
		mEnemyLvl.addListener((observable, oldValue, newValue) -> enemyLvlTxt.set("Lvl " + mEnemyLvl.get()));

		createBindsTxt(mPlayerLvlTxt, scene, 1.71, 1.83, m85FontProperty, playerLvlTxt);
		createBindsTxt(mEnemyLvlTxt, scene, 2.61, 5.8, m85FontProperty, enemyLvlTxt);
	}

	private void setUpAnatureHpAndXpBars(Scene scene)
	{
		HpBar playerHpBar = new HpBar(mPlayerHp, mPlayerHpTotal, scene);
		playerHpBar.bindX(1.509);
		playerHpBar.bindY(1.995);
		playerHpBar.visibleProperty().bind(mShowPlayerBars);

		mPane.getChildren().add(playerHpBar);

		HpBar enemyHpBar = new HpBar(mEnemyHp, mEnemyHpTotal, scene);
		enemyHpBar.bindX(4.15);
		enemyHpBar.bindY(7.95);

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
		escapeImage.setOnAction(event -> endBattle(BattleEndMethods.Escape)); // TODO for demo
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
				int experienceToEarn = mFightManager.getPlayerAnature().getStats().getRequiredExperience() / 10;
				mFightManager.getPlayerAnature().getStats().addExperience(experienceToEarn);

				mPlayerXp.set(mFightManager.getPlayerAnature().getStats().getExperienceProgression());
			}
		});
	}

	private void setUpDialogue(Scene scene)
	{
		mDialogueTxtArea.textProperty().bind(mDialogueTxt);
		mDialogueTxtArea.getStylesheets().add("/resources/css/BattleStyle.css");
		mDialogueTxtArea.prefWidthProperty().bind(scene.widthProperty().divide(3.2));
		mDialogueTxtArea.prefHeightProperty().bind(scene.heightProperty().divide(5));
		mDialogueTxtArea.layoutYProperty().bind(scene.heightProperty().divide(1.32));
		mDialogueTxtArea.layoutXProperty().bind(scene.widthProperty().divide(4.6));
		mDialogueTxtArea.fontProperty().bind(m75FontProperty);
	}

	private void onPlayerAnatureDeath()
	{
		boolean isThereAliveAnatureInParty = false;
		for(IAnature anature : mPlayer.getAnatures())
		{
			if(anature.getStats().getCurrentHitPoints() > 0)
			{
				isThereAliveAnatureInParty = true;
				break;
			}
		}

		if(isThereAliveAnatureInParty)
		{
			Runnable showSwitch = new Runnable()
			{
				@Override
				public void run()
				{
					onSwitchBtn();
					mShowSwitchBackBtn.set(false);
				}
			};

			if(!afterAllTurnsStatusCheck(false, mFightManager.getEnemyAnature(), showSwitch))
			{
				showSwitch.run();
			}

			else
			{
				mPlayerFaintSequenceActive = true;
				mClickQueue.dequeue().run();
			}
		}

		else
		{
			mDialogueTxt.set("You have no Anature left!");
			mShowBtns.set(false);

			mClickQueue.clear();
			mClickQueue.enqueue(() -> mDialogueTxt.set("You have no more Anatures!\nYou quickly run back to the nearest Rest Station!"), "Player Dead");
			mClickQueue.enqueue(() -> endBattle(BattleEndMethods.Defeat), "To Overworld");

			mCanClick.set(true);
			mToEnd = true;
		}
	}

	private void onEnemyAnatureDeath()
	{
		boolean isThereAliveAnatureInParty = false;
		for(IAnature anature : mEnemyTrainer.getAnatureParty())
		{
			if(anature.getStats().getCurrentHitPoints() > 0)
			{
				isThereAliveAnatureInParty = true;
				break;
			}
		}

		addEvs();

		if(isThereAliveAnatureInParty)
		{
			evaluateAnatureExperienceGain();

			afterAllTurnsStatusCheck(true, mFightManager.getPlayerAnature(), () ->
			{
				System.out.println("Choosing enemy anature yet to be implemented!"); // TODO
			});
		}

		else
		{
			playerWin(false);
		}
	}

	private void evaluateAnatureExperienceGain()
	{
		ArrayList<IAnature> defeatedAnatures = new ArrayList<IAnature>();
		for(IAnature anature : mFightManager.getEnemyTeam())
		{
			if(anature.getStats().getCurrentHitPoints() == 0)
			{
				defeatedAnatures.add(anature);
			}
		}

		ArrayList<IAnature> participatingAnatures = mFightManager.getPlayerParticipantingAnatures();
		// TODO we need to gather the Exp gains here or somewhere else
		// TODO there is more to do for Exp share but we don't have it
		double isTrainerCalculation = mEnemyTrainer.getId().equals(TrainerIds.Wild) ? 1.0 : 1.5;

		for(IAnature playerAnature : participatingAnatures)
		{
			double playerAnatureLevel = playerAnature.getStats().getLevel();

			for(IAnature enemyAnature : defeatedAnatures)
			{
				double enemyAnatureLevel = enemyAnature.getStats().getLevel();

				double calculationA = (enemyAnatureLevel * 2) + 10;
				double calculationB = enemyAnatureLevel + playerAnatureLevel + 10;
				double calculationC = (enemyAnatureLevel * playerAnatureLevel) / 5.0 * isTrainerCalculation;

				double finalCalculation = calculationC * (Math.floor(Math.pow(calculationA, 2.5)) / Math.floor(Math.pow(calculationB, 2.5)));

				int result = ((int) finalCalculation) + 1;

				int lvlsGained = playerAnature.getStats().addExperience(result);
				updateXp(playerAnature, result, lvlsGained);
			}
		}
	}

	private void updateXp(IAnature anature, int xpGained, int lvlsGained)
	{
		mClickQueue.enqueue(() ->
		{
			String toDisplay = anature.getName() + " gained " + xpGained + " xp!";

			if(lvlsGained > 0)
			{
				toDisplay += "\nAnd also leveled up!";
			}

			mDialogueTxt.set(toDisplay);

			if(mFightManager.getPlayerAnature().equals(anature))
			{
				mCanClick.set(false);
				animateXpBar(xpGained, lvlsGained);
			}

		}, "Xp display for currently showing Anature");
	}

	private void animateXpBar(int xpGained, int lvlsGained)
	{
		double newCurrXp = mPlayerXp.get() + xpGained;

		if(newCurrXp > mPlayerXpTotal.doubleValue())
		{
			newCurrXp = mPlayerXpTotal.doubleValue() - mPlayerXp.doubleValue();
		}

		XpBarIncrease animation = new XpBarIncrease(mPlayerXp, Duration.seconds(1), mPlayerXp.get() + newCurrXp, lvlsGained);
		animation.setOnFinished(event ->
		{
			IAnature curr = mFightManager.getPlayerAnature();

			if(animation.getLvlsGained() > 0)
			{
				mPlayerLvl.set(mPlayerLvl.get() + 1);
			}

			if(animation.getLvlsGained() - 1 > 0)
			{
				mPlayerXp.set(0);
				mPlayerXpTotal.set(100);
				animateXpBar(100, animation.getLvlsGained() - 1);
			}

			else if(animation.getLvlsGained() - 1 == 0)
			{
				mPlayerXp.set(0);
				mPlayerXpTotal.set(curr.getStats().getRequiredExperience());
				animateXpBar(curr.getStats().getExperienceProgression(), -1);
			}

			else
			{
				mCanClick.set(true);
			}
		});
		animation.play();
	}

	private void setUpClickTracker(Scene scene)
	{
		scene.setOnMouseClicked(event -> dequeueClickTracker(event));
		scene.setOnKeyReleased(keyEvent ->
		{
			switch(keyEvent.getCode())
			{
				case E:
					dequeueClickTracker(keyEvent);
					break;

				default:
					break;
			}
		});
	}

	private void setUpSwitchElements(Scene scene)
	{
		createBindsImageView(mSwitchSelection, scene, 1, 1, mShowSwitch);
		createBindsImageView(mSwitchDialogue, scene, 1, 1, mShowSwitch);

		createBindsImageView(mSwitchBtn, scene, 2.71, 1.5, 8.31, 21.818, mShowSwitch);
		mSwitchBtn.setOnMouseClicked(event ->
		{
			event.consume();

			if(mShowSwitchBackBtn.get())
			{
				activateTurn(BattleChoice.Switch);
			}

			else
			{
				if(mPlayer.getAnatures().get(mSwitchIndexSelected).getStats().getCurrentHitPoints() <= 0)
				{
					return;
				}

				activateSwitch(null);

				try
				{
					Thread.sleep(10);
				}

				catch(InterruptedException e)
				{
					LoggerController.logEvent(LoggingTypes.Error, "The pause in-between activateSwitch() & the rest ui enqueue was interrupted.");
				}

				mClickQueue.enqueue(new Runnable()
				{
					@Override
					public void run()
					{
						mShowBtns.set(true);
						mDialogueTxt.set("What will you do?");
						mCanClick.set(false);
					}
				}, "Reset GUI");

				try
				{
					Thread.sleep(10); // Without a short pause here, the game will sometimes not perform the switch
				}

				catch(InterruptedException e)
				{
					LoggerController.logEvent(LoggingTypes.Error, "The pause after the reset ui enqueue in the switch button event was interrupted.");
				}

				onBackBtn();
				mShowBtns.set(false);
				mClickQueue.dequeue().run();
			}
		});

		createBindsImageView(mSwitchBackBtn, scene, 2.25, 1.16, 9, 11, mShowSwitch.and(mShowSwitchBackBtn));
		mSwitchBackBtn.setOnMouseClicked(event ->
		{
			onBackBtn();
			mSwitchIndexSelected = mPlayer.getSelectedIndex();
		});

		createBindsImageView(mSwitchSelectedImg, scene, 2.889, 3.396, 5.638, 3.03, mShowSwitch); // TODO update Image

		setUpSwitchPageOne(scene, m65FontProperty);
		setUpSwitchPageTwo(scene, m65FontProperty);

		createBindsTxt(mSwitchPageTxt, scene, 1.16, 5.4, m65FontProperty, mShowSwitch);

		createBindsImageView(mSwitchPageLeft, scene, 1.245, 6.99, 46.23, 18.46, mShowSwitch);
		createBindsImageView(mSwitchPageRight, scene, 1.043, 6.99, 46.23, 18.46, mShowSwitch);

		setUpAnatureTabs(scene);
	}

	private void setUpStatuses(Scene scene)
	{
		createBindsImageView(mStatusIconPlayer, scene, 1.578, 1.909, 26.122, 34.285, mShowPlayerBars);
		mStatusIconPlayer.setImage(null);

		createBindsImageView(mStatusIconEnemy, scene, 3.122, 6.79, 26.122, 34.285);
		mStatusIconEnemy.setImage(null);
	}

	private void setUpSwitchPageOne(Scene scene, ObjectProperty<Font> fontTracking)
	{
		createBindsTxt(mSwitchSelectedCatalogNum, scene, 1.2895, 3.7, fontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedName, scene, 1.2895, 2.8, fontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedOwner, scene, 1.2895, 1.93, m75FontProperty, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedCurrXp, scene, 1.2895, 1.65, fontTracking, mShowSwitchPageOne.and(mShowSwitch));
		createBindsTxt(mSwitchSelectedNextXp, scene, 1.2895, 1.45, fontTracking, mShowSwitchPageOne.and(mShowSwitch));

		createBindsImageView(mSwitchSelectedTypeOne, scene, 1.298, 2.52, 10.578, 18, mShowSwitchPageOne.and(mShowSwitch));
		createBindsImageView(mSwitchSelectedTypeTwo, scene, 1.1469, 2.52, 10.578, 18, mShowSwitchPageOne.and(mShowSwitch));
	}

	private void setUpSwitchPageTwo(Scene scene, ObjectProperty<Font> fontTracking)
	{
		createBindsTxt(mSwitchSelectedHp, scene, 1.4, 3.7, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedAtk, scene, 1.4, 3, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedSpAtk, scene, 1.4, 2.55, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedDef, scene, 1.4, 2.2, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedSpDef, scene, 1.4, 1.94, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedSpeed, scene, 1.4, 1.73, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedAbilityName, scene, 1.4, 1.57, fontTracking, mShowSwitchPageOne.not().and(mShowSwitch));
		createBindsTxt(mSwitchSelectedAbilityDesc, scene, 1.4, 1.48, 3.71, m115FontProperty, mShowSwitchPageOne.not().and(mShowSwitch));
	}

	private void setUpAnatureTabs(Scene scene)
	{
		Image anatureImg = new Image(getClass().getResource("/resources/images/anatures/Null_Front.png").toExternalForm());

		mSlotOne = new AnatureSlot(scene, true, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotOne, 100.0, 100.0, true, StatusEffects.None);
		mSlotTwo = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotTwo, 100.0, 100.0, false, StatusEffects.None);
		mSlotThree = new AnatureSlot(scene, false, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotThree, 100.0, 100.0, false, StatusEffects.None);
		mSlotFour = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotFour, 100.0, 100.0, false, StatusEffects.None);
		mSlotFive = new AnatureSlot(scene, false, anatureImg, Gender.Female, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotFive, 100.0, 100.0, false, StatusEffects.None);
		mSlotSix = new AnatureSlot(scene, false, anatureImg, Gender.Male, "Null", "Lvl 5", "20/20", mShowSwitch, mSwitchSlotSix, 100.0, 100.0, false, StatusEffects.None);

		createBindsAnatureslot(mSlotOne, scene, 85, 4.2, 3.7, 15.1, 0); // TODO update images and hp bars
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

		createBindsTxt(mSelectedItemName, scene, 28.44, 4.0, 5.8479, m85FontProperty, mShowItemSelection);
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

		createBindsImageView(mAttackImgOne, scene, 4.5, 1.31, 8.5, 11, mShowMoveSelection.and(mShowMoveOne));
		createBindsTxt(mAttackNameOne, scene, 4.39, 1.24, 9.36, m85FontProperty, mShowMoveSelection.and(mShowMoveOne), mAttackNameOneTxt);
		createBindsTxt(mAttackMpOne, scene, 4.39, 1.19, 9.36, m75FontProperty, mShowMoveSelection.and(mShowMoveOne), mAttackMpOneTxt);
		mAttackImgOne.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_1);
			event.consume();
		});
		mAttackNameOne.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_1);
			event.consume();
		});
		mAttackMpOne.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_1);
			event.consume();
		});

		createBindsImageView(mAttackImgTwo, scene, 4.5, 1.158, 8.5, 11, mShowMoveSelection.and(mShowMoveTwo));
		createBindsTxt(mAttackNameTwo, scene, 4.39, 1.103, 9.36, m85FontProperty, mShowMoveSelection.and(mShowMoveTwo), mAttackNameTwoTxt);
		createBindsTxt(mAttackMpTwo, scene, 4.39, 1.063, 9.36, m75FontProperty, mShowMoveSelection.and(mShowMoveTwo), mAttackMpTwoTxt);
		mAttackImgTwo.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_2);
			event.consume();
		});
		mAttackNameTwo.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_2);
			event.consume();
		});
		mAttackMpTwo.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_2);
			event.consume();
		});

		createBindsImageView(mAttackImgThree, scene, 1.515, 1.31, 8.5, 11, mShowMoveSelection.and(mShowMoveThree));
		createBindsTxt(mAttackNameThree, scene, 1.507, 1.24, 9.36, m85FontProperty, mShowMoveSelection.and(mShowMoveThree), mAttackNameThreeTxt);
		createBindsTxt(mAttackMpThree, scene, 1.507, 1.19, 9.36, m75FontProperty, mShowMoveSelection.and(mShowMoveThree), mAttackMpThreeTxt);
		mAttackImgThree.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_3);
			event.consume();
		});
		mAttackNameThree.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_3);
			event.consume();
		});
		mAttackMpThree.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_3);
			event.consume();
		});

		createBindsImageView(mAttackImgFour, scene, 1.515, 1.158, 8.5, 11, mShowMoveSelection.and(mShowMoveFour));
		createBindsTxt(mAttackNameFour, scene, 1.507, 1.103, 9.36, m85FontProperty, mShowMoveSelection.and(mShowMoveFour), mAttackNameFourTxt);
		createBindsTxt(mAttackMpFour, scene, 1.507, 1.063, 9.36, m75FontProperty, mShowMoveSelection.and(mShowMoveFour), mAttackMpFourTxt);
		mAttackImgFour.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_4);
			event.consume();
		});
		mAttackNameFour.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_4);
			event.consume();
		});
		mAttackMpFour.setOnMouseClicked(event ->
		{
			activateTurn(BattleChoice.Attack_4);
			event.consume();
		});
	}

	private void setUpFontProperties(Scene scene)
	{
		m55FontProperty = createFontProperty(55);
		m65FontProperty = createFontProperty(65);
		m75FontProperty = createFontProperty(75);
		m85FontProperty = createFontProperty(85);
		m115FontProperty = createFontProperty(115);

		ChangeListener<Number> fontListener = (observableValue, oldWidth, newWidth) ->
		{
			m55FontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 55));
			m65FontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 65));
			m75FontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 75));
			m85FontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 85));
			m115FontProperty.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize(scene) / 115));
		};

		scene.widthProperty().addListener(fontListener);
		scene.heightProperty().addListener(fontListener);
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

	public void updateElements(Player player, ITrainer enemyTrainer)
	{
		IAnature enemyCurr = enemyTrainer.getAnatureParty().get(0);
		IAnature playerCurr = player.getAnatures().get(0);

		mEnemyName.set(enemyCurr.getName());

		mEnemyHp.set(enemyCurr.getStats().getCurrentHitPoints());
		mEnemyHpTotal.set(enemyCurr.getStats().getTotalStat(Stat.HitPoints));

		mEnemyLvl.set(enemyCurr.getStats().getLevel());

		mPlayer = player;
		mEnemyTrainer = enemyTrainer;

		mTrainerImage.setImage(enemyTrainer.getBattleSprite());
		updatePlayerAnature(playerCurr);
		updateMoves(playerCurr);
		updateSwitch(player.getAnatures(), player.getSelectedIndex());
		updateBagMenu();
		updateGender(playerCurr, true);
		updateGender(enemyCurr, false);

		mAnatureFront.setImage(enemyCurr.getFrontSprite());

		startIntro(player, enemyTrainer, enemyCurr);

		mFightManager = new FightManager(player.getAnatures(), enemyTrainer.getAnatureParty());
		mThrownAnacubeImg.setVisible(false);
	}

	private void startIntro(Player player, ITrainer enemyTrainer, IAnature enemyCurr)
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
						back.setOnFinished(event ->
						{
							boolean isPlayer = true;

							activateEntryAbility(isPlayer);
							activateEntryAbility(!isPlayer);

							mClickQueue.enqueue(() -> resetGui(), "Reset Gui");
							mCanClick.set(true);
						});
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
		}, "Start Intro Animations");

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

	private void updatePlayerAnature(IAnature playerCurr)
	{
		mPlayerName.set(playerCurr.getName());

		mPlayerHp.set(playerCurr.getStats().getCurrentHitPoints());
		mPlayerHpTotal.set(playerCurr.getStats().getTotalStat(Stat.HitPoints));

		mPlayerXp.set(playerCurr.getStats().getExperienceProgression());
		mPlayerXpTotal.set(playerCurr.getStats().getRequiredExperience());

		mPlayerLvl.set(playerCurr.getStats().getLevel());
		updateStatusIcon(mStatusIconPlayer, playerCurr);

		mAnatureBack.setImage(playerCurr.getBackSprite());

		updateMoves(playerCurr);
	}

	private void updateEnemyAnature(IAnature enemyCurr)
	{
		mEnemyName.set(enemyCurr.getName());

		mEnemyHp.set(enemyCurr.getStats().getCurrentHitPoints());
		mEnemyHpTotal.set(enemyCurr.getStats().getTotalStat(Stat.HitPoints));

		mEnemyLvl.set(enemyCurr.getStats().getLevel());

		mAnatureFront.setImage(enemyCurr.getFrontSprite());
	}

	private void updateMoves(IAnature playerCurr)
	{
		MoveSet moves = playerCurr.getMoveSet(); // TODO Make move btn color change based on move type
		IMove move1 = moves.getMove(1);
		IMove move2 = moves.getMove(2);
		IMove move3 = moves.getMove(3);
		IMove move4 = moves.getMove(4);

		updateMove(move1, moves.getMovePoints(1), mShowMoveOne, mAttackNameOneTxt, mAttackMpOneTxt);
		updateMove(move2, moves.getMovePoints(2), mShowMoveTwo, mAttackNameTwoTxt, mAttackMpTwoTxt);
		updateMove(move3, moves.getMovePoints(3), mShowMoveThree, mAttackNameThreeTxt, mAttackMpThreeTxt);
		updateMove(move4, moves.getMovePoints(4), mShowMoveFour, mAttackNameFourTxt, mAttackMpFourTxt);
	}

	private void updateMove(IMove moveToCheck, int currMp, BooleanProperty showMove, StringProperty nameTxt, StringProperty mpTxt)
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

	private void updateSwitch(ArrayList<IAnature> party, int selectedIndex)
	{
		boolean isSelected = false;

		switch(party.size())
		{
			case 6:
				isSelected = false;

				if(selectedIndex == 5)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(5), party.get(5).getFrontSprite(), mSwitchSlotSix, mSlotSix, isSelected);

			case 5:
				isSelected = false;

				if(selectedIndex == 4)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(4), party.get(4).getFrontSprite(), mSwitchSlotFive, mSlotFive, isSelected);

			case 4:
				isSelected = false;

				if(selectedIndex == 3)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(3), party.get(3).getFrontSprite(), mSwitchSlotFour, mSlotFour, isSelected);

			case 3:
				isSelected = false;

				if(selectedIndex == 2)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(2), party.get(2).getFrontSprite(), mSwitchSlotThree, mSlotThree, isSelected);

			case 2:
				isSelected = false;

				if(selectedIndex == 1)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(1), party.get(1).getFrontSprite(), mSwitchSlotTwo, mSlotTwo, isSelected);

			case 1:
				isSelected = false;

				if(selectedIndex == 0)
				{
					isSelected = true;
				}

				updateSwitchSlot(party.get(0), party.get(0).getFrontSprite(), mSwitchSlotOne, mSlotOne, isSelected);
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

		ArrayList<IAnature> party = mPlayer.getAnatures();
		IAnature selected = party.get(selectedIndex);

		mSwitchSelectedCatalogNum.setText(String.format("%03d", selected.getIndexNumber()));
		mSwitchSelectedName.setText(selected.getName());
		mSwitchSelectedOwner.setText(selected.getOwner());
		mSwitchSelectedCurrXp.setText(selected.getStats().getExperienceProgression() + "");
		mSwitchSelectedNextXp.setText(selected.getStats().getRequiredExperience() + "");
		mSwitchSelectedImg.setImage(selected.getFrontSprite());

		mSwitchSelectedHp.setText(selected.getStats().getTotalStat(Stat.HitPoints) + "");
		mSwitchSelectedAtk.setText(selected.getStats().getTotalStat(Stat.Attack) + "");
		mSwitchSelectedSpAtk.setText(selected.getStats().getTotalStat(Stat.SpecialAttack) + "");
		mSwitchSelectedDef.setText(selected.getStats().getTotalStat(Stat.Defense) + "");
		mSwitchSelectedSpDef.setText(selected.getStats().getTotalStat(Stat.SpecialDefense) + "");
		mSwitchSelectedSpeed.setText(selected.getStats().getTotalStat(Stat.Speed) + "");

		mSwitchSelectedAbilityName.setText(selected.getAbility().toString());
		mSwitchSelectedAbilityDesc.setText(selected.getAbility().getAbilityDescription());

		mSwitchSelectedTypeOne.setImage(getTypeIcon(selected.getPrimaryType()));

		if(selected.getSecondaryType() != Type.NotSet)
		{
			mSwitchSelectedTypeTwo.setImage(getTypeIcon(selected.getSecondaryType()));
		}

		else
		{
			mSwitchSelectedTypeTwo.setImage(null);
		}
	}

	private Image getTypeIcon(Type type)
	{
		String path = getClass().getResource("/resources/images/types/" + type + "_Type.png").toExternalForm().replace("file:/", "");
		File toCheck = new File(path);

		if(!toCheck.exists())
		{
			path = getClass().getResource("/resources/images/types/Normal_Type.png").toExternalForm().replace("file:/", "");
			toCheck = new File(path);
		}

		return new Image(toCheck.toURI().toString(), 100.0, 100.0, true, false);
	}

	private void updateSwitchSlot(IAnature curr, Image anatureImg, BooleanProperty visibleProp, AnatureSlot slot, boolean isSelected)
	{
		visibleProp.set(true);
		slot.updateSlot(isSelected, anatureImg, curr.getGender(), curr.getName(), "Lvl " + curr.getStats().getLevel(),
				curr.getStats().getCurrentHitPoints() + "/" + curr.getStats().getTotalStat(Stat.HitPoints), mShowSwitch.get(), visibleProp.get(),
				curr.getStats().getCurrentHitPoints(), curr.getStats().getTotalStat(Stat.HitPoints), curr.getStatus());
	}

	private void updateBagMenu()
	{
		ObservableList<String> items = mItemList.getItems();
		items.clear();

		Backpack backpack = mPlayer.getBackpack();

		if(mShowPotionTab)
		{
			updatePotionMenu(items, backpack);
		}

		else
		{
			updateAnacubeMenu(items, backpack);
		}

		mItemList.getSelectionModel().select(0);
		onItemSelect();

		mItemList.setItems(items);
	}

	private void updatePotionMenu(ObservableList<String> items, Backpack backpack)
	{
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
	}

	private void updateAnacubeMenu(ObservableList<String> items, Backpack backpack)
	{
		int anacubeCount = backpack.getAnacubeCount(ItemIds.Anacube);
		int superAnacubeCount = backpack.getAnacubeCount(ItemIds.Super_Anacube);
		int hyperAnacubeCount = backpack.getAnacubeCount(ItemIds.Hyper_Anacube);
		int maxAnacubeCount = backpack.getAnacubeCount(ItemIds.Max_Anacube);

		if(anacubeCount > 0)
		{
			items.add("Anacubes " + anacubeCount + "x");
		}

		if(superAnacubeCount > 0)
		{
			items.add("Super Anacubes " + superAnacubeCount + "x");
		}

		if(hyperAnacubeCount > 0)
		{
			items.add("Hyper Anacubes " + hyperAnacubeCount + "x");
		}

		if(maxAnacubeCount > 0)
		{
			items.add("Max Anacubes " + maxAnacubeCount + "x");
		}
	}

	private void updateGender(IAnature anature, boolean isPlayer)
	{
		Image toUse = mMaleIcon;

		switch(anature.getGender())
		{
			case Female:
				toUse = mFemaleIcon;
				break;

			case Male:
				break;

			default:
				toUse = null;
		}

		if(isPlayer)
		{
			mPlayerGender.setImage(toUse);
		}

		else
		{
			mEnemyGender.setImage(toUse);
		}
	}

	private void onItemSelect()
	{
		String selectedItem = mItemList.getSelectionModel().getSelectedItem();

		if(selectedItem == null)
			return;

		if(mShowPotionTab)
		{
			updateItemSelectPotion(selectedItem);
		}

		else
		{
			updateItemSelectAnacube(selectedItem);
		}
	}

	private void updateItemSelectPotion(String selectedItem)
	{
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

	private void updateItemSelectAnacube(String selectedItem)
	{
		if(selectedItem.startsWith("Anacube"))
		{
			mSelectedItemImg.setImage(mItemAnacube);
			mSelectedItemTxt.set("Anacube");
		}

		else if(selectedItem.startsWith("Super"))
		{
			mSelectedItemImg.setImage(mItemSuperAnacube);
			mSelectedItemTxt.set("Super Anacube");
		}

		else if(selectedItem.startsWith("Hyper"))
		{
			mSelectedItemImg.setImage(mItemHyperAnacube);
			mSelectedItemTxt.set("Hyper Anacube");
		}

		else if(selectedItem.startsWith("Max"))
		{
			mSelectedItemImg.setImage(mItemMaxAnacube);
			mSelectedItemTxt.set("Max Anacube");
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
		if(canCapture())
		{
			mShowPotionTab = isPotion;
		}

		else
		{
			mShowPotionTab = true;
		}

		if(mShowPotionTab)
		{
			mItemPotionsTab.setImage(mItemTabSelected);
			mItemAnaCubeTab.setImage(mItemTabDeselected);
		}

		else
		{
			mItemPotionsTab.setImage(mItemTabDeselected);
			mItemAnaCubeTab.setImage(mItemTabSelected);
		}

		updateBagMenu();
	}

	private void activateTurn(BattleChoice choice)
	{
		IAnature anatureForSwitching = mPlayer.getAnatures().get(mSwitchIndexSelected);

		if(anatureForSwitching.getStats().getCurrentHitPoints() <= 0)
		{
			return;
		}

		mShowBtns.set(false);
		IAnature enemyCurr = mFightManager.getEnemyAnature();
		IAnature playerCurr = mFightManager.getPlayerAnature();

		AiChoiceObject<?> enemyTurn = mEnemyTrainer.useTurn(playerCurr);

		int whoGoesFirst = playerCurr.getStats().getTotalStat(Stat.Speed) - enemyCurr.getStats().getTotalStat(Stat.Speed);

		if(whoGoesFirst == 0)
		{
			whoGoesFirst += Math.random() > 0.5 ? -1 : 1;
		}

		if(choice == BattleChoice.Switch || choice == BattleChoice.Item)
		{
			whoGoesFirst = 1;
		}

		Runnable resetGui = new Runnable()
		{
			@Override
			public void run()
			{
				if(mClickQueue.size() != 0)
				{
					mClickQueue.enqueue(() -> resetGui(), "Reset GUI");
				}

				else
				{
					resetGui();
				}
			}
		};

		Runnable afterTurns = () ->
		{
			boolean activatedPlayer = false;
			boolean activatedEnemy = false;

			activatedPlayer = afterAllTurnsStatusCheck(true, mFightManager.getPlayerAnature(), null);
			activatedEnemy = afterAllTurnsStatusCheck(false, mFightManager.getEnemyAnature(), resetGui);

			if(!activatedPlayer && !activatedEnemy)
			{
				mCanClick.set(true);
			}

			if(!activatedEnemy)
			{
				resetGui.run();
			}
		};

		mCanClick.set(false);

		if(whoGoesFirst > 0) // Player goes first
		{
			activatePlayerTurn(mFightManager.getPlayerAnature(), mFightManager.getEnemyAnature(), choice,
					() -> activateEnemyTurn(mFightManager.getPlayerAnature(), mFightManager.getEnemyAnature(), enemyTurn, afterTurns));
		}

		else // Enemy goes first
		{
			activateEnemyTurn(mFightManager.getPlayerAnature(), mFightManager.getEnemyAnature(), enemyTurn,
					() -> activatePlayerTurn(mFightManager.getPlayerAnature(), mFightManager.getEnemyAnature(), choice, afterTurns));
		}

		onBackBtn();
		mShowBtns.set(false);
		mClickQueue.dequeue().run();
	}

	private void activatePlayerTurn(IAnature playerCurr, IAnature enemyCurr, BattleChoice choice, Runnable nextTurn)
	{
		if(beforeTurnStatusCheck(true, playerCurr) || choice == BattleChoice.Item || choice == BattleChoice.Switch)
		{
			playerTurn(choice, nextTurn);
		}

		else
		{
			mClickQueue.enqueue(nextTurn, "Next Turn");
		}
	}

	private void activateEnemyTurn(IAnature playerCurr, IAnature enemyCurr, AiChoiceObject<?> enemyTurn, Runnable nextTurn)
	{
		AiChoice aiChoice = enemyTurn.getAiChoice();
		if(beforeTurnStatusCheck(false, enemyCurr) || aiChoice == AiChoice.Item_Consumed || aiChoice == AiChoice.Switch_Anature)
		{
			enemyTurn(enemyTurn, nextTurn);
		}

		else
		{
			mClickQueue.enqueue(nextTurn, "Next Turn");
		}
	}

	private void activateAfterTurn(Runnable nextTurn)
	{
		mClickQueue.enqueue(() ->
		{
			updateMpCounts();

			afterTurnStatusCheck(true, mFightManager.getPlayerAnature());
			afterTurnStatusCheck(false, mFightManager.getEnemyAnature());

			nextTurn.run();

			try
			{
				Thread.sleep(100);
			}

			catch(InterruptedException e)
			{
				LoggerController.logEvent(LoggingTypes.Error, "Sleep after turn was interrupted.");
			}

			if(mClickQueue.size() != 0 && mClickQueue.upNextName().compareTo("Reset GUI") != 0)
			{
				mClickQueue.dequeue().run();
			}
		}, "After Turn Status Checks");
	}

	private void playerTurn(BattleChoice choice, Runnable nextTurn)
	{
		IAnature playerAnature = mFightManager.getPlayerAnature();

		switch(choice)
		{
			case Attack_1:
				mClickQueue.enqueue(() ->
				{
					useAttack(playerAnature, true, BattleChoice.Attack_1, 1);
					activateAfterTurn(nextTurn);

				}, "Player Attack 1");
				break;

			case Attack_2:
				mClickQueue.enqueue(() ->
				{
					useAttack(playerAnature, true, BattleChoice.Attack_2, 2);
					activateAfterTurn(nextTurn);

				}, "Player Attack 2");
				break;

			case Attack_3:
				mClickQueue.enqueue(() ->
				{
					useAttack(playerAnature, true, BattleChoice.Attack_3, 3);
					activateAfterTurn(nextTurn);

				}, "Player Attack 3");
				break;

			case Attack_4:
				mClickQueue.enqueue(() ->
				{
					useAttack(playerAnature, true, BattleChoice.Attack_4, 4);
					activateAfterTurn(nextTurn);

				}, "Player Attack 4");
				break;

			case Item: // TODO Change it so u can use items on other anatures
				String id = mItemList.getSelectionModel().getSelectedItem();

				IItem selectedItem = ItemPool.getItem(id);
				if(id.contains("Anacube"))
				{
					onAnacubeUse(selectedItem, nextTurn);
				}

				else
				{
					mClickQueue.enqueue(() ->
					{
						ItemResult result = mFightManager.itemUse(true, mPlayer.getSelectedIndex(), selectedItem);
						healthGain(result, mPlayerHp);

						mPlayer.getBackpack().removeItem(selectedItem.getItemId());
						updateBagMenu();
						activateAfterTurn(nextTurn);
					}, "Player Potion Use");
				}

				break;

			case Escape:
				enqueueDialogue("You clicked on Escape!\nThat has yet to be implemented!", "Player Escape");
				break;

			case Switch:
				activateSwitch(nextTurn);
				break;
		}
	}

	private void enemyTurn(AiChoiceObject<?> enemyTurn, Runnable nextTurn)
	{
		AiChoice aiChoice = enemyTurn.getAiChoice();
		switch(aiChoice)
		{
			case Move1:
				mClickQueue.enqueue(() ->
				{
					useAttack(mFightManager.getEnemyAnature(), false, BattleChoice.Attack_1, 1);
					activateAfterTurn(nextTurn);

				}, "Enemy Attack 1");
				break;

			case Move2:
				mClickQueue.enqueue(() ->
				{
					useAttack(mFightManager.getEnemyAnature(), false, BattleChoice.Attack_2, 2);
					activateAfterTurn(nextTurn);

				}, "Enemy Attack 2");
				break;

			case Move3:
				mClickQueue.enqueue(() ->
				{
					useAttack(mFightManager.getEnemyAnature(), false, BattleChoice.Attack_3, 3);
					activateAfterTurn(nextTurn);

				}, "Enemy Attack 3");
				break;

			case Move4:
				mClickQueue.enqueue(() ->
				{
					useAttack(mFightManager.getEnemyAnature(), false, BattleChoice.Attack_4, 4);
					activateAfterTurn(nextTurn);

				}, "Enemy Attack 4");
				break;

			case Item_Consumed:
				mClickQueue.enqueue(() ->
				{
					healthGain(mFightManager.itemUse(false, mFightManager.getEnemyIndex(), (HealthPotionBase) enemyTurn.getChoiceObject()), mEnemyHp);
					activateAfterTurn(nextTurn);
				}, "Enemy Item Use");
				break;

			case Switch_Anature:
				mClickQueue.enqueue(() ->
				{
					activateEnemySwitch(enemyTurn, nextTurn);
					activateAfterTurn(nextTurn);
				}, "Enemy Anature Switch");
				break;

			default:
				return;
		}
	}

	private void activateSwitch(Runnable nextTurn)
	{
		mClickQueue.enqueue(new Runnable()
		{
			@Override
			public void run()
			{
				mPlayerFaintSequenceActive = false;

				mFightManager.setPlayerSelectedIndex(mSwitchIndexSelected);
				IAnature oldAnature = mPlayer.getAnatures().get(mPlayer.getSelectedIndex());
				mPlayer.setSelectedIndex(mSwitchIndexSelected);
				IAnature newAnature = mPlayer.getAnatures().get(mPlayer.getSelectedIndex());

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
						fadeInNew.setOnFinished(actionEvent ->
						{
							AbilityResult entryResult = mFightManager.activateEntryAbility(true);

							for(String dialogue : entryResult.getDialogue())
							{
								enqueueDialogue(dialogue, "Player Entry Ability Dialogue");
							}

							if(nextTurn != null)
							{
								activateAfterTurn(nextTurn);
							}

							mCanClick.set(true);
						});
						fadeInNew.play();
					}
				});

				fadeOld.play();
				mDialogueTxt.set("Come on back " + oldAnature.getName() + ".");
			}
		}, "Activate Switch");
	}

	private void activateEnemySwitch(AiChoiceObject<?> enemyTurn, Runnable nextTurn)
	{
		mClickQueue.enqueue(new Runnable()
		{
			@Override
			public void run()
			{
				mPlayerFaintSequenceActive = false;

				IAnature oldAnature = mFightManager.getEnemyAnature();
				IAnature newAnature = (IAnature) enemyTurn.getChoiceObject();
				int newAnatureIndex = mEnemyTrainer.getAnatureIndex(newAnature);
				mFightManager.setEnemySelectedIndex(newAnatureIndex);

				OpacityAnimation fadeOld = new OpacityAnimation(mAnatureBack, Duration.millis(400), false);
				fadeOld.setOnFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						updateEnemyAnature(newAnature);

						try
						{
							Thread.sleep(500);
						}

						catch(InterruptedException e)
						{
							LoggerController.logEvent(LoggingTypes.Error, e.getMessage());
						}

						OpacityAnimation fadeInNew = new OpacityAnimation(mAnatureBack, Duration.millis(400), true);
						fadeInNew.setOnFinished(actionEvent ->
						{
							if(nextTurn != null)
							{
								activateAfterTurn(nextTurn);
							}

							mCanClick.set(true);
						});
						fadeInNew.play();
					}
				});

				fadeOld.play();
				mDialogueTxt.set(mEnemyTrainer.getName() + " calls back" + oldAnature.getName() + ".");
			}
		}, "Enemy Activate Switch");
	}

	private void activateEntryAbility(boolean isPlayer)
	{
		AbilityResult result = mFightManager.activateEntryAbility(isPlayer);

		for(String dialogue : result.getDialogue())
		{
			enqueueDialogue(dialogue, "Entry ability of player: " + isPlayer);
		}
	}

	private void playBattleAnimation(EventHandler<ActionEvent> event, boolean isPlayer, int moveIndex)
	{
		ImageView imgView = mFightAnimationEnemy;
		if(isPlayer)
		{
			imgView = mFightAnimationPlayer;
		}

		BattleAnimationType animationType = mFightManager.getPlayerAnature().getMoveAnimationType(moveIndex);

		Image icon1, icon2, icon3;
		switch(animationType)
		{
			case Physical:
				icon1 = mFistBottomLeftIcon;
				icon2 = mFistCenterIcon;
				icon3 = mFistTopRightIcon;
				break;

			case Special:
				icon1 = mSpecialAttackBottomLeftIcon;
				icon2 = mSpecialAttackCenterIcon;
				icon3 = mSpecialAttackTopRightIcon;
				break;

			case Raise_Stat:
				icon1 = mSpecialAttackRaiseBottomLeftIcon;
				icon2 = mSpecialAttackRaiseCenterIcon;
				icon3 = mSpecialAttackRaiseTopRightIcon;
				break;

			case Lower_Stat:
				icon1 = mSpecialAttackLowerBottomLeftIcon;
				icon2 = mSpecialAttackLowerCenterIcon;
				icon3 = mSpecialAttackLowerTopRightIcon;

			default:
				icon1 = mFistBottomLeftIcon;
				icon2 = mFistCenterIcon;
				icon3 = mFistTopRightIcon;
				break;
		}

		ThreeFrameAnimation animation = new ThreeFrameAnimation(imgView, Duration.seconds(1.5), icon1, icon2, icon3);
		animation.setOnFinished(event);

		animation.play();
	}

	private void useAttack(IAnature anature, boolean isPlayer, BattleChoice choice, int moveNum)
	{
		MoveResult moveResult = mFightManager.attack(isPlayer, moveNum);
		AbilityResult abilityResult = moveResult.getAbilityResult();
		ArrayList<String> moveDialogue = moveResult.getDialogue();
		IMove move = moveResult.getMove();

		try
		{
			Thread.sleep(10);
		}

		catch(InterruptedException e)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Thread was interrupted during sleep in useAttack.");
		}

		mDialogueTxt.set(moveDialogue.get(0));

		EventHandler<ActionEvent> visualizeAttack = actionEvent ->
		{
			if(!move.doesDamage())
			{
				mCanClick.set(true);
			}

			for(String abilityDialogue : abilityResult.getDialogue())
			{
				enqueueDialogue(abilityDialogue, "Ability Dialogue");
			}

			if(move.doesDamage())
			{
				healthDrainMove(moveResult, !isPlayer);
			}
		};

		if(!moveDialogue.get(0).contains("missed"))
		{
			playBattleAnimation(visualizeAttack, isPlayer, moveNum);
		}

		else
		{
			visualizeAttack.handle(null);
		}
	}

	private void healthDrainMove(MoveResult result, boolean playerWasTarget)
	{
		IAnature userAnature = null, targetAnature = null;
		DoubleProperty userOldHp, targetOldHp;

		if(!result.isPlayer())
		{
			targetAnature = mFightManager.getPlayerAnature();
			userAnature = mFightManager.getEnemyAnature();

			userOldHp = mEnemyHp;
			targetOldHp = mPlayerHp;
		}

		else
		{
			targetAnature = mFightManager.getEnemyAnature();
			userAnature = mFightManager.getPlayerAnature();

			userOldHp = mPlayerHp;
			targetOldHp = mEnemyHp;
		}

		double damageDoneToTarget = targetOldHp.get() - targetAnature.getStats().getCurrentHitPoints();
		double damageDoneToUser = userOldHp.get() - userAnature.getStats().getCurrentHitPoints();

		if(result.isPlayer() && damageDoneToTarget > mEnemyHp.get())
		{
			damageDoneToTarget = mEnemyHp.get();
		}

		else if(!result.isPlayer() && damageDoneToTarget > mPlayerHp.get())
		{
			damageDoneToTarget = mPlayerHp.get();
		}

		if(damageDoneToTarget != 0)
		{
			ProgressBarDecrease decreaseTargetHp = new ProgressBarDecrease(targetOldHp, Duration.millis(3000), damageDoneToTarget);
			decreaseTargetHp.setOnFinished(event ->
			{
				if(damageDoneToUser > 0)
				{
					ProgressBarDecrease decreaseUserHp = new ProgressBarDecrease(userOldHp, Duration.millis(3000), damageDoneToUser);
					decreaseUserHp.setOnFinished(userEvent -> mCanClick.set(true));
					decreaseUserHp.play();
				}

				else
				{
					mCanClick.set(true);
				}
			});
			decreaseTargetHp.play();
		}

		else
		{
			mCanClick.set(true);
		}
	}

	private void healthDrainStatus(String statusDialogue, double damageDone, boolean isPlayer, Runnable nextTurn)
	{
		mDialogueTxt.set(statusDialogue);

		DoubleProperty toDamage = null;
		if(isPlayer)
		{
			toDamage = mPlayerHp;
		}

		else
		{
			toDamage = mEnemyHp;
		}

		if(toDamage.get() - damageDone < 0)
		{
			damageDone = toDamage.get();
		}

		ProgressBarDecrease decrease = new ProgressBarDecrease(toDamage, Duration.millis(3000), damageDone);

		if(nextTurn == null)
		{
			decrease.setOnFinished(event -> mCanClick.set(true));
		}

		else
		{
			decrease.setOnFinished(event ->
			{
				mClickQueue.enqueue(nextTurn, "Health Drain Next Turn");
				mCanClick.set(true);
			});
		}

		decrease.play();
	}

	private void healthGain(ItemResult result, DoubleProperty toChange)
	{
		double duration = 2000;

		if(result.getHpGained() < 5)
			duration = 100;

		mDialogueTxt.set(result.getDialogue().get(0));
		ProgressBarIncrease increase = new ProgressBarIncrease(toChange, Duration.millis(duration), result.getHpGained());
		increase.setOnFinished(event -> mCanClick.set(true));
		increase.play();
	}

	private void onAnacubeUse(IItem selectedItem, Runnable nextTurn)
	{
		mClickQueue.enqueue(() ->
		{
			ItemResult result = mFightManager.itemUse(false, 0, selectedItem);
			AnacubeResults catchResult = result.getCatchResults();

			showAnacube(selectedItem);

			mPlayer.getBackpack().removeItem(selectedItem.getItemId());
			updateBagMenu();

			AnacubeThrow animation = new AnacubeThrow(mThrownAnacubeImg, Duration.seconds(2));
			animation.setOnFinished(value ->
			{
				mAnatureFront.setVisible(false);

				int bounceCount = 0;
				switch(catchResult)
				{
					case One_Bounce:
						bounceCount = 1;
						break;

					case Two_Bounce:
						bounceCount = 2;
						break;

					case Three_Bounce:
					case Catch:
						bounceCount = 3;
						break;

					default:
						break;
				}

				ImageViewBounce bounceAnaimation = new ImageViewBounce(mThrownAnacubeImg, Duration.seconds(0.75), bounceCount);
				bounceAnaimation.setOnFinished(value2 ->
				{
					mDialogueTxt.set(result.getDialogue().get(0));

					if(catchResult == AnacubeResults.Catch)
					{
						ColorAdjust darken = new ColorAdjust();
						darken.setBrightness(-0.75);
						mThrownAnacubeImg.setEffect(darken);

						playerWin(true);
					}

					else
					{
						mThrownAnacubeImg.setVisible(false);
						mAnatureFront.setVisible(true);
						activateAfterTurn(nextTurn);
					}

					mCanClick.set(true);
				});
				bounceAnaimation.play();
			});

			animation.play();
		}, "Player using Anacube");
	}

	private boolean canCapture()
	{
		return mEnemyTrainer.getId() == TrainerIds.Wild && mPlayer.getAnatures().size() < 6;
	}

	private void showAnacube(IItem selectedItem)
	{
		mThrownAnacubeImg.setVisible(true);

		switch(selectedItem.getItemId())
		{
			case Super_Anacube:
				mThrownAnacubeImg.setImage(mItemSuperAnacube);
				break;

			case Hyper_Anacube:
				mThrownAnacubeImg.setImage(mItemHyperAnacube);
				break;

			case Max_Anacube:
				mThrownAnacubeImg.setImage(mItemMaxAnacube);
				break;

			default:
				mThrownAnacubeImg.setImage(mItemAnacube);
				break;
		}
	}

	private void onSwitchBtn()
	{
		updateSwitch(mPlayer.getAnatures(), mSwitchIndexSelected);
		mShowSwitch.set(true);
		mShowBtns.set(false);
		mShowPlayerBars.set(false);
		mShowSwitchBackBtn.set(true);
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

	private void resetGui()
	{
		mShowBtns.set(true);
		mDialogueTxt.set("What will you do?");
		mCanClick.set(false);
	}

	private void enqueueDialogue(String dialogue, String id)
	{
		mClickQueue.enqueue(() ->
		{
			mDialogueTxt.set(dialogue);
			mCanClick.set(true);
		}, id);
	}

	private boolean beforeTurnStatusCheck(boolean isPlayer, IAnature anature)
	{
		StatusEffects anatureStatus = anature.getStatus();
		boolean canAttack = true;

		switch(anatureStatus)
		{
			case Burn:
				break;

			case Paralysis:
				enqueueDialogue(anature.getName() + " is paralysed! It may not be able to move!", "Paralysis Before Turn");

				canAttack = Math.random() <= 0.25;

				if(!canAttack)
				{
					enqueueDialogue(anature.getName() + " could not attack because of the paralysis!", "Paralysis Before Turn - Can't Attack");
				}
				break;

			case Sleep:
				boolean wakeUp = Math.random() <= 0.1;

				if(wakeUp)
				{
					enqueueDialogue(anature.getName() + " woke up!", "Sleep Before Turn - Woke Up");
				}

				else
				{
					enqueueDialogue(anature.getName() + " is fast asleep!", "Sleep Before Turn");
					canAttack = false;
				}
				break;

			default:
				break;
		}

		return canAttack;
	}

	private void afterTurnStatusCheck(boolean isPlayer, IAnature anature)
	{
		StatusEffects anatureStatus = anature.getStatus();
		boolean wasChanged = false;

		if(isPlayer)
		{
			wasChanged = updateStatusIcon(mStatusIconPlayer, anature);
		}

		else
		{
			wasChanged = updateStatusIcon(mStatusIconEnemy, anature);
		}

		if(wasChanged)
		{
			switch(anatureStatus)
			{
				case Burn:
					enqueueDialogue(anature.getName() + " is burned!", "Burn After Turn");
					break;

				case Paralysis:
					enqueueDialogue(anature.getName() + " is now paralyzed!", "Paralysis After Turn");
					break;

				case Sleep:
					enqueueDialogue(anature.getName() + " fell asleep!", "Sleep After Turn");
					break;

				default:
					break;
			}
		}
	}

	private boolean afterAllTurnsStatusCheck(boolean isPlayer, IAnature anature, Runnable nextTurn)
	{
		StatusEffects anatureStatus = anature.getStatus();

		if(isPlayer)
		{
			updateStatusIcon(mStatusIconPlayer, anature);
		}

		else
		{
			updateStatusIcon(mStatusIconEnemy, anature);
		}

		switch(anatureStatus)
		{
			case Burn:
				mClickQueue.enqueue(() ->
				{
					healthDrainStatus(anature.getName() + " is hurt because it is burned!", anature.getStats().getTotalStat(Stat.HitPoints) / 16, isPlayer, nextTurn);
					mFightManager.applyDamage(isPlayer, 0, anature.getStats().getTotalStat(Stat.HitPoints) / 16);
				}, "Burn After All Turns");

				return true;

			case Paralysis:
				break;

			case Sleep:
				break;

			default:
				break;
		}

		return false;
	}

	private boolean updateStatusIcon(ImageView icon, IAnature toCheck)
	{
		StatusEffects anatureStatus = toCheck.getStatus();
		boolean wasChanged = false;

		switch(anatureStatus)
		{
			case Sleep:
				if(icon.getImage() == null || !icon.getImage().equals(mSleepStatusIcon))
				{
					icon.setImage(mSleepStatusIcon);
					wasChanged = true;
				}
				break;

			case Paralysis:
				if(icon.getImage() == null || !icon.getImage().equals(mParalyzedStatusIcon))
				{
					icon.setImage(mParalyzedStatusIcon);
					wasChanged = true;
				}
				break;

			case Burn:
				if(icon.getImage() == null || !icon.getImage().equals(mBurnStatusIcon))
				{
					icon.setImage(mBurnStatusIcon);
					wasChanged = true;
				}
				break;

			default:
				icon.setImage(null);
				break;
		}

		return wasChanged;
	}

	private void updateMpCounts()
	{
		IAnature playerAnature = mFightManager.getPlayerAnature();
		MoveSet moveSet = playerAnature.getMoveSet();

		if(moveSet.hasMove(1))
		{
			mAttackMpOneTxt.set(moveSet.getMovePoints(1) + " / " + moveSet.getMove(1).getTotalMovePoints());
		}

		if(moveSet.hasMove(2))
		{
			mAttackMpTwoTxt.set(moveSet.getMovePoints(2) + " / " + moveSet.getMove(2).getTotalMovePoints());
		}

		if(moveSet.hasMove(3))
		{
			mAttackMpThreeTxt.set(moveSet.getMovePoints(3) + " / " + moveSet.getMove(3).getTotalMovePoints());
		}

		if(moveSet.hasMove(4))
		{
			mAttackMpFourTxt.set(moveSet.getMovePoints(4) + " / " + moveSet.getMove(4).getTotalMovePoints());
		}
	}

	private void dequeueClickTracker(Event event)
	{
		event.consume();

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

				if(mPlayerHp.get() <= 0 && !mPlayerFaintSequenceActive)
				{
					onPlayerAnatureDeath();
				}

				else if(mEnemyHp.get() == 0)
				{
					onEnemyAnatureDeath();
				}

				else
					toRun.run();
			}
		}
	}

	private void addEvs()
	{
		ArrayList<IAnature> participatingAnatures = mFightManager.getPlayerParticipantingAnatures();
		Stat evToAddTo = mFightManager.getEnemyAnature().getStats().getLargestStat();

		for(IAnature anature : participatingAnatures)
		{
			anature.getStats().addEv(evToAddTo, anature.getStats().getLevel());
		}
	}

	private void playerWin(boolean enemyWasCaught)
	{
		if(!enemyWasCaught)
		{
			mDialogueTxt.set(mFightManager.getEnemyTeam().get(0).getName() + " has been defeated!");
		}

		else if(mPlayer.getAnatures().size() < 6)
		{
			mPlayer.getAnatures().add(mFightManager.getEnemyAnature());
		}

		mClickQueue.clear();
		evaluateAnatureExperienceGain();

		if(!mEnemyTrainer.getId().equals(TrainerIds.Wild))
		{
			int randomCalculation = new Random().nextInt(21) - 10;
			double percentonvertion = (randomCalculation) / 100.0;
			double adjustmentPercent = 1.0 + percentonvertion;
			int tokensToAdd = (int) ((mEnemyTrainer.getRewardForDefeat()) * adjustmentPercent);

			mPlayer.addTokens(tokensToAdd);

			enqueueDialogue("You earned " + tokensToAdd + " tokens!", "Earning tokens");
			enqueueDialogue("You have defeated " + mEnemyTrainer.getName() + "!", "Enemy Dead");
		}

		mShowBtns.set(false);

		mClickQueue.enqueue(() -> endBattle(BattleEndMethods.Victory), "To Overworld");

		mCanClick.set(true);
		mToEnd = true;
	}

	private void endBattle(BattleEndMethods endMethod)
	{
		HashMap<IAnature, Species> evolutions = new HashMap<IAnature, Species>();

		for(IAnature toCheck : mPlayer.getAnatures())
		{
			Species canEvolveInto = EvolutionManager.checkEvolution(toCheck);

			if(canEvolveInto != null)
			{
				evolutions.put(toCheck, canEvolveInto);
			}
		}

		Startup.endBattle(new BattleResult(endMethod, evolutions));
	}

	private Image getHighQualityImg(String url)
	{
		return new Image(url, 1000.0, 1000.0, false, false);
	}

	private ObjectProperty<Font> createFontProperty(int toDivideBy)
	{
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), toDivideBy);
		return new SimpleObjectProperty<Font>(font);
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