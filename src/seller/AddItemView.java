package seller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.LoginPageView;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AddItemView extends JFrame 
{
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textPurchasePrice;
	private JTextField textSellPrice;
	private JTextField textQuantity;


	private JTextArea textDescription;
	private JButton btnSubmit;
	private static AddItemView frame;
	private JLabel lblOpt;
	private JLabel lblMsg;
	private JLabel lblHelpQuantity;
	private JLabel lblHelpPrice1;

	/**Constructor, creates the frame for AddItemView
	 *@author Richmond Liang
	 *@precondition view initialized
	 *@postcondition frame initialized
	 */
	public AddItemView() 
	{
		setTitle("Vapor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(25, 10, 90, 14);
		contentPane.add(lblName);
		
		JLabel lblPurchasePrice = new JLabel("Purchase Price");
		lblPurchasePrice.setBounds(25, 40, 90, 14);
		contentPane.add(lblPurchasePrice);
		
		JLabel lblSellPrice = new JLabel("Sell Price");
		lblSellPrice.setBounds(25, 70, 90, 14);
		contentPane.add(lblSellPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(25, 100, 90, 14);
		contentPane.add(lblQuantity);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(25, 130, 90, 14);
		contentPane.add(lblDescription);
		
		textName = new JTextField();
		lblName.setLabelFor(textName);
		textName.setBounds(125, 10, 200, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textPurchasePrice = new JTextField();
		lblPurchasePrice.setLabelFor(textPurchasePrice);
		textPurchasePrice.setBounds(125, 40, 96, 20);
		contentPane.add(textPurchasePrice);
		textPurchasePrice.setColumns(10);
		
		textSellPrice = new JTextField();
		lblSellPrice.setLabelFor(textSellPrice);
		textSellPrice.setBounds(125, 70, 96, 20);
		contentPane.add(textSellPrice);
		textSellPrice.setColumns(10);
	
		textQuantity = new JTextField();
		lblQuantity.setLabelFor(textQuantity);
		textQuantity.setBounds(125, 100, 96, 20);
		contentPane.add(textQuantity);
		textQuantity.setColumns(10);
		
		textDescription = new JTextArea();
		textDescription.setLineWrap(true);
		lblDescription.setLabelFor(textDescription);
		textDescription.setBounds(125, 130, 200, 124);
		contentPane.add(textDescription);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(155, 327, 75, 23);
		contentPane.add(btnSubmit);
		
		lblOpt = new JLabel("(Optional)");
		lblOpt.setBounds(25, 146, 69, 14);
		contentPane.add(lblOpt);
		
		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(10, 265, 364, 51);
		contentPane.add(lblMsg);
		
		lblHelpQuantity = new JLabel("(Must be a whole number)");
		lblHelpQuantity.setBounds(227, 103, 127, 14);
		contentPane.add(lblHelpQuantity);
		
		lblHelpPrice1 = new JLabel("<html>(Use format #.## No separators)</html>");
		lblHelpPrice1.setBounds(231, 40, 96, 44);
		contentPane.add(lblHelpPrice1);
		
		AddItemController controller = new AddItemController(this);
	}
	///Getters
	public JTextField getTextName() 
	{
		return textName;
	}

	public JTextField getTextPurchasePrice() 
	{
		return textPurchasePrice;
	}

	public JTextField getTextSellPrice() 
	{
		return textSellPrice;
	}

	public JTextField getTextQuantity() 
	{
		return textQuantity;
	}

	public JTextArea getTextDescription() 
	{
		return textDescription;
	}

	public JButton getBtnSubmit() 
	{
		return btnSubmit;
	}
	public JLabel getMsg() 
	{
		return lblMsg;
	}

	public JFrame getFrame() 
	{
		return frame;
	}
}
