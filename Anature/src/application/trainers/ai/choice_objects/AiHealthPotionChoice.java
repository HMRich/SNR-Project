package application.trainers.ai.choice_objects;

import application.controllers.LoggerController;
import application.enums.AiChoice;
import application.enums.LoggingTypes;
import application.interfaces.AiChoiceObject;
import application.items.HealthPotion;

public class AiHealthPotionChoice extends AiFinalChoice implements AiChoiceObject<HealthPotion>
{
	private HealthPotion mPotion;

	public AiHealthPotionChoice(HealthPotion potion)
	{
		super(AiChoice.Item_Consumed);
		setHealthPotion(potion);
	}

	private void setHealthPotion(HealthPotion potion)
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

	private HealthPotion getHealthPotion()
	{
		return mPotion;
	}

	@Override
	public HealthPotion getChoiceObject()
	{
		return getHealthPotion();
	}

	@Override
	public AiChoice getAiChoice()
	{
		return getChoice();
	}
}
