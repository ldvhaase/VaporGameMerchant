package customer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import product.ShoppingCartList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckoutWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ShoppingCartList cartList;

	/**
	 * Create the dialog window
         * @postcondition checkout window will be opened
         * @param total value of all items in shopping cart
	 */
	public CheckoutWindow(double total) 
	{
		setAlwaysOnTop(true);
		cartList = ShoppingCartList.getInstance();
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 284, 161);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel receiptPanel = new JPanel();
		receiptPanel.setBounds(0, 0, 284, 161);
		contentPanel.add(receiptPanel);
		receiptPanel.setLayout(null);
		
		JLabel lblThanks = new JLabel("<html>Thank you for your business. Enjoy your new games!</html");
		lblThanks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThanks.setBounds(10, 0, 264, 76);
		receiptPanel.add(lblThanks);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnOk.setBounds(98, 127, 89, 23);
		receiptPanel.add(btnOk);
		
		NumberFormat format = new DecimalFormat("#0.00");
		JLabel lblYourTotal = new JLabel("Your Total: " + format.format(total));
		lblYourTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourTotal.setBounds(20, 87, 234, 14);
		receiptPanel.add(lblYourTotal);
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(74, 12, 137, 137);
		contentPanel.add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("Checkout?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel, BorderLayout.CENTER);
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				cartList.clear();
				mainPanel.setVisible(false);
				receiptPanel.setVisible(true);
			}
		});
		mainPanel.add(btnConfirm, BorderLayout.SOUTH);
		receiptPanel.setVisible(false);
		mainPanel.setVisible(true);
	}
}
