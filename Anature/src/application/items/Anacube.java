package application.items;

import java.util.Random;

import application.controllers.results.ItemResult;
import application.enums.AnacubeResults;
import application.enums.Gender;
import application.enums.ItemIds;
import application.enums.Stat;
import application.interfaces.IAnature;

public class Anacube extends ItemBase
{
	private double mCatchRateModifier;

	public Anacube(double catchRateModifier)
	{
		if(catchRateModifier < 1)
		{
			catchRateModifier = 1;
		}

		mCatchRateModifier = catchRateModifier;
	}

	@Override
	public ItemResult useItem(IAnature target)
	{
		double maxHp = target.getStats().getTotalStat(Stat.HitPoints);
		double currHp = target.getStats().getCurrentHitPoints();
		double catchRate = target.getCatchRate();
		double statusBonus = getStatusBonus(target);

		double calculationA = (3 * maxHp - 2 * currHp) * catchRate * mCatchRateModifier;
		calculationA /= (3 * maxHp);
		calculationA *= statusBonus;

		AnacubeResults bounceResult = null;

		if(calculationA >= 255)
		{
			bounceResult = AnacubeResults.Catch;
		}

		else
		{
			bounceResult = secondCalculations(calculationA);
		}

		ItemResult result = new ItemResult(getDialogue(bounceResult, target.getGender(), target.getName()), 0);
		result.setCatchResults(bounceResult);

		return result;
	}

	private AnacubeResults secondCalculations(double calculationA)
	{
		double calculationB = 16711680 / calculationA;
		calculationB = Math.sqrt(calculationB);
		calculationB = Math.sqrt(calculationB);
		calculationB = 1048560 / calculationB;

		Random rng = new Random();
		boolean bounceOneCheck = rng.nextInt(65536) < calculationB;
		boolean bounceTwoCheck = rng.nextInt(65536) < calculationB;
		boolean bounceThreeCheck = rng.nextInt(65536) < calculationB;
		boolean bounceFourCheck = rng.nextInt(65536) < calculationB;

		return calculateBounceResult(bounceOneCheck, bounceTwoCheck, bounceThreeCheck, bounceFourCheck);
	}

	private AnacubeResults calculateBounceResult(boolean bounceOneCheck, boolean bounceTwoCheck, boolean bounceThreeCheck, boolean bounceFourCheck)
	{
		int bounceCount = boolToInt(bounceOneCheck) + boolToInt(bounceTwoCheck) + boolToInt(bounceThreeCheck) + boolToInt(bounceFourCheck);

		switch(bounceCount)
		{
			case 1:
				return AnacubeResults.One_Bounce;

			case 2:
				return AnacubeResults.Two_Bounce;

			case 3:
				return AnacubeResults.Three_Bounce;

			case 4:
				return AnacubeResults.Catch;

			default:
				return AnacubeResults.Zero_Bounce;
		}
	}

	private String getDialogue(AnacubeResults bounceResult, Gender gender, String name)
	{
		String pronoun = "It";

		switch(gender)
		{
			case Male:
				pronoun = "He";
				break;

			case Female:
				pronoun = "She";
				break;

			default:
				break;
		}

		switch(bounceResult)
		{
			case One_Bounce:
				return pronoun + " doesn't want to give up!";

			case Two_Bounce:
				return pronoun + " was almost captured!";

			case Three_Bounce:
				return pronoun + " was about to be caught!";

			case Catch:
				return "Congratulations! You caught " + name;

			default:
				break;
		}

		return pronoun + " broke out instantly!";
	}

	private int boolToInt(boolean bool)
	{
		return bool ? 1 : 0;
	}

	private double getStatusBonus(IAnature target)
	{
		switch(target.getStatus())
		{
			case Frozen:
			case Sleep:
				return 2;

			case Paralysis:
			case Poison:
			case Burn:
				return 1.5;

			default:
				return 1;
		}
	}

	public void setItemId(ItemIds id)
	{
		super.setItemId(id);
	}

	public void setItemName(String name)
	{
		super.setItemName(name);
	}
}
