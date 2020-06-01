package application.anatures.stats;

import java.util.HashMap;

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
					return nDef ;
					
				case SpecialAttack:
					return mSpAtk;
					
				case SpecialDefense:
					return mSpDef;
					
				case Speed:
					return mSpd;
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

	public int getEVHitPoints()
	{
		return mEVHitPoints;
	}

	public int getEVAttack()
	{
		return mEVAttack;
	}

	public int getEVDefense()
	{
		return mEVDefense;
	}

	public int getEVSpecialAttack()
	{
		return mEVSpecialAttack;
	}

	public int getEVSpecialDefense()
	{
		return mEVSpecialDefense;
	}

	public int getEVSpeed()
	{
		return mEVSpeed;
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

	/*
	 * PUBLIC METHODS
	 */

	public int getEVHitPointsReduced()
	{
		return mEVHitPoints / 4;
	}

	public int getEVAttackReduced()
	{
		return mEVAttack / 4;
	}

	public int getEVDefenseReduced()
	{
		return mEVDefense / 4;
	}

	public int getEVSpecialAttackReduced()
	{
		return mEVSpecialAttack / 4;
	}

	public int getEVSpecialDefenseReduced()
	{
		return mEVSpecialDefense / 4;
	}

	public int getEVSpeedReduced()
	{
		return mEVSpeed / 4;
	}
}
