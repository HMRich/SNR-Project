package application;

import java.sql.Connection;
import java.sql.DriverManager;

import application.enums.DatabaseType;

public class DatabaseConnection
{
	public static Connection dbConnector(DatabaseType type)
	{
		if(type == null)
			throw new IllegalArgumentException("Null Database Type");

		try
		{
			String firstLetter = type.toString().substring(0, 1).toLowerCase();
			String databaseName = firstLetter + type.toString().substring(1);

			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager
					.getConnection("jdbc:sqlite::resource:" + DatabaseConnection.class.getResource("/resources/databases/" + databaseName + ".sqlite"));

			return connect;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
