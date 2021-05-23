package customer;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import product.Product;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class CustomerProductPanel extends JPanel {
	private JButton btnInfo;
	private JButton btnBuy;
	private JSpinner spinner;
	private Product product;

	/**Constructor, creates individual product panels displaying information and buttons to control them.
	 *@author Richmond Liang
	 *@param view view the SellerProductPanel this controller controls
	 *@precondition view initialized
	 *@postcondition controller controls the view
	 */
	public CustomerProductPanel(Product item) 
	{
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setLayout(new MigLayout("", "[719px,fill][][42.00px,fill][719px,fill]", "[300,fill][200][154,fill]"));
		JLabel lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName, "cell 0 0 4 1,alignx center,aligny center");
		lblName.setText("<html>" + item.getName() + "</html>");
		
		NumberFormat format = new DecimalFormat("#0.00");
		
		JLabel lblQuantity = new JLabel("<html> Remaining Quantity: <br>0");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblQuantity, "cell 0 1");
		lblQuantity.setText(String.valueOf(item.getQuantity()));
		
		JLabel lblSellPrice = new JLabel("<html>Price: <br>" + format.format(item.getSellPrice()));
		lblSellPrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSellPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSellPrice, "cell 3 1");
		
		btnBuy = new JButton("Add to Cart");
		add(btnBuy, "cell 0 2,alignx center,aligny center");

		setPreferredSize(new Dimension(300, 200));
		SpinnerModel value = new SpinnerNumberModel(0, 0, item.getQuantity(), 1);
		spinner = new JSpinner(value);
		add(spinner, "cell 1 2");
		
		btnInfo = new JButton("Info");
		add(btnInfo, "cell 3 2,alignx center,aligny center");
		product = item;
		CustomerProductController controller = new CustomerProductController(this);
	}
	
	///Getters
	public JButton getBtnInfo() 
	{
		return btnInfo;
	}
	public JButton getBtnBuy() 
	{
		return btnBuy;
	}
	
	public Product getProduct() 
	{
		return product;
	}
	
	public int getProductId() 
	{
		return getProduct().getId();
	}
	
	public JSpinner getSpinner() 
	{
		return spinner;
	}
}
