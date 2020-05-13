package application.interfaces;

import application.enums.MoveIds;
import application.enums.Type;

public interface IMove
{
	public void activateMove(IAnature source, IAnature target);
	public String getName();
	public MoveIds getMoveId();
	public Type getType();
	public boolean doesDamage();
	public boolean isPhysicalAttack();
	public int getTotalMovePoints();
	public int getMovePower();
	public double getAccuracy();
}
