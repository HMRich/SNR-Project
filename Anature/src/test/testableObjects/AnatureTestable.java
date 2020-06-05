package test.testableObjects;

import application.anatures.Anature;
import application.anatures.AnatureVariables;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;

public class AnatureTestable extends Anature
{
	public AnatureTestable(AnatureVariables context)
	{
		super(context);
	}

	private boolean mGetNameWasCalled;
	private boolean mGetOwnerWasCalled;
	private boolean mIsShinyWasCalled;
	private boolean mGetSpeciesWasCalled;
	private boolean mGetGenderWasCalled;
	private boolean mGetPrimaryTypeWasCalled;
	private boolean mGetSecondaryTypeWasCalled;
	private boolean mGetAbilityWasCalled;
	private boolean mGetStatusWasCalled;
	private boolean mGetIndexNumberWasCalled;
	private boolean mGetCatchRateWasCalled;

	private boolean mSetNameWasCalled;
	private boolean mSetStatusWasCalled;

	/*
	 * GETTER STUBS
	 */

	@Override
	public String getName()
	{
		mGetNameWasCalled = true;
		return super.getName();
	}

	@Override
	public String getOwner()
	{
		mGetNameWasCalled = true;
		return super.getOwner();
	}

	@Override
	public boolean isShiny()
	{
		mIsShinyWasCalled = true;
		return super.isShiny();
	}

	@Override
	public Species getSpecies()
	{
		mGetSpeciesWasCalled = true;
		return super.getSpecies();
	}

	@Override
	public Gender getGender()
	{
		mGetGenderWasCalled = true;
		return super.getGender();
	}

	@Override
	public Type getPrimaryType()
	{
		mGetPrimaryTypeWasCalled = true;
		return super.getPrimaryType();
	}

	@Override
	public Type getSecondaryType()
	{
		mGetSecondaryTypeWasCalled = true;
		return super.getSecondaryType();
	}

	@Override
	public IAbility getAbility()
	{
		mGetAbilityWasCalled = true;
		return super.getAbility();
	}

	@Override
	public StatusEffects getStatus()
	{
		mGetStatusWasCalled = true;
		return super.getStatus();
	}

	@Override
	public int getIndexNumber()
	{
		mGetIndexNumberWasCalled = true;
		return super.getIndexNumber();
	}

	@Override
	public int getCatchRate()
	{
		mGetCatchRateWasCalled = true;
		return super.getCatchRate();
	}

	/*
	 * SETTER STUBS
	 */

	@Override
	public Anature setName(String name)
	{
		mSetNameWasCalled = true;
		return this;
	}

	@Override
	public Anature setStatus(StatusEffects statusEffect)
	{
		mSetStatusWasCalled = true;
		return this;
	}

	/*
	 * TESTING METHODS
	 */

	public boolean getNameWasCalled()
	{
		return mGetNameWasCalled;
	}

	public boolean getOwnerWasCalled()
	{
		return mGetOwnerWasCalled;
	}

	public boolean isShinyWasCalled()
	{
		return mIsShinyWasCalled;
	}

	public boolean getSpeciesWasCalled()
	{
		return mGetSpeciesWasCalled;
	}

	public boolean getGenderWasCaled()
	{
		return mGetGenderWasCalled;
	}

	public boolean getPrimaryTypeWasCalled()
	{
		return mGetPrimaryTypeWasCalled;
	}

	public boolean getSecondaryTypeWasCalled()
	{
		return mGetSecondaryTypeWasCalled;
	}

	public boolean getAbilityWasCalled()
	{
		return mGetAbilityWasCalled;
	}

	public boolean getStatusWasCalled()
	{
		return mGetStatusWasCalled;
	}

	public boolean getIndexNumberWasCalled()
	{
		return mGetIndexNumberWasCalled;
	}

	public boolean getCatchRateWasCalled()
	{
		return mGetCatchRateWasCalled;
	}

	public boolean setNameWasCalled()
	{
		return mSetNameWasCalled;
	}

	public boolean setStatusWasCalled()
	{
		return mSetStatusWasCalled;
	}
}
