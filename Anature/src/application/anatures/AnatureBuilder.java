package application.anatures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.DatabaseConnection;
import application.Startup;
import application.anatures.abillities.NullAbility;
import application.interfaces.IMove;
import application.enums.AbilityIds;
import application.enums.DatabaseType;
import application.enums.Gender;
import application.enums.MoveIds;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.interfaces.IAbility;
import application.interfaces.IBuilder;
import application.pools.AbilityPool;
import application.pools.MovePool;

public class AnatureBuilder implements IBuilder<Anature>
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

	public AnatureBuilder setAbility(IAbility iAbility)
	{
		mAnature.setAbility(iAbility);
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
		if(buildIsComplete())
		{
			Anature anatureToReturn = mAnature;

			generateNewAnature();

			return anatureToReturn;
		}

		throw new IllegalStateException("All the builder variables need to have a value before you create a Anature.");
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

	/*
	 * STATIC PUBLIC METHODS
	 */

	public static Anature createAnature(Species species, int level)
	{
		String name = species.toString()
				.replaceAll("_", " ");
		String ownerName = Startup.getPlayerName();
		boolean isShiny = generateIsShiny();

		Gender gender = generateGender();
		Type[] types =
		{ Type.NotSet, Type.NotSet };
		MoveSet moveSet = generateMoveSet(species, level);
		IAbility iAbility = NullAbility.getNullAbility();
		StatusEffects status = StatusEffects.None;

		// TODO Figure out how to implement the Experience system
		int currentExperiencePoints = 0;
		int totalHitPoints = 0;

		int indexNumber = 0;
		int attack = 0;
		int defense = 0;
		int specialAttack = 0;
		int specialDefense = 0;
		int speed = 0;
		int accuracy = 0;

		String possibleAbilitiesString = "";
		String typesString = "";
		String totalHitPointsString = "";
		String indexNumberString = "";
		String attackString = "";
		String defenseString = "";
		String specialAttackString = "";
		String specialDefenseString = "";
		String speedString = "";
		String accuracyString = "";

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureDatabase);

			String query = "Select * from Anature Where SpeciesName=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, species.toString());

			ResultSet results = pst.executeQuery();
			if(results.next())
			{
				possibleAbilitiesString = results.getString("PossibleAbilities");
				typesString = results.getString("Types");
				totalHitPointsString = results.getString("BaseHp");
				indexNumberString = results.getString("IndexNum");
				attackString = results.getString("BaseAttack");
				defenseString = results.getString("BaseDefense");
				specialAttackString = results.getString("BaseSpecialAttack");
				specialDefenseString = results.getString("BaseSpecialDefense");
				speedString = results.getString("BaseSpeed");
				accuracyString = results.getString("Accuracy");
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		iAbility = generateAbility(possibleAbilitiesString);

		totalHitPoints = Integer.parseInt(totalHitPointsString);
		indexNumber = Integer.parseInt(indexNumberString);
		attack = Integer.parseInt(attackString);
		defense = Integer.parseInt(defenseString);
		specialAttack = Integer.parseInt(specialAttackString);
		specialDefense = Integer.parseInt(specialDefenseString);
		speed = Integer.parseInt(speedString);
		accuracy = Integer.parseInt(accuracyString);

		types = generateTypes(typesString, types);

		return new AnatureBuilder().setName(name)
				.setOwnerName(ownerName)
				.setIsShiny(isShiny)
				.setSpecies(species)
				.setGender(gender)
				.setPrimaryType(types[0])
				.setSecondaryType(types[1])
				.setMoveSet(moveSet)
				.setAbility(iAbility)
				.setStatus(status)
				.setIndexNumber(indexNumber)
				.setLevel(level)
				.setCurrentExperiencePoints(currentExperiencePoints)
				.setTotalHitPoints(totalHitPoints)
				.setCurrentHitPoints(totalHitPoints)
				.setAttack(attack)
				.setDefense(defense)
				.setSpecialAttack(specialAttack)
				.setSpecialDefense(specialDefense)
				.setSpeed(speed)
				.setAccuracy(accuracy)
				.create();
	}

	/*
	 * STATIC PRIVATE METHODS
	 */

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

		IMove move1 = null;
		IMove move2 = null;
		IMove move3 = null;
		IMove move4 = null;

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

	private static Gender generateGender()
	{
		Random r = new Random();
		Gender[] genderArray = Gender.values();
		ArrayList<Gender> genderList = new ArrayList<Gender>();

		for(Gender gender : genderArray)
		{
			if(!gender.equals(Gender.NotSet))
			{
				genderList.add(gender);
			}
		}

		return genderList.get(r.nextInt(genderList.size()));
	}

	private static IAbility generateAbility(String possilbeAbilities)
	{
		Random r = new Random();

		ArrayList<String> abilities = new ArrayList<String>(Arrays.asList(possilbeAbilities.split(",")));
		AbilityIds chosenAbility = AbilityIds.valueOf(abilities.get(r.nextInt(abilities.size())));

		return AbilityPool.getAbility(chosenAbility);
	}

	private static Type[] generateTypes(String typesString, Type[] types)
	{
		String[] typesStringArray = typesString.split(",");

		for(int i = 0; i < typesStringArray.length && i < 2; i++)
		{
			types[i] = Type.valueOf(typesStringArray[i]);
		}

		return types;
	}

	private static boolean generateIsShiny()
	{
		Random r = new Random();

		return r.nextInt(4200) == 420;
	}

}