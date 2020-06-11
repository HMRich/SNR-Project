package application.enums;

import java.util.ArrayList;

public enum Type
{
	NotSet,
	Normal,
	Fire,
	Water,
	Electric,
	Grass,
	Ice,
	Fighting,
	Poison,
	Ground,
	Flying,
	Psychic,
	Bug,
	Rock,
	Ghost,
	Dragon,
	Dark,
	Steel,
	Fairy;

	public static ArrayList<Type> getTypes()
	{
		ArrayList<Type> typeList = new ArrayList<Type>();

		for(Type type : Type.values())
		{
			typeList.add(type);
		}

		return typeList;
	}
}
