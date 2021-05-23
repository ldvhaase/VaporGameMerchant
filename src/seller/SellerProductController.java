package seller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import product.ProductList;
import seller.InventoryListView.Sort;

public class SellerProductController 
{
	private SellerProductPanel view;
	private ProductList model;
	
	/**Constructor, controls the buttons on the associated panel to call the appropriate pages and functions
	 *@author Richmond Liang
	 *@param view view the SellerProductPanel this controller controls
	 *@precondition view initialized
	 *@postcondition controller controls the view
	 */
	public SellerProductController(SellerProductPanel view)
	{
		model = ProductList.getInstance();
		this.view = view;
		view.getBtnBuy().addActionListener(buy);
		view.getBtnEdit().addActionListener(edit);
		view.getBtnRemove().addActionListener(remove);
	}
	Action buy = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	BuyItemView addView = new BuyItemView(view.getProduct());
	    	addView.setVisible(true);
	    }
	};
	Action edit = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	EditItemView addView = new EditItemView(view.getProduct());
	    	addView.setVisible(true);
	    }
	};
	Action remove = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	model.remove(view.getProductId());
	    }
	};
}
