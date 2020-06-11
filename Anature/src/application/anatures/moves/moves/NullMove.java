package application.anatures.moves.moves;

import application.anatures.Anature;
import application.anatures.moves.Move;
import application.interfaces.IMove;

public class NullMove extends Move
{
	private static final long serialVersionUID = 1L;

	private static IMove mNullMove;

	@Override
	public void activateMove(Anature source, Anature target)
	{

	}

	public static IMove getNullMove()
	{
		if(mNullMove == null)
		{
			mNullMove = new NullMove();
		}

		return mNullMove;
	}
}
