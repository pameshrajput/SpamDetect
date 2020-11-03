package spam;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Post extends JInternalFrame {
	String x;
	
	private JTextField textField;
	private JTextField txtNearby;
	private JPanel panel;
	JTextField yourInputField = new JTextField(16);
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Post frame = new Post();
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
	public Post() {
		setClosable(true);
		setBounds(100, 100, 544, 478);
		
		panel = new JPanel();
		panel.add(yourInputField);
		panel.setBackground(new Color(244, 164, 96));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(25, 54, 233, 218);
		panel.add(textField);
		textField.setColumns(10);
		x = textField.getText();
		
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
				
				panel.add(textField_1);
				textField_1.setText(x);
				JOptionPane.showMessageDialog(null, "Data Saved Succesfully");
				
				}
				catch (Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Data Not Save	 successfully");
					
				}
			}
		});
		btnVerify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVerify.setBounds(221, 318, 89, 23);
		panel.add(btnVerify);
		
		txtNearby = new JTextField();
		txtNearby.setText("Twitter/Facebook");
		txtNearby.setBounds(39, 387, 102, 20);
		panel.add(txtNearby);
		txtNearby.setColumns(10);
		
		JButton btnPost = new JButton("POST");
		btnPost.setBounds(368, 373, 89, 23);
		panel.add(btnPost);
		
		textField_1 = new JTextField();
		textField_1.setBounds(329, 110, 112, 119);
		panel.add(textField_1);
		textField_1.setColumns(10);

	}
}
