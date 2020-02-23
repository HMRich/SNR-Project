package application.trainers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.AiPool;
import application.Anature;
import application.AnatureBuilder;
import application.BaseAI;
import application.DatabaseConnection;
import application.enums.AiIds;
import application.enums.DatabaseType;
import application.enums.Species;
import application.enums.TrainerIds;
import application.items.Item;

public class TrainerBuilder
{
	public static Trainer createTrainer(TrainerIds id, int anatureCount, int minLevel, int maxLevel)
	{
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<Anature> party = new ArrayList<Anature>(); ;
		BaseAI ai = null;
		int switchThreshold = 0, healthThreshold = 0;
		String name = "";
		
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

		return new Trainer(id, name, party, items, party.get(0), ai, healthThreshold, switchThreshold);
	}
}