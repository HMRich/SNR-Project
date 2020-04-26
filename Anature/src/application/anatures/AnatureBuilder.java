package application.anatures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.Builder;
import application.DatabaseConnection;
import application.Startup;
import application.anatures.abillities.Ability;
import application.anatures.moves.Move;
import application.anatures.moves.MoveSet;
import application.enums.AbilityIds;
import application.enums.DatabaseType;
import application.enums.Gender;
import application.enums.MoveIds;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.pools.AbilityPool;
import application.pools.MovePool;

public class AnatureBuilder implements Builder<Anature>
{
	private Anature mAnature;

	public AnatureBuilder()
	{
		generateNewAnature();
	}

	/*
	 * PUBLIC SETS
	 */

	public AnatureBuilder setName(String name)
	{
		mAnature.setName(name);
		return this;
	}

	public AnatureBuilder setOwnerName(String ownerName)
	{
		mAnature.setOwnerName(ownerName);
		return this;
	}

	public AnatureBuilder setIsShiny(boolean isShiny)
	{
		mAnature.setIsShiny(isShiny);
		return this;
	}

	public AnatureBuilder setSpecies(Species species)
	{
		mAnature.setSpecies(species);
		return this;
	}

	public AnatureBuilder setGender(Gender gender)
	{
		mAnature.setGender(gender);
		return this;
	}

	public AnatureBuilder setPrimaryType(Type primaryType)
	{
		mAnature.setPrimaryType(primaryType);
		return this;
	}

	public AnatureBuilder setSecondaryType(Type secondaryType)
	{
		mAnature.setSecondaryType(secondaryType);
		return this;
	}

	public AnatureBuilder setMoveSet(MoveSet moveSet)
	{
		mAnature.setMoveSet(moveSet);
		return this;
	}

	public AnatureBuilder setAbility(Ability ability)
	{
		mAnature.setAbility(ability);
		return this;
	}

	public AnatureBuilder setStatus(StatusEffects statusEffect)
	{
		mAnature.setStatus(statusEffect);
		return this;
	}

	public AnatureBuilder setIndexNumber(int indexNumber)
	{
		mAnature.setIndexNumber(indexNumber);
		return this;
	}

	public AnatureBuilder setLevel(int level)
	{
		mAnature.setLevel(level);
		return this;
	}

	public AnatureBuilder setCurrentExperiencePoints(int currentExperiencePoints)
	{
		mAnature.setCurrentExperiencePoints(currentExperiencePoints);
		return this;
	}

	public AnatureBuilder setTotalHitPoints(int totalHitPoints)
	{
		mAnature.setTotalHitPoints(totalHitPoints);
		return this;
	}
	
	public AnatureBuilder setCurrentHitPoints(int currentHitPoints)
	{
		mAnature.setCurrentHitPoints(currentHitPoints);
		return this;
	}

	public AnatureBuilder setAttack(int attack)
	{
		mAnature.setAttack(attack);
		return this;
	}

	public AnatureBuilder setDefense(int defense)
	{
		mAnature.setDefense(defense);
		return this;
	}

	public AnatureBuilder setSpecialAttack(int specialAttack)
	{
		mAnature.setSpecialAttack(specialAttack);
		return this;
	}

	public AnatureBuilder setSpecialDefense(int specialDefense)
	{
		mAnature.setSpecialDefense(specialDefense);
		return this;
	}

	public AnatureBuilder setSpeed(int speed)
	{
		mAnature.setSpeed(speed);
		return this;
	}

	public AnatureBuilder setAccuracy(int accuracy)
	{
		mAnature.setAccuracy(accuracy);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public Anature create()
	{
		if(!buildIsComplete())
		{
			throw new IllegalStateException("All the builder variables need to have a value before you create a Anature.");
		}

		Anature anatureToReturn = mAnature;

		generateNewAnature();

		return anatureToReturn;
	}

	public static Anature createAnature(Species species, int level)
	{
		String name = species.toString();
		String ownerName = Startup.getPlayerName();
		MoveSet moves = generateMoveSet(species, level);
		Gender gender = null;
		Type[] types = new Type[2];
		boolean isShiny = false;
		Ability ability = null;
		int attack = 0, specialAttack = 0, defense = 0, specialDefense = 0, hp = 0, speed = 0, indexNum = 0, accuracy = 0;

		Random r = new Random();

		if(r.nextInt(2) == 0)
		{
			gender = Gender.Male;
		}

		else
		{
			gender = Gender.Female;
		}

		if(r.nextInt(4200) == 420)
		{
			isShiny = true;
		}

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureDatabase);

			String query = "Select * from Anature Where SpeciesName=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, species.toString());

			ResultSet results = pst.executeQuery();
			if(results.next())
			{
				String hpStr = results.getString("BaseHp");
				String atkStr = results.getString("BaseAttack");
				String spAtkStr = results.getString("BaseSpecialAttack");
				String defStr = results.getString("BaseDefense");
				String spDefStr = results.getString("BaseSpecialDefense");
				String abilitiesStr = results.getString("PossibleAbilities");
				String speedStr = results.getString("BaseSpeed");
				String typesStr = results.getString("Types");
				String indexNumStr = results.getString("IndexNum");
				String accuracyStr = results.getString("Accuracy");

				hp = Integer.parseInt(hpStr);
				attack = Integer.parseInt(atkStr);
				specialAttack = Integer.parseInt(spAtkStr);
				defense = Integer.parseInt(defStr);
				specialDefense = Integer.parseInt(spDefStr);
				speed = Integer.parseInt(speedStr);
				indexNum = Integer.parseInt(indexNumStr);
				accuracy = Integer.parseInt(accuracyStr);

				ArrayList<String> abilities = new ArrayList<String>(Arrays.asList(abilitiesStr.split(",")));
				AbilityIds chosenAbility = AbilityIds.valueOf(abilities.get(r.nextInt(abilities.size())));
				ability = AbilityPool.getAbility(chosenAbility);

				String[] typesStrAra = typesStr.split(",");
				for(int i = 0; i < typesStrAra.length && i < 2; i++)
				{
					types[i] = Type.valueOf(typesStrAra[i]);
				}

			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return new AnatureBuilder().setName(name)
				.setOwnerName(ownerName)
				.setIsShiny(isShiny)
				.setSpecies(species)
				.setGender(gender)
				.setPrimaryType(types[0])
				.setSecondaryType(types[1])
				.setMoveSet(moves)
				.setAbility(ability)
				.setStatus(StatusEffects.None)
				.setIndexNumber(indexNum)
				.setLevel(level)
				.setCurrentExperiencePoints(0)
				.setTotalHitPoints(hp)
				.setCurrentExperiencePoints(hp)
				.setAttack(attack)
				.setDefense(defense)
				.setSpecialAttack(specialAttack)
				.setSpecialDefense(specialDefense)
				.setSpeed(speed)
				.setAccuracy(accuracy)
				.create();
	}

	/*
	 * PRIVATE METHODS
	 */

	private void generateNewAnature()
	{
		mAnature = new Anature();
	}

	private boolean buildIsComplete()
	{
		return mAnature.canCreate();
	}

	private static MoveSet generateMoveSet(Species species, int level)
	{
		ArrayList<MoveIds> availableMoves = new ArrayList<MoveIds>();

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureSpeciesDatabase);

			String query = "Select * from " + species.toString() + "Species";
			PreparedStatement pst = connect.prepareStatement(query);

			ResultSet results = pst.executeQuery();
			while(results.next())
			{
				String minLevelStr = results.getString("Level");
				String moveNameStr = results.getString("MoveName");

				int minLevel = Integer.parseInt(minLevelStr);

				if(minLevel > level)
					continue;

				MoveIds moveName = MoveIds.valueOf(moveNameStr);
				availableMoves.add(moveName);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		Move move1 = null;
		Move move2 = null;
		Move move3 = null;
		Move move4 = null;

		if(availableMoves.size() <= 4)
		{
			if(availableMoves.size() >= 1)
				move1 = MovePool.getMove(availableMoves.get(0));

			if(availableMoves.size() >= 2)
				move2 = MovePool.getMove(availableMoves.get(1));

			if(availableMoves.size() >= 3)
				move3 = MovePool.getMove(availableMoves.get(2));

			if(availableMoves.size() >= 4)
				move4 = MovePool.getMove(availableMoves.get(3));
		}

		else
		{
			int chosenIndex1 = 0, chosenIndex2 = 0, chosenIndex3 = 0, chosenIndex4 = 0;

			chosenIndex1 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex1, chosenIndex2, chosenIndex3, chosenIndex4);
			chosenIndex2 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex2, chosenIndex1, chosenIndex3, chosenIndex4);
			chosenIndex3 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex3, chosenIndex2, chosenIndex1, chosenIndex4);
			chosenIndex4 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex4, chosenIndex2, chosenIndex1, chosenIndex3);

			move1 = MovePool.getMove(availableMoves.get(chosenIndex1));
			move2 = MovePool.getMove(availableMoves.get(chosenIndex2));
			move3 = MovePool.getMove(availableMoves.get(chosenIndex3));
			move4 = MovePool.getMove(availableMoves.get(chosenIndex4));
		}

		return new MoveSet(move1, move2, move3, move4);
	}

	private static int generateRandomUniqueIndex(int max, int toGenerate, int otherIndex1, int otherIndex2, int otherIndex3)
	{
		Random r = new Random();
		toGenerate = r.nextInt(max);

		while(toGenerate == otherIndex1 || toGenerate == otherIndex2 || toGenerate == otherIndex3)
		{
			toGenerate = r.nextInt(max);
		}

		return toGenerate;
	}
}