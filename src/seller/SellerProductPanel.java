package seller;

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

public class SellerProductPanel extends JPanel {
	private JButton btnRemove;
	private JButton btnEdit;
	private JButton btnBuy;
	private Product product;

	/**Constructor, creates individual product panels displaying information and buttons to control them.
	 *@author Richmond Liang
	 *@param view view the SellerProductPanel this controller controls
	 *@precondition view initialized
	 *@postcondition controller controls the view
	 */
	public SellerProductPanel(Product item) 
	{
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setLayout(new MigLayout("", "[719px,fill][719px,fill][719px,fill]", "[275,fill][350,fill][154][154,fill]"));
		
		JLabel lblId = new JLabel("Id:");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblId, "cell 0 0");
		
		JLabel lblIdnum = new JLabel("");
		add(lblIdnum, "cell 1 0");
		lblIdnum.setText(String.valueOf(item.getId()));
		JLabel lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName, "cell 0 1 3 1,alignx center,aligny center");
		lblName.setText("<html>" + item.getName() + "</html>");
		
		NumberFormat format = new DecimalFormat("#0.00");
		
		JLabel lblPurchasePrice = new JLabel("<html>Last Purchase Price: " + "<br>" + "$" + format.format(item.getPurchasePrice()));
		lblPurchasePrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPurchasePrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPurchasePrice, "cell 0 2");
		
		JLabel lblQuantity = new JLabel("<html> Quantity: " + "<br>" + item.getQuantity());
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblQuantity, "cell 1 2");
		
		JLabel lblSellPrice = new JLabel("<html>Sell Price: " + "<br>" + "$" + format.format(item.getSellPrice()));
		lblSellPrice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSellPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSellPrice, "cell 2 2");
		
		btnBuy = new JButton("Buy");
		add(btnBuy, "cell 0 3,alignx center,aligny center");
		
		btnEdit = new JButton("Info/Edit");
		add(btnEdit, "cell 1 3,alignx center,aligny center");
		
		btnRemove = new JButton("Remove");
		add(btnRemove, "cell 2 3,alignx center,aligny center");
		
		product = item;
		setPreferredSize(new Dimension(300, 200));
		SellerProductController controller = new SellerProductController(this);
	}
	
	///Getters
	public JButton getBtnRemove() 
	{
		return btnRemove;
	}
	public JButton getBtnEdit() 
	{
		return btnEdit;
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
	ChangeListener listener = new ChangeListener()
	{
		public void stateChanged(ChangeEvent e) 
		{
			revalidate();
			repaint();
		}
	};
}
