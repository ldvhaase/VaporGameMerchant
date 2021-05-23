package customer;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import product.Product;
import product.ProductList;
import product.ShoppingCartList;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainPageView extends JFrame {

	enum Sort
	{
		ID, NAME, PURCHASE_PRICE, SELL_PRICE, QUANTITY;
	}
	private JPanel contentPane;
	private ProductList productList;
	private ShoppingCartList cartList;
	private ArrayList<CustomerProductPanel> productPanels;
	private JPanel mainPanel;
	private JToolBar toolBar;
	private JButton btnCart;
	private JLabel lblNewLabel;
	private JLabel lblItemsInCart;
	private JLabel lblCartAmount;
	private JLabel lblTotal;
	public JLabel getLblTotal; 
	private JLabel lblSortBy;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnPrice;
	private JRadioButton rdbtnQuantity;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Sort sort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPageView frame = new MainPageView();
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
	public MainPageView() {
		setTitle("Vapor");
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		
		contentPane.setBorder(null);
		contentPane.setPreferredSize(getMaximumSize());
		
		setContentPane(contentPane);
		
		contentPane.setLayout(new MigLayout("", "[1.00,grow,fill][fill][grow]", "[grow]"));
		
		mainPanel = new JPanel();
		contentPane.add(mainPanel, "cell 0 0 3 1,grow");
		mainPanel.setLayout(new MigLayout("wrap 3", "[300px,fill]", "[200px,fill]"));
		
		toolBar = new JToolBar();
		toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		toolBar.setFloatable(false);
		contentPane.add(toolBar, "north,alignx center,aligny center");
		
		JLabel lblNewLabel_3 = new JLabel("     ");
		toolBar.add(lblNewLabel_3);
		
		btnCart = new JButton("Open Shopping Cart");
		toolBar.add(btnCart);
		
		lblNewLabel = new JLabel("         ");
		toolBar.add(lblNewLabel);
		
		lblItemsInCart = new JLabel("Items In Cart:  ");
		toolBar.add(lblItemsInCart);
		
		lblCartAmount = new JLabel("");
		toolBar.add(lblCartAmount);
		
		JLabel lblNewLabel_1 = new JLabel("            ");
		toolBar.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Value of Shopping Cart:    ");
		toolBar.add(lblNewLabel_2);
		
		lblTotal = new JLabel("");
		toolBar.add(lblTotal);
		
		lblSortBy = new JLabel("   Sort By   ");
		toolBar.add(lblSortBy);
		
		rdbtnName = new JRadioButton("Name");
		buttonGroup.add(rdbtnName);
		toolBar.add(rdbtnName);
		
		rdbtnPrice = new JRadioButton("Price");
		buttonGroup.add(rdbtnPrice);
		toolBar.add(rdbtnPrice);
		
		rdbtnQuantity = new JRadioButton("Quantity");
		buttonGroup.add(rdbtnQuantity);
		toolBar.add(rdbtnQuantity);

		
		productPanels = new ArrayList<>();
		
		///JScrollPane scrollPane = new JScrollPane(mainPanel);
		///scrollPane.getVerticalScrollBar().setUnitIncrement(25);
		//contentPane.add(scrollPane, "east");
		productList = ProductList.getInstance();
		cartList = ShoppingCartList.getInstance();
		productList.addChangeListener(listener);
		cartList.addChangeListener(listener);
		MainPageController controller = new MainPageController(this);
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
	
		for(Product item : productList.getMap().values())
		{
			CustomerProductPanel panel = new CustomerProductPanel(item);
			productPanels.add(panel);
		}	
		if(sort == Sort.NAME)
		{
			Collections.sort(productPanels, sortByName());
		}
		else if(sort == Sort.SELL_PRICE)
		{
			Collections.sort(productPanels, sortBySellPrice());
		}
		else if(sort == Sort.QUANTITY)
		{
			Collections.sort(productPanels, sortByQuantity());
		}
		for(JPanel panel : productPanels)
		{
			mainPanel.add(panel);
		}
		NumberFormat format = new DecimalFormat("#0.00");
		cartList.calculateTotal();
		lblTotal.setText(format.format(cartList.getTotal()));
		int cartTotal = 0;
		for (Integer value : cartList.getMap().values())
		{
			cartTotal += value;
		}
		lblCartAmount.setText(String.valueOf(cartTotal));
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	public JPanel getMainPanel() 
	{
		return mainPanel;
	}
	public ArrayList<CustomerProductPanel> getProductPanels() 
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

	
	public JLabel getTotal()
	{
		return lblTotal;
	}
	public JLabel getLblCartAmount() {
		return lblCartAmount;
	}
	public JButton getBtnCart() {
		return btnCart;
	}
	public JRadioButton getRdbtnPrice() {
		return rdbtnPrice;
	}
	public JRadioButton getRdbtnQuantity() {
		return rdbtnQuantity;
	}
	public JRadioButton getRdbtnName() {
		return rdbtnName;
	}
	public void setSort(Sort sort) 
	{
		this.sort = sort;
	}
	public static Comparator<CustomerProductPanel> sortByName()
	{
		return new Comparator<CustomerProductPanel>()
		{
			public int compare(CustomerProductPanel p1, CustomerProductPanel p2)
			{
				return p1.getProduct().getName().compareToIgnoreCase(p2.getProduct().getName());
			}
		};		
	}
	public static Comparator<CustomerProductPanel> sortBySellPrice()
	{
		return new Comparator<CustomerProductPanel>()
		{
			public int compare(CustomerProductPanel p1, CustomerProductPanel p2)
			{
				return Double.compare(p1.getProduct().getSellPrice() , p2.getProduct().getSellPrice());
			}
		};		
	}
	public static Comparator<CustomerProductPanel> sortByQuantity()
	{
		return new Comparator<CustomerProductPanel>()
		{
			public int compare(CustomerProductPanel p1, CustomerProductPanel p2)
			{
				return p1.getProduct().getQuantity() - p2.getProduct().getQuantity();
			}
		};		
	}
	
}
