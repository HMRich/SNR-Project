package application.anatures.movesets;

import application.anatures.moves.moves.NullMove;

public class NullMoveSet extends MoveSet
{
	private static MoveSet mNullMoveSet = new NullMoveSet();

	public NullMoveSet()
	{
		super(new NullMove(), new NullMove(), new NullMove(), new NullMove());
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
