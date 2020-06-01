package application.anatures.stats;

import application.enums.Stat;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
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
		return getTotalStat(Stat.HitPoints);
	}

	public int getTotalAttack()
	{
		return getTotalStat(Stat.Attack);
	}

	public int getTotalDefense()
	{
		return getTotalStat(Stat.Defense);
	}

	public int getTotalSpecialAttack()
	{
		return getTotalStat(Stat.Attack);
	}

	public int getTotalSpecialDefense()
	{
		return getTotalStat(Stat.SpecialDefense);
	}

	public int getTotalSpeed()
	{
		return getTotalStat(Stat.Speed);
	}

	public int getTotalAccuracy()
	{
		return getTotalStat(Stat.Accuracy);
	}

	public int getTotalEvasion()
	{
		return getTotalStat(Stat.Evasion);
	}

	public int getTotalStat(Stat stat)
	{
		double value = getStatValue(stat);
		double modifierValue = getNatureModifierValue(stat) + 1.0;

		modifierValue = modifierValue == 0 ? 1 : modifierValue;

		return (int) ((value + modifierValue) * getTempStatModifier(stat));
	}

	public int getNatureModifierValue(Stat stat)
	{
		if(getNature().getIncreasedStat() == null || getNature().getDecreasedStat() == null)
		{
			return 0;
		}
		
		if(getNature().getIncreasedStat()
				.equals(stat))
		{
			return (int) (getStatValue(stat) * getNature().getModifier());
		}

		if(getNature().getDecreasedStat()
				.equals(stat))
		{
			return (int) (getStatValue(stat) * -getNature().getModifier());
		}

		return 0;
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

	public int healAnature(int healAmount)
	{
		int hitPointsAfterHeal = getCurrentHitPoints() + healAmount;
		if(healAmount == Integer.MAX_VALUE || hitPointsAfterHeal > getTotalHitPoints())
		{
			setCurrentHitPoints(getTotalHitPoints());
		}

		else
		{
			setCurrentHitPoints(hitPointsAfterHeal);
		}

		return getCurrentHitPoints();
	}

	/*
	 * PUBLIC METHODS
	 */

	public int addExperience(int expeienceGain)
	{
		if(expeienceGain < 0)
		{
			throw new IllegalArgumentException("Passed value \"expeienceGain\" was below 0.");
		}

		mTotalExperiencePoints += expeienceGain;

		int levelsGained = 0;
		while(levelGained())
		{
			levelsGained++;
		}

		return levelsGained;
	}

	public int getExperienceProgression()
	{
		return getTotalExperiencePoints() - requiredExperienceForLevel(getLevel());
	}

	public int getRequiredExperience()
	{
		return requiredExperienceForLevel(getLevel() + 1) - requiredExperienceForLevel(getLevel());
	}

	public StatsBuilder getClone()
	{
		return new StatsBuilder().atLevel(getLevel())
				.withLevlingSpeed(getLevelingSpeed())
				.withNature(getNature())
				.withBaseExperience(getTotalExperiencePoints())
				.withBaseHitPoints(getBaseHitPoints())
				.withBaseAttack(getBaseAttack())
				.withBaseDefense(getBaseDefense())
				.withBaseSpecialAttack(getBaseSpecialAttack())
				.withBaseSpecialDefense(getBaseSpecialDefense())
				.withBaseSpeed(getBaseSpeed())
				.withBaseAccuracy(getBaseAccuracy())
				.withBaseEvasion(getBaseEvasion())
				.withIVAttack(getIVAttack())
				.withIVDefense(getIVDefense())
				.withIVHitPoints(getIVHitPoints())
				.withIVSpecialAttack(getIVSpecialAttack())
				.withIVSpecialDefense(getIVSpecialDefense())
				.withIVSpeed(getIVSpeed())
				.withEVHitPoints(getEVHitPoints())
				.withEVAttack(getEVAttack())
				.withEVDefense(getEVDefense())
				.withEVSpecialAttack(getEVSpecialAttack())
				.withEVSpecialDefense(getEVSpecialDefense())
				.withEVSpeed(getEVSpeed());
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
		// TODO add evs from hashmap
		
		int hitPointsIncrease = (int) ((0.01 * (double) getBaseHitPoints()) + (0.02 * ((double) getIVHitPoints() + (double) getEVHitPoints())));
		hitPointsIncrease = hitPointsIncrease == 0 ? 1 : hitPointsIncrease;
		addLevelHitPoints(hitPointsIncrease);
		maintainHitPointPercentage(hitPointsIncrease);

		int attackIncrease = (int) ((0.01 * (double) getBaseAttack()) + (0.02 * ((double) getIVAttack() + (double) getEVAttack())));
		attackIncrease = attackIncrease == 0 ? 1 : attackIncrease;
		addLevelAttack(attackIncrease);

		int defenseIncrease = (int) ((0.01 * (double) getBaseDefense()) + (0.02 * ((double) getIVDefense() + (double) getEVDefense())));
		defenseIncrease = defenseIncrease == 0 ? 1 : defenseIncrease;
		addLevelDefnse(defenseIncrease);

		int specialAttackIncrease = (int) ((0.01 * (double) getBaseSpecialAttack()) + (0.02 * ((double) getIVSpecialAttack() + (double) getEVSpecialAttack())));
		specialAttackIncrease = specialAttackIncrease == 0 ? 1 : specialAttackIncrease;
		addLevelSpecialAttack(specialAttackIncrease);

		int specialDefenseIncrease = (int) ((0.01 * (double) getBaseSpecialDefense())
				+ (0.02 * ((double) getIVSpecialDefense() + (double) getEVSpecialDefense())));
		specialDefenseIncrease = specialDefenseIncrease == 0 ? 1 : specialDefenseIncrease;
		addLevelSpecialDefense(specialDefenseIncrease);

		int speedIncrease = (int) ((0.01 * (double) getBaseSpeed()) + (0.02 * ((double) getIVSpeed() + (double) getEVSpeed())));
		speedIncrease = speedIncrease == 0 ? 1 : speedIncrease;
		addLevelSpeed(speedIncrease);

		return true;
	}

	private void maintainHitPointPercentage(int hitPointsIncrease)
	{
		int currentHitPoints = getCurrentHitPoints();
		int previousTotalHitPoints = getTotalHitPoints() - hitPointsIncrease;

		double getHitPointsPercent = (double) currentHitPoints / (double) previousTotalHitPoints;

		int setHitPointsValue = (int) Math.ceil((((double) getTotalHitPoints()) * getHitPointsPercent));
		setCurrentHitPoints(setHitPointsValue);
	}

	private double getStatValue(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
				return getBaseHitPoints() + getIVHitPoints() + getEVHitPointsReduced() + getLevelHitPoints();

			case Attack:
				return getBaseAttack() + getIVAttack() + getEVAttackReduced() + getLevelAttack();

			case Defense:
				return getBaseDefense() + getIVDefense() + getEVDefenseReduced() + getLevelDefense();

			case SpecialAttack:
				return getBaseSpecialAttack() + getIVSpecialAttack() + getEVSpecialAttackReduced() + getLevelSpecialAttack();

			case SpecialDefense:
				return getBaseSpecialDefense() + getIVSpecialDefense() + getEVSpecialDefenseReduced() + getLevelSpecialDefense();

			case Speed:
				return getBaseSpeed() + getIVSpeed() + getEVSpeedReduced() + getLevelSpeed();

			case Evasion:
				return getBaseEvasion();

			case Accuracy:
				return getBaseAccuracy();

			default:
		}

		return 0;
	}

}
