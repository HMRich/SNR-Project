package application.trainers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import application.Builder;
import application.DatabaseConnection;
import application.TypeAdvantage;
import application.anatures.Anature;
import application.anatures.AnatureBuilder;
import application.controllers.LoggerController;
import application.enums.AttackEffectiveness;
import application.enums.DatabaseType;
import application.enums.ItemIds;
import application.enums.LoggingTypes;
import application.enums.Species;
import application.enums.TrainerIds;
import application.items.HealthPotion;
import application.pools.ItemPool;
import application.trainers.ai.AI;
import application.trainers.ai.AIBuilder;

public class TrainerBuilder implements Builder<Trainer>
{
	private Trainer mTrainer;

	public TrainerBuilder()
	{
		genertaeNewTrainer();
	}

	/*
	 * PUBLIC SETS
	 */

	public TrainerBuilder setTrainerId(TrainerIds trainerId)
	{
		mTrainer.setTrainerId(trainerId);
		return this;
	}

	public TrainerBuilder setTrainerName(String name)
	{
		mTrainer.setTrainerName(name);
		return this;
	}

	public TrainerBuilder setAnatureParty(ArrayList<Anature> anatureParty)
	{
		mTrainer.setAnatureParty(anatureParty);
		return this;
	}

	public TrainerBuilder setHealthPotions(ArrayList<HealthPotion> healthPotions)
	{
		mTrainer.setHealthPotions(healthPotions);
		return this;
	}

	public TrainerBuilder setCurrentAnature(Anature currentAnature)
	{
		mTrainer.setCurrentAnature(currentAnature);
		return this;
	}

	public TrainerBuilder setAI(AI ai)
	{
		mTrainer.setAI(ai);
		return this;
	}

	/*
	 * PUBLIC METHODS
	 */

	public Trainer create()
	{
		if(!buildIsComplete())
		{
			throw new IllegalStateException("All the builder variables need to have a value before you create a Trainer.");
		}

		Trainer trainerToReturn = mTrainer;

		genertaeNewTrainer();

		return trainerToReturn;
	}

	/*
	 * STATIC PUBLIC METHODS
	 */

	public static Trainer createTrainer(TrainerIds id, int anatureCount, int minLevel, int maxLevel)
	{
		String trainerName = "<Null>";
		String partyList = "<Null>";
		String itemsList = "<Null>";
		String aiHealthThreshold = "<Null>";
		String aiSwitchThreshold = "<Null>";
		String aiMoveThreshold = "<Null>";

		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.TrainerDatabase);

			String query = "Select * from Trainers Where TrainerID=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, id.toString());

			ResultSet results = pst.executeQuery();
			if(results.next())
			{
				trainerName = results.getString("Name");
				partyList = results.getString("Anatures");
				itemsList = results.getString("ItemList");
				aiHealthThreshold = results.getString("HealthThreshold");
				aiSwitchThreshold = results.getString("SwitchThreshold");
				aiMoveThreshold = results.getString("MoveThreshold");
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}

		ArrayList<Anature> party = parsePartyList(partyList, anatureCount, minLevel, maxLevel);
		ArrayList<HealthPotion> potions = parsePotionList(itemsList);
		AI ai = parseAi(aiHealthThreshold, aiSwitchThreshold, aiMoveThreshold);

		return new TrainerBuilder().setTrainerId(id)
				.setTrainerName(trainerName)
				.setAnatureParty(party)
				.setHealthPotions(potions)
				.setCurrentAnature(party.get(0))
				.setAI(ai)
				.create();
	}

	/*
	 * PRIVATE METHODS
	 */

	private void genertaeNewTrainer()
	{
		mTrainer = new Trainer();
	}

	private boolean buildIsComplete()
	{
		return mTrainer.canComplete();
	}

	/*
	 * STATIC PRIVATE METHODS
	 */

	private static ArrayList<HealthPotion> parsePotionList(String itemsString)
	{
		ArrayList<HealthPotion> items = new ArrayList<HealthPotion>();

		if(itemsString.length() != 0)
		{
			ArrayList<String> itemsStrAra = new ArrayList<String>(Arrays.asList(itemsString.split(",")));

			for(String itemId : itemsStrAra)
			{
				try
				{
					HealthPotion toAdd = ItemPool.getHealthPotion(ItemIds.valueOf(itemId)); // TODO redo ItemPool.java file to decouple

					if(toAdd == null)
					{
						LoggerController.logEvent(LoggingTypes.Error, "Trainer Builder tried to retrieve an item with an invalid Id.");
					}

					else
					{
						items.add(toAdd);
					}
				}

				catch(Exception e)
				{
					LoggerController.logEvent(LoggingTypes.Error, "Trainer Builder tried to retrieve an item with an invalid Id.");
				}
			}
		}

		return items;
	}

	private static ArrayList<Anature> parsePartyList(String partyString, int anatureCount, int anatureMinLevel, int anatureMaxLevel)
	{
		ArrayList<Anature> party = new ArrayList<Anature>();

		ArrayList<String> partyStrAra = new ArrayList<String>(Arrays.asList(partyString.split(",")));
		Random levelGen = new Random();

		for(String anatureStr : partyStrAra)
		{
			int level = levelGen.nextInt(anatureMaxLevel - anatureMinLevel) + anatureMinLevel;
			party.add(AnatureBuilder.createAnature(Species.valueOf(anatureStr), level));
		}

		return party;
	}

	private static AI parseAi(String aiHealthThreshold, String aiSwitchThreshold, String aiMoveThreshold)
	{
		double healthThreshold = Double.parseDouble(aiHealthThreshold);
		AttackEffectiveness switchThreshold = TypeAdvantage.parseInt(Integer.parseInt(aiSwitchThreshold));
		AttackEffectiveness moveThreshold = TypeAdvantage.parseInt(Integer.parseInt(aiMoveThreshold));

		return new AIBuilder().setHealthThreshold(healthThreshold)
				.setSwitchThreshold(switchThreshold)
				.setMoveThreshold(moveThreshold)
				.create();
	}

}