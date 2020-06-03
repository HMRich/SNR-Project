package application.controllers.results;

import java.util.HashMap;

import application.enums.BattleEndMethods;
import application.enums.Species;
import application.interfaces.IAnature;

public class BattleResult
{
	private BattleEndMethods mEndMethod;
	private HashMap<IAnature, Species> mAnaturesToEvolve;
	
	public BattleResult(BattleEndMethods endMethod, HashMap<IAnature, Species> anaturesToEvolve)
	{
		mEndMethod = endMethod;
		mAnaturesToEvolve = anaturesToEvolve;
	}
	
	public boolean hasEvolutions()
	{
		return mAnaturesToEvolve.size() > 0;
	}
	
	public BattleEndMethods getBattleEndMethod()
	{
		return mEndMethod;
	}

	public HashMap<IAnature, Species> getAnaturesToEvolve()
	{
		return mAnaturesToEvolve;
	}
}
