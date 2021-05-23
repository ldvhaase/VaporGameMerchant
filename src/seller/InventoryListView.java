package seller;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import product.Product;
import product.ProductList;
import seller.InventoryListView.Sort;

import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;


public class InventoryListView extends JFrame 
{
	enum Sort
	{
		ID, NAME, PURCHASE_PRICE, SELL_PRICE, QUANTITY;
	}
	private ProductList productList;
	private JPanel contentPane;
	private ArrayList<SellerProductPanel> productPanels;
	private JPanel mainPanel;
	private JToolBar toolBar;
	private JButton btnAdd;
	private JLabel lblSortBy;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnPurchase;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnSell;
	private JRadioButton rdbtnQuantity;
	private JLabel lblProfit;
	private JLabel lblCost;
	private JLabel lblRevenue;
	private JLabel lblHoverOverTo;
	private Sort sort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryListView frame = new InventoryListView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**Constructor, creates the frame for InventoryListView
	 *@author Richmond Liang
	 *@precondition view initialized
	 *@postcondition frame initialized
	 */
	public InventoryListView() 
	{
		setTitle("Vapor");
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		
		contentPane.setBorder(null);
		contentPane.setPreferredSize(getMaximumSize());
		
		setContentPane(contentPane);
		
		contentPane.setLayout(new MigLayout("", "[23.00,left][left][grow,left]", "[grow]"));
		
		mainPanel = new JPanel();
		contentPane.add(mainPanel, "cell 0 0 3 1,alignx left,aligny top");
		mainPanel.setLayout(new MigLayout("wrap 3", "[300px,fill]", "[200px,fill]"));
		
		toolBar = new JToolBar();
		toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		toolBar.setFloatable(false);
		contentPane.add(toolBar, "north,alignx center,aligny center");
		
		btnAdd = new JButton(" Add New Product             ");
		toolBar.add(btnAdd);
		
		lblSortBy = new JLabel("Sort By:   ");
		toolBar.add(lblSortBy);
		
		rdbtnId = new JRadioButton("Id     ");
		rdbtnId.setSelected(true);
		rdbtnId.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnId);
		toolBar.add(rdbtnId);
		
		rdbtnName = new JRadioButton("Name     ");
		rdbtnName.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnName);
		toolBar.add(rdbtnName);
		
		rdbtnPurchase = new JRadioButton("Purchase Price     ");
		rdbtnPurchase.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnPurchase);
		toolBar.add(rdbtnPurchase);
		
		rdbtnSell = new JRadioButton("Sell Price     ");
		rdbtnSell.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnSell);
		toolBar.add(rdbtnSell);
		
		rdbtnQuantity = new JRadioButton("Quantity     ");
		rdbtnQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnQuantity);
		toolBar.add(rdbtnQuantity);
		
		lblHoverOverTo = new JLabel("Hover over to view:     ");
		toolBar.add(lblHoverOverTo);
		
		lblCost = new JLabel("Costs:   ");
		lblCost.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar.add(lblCost);
		
		lblRevenue = new JLabel("Revenue:   ");
		toolBar.add(lblRevenue);
		
		lblProfit = new JLabel("Profit:   ");
		toolBar.add(lblProfit);
		
		productPanels = new ArrayList<>();
		sort = Sort.ID;
		
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(25);
		contentPane.add(scrollPane, "east");
		productList = ProductList.getInstance();
		productList.addChangeListener(listener);
		InventoryListController controller = new InventoryListController(this);
		createPanels();
	}
	
	/**Creates panels and adds them to a sorted list of panels which are then added in the order they are sorted. 
	 * Called whenever a change event occurs or a radio button is pressed. 
	 * Also controls the display for the cost, profit, and revenue
	 *@author Richmond Liang
	 *@precondition view initialized
	 *@postcondition panels added to frame, cost, revenue, profit updated
	 */
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
			SellerProductPanel panel = new SellerProductPanel(item);
			productPanels.add(panel);
			
		}
		
		if(sort == Sort.ID)
		{
			Collections.sort(productPanels, sortById());
		}
		else if(sort == Sort.NAME)
		{
			Collections.sort(productPanels, sortByName());
		}
		else if(sort == Sort.PURCHASE_PRICE)
		{
			Collections.sort(productPanels, sortByPurchasePrice());
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
		
		lblCost.setToolTipText(format.format(productList.getCost()));
		lblRevenue.setToolTipText(format.format(productList.getRevenue()));
		lblProfit.setToolTipText(format.format(productList.getTotal()));
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	///Getters
	public JButton getBtnAdd() 
	{
		return btnAdd;
	}
	public JRadioButton getRdbtnId() {
		return rdbtnId;
	}
	public JRadioButton getRdbtnName() {
		return rdbtnName;
	}
	public JRadioButton getRdbtnQuantity() {
		return rdbtnQuantity;
	}
	public JRadioButton getRdbtnSell() {
		return rdbtnSell;
	}
	public JRadioButton getRdbtnPurchase() {
		return rdbtnPurchase;
	}
	public Sort getSort() 
	{
		return sort;
	}
	public void setSort(Sort sort) 
	{
		this.sort = sort;
	}
	public ArrayList<SellerProductPanel> getProductPanels() 
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

	///Comparators for sorting the panel list by a certain variable
	public static Comparator<SellerProductPanel> sortById()
	{
		return new Comparator<SellerProductPanel>()
		{
			public int compare(SellerProductPanel p1, SellerProductPanel p2)
			{
				return p1.getProductId() - p2.getProductId();
			}
		};		
	}
	public static Comparator<SellerProductPanel> sortByName()
	{
		return new Comparator<SellerProductPanel>()
		{
			public int compare(SellerProductPanel p1, SellerProductPanel p2)
			{
				return p1.getProduct().getName().compareToIgnoreCase(p2.getProduct().getName());
			}
		};		
	}
	public static Comparator<SellerProductPanel> sortByPurchasePrice()
	{
		return new Comparator<SellerProductPanel>()
		{
			public int compare(SellerProductPanel p1, SellerProductPanel p2)
			{
				return Double.compare(p1.getProduct().getPurchasePrice() , p2.getProduct().getPurchasePrice());
			}
		};		
	}
	public static Comparator<SellerProductPanel> sortBySellPrice()
	{
		return new Comparator<SellerProductPanel>()
		{
			public int compare(SellerProductPanel p1, SellerProductPanel p2)
			{
				return Double.compare(p1.getProduct().getSellPrice() , p2.getProduct().getSellPrice());
			}
		};		
	}
	public static Comparator<SellerProductPanel> sortByQuantity()
	{
		return new Comparator<SellerProductPanel>()
		{
			public int compare(SellerProductPanel p1, SellerProductPanel p2)
			{
				return p1.getProduct().getQuantity() - p2.getProduct().getQuantity();
			}
		};		
	}
}
