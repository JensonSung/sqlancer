package sqlancer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sqlancer.sqlite3.schema.SQLite3Schema.SQLite3Table;

public class DatabaseFacade {
	
	private DatabaseFacade() {
	}

	public static Connection createDatabase(String fileName) throws SQLException {
		File dataBase = new File("." + File.separator + "databases", fileName + ".db");
		if (dataBase.exists()) {
			dataBase.delete();
		}
		String url = "jdbc:sqlite:" + dataBase.getAbsolutePath();
		return DriverManager.getConnection(url);
	}

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/employees?serverTimezone=UTC";
		return DriverManager.getConnection(url, "lama", "lamalama123!");
	}

	public static String queryStringToGetRandomTableRow(SQLite3Table table) {
		return String.format("SELECT %s, %s FROM %s ORDER BY RANDOM() LIMIT 1", table.getColumnsAsString(),
				table.getColumnsAsString(c -> "typeof(" + c.getName() + ")"), table.getName());
	}

}
