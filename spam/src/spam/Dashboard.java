 package spam;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnAboutUs = new JMenu("About us");
		menuBar.add(mnAboutUs);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnAboutUs.add(mntmHelp);
		
		JSeparator separator = new JSeparator();
		mnAboutUs.add(separator);
		
		JMenuItem mntmContactUs = new JMenuItem("Contact us");
		mnAboutUs.add(mntmContactUs);
		
		JSeparator separator_1 = new JSeparator();
		mnAboutUs.add(separator_1);
		
		JMenuItem mntmDeveloper = new JMenuItem("Developer");
		mnAboutUs.add(mntmDeveloper);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSpamDetection = new JButton("Spam Detection");
		btnSpamDetection.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnSpamDetection);
		
		JButton btnPost = new JButton("Post  Detection");
		btnPost.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				panel_1.removeAll();
				Post ps=new Post();
				panel_1.repaint();
				panel_1.revalidate();
				panel_1.add(ps);
				ps.setVisible(true);
				
			}
		});
		panel.add(btnPost);
		
		JButton btnNewButton = new JButton("Twitter/Facebook");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnNewButton);
		
		JButton btnAddSpamWord = new JButton("Add Spam Word");
		btnAddSpamWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				panel_1.removeAll();
				Add_spam as=new Add_spam();
				panel_1.repaint();
				panel_1.revalidate();
				panel_1.add(as);
				as.setVisible(true);
				
			}
		});
		btnAddSpamWord.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnAddSpamWord);
		
		JButton btnRunPython = new JButton("Run Python");
		btnRunPython.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnRunPython);
		
		panel_1 = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				Image img=new ImageIcon("e:\\main.jpg").getImage();
				g.drawImage(img, 0,0,getWidth(), getHeight(), this);
			}
		};
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
	}
}
