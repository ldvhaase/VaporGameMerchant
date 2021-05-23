package login;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;

import customer.MainPageView;
import login.User.AccountType;
import seller.InventoryListView;

public class LoginPageController 
{
	private LoginPageView view;
	private UserList model;
	
	/**Constructor, adds action listeners to buttons and textfields. Pressing enter while focused on a field is the same as pressing submit
	 *@author Richmond Liang, Lucas Haase
	 *@param view the LoginPageView this controller controls
	 *@param model the UserList the controller checks against for logging in
	 *@precondition view and model initialized
	 *@postcondition controller attaches listeners to view
	 */
	public LoginPageController(LoginPageView view, UserList model)
	{
		this.view = view;
		this.model = model;
		view.getSubmit().addActionListener(action);
		view.getPassword().addActionListener(action);
		view.getUsername().addActionListener(action);	
		
		view.getBtnBack().addActionListener(back);
		view.getBtnRegister().addActionListener(toRegister);
		view.getBtnRegisterRegister().addActionListener(register);
	}
	/**Checks user and password by fetching a user by username from the list and then checking the password associated with the username. Uses strategy pattern
	 *@author Richmond Liang
	 *@precondition controller initialized
	 *@postcondition Closes the LoginPageView if login is successful, launches the proper page for the user
	 */
	
	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	if(view.getUsername().getText().equals("") || view.getPassword().getPassword().length == 0)
			{
				view.getMsg().setText("One or more fields are empty");
			}
			else
			{
				HashMap<String, User> userlist = (HashMap<String, User>) model.getUserList();
				if(userlist.containsKey(view.getUsername().getText()))
				{
					User user = userlist.get(view.getUsername().getText());
					if (user.getPassword().equals(new String(view.getPassword().getPassword())))
					{
						if (user.getType() == AccountType.CUSTOMER)
						{
							MainPageView customer = new MainPageView();
							customer.setVisible(true);
							view.getFrame().dispose();
						}
						else
						{			
							InventoryListView inventory = new InventoryListView(); 
							inventory.setVisible(true);
							view.getFrame().dispose();
						}
					}
					else
					{
						view.getMsg().setText("One or more fields are incorrect");
					}
				}
				else
				{
					view.getMsg().setText("One or more fields are incorrect");
				}
			}
	    }
	};
	Action toRegister = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        view.getRegisterPanel().setVisible(true);
	        view.getMainPanel().setVisible(false);
	    }
	};
	Action register = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	if(view.getRegisterUsername().getText().equals("") || view.getRegisterPassword().getText().equals("") || (!view.getRdbtnCustomer().isSelected() && !view.getRdbtnSeller().isSelected()))
			{
				view.getRegisterMsg().setText("One or more fields are empty");
			}
	    	else
	    	{
	    		String username = view.getRegisterUsername().getText();
	    		String password = view.getRegisterPassword().getText();
	    		AccountType type;
	    		if(view.getRdbtnCustomer().isSelected())
	    		{
	    			type = AccountType.CUSTOMER;
	    		}
	    		else
	    		{
	    			type = AccountType.SELLER;
	    		}
	    		User newUser = new User(username, password, type);
	    		model.add(newUser);
	    		view.getRegisterPanel().setVisible(false);
	    		view.getMainPanel().setVisible(true);
	    	}
	    }
	};
	Action back = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        view.getRegisterPanel().setVisible(false);
	        view.getRegisterUsername().setText("");
	        view.getRegisterPassword().setText("");
	        view.getRdbtnCustomer().setSelected(false);
	        view.getRdbtnSeller().setSelected(false);
	        view.getRegisterMsg().setText("");
	        view.getMainPanel().setVisible(true);
	    }
	};
			

}
