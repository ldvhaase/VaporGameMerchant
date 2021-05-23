package seller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import product.Game;
import product.Product;
import product.ProductList;

public class EditItemController 
{
	private EditItemView view;
	
	private Product item;
	private ProductList model;
	
	/**Constructor
	 *@author Richmond Liang, Lucas Haase
	 *@param view view the EditItemView this controller controls
	 *@param item the product associated with the calling Seller panel
	 *@precondition view initialized
	 *@postcondition calls model to update item with the given purchase price and quantity
	 */
	public EditItemController(EditItemView view, Product item)
	{
		this.view = view;
		this.item = item;
		view.getBtnSubmit().addActionListener(action);
	}
	
	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	if(view.getTextName().getText().equals("") || view.getTextPurchasePrice().getText().equals("") || view.getTextSellPrice().getText().equals("") || view.getTextQuantity().getText().equals(""))
			{
				view.getMsg().setText("One or more required fields are empty");
			}
			else if(!view.getTextPurchasePrice().getText().matches("\\d+\\.[0-9]{2}") || !view.getTextSellPrice().getText().matches("\\d+\\.[0-9]{2}") || !view.getTextQuantity().getText().matches("\\d+"))
			{
				view.getMsg().setText("<html>One or more price entries are not in the correct format or quantity is not a whole number</html>");
			}
			else
			{
				if(!view.getTextName().getText().equals(""))
				{
					String name = view.getTextName().getText();
					model.updateName(item.getId(), name);
				}
				if(!view.getTextPurchasePrice().getText().equals("") && view.getTextPurchasePrice().getText().matches("\\d+\\.[0-9]{2}"))
				{
					double purchasePrice = Double.parseDouble(view.getTextPurchasePrice().getText());
					model.updatePurchasePrice(item.getId(), purchasePrice);
				}
				if(!view.getTextSellPrice().getText().equals("") && view.getTextSellPrice().getText().matches("\\d+\\.[0-9]{2}"))
				{
					double sellPrice = Double.parseDouble(view.getTextSellPrice().getText());
					model.updateSellPrice(item.getId(), sellPrice);
				}
				if(!view.getTextQuantity().getText().equals("") && view.getTextPurchasePrice().getText().matches("\\d+\\.[0-9]{2}"))
				{
					int quantity = Integer.parseInt(view.getTextQuantity().getText());
					model.setQuantity(item.getId(), quantity);
				}
				if(!view.getTextDescription().getText().equals(""))
				{
					String description = view.getTextDescription().getText();
					model.updateDescription(item.getId(), description);
				}
				view.dispose();
			}
	    }
	};
}
