package customer;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import product.Product;
import product.ShoppingCartList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;

public class ShoppingCartPanel extends JPanel {
	private JLabel lblAmount;
	private JButton btnEdit;
	private JSpinner spinner;
	private Product item;
	private JButton btnRemove;
	private ShoppingCartList cartList;

	/**
	 * Create the panel.
         * @param Product
         * Loads the shopping cart panel with desired items
	 */
	public ShoppingCartPanel(Product item) 
	{
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		cartList = ShoppingCartList.getInstance();
		this.item = item;
		
		setLayout(new MigLayout("", "[368.00,fill][274.00,fill][274.00,fill][252.00,fill]", "[50.00,grow,fill][grow,fill][grow,fill]"));
		
		JLabel lblName = new JLabel("");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblName, "cell 0 0 1 3");
		lblName.setText("<html>" + item.getName() + "</html>");
		
		NumberFormat format = new DecimalFormat("#0.00");
		JLabel lblPrice = new JLabel("<html>Price: " + "<br>" + "$" + format.format(item.getSellPrice()));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrice, "cell 1 0");
		
		JLabel lblInCart = new JLabel("In Cart:");
		lblInCart.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblInCart, "cell 2 0 1 2");
		
		lblAmount = new JLabel("");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblAmount, "cell 3 0 1 2");
		lblAmount.setText(String.valueOf(cartList.getMap().get(item)));
		
		spinner = new JSpinner();
		spinner.setValue(cartList.getMap().get(item));
		add(spinner, "cell 1 1");
		
		btnEdit = new JButton("Edit");
		add(btnEdit, "cell 1 2");
		
		btnRemove = new JButton("Remove");
		add(btnRemove, "cell 2 2");
		
		ShoppingCartPanelController controller = new ShoppingCartPanelController(this);
	}

	public JLabel getLblAmount() {
		return lblAmount;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JSpinner getSpinner() {
		return spinner;
	}
	public Product getProduct()
	{
		return item;
	}
	public JButton getBtnRemove() {
		return btnRemove;
	}
}
