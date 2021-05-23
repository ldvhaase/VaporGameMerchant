package customer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import product.Product;
import product.ProductList;
import product.ShoppingCartList;

public class ShoppingCartViewController {

	//private CheckoutView checkoutView;
	private ShoppingCartList cartList;
	private ShoppingCartView cartView;
	
	public ShoppingCartViewController(ShoppingCartView view)
	{
		cartView = view;
		cartList = ShoppingCartList.getInstance();
		cartView.getBtnCheckout().addActionListener(checkout);
		cartView.getBtnCheckout().addActionListener(removeAll);
		
	}
	
	Action checkout = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	cartList.calculateTotal();
	    	CheckoutWindow checkoutNow = new CheckoutWindow(cartList.getTotal());
	    	checkoutNow.setVisible(true);
	    }
	};
	Action removeAll = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	cartList.clearRefund();
	    	cartView.createPanels();

	    }
	};
}

