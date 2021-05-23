package global;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import seller.AddItemView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MsgWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	/**Instantiates a small error window displaying the given error message
	 *@author Richmond Liang
	 *@param msg message to display
	 *@postcondition Error Window displayed with the given message
	 */
	public MsgWindow(String msg) 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[530.00,center]", "[200.00,fill][124.00,fill]"));
		
		JLabel lblMsg = new JLabel("");
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMsg, "cell 0 0");
		lblMsg.setText("<html>" + msg + "</html");
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(close);
		contentPane.add(btnOk, "cell 0 1");
	}
	Action close = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	dispose();
	    }
	};
}
