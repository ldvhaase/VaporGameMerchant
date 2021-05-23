package login;

import java.io.Serializable;

public class User implements Serializable
{
	enum AccountType
	{
		CUSTOMER, SELLER;
	}
	
	private String username;
	private String password;
	private AccountType type;
	
	/**Constructor, Creates a new user with the parameters
	 *@author Richmond Liang
	 *@param username username of the user
	 *@param password password of the user
	 *@param type account type of the user
	 *@postcondition user created
	 */
	public User(String username, String password, AccountType type)
	{
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	///Getters
	public String getUsername() 
	{
		return username;
	}

	public String getPassword() 
	{
		return password;
	}

	public AccountType getType() 
	{
		return type;
	}

	///Equals
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		else if (o == null || this.getClass() != o.getClass())
		{
			return false;
		}
		User obj = (User) o;
		return this.username.equals(obj.username) && this.password.equals(obj.password) && this.type.equals(obj.type);	
	}
	
	///hashcode
	@Override
	public int hashCode()
	{
		return username.hashCode() + password.hashCode() + type.hashCode();
	}
}
