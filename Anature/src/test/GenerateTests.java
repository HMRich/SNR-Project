package test;

import application.enums.Type;

public class GenerateTests
{

	final static Type[] types =
	{ Type.Normal, Type.Fire, Type.Fighting, Type.Water, Type.Flying, Type.Grass, Type.Poison, Type.Electric, Type.Ground, Type.Psychic, Type.Rock, Type.Ice,
			Type.Bug, Type.Dragon, Type.Ghost, Type.Dark, Type.Steel, Type.Fairy };

	public static void main(String... args)
	{
		for(Type outerType : types)
		{
			for(Type innerType : types)
			{
				System.out.println("addCase(createTestCase(new Type[] {Type." + outerType.toString() + "}, new Type[] {Type." + innerType.toString()
						+ "}, AttackEffectiveness.Normal)),");
			}
		}
	}

}