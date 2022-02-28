package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *	@author Bosko Gogic
 *	@version java 1.8
 *
 *	Class which I use to create and save User mail and password in database.
 */

public class UserDAO {
	Connection c = null;
	Statement s = null;
	PreparedStatement ps = null;
	
	public void writeInDataBase(int id, String userEmail, String password) {
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/signUpForma", "root", "root");
			s = c.createStatement();
			
			ps = c.prepareStatement("insert into USER values (?, ?, ?)");
			ps.setInt(1, id );
			ps.setString(2, userEmail);
			ps.setString(3, password);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally { 
			
			if(ps != null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(c != null) {
				try{
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
