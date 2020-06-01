package application.controllers;

import java.util.ArrayList;

import application.Startup;
import application.anatures.movesets.MoveSet;
import application.enums.LoggingTypes;
import application.enums.Type;
import application.interfaces.IAnature;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AnatureSummaryController
{
	@FXML ImageView mBg, mPageOneBtn, mPageTwoBtn, mPageThreeBtn;
	@FXML ImageView mReleaseBtn, mBackBtn;
	@FXML GridPane mAnatureTabGrid;

	@FXML Label mLvlTxt, mNameTxt;
	@FXML ImageView mGenderImg, mAnatureImg;

	@FXML GridPane mPageOneGrid;
	@FXML Label mTitleNameTxt, mTitleTypeTxt, mTitleOwnerTxt, mTitleXpTxt;
	@FXML Label mDetailNameTxt, mDetailsTypeBgTxt, mDetailOwnerTxt, mDetailsXpBgTxt;
	@FXML Text mDetailXpTxt;
	@FXML ImageView mDetailTypeOneImg, mDetailTypeTwoImg;
	@FXML ProgressBar mDetailXpBar;

	@FXML GridPane mPageTwoGrid;
	@FXML Label mTitleHpTxt, mTitleAtkTxt, mTitleDefTxt, mTitleSpecialAtkTxt, mTitleSpecialDefTxt, mTitleSpeedTxt, mTitleNatureTxt, mTitleAbilityTxt;
	@FXML Label mDetailKeyTxt, mDetailHpTxt, mDetailAtkTxt, mDetailDefTxt, mDetailSpecialAtkTxt, mDetailSpecialDefTxt;
	@FXML Label mDetailSpeedTxt, mDetailNatureTxt, mDetailAbilityNameTxt, mDetailAbilityDescTxt;

	@FXML GridPane mPageThreeGrid;
	@FXML Label mMoveNameOneTxt, mMoveNameTwoTxt, mMoveNameThreeTxt, mMoveNameFourTxt;
	@FXML ImageView mMoveTypeOneImg, mMoveTypeTwoImg, mMoveTypeThreeImg, mMoveTypeFourImg;
	@FXML Label mMoveMpOneTxt, mMoveMpTwoTxt, mMoveMpThreeTxt, mMoveMpFourTxt;
	@FXML Label mDetailsTxt;
	@FXML Label mTitleMoveNameTxt, mTitleMovePowerTxt, mTitleMoveAccTxt, mTitleMoveTypeTxt;
	@FXML Label mDetailMoveNameTxt, mDetailMovePowerTxt, mDetailMoveAccTxt, mDetailMoveTypeBgTxt;
	@FXML ImageView mDetailMoveTypeImg;

	private Image mPageOneImg, mPageOneSelectImg, mPageTwoImg, mPageTwoSelectImg, mPageThreeImg, mPageThreeSelectImg;
	private Image mReleaseImg, mReleaseSelectImg, mBackImg, mBackSelectImg;
	private Image mMaleImg, mFemaleImg;
	private int mCurrPageNum, mSelectedMoveIndex;
	private ObjectProperty<Font> mMediumFontProperty;
	private Scene mScene;
	private ArrayList<IAnature> mParty;
	private IAnature mCurrAnature;

	public void initialize()
	{
		String path = "/resources/images/menus/summary/";
		mPageOneImg = new Image(getClass().getResource(path + "Anature_Summary_Page_1.png").toExternalForm());
		mPageOneSelectImg = new Image(getClass().getResource(path + "Anature_Summary_Page_1_Selected.png").toExternalForm());

		mPageTwoImg = new Image(getClass().getResource(path + "Anature_Summary_Page_2.png").toExternalForm());
		mPageTwoSelectImg = new Image(getClass().getResource(path + "Anature_Summary_Page_2_Selected.png").toExternalForm());

		mPageThreeImg = new Image(getClass().getResource(path + "Anature_Summary_Page_3.png").toExternalForm());
		mPageThreeSelectImg = new Image(getClass().getResource(path + "Anature_Summary_Page_3_Selected.png").toExternalForm());

		mCurrPageNum = 1;
		mSelectedMoveIndex = 1;

		mReleaseImg = new Image(getClass().getResource(path + "Anature_Summary_Release_Btn.png").toExternalForm());
		mReleaseSelectImg = new Image(getClass().getResource(path + "Anature_Summary_Release_Btn_Selected.png").toExternalForm());

		mBackImg = new Image(getClass().getResource(path + "Anature_Summary_Back_Btn.png").toExternalForm());
		mBackSelectImg = new Image(getClass().getResource(path + "Anature_Summary_Back_Btn_Selected.png").toExternalForm());

		mMaleImg = new Image(getClass().getResource("/resources/images/battle/Male_Symbol.png").toExternalForm());
		mFemaleImg = new Image(getClass().getResource("/resources/images/battle/Female_Symbol.png").toExternalForm());
	}

	public void displayParty(ArrayList<IAnature> party)
	{
		if(party == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried to display a null party in Anature Summary.");
			return;
		}

		mAnatureTabGrid.getChildren().clear();
		for(int i = 0; i < party.size(); i++)
		{
			setUpTabs(i, party.get(i));
		}

		mParty = party;
		mCurrAnature = mParty.get(0);
		displayAnature(mCurrAnature);
	}

	private void displayAnature(IAnature anature)
	{
		if(anature == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried to display a null anature in Anature Summary.");
			return;
		}

		hideAllPages();
		mCurrPageNum = 1;
		mPageOneBtn.setImage(mPageOneSelectImg);
		mPageOneGrid.setVisible(true);

		mCurrAnature = anature;
		IStats stats = anature.getStats();
		displayLeftSideInfo(anature, stats);
		displayPageOneInfo(anature, stats);
		displayPageTwoInfo(anature, stats);
		displayPageThreeInfo(anature, stats);
	}

	private void displayLeftSideInfo(IAnature anature, IStats stats)
	{
		mLvlTxt.setText("Lvl. " + stats.getLevel());
		mNameTxt.setText(anature.getName());

		switch(anature.getGender())
		{
			case Female:
				mGenderImg.setImage(mFemaleImg);
				break;

			default:
				mGenderImg.setImage(mMaleImg);
				break;
		}
	}

	private void displayPageOneInfo(IAnature anature, IStats stats)
	{
		mDetailNameTxt.setText("   " + anature.getName());
		mDetailTypeOneImg.setImage(new Image(getClass().getResource("/resources/images/types/" + anature.getPrimaryType() + "_Type.png").toExternalForm()));

		if(anature.getSecondaryType() != Type.NotSet)
		{
			mDetailTypeTwoImg
					.setImage(new Image(getClass().getResource("/resources/images/types/" + anature.getSecondaryType() + "_Type.png").toExternalForm()));
		}

		mDetailOwnerTxt.setText("   " + anature.getOwner());

		mDetailXpBar.setProgress(stats.getExperienceProgression() / (stats.getRequiredExperience() * 1.0));
		mDetailXpTxt.setText(stats.getExperienceProgression() + " / " + stats.getRequiredExperience());
	}

	private void displayPageTwoInfo(IAnature anature, IStats stats)
	{
		String hp = String.format("   %s + %s + %s + %s = %s", stats.getBaseHitPoints(), stats.getIVHitPoints(), stats.getEVHitPoints(),
				stats.getLevelHitPoints(), stats.getTotalHitPoints());

		String atk = String.format("   %s + %s + %s + %s = %s", stats.getBaseAttack(), stats.getIVAttack(), stats.getEVAttack(), stats.getLevelAttack(),
				stats.getTotalAttack());

		String def = String.format("   %s + %s + %s + %s = %s", stats.getBaseDefense(), stats.getIVDefense(), stats.getEVDefense(), stats.getLevelDefense(),
				stats.getTotalDefense());

		String specAtk = String.format("   %s + %s + %s + %s = %s", stats.getBaseSpecialAttack(), stats.getIVSpecialAttack(), stats.getEVSpecialAttack(),
				stats.getLevelSpecialAttack(), stats.getTotalSpecialAttack());

		String specDef = String.format("   %s + %s + %s + %s = %s", stats.getBaseSpecialDefense(), stats.getIVSpecialDefense(), stats.getEVSpecialDefense(),
				stats.getLevelSpecialDefense(), stats.getTotalSpecialDefense());

		String speed = String.format("   %s + %s + %s + %s = %s", stats.getBaseSpeed(), stats.getIVSpeed(), stats.getEVSpeed(), stats.getLevelSpeed(),
				stats.getTotalSpeed());

		mDetailHpTxt.setText(hp);
		mDetailAtkTxt.setText(atk);
		mDetailDefTxt.setText(def);
		mDetailSpecialAtkTxt.setText(specAtk);
		mDetailSpecialDefTxt.setText(specDef);
		mDetailSpeedTxt.setText(speed);
		mDetailNatureTxt.setText("   " + stats.getNature().toString());
		mDetailAbilityNameTxt.setText("   " + anature.getAbility().toString());
		mDetailAbilityDescTxt.setText("   " + anature.getAbility().getAbilityDescription());
	}

	private void displayPageThreeInfo(IAnature anature, IStats stats)
	{
		MoveSet moveset = anature.getMoveSet();
		displayMoveRow(moveset.getMove(1), moveset, mMoveNameOneTxt, mMoveTypeOneImg, mMoveMpOneTxt, 1);
		displayMoveRow(moveset.getMove(2), moveset, mMoveNameTwoTxt, mMoveTypeTwoImg, mMoveMpTwoTxt, 2);
		displayMoveRow(moveset.getMove(3), moveset, mMoveNameThreeTxt, mMoveTypeThreeImg, mMoveMpThreeTxt, 3);
		displayMoveRow(moveset.getMove(4), moveset, mMoveNameFourTxt, mMoveTypeFourImg, mMoveMpFourTxt, 4);

		mSelectedMoveIndex = 1;
		displayMoveDetails(moveset.getMove(mSelectedMoveIndex));
	}

	private void displayMoveRow(IMove move, MoveSet moveset, Label name, ImageView img, Label mp, int moveIndex)
	{
		name.setText("   " + move.getName());
		img.setImage(new Image(getClass().getResource("/resources/images/types/" + move.getType() + "_Type.png").toExternalForm()));
		mp.setText(moveset.getMovePoints(moveset.getMoveIndex(move)) + " / " + move.getTotalMovePoints());

		EventHandler<MouseEvent> event = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				displayMoveDetails(move);
				mSelectedMoveIndex = moveIndex;
			}
		};

		name.setOnMouseClicked(event);
		img.setOnMouseClicked(event);
		mp.setOnMouseClicked(event);
	}

	private void displayMoveDetails(IMove move)
	{
		mDetailMoveNameTxt.setText("   " + move.getName());
		mDetailMovePowerTxt.setText("   " + move.getMovePower());
		mDetailMoveAccTxt.setText("   " + move.getAccuracy() + "%");
		mDetailMoveTypeImg.setImage(new Image(getClass().getResource("/resources/images/types/" + move.getType() + "_Type.png").toExternalForm()));
	}

	private void releaseAnature()
	{
		if(mParty.size() > 1)
		{
			mParty.remove(mCurrAnature);
		}
		
		displayParty(mParty);
	}

	public void updateBinds(Scene scene)
	{
		if(scene == null)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Tried to bind to a null scene in Anature Summary.");
			return;
		}

		mScene = scene;
		mBg.fitWidthProperty().bind(mScene.widthProperty());
		mBg.fitHeightProperty().bind(mScene.heightProperty());

		setUpBackAndRelease();
		setUpPageBtns();

		int fontSize = 60;
		ObjectProperty<Font> largeFontProperty = createFontProperty(fontSize);

		fontSize = 80;
		mMediumFontProperty = createFontProperty(fontSize);

		fontSize = 95;
		ObjectProperty<Font> verySmallFontProperty = createFontProperty(fontSize);

		setUpTopBar();
		setUpLeftSide(largeFontProperty);
		setUpRightSide(largeFontProperty, verySmallFontProperty);
	}

	private void setUpBackAndRelease()
	{
		bindImage(mReleaseBtn, 188, 925, 237, 57);
		bindImage(mBackBtn, 835, 1004, 369, 76);

		mReleaseBtn.setOnMouseEntered(value -> mReleaseBtn.setImage(mReleaseSelectImg));
		mReleaseBtn.setOnMouseExited(value -> mReleaseBtn.setImage(mReleaseImg));
		mReleaseBtn.setOnMouseClicked(value -> releaseAnature());

		mBackBtn.setOnMouseEntered(value -> mBackBtn.setImage(mBackSelectImg));
		mBackBtn.setOnMouseExited(value -> mBackBtn.setImage(mBackImg));
		mBackBtn.setOnMouseClicked(value -> Startup.changeScene(null, null));
	}

	private void setUpPageBtns()
	{
		bindImage(mPageOneBtn, 1576, 222, 105, 85);
		bindImage(mPageTwoBtn, 1693, 222, 105, 85);
		bindImage(mPageThreeBtn, 1811, 222, 105, 85);

		setUpPageBtnEvents(mPageOneBtn, 1, mPageOneImg, mPageOneSelectImg, mPageOneGrid);
		setUpPageBtnEvents(mPageTwoBtn, 2, mPageTwoImg, mPageTwoSelectImg, mPageTwoGrid);
		setUpPageBtnEvents(mPageThreeBtn, 3, mPageThreeImg, mPageThreeSelectImg, mPageThreeGrid);
	}

	private void setUpPageBtnEvents(ImageView btn, int pageNum, Image normal, Image selected, GridPane grid)
	{
		btn.setOnMouseEntered(value -> btn.setImage(selected));
		btn.setOnMouseExited(value ->
		{
			if(mCurrPageNum != pageNum)
			{
				btn.setImage(normal);
			}
		});

		btn.setOnMouseClicked(value ->
		{
			mCurrPageNum = pageNum;
			hideAllPages();

			btn.setImage(selected);
			grid.setVisible(true);
		});
	}

	private void setUpTopBar()
	{
		mAnatureTabGrid.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 44)));
		mAnatureTabGrid.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 1821)));
		mAnatureTabGrid.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 186)));
	}

	@SuppressWarnings("static-access")
	private void setUpTabs(int col, IAnature anature)
	{
		ImageView bg = new ImageView(new Image(getClass().getResource("/resources/images/menus/summary/Anature_Summary_Upper_Tab.png").toExternalForm()));
		bg.setPreserveRatio(false);
		bg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 216)));
		bg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 167)));

		ImageView img = new ImageView(anature.getFrontSprite());
		img.setPreserveRatio(false);
		img.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 117)));
		img.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 126)));

		Label name = new Label(anature.getName());
		name.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 217)));
		name.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 33)));
		name.fontProperty().bind(mMediumFontProperty);
		name.setAlignment(Pos.CENTER);

		mAnatureTabGrid.add(bg, col, 0);
		mAnatureTabGrid.add(img, col, 0);
		mAnatureTabGrid.add(name, col, 0);

		mAnatureTabGrid.setValignment(bg, VPos.TOP);
		mAnatureTabGrid.setValignment(img, VPos.CENTER);
		mAnatureTabGrid.setValignment(name, VPos.TOP);

		mAnatureTabGrid.setHalignment(bg, HPos.CENTER);
		mAnatureTabGrid.setHalignment(img, HPos.CENTER);
		mAnatureTabGrid.setHalignment(name, HPos.CENTER);

		bg.setOnMouseClicked(event -> displayAnature(anature));
		img.setOnMouseClicked(event -> displayAnature(anature));
		name.setOnMouseClicked(event -> displayAnature(anature));
	}

	private void setUpLeftSide(ObjectProperty<Font> largeFontProperty)
	{
		bindImage(mGenderImg, 35, 339, 43, 44);
		bindImage(mAnatureImg, 116, 514, 386, 386);

		mLvlTxt.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 99)));
		mLvlTxt.layoutYProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 329)));
		mLvlTxt.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 168)));
		mLvlTxt.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 63)));

		mNameTxt.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 279)));
		mNameTxt.layoutYProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 329)));
		mNameTxt.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 307)));
		mNameTxt.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 63)));

		mLvlTxt.fontProperty().bind(largeFontProperty);
		mNameTxt.fontProperty().bind(largeFontProperty);
	}

	private void setUpRightSide(ObjectProperty<Font> largeFontProperty, ObjectProperty<Font> verySmallFontProperty)
	{
		setUpPageOne(largeFontProperty);
		setUpPageTwo(verySmallFontProperty);
		setUpPageThree(largeFontProperty, verySmallFontProperty);
	}

	private void setUpPageOne(ObjectProperty<Font> largeFontProperty)
	{
		mPageOneGrid.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 811)));
		mPageOneGrid.layoutYProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 382)));
		mPageOneGrid.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 886)));
		mPageOneGrid.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 274)));

		mTitleNameTxt.fontProperty().bind(largeFontProperty);
		mTitleTypeTxt.fontProperty().bind(largeFontProperty);
		mTitleOwnerTxt.fontProperty().bind(largeFontProperty);
		mTitleXpTxt.fontProperty().bind(largeFontProperty);

		mDetailNameTxt.fontProperty().bind(mMediumFontProperty);
		mDetailOwnerTxt.fontProperty().bind(mMediumFontProperty);
		mDetailXpTxt.fontProperty().bind(mMediumFontProperty);
		mDetailsTypeBgTxt.fontProperty().bind(mMediumFontProperty);
		mDetailsXpBgTxt.fontProperty().bind(mMediumFontProperty);

		mDetailTypeOneImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mDetailTypeOneImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

		mDetailTypeTwoImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mDetailTypeTwoImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

		mDetailXpBar.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 334)));
		mDetailXpBar.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 31)));
	}

	private void setUpPageTwo(ObjectProperty<Font> verySmallFontProperty)
	{
		int fontSize = 80;
		ObjectProperty<Font> largeFontProperty = createFontProperty(fontSize);

		mPageTwoGrid.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 811)));
		mPageTwoGrid.layoutYProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 335)));
		mPageTwoGrid.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 886)));
		mPageTwoGrid.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 631)));

		mTitleHpTxt.fontProperty().bind(largeFontProperty);
		mTitleAtkTxt.fontProperty().bind(largeFontProperty);
		mTitleDefTxt.fontProperty().bind(largeFontProperty);
		mTitleSpecialAtkTxt.fontProperty().bind(largeFontProperty);
		mTitleSpecialDefTxt.fontProperty().bind(largeFontProperty);
		mTitleSpeedTxt.fontProperty().bind(largeFontProperty);
		mTitleNatureTxt.fontProperty().bind(largeFontProperty);
		mTitleAbilityTxt.fontProperty().bind(largeFontProperty);

		mDetailKeyTxt.fontProperty().bind(verySmallFontProperty);
		mDetailHpTxt.fontProperty().bind(verySmallFontProperty);
		mDetailAtkTxt.fontProperty().bind(verySmallFontProperty);
		mDetailDefTxt.fontProperty().bind(verySmallFontProperty);
		mDetailSpecialAtkTxt.fontProperty().bind(verySmallFontProperty);
		mDetailSpecialDefTxt.fontProperty().bind(verySmallFontProperty);
		mDetailSpeedTxt.fontProperty().bind(verySmallFontProperty);
		mDetailNatureTxt.fontProperty().bind(verySmallFontProperty);
		mDetailAbilityNameTxt.fontProperty().bind(verySmallFontProperty);
		mDetailAbilityDescTxt.fontProperty().bind(verySmallFontProperty);
	}

	private void setUpPageThree(ObjectProperty<Font> largeFontProperty, ObjectProperty<Font> verySmallFontProperty)
	{
		mPageThreeGrid.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 811)));
		mPageThreeGrid.layoutYProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 344)));
		mPageThreeGrid.prefWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 886)));
		mPageThreeGrid.prefHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 626)));

		mMoveNameOneTxt.fontProperty().bind(largeFontProperty);
		mMoveNameTwoTxt.fontProperty().bind(largeFontProperty);
		mMoveNameThreeTxt.fontProperty().bind(largeFontProperty);
		mMoveNameFourTxt.fontProperty().bind(largeFontProperty);

		mMoveMpOneTxt.fontProperty().bind(verySmallFontProperty);
		mMoveMpTwoTxt.fontProperty().bind(verySmallFontProperty);
		mMoveMpThreeTxt.fontProperty().bind(verySmallFontProperty);
		mMoveMpFourTxt.fontProperty().bind(verySmallFontProperty);

		mDetailsTxt.fontProperty().bind(largeFontProperty);

		mTitleMoveNameTxt.fontProperty().bind(largeFontProperty);
		mTitleMovePowerTxt.fontProperty().bind(largeFontProperty);
		mTitleMoveAccTxt.fontProperty().bind(largeFontProperty);
		mTitleMoveTypeTxt.fontProperty().bind(largeFontProperty);

		mDetailMoveNameTxt.fontProperty().bind(largeFontProperty);
		mDetailMovePowerTxt.fontProperty().bind(largeFontProperty);
		mDetailMoveAccTxt.fontProperty().bind(largeFontProperty);
		mDetailMoveTypeBgTxt.fontProperty().bind(largeFontProperty);

		mDetailMoveTypeImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mDetailMoveTypeImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

		mMoveTypeOneImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mMoveTypeOneImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

		mMoveTypeTwoImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mMoveTypeTwoImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

		mMoveTypeThreeImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mMoveTypeThreeImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

		mMoveTypeFourImg.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, 200)));
		mMoveTypeFourImg.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, 150)));

	}

	// This method exists because you can't use (first / second) when binding properties.
	// The reason it needs to be like this is because the binding won't work with the full double value. It must be rounded / shortened.
	// Normally I would just divide them and hardcode the input, but that not only got tiresome, it made it hard to know why the values were what they were.
	// So I've done this instead.
	private double calculateValue(double first, double second)
	{
		String value = (first / second) + "";

		if(value.length() > 4)
		{
			value = value.substring(0, 4);
		}

		return Double.parseDouble(value);
	}

	private ObjectProperty<Font> createFontProperty(int fontSize)
	{
		Font font = Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), fontSize);
		ObjectProperty<Font> fontProperty = new SimpleObjectProperty<Font>(font);

		mScene.widthProperty().addListener((observableValue, oldWidth, newWidth) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / fontSize)));

		mScene.heightProperty().addListener((observableValue, oldHeight, newHeight) -> fontProperty
				.set(Font.loadFont(getClass().getResourceAsStream("/resources/font/pixelFJ8pt1__.TTF"), getFontSize() / fontSize)));

		return fontProperty;
	}

	private double getFontSize()
	{
		double value = mScene.getWidth();

		if(mScene.getHeight() < 464)
		{
			value = mScene.getHeight() / 0.45;
		}

		if(mScene.getWidth() >= 1940)
		{
			value = value - (mScene.getWidth() - 1940);
		}

		return value;
	}

	private void hideAllPages()
	{
		mPageOneBtn.setImage(mPageOneImg);
		mPageTwoBtn.setImage(mPageTwoImg);
		mPageThreeBtn.setImage(mPageThreeImg);

		mPageOneGrid.setVisible(false);
		mPageTwoGrid.setVisible(false);
		mPageThreeGrid.setVisible(false);
	}

	private void bindImage(ImageView toBind, double xToDivide, double yToDivide, double widthToDivide, double heightToDivide)
	{
		toBind.layoutXProperty().bind(mScene.widthProperty().divide(calculateValue(1920, xToDivide)));
		toBind.layoutYProperty().bind(mScene.heightProperty().divide(calculateValue(1080, yToDivide)));
		toBind.fitWidthProperty().bind(mScene.widthProperty().divide(calculateValue(1920, widthToDivide)));
		toBind.fitHeightProperty().bind(mScene.heightProperty().divide(calculateValue(1080, heightToDivide)));
	}
}
