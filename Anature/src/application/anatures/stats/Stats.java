package application.anatures.stats;

import java.util.Arrays;
import java.util.HashMap;

import application.enums.Stat;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.stats.IStats;

class Stats extends StatsBase implements IStats
{
	private static final long serialVersionUID = 8358817858029495206L;

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

	@Override
	public int getLevel()
	{
		return mLevel;
	}

	@Override
	public int getTotalExperiencePoints()
	{
		return mTotalExperiencePoints;
	}

	@Override
	public int getCurrentHitPoints()
	{
		return mCurrentHitPoints;
	}

	@Override
	public LevelingSpeed getLevelingSpeed()
	{
		return mLevelingSpeed;
	}

	@Override
	public Natures getNature()
	{
		return mNature;
	}

	@Override
	public int getTotalStat(Stat stat)
	{
		double value = getStatValue(stat);
		double modifierValue = getNatureModifierValue(stat) * 1.0;

		return (int) ((value + modifierValue) * getTempStatModifier(stat));
	}

	@Override
	public int getNatureModifierValue(Stat stat)
	{
		if(getNature().getIncreasedStat() == null
				|| getNature().getDecreasedStat() == null)
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

	@Override
	public Stat getLargestStat()
	{
		int hp = getTotalStat(Stat.HitPoints);
		int atk = getTotalStat(Stat.Attack);
		int def = getTotalStat(Stat.Defense);
		int spAtk = getTotalStat(Stat.SpecialAttack);
		int spDef = getTotalStat(Stat.SpecialDefense);
		int spd = getTotalStat(Stat.Speed);

		int[] stats = { hp, atk, def, spAtk, spDef, spd };
		Arrays.sort(stats);

		if(hp == stats[0])
		{
			return Stat.HitPoints;
		}

		else if(atk == stats[0])
		{
			return Stat.Attack;
		}

		else if(def == stats[0])
		{
			return Stat.Defense;
		}

		else if(spAtk == stats[0])
		{
			return Stat.SpecialAttack;
		}

		else if(spDef == stats[0])
		{
			return Stat.SpecialDefense;
		}

		return Stat.Speed;
	}

	/*
	 * PUBLIC SETS
	 */

	@Override
	public void setCurrentHitPoints(int hitPoints)
	{
		if(hitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"hitPoints\" was below 0.");
		}

		if(hitPoints > getTotalStat(Stat.HitPoints))
		{
			throw new IllegalArgumentException("Passed value \"hitPoints\" was above Anature's total hit points.");
		}

		mCurrentHitPoints = hitPoints;
	}

	@Override
	public int healAnature(int healAmount)
	{
		int hitPointsAfterHeal = getCurrentHitPoints() + healAmount;
		if(healAmount == Integer.MAX_VALUE
				|| hitPointsAfterHeal > getTotalStat(Stat.HitPoints))
		{
			setCurrentHitPoints(getTotalStat(Stat.HitPoints));
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

	@Override
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

	@Override
	public int getExperienceProgression()
	{
		return getTotalExperiencePoints() - requiredExperienceForLevel(getLevel());
	}

	@Override
	public int getRequiredExperience()
	{
		return requiredExperienceForLevel(getLevel() + 1) - requiredExperienceForLevel(getLevel());
	}

	@Override
	public double getHitPointsPercent()
	{
		return ((double) getCurrentHitPoints()) / ((double) getTotalStat(Stat.HitPoints));
	}

	@Override
	public void applyDamage(int damage)
	{
		if(damage <= 0)
		{
			throw new IllegalArgumentException("Passed value \"damage\" was equal to or below 0.");
		}

		int newCurrentHitPoints = getCurrentHitPoints() - damage;

		if(newCurrentHitPoints > 0)
		{
			setCurrentHitPoints(newCurrentHitPoints);
		}

		else
		{
			setCurrentHitPoints(0);
		}
	}

	@Override
	public String applyHeal(int healAmount)
	{
		if(healAmount <= 0)
		{
			throw new IllegalArgumentException("Passed value \"healAmount\" was equal to or below 0.");
		}

		int hitPointsAfterHeal = healAnature(healAmount);
		if(hitPointsAfterHeal == getTotalStat(Stat.HitPoints))
		{
			return " was healed completely!";
		}

		return " was healed " + healAmount + " hp.";
	}

	@Override
	public StatsBuilder getClone()
	{
		return new StatsBuilder().atLevel(getLevel())
				.withLevlingSpeed(getLevelingSpeed())
				.withNature(getNature())
				.withBaseExperience(getBaseExperience())
				.withBaseHitPoints(getBaseStat(Stat.HitPoints))
				.withBaseAttack(getBaseStat(Stat.Attack))
				.withBaseDefense(getBaseStat(Stat.Defense))
				.withBaseSpecialAttack(getBaseStat(Stat.SpecialAttack))
				.withBaseSpecialDefense(getBaseStat(Stat.SpecialDefense))
				.withBaseSpeed(getBaseStat(Stat.Speed))
				.withBaseAccuracy(getBaseStat(Stat.Accuracy))
				.withBaseEvasion(getBaseStat(Stat.Evasion))
				.withIVAttack(getIvStat(Stat.Attack))
				.withIVDefense(getIvStat(Stat.Defense))
				.withIVHitPoints(getIvStat(Stat.HitPoints))
				.withIVSpecialAttack(getIvStat(Stat.SpecialAttack))
				.withIVSpecialDefense(getIvStat(Stat.SpecialDefense))
				.withIVSpeed(getIvStat(Stat.Speed))
				.withEVHitPoints(getEvStat(Stat.HitPoints))
				.withEVAttack(getEvStat(Stat.Attack))
				.withEVDefense(getEvStat(Stat.Defense))
				.withEVSpecialAttack(getEvStat(Stat.SpecialAttack))
				.withEVSpecialDefense(getEvStat(Stat.SpecialDefense))
				.withEVSpeed(getEvStat(Stat.Speed));
	}

	@Override
	public boolean addEv(Stat statToAdd, int level)
	{
		switch(statToAdd)
		{
			case HitPoints:
				return addEVHitPoint(level);

			case Attack:
				return addEVAttack(level);

			case Defense:
				return addEVDefense(level);

			case SpecialAttack:
				return addEVSpecialAttack(level);

			case SpecialDefense:
				return addEVSpecialDefense(level);

			case Speed:
				return addEVSpeed(level);

			default:
				return false;
		}
	}

	/*
	 * PACKAGE METHODS
	 */

	void levelUpStats()
	{
		for(int statLevel = 1; statLevel != getLevel(); statLevel++)
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
		checkEvRoadmap();

		int hitPointsIncrease = (int) ((0.01 * getBaseStat(Stat.HitPoints))
				+ (0.02 * ((double) getIvStat(Stat.HitPoints) + (double) getEvStat(Stat.HitPoints))));
		hitPointsIncrease = hitPointsIncrease == 0 ? 1 : hitPointsIncrease;
		addLevelHitPoints(hitPointsIncrease);
		maintainHitPointPercentage(hitPointsIncrease);

		int attackIncrease = (int) ((0.01 * getBaseStat(Stat.Attack)) + (0.02 * ((double) getIvStat(Stat.Attack) + (double) getEvStat(Stat.Attack))));
		attackIncrease = attackIncrease == 0 ? 1 : attackIncrease;
		addLevelAttack(attackIncrease);

		int defenseIncrease = (int) ((0.01 * getBaseStat(Stat.Defense)) + (0.02 * ((double) getIvStat(Stat.Defense) + (double) getEvStat(Stat.Defense))));
		defenseIncrease = defenseIncrease == 0 ? 1 : defenseIncrease;
		addLevelDefnse(defenseIncrease);

		int specialAttackIncrease = (int) ((0.01 * getBaseStat(Stat.SpecialAttack))
				+ (0.02 * ((double) getIvStat(Stat.SpecialAttack) + (double) getEvStat(Stat.SpecialAttack))));
		specialAttackIncrease = specialAttackIncrease == 0 ? 1 : specialAttackIncrease;
		addLevelSpecialAttack(specialAttackIncrease);

		int specialDefenseIncrease = (int) ((0.01 * getBaseStat(Stat.SpecialDefense))
				+ (0.02 * ((double) getIvStat(Stat.SpecialDefense) + (double) getEvStat(Stat.SpecialDefense))));
		specialDefenseIncrease = specialDefenseIncrease == 0 ? 1 : specialDefenseIncrease;
		addLevelSpecialDefense(specialDefenseIncrease);

		int speedIncrease = (int) ((0.01 * getBaseStat(Stat.Speed)) + (0.02 * ((double) getIvStat(Stat.Speed) + (double) getEvStat(Stat.Speed))));
		speedIncrease = speedIncrease == 0 ? 1 : speedIncrease;
		addLevelSpeed(speedIncrease);

		return true;
	}

	private void checkEvRoadmap()
	{
		HashMap<Integer, EvChanged> roadmap = getEvRoadMap();

		if(roadmap.containsKey(mLevel))
		{
			EvChanged changed = roadmap.get(mLevel);

			int evHp = changed.getStatAmount(Stat.HitPoints);
			int evAtk = changed.getStatAmount(Stat.Attack);
			int evDef = changed.getStatAmount(Stat.Defense);
			int evSpAtk = changed.getStatAmount(Stat.SpecialAttack);
			int evSpDef = changed.getStatAmount(Stat.SpecialDefense);
			int evSpeed = changed.getStatAmount(Stat.Speed);

			for(int i = 0; i < evHp; i++)
			{
				addEVHitPoint(mLevel);
			}
			changed.setStatAmount(Stat.HitPoints, evHp);

			for(int i = 0; i < evAtk; i++)
			{
				addEVAttack(mLevel);
			}
			changed.setStatAmount(Stat.Attack, evAtk);

			for(int i = 0; i < evDef; i++)
			{
				addEVDefense(mLevel);
			}
			changed.setStatAmount(Stat.Defense, evDef);

			for(int i = 0; i < evSpAtk; i++)
			{
				addEVSpecialAttack(mLevel);
			}
			changed.setStatAmount(Stat.SpecialAttack, evSpAtk);

			for(int i = 0; i < evSpDef; i++)
			{
				addEVSpecialDefense(mLevel);
			}
			changed.setStatAmount(Stat.SpecialDefense, evSpDef);

			for(int i = 0; i < evSpeed; i++)
			{
				addEVSpeed(mLevel);
			}
			changed.setStatAmount(Stat.Speed, evSpeed);

			roadmap.put(mLevel, changed);
		}
	}

	private void maintainHitPointPercentage(int hitPointsIncrease)
	{
		int currentHitPoints = getCurrentHitPoints();
		int previousTotalHitPoints = getTotalStat(Stat.HitPoints) - hitPointsIncrease;

		double getHitPointsPercent = (double) currentHitPoints / (double) previousTotalHitPoints;

		int setHitPointsValue = (int) Math.ceil(((getTotalStat(Stat.HitPoints)) * getHitPointsPercent));
		setCurrentHitPoints(setHitPointsValue);
	}

	private double getStatValue(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
			case Attack:
			case Defense:
			case SpecialAttack:
			case SpecialDefense:
			case Speed:
				return getBaseStat(stat) + getIvStat(stat) + getEvReducedStat(stat) + getLevelStat(stat);

			case Evasion:
			case Accuracy:
				return getBaseStat(stat);

			default:
		}

		return 0;
	}

}
