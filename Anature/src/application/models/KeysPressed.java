package application.models;

public class KeysPressed
{
	private boolean mUp, mDown, mLeft, mRight, mCanMove, mSprint;

	public KeysPressed()
	{
		mUp = false;
		mDown = false;
		mLeft = false;
		mRight = false;
		mCanMove = true;
		mSprint = false;
	}

	public boolean isUp()
	{
		return mUp;
	}

	public void setUp(boolean up)
	{
		mUp = up;
	}

	public boolean isDown()
	{
		return mDown;
	}

	public void setDown(boolean down)
	{
		mDown = down;
	}

	public boolean isLeft()
	{
		return mLeft;
	}

	public void setLeft(boolean left)
	{
		mLeft = left;
	}

	public boolean isRight()
	{
		return mRight;
	}

	public void setRight(boolean right)
	{
		mRight = right;
	}

	public boolean canMove()
	{
		return mCanMove;
	}

	public void setCanMove(boolean canMove)
	{
		mCanMove = canMove;
	}

	public boolean isSprint()
	{
		return mSprint;
	}

	public void setSprint(boolean sprint)
	{
		mSprint = sprint;
	}

	public void turnOffAll()
	{
		mUp = false;
		mDown = false;
		mRight = false;
		mLeft = false;
		mCanMove = false;
		mSprint = false;
	}
}
