package product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import global.MsgWindow;


public class ProductList implements ItemList
{
	//Store products as values using product id as key
	private HashMap<Integer,Product> productListMap = null;
	//Used to generate unique Ids by incrementing by 1 every time a product is created and added
	//When reading from a serialized HashMap, start from the highest key value + 1
	private int listId;
	String filenameProduct = "ProductList.ser";
	String filenameProfit = "Profit.ser";
	private double revenue;
	private double cost;
	
	private ArrayList<ChangeListener> listeners;
	
	///////////////////////////Singleton Pattern and Constructor////////////////////////////////////
	////////////////////Use ProductList.getInstance() to instantiate///////////////////////
	/**private Constructor, instantiates a productList from deserializion, or a new one if it cannot. Uses singleton pattern so only one instance can exist 
	 *@author Richmond Liang
	 *@postcondition ProductList instantiated using singleton pattern
	 */
	private ProductList()
	{
		listeners = new ArrayList<>();
		File fProductList = new File(filenameProduct);
		File fProfit = new File(filenameProfit);
		
		//Attempt to deserialize
		if(fProductList.isFile() && fProductList.canRead())
		{
			ObjectInputStream in;
			try 
			{
				in = new ObjectInputStream(new FileInputStream(filenameProduct));
				try
				{
					productListMap = (HashMap<Integer, Product>) in.readObject();
					listId = Collections.max(productListMap.keySet()) + 1;		
					if(fProfit.isFile() && fProfit.canRead())
					{
						ObjectInputStream inProfit;
						try 
						{
							inProfit = new ObjectInputStream(new FileInputStream(filenameProfit));
							try
							{
								
								double[] profit = (double[]) inProfit.readObject();
								revenue = profit[0];
								cost = profit[1];
							}
							finally
							{
								inProfit.close();
							}	
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						} 
						catch (ClassNotFoundException e) 
						{
							e.printStackTrace();
						}
					}
				}
				finally
				{
					in.close();
				}	
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			productListMap = new HashMap<>();
			revenue = 0.00;
			cost = 0.00;
			listId = 1;
		}	
	}
	public static ProductList getInstance()
	{
		return ProductListHelper.instance;
	}
	private static class ProductListHelper
	{
		private static final ProductList instance = new ProductList();
	}
	///////////////////////////END OF SINGLETON PATTERN////////////////////////////
	//////////////////////////Override Functions//////////////////////////////////
	///Returns profit
	@Override
	public double getTotal() 
	{
		return revenue - cost;
	}
	
	/**adds new item to the Productlist
	 *@author Richmond Liang
	 *@param item Product to add
	 *@param quantity number of items to add
	 *@precondition productlist instantiated
	 *@postcondition Product with given id set to given quantity added to ProductList, listId incremented by 1
	 */
	@Override
	public void add(Product item, int quantity) 
	{
		if (!productListMap.containsKey(item.getId()))
		{
			cost =  cost + (item.getPurchasePrice() * quantity);
			item.setQuantity(quantity);
			productListMap.put(item.getId(), item);
			listId++;
			serialize();
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
		else
		{
			listId++;
			new MsgWindow("Product ID already taken. Try again");
		}
	}
	/**adds new item to the Productlist
	 *@author Richmond Liang
	 *@param id Product to remove
	 *@precondition productlist instantiated
	 *@postcondition Product with given id removed from ProductList
	 */
	@Override
	public void remove(int id) 
	{
		if (!productListMap.isEmpty())
		{
			if (productListMap.containsKey(id))
			{
				productListMap.remove(id);
				serialize();
				ChangeEvent event = new ChangeEvent(this);
				for (ChangeListener listener : listeners)
				{
					listener.stateChanged(event);
				}
			}
			else
			{
				new MsgWindow("Item does not exist");
			}
		}
		else
		{
			new MsgWindow("List does not exist");
		}
	}
	/**Clears entire product list
	 *@author Richmond Liang
	 *@precondition productlist instantiated
	 *@postcondition ProductList emptied
	 */
	@Override
	public void clear()
	{
		productListMap.clear();
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
		serialize();
	}
	/**Returns a product with the given id
	 *@author Richmond Liang
	 *@param id ID of Product to get
	 *@return Product
	 *@precondition productlist instantiated
	 *@postcondition returns Product
	 */
	@Override
	public Product get(int id) 
	{
		return productListMap.get(id);
	}
	/**sets quantity of item, does not affect profits
	 *@author Richmond Liang
	 *@param item Product to add
	 *@param quantity item quantity to change to
	 *@precondition productlist instantiated
	 *@postcondition Product quantity set to given quantity
	 */
	@Override
	public void setQuantity(int id, int quantity) 
	{
		if (productListMap.containsKey(id))
		{
			Product toUpdate = get(id);
			toUpdate.setQuantity(quantity);
			serialize();
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
		else
		{
			new MsgWindow("Item does not exist");
		}
	}
	
	@Override
	public void addChangeListener(ChangeListener listener)
	{
		listeners.add(listener);
	}
	///////////////////////END OF OVERRIDE FUNCTIONS/////////////////////////////////
	///////////////////////Unique Functions//////////////////////////////////////////
	/**Return next id to use for a new item
	 *@author Richmond Liang
	 *@precondition productlist instantiated
	 *@return int listId
	 *@postcondition returns listid
	 */
	public int getListId()
	{
		return listId;
	}
	public double getRevenue()
	{
		return revenue;
	}
	public double getCost()
	{
		return cost;
	}
	public void addRevenue(double value)
	{
		revenue += value;
		serialize();
	}
	public void addCost(double value)
	{
		cost += value;
		serialize();
	}
	///Setters
	public void updateName(int id, String name)
	{
		if (productListMap.containsKey(id))
		{
			get(id).setName(name);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
		else
		{
			new MsgWindow("Item does not exist");
		}
		
	}
	/**sets sell price of item to the given price. Does not affect profit
	 *@author Richmond Liang
	 *@precondition productlist instantiated
	 *@postcondition sell price updated, revenues and costs unchanged
	 */
	public void updateSellPrice(int id, double price)
	{
		if (productListMap.containsKey(id))
		{
			get(id).setSellPrice(price);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
		else
		{
			new MsgWindow("Item does not exist");
		}
	}
	/**sets purchase price of item to the given price. Does not affect profit
	 *@author Richmond Liang
	 *@precondition productlist instantiated
	 *@postcondition purchase price updated, revenues and costs unchanged
	 */
	public void updatePurchasePrice(int id, double price)
	{
		if (productListMap.containsKey(id))
		{
			get(id).setPurchasePrice(price);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
		else
		{
			new MsgWindow("Item does not exist");
		}
	}
	///Update description
	public void updateDescription(int id, String desc)
	{
		if (productListMap.containsKey(id))
		{
			get(id).setDescription(desc);
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners)
			{
				listener.stateChanged(event);
			}
		}
		else
		{
			new MsgWindow("Item does not exist");
		}
		
	}
	//return map of productList
	public Map<Integer, Product> getMap() 
	{
		return productListMap;
	}
	/**Serializes product list and revenues and costs to separate files
	 *@author Richmond Liang
	 *@precondition productlist instantiated, outputstreams are valid
	 *@postcondition creates or updates ProductList.ser and Profits.ser
	 */
	private void serialize()
	{
		ObjectOutputStream out;
		try 
		{
			out = new ObjectOutputStream(new FileOutputStream(filenameProduct));
			try
			{
				out.writeObject(productListMap);
			}
			finally
			{
				out.close();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		ObjectOutputStream outP;
		try 
		{
			outP = new ObjectOutputStream(new FileOutputStream(filenameProfit));
			try
			{
				double[] profit = {revenue, cost};
				outP.writeObject(profit);
			}
			finally
			{
				outP.close();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Serialized");
	}
	
	
}
