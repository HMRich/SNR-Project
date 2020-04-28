package application;

import java.util.ArrayList;
import java.util.Random;

import application.anatures.Anature;
import application.anatures.MoveSet;
import application.controllers.results.AbilityActivation;
import application.controllers.results.AbilityResult;
import application.controllers.results.ItemResult;
import application.controllers.results.MoveResult;
import application.enums.AbilityIds;
import application.enums.MoveIds;
import application.interfaces.IMove;
import application.items.Item;
import application.pools.MovePool;

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

		int oldUserHp = userAnature.getCurrentHitPoints();
		int oldTargetHp = targetAnature.getCurrentHitPoints();
		ArrayList<String> dialogue = new ArrayList<String>();
		MoveSet moveSet = userAnature.getMoveSet();
		IMove moveBase = moveSet.getMove(indexOfMove);

		if(indexOfMove <= 0)
		{
			moveBase = MovePool.getMove(MoveIds.Flail);
		}

		AbilityIds userAbilityId = userAnature.getAbility()
				.getAbilityId();
		AbilityIds targetAbilityId = targetAnature.getAbility()
				.getAbilityId();

		moveSet.useMovePoint(indexOfMove);

		AbilityResult canAttackAbilityResult = AbilityActivation.useAbilityCanAttack(userAbilityId, userAnature, targetAnature, moveBase);
		boolean landedTheAttack = landedAttack(userAnature, moveBase);

		if(landedTheAttack)
		{
			dialogue.add(userAnature.getName() + " used " + moveBase.getName() + " on " + targetAnature.getName());

			if(!canAttackAbilityResult.isActiviated())
			{
				moveBase.activateMove(userAnature, targetAnature);
			}
		}

		else
		{
			dialogue.add(userAnature.getName() + " used " + moveBase.getName() + " but missed!");
		}

		AbilityResult targetAfterTurnAbilityResult = AbilityActivation.useAbilityAfterAttack(targetAbilityId, targetAnature, userAnature, moveBase, oldTargetHp,
				false, !landedTheAttack);
		AbilityResult userAfterTurnAbilityResult = AbilityActivation.useAbilityAfterAttack(userAbilityId, userAnature, targetAnature, moveBase, oldUserHp, true,
				!landedTheAttack);

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

		return new MoveResult(dialogue, afterTurn, indexOfMove, isPlayerAttacking, moveBase);
	}

	private boolean landedAttack(Anature userAnature, IMove iMove)
	{
		Random rng = new Random();
		double anatureAccuracy = userAnature.getAccuracy();
		if(anatureAccuracy >= 1.0)
		{
			anatureAccuracy = 1.0;
		}

		double totalAccuracy = iMove.getAccuracy() * anatureAccuracy;
		return rng.nextInt(101) < totalAccuracy * 100;
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

	public AbilityResult activateEntryAbility(boolean isPlayer)
	{
		Anature player = getPlayerAnature();
		Anature enemy = getEnemyAnature();

		if(isPlayer)
		{
			return AbilityActivation.useEntryAbility(player.getAbility()
					.getAbilityId(), player, enemy);
		}

		return AbilityActivation.useEntryAbility(enemy.getAbility()
				.getAbilityId(), enemy, player);
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

		if(team.get(0)
				.getMoveSet() == null)
		{
			throw new NullPointerException("Anature's MoveSet was null, String or Result Object?");
		}

		if(team.get(0)
				.getMoveSet()
				.getMove(indexOfMove) == null)
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

	public int getEnemyIndex()
	{
		return mEnemyIndex;
	}

	public int getPlayerIndex()
	{
		return mPlayerIndex;
	}
}
