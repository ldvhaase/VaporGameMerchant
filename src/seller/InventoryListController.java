package seller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import product.ProductList;
import seller.InventoryListView.Sort;

public class InventoryListController 
{
	private InventoryListView view;
	
	/**Constructor, controls the upper menu bar of the view
	 *@author Richmond Liang, Lucas Haase
	 *@param view view the InventoryListView this controller controls
	 *@precondition view initialized
	 *@postcondition controller attached to this view
	 */
	public InventoryListController(InventoryListView view)
	{
		this.view = view;
		view.getBtnAdd().addActionListener(add);
		view.getRdbtnId().addActionListener(sortId);
		view.getRdbtnName().addActionListener(sortName);
		view.getRdbtnPurchase().addActionListener(sortPurchasePrice);
		view.getRdbtnSell().addActionListener(sortQuantity);
		view.getRdbtnQuantity().addActionListener(sortSellPrice);
		
	}
	Action add = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	 
	    	AddItemView addView = new AddItemView();
	    	addView.setVisible(true);
	    }
	};
	Action sortId = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.ID);
	        view.createPanels();
	    }
	};
	Action sortName = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.NAME);
	    	view.createPanels();
	    }
	};
	Action sortPurchasePrice = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.PURCHASE_PRICE);
	    	view.createPanels();
	    }
	};
	Action sortSellPrice = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.SELL_PRICE);
	    	view.createPanels();
	    }
	};
	Action sortQuantity = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.QUANTITY);
	    	view.createPanels();
	    }
	};
}
