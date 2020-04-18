package application;

import java.util.ArrayList;
import java.util.Random;

import application.abillities.AbilityResult;
import application.enums.AbilityIds;
import application.enums.MoveIds;
import application.items.Item;
import application.moves.Move;
import application.moves.MovePool;

public class FightManager
{
	private ArrayList<Anature> mPlayerTeam, mEnemyTeam;
	private int mPlayerIndex, mEnemyIndex, mTurnCount;

	public FightManager(ArrayList<Anature> playerTeam, ArrayList<Anature> enemyTeam)
	{
		if(playerTeam == null || enemyTeam == null)
			throw new IllegalArgumentException("Passed in parameter was null.");

		mPlayerTeam = playerTeam;
		mEnemyTeam = enemyTeam;

		mPlayerIndex = 0;
		mEnemyIndex = 0;
		mTurnCount = 0;
	}
	
	public void applyDamage(boolean isPlayer, int index, double damage)
	{
		ArrayList<Anature> team = null;
		
		if(isPlayer)
		{
			team = mPlayerTeam;
		}
		
		else
		{
			team = mEnemyTeam;
		}
		
		Anature selected = team.get(index);
		selected.takeDamage(damage);
	}
	
	public void useStruggle(boolean isPlayerAttacking)
	{
		attack(isPlayerAttacking, -1);
	}
	
	public MoveResult attack(boolean isPlayerAttacking, int indexOfMove)
	{
		Anature userAnature = null;
		Anature targetAnature = null;
		
		if(isPlayerAttacking)
		{
			checkNulls(mPlayerTeam, indexOfMove);
			
			userAnature = mPlayerTeam.get(mPlayerIndex);
			targetAnature = mEnemyTeam.get(mEnemyIndex);
		}
		
		else
		{
			checkNulls(mEnemyTeam, indexOfMove);
			
			userAnature = mEnemyTeam.get(mEnemyIndex);
			targetAnature = mPlayerTeam.get(mPlayerIndex);
		}

		int oldUserHp = userAnature.getCurrHp();
		int oldTargetHp = targetAnature.getCurrHp();
		ArrayList<String> dialogue = new ArrayList<String>();
		MoveSet moveSet = userAnature.getMoves();
		Move move = moveSet.getMove(indexOfMove);
		
		if(indexOfMove <= 0)
		{
			move = MovePool.getMove(MoveIds.Struggle);
		}
		
		AbilityIds userAbility = userAnature.getAbility().getAbilityId();
		AbilityIds targetAbilityIds = targetAnature.getAbility().getAbilityId();
		
		AbilityResult canAttackAbilityResult = AbilityActivation.useAbilityCanAttack(userAbility, userAnature, targetAnature, move);

		if(!canAttackAbilityResult.isActiviated())
		{
			moveSet.useMp(indexOfMove);
			activateAttack(userAnature, targetAnature, move, dialogue);
		}
		
		dialogue.add(userAnature.getName() + " used " + move.getName() + " on " + targetAnature.getName());
		
		AbilityResult targetAfterTurnAbilityResult = AbilityActivation.useAbilityAfterAttack(targetAbilityIds, targetAnature, userAnature, move, oldTargetHp);
		AbilityResult userAfterTurnAbilityResult = AbilityActivation.useAbilityAfterAttack(userAbility, userAnature, targetAnature, move, oldUserHp);
		
		ArrayList<String> afterTurnDialogue = new ArrayList<String>();
		afterTurnDialogue.addAll(canAttackAbilityResult.getDialogue());
		afterTurnDialogue.addAll(targetAfterTurnAbilityResult.getDialogue());
		afterTurnDialogue.addAll(userAfterTurnAbilityResult.getDialogue());
		
		boolean abilitiesWereActivated = false;
		if(canAttackAbilityResult.isActiviated() || targetAfterTurnAbilityResult.isActiviated() || userAfterTurnAbilityResult.isActiviated())
		{
			abilitiesWereActivated = true;
		}
		
		AbilityResult afterTurn = new AbilityResult(afterTurnDialogue, abilitiesWereActivated);
		
		return new MoveResult(dialogue, afterTurn, indexOfMove, isPlayerAttacking, move.doesDamage());				
	}
	
	private void activateAttack(Anature userAnature, Anature targetAnature, Move move, ArrayList<String> dialogue)
	{
		Random rng = new Random();
		int anatureAccuracy = userAnature.getAccuracy() + userAnature.getTempAccuracy();
		if(anatureAccuracy > 100)
		{
			anatureAccuracy = 100;
		}
		
		double totalAccuracy = (move.getAccuracy() * 0.01) * (anatureAccuracy * 0.01);
		if(rng.nextInt(101) < totalAccuracy * 100)
		{
			move.activateMove(userAnature, targetAnature);
		}
	}

	public ItemResult itemUse(boolean isPlayer, int indexOfTeam, Item item)
	{
		mTurnCount++;
		Anature target;

		if(isPlayer)
		{
			target = mPlayerTeam.get(indexOfTeam);
			return item.useItem(target);
		}

		else
		{
			target = mEnemyTeam.get(indexOfTeam);
			return item.useItem(target);
		}
	}

	public ArrayList<Anature> getPlayerTeam()
	{
		return mPlayerTeam;
	}
	
	public Anature getPlayerAnature()
	{
		return mPlayerTeam.get(mPlayerIndex);
	}

	public ArrayList<Anature> getEnemyTeam()
	{
		return mEnemyTeam;
	}
	
	public Anature getEnemyAnature()
	{
		return mEnemyTeam.get(mEnemyIndex);
	}

	public int getTurnNumber()
	{
		return mTurnCount;
	}
	
	public void increaseTurnNumber()
	{
		mTurnCount++;
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
