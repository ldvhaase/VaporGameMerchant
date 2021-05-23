package seller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import product.Product;
import product.ProductList;

public class BuyItemView extends JFrame {

	private JPanel contentPane;
	private JLabel lblMsg;
	private static BuyItemView frame;
	private JButton btnSubmit;
	private JTextField textPurchasePrice;
	private JTextField textQuantity;

	/**Constructor, creates the frame for BuyItemView
	 *@author Richmond Liang, Lucas Haase
	 *@param item Product associated with the view
	 *@precondition view initialized
	 *@postcondition frame initialized
	 */
	public BuyItemView(Product item) 
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
		lblPurchasePrice.setBounds(25, 45, 90, 14);
		contentPane.add(lblPurchasePrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(25, 80, 90, 14);
		contentPane.add(lblQuantity);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(25, 115, 90, 14);
		contentPane.add(lblDescription);
		
		JLabel textName = new JLabel();
		lblName.setLabelFor(textName);
		textName.setBounds(125, 10, 200, 20);
		contentPane.add(textName);
		textName.setText("<html>" + item.getName() + "</html");
		
		NumberFormat format = new DecimalFormat("#0.00");
		textPurchasePrice = new JTextField();
		lblPurchasePrice.setLabelFor(textPurchasePrice);
		textPurchasePrice.setBounds(125, 45, 96, 20);
		contentPane.add(textPurchasePrice);
		textPurchasePrice.setText(format.format(item.getPurchasePrice()));
	
		textQuantity = new JTextField();
		lblQuantity.setLabelFor(textQuantity);
		textQuantity.setBounds(125, 80, 96, 20);
		contentPane.add(textQuantity);
		
		JLabel textDescription = new JLabel();
		lblDescription.setLabelFor(textDescription);
		textDescription.setBounds(125, 115, 200, 124);
		contentPane.add(textDescription);
		textDescription.setText("<html>" + item.getDescription() + "</html");
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(146, 325, 75, 23);
		contentPane.add(btnSubmit);
		
		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setBounds(10, 265, 364, 51);
		contentPane.add(lblMsg);
		
		JLabel lblHelpQuantity = new JLabel("(Must be a whole number)");
		lblHelpQuantity.setBounds(227, 80, 127, 14);
		contentPane.add(lblHelpQuantity);
		
		JLabel lblHelpPrice1 = new JLabel("<html>(Use format #.## No separators)</html>");
		lblHelpPrice1.setBounds(231, 32, 96, 44);
		contentPane.add(lblHelpPrice1);
		
		BuyItemController controller = new BuyItemController(this, item);
	}
	///Getters
	public JLabel getMsg() 
	{
		return lblMsg;
	}

	public JButton getBtnSubmit() 
	{
		return btnSubmit;
	}

	public JTextField getTextPurchasePrice() 
	{
		return textPurchasePrice;
	}

	public JTextField getTextQuantity() 
	{
		return textQuantity;
	}
	public static BuyItemView getFrame() 
	{
		return frame;
	}
	
}
