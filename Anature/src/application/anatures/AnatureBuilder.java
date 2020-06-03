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
import application.anatures.movesets.MoveSet;
import application.anatures.stats.StatsBuilder;
import application.enums.AbilityIds;
import application.enums.DatabaseType;
import application.enums.Gender;
import application.enums.MoveIds;
import application.enums.Species;
import application.enums.StatusEffects;
import application.enums.Type;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.IBuilder;
import application.interfaces.IMove;
import application.interfaces.stats.IStats;
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

	public AnatureBuilder withName(String name)
	{
		mAnature.setName(name);
		return this;
	}

	public AnatureBuilder withOwnerName(String ownerName)
	{
		mAnature.setOwnerName(ownerName);
		return this;
	}

	public AnatureBuilder isShiny(boolean isShiny)
	{
		mAnature.setIsShiny(isShiny);
		return this;
	}

	public AnatureBuilder withSpecies(Species species)
	{
		mAnature.setSpecies(species);
		return this;
	}

	public AnatureBuilder withGender(Gender gender)
	{
		mAnature.setGender(gender);
		return this;
	}

	public AnatureBuilder withPrimaryType(Type primaryType)
	{
		mAnature.setPrimaryType(primaryType);
		return this;
	}

	public AnatureBuilder withSecondaryType(Type secondaryType)
	{
		mAnature.setSecondaryType(secondaryType);
		return this;
	}

	public AnatureBuilder withMoveSet(MoveSet moveSet)
	{
		mAnature.setMoveSet(moveSet);
		return this;
	}

	public AnatureBuilder withAbility(IAbility ability)
	{
		mAnature.setAbility(ability);
		return this;
	}

	public AnatureBuilder withStatus(StatusEffects statusEffect)
	{
		mAnature.setStatus(statusEffect);
		return this;
	}

	public AnatureBuilder withStats(IStats stats)
	{
		mAnature.setStats(stats);
		return this;
	}

	public AnatureBuilder withIndexNumber(int indexNumber)
	{
		mAnature.setIndexNumber(indexNumber);
		return this;
	}

	private AnatureBuilder withCatchRate(int catchRate)
	{
		mAnature.setCatchRate(catchRate);
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

	private static Random randomObject = new Random();

	public static Anature createAnature(Species species, int level)
	{
		String name = species.toString()
				.replaceAll("_", " ");
		String ownerName = Startup.getPlayerName();
		Type[] types = new Type[]
		{ Type.NotSet, Type.NotSet };

		String possibleAbilitiesString = "";
		String typesString = "";
		String indexNumberString = "";
		String levelingSpeedString = "";

		String baseExperienceString = "";
		String baseHitPointsString = "";
		String baseAttackString = "";
		String baseDefenseString = "";
		String baseSpecialAttackString = "";
		String baseSpecialDefenseString = "";
		String baseSpeedString = "";
		String baseAccuracyString = "";
		String baseEvasionString = "";
		String catchRateString = "";

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
				indexNumberString = results.getString("IndexNumber");
				levelingSpeedString = results.getString("LevelingSpeed");

				baseExperienceString = results.getString("BaseExperience");
				baseHitPointsString = results.getString("BaseHitPoints");
				baseAttackString = results.getString("BaseAttack");
				baseDefenseString = results.getString("BaseDefense");
				baseSpecialAttackString = results.getString("BaseSpecialAttack");
				baseSpecialDefenseString = results.getString("BaseSpecialDefense");
				baseSpeedString = results.getString("BaseSpeed");
				baseAccuracyString = results.getString("BaseAccuracy");
				baseEvasionString = results.getString("BaseEvasion");
				catchRateString = results.getString("CatchRate");
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		types = generateTypes(typesString, types);
		int indexNumber = Integer.parseInt(indexNumberString);

		int baseExperience = Integer.parseInt(baseExperienceString);
		int baseHitPoints = Integer.parseInt(baseHitPointsString);
		int baseAttack = Integer.parseInt(baseAttackString);
		int baseDefense = Integer.parseInt(baseDefenseString);
		int baseSpecialAttack = Integer.parseInt(baseSpecialAttackString);
		int baseSpecialDefense = Integer.parseInt(baseSpecialDefenseString);
		int baseSpeed = Integer.parseInt(baseSpeedString);
		int baseAccuracy = Integer.parseInt(baseAccuracyString);
		int baseEvasion = Integer.parseInt(baseEvasionString);
		int catchRate = Integer.parseInt(catchRateString);

		return new AnatureBuilder().withName(name)
				.withOwnerName(ownerName)
				.isShiny(generateIsShiny())
				.withSpecies(species)
				.withGender(generateGender())
				.withPrimaryType(types[0])
				.withSecondaryType(types[1])
				.withMoveSet(generateMoveSet(species, level))
				.withAbility(generateAbility(possibleAbilitiesString))
				.withStatus(StatusEffects.None)
				.withCatchRate(catchRate)
				.withStats(new StatsBuilder().atLevel(level)
						.withLevlingSpeed(generateLevelingSpeed(levelingSpeedString))
						.withNature(generateRandomNature())
						.withBaseExperience(baseExperience)
						.withBaseHitPoints(baseHitPoints)
						.withBaseAttack(baseAttack)
						.withBaseDefense(baseDefense)
						.withBaseSpecialAttack(baseSpecialAttack)
						.withBaseSpecialDefense(baseSpecialDefense)
						.withBaseSpeed(baseSpeed)
						.withBaseAccuracy(baseAccuracy)
						.withBaseEvasion(baseEvasion)
						.withIVAttack(randomObject.nextInt(32))
						.withIVDefense(randomObject.nextInt(32))
						.withIVHitPoints(randomObject.nextInt(32))
						.withIVSpecialAttack(randomObject.nextInt(32))
						.withIVSpecialDefense(randomObject.nextInt(32))
						.withIVSpeed(randomObject.nextInt(32))
						.create())
				.withIndexNumber(indexNumber)
				.create();
	}
	
	public static Anature createEvolvedAnature(IAnature toEvolve, Species evolveInto)
	{
		Type[] types = { Type.NotSet, Type.NotSet };

		String possibleAbilitiesString = "";
		String typesString = "";
		String indexNumberString = "";
		String levelingSpeedString = "";

		String baseExperienceString = "";
		String baseHitPointsString = "";
		String baseAttackString = "";
		String baseDefenseString = "";
		String baseSpecialAttackString = "";
		String baseSpecialDefenseString = "";
		String baseSpeedString = "";
		String baseAccuracyString = "";
		String baseEvasionString = "";
		String catchRateString = "";

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureDatabase);

			String query = "Select * from Anature Where SpeciesName=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, evolveInto.toString());

			ResultSet results = pst.executeQuery();
			if(results.next())
			{
				possibleAbilitiesString = results.getString("PossibleAbilities");
				typesString = results.getString("Types");
				indexNumberString = results.getString("IndexNumber");
				levelingSpeedString = results.getString("LevelingSpeed");

				baseExperienceString = results.getString("BaseExperience");
				baseHitPointsString = results.getString("BaseHitPoints");
				baseAttackString = results.getString("BaseAttack");
				baseDefenseString = results.getString("BaseDefense");
				baseSpecialAttackString = results.getString("BaseSpecialAttack");
				baseSpecialDefenseString = results.getString("BaseSpecialDefense");
				baseSpeedString = results.getString("BaseSpeed");
				baseAccuracyString = results.getString("BaseAccuracy");
				baseEvasionString = results.getString("BaseEvasion");
				catchRateString = results.getString("CatchRate");
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		types = generateTypes(typesString, types);
		int indexNumber = Integer.parseInt(indexNumberString);

		int baseExperience = Integer.parseInt(baseExperienceString);
		int baseHitPoints = Integer.parseInt(baseHitPointsString);
		int baseAttack = Integer.parseInt(baseAttackString);
		int baseDefense = Integer.parseInt(baseDefenseString);
		int baseSpecialAttack = Integer.parseInt(baseSpecialAttackString);
		int baseSpecialDefense = Integer.parseInt(baseSpecialDefenseString);
		int baseSpeed = Integer.parseInt(baseSpeedString);
		int baseAccuracy = Integer.parseInt(baseAccuracyString);
		int baseEvasion = Integer.parseInt(baseEvasionString);
		int catchRate = Integer.parseInt(catchRateString);
		
		String name = toEvolve.getName().compareTo(toEvolve.getSpecies().toString()) == 0 ? evolveInto.toString() : toEvolve.getName();
		
		IStats stats = toEvolve.getStats().getClone()
				.withLevlingSpeed(generateLevelingSpeed(levelingSpeedString))
				.withBaseExperience(baseExperience)
				.withBaseHitPoints(baseHitPoints)
				.withBaseAttack(baseAttack)
				.withBaseDefense(baseDefense)
				.withBaseSpecialAttack(baseSpecialAttack)
				.withBaseSpecialDefense(baseSpecialDefense)
				.withBaseSpeed(baseSpeed)
				.withBaseAccuracy(baseAccuracy)
				.withBaseEvasion(baseEvasion)
				.create();

		Anature evolvedAnature = toEvolve.getClone()
				.withName(name)
				.withSpecies(evolveInto)
				.withPrimaryType(types[0])
				.withSecondaryType(types[1])
				.withAbility(generateAbility(possibleAbilitiesString))
				.withCatchRate(catchRate)
				.withIndexNumber(indexNumber)
				.withStats(stats)
				.create();

		evolvedAnature.getStats().addExperience(toEvolve.getStats().getExperienceProgression());
		return evolvedAnature;
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

				moveNameStr = moveNameStr.replace(" ", "_");
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
		String abilityStr = abilities.get(r.nextInt(abilities.size())).replace(" ", "");
		AbilityIds chosenAbility = AbilityIds.valueOf(abilityStr);

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

	private static LevelingSpeed generateLevelingSpeed(String levelingSpeedString)
	{
		return LevelingSpeed.valueOf(levelingSpeedString);
	}

	private static Natures generateRandomNature()
	{
		ArrayList<Natures> natureList = Natures.getNatureList();

		return natureList.get(randomObject.nextInt(natureList.size()));
	}

	private static boolean generateIsShiny()
	{
		Random r = new Random();

		return r.nextInt(4200) == 420;
	}

}