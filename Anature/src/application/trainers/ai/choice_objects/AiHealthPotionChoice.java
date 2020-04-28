package application.trainers.ai.choice_objects;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.interfaces.AiChoiceObject;
import application.interfaces.IHealthPotion;

public class AiHealthPotionChoice extends AiFinalChoice implements AiChoiceObject<IHealthPotion>
{
	private IHealthPotion mPotion;

	public AiHealthPotionChoice(IHealthPotion potion)
	{
		super(AiChoice.Item_Consumed);
		setHealthPotion(potion);
	}

	private void setHealthPotion(IHealthPotion potion)
	{
		if(potion != null)
		{
			mPotion = potion;
		}

		else
		{
			LoggerController.logEvent(LoggingTypes.Error,
					"IllegalArgumentException in AiMoveChoice.java, Method: setHealthPotion(HealthPotion potion), potion value was null.");
		}
	}

	private IHealthPotion getHealthPotion()
	{
		return mPotion;
	}

	@Override
	public IHealthPotion getChoiceObject()
	{
		return getHealthPotion();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
