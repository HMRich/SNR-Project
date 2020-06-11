package application.interfaces;

import java.io.Serializable;

import application.enums.MoveIds;
import application.enums.Type;

public interface IMove extends Serializable
{
	public void activateMove(Anature source, Anature target);

	public String getName();

	public MoveIds getMoveId();

	public Type getType();

	public boolean doesDamage();

	public boolean isPhysicalAttack();

	public int getTotalMovePoints();

	public int getMovePower();

	public double getAccuracy();
}
