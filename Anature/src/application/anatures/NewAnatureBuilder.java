package application.anatures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.DatabaseConnection;
import application.anatures.movesets.MoveSet;
import application.anatures.stats.StatsBuilder;
import application.enums.AbilityIds;
import application.enums.DatabaseType;
import application.enums.Gender;
import application.enums.MoveIds;
import application.enums.Species;
import application.enums.Type;
import application.enums.stats.LevelingSpeed;
import application.enums.stats.Natures;
import application.interfaces.IAbility;
import application.interfaces.IAnature;
import application.interfaces.IMove;
import application.pools.AbilityPool;
import application.pools.MovePool;

public class NewAnatureBuilder
{
	private static NewAnatureTestable mAnatureInstance = new NewAnatureTestable();
	private static Random randomObject = new Random();

	public static IAnature createInnerAnature(String playerName, Species species, int level)
	{
		return mAnatureInstance.new NewAnature(mAnatureInstance.new AnatureVariables()
		{
			@Override
			public void getContext()
			{
				anatureName = anatureSpecies.toString();
				anatureOwnerName = playerName;
				anatureIsShiny = generateIsShiny();
				anatureSpecies = species;
				anatureGender = generateGender();

				try
				{
					Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureDatabase);

					String query = "Select * from Anature Where SpeciesName=?";
					PreparedStatement pst = connect.prepareStatement(query);
					pst.setString(1, species.toString());

					ResultSet results = pst.executeQuery();
					if(results.next())
					{
						Type[] types = generateTypes(results.getString("Types"));
						anaturePrimaryType = types[0];
						anatureSecondaryType = types[1];

						anatureMoveSet = generateMoveSet(species, level);
						anatureAbility = generateAbility(results.getString("PossibleAbilities"));

						anatureIndexNumber = Integer.parseInt(results.getString("IndexNumber"));
						anatureCatchRate = Integer.parseInt(results.getString("CatchRate"));

						anatureStats = new StatsBuilder().atLevel(level)
								.withLevlingSpeed(generateLevelingSpeed(results.getString("LevelingSpeed")))
								.withNature(generateRandomNature())
								.withBaseExperience(Integer.parseInt(results.getString("BaseExperience")))
								.withBaseHitPoints(Integer.parseInt(results.getString("BaseHitPoints")))
								.withBaseAttack(Integer.parseInt(results.getString("BaseAttack")))
								.withBaseDefense(Integer.parseInt(results.getString("BaseDefense")))
								.withBaseSpecialAttack(Integer.parseInt(results.getString("BaseSpecialAttack")))
								.withBaseSpecialDefense(Integer.parseInt(results.getString("BaseSpecialDefense")))
								.withBaseSpeed(Integer.parseInt(results.getString("BaseSpeed")))
								.withBaseAccuracy(Integer.parseInt(results.getString("BaseAccuracy")))
								.withBaseEvasion(Integer.parseInt(results.getString("BaseEvasion")))
								.withIVAttack(randomObject.nextInt(32))
								.withIVDefense(randomObject.nextInt(32))
								.withIVHitPoints(randomObject.nextInt(32))
								.withIVSpecialAttack(randomObject.nextInt(32))
								.withIVSpecialDefense(randomObject.nextInt(32))
								.withIVSpeed(randomObject.nextInt(32))
								.create();
					}

					results.close();
					pst.close();
				}

				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public static IAnature createInnerAnature(IAnature toEvolve, Species evolveInto)
	{
		return mAnatureInstance.new NewAnature(mAnatureInstance.new AnatureVariables()
		{
			@Override
			public void getContext()
			{
				String name = toEvolve.getName()
						.compareTo(toEvolve.getSpecies()
								.toString()) == 0 ? evolveInto.toString() : toEvolve.getName();

				anatureName = name;
				anatureOwnerName = toEvolve.getOwner();
				anatureIsShiny = toEvolve.isShiny();
				anatureSpecies = evolveInto;
				anatureGender = toEvolve.getGender();

				try
				{
					Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureDatabase);

					String query = "Select * from Anature Where SpeciesName=?";
					PreparedStatement pst = connect.prepareStatement(query);
					pst.setString(1, evolveInto.toString());

					ResultSet results = pst.executeQuery();
					if(results.next())
					{
						Type[] types = generateTypes(results.getString("Types"));
						anaturePrimaryType = types[0];
						anatureSecondaryType = types[1];

						anatureMoveSet = toEvolve.getMoveSet();
						anatureAbility = generateAbility(results.getString("PossibleAbilities"));

						anatureIndexNumber = Integer.parseInt(results.getString("IndexNumber"));
						anatureCatchRate = Integer.parseInt(results.getString("CatchRate"));

						anatureStats = toEvolve.getStats()
								.getClone()
								.atLevel(toEvolve.getStats()
										.getLevel())
								.withLevlingSpeed(generateLevelingSpeed(results.getString("LevelingSpeed")))
								.withBaseExperience(Integer.parseInt(results.getString("BaseExperience")))
								.withBaseHitPoints(Integer.parseInt(results.getString("BaseHitPoints")))
								.withBaseAttack(Integer.parseInt(results.getString("BaseAttack")))
								.withBaseDefense(Integer.parseInt(results.getString("BaseDefense")))
								.withBaseSpecialAttack(Integer.parseInt(results.getString("BaseSpecialAttack")))
								.withBaseSpecialDefense(Integer.parseInt(results.getString("BaseSpecialDefense")))
								.withBaseSpeed(Integer.parseInt(results.getString("BaseSpeed")))
								.withBaseAccuracy(Integer.parseInt(results.getString("BaseAccuracy")))
								.withBaseEvasion(Integer.parseInt(results.getString("BaseEvasion")))
								.create();
					}

					results.close();
					pst.close();
				}

				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
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

		while(toGenerate == otherIndex1
				|| toGenerate == otherIndex2
				|| toGenerate == otherIndex3)
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
		String abilityStr = abilities.get(r.nextInt(abilities.size()))
				.replace(" ", "");
		AbilityIds chosenAbility = AbilityIds.valueOf(abilityStr);

		return AbilityPool.getAbility(chosenAbility);
	}

	private static Type[] generateTypes(String typesString)
	{
		Type[] types = new Type[] { Type.NotSet, Type.NotSet };
		String[] typesStringArray = typesString.split(",");

		for(int i = 0; i < typesStringArray.length
				&& i < 2; i++)
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
