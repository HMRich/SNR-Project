package application;

import java.util.ArrayList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FightManager {

	private ArrayList<Anature> mPlayerTeam;
	private ArrayList<Anature> mEnemyTeam;

	public FightManager(ArrayList<Anature> playerTeam, ArrayList<Anature> enemyTeam) {
		mPlayerTeam = playerTeam;
		mEnemyTeam = enemyTeam;
	}

	public String attackEnemy(int indexOfPlayer, int indexOfEnemy, int indexOfMove) {
		checkNulls(indexOfPlayer, indexOfEnemy, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(indexOfPlayer);
		Anature enemyAnature = mEnemyTeam.get(indexOfEnemy);
		Move playerAnatureMove = mPlayerTeam.get(indexOfPlayer).getMoves().getMove(indexOfMove);
		playerAnatureMove.activateMove(playerAnature, enemyAnature);
		return "Player attacked Enemy Anature";
	}

	public String attackPlayer(int indexOfPlayer, int indexOfEnemy, int indexOfMove) {
		checkNulls(indexOfPlayer, indexOfEnemy, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(indexOfPlayer);
		Anature enemyAnature = mEnemyTeam.get(indexOfEnemy);
		Move enemyAnatureMove = mEnemyTeam.get(indexOfEnemy).getMoves().getMove(indexOfMove);
		enemyAnatureMove.activateMove(enemyAnature, playerAnature);
		return "Enemy attacked player Anature";
	}

	public void itemUse(boolean isPlayer, int indexOfTeam, Enum<?> item) {
		throw new NotImplementedException();
	}

	public ArrayList<Anature> getPlayerTeam() {
		return mPlayerTeam;
	}

	public ArrayList<Anature> getEnemyTeam() {
		return mEnemyTeam;
	}

	private void checkNulls(int indexOfPlayer, int indexOfEnemy, int indexOfMove) {
		if (mPlayerTeam.get(indexOfPlayer) == null) {
			throw new NullPointerException("Player Anature was null, String or Result Object?");
		}
		if (mEnemyTeam.get(indexOfEnemy) == null) {
			throw new NullPointerException("Enemy Anature was null, String or Result Object?");
		}
		if (mPlayerTeam.get(indexOfEnemy).getMoves() == null) {
			throw new NullPointerException("Player Anature Move was null, String or Result Object?");
		}
		if (mPlayerTeam.get(indexOfPlayer).getMoves().getMove(indexOfMove) == null) {
			throw new NullPointerException("PlayerTeam Anature Move was null, String or Result Object?");
		}
	}

}
