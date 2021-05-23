package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class LoginPageView 
{

	private JFrame frmVapor;
	private JTextField textFieldUsername;
	private JButton btnSubmit;
	private JPasswordField passwordField;
	private UserList users;
	private JLabel msgLabel;
	private JButton btnRegister;
	private JPanel registerPanel;
	private JButton btnRegisterRegister;
	private JButton btnBack;
	private JTextField registerUsername;
	private JTextField registerPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel mainPanel;
	private JRadioButton rdbtnSeller;
	private JRadioButton rdbtnCustomer;
	private JLabel lblRegisterMsg;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPageView window = new LoginPageView();
					window.frmVapor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPageView() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		frmVapor = new JFrame();
		frmVapor.setTitle("Vapor");
		frmVapor.setBounds(100, 100, 431, 300);
		frmVapor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVapor.getContentPane().setLayout(null);
		
		registerPanel = new JPanel();
		registerPanel.setBounds(0, 0, 415, 261);
		frmVapor.getContentPane().add(registerPanel);
		registerPanel.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(114, 175, 81, 23);
		registerPanel.add(btnBack);
		
		btnRegisterRegister = new JButton("Register");
		btnRegisterRegister.setBounds(216, 175, 99, 23);
		registerPanel.add(btnRegisterRegister);
		
		registerUsername = new JTextField();
		registerUsername.setBounds(175, 35, 123, 20);
		registerPanel.add(registerUsername);
		registerUsername.setColumns(10);
		
		registerPassword = new JTextField();
		registerPassword.setBounds(175, 67, 123, 20);
		registerPanel.add(registerPassword);
		registerPassword.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(103, 37, 71, 17);
		registerPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(103, 70, 73, 17);
		registerPanel.add(lblPassword);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(103, 104, 55, 20);
		registerPanel.add(lblType);
		
		rdbtnCustomer = new JRadioButton("Customer");
		buttonGroup.add(rdbtnCustomer);
		rdbtnCustomer.setBounds(175, 103, 123, 23);
		registerPanel.add(rdbtnCustomer);
		
		rdbtnSeller = new JRadioButton("Seller");
		buttonGroup.add(rdbtnSeller);
		rdbtnSeller.setBounds(175, 129, 123, 23);
		registerPanel.add(rdbtnSeller);
		
		lblRegisterMsg = new JLabel("");
		lblRegisterMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterMsg.setBounds(10, 209, 395, 23);
		registerPanel.add(lblRegisterMsg);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setForeground(Color.GRAY);
		mainPanel.setBounds(0, 0, 415, 261);
		frmVapor.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(171, 47, 123, 20);
		mainPanel.add(textFieldUsername);
		textFieldUsername.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldUsername.setColumns(10);
		
		msgLabel = new JLabel("");
		msgLabel.setBounds(10, 220, 395, 14);
		mainPanel.add(msgLabel);
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 78, 123, 20);
		mainPanel.add(passwordField);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(150, 121, 89, 23);
		mainPanel.add(btnSubmit);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(107, 78, 64, 20);
		mainPanel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(107, 47, 64, 20);
		mainPanel.add(usernameLabel);
		usernameLabel.setLabelFor(textFieldUsername);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(150, 155, 89, 23);
		mainPanel.add(btnRegister);
		users = new UserList();
		registerPanel.setVisible(false);
		LoginPageController controller = new LoginPageController(this, users);
	}
	
	///Getters for fields and button
	public JTextField getUsername()
	{
		return textFieldUsername;
	}
	public JPasswordField getPassword()
	{
		return passwordField;
	}
	public JButton getSubmit()
	{
		return btnSubmit;
	}
	public JLabel getMsg()
	{
		return msgLabel;
	}
	public JFrame getFrame()
	{
		return frmVapor;
	}
	public JButton getBtnRegister() 
	{
		return btnRegister;
	}
	public JPanel getRegisterPanel() 
	{
		return registerPanel;
	}
	public JPanel getMainPanel() 
	{
		return mainPanel;
	}
	public JTextField getRegisterUsername() {
		return registerUsername;
	}
	public JTextField getRegisterPassword() {
		return registerPassword;
	}
	public JRadioButton getRdbtnSeller() {
		return rdbtnSeller;
	}
	public JRadioButton getRdbtnCustomer() {
		return rdbtnCustomer;
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public JButton getBtnRegisterRegister() {
		return btnRegisterRegister;
	}
	public JLabel getRegisterMsg() {
		return lblRegisterMsg;
	}
}
