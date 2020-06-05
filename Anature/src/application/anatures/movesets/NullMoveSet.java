package application.anatures.movesets;

import java.io.Serializable;

import application.anatures.moves.moves.NullMove;

public class NullMoveSet extends MoveSet implements Serializable
{
	private static final long serialVersionUID = -5479053677379093858L;

	private static MoveSet mNullMoveSet;

	public NullMoveSet()
	{
		super(new NullMove(), new NullMove(), new NullMove(), new NullMove());
	}

	public static MoveSet getNullMoveSet()
	{
		if(mNullMoveSet == null)
		{
			mNullMoveSet = new NullMoveSet();
		}

		return mNullMoveSet;
	}

	@Override
	public String toString()
	{
		return "Null MoveSet";
	}
}
