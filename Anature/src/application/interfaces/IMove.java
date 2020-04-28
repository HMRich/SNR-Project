package application.interfaces;

import application.anatures.Anature;
import application.enums.MoveIds;
import application.enums.Type;

public interface IMove
{
	public void activateMove(Anature source, Anature target);
	public String getName();
	public MoveIds getMoveId();
	public Type getType();
	public boolean doesDamage();
	public boolean isPhysicalAttack();
	public int getTotalMovePoints();
	public double getAccuracy();
}
