package spam;

import java.awt.BorderLayout;



import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.Vector;
import java.awt.TextField;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Kmeans extends JFrame  {
	java.sql.Connection conn=DB_Connect.getConnect();
	private JPanel contentPane;
	private JTable jtable30;
	
	private JTable jtable60;
	private JTable jtable90;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kmeans frame = new Kmeans();
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
	public Kmeans() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final TextArea textArea = new TextArea();
		textArea.setFont(new Font("Arabic Typesetting", Font.BOLD, 16));
		textArea.setBackground(new Color(30, 144, 255));
		
		Button Apply = new Button("Apply");
		Apply.setBackground(Color.CYAN);
		Apply.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				
				String data= textArea.getText();
				System.out.println(""+data);
				String sample[]=data.split(" ");
				
				DefaultTableModel model1 = new DefaultTableModel();
				model1= (DefaultTableModel) jtable30.getModel();
				model1.getDataVector().removeAllElements();	
				
				DefaultTableModel model2 = new DefaultTableModel();
				model2= (DefaultTableModel) jtable60.getModel();
				model2.getDataVector().removeAllElements();	
				
				DefaultTableModel model3 = new DefaultTableModel();
				model3= (DefaultTableModel) jtable90.getModel();
				model3.getDataVector().removeAllElements();
				
				int total_word=0,span_word=0,intensity=0;
				int ans=0;
				int Tot_Intensity=0;
				
				int min=0,max=0;
				 int minnew=0;
				int maxnew=0;
				total_word=sample.length;
				String send = "";
				int Tot_Word;
				int i;
				 int n1 = 0;
					int n2=0;
					int n3=0;
				
				for(int i1=0;i1<sample.length;i1++)		//Outer loop for Comparison		
				{
					if(sample[i1]!=null)
					{
					
					for(int j=i1+1;j<sample.length;j++)	//Inner loop for Comparison
					{
						
					if(sample[i1].equals(sample[j]))	//Checking for both strings are equal
						{
							sample[j]=null;			//Delete the duplicate words
						}
					}
					}
				}
				for(int k=0;k<sample.length;k++)		//Displaying the String without duplicate words	
				{
					if(sample[k]!=null)
					{
						System.out.println(sample[k]);
					}
				}	
				
				
				
				
			    				
				for( i=0;i<sample.length;i++)
				{
					try
					{ 
						
						PreparedStatement pst=(PreparedStatement) conn.prepareStatement("Select * from add_spam where Spam_Word=?");
						pst.setString(1, sample[i]);
						
						java.sql.ResultSet rs=pst.executeQuery();
						
						boolean isSelected = true;
						 boolean hasFocus = false;
						  
							
						while(rs.next())
						{   
							
							
							System.out.println(""+rs.getString(2));
							System.out.println(""+rs.getString(3));
							
						    /*ar[j]=(String)rs.getString(2);
							j++;
							for(int k=0;k<=ar.length;k++)
							{
								if(ar[k].equalsIgnoreCase(rs.getString(2)))
								{
									System.out.println("he");
								}
								
								else
								{
									
								}
								
								
								
							}*/
							
							String arg[]= {Integer.toString(i+1),rs.getString(2),rs.getString(3)};
							
							
							
							
							intensity=Integer.parseInt(rs.getString(3));
							
							if(intensity<30 )
							{
								 
								 String arg1[]= {Integer.toString(n1+1),rs.getString(2),rs.getString(3)};
								 n1=n1+1;
							model1.addRow(arg1);
							int row = jtable30.getRowCount();
							int column=2;
							System.out.println("ROW"+row);
							System.out.println("column"+column);
							jtable30.setBackground(Color.green);
							}
							if(intensity>30 && intensity<=60)
							{
								
								 String arg2[]= {Integer.toString(n2+1),rs.getString(2),rs.getString(3)};
								 n2=n2+1;
								model2.addRow(arg2);
								int row = jtable60.getRowCount();
								int column=2;
								System.out.println("ROW"+row);
								System.out.println("column"+column);
								jtable60.setBackground(Color.orange);
							}
							//jtable30.setForeground(Color.orange);
							if(intensity<=90 && intensity>60)
							{   
							 String arg3[]= {Integer.toString(n3+1),rs.getString(2),rs.getString(3)};
								//jtable30.setForeground(Color.red);
							    n3=n3+1;
								model3.addRow(arg3);
								int row = jtable90.getRowCount();
								int column=2;
								System.out.println("ROW"+row);
								System.out.println("column"+column);
								jtable90.setBackground(Color.red);
								
							}
							
						
							
							
							
							
							
							
							//System.out.println("Word===>"+sample[i]+"Intensity===>"+rs.getString(3));
							send=""+rs.getString(3)+","+send;
							System.out.println(""+intensity);
							
							Tot_Intensity=Tot_Intensity+Integer.parseInt(rs.getString(3));
							if(intensity>=60)
							{
								minnew=60 ;
								maxnew=90 ;
							}
							
							else if(intensity>=30 && intensity<=59)
							{
								minnew=30;
								maxnew= 59;
							}
							else if(intensity>=1 && intensity<=29)
							{
								minnew=1;
								maxnew= 29;
							}
							max=max+maxnew;
							min=min+minnew;
							System.out.println("Max"+max);
							System.out.println("Min"+min);
							System.out.println("Original "+rs.getString(3));
							
			
							//System.out.println("INTENSITY"+rs.getString(3));
							
							if(sample[i]==rs.getString(2))
							{
							System.out.println(""+sample[i]);
							}
							
				
							
							
							
						}
						
						
						
					}
					catch(Exception e)
					
					{
						System.out.print(e);
					}
 	
				}
				
				
				
				
				System.out.println("Total Intensity"+Tot_Intensity);
				ans=Tot_Intensity/sample.length;
              
				System.out.println("Answer"+ans);
				
				int x=max-min;
				Tot_Word=x/sample.length;
				System.out.println("Tot_Word"+Tot_Word);
				if(Tot_Intensity<=30)
				{
					textArea.setForeground(Color.red);
					//textArea.setBackground(Color.red);
				}
				
				
				}
			
		});
		
		JLabel label = new JLabel("");
		label.setBackground(new Color(135, 206, 235));
		label.setForeground(new Color(244, 164, 96));
		
		scrollPane = new JScrollPane();
		
		jtable30 = new JTable();
		scrollPane.setViewportView(jtable30);
		jtable30.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Number", "Word", "Intensity"
			}
		));
		
		scrollPane1 = new JScrollPane();
		
		jtable60 = new JTable();
		jtable60.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable60.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "Word", "Intensity"
			}
		));
		scrollPane1.setViewportView(jtable60);
		
		scrollPane_1 = new JScrollPane();
		
		jtable90 = new JTable();
		jtable90.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No.", "Word", "Intensity"
			}
		));
		scrollPane_1.setViewportView(jtable90);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(179)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
					.addGap(33))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
					.addGap(36)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(901, Short.MAX_VALUE)
					.addComponent(Apply, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, GroupLayout.PREFERRED_SIZE)
					.addComponent(Apply, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		contentPane.setLayout(gl_contentPane);
	}
}


