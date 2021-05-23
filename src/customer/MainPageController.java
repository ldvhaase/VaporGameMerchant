package customer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import customer.MainPageView.Sort;

public class MainPageController 
{
	private MainPageView view;
	
        /**
         * @author Richmond Liang, Lucas Haase
         * @param view page to be loaded in window
         * @precondition button clicked and new view needs to be loaded
         * @postcondition view loaded to window
         */
	public MainPageController(MainPageView view)
	{
		this.view = view;
		view.getBtnCart().addActionListener(open);
		view.getRdbtnName().addActionListener(sortName);
		view.getRdbtnPrice().addActionListener(sortQuantity);
		view.getRdbtnQuantity().addActionListener(sortSellPrice);
		
	}
	Action open = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	
	    	ShoppingCartView cart = new ShoppingCartView();
	    	cart.setVisible(true);
	    }
	};
	Action sortName = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.NAME);
	    	view.createPanels();
	    }
	};
	Action sortSellPrice = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.SELL_PRICE);
	    	view.createPanels();
	    }
	};
	Action sortQuantity = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	view.setSort(Sort.QUANTITY);
	    	view.createPanels();
	    }
	};
}
