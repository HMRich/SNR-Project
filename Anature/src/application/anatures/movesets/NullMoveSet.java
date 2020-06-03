package application.anatures.movesets;

import java.io.Serializable;

import application.anatures.moves.moves.NullMove;

public class NullMoveSet extends MoveSet implements Serializable
{
	private static final long serialVersionUID = -5479053677379093858L;

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
