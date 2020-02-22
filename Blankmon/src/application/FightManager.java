package application;

import java.util.ArrayList;

import application.abillities.*;
import application.enums.AbilityIds;
import application.enums.ItemIds;
import application.moves.Move;

public class FightManager
{
	private ArrayList<Anature> mPlayerTeam, mEnemyTeam;
	private String mPlayerName, mEnemyName;
	private int mPlayerIndex, mEnemyIndex;

	public FightManager(ArrayList<Anature> playerTeam, ArrayList<Anature> enemyTeam, String playerName, String enemyName)
	{
		if(playerTeam == null || enemyTeam == null || playerName == null || enemyName == null)
			throw new IllegalArgumentException("Passed in parameter was null.");

		mPlayerTeam = playerTeam;
		mEnemyTeam = enemyTeam;
		mPlayerName = playerName;
		mEnemyName = enemyName;
		
		mPlayerIndex = 0;
		mEnemyIndex = 0;
	}

	public MoveResult attackEnemy(int indexOfMove)
	{
		checkNulls(mPlayerTeam, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(mPlayerIndex);
		Anature enemyAnature = mEnemyTeam.get(mEnemyIndex);
		double oldHp = enemyAnature.getCurrHp();
		
		MoveSet moves = mPlayerTeam.get(mPlayerIndex).getMoves();
		if(moves.getMovePoints(indexOfMove) <= 0) // TODO Fully implement Struggle
		{
			playerAnature.takeDamage(10);
			enemyAnature.takeDamage(20);
			
			return new MoveResult(oldHp - enemyAnature.getCurrHp(),
					mPlayerName + "'s " + playerAnature.getName() + " Flaied at " + mEnemyName + "'s " + enemyAnature.getName() + "!", -1,
					"1", true);
		}

		Move playerAnatureMove = moves.getMove(indexOfMove);

		playerAnatureMove.activateMove(playerAnature, enemyAnature);
		moves.useMp(indexOfMove);
		
		abilityUse(enemyAnature.getAbility().getAbilityId(), playerAnature, enemyAnature, playerAnatureMove, oldHp);
		
		return new MoveResult(oldHp - enemyAnature.getCurrHp(),
				mPlayerName + "'s " + playerAnature.getName() + " attacked " + mEnemyName + "'s " + enemyAnature.getName() + "!", indexOfMove,
				moves.getMovePoints(indexOfMove) + "/" + playerAnatureMove.getTotalMovePoints(), true);
	}

	public MoveResult attackPlayer(int indexOfMove)
	{
		checkNulls(mEnemyTeam, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(mPlayerIndex);
		Anature enemyAnature = mEnemyTeam.get(mEnemyIndex);
		double oldHp = playerAnature.getCurrHp();
		
		MoveSet moves = mEnemyTeam.get(mEnemyIndex).getMoves();
		if(moves.getMovePoints(indexOfMove) <= 0) // TODO Fully implement Struggle
		{
			enemyAnature.takeDamage(10);
			playerAnature.takeDamage(20);
			
			return new MoveResult(oldHp - playerAnature.getCurrHp(),
					mEnemyName + "'s " + enemyAnature.getName() + " Flaied at " + mPlayerName + "'s " + playerAnature.getName() + "!", -1,
					"1", false);
		}
		
		Move enemyAnatureMove = moves.getMove(indexOfMove);

		enemyAnatureMove.activateMove(enemyAnature, playerAnature);
		moves.useMp(indexOfMove);
		
		abilityUse(playerAnature.getAbility().getAbilityId(), enemyAnature, playerAnature, enemyAnatureMove, oldHp);
		
		return new MoveResult(oldHp - playerAnature.getCurrHp(),
				mEnemyName + "'s " + enemyAnature.getName() + " attacked " + mPlayerName + "'s " + playerAnature.getName() + "!", indexOfMove,
				moves.getMovePoints(indexOfMove) + "/" + enemyAnatureMove.getTotalMovePoints(), false);
	}

	public void itemUse(boolean isPlayer, int indexOfTeam, ItemIds itemId)
	{
		Item item = ItemPool.getItems(itemId);
		Anature target;

		if(isPlayer)
		{
			target = mPlayerTeam.get(indexOfTeam);
			item.useItem(target);
		}
		
		else
		{
			target = mEnemyTeam.get(indexOfTeam);
			item.useItem(target);
		}
	}

	public void abilityUse(AbilityIds abilityId, Anature attackingAnature, Anature targetAnature, Move move, double oldHp)
	{
		switch(abilityId)
		{
			case Determined:
				Determined.activateAbility(targetAnature, move, oldHp);
				break;
				
			case Dry_Skin:
				
				break;
				
			case Intimidate:
				
				break;
				
			case Iron_Barbs:
				
				break;
				
			case Spiky:
				Spiky.activateAbility(attackingAnature, targetAnature, move);
				break;
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
	
	public void setPlayerSelectedIndex(int index)
	{
		if(index > -1 && index < 7)
			mPlayerIndex = index;
	}
	
	public void setEnemySelectedIndex(int index)
	{
		if(index > -1 && index < 7)
			mEnemyIndex = index;
	}
}
