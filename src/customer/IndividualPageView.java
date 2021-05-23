package customer;

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
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

public class IndividualPageView extends JFrame {

	private JPanel contentPane;
	private JLabel textName;
	private JLabel textSellPrice;
	private JLabel textQuantity;


	private JTextPane textDescription;
	private JButton btnSubmit;

	private static IndividualPageView frame;
	private Product item;
	private JSpinner spinner;

	/**Constructor, creates the frame for BuyItemView
	 *@author Richmond Liang
	 *@param item Product associated with the view
	 *@precondition view initialized
	 *@postcondition frame initialized
	 */
	public IndividualPageView(Product item) 
	{
		setTitle("Vapor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(25, 10, 90, 20);
		contentPane.add(lblName);
		
		JLabel lblSellPrice = new JLabel("Price");
		lblSellPrice.setBounds(25, 50, 90, 20);
		contentPane.add(lblSellPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(25, 90, 90, 20);
		contentPane.add(lblQuantity);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(25, 130, 90, 14);
		contentPane.add(lblDescription);
		
		textName = new JLabel();
		lblName.setLabelFor(textName);
		textName.setBounds(125, 10, 200, 20);
		contentPane.add(textName);
		textName.setText(item.getName());
		
		
		NumberFormat format = new DecimalFormat("#0.00");
		
		textSellPrice = new JLabel();
		lblSellPrice.setLabelFor(textSellPrice);
		textSellPrice.setBounds(125, 50, 96, 20);
		contentPane.add(textSellPrice);
		textSellPrice.setText(format.format(item.getSellPrice()));
	
		textQuantity = new JLabel();
		lblQuantity.setLabelFor(textQuantity);
		textQuantity.setBounds(125, 90, 96, 20);
		contentPane.add(textQuantity);
		textQuantity.setText(String.valueOf(item.getQuantity()));
		
		textDescription = new JTextPane();
		textDescription.setEditable(false);
		lblDescription.setLabelFor(textDescription);
		textDescription.setBounds(125, 130, 200, 174);
		contentPane.add(textDescription);
		textDescription.setText(item.getDescription());
		
		btnSubmit = new JButton("Add to Cart");
		btnSubmit.setBounds(125, 327, 102, 23);
		contentPane.add(btnSubmit);
		
		SpinnerModel value = new SpinnerNumberModel(0, 0, item.getQuantity(), 1);
		spinner = new JSpinner(value);
		spinner.setBounds(237, 328, 30, 20);
		contentPane.add(spinner);
		
		this.item = item;
		IndividualPageController controller = new IndividualPageController(this);
	}
	//Getters
	
	public Product getProduct()
	{
		return item;
	}
	public static IndividualPageView getFrame() 
	{
		return frame;
	}
	public JButton getBtnSubmit() 
	{
		return btnSubmit;
	}
	public JSpinner getSpinner() 
	{
		return spinner;
	}
}
