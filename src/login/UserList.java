package login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import login.User.AccountType;
import product.Product;

public class UserList 
{
	private HashMap<String,User> userListMap; 
	private String filenameUserList = "UserList.ser";
	
	/**Constructor, creates a new UserList. For the purposes of this demo, it is prepoulated with a customer account and a seller account. Users are stored in hashmap using the username as a key and the user object as the value.
	 *@author Richmond Liang
	 *@precondition none
	 *@postcondition creates a new userlist 
	 */
	protected UserList()
	{
		File fUserList = new File(filenameUserList);
		if(fUserList .isFile() && fUserList .canRead())
		{
			ObjectInputStream in;
			try 
			{
				in = new ObjectInputStream(new FileInputStream(filenameUserList));
				try
				{
					userListMap = (HashMap<String, User>) in.readObject();
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
			userListMap = new HashMap<>();
			User user = new User("user", "pass", AccountType.CUSTOMER);
			User seller = new User("seller", "pass", AccountType.SELLER);
			userListMap.put(user.getUsername(),user);
			userListMap.put(seller.getUsername(),seller);
			serialize();
		}
	}
	
	public void add(User user)
	{
		userListMap.put(user.getUsername(), user);
		serialize();
	}
	///Get the hashmap
	protected Map<String, User> getUserList()
	{
		return userListMap;
	}
	private void serialize()
	{
		ObjectOutputStream out;
		try 
		{
			out = new ObjectOutputStream(new FileOutputStream(filenameUserList));
			try
			{
				out.writeObject(userListMap);
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
		System.out.println("Serialized");
	}
}
