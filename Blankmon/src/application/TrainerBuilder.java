package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.enums.AbilityIds;
import application.enums.AiIds;
import application.enums.DatabaseType;
import application.enums.Gender;
import application.enums.MoveIds;
import application.enums.Species;
import application.enums.TrainerIds;
import application.enums.Type;

public class TrainerBuilder
{
	public Trainer createTrainer(TrainerIds id, int anatureCount, int minLevel, int maxLevel)
	{
		ArrayList<Item> items = new ArrayList<Items>(); ;
		ArrayList<Anature> party = new ArrayList<Anature>(); ;
		BaseAI ai;
		int switchThreshold, healthThreshold;
		String name;
		
		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.TrainerDatabase);

			String query = "Select * from Trainers Where TrainerID=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, id.toString());

			ResultSet results = pst.executeQuery();
			if (results.next())
			{
				String itemsStr = results.getString("ItemList");
				String partyStr = results.getString("Anatures");
				String aiStr = results.getString("AI_ID");
				String switchStr = results.getString("SwitchThreshold");
				String healthStr = results.getString("HealthThreshold");
				name = results.getString("Name");

				if(itemsStr.length() != 0)
				{
					ArrayList<String> itemsStrAra = new ArrayList<String>(Arrays.asList(itemsStr.split(",")));
					// TODO use Item pool or generate items based on id.
				}

				ArrayList<String> partyStrAra = new ArrayList<String>(Arrays.asList(partyStr.split(",")));
				AnatureBuilder builder = new AnatureBuilder();
				Random levelGen = new Random();
				
				for(String anatureStr : partyStrAra)
				{
					int level = levelGen.nextInt(maxLevel - minLevel) + minLevel;
					party.add(builder.createAnature(Species.valueOf(anatureStr), level));
				}
				
				ai = AiPool.getAi(AiIds.valueOf(aiStr));
				
				switchThreshold = Integer.parseInt(switchStr);
				healthThreshold = Integer.parseInt(healthStr);				
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		return new Trainer(party, items, party.get(0), ai);
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
				String moveNameStr = results.getString("MoveName");

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