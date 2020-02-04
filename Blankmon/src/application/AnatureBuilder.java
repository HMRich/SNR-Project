package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.enums.AbilityIds;
import application.enums.DatabaseType;
import application.enums.Gender;
import application.enums.MoveIds;
import application.enums.Species;
import application.enums.Type;

public class AnatureBuilder
{
	public Anature createAnature(Species species, int level)
	{
		MoveSet moves = generateMoveSet(species, level);
		Gender gender = null;
		Type[] types = new Type[2];
		boolean isShiny = false;
		Ability ability = null;
		int attack = 0, specialAttack = 0, defense = 0, specialDefense = 0, hp = 0;

		Random r = new Random();

		if (r.nextInt(2) == 0)
			gender = Gender.Male;

		else
			gender = Gender.Female;

		if (r.nextInt(4200) == 420)
			isShiny = true;

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureDatabase);

			String query = "Select * from Pokemon Where SpeciesName=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, species.toString());

			ResultSet results = pst.executeQuery();
			if (results.next())
			{
				String hpStr = results.getString("BaseHp");
				String atkStr = results.getString("BaseAttack");
				String spAtkStr = results.getString("BaseSpecialAttack");
				String defStr = results.getString("BaseDefense");
				String spDefStr = results.getString("BaseSpecialDefense");
				String abilitiesStr = results.getString("PossibleAbilities");
				String typesStr = results.getString("Types");

				hp = Integer.parseInt(hpStr);
				attack = Integer.parseInt(atkStr);
				specialAttack = Integer.parseInt(spAtkStr);
				defense = Integer.parseInt(defStr);
				specialDefense = Integer.parseInt(spDefStr);

				ArrayList<String> abilities = new ArrayList<String>(Arrays.asList(abilitiesStr.split(",")));
				AbilityIds chosenAbility = AbilityIds.valueOf(abilities.get(r.nextInt(abilities.size())));
				ability = AbilityPool.getAbility(chosenAbility);
				
				String[] typesStrAra = typesStr.split(",");
				for(int i = 0; i < typesStrAra.length && i < 2; i++)
					types[i] = Type.valueOf(typesStrAra[i]);
				
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return new Anature(species.toString(), "TODO PLAYER NAME HERE", level, 0, gender, moves, types, species, isShiny, ability,
				attack, specialAttack, defense, specialDefense, hp);
	}

	private MoveSet generateMoveSet(Species species, int level)
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
				String moveNameStr = results.getString("moveName");

				int minLevel = Integer.parseInt(minLevelStr);

				if (minLevel > level)
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

		if (availableMoves.size() <= 4)
		{
			if (availableMoves.size() >= 1)
				move1 = MovePool.getMove(availableMoves.get(0));

			if (availableMoves.size() >= 2)
				move2 = MovePool.getMove(availableMoves.get(1));

			if (availableMoves.size() >= 3)
				move3 = MovePool.getMove(availableMoves.get(2));

			if (availableMoves.size() >= 4)
				move4 = MovePool.getMove(availableMoves.get(3));
		}

		else
		{
			int chosenIndex1 = 0, chosenIndex2 = 0, chosenIndex3 = 0, chosenIndex4 = 0;

			chosenIndex1 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex1, chosenIndex2, chosenIndex3,
					chosenIndex4);
			chosenIndex2 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex2, chosenIndex1, chosenIndex3,
					chosenIndex4);
			chosenIndex3 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex3, chosenIndex2, chosenIndex1,
					chosenIndex4);
			chosenIndex4 = generateRandomUniqueIndex(availableMoves.size(), chosenIndex4, chosenIndex2, chosenIndex1,
					chosenIndex3);

			move1 = MovePool.getMove(availableMoves.get(chosenIndex1));
			move2 = MovePool.getMove(availableMoves.get(chosenIndex2));
			move3 = MovePool.getMove(availableMoves.get(chosenIndex3));
			move4 = MovePool.getMove(availableMoves.get(chosenIndex4));
		}

		return new MoveSet(move1, move2, move3, move4);
	}

	private int generateRandomUniqueIndex(int max, int toGenerate, int otherIndex1, int otherIndex2, int otherIndex3)
	{
		Random r = new Random();
		toGenerate = r.nextInt(max);

		while(toGenerate == otherIndex1 || toGenerate == otherIndex2 || toGenerate == otherIndex3)
			toGenerate = r.nextInt(max);

		return toGenerate;
	}
}