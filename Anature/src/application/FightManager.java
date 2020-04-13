package application;

import java.util.ArrayList;
import java.util.Random;

import application.abillities.AbilityResult;
import application.abillities.Determination;
import application.abillities.Spiky;
import application.abillities.Tyrannize;
import application.enums.AbilityIds;
import application.enums.MoveIds;
import application.items.Item;
import application.moves.Move;
import application.moves.MovePool;

public class FightManager
{
	private ArrayList<Anature> mPlayerTeam, mEnemyTeam;
	private String mPlayerName, mEnemyName;
	private int mPlayerIndex, mEnemyIndex, mTurnCount;

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
			activateAttack(userAnature, targetAnature, move, dialogue);
		}
		
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
			dialogue.add(userAnature.getName() + " used " + move.getName() + " on " + targetAnature.getName());
		}
	}

	public MoveResult attackEnemy(int indexOfMove)
	{
		mTurnCount++;
		checkNulls(mPlayerTeam, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(mPlayerIndex);
		Anature enemyAnature = mEnemyTeam.get(mEnemyIndex);
		double oldHp = enemyAnature.getCurrHp();

		MoveSet moves = mPlayerTeam.get(mPlayerIndex).getMoves();
		AbilityResult playerResult = abilityUse(playerAnature.getAbility().getAbilityId(), playerAnature, enemyAnature, null, enemyAnature.getCurrHp());
		if((moves.getMovePoints(indexOfMove) <= 0) && (indexOfMove != -1)) // TODO Fully implement Struggle
		{
			playerAnature.takeDamage(10);
			enemyAnature.takeDamage(20);

			return new MoveResult(oldHp - enemyAnature.getCurrHp(),
					mPlayerName + "'s " + playerAnature.getName() + " Flaied at " + mEnemyName + "'s " + enemyAnature.getName() + "!", -1, "1", true);
		}

		Move playerAnatureMove = moves.getMove(indexOfMove);
		
		/* i'm not sure how you want to add enemy/player result to this
		 * 
		if(playerAnatureMove.getMoveId() == MoveIds.Skip_Turn) {
			AbilityResult enemyResult = abilityUse(enemyAnature.getAbility().getAbilityId(), playerAnature, enemyAnature, playerAnatureMove, oldHp);
			return new MoveResult(oldHp - enemyAnature.getCurrHp(),
					mPlayerName + "'s " + playerAnature.getName() + " could not attack " + mEnemyName + "'s " + enemyAnature.getName() + "because it has " + playerAnature.getStatus() + "!", -1,
					"-1/" + playerAnatureMove.getTotalMovePoints(), false);
		}
		*/
		// This if statement checks if the move is going to miss
		if((playerAnatureMove.getAccuracy() / playerAnature.getTempAccuracy()) > (Math.random() + .1))
		{
			
			playerAnatureMove.activateMove(playerAnature, enemyAnature);
			moves.useMp(indexOfMove);

			AbilityResult enemyResult = abilityUse(enemyAnature.getAbility().getAbilityId(), playerAnature, enemyAnature, playerAnatureMove, oldHp);

			return new MoveResult(oldHp - enemyAnature.getCurrHp(),
					mPlayerName + "'s " + playerAnature.getName() + " attacked " + mEnemyName + "'s " + enemyAnature.getName() + "!", indexOfMove,
					moves.getMovePoints(indexOfMove) + "/" + playerAnatureMove.getTotalMovePoints(), true);
		}
		
		else
		{
			return new MoveResult(0, mPlayerName + "'s " + playerAnature.getName() + " missed " + mEnemyName + "'s " + enemyAnature.getName() + "!",
					indexOfMove, moves.getMovePoints(indexOfMove) + "/" + playerAnatureMove.getTotalMovePoints(), true);
		}

	}

	public MoveResult attackPlayer(int indexOfMove)
	{
		mTurnCount++;
		checkNulls(mEnemyTeam, indexOfMove);
		Anature playerAnature = mPlayerTeam.get(mPlayerIndex);
		Anature enemyAnature = mEnemyTeam.get(mEnemyIndex);
		double oldHp = playerAnature.getCurrHp();

		MoveSet moves = mEnemyTeam.get(mEnemyIndex).getMoves();
		AbilityResult enemyResult = abilityUse(enemyAnature.getAbility().getAbilityId(), playerAnature, playerAnature, null, playerAnature.getCurrHp());
		if((moves.getMovePoints(indexOfMove) <= 0) && (indexOfMove != -1)) // TODO Fully implement Struggle
		{
			enemyAnature.takeDamage(10);
			playerAnature.takeDamage(20);

			return new MoveResult(oldHp - playerAnature.getCurrHp(),
					mEnemyName + "'s " + enemyAnature.getName() + " Flaied at " + mPlayerName + "'s " + playerAnature.getName() + "!", -1, "1", false);
		}

		Move enemyAnatureMove = moves.getMove(indexOfMove);
		
		
		if(enemyAnatureMove.getMoveId() == MoveIds.Skip_Turn) {
			
			return new MoveResult(oldHp - playerAnature.getCurrHp(),
					mEnemyName + "'s " + enemyAnature.getName() + " could not attack " + mPlayerName + "'s " + playerAnature.getName() + "because it has " + enemyAnature.getStatus() + "!", -1,
					"-1/" + enemyAnatureMove.getTotalMovePoints(), false);
		}
		
		if((enemyAnatureMove.getAccuracy() / enemyAnature.getTempAccuracy()) > (Math.random() + .1))
		{
			
			
			enemyAnatureMove.activateMove(enemyAnature, playerAnature);
			moves.useMp(indexOfMove);

			AbilityResult playerResult = abilityUse(playerAnature.getAbility().getAbilityId(), enemyAnature, playerAnature, enemyAnatureMove, oldHp);

			return new MoveResult(oldHp - playerAnature.getCurrHp(),
					mEnemyName + "'s " + enemyAnature.getName() + " attacked " + mPlayerName + "'s " + playerAnature.getName() + "!", indexOfMove,
					moves.getMovePoints(indexOfMove) + "/" + enemyAnatureMove.getTotalMovePoints(), false);
		}
		
		else
		{
			return new MoveResult(0, mEnemyName + "'s " + enemyAnature.getName() + " missed " + mPlayerName + "'s " + playerAnature.getName() + "!",
					indexOfMove, moves.getMovePoints(indexOfMove) + "/" + enemyAnatureMove.getTotalMovePoints(), false);
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

	public AbilityResult abilityUse(AbilityIds abilityId, Anature attackingAnature, Anature targetAnature, Move move, double oldHp)
	{
		String dialogue;
		AbilityResult result = new AbilityResult("", false);
		boolean skipTurn = (move.getMoveId() == MoveIds.Skip_Turn);
		switch(abilityId)
		{
			case Determination:
				dialogue = Determination.activateAbility(targetAnature, move, oldHp);
				if (!dialogue.equals(""))
				{
					result = new AbilityResult(dialogue, true);
				}
				break;

			case Dry_Skin:

				break;

			case Tyrannize:
				dialogue = Tyrannize.activateAbility(targetAnature);
				if (getTurnNumber() == 1) 
				{
					result =  new AbilityResult(dialogue, true);
				}
				break;

			case SleepDeprived:
//				SleepDeprived.activateAbility(); // TODO add functionality
				break;

			case ToughSkin:
//				dialogue = ToughSkin.activateAbility(attackingAnature, targetAnature, move, oldHp);
//				if (!dialogue.equals(""))
//				{
//					result = new AbilityResult(dialogue,true);
//				}
				break;

			case Spiky:
				dialogue = Spiky.activateAbility(attackingAnature, targetAnature, move);
				if (!dialogue.equals(""))
				{
					result =  new AbilityResult(dialogue, true);
				}
				
				break;
		}
		return result; 
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

	private int getTurnNumber()
	{
		return mTurnCount % 2;
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
