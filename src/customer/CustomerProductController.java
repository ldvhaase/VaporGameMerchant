package customer;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import global.MsgWindow;
import product.ShoppingCartList;

public class CustomerProductController 
{
	private CustomerProductPanel view;
	private ShoppingCartList model;
	
	/**Constructor, controls the buttons on the associated panel to call the appropriate pages and functions
	 *@author Richmond Liang, Lucas Haase
	 *@param view view the SellerProductPanel this controller controls
	 *@precondition view initialized
	 *@postcondition controller controls the view
	 */
	public CustomerProductController(CustomerProductPanel view)
	{
		model = ShoppingCartList.getInstance();
		this.view = view;
		view.getBtnBuy().addActionListener(buy);
		view.getBtnInfo().addActionListener(info);
	}
	Action buy = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	if((Integer)view.getSpinner().getValue() > 0)
	    	{
	    		if((Integer)view.getSpinner().getValue() < view.getProduct().getQuantity())
	    		{
	    			if(model.getMap().containsKey(view.getProduct()))
		    		{
		    			model.addQuantity(view.getProduct(), (Integer)view.getSpinner().getValue());
		    			view.getSpinner().setValue(0);
		    			new MsgWindow("Item(s) added");
		    		}
	    			else
		    		{
		    			model.add(view.getProduct(), (Integer)view.getSpinner().getValue());
		    		}
	    		}
	    	}
	    }
	};
	Action info = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	IndividualPageView addView = new IndividualPageView(view.getProduct());
	    	addView.setVisible(true);
	    }
	};
}
