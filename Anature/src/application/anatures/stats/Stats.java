package application.anatures.stats;

import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.enums.stats.Natures.NatureStats;
import application.interfaces.stats.IStats;

class Stats extends StatsBase implements IStats
{
	private int mLevel;
	private int mExperiencePoints;
	private int mCurrentHitPoints;
	private LevelingSpeed mLevelingSpeed;
	private Natures mNature;

	Stats()
	{
		mLevel = 1;
		mExperiencePoints = 0;
		mCurrentHitPoints = 0;
		mLevelingSpeed = LevelingSpeed.NotSet;
		mNature = Natures.NotSet;
	}

	/*
	 * PACKAGE SETS
	 */

	void setLevel(int level)
	{
		if(level < 0)
		{
			throw new IllegalArgumentException("Passed value \"level\" was below 0.");
		}

		mLevel = level;
	}

	void setLevelingSpeed(LevelingSpeed levelingSpeed)
	{
		if(levelingSpeed == null)
		{
			throw new IllegalArgumentException("Passed value\"levelingSpeed\" was null.");
		}

		if(levelingSpeed.equals(LevelingSpeed.NotSet))
		{
			throw new IllegalArgumentException("Passed value\"levelingSpeed\" was equal to " + levelingSpeed.toString() + ".");
		}

		mLevelingSpeed = levelingSpeed;
	}

	void setNature(Natures nature)
	{
		if(nature == null)
		{
			throw new IllegalArgumentException("Passed value\"nature\" was null.");
		}

		if(nature.equals(Natures.NotSet))
		{
			throw new IllegalArgumentException("Passed value\"nature\" was equal to " + nature.toString() + ".");
		}

		mNature = nature;
	}

	/*
	 * PUBLIC GETS
	 */

	public int getLevel()
	{
		return mLevel;
	}

	public int getExperiencePoints()
	{
		return mExperiencePoints;
	}

	public int getCurrentHitPoints()
	{
		return mCurrentHitPoints;
	}

	public LevelingSpeed getLevelingSpeed()
	{
		return mLevelingSpeed;
	}

	public Natures getNature()
	{
		return mNature;
	}

	public int getTotalHitPoints()
	{
		return getBaseHitPoints() + getIVHitPoints() + getEVHitPointsReduced() + getLevelHitPoints();
	}

	public int getTotalAttack()
	{
		if(mNature.getDecreasedStat()
				.equals(NatureStats.Attack))
		{
			int calculatedAttack = getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack();
			calculatedAttack = calculatedAttack + (int) (calculatedAttack * -mNature.getModifier());
			return calculatedAttack + getTempAttack();
		}

		else if(mNature.getIncreasedStat()
				.equals(NatureStats.Attack))
		{
			int calculatedAttack = getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack();
			calculatedAttack = calculatedAttack + (int) (calculatedAttack * mNature.getModifier());
			return calculatedAttack + getTempAttack();
		}

		return getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack() + getTempAttack();
	}

	public int getTotalDefense()
	{
		if(mNature.getDecreasedStat()
				.equals(NatureStats.Defense))
		{
			int calculatedDefense = getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense();
			calculatedDefense = calculatedDefense + (int) (calculatedDefense * -mNature.getModifier());
			return calculatedDefense + getTempDefense();
		}

		else if(mNature.getIncreasedStat()
				.equals(NatureStats.Defense))
		{
			int calculatedDefense = getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense();
			calculatedDefense = calculatedDefense + (int) (calculatedDefense * mNature.getModifier());
			return calculatedDefense + getTempDefense();
		}

		return getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense() + getTempDefense();
	}

	public int getTotalSpecialAttack()
	{
		if(mNature.getDecreasedStat()
				.equals(NatureStats.SpecialAttack))
		{
			int calculatedSpecialAttack = getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack();
			calculatedSpecialAttack = calculatedSpecialAttack + (int) (calculatedSpecialAttack * -mNature.getModifier());
			return calculatedSpecialAttack + getTempSpecialAttack();
		}

		else if(mNature.getIncreasedStat()
				.equals(NatureStats.SpecialAttack))
		{
			int calculatedSpecialAttack = getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack();
			calculatedSpecialAttack = calculatedSpecialAttack + (int) (calculatedSpecialAttack * mNature.getModifier());
			return calculatedSpecialAttack + getTempSpecialAttack();
		}

		return getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack() + getTempSpecialAttack();
	}

	public int getTotalSpecialDefense()
	{
		if(mNature.getDecreasedStat()
				.equals(NatureStats.SpecialDefense))
		{
			int calculatedSpecialDefense = getBaseSpecialDefense() + getIVSpecialDefnese() + getEVSpecialDefenseReduced() + getLevelSpecialDefense();
			calculatedSpecialDefense = calculatedSpecialDefense + (int) (calculatedSpecialDefense * -mNature.getModifier());
			return calculatedSpecialDefense + getTempSpecialDefense();
		}

		else if(mNature.getIncreasedStat()
				.equals(NatureStats.SpecialDefense))
		{
			int calculatedSpecialDefense = getBaseSpecialDefense() + getIVSpecialDefnese() + getEVSpecialDefenseReduced() + getLevelSpecialDefense();
			calculatedSpecialDefense = calculatedSpecialDefense + (int) (calculatedSpecialDefense * mNature.getModifier());
			return calculatedSpecialDefense + getTempSpecialDefense();
		}

		return getBaseSpecialDefense() + getIVSpecialDefnese() + getEVSpecialDefenseReduced() + getLevelSpecialDefense() + getTempSpecialDefense();
	}

	public int getTotalSpeed()
	{
		if(mNature.getDecreasedStat()
				.equals(NatureStats.Speed))
		{
			int calculatedSpeed = getBaseSpeed() + getIVSpeed() + getEVSpeedReduced() + getLevelSpeed();
			calculatedSpeed = calculatedSpeed + (int) (calculatedSpeed * -mNature.getModifier());
			return calculatedSpeed + getTempSpeed();
		}

		else if(mNature.getIncreasedStat()
				.equals(NatureStats.Speed))
		{
			int calculatedSpeed = getBaseSpeed() + getIVSpeed() + getEVSpeedReduced() + getLevelSpeed();
			calculatedSpeed = calculatedSpeed + (int) (calculatedSpeed * -mNature.getModifier());
			return calculatedSpeed + getTempSpeed();
		}

		return getBaseSpeed() + getIVSpeed() + getEVSpeedReduced() + getLevelSpeed() + getTempSpeed();
	}

	public int getTotalAccuracy()
	{
		return getBaseAccuracy() + getTempAccuracy();
	}

	public int getTotalEvasion()
	{
		return getBaseEvasion() + getTempEvasion();
	}

	/*
	 * PUBLIC SETS
	 */

	public void setCurrentHitPoints(int hitPoints)
	{
		if(hitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"hitPoints\" was below 0.");
		}

		if(hitPoints > getTotalHitPoints())
		{
			throw new IllegalArgumentException("Passed value \"hitPoints\" was above Anature's total hit points.");
		}

		mCurrentHitPoints = hitPoints;
	}

	/*
	 * PUBLIC METHODS
	 */

	public void addExperience(int expeienceGain)
	{
		if(expeienceGain < 0)
		{
			throw new IllegalArgumentException("Passed value \"expeienceGain\" was below 0.");
		}

		mExperiencePoints += expeienceGain;
		while(levelGained())
			;
	}

	public void adjustAttack(double attackAdjustment)
	{
		if(attackAdjustment < -1 || attackAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(attackAdjustment > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (attackAdjustment * getTotalAttack());

		adjustTempAttack(adjustment);
	}

	public void adjustDefense(double adjustmentPercentage)
	{
		if(adjustmentPercentage < -1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(adjustmentPercentage > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (adjustmentPercentage * getTotalDefense());

		adjustTempDefense(adjustment);
	}

	public void adjustSpecialAttack(double adjustmentPercentage)
	{
		if(adjustmentPercentage < -1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(adjustmentPercentage > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (adjustmentPercentage * getTotalSpecialAttack());

		adjustTempSpecialAttack(adjustment);
	}

	public void adjustSpecialDefense(double adjustmentPercentage)
	{
		if(adjustmentPercentage < -1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(adjustmentPercentage > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (adjustmentPercentage * getTotalSpecialDefense());

		adjustTempSpecialDefense(adjustment);
	}

	public void adjustSpeed(double adjustmentPercentage)
	{
		if(adjustmentPercentage < -1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(adjustmentPercentage > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (adjustmentPercentage * getTotalSpeed());

		adjustTempSpeed(adjustment);
	}

	public void adjustAccuracy(double adjustmentPercentage)
	{
		if(adjustmentPercentage < -1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(adjustmentPercentage > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (adjustmentPercentage * getTotalAccuracy());

		adjustTempAccuracy(adjustment);
	}

	public void adjustEvasion(double adjustmentPercentage)
	{
		if(adjustmentPercentage < -1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was below -1.");
		}

		if(adjustmentPercentage > 1)
		{
			throw new IllegalArgumentException("Passed value \"adjustmentPercentage\" was above 1.");
		}

		int adjustment = (int) (adjustmentPercentage * getTotalEvasion());

		adjustTempEvaion(adjustment);
	}

	/*
	 * PACKAGE METHODS
	 */

	void levelUpStats()
	{
		addExperience(requiredExperienceForLevel(getLevel()));
	}

	boolean canCreateStats()
	{
		if(getLevelingSpeed().equals(LevelingSpeed.NotSet))
		{
			throw new IllegalStateException("The \"levelingSpeed\" variable was never set during construction.");
		}

		return canCreateStatsBase() && canCreateStats();
	}

	/*
	 * PRIVATE METHODS
	 */

	private int requiredExperienceForLevel(int level)
	{
		double levelingSpeedModifier = mLevelingSpeed.getModifier();
		return (int) (levelingSpeedModifier * Math.pow(level, 3));
	}

	private boolean levelGained()
	{
		int nextLevel = getLevel() + 1;
		int requiredExperienceForLevel = requiredExperienceForLevel(nextLevel);

		if(getExperiencePoints() >= requiredExperienceForLevel)
		{
			return incrementLevel();
		}
		return false;
	}

	private boolean incrementLevel()
	{
		mLevel = +1;

		int hitPointsIncrese = (int) ((0.01 * getBaseHitPoints()) + (0.02 * (getIVHitPoints() + getEVHitPoints())));
		addLevelHitPoints(hitPointsIncrese);

		int attackIncrese = (int) ((0.01 * getBaseAttack()) + (0.02 * (getIVAttack() + getEVAttack())));
		addLevelAttack(attackIncrese);

		int defenseIncrese = (int) ((0.01 * getBaseDefense()) + (0.02 * (getIVDefense() + getEVDefense())));
		addLevelDefnse(defenseIncrese);

		int specialAttackIncrese = (int) ((0.01 * getBaseSpecialAttack()) + (0.02 * (getIVSpecialAttack() + getEVSpecialAttack())));
		addLevelSpecialAttack(specialAttackIncrese);

		int specialDefenseIncrese = (int) ((0.01 * getBaseSpecialDefense()) + (0.02 * (getIVSpecialDefnese() + getEVSpecialDefense())));
		addLevelSpecialDefense(specialDefenseIncrese);

		int speedIncrese = (int) ((0.01 * getBaseSpeed()) + (0.02 * (getIVSpeed() + getEVSpeed())));
		addLevelSpeed(speedIncrese);

		return true;
	}
}
