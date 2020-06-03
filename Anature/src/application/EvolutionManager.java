package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.controllers.LoggerController;
import application.enums.DatabaseType;
import application.enums.LoggingTypes;
import application.enums.Species;
import application.interfaces.IAnature;

public class EvolutionManager
{
	public static Species checkEvolution(IAnature anature)
	{
		Species toEvolveInto = null;
		
		try
		{
			Connection connect = DatabaseConnection.dbConnector(DatabaseType.AnatureEvolutionDatabase);

			String query = "Select * from Evolution Where Anature=? AND Level<=?";
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, anature.getSpecies().toString());
			pst.setInt(2, anature.getStats().getLevel());

			ResultSet results = pst.executeQuery();
			if(results.next())
			{
				String evolveIntoString = results.getString("EvolveInto");
				toEvolveInto = Species.valueOf(evolveIntoString);
			}

			results.close();
			pst.close();
		}

		catch(SQLException e)
		{
			LoggerController.logEvent(LoggingTypes.Error, "Error occured when doing evolution check.\n" + e.getMessage());
		}
		
		return toEvolveInto;
	}
}
