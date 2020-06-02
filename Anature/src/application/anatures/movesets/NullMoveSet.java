package application.anatures.movesets;

import application.anatures.moves.MoveCollection;

public class NullMoveSet extends MoveSet
{
	private static MoveSet mNullMoveSet = new NullMoveSet();

	public NullMoveSet()
	{
		super(MoveCollection.getNullMove(), MoveCollection.getNullMove(), MoveCollection.getNullMove(), MoveCollection.getNullMove());
	}

	public static MoveSet getNullMoveSet()
	{
		return mNullMoveSet;
	}

	public String toString()
	{
		return "Null MoveSet";
	}
}
