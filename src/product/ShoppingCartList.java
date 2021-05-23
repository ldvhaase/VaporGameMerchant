package product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ShoppingCartList implements ItemList
{
	
	private ProductList productList;
	private HashMap<Product, Integer> shoppingCartMap;
	private ArrayList<ChangeListener> listeners;
	private double total;
	///////////////////Singleton Pattern//////////////////////////
	////////////////////Use ShoppingCartList.getInstance() to instantiate///////////////////////
	/**private Constructor, instantiates a shopping list. Uses singleton pattern so only one instance can exist. 
	 * ShoppingCartMap uses products as the key and the current quantity in the cart as the value
	 *@author Richmond Liang
	 *@postcondition ShoppingCartList instantiated using singleton pattern
	 */
	private ShoppingCartList()
	{
		System.out.println("Initialized");
		total = 0.00;
		shoppingCartMap = new HashMap<>();
		listeners = new ArrayList<>();
		productList = ProductList.getInstance();
	}
	public static ShoppingCartList getInstance()
	{
		return ShoppingCartListHelper.instance;
	}
	private static class ShoppingCartListHelper
	{
		
		private static final ShoppingCartList instance = new ShoppingCartList();
	}
	////////////////////////////END OF SINGLETON PATTERN//////////////////////
	///////////////////////////Override Functions/////////////////////////////
	///Update views after adding
	@Override
	public void add(Product item, int quantity) 
	{
		if(!shoppingCartMap.containsKey(item))
		{
			shoppingCartMap.put(item, quantity);
			removeQuantity(item.getId(), quantity);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
	}
	///Update views after removing
	@Override
	public void remove(int id) 
	{
		Product toRemove = get(id);
		if(shoppingCartMap.containsKey(toRemove))
		{
			returnQuantity(id, shoppingCartMap.get(toRemove));
			shoppingCartMap.remove(toRemove);
			
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
	}
	///Update views after clearing
	@Override
	public void clear()
	{
		calculateTotal();
		productList.addRevenue(total);
		shoppingCartMap.clear();
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
	}
	
	@Override
	public Product get(int id) 
	{
		return productList.get(id);
	}
	///Update views after updating
	@Override
	public void setQuantity(int id, int quantity) 
	{
		Product toUpdate = get(id);
		if(shoppingCartMap.containsKey(toUpdate))
		{
			shoppingCartMap.put(toUpdate, quantity);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
	}
	@Override
	public double getTotal() 
	{
		return total;
		
	}
	@Override
	public void addChangeListener(ChangeListener listener)
	{
		listeners.add(listener);
	}
	///////////////////END OF OVERRIDE FUNCTIONS//////////////////////////////
	////////////////////Specific Functions////////////////////////////////
	///
	/**Calculates total value of the shopping cart. Update views after calculating. Call this before calling getTotal to get up to date value
	 *@author Richmond Liang
	 *@postcondition ProductList instantiated using singleton pattern
	 */
	public void calculateTotal() 
	{
		total = 0.00;
		if (shoppingCartMap.size() > 0)
		{
			for(Map.Entry<Product, Integer> entry : shoppingCartMap.entrySet())
			{
				total += (entry.getKey().getSellPrice() * entry.getValue());
			}
		}
	}
	//Add to shopping cart
	public void addQuantity(Product item, int quantity) 
	{
		if(shoppingCartMap.containsKey(item))
		{
			int newQuantity = shoppingCartMap.get(item) + quantity;
			shoppingCartMap.put(item, newQuantity);
			removeQuantity(item.getId(), quantity);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
	}
	///Set remaining quantity after an item has been added to cart
	public void removeQuantity(int id, int quantity) 
	{
		Product toUpdate = get(id);
		int current = toUpdate.getQuantity();
		productList.setQuantity(id, current - quantity);
	}
	///Set remaining quantity after an item has been returned
	public void returnQuantity(int id, int quantity) 
	{
		Product toUpdate = get(id);
		int current = toUpdate.getQuantity();
		productList.setQuantity(id, current + quantity);
	}
	//return map of shoppingcart list
	public Map<Product, Integer> getMap() 
	{
		return shoppingCartMap;
	}
	public void clearRefund()
	{
		for(Map.Entry<Product, Integer> entry: shoppingCartMap.entrySet())
		{
			returnQuantity(entry.getKey().getId(), entry.getValue());
		}
		shoppingCartMap.clear();
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
	}
}
