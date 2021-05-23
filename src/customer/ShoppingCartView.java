package customer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import product.Product;
import product.ProductList;
import product.ShoppingCartList;
import seller.SellerProductPanel;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Richmond Liang, Lucas Haase
 * Displays shopping cart window
 * @precondition shopping cart button is pressed
 * @postcondition JFrame is opened with cart
 */
public class ShoppingCartView extends JFrame {

	private ShoppingCartList cartList;
	private ArrayList<ShoppingCartPanel> productPanels;
	private JPanel contentPane;
	private JPanel mainPanel;
	private JLabel lblAmount;
	private JButton btnCheckout;
	private JButton btnRemoveAll;

	/**
	 * Create the frame.
	 */
	public ShoppingCartView() 
	{
		productPanels = new ArrayList<>();
		cartList = ShoppingCartList.getInstance();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[-142.00px,grow,fill][374.00px,fill][374px,fill]", "[497.00px,grow,fill][]"));
		
		mainPanel = new JPanel();
		contentPane.add(mainPanel, "cell 0 0 3 1,alignx left,aligny top");
		mainPanel.setLayout(new MigLayout("wrap 1", "[]", "[]"));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, "cell 0 1 3 1,grow");
		
		btnCheckout = new JButton("Checkout");
		
		btnRemoveAll = new JButton("Remove All");
		toolBar.add(btnRemoveAll);
		
		JLabel label_1 = new JLabel("                         ");
		toolBar.add(label_1);
		toolBar.add(btnCheckout);
		
		JLabel label = new JLabel("                        ");
		toolBar.add(label);
		
		JLabel lblTotal = new JLabel("Total:     ");
		toolBar.add(lblTotal);
		
		lblAmount = new JLabel("");
		toolBar.add(lblAmount);
		
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);
		contentPane.add(scrollPane, "east");
		
		cartList.addChangeListener(listener);
		ShoppingCartViewController controller = new ShoppingCartViewController(this);
		createPanels();
		
	}
	public void createPanels()
	{
		if (!productPanels.isEmpty())
		{
			for(JPanel panel : productPanels)
			{
				mainPanel.remove(panel);
			}
			productPanels.clear();
		}
		if(cartList.getMap().isEmpty())
		{
			btnCheckout.setEnabled(false);
			btnRemoveAll.setEnabled(false);
		}
		else
		{
			btnCheckout.setEnabled(true);
			btnRemoveAll.setEnabled(true);
		}
		for(Product item : cartList.getMap().keySet())
		{
			ShoppingCartPanel panel = new ShoppingCartPanel(item);
			productPanels.add(panel);
		}	
		for(JPanel panel : productPanels)
		{
			mainPanel.add(panel);
		}
		NumberFormat format = new DecimalFormat("#0.00");
		cartList.calculateTotal();
		lblAmount.setText(format.format(cartList.getTotal()));
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	public JPanel getMainPanel() 
	{
		return mainPanel;
	}
	public JLabel getLblAmount() {
		return lblAmount;
	}
	public JButton getBtnCheckout() {
		return btnCheckout;
	}
	public ArrayList<ShoppingCartPanel> getProductPanels() 
	{
		return productPanels;
	}
	//Change listener
	ChangeListener listener = new ChangeListener()
	{
		public void stateChanged(ChangeEvent e) 
		{
			System.out.println("Here");
			createPanels();
		}
	};
	
	public JButton getBtnRemoveAll() {
		return btnRemoveAll;
	}
}
