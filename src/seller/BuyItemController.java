package seller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import product.Game;
import product.Product;
import product.ProductList;

public class BuyItemController 
{
	private BuyItemView view;
	
	private Product item;
	
	private ProductList model;
	
	/**Constructor
	 *@author Richmond Liang, Lucas Haase
	 *@param view view the BuyItemView this controller controls
	 *@param item the product associated with the calling Seller panel
	 *@precondition view initialized
	 *@postcondition calls model to update item with the given purchase price and quantity
	 */
	public BuyItemController(BuyItemView view, Product item)
	{
		this.view = view;
		this.model = ProductList.getInstance();
		this.item = item;
		view.getBtnSubmit().addActionListener(action);
		System.out.println("active");
	}
	
	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	if(view.getTextPurchasePrice().getText().equals("") || view.getTextQuantity().getText().equals(""))
			{
				view.getMsg().setText("One or more required fields are empty");
			}
			else if(!view.getTextPurchasePrice().getText().matches("\\d+\\.[0-9]{2}") || !view.getTextQuantity().getText().matches("\\d+"))
			{
				view.getMsg().setText("<html>Purchase price entry is not in the correct format or quantity is not a whole number</html>");
			}
			else
			{
					double purchasePrice = Double.parseDouble(view.getTextPurchasePrice().getText());
					int quantity = Integer.parseInt(view.getTextQuantity().getText());
					model.updatePurchasePrice(item.getId(), purchasePrice);
					model.setQuantity(item.getId(), item.getQuantity() + quantity);
					model.addCost(purchasePrice * quantity);
					view.dispose();
			}
	    }
	};
}
