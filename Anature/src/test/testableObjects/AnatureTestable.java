package test.testableObjects;

import application.anatures.AnatureClass;
import application.anatures.AnatureClass.AnatureExport;
import application.anatures.AnatureClass.AnatureVariables;
import application.enums.Gender;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import test.TestObjects;

public class AnatureTestable extends AnatureExport
{
	public AnatureTestable(AnatureVariables context)
	{
		AnatureClass.Classes.super(context);
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
		return TestObjects.getDefaultAnatureName();
	}

	@Override
	public String getOwner()
	{
		mGetNameWasCalled = true;
		return TestObjects.getDefaultOwnerName();
	}

	@Override
	public boolean isShiny()
	{
		mIsShinyWasCalled = true;
		return TestObjects.getDefaultShinyValue();
	}

	@Override
	public Species getSpecies()
	{
		mGetSpeciesWasCalled = true;
		return TestObjects.getDefaultSpecies();
	}

	@Override
	public Gender getGender()
	{
		mGetGenderWasCalled = true;
		return TestObjects.getDefaultGender();
	}

	@Override
	public Type getPrimaryType()
	{
		mGetPrimaryTypeWasCalled = true;
		return TestObjects.getDefaultPrimaryType();
	}

	@Override
	public Type getSecondaryType()
	{
		mGetSecondaryTypeWasCalled = true;
		return TestObjects.getDefaultSecondaryType();
	}

	@Override
	public IAbility getAbility()
	{
		mGetAbilityWasCalled = true;
		return TestObjects.getDefaultAbility();
	}

	@Override
	public StatusEffects getStatus()
	{
		mGetStatusWasCalled = true;
		return TestObjects.getDefaultStatusEffect();
	}

	@Override
	public int getIndexNumber()
	{
		mGetIndexNumberWasCalled = true;
		return TestObjects.getDefaultIndexNumber();
	}

	@Override
	public int getCatchRate()
	{
		mGetCatchRateWasCalled = true;
		return TestObjects.getDefaultCatchRate();
	}

	/*
	 * SETTER STUBS
	 */

	@Override
	public IAnature setName(String name)
	{
		mSetNameWasCalled = true;
		return this;
	}

	@Override
	public IAnature setStatus(StatusEffects statusEffect)
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
