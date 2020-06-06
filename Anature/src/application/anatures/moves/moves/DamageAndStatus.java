package application.anatures.moves.moves;

import application.anatures.moves.Move;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAnature;

public class DamageAndStatus extends Move
{
	private static final long serialVersionUID = 3724356003537500882L;

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
	public void activateMove(IAnature source, IAnature target)
	{
		Type primary = target.getPrimaryType();
		Type secondary = target.getSecondaryType();
		
		target.applyDamage(calculateDamage(source, target, false));

		if(Math.random() > mThreshold
				&& target.getStatus() == StatusEffects.None)
		{
			if(mStatus == StatusEffects.Burn
					&& (primary == Type.Fire
							|| secondary == Type.Fire))
			{
				return;
			}

			else if(mStatus == StatusEffects.Poison
					&& (primary == Type.Poison
							|| secondary == Type.Poison))
			{
				return;
			}

			else if(mStatus == StatusEffects.Paralysis
					&& (primary == Type.Electric
							|| secondary == Type.Electric))
			{
				return;
			}

			target.updateStatus(mStatus);
		}
	}
}