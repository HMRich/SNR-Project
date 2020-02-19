package application;

import java.util.ArrayList;

import application.enums.ItemIds;
import application.moves.Move;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FightManager
{
	private ArrayList<Anature> mPlayerTeam, mEnemyTeam;
	private String mPlayerName, mEnemyName;

	public FightManager(ArrayList<Anature> playerTeam, ArrayList<Anature> enemyTeam, String playerName, String enemyName)
	{
		if(playerTeam == null || enemyTeam == null || playerName == null || enemyName == null)
			throw new IllegalArgumentException("Passed in parameter was null.");
		
		mPlayerTeam = playerTeam;
		mEnemyTeam = enemyTeam;
		mPlayerName = playerName;
		mEnemyName = enemyName;
	}

	public MoveResult attackEnemy(int indexOfMove)
	{
		checkNulls(mPlayerTeam, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(0);
		Anature enemyAnature = mEnemyTeam.get(0);
		Move playerAnatureMove = mPlayerTeam.get(0).getMoves().getMove(indexOfMove);
		double oldHp = enemyAnature.getCurrHp();
		
		playerAnatureMove.activateMove(playerAnature, enemyAnature);
		return new MoveResult(oldHp - enemyAnature.getCurrHp(), mPlayerName + "'s " + playerAnature.getName() + " attacked "
																+ mEnemyName + "'s " + enemyAnature.getName() + "!");
	}

	public MoveResult attackPlayer(int indexOfMove)
	{
		checkNulls(mEnemyTeam, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(0);
		Anature enemyAnature = mEnemyTeam.get(0);
		Move enemyAnatureMove = mEnemyTeam.get(0).getMoves().getMove(indexOfMove);
		double oldHp = playerAnature.getCurrHp();
		
		enemyAnatureMove.activateMove(enemyAnature, playerAnature);		
		return new MoveResult(oldHp - playerAnature.getCurrHp(), mEnemyName + "'s " + enemyAnature.getName() + " attacked "
																+ mPlayerName + "'s " + playerAnature.getName() + "!");
	}

	public void itemUse(boolean isPlayer, int indexOfTeam, Enum<ItemIds> itemId)
	{
		Item item = ItemPool.getItems(itemId); 
		Anature target; 
		
		if(isPlayer) {
			target = mPlayerTeam.get(indexOfTeam);
			item.useItem(target);
		} else {
			target = mEnemyTeam.get(indexOfTeam);
			item.useItem(target);
		}
	}

	public ArrayList<Anature> getPlayerTeam()
	{
		return mPlayerTeam;
	}

	public ArrayList<Anature> getEnemyTeam()
	{
		return mEnemyTeam;
	}

	private void checkNulls(ArrayList<Anature> team, int indexOfMove)
	{
		if(mPlayerTeam.get(0) == null)
		{
			throw new NullPointerException("Player Anature was null, String or Result Object?");
		}
		
		if(mEnemyTeam.get(0) == null)
		{
			throw new NullPointerException("Enemy Anature was null, String or Result Object?");
		}
		
		if(team.get(0).getMoves() == null)
		{
			throw new NullPointerException("Anature's MoveSet was null, String or Result Object?");
		}
		
		if(team.get(0).getMoves().getMove(indexOfMove) == null)
		{
			throw new NullPointerException("Anature's Move was null, String or Result Object?");
		}
	}
}
