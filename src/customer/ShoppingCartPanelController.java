package customer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import global.MsgWindow;
import product.Product;
import product.ProductList;
import product.ShoppingCartList;

/**
 * 
 * @author Richmond Liang, Lucas Haase
 * controls movement of data between cartView and cartList
 */
public class ShoppingCartPanelController  {

	private ShoppingCartPanel cartView;
	private ShoppingCartList cartList;
	
	public ShoppingCartPanelController(ShoppingCartPanel view)
	{
		cartView = view;
		cartList = ShoppingCartList.getInstance();
		cartView.getBtnEdit().addActionListener(edit);
		cartView.getBtnRemove().addActionListener(remove);
	}
	Action edit = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
    		if((Integer)cartView.getSpinner().getValue() < cartList.getMap().get(cartView.getProduct()))
    		{
    			if(cartList.getMap().containsKey(cartView.getProduct()))
	    		{
    				cartList.returnQuantity(cartView.getProduct().getId(), cartList.getMap().get(cartView.getProduct()) - (Integer)cartView.getSpinner().getValue());
	    			cartView.getSpinner().setValue(cartList.getMap().get(cartView.getProduct()));
	    			cartView.getLblAmount().setText(String.valueOf(cartList.getMap().get(cartView.getProduct())));
	    			cartList.setQuantity(cartView.getProduct().getId(), (Integer)cartView.getSpinner().getValue());
	    		}
    		}
    		else if((Integer)cartView.getSpinner().getValue() > cartList.getMap().get(cartView.getProduct())) 
    		{
    			cartList.removeQuantity(cartView.getProduct().getId(), (Integer)cartView.getSpinner().getValue() - cartList.getMap().get(cartView.getProduct()));
    			cartView.getSpinner().setValue(cartList.getMap().get(cartView.getProduct()));
    			cartView.getLblAmount().setText(String.valueOf(cartList.getMap().get(cartView.getProduct())));
    			cartList.setQuantity(cartView.getProduct().getId(), (Integer)cartView.getSpinner().getValue());
    		}
	    }
	};
	Action remove = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	cartList.remove(cartView.getProduct().getId());
	    }
	};
	
	
}
