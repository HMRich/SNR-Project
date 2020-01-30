package application;

import application.enums.*;

public class PICKNAMEmon
{
	private String mName, mOwner;
	private int mLevel, mCurrentXp;
	private Gender mGender;
	private MoveSet mMoves;
	private Type mPrimaryType, mSecondaryType;
	private Species mSpecies;
	private boolean mIsShiny;
	private Ability mAbility;
	private int mAttack, mSpecialAttack, mDefense, mSpecialDefense, mHp;
	
	public MoveSet getMoves()
	{
		return mMoves;
	}
}
