package application.anatures.stats;

import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.enums.stats.Natures.NatureStats;
import application.interfaces.stats.IStats;

class Stats extends StatsBase implements IStats
{
	private int mLevel;
	private int mTotalExperiencePoints;
	private int mCurrentHitPoints;
	private LevelingSpeed mLevelingSpeed;
	private Natures mNature;

	Stats()
	{
		mLevel = 1;
		mTotalExperiencePoints = 0;
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

	public int getTotalExperiencePoints()
	{
		return mTotalExperiencePoints;
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
		if(getNature().getDecreasedStat()
				.equals(NatureStats.Attack))
		{
			int calculatedAttack = getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack();
			calculatedAttack = calculatedAttack + (int) (calculatedAttack * -getNature().getModifier());
			return calculatedAttack + getTempAttack();
		}

		else if(getNature().getIncreasedStat()
				.equals(NatureStats.Attack))
		{
			int calculatedAttack = getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack();
			calculatedAttack = calculatedAttack + (int) (calculatedAttack * getNature().getModifier());
			return calculatedAttack + getTempAttack();
		}

		return getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack() + getTempAttack();
	}

	public int getTotalDefense()
	{
		if(getNature().getDecreasedStat()
				.equals(NatureStats.Defense))
		{
			int calculatedDefense = getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense();
			calculatedDefense = calculatedDefense + (int) (calculatedDefense * -getNature().getModifier());
			return calculatedDefense + getTempDefense();
		}

		else if(getNature().getIncreasedStat()
				.equals(NatureStats.Defense))
		{
			int calculatedDefense = getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense();
			calculatedDefense = calculatedDefense + (int) (calculatedDefense * getNature().getModifier());
			return calculatedDefense + getTempDefense();
		}

		return getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense() + getTempDefense();
	}

	public int getTotalSpecialAttack()
	{
		if(getNature().getDecreasedStat()
				.equals(NatureStats.SpecialAttack))
		{
			int calculatedSpecialAttack = getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack();
			calculatedSpecialAttack = calculatedSpecialAttack + (int) (calculatedSpecialAttack * -getNature().getModifier());
			return calculatedSpecialAttack + getTempSpecialAttack();
		}

		else if(getNature().getIncreasedStat()
				.equals(NatureStats.SpecialAttack))
		{
			int calculatedSpecialAttack = getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack();
			calculatedSpecialAttack = calculatedSpecialAttack + (int) (calculatedSpecialAttack * getNature().getModifier());
			return calculatedSpecialAttack + getTempSpecialAttack();
		}

		return getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack() + getTempSpecialAttack();
	}

	public int getTotalSpecialDefense()
	{
		if(getNature().getDecreasedStat()
				.equals(NatureStats.SpecialDefense))
		{
			int calculatedSpecialDefense = getBaseSpecialDefense() + getIVSpecialDefnese() + getEVSpecialDefenseReduced() + getLevelSpecialDefense();
			calculatedSpecialDefense = calculatedSpecialDefense + (int) (calculatedSpecialDefense * -getNature().getModifier());
			return calculatedSpecialDefense + getTempSpecialDefense();
		}

		else if(getNature().getIncreasedStat()
				.equals(NatureStats.SpecialDefense))
		{
			int calculatedSpecialDefense = getBaseSpecialDefense() + getIVSpecialDefnese() + getEVSpecialDefenseReduced() + getLevelSpecialDefense();
			calculatedSpecialDefense = calculatedSpecialDefense + (int) (calculatedSpecialDefense * getNature().getModifier());
			return calculatedSpecialDefense + getTempSpecialDefense();
		}

		return getBaseSpecialDefense() + getIVSpecialDefnese() + getEVSpecialDefenseReduced() + getLevelSpecialDefense() + getTempSpecialDefense();
	}

	public int getTotalSpeed()
	{
		if(getNature().getDecreasedStat()
				.equals(NatureStats.Speed))
		{
			int calculatedSpeed = getBaseSpeed() + getIVSpeed() + getEVSpeedReduced() + getLevelSpeed();
			calculatedSpeed = calculatedSpeed + (int) (calculatedSpeed * -getNature().getModifier());
			return calculatedSpeed + getTempSpeed();
		}

		else if(getNature().getIncreasedStat()
				.equals(NatureStats.Speed))
		{
			int calculatedSpeed = getBaseSpeed() + getIVSpeed() + getEVSpeedReduced() + getLevelSpeed();
			calculatedSpeed = calculatedSpeed + (int) (calculatedSpeed * -getNature().getModifier());
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

		mTotalExperiencePoints += expeienceGain;
		while(levelGained())
			;
	}
	
	public int getExperienceProgression()
	{
		return getTotalExperiencePoints() - requiredExperienceForLevel(getLevel());
	}
	
	public int getRequiredExperience()
	{
		return requiredExperienceForLevel(getLevel() + 1) - requiredExperienceForLevel(getLevel());
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
		for(int statLevel = 0; statLevel != getLevel(); statLevel++)
		{
			levelStats();
		}
		addExperience(requiredExperienceForLevel(getLevel()));
	}

	boolean canCreateStats()
	{
		if(getLevelingSpeed().equals(LevelingSpeed.NotSet))
		{
			throw new IllegalStateException("The \"levelingSpeed\" variable was never set during construction.");
		}

		if(getNature().equals(Natures.NotSet))
		{
			throw new IllegalStateException("The \"nature\" variable was never set during construction.");
		}

		return canCreateStatsBase();
	}

	/*
	 * PRIVATE METHODS
	 */

	private int requiredExperienceForLevel(int level)
	{
		double levelingSpeedModifier = getLevelingSpeed().getModifier();
		return (int) (levelingSpeedModifier * Math.pow(level, 3));
	}

	private boolean levelGained()
	{
		int nextLevel = getLevel() + 1;
		int requiredExperienceForLevel = requiredExperienceForLevel(nextLevel);

		if(getTotalExperiencePoints() >= requiredExperienceForLevel)
		{
			setLevel(nextLevel);
			return levelStats();
		}

		return false;
	}

	private boolean levelStats()
	{
		int hitPointsIncrease = (int) ((0.01 * (double) getBaseHitPoints()) + (0.02 * ((double) getIVHitPoints() + (double) getEVHitPoints())));
		hitPointsIncrease = hitPointsIncrease == 0 ? 1 : hitPointsIncrease;
		addLevelHitPoints(hitPointsIncrease);

		int attackIncrease = (int) ((0.01 * (double) getBaseAttack()) + (0.02 * ((double) getIVAttack() + (double) getEVAttack())));
		attackIncrease = attackIncrease == 0 ? 1 : attackIncrease;
		addLevelAttack(attackIncrease);

		int defenseIncrease = (int) ((0.01 * (double) getBaseDefense()) + (0.02 * ((double) getIVDefense() + (double) getEVDefense())));
		defenseIncrease = defenseIncrease == 0 ? 1 : defenseIncrease;
		addLevelDefnse(defenseIncrease);

		int specialAttackIncrease = (int) ((0.01 * (double) getBaseSpecialAttack()) + (0.02 * ((double) getIVSpecialAttack() + (double) getEVSpecialAttack())));
		specialAttackIncrease = specialAttackIncrease == 0 ? 1 : specialAttackIncrease;
		addLevelSpecialAttack(specialAttackIncrease);

		int specialDefenseIncrease = (int) ((0.01 * (double) getBaseSpecialDefense()) + (0.02 * ((double) getIVSpecialDefnese() + (double) getEVSpecialDefense())));
		specialDefenseIncrease = specialDefenseIncrease == 0 ? 1 : specialDefenseIncrease;
		addLevelSpecialDefense(specialDefenseIncrease);

		int speedIncrease = (int) ((0.01 * (double) getBaseSpeed()) + (0.02 * ((double) getIVSpeed() + (double) getEVSpeed())));
		speedIncrease = speedIncrease == 0 ? 1 : speedIncrease;
		addLevelSpeed(speedIncrease);

		return true;
	}
}
