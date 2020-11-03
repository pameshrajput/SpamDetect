package spam;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DB_Connect
{
	public static Connection con=null;
	public static Connection getConnect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/spam_detection","root","");
			JOptionPane.showMessageDialog(null,"Connected Successfully");
			return con;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		return null;
		
		
	}

}
