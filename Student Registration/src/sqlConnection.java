/*** created by @ahmet_Kara 
github_Link: https://github.com/ahmetQara ***/

import java.sql.*;
import javax.swing.*;

public class sqlConnection {
	
	Connection conn = null;
	/**
	 * @return
	 */
	public static Connection dbConnection() 
	
	 {
		
		// connecting database file...
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:StudentData.sqlite");

			return connection;
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
