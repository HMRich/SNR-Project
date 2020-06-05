package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.enums.Type;

public class DamageAndStatus extends Move
{
	private double mThreshold;
	private StatusEffects mStatus;

	public DamageAndStatus(StatusEffects status, double thresholdToGoOver)
	{
		if(status == null)
		{
			status = StatusEffects.None;
		}

		mThreshold = thresholdToGoOver;
		mStatus = status;
	}

	@Override
	public void activateMove(Anature source, Anature target)
	{
		Type primary = target.getPrimaryType();
		Type secondary = target.getSecondaryType();
		
		target.applyDamage(calculateDamage(source, target, false));

		if(Math.random() > mThreshold && target.getStatus() == StatusEffects.None)
		{
			if(mStatus == StatusEffects.Burn && (primary == Type.Fire || secondary == Type.Fire))
			{
				return;
			}

			else if(mStatus == StatusEffects.Poison && (primary == Type.Poison || secondary == Type.Poison))
			{
				return;
			}

			else if(mStatus == StatusEffects.Paralysis && (primary == Type.Electric || secondary == Type.Electric))
			{
				return;
			}

			target.setStatus(mStatus);
		}
	}
}