package application;

import Enums.Abilities;
import Enums.Gender;
import Enums.Species;
import Enums.Type;

public class PICKNAMEmon
{
	private String mName, mOwner;
	private int mLevel, mCurrentXp;
	private Gender mGender;
	private MoveSet mMoves;
	private Type mPrimaryType, mSecondaryType;
	private Species mSpecies;
	private boolean mIsShiny;
	private Abilities mAbility;
	private int mAttack, mSpecialAttack, mDefense, mSpecialDefense, mHp;
	
	public MoveSet getMoves()
	{
		return mMoves;
	}
}
