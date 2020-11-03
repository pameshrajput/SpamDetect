package spam;
import java.util.Random;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
public class spam1 extends Sam {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args)
	{
		
	    
		java.sql.Connection conn=DB_Connect.getConnect();//
		String data="death sample died";
		String sample[]=data.split(" ");	//here we seprated a sentense to using spaces for we get individual word 
		int total_word=0,span_word=0,intensity=0;
		total_word=sample.length;//here we calculate the total word present in sentence
		String send = ""; // here we creted the string  for joint
		for(int i=0;i<sample.length;i++)
		{
			try
			{ 
				PreparedStatement pst=(PreparedStatement) conn.prepareStatement("Select * from add_spam where Spam_Word=?");
				//preparedStatement is subinteface of Statment is used for parametezed query
				//imporve perfocmce --> if we use PS --> the query compile only once
				//
				
				pst.setString(1, sample[i]);//sample[i] -> here we provide the query for executinon
				//Create the resultset object
				ResultSet rs=pst.executeQuery();//it is return the instace of resultset
				while(rs.next())
				{
					
					//System.out.println("Word===>"+sample[i]+"Intensity===>"+rs.getString(3));
					send=""+rs.getString(3)+","+send;//
					intensity=intensity+Integer.parseInt(rs.getString(3));
					
					System.out.println("INTESITY"+rs.getString(3));
				}
				
			}
			catch(Exception e)
			
			{
				System.out.print(e);
			}
		}
		System.out.println("TOTAL WORDS: "+total_word+"\nSpam Word :"+span_word+"Intensity :"+intensity);	
		int a=total_word*100;
		int l=a/intensity ;
		System.out.println("l="+l);
		try
		{
			Random rand=new Random();
			java.sql.PreparedStatement ps=conn.prepareStatement("insert into Temp values(?,?)");
			ps.setInt(1, rand.nextInt(500));
			ps.setString(2, send);
			System.out.println(""+ps);
			ps.execute();
			JOptionPane.showMessageDialog(null, "Data Saved....1");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
