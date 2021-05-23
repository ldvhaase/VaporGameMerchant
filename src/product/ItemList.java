package product;

import javax.swing.event.ChangeListener;

import java.util.HashMap;
import java.util.Map;

public interface ItemList
{
	///Add new product to list
	public void add(Product item, int quantity);
	///Remove a product
	public void remove(int id);
	///Clears list
	public void clear();
	///Return a product by searching for its id
	public Product get(int id);
	///set the quantity of an item
	public void setQuantity(int id, int quantity);
	///Return profit or total cost of all items in a shopping cart
	public double getTotal();
	///Change listener
	public void addChangeListener(ChangeListener listener);
}
