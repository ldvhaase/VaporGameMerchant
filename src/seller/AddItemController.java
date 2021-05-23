package seller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import login.LoginPageView;
import login.UserList;
import product.Game;
import product.Product;
import product.ProductList;

public class AddItemController 
{
	private ProductList model;
	private AddItemView view;
	
	/**Constructor
	 *@author Richmond Liang
	 *@param view view the AddItemView this controller controls
	 *@precondition view initialized
	 *@postcondition controller attaches listeners to view
	 */
	public AddItemController(AddItemView view)
	{
		this.view = view;
		model = ProductList.getInstance();
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
				String name = view.getTextName().getText();
				double purchasePrice = Double.parseDouble(view.getTextPurchasePrice().getText());
				double sellPrice = Double.parseDouble(view.getTextSellPrice().getText());
				int quantity = Integer.parseInt(view.getTextQuantity().getText());
				String description = view.getTextDescription().getText();
				Product newProduct = new Game(model.getListId(), name, purchasePrice, sellPrice, description);
				model.add(newProduct, quantity);
				view.dispose();
			}
	    }
	};
}
