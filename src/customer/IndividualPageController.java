package customer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import global.MsgWindow;
import product.ShoppingCartList;

public class IndividualPageController
{

	private ShoppingCartList model;
	private IndividualPageView view;
	
	
	public IndividualPageController(IndividualPageView view) 
	{
		this.view = view;
		model = ShoppingCartList.getInstance();
		view.getBtnSubmit().addActionListener(buy);
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
		    		}
	    			else
	    			{
	    				model.add(view.getProduct(), (Integer)view.getSpinner().getValue());
	    			}
	    		}
	    	}
	    	view.dispose();
	    }
	};
}
