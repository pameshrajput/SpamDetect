package spam;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.Label;
import javax.swing.JLabel;

public class Sam extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JButton btnNewButton = new JButton("View Graph");
	private final JEditorPane editorPane = new JEditorPane();
	private final JLabel lblin = new JLabel("New label");

	
	private final JLabel wo = new JLabel("l");
	private final JLabel per = new JLabel("New label");
	
	
	int mainid=0;

	/**
	 * Launch the application.
	 */
	
	
	
	
	
	public static void main(String[] args) {
		
		 
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sam frame = new Sam();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sam() {
		initGUI();
	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel.setBackground(Color.PINK);
		
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		btnNewButton.addActionListener(new ActionListener() {
			
			
			void add(int mainid)
			{
				
			 if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) 
				{
				    try {
						Desktop.getDesktop().browse(new URI("http://localhost:8080/sample.php?id="+mainid));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				String x=editorPane.getText();
				System.out.println(""+x);
				java.sql.Connection conn=DB_Connect.getConnect();
				//String data="death sample died";
				String sample[]=x.split(" ");
				
				int total_word=0,span_word=0,intensity=0;
				total_word=sample.length;
				String send = "";
				for(int i=0;i<sample.length;i++)
				{
					try
					{ 
						PreparedStatement pst=(PreparedStatement) conn.prepareStatement("Select * from add_spam where Spam_Word=?");
						pst.setString(1, sample[i]);
						java.sql.ResultSet rs=pst.executeQuery();
						while(rs.next())
						{
							
							//System.out.println("Word===>"+sample[i]+"Intensity===>"+rs.getString(3));
							send=""+rs.getString(3)+","+send;
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
				lblin.setText("Intensity"+intensity);
				wo.setText("Total Words"+total_word);
				
				int a=total_word*100;
				float l=a/intensity ;
				per.setText("percentage"+l);
				System.out.println("l="+l);
				try
				{
					Random rand=new Random();
					mainid=rand.nextInt(500);
					
					java.sql.PreparedStatement ps=conn.prepareStatement("insert into SVM values(?,?)");
					ps.setInt(1, mainid);
					
					add(mainid);
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
		});
		btnNewButton.setBounds(208, 192, 89, 23);
		
		panel.add(btnNewButton);
		editorPane.setBounds(128, 11, 190, 66);
		
		panel.add(editorPane);
		lblin.setForeground(Color.BLACK);
		lblin.setBackground(Color.WHITE);
		lblin.setBounds(28, 95, 144, 28);
		
		panel.add(lblin);
		wo.setForeground(Color.BLACK);
		wo.setBackground(Color.WHITE);
		wo.setBounds(28, 134, 172, 32);
		
		panel.add(wo);
		per.setForeground(Color.BLACK);
		per.setBackground(Color.WHITE);
		per.setBounds(28, 177, 144, 38);
		
		panel.add(per);
		
		
		
	}
}
