package application.anatures.stats;

import application.enums.LevelingSpeed;
import application.enums.Natures;
import application.enums.Natures.NatureStats;
import application.interfaces.IStats;

class StatsCore extends StatsBase implements IStats
{
	private int mLevel;
	private int mExperiencePoints;
	private LevelingSpeed mLevelingSpeed;
	private Natures mNature;

	StatsCore()
	{
		mLevel = 1;
		mExperiencePoints = 0;
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

	public LevelingSpeed getLevelingSpeed()
	{
		return mLevelingSpeed;
	}

	public Natures getNature()
	{
		return mNature;
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

	/*
	 * PACKAGE METHODS
	 */

	void levelUpStats()
	{
		addExperience(requiredExperienceForLevel(getLevel()));
	}

	boolean canCreateStatsCore()
	{
		if(getLevelingSpeed().equals(LevelingSpeed.NotSet))
		{
			throw new IllegalStateException("The \"levelingSpeed\" variable was never set during construction.");
		}

		return canCreateStatsBase() && canCreateStatsCore();
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
