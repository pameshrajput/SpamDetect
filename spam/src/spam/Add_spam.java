package spam;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import com.mysql.jdbc.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class Add_spam extends JInternalFrame {
	private JTextField txt_spam;
	private JPanel panel;
	private JTable table;
    java.sql.Connection con;
    private JRadioButton rd_red;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rd_green;
    private JRadioButton rd_orange;
	/**
	 * Launch the application.
	 */
    int var;
	public static void main(String[] args) {
	     
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_spam frame = new Add_spam();
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
	public Add_spam() {
	    
		con = DB_Connect.getConnect();
		setClosable(true);
		setBounds(100, 100, 450, 472);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSpam = new JLabel("Spam");
		lblSpam.setForeground(new Color(255, 255, 255));
		lblSpam.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpam.setBounds(51, 58, 54, 20);
		panel.add(lblSpam);
		
		
		
		
		JLabel lblIntensity = new JLabel("Intensity");
		lblIntensity.setForeground(new Color(255, 255, 255));
		lblIntensity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIntensity.setBounds(51, 116, 100, 20);
		panel.add(lblIntensity);
		
		txt_spam = new JTextField();
		txt_spam.setBounds(186, 60, 140, 20);
		panel.add(txt_spam);
		txt_spam.setColumns(10);
		
		rd_red = new JRadioButton("0-30");
		rd_red.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(rd_red.isSelected())
				{
					
					JOptionPane.showMessageDialog(null, "0-30  Selected");
					var =15;
				}
				
			}
		});
		buttonGroup.add(rd_red);
		rd_red.setForeground(new Color(255, 0, 0));
		rd_red.setFont(new Font("Tahoma", Font.BOLD, 14));
		rd_red.setBounds(189, 113, 137, 23);
		panel.add(rd_red);
		
		rd_orange = new JRadioButton("31-59");
		rd_orange.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rd_orange.isSelected())
				{
					var = 45 ;
					JOptionPane.showMessageDialog(null, "51 -0  Selected");
				}
			}
		});
		buttonGroup.add(rd_orange);
		rd_orange.setForeground(Color.ORANGE);
		rd_orange.setFont(new Font("Tahoma", Font.BOLD, 14));
		rd_orange.setBounds(189, 140, 137, 23);
		panel.add(rd_orange);
		
		rd_green = new JRadioButton("60+");
		rd_green.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rd_green.isSelected())
				{
					var = 75 ;
					JOptionPane.showMessageDialog(null, "60  Selected");
				}
			}
		});
		buttonGroup.add(rd_green);
		rd_green.setForeground(new Color(124, 252, 0));
		rd_green.setFont(new Font("Tahoma", Font.BOLD, 14));
		rd_green.setBounds(189, 167, 137, 23);
		panel.add(rd_green);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try{
					PreparedStatement pst = con.prepareStatement("insert into add_spam (Spam_Word,Intensity) values (?,?)");
					pst.setString(1,txt_spam.getText().toString() );
					pst.setInt(2, var);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data Saved Succesfully");
					
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Data Not Save	 successfully");
				}
				
			}
		});
		btnSave.setForeground(new Color(0, 0, 0));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(186, 222, 89, 23);
		panel.add(btnSave);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 253, 383, 178);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.YELLOW);
		table.setForeground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "Spam Word", "Intensity"
			}
		) 
		
		
		{
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

	}
}
