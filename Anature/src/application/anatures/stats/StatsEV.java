package application.anatures.stats;

import java.util.HashMap;

import application.controllers.LoggerController;
import application.enums.LoggingTypes;
import application.enums.Stat;
import application.interfaces.stats.IStatsEV;

class StatsEV extends StatsLevel implements IStatsEV
{
	class EvChanged
	{
		private int mHp, mAtk, nDef, mSpAtk, mSpDef, mSpd;

		public int getStatAmount(Stat stat)
		{
			switch(stat)
			{
				case HitPoints:
					return mHp;

				case Attack:
					return mAtk;

				case Defense:
					return nDef;

				case SpecialAttack:
					return mSpAtk;

				case SpecialDefense:
					return mSpDef;

				case Speed:
					return mSpd;
					
				default:
					break;
			}

			return -1;
		}

		public void addStatAmount(Stat stat)
		{
			switch(stat)
			{
				case HitPoints:
					mHp++;
					break;

				case Attack:
					mAtk++;
					break;

				case Defense:
					nDef++;
					break;

				case SpecialAttack:
					mSpAtk++;
					break;

				case SpecialDefense:
					mSpDef++;
					break;

				case Speed:
					mSpd++;
					break;

				default:
					break;
			}
		}

		public void setStatAmount(Stat stat, int amount)
		{
			switch(stat)
			{
				case HitPoints:
					mHp = amount;
					break;

				case Attack:
					mAtk = amount;
					break;

				case Defense:
					nDef = amount;
					break;

				case SpecialAttack:
					mSpAtk = amount;
					break;

				case SpecialDefense:
					mSpDef = amount;
					break;

				case Speed:
					mSpd = amount;
					break;

				default:
					break;
			}
		}
	}

	private int mEVHitPoints;
	private int mEVAttack;
	private int mEVDefense;
	private int mEVSpecialAttack;
	private int mEVSpecialDefense;
	private int mEVSpeed;
	private HashMap<Integer, EvChanged> mEvRoadmap;

	StatsEV()
	{
		mEVHitPoints = 0;
		mEVAttack = 0;
		mEVDefense = 0;
		mEVSpecialAttack = 0;
		mEVSpecialDefense = 0;
		mEVSpeed = 0;
		mEvRoadmap = new HashMap<Integer, EvChanged>();
	}

	/*
	 * PACKAGE SETS
	 */

	void setEVHitPoints(int EVHitPoints)
	{
		if(EVHitPoints < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVHitPoints\" was below 0.");
		}

		if(EVHitPoints > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVHitPoints\" was above 255.");
		}

		mEVHitPoints = EVHitPoints;
	}

	void setEVAttack(int EVAttack)
	{
		if(EVAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVAttack\" was below 0.");
		}

		if(EVAttack > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVAttack\" was above 255.");
		}

		mEVAttack = EVAttack;
	}

	void setEVDefense(int EVDefense)
	{
		if(EVDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVDefense\" was below 0.");
		}

		if(EVDefense > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVDefense\" was above 255.");
		}

		mEVDefense = EVDefense;
	}

	void setEVSpecialAttack(int EVSpecialAttack)
	{
		if(EVSpecialAttack < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialAttack\" was below 0.");
		}

		if(EVSpecialAttack > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialAttack\" was above 255.");
		}

		mEVSpecialAttack = EVSpecialAttack;
	}

	void setEVSpecialDefense(int EVSpecialDefense)
	{
		if(EVSpecialDefense < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialDefense\" was below 0.");
		}

		if(EVSpecialDefense > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVSpecialDefense\" was above 255.");
		}

		mEVSpecialDefense = EVSpecialDefense;
	}

	void setEVSpeed(int EVSpeed)
	{
		if(EVSpeed < 0)
		{
			throw new IllegalArgumentException("Passed value \"EVSpeed\" was below 0.");
		}

		if(EVSpeed > 252)
		{
			throw new IllegalArgumentException("Passed value \"EVSpeed\" was above 255.");
		}

		mEVSpeed = EVSpeed;
	}

	/*
	 * PACKAGE METHODS
	 */
	
	public int getEvStat(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
				return mEVHitPoints;

			case Attack:
				return mEVAttack;
				
			case Defense:
				return mEVDefense;

			case SpecialAttack:
				return mEVSpecialAttack;
				
			case SpecialDefense:
				return mEVSpecialDefense;
				
			case Speed:
				return mEVSpeed;
				
			default:
				LoggerController.logEvent(LoggingTypes.Error, "Tried getting Ev Stat with non applicable enum.");
				return -1;
		}
	}
	
	public int getEvReducedStat(Stat stat)
	{
		switch(stat)
		{
			case HitPoints:
				return mEVHitPoints / 4;

			case Attack:
				return mEVAttack / 4;
				
			case Defense:
				return mEVDefense / 4;

			case SpecialAttack:
				return mEVSpecialAttack / 4;
				
			case SpecialDefense:
				return mEVSpecialDefense / 4;
				
			case Speed:
				return mEVSpeed / 4;
				
			default:
				LoggerController.logEvent(LoggingTypes.Error, "Tried getting Ev Stat with non applicable enum.");
				return -1;
		}
	}

	/*
	 * PACKAGE METHODS
	 */

	boolean addEVHitPoint(int level)
	{
		if(!canAddEv())
		{
			return false;
		}

		if(mEVHitPoints != 252)
		{
			mEVHitPoints += 1;
			addToRoadMap(Stat.HitPoints, level);
			return true;
		}

		return false;
	}

	boolean addEVAttack(int level)
	{
		if(!canAddEv())
		{
			return false;
		}

		if(mEVAttack != 252)
		{
			mEVAttack += 1;
			addToRoadMap(Stat.Attack, level);
			return true;
		}

		return false;
	}

	boolean addEVDefense(int level)
	{
		if(!canAddEv())
		{
			return false;
		}

		if(mEVDefense != 252)
		{
			mEVDefense += 1;
			addToRoadMap(Stat.Defense, level);
			return true;
		}

		return false;
	}

	boolean addEVSpecialAttack(int level)
	{
		if(!canAddEv())
		{
			return false;
		}

		if(mEVSpecialAttack != 252)
		{
			mEVSpecialAttack += 1;
			addToRoadMap(Stat.SpecialAttack, level);
			return true;
		}

		return false;
	}

	boolean addEVSpecialDefense(int level)
	{
		if(!canAddEv())
		{
			return false;
		}

		if(mEVSpecialDefense != 252)
		{
			mEVSpecialDefense += 1;
			addToRoadMap(Stat.SpecialDefense, level);
			return true;
		}

		return false;
	}

	boolean addEVSpeed(int level)
	{
		if(!canAddEv())
		{
			return false;
		}

		if(mEVSpeed != 252)
		{
			mEVSpeed += 1;
			addToRoadMap(Stat.Speed, level);
			return true;
		}

		return false;
	}

	boolean canAddEv()
	{
		return mEVHitPoints + mEVAttack + mEVDefense + mEVSpecialAttack + mEVSpecialDefense + mEVSpeed < 510;
	}

	HashMap<Integer, EvChanged> getEvRoadMap()
	{
		return mEvRoadmap;
	}

	/*
	 * PRIVATE METHODS
	 */

	private void addToRoadMap(Stat stat, int level)
	{
		if(mEvRoadmap.containsKey(level))
		{
			EvChanged changed = mEvRoadmap.get(level);
			changed.addStatAmount(stat);
		}

		else
		{
			EvChanged changed = new EvChanged();
			changed.addStatAmount(stat);
			mEvRoadmap.put(level, changed);
		}
	}
}
